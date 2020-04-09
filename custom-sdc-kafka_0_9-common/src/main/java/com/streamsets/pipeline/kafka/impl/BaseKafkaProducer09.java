package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.lib.kafka.KafkaErrors;
import com.streamsets.pipeline.kafka.api.SdcKafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.RecordTooLargeException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class BaseKafkaProducer09 implements SdcKafkaProducer {

  private Producer producer;
  private final List<Future<RecordMetadata>> futureList;
  private final boolean sendWriteResponse;

  public BaseKafkaProducer09(boolean sendWriteResponse) {
    this.futureList = new ArrayList<>();
    this.sendWriteResponse = sendWriteResponse;
  }

  @Override
  public void init() throws StageException {
    producer = createKafkaProducer();
  }

  @Override
  public void destroy() {
    if(producer != null) {
      producer.close();
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void enqueueMessage(String topic, Object message, Object messageKey) {
    ProducerRecord e = new ProducerRecord<>(topic, messageKey, message);
    // send will place this record in the buffer to be batched later
    futureList.add(producer.send(e));
  }

  @Override
  public List<Record> write(Stage.Context context) throws StageException {
    // force all records in the buffer to be written out
    producer.flush();
    // make sure each record was written and handle exception if any
    List<Integer> failedRecordIndices = new ArrayList<Integer>();
    List<Exception> failedRecordExceptions = new ArrayList<Exception>();
    List<Record> responseRecords = new ArrayList<>();
    for (int i = 0; i < futureList.size(); i++) {
      Future<RecordMetadata> f = futureList.get(i);
      try {
        RecordMetadata recordMetadata = f.get();
        if (sendWriteResponse ) {
          Record record = context.createRecord("responseRecord");
          LinkedHashMap<String, Field> recordMetadataVal = new LinkedHashMap<>();
          recordMetadataVal.put("offset", Field.create(recordMetadata.offset()));
          recordMetadataVal.put("partition", Field.create(recordMetadata.partition()));
          recordMetadataVal.put("topic", Field.create(recordMetadata.topic()));
          record.set(Field.createListMap(recordMetadataVal));
          responseRecords.add(record);
        }
      } catch (InterruptedException | ExecutionException e) {
        Throwable actualCause = e.getCause();
        if (actualCause != null && actualCause instanceof RecordTooLargeException) {
          failedRecordIndices.add(i);
          failedRecordExceptions.add((Exception)actualCause);
        } else {
          throw createWriteException(e);
        }
      }
    }
    futureList.clear();
    if (!failedRecordIndices.isEmpty()) {
      throw new StageException(KafkaErrors.KAFKA_69, failedRecordIndices, failedRecordExceptions);
    }
    return responseRecords;
  }

  @Override
  public void clearMessages() {
    futureList.clear();
  }

  @Override
  public String getVersion() {
    return Kafka09Constants.KAFKA_VERSION;
  }

  protected abstract Producer<Object, byte[]> createKafkaProducer();

  protected abstract StageException createWriteException(Exception e);

}
