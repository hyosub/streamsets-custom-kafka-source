package com.streamsets.pipeline.stage.origin.kafka;

import com.streamsets.pipeline.api.BatchMaker;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.lineage.EndPointType;
import com.streamsets.pipeline.api.lineage.LineageEvent;
import com.streamsets.pipeline.api.lineage.LineageEventType;
import com.streamsets.pipeline.api.lineage.LineageSpecificAttribute;
import com.streamsets.pipeline.kafka.api.FullMessageAndOffset;
import com.streamsets.pipeline.kafka.api.FullMessageAndOffsetWithTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StandaloneKafkaSource extends BaseKafkaSource {

  private static final Logger LOG = LoggerFactory.getLogger(StandaloneKafkaSource.class);
  private static final String DEFAULT_HEADER_VALUE = "";

  public StandaloneKafkaSource(KafkaConfigBean conf) {
    super(conf);
  }

  @Override
  protected List<ConfigIssue> init() {
    List<ConfigIssue> issues = super.init();

    if (issues.isEmpty()) {
      if (getContext().isPreview()) {
        //set fixed batch duration time of 1 second for preview.
        conf.maxWaitTime = 1000;
      }
      try {
        kafkaConsumer.init();
        LOG.info("Successfully initialized Kafka Consumer");
      } catch (StageException ex) {
        issues.add(getContext().createConfigIssue(null, null, ex.getErrorCode(), ex.getParams()));
      }
    }

    LineageEvent event = getContext().createLineageEvent(LineageEventType.ENTITY_READ);
    event.setSpecificAttribute(LineageSpecificAttribute.ENDPOINT_TYPE, EndPointType.KAFKA.name());
    event.setSpecificAttribute(LineageSpecificAttribute.ENTITY_NAME, conf.topic);
    event.setSpecificAttribute(LineageSpecificAttribute.DESCRIPTION, conf.consumerGroup);
    getContext().publishLineageEvent(event);

    return issues;
  }

  private String getMessageID(FullMessageAndOffset message) {
    return conf.topic + "::" + message.getPartition() + "::" + message.getOffset();
  }

  @Override
  public String produce(String lastSourceOffset, int maxBatchSize, BatchMaker batchMaker) throws StageException {
    int recordCounter = 0;
    int batchSize = conf.maxBatchSize > maxBatchSize ? maxBatchSize : conf.maxBatchSize;
    long startTime = System.currentTimeMillis();
    while (recordCounter < batchSize && (startTime + conf.maxWaitTime) > System.currentTimeMillis()) {
      FullMessageAndOffset message = kafkaConsumer.read();

      if (message != null) {
        if (isMatchedHeader(message, conf)) {
          String messageId = getMessageID(message);
          List<Record> records;

          if (conf.timestampsEnabled && message instanceof FullMessageAndOffsetWithTimestamp){
            records = processKafkaMessageDefault(
                    message.getMessageKey(),
                    String.valueOf(message.getPartition()),
                    message.getOffset(),
                    messageId,
                    (byte[]) message.getPayload(),
                    ((FullMessageAndOffsetWithTimestamp) message).getTimestamp(),
                    ((FullMessageAndOffsetWithTimestamp) message).getTimestampType()
            );
          } else {
            records = processKafkaMessageDefault(
                    message.getMessageKey(),
                    String.valueOf(message.getPartition()),
                    message.getOffset(),
                    messageId,
                    (byte[]) message.getPayload()
            );
          }

          // If we are in preview mode, make sure we don't send a huge number of messages.
          if (getContext().isPreview() && recordCounter + records.size() > batchSize) {
            records = records.subList(0, batchSize - recordCounter);
          }

          for (Record record : records) {
            batchMaker.addRecord(record);
          }

          recordCounter += records.size();
        }
      }
    }
    return lastSourceOffset;
  }

  @Override
  public void destroy() {
    if (kafkaConsumer != null) {
      kafkaConsumer.destroy();
    }
    super.destroy();
  }

  @Override
  public void commit(String offset) throws StageException {
    kafkaConsumer.commit();
  }

  private boolean isMatchedHeader(FullMessageAndOffset message, KafkaConfigBean config) {
    String headerName = config.headerName;
    String headerValue = convertHeaderValue(message.getHeaderValue(headerName));

    LOG.info("headerName : {}", headerName);

    if (headerName == null || headerName.equals("")) {
      LOG.info("headerName is empty. Proceed.");
      return true;
    } else if (headerName.equals(config.headerName) && headerValue.equals(config.headerValue)) {
      LOG.info("headerName : {}, headerValue: {}", headerName, headerValue);
      LOG.info("headerName and headerValue matched. Proceed.");
      return true;
    }
    return false;
  }

  private String convertHeaderValue(byte[] headerValueBytes) {
    if (headerValueBytes != null && headerValueBytes.length > 0) {
      return new String(headerValueBytes);
    }
    return DEFAULT_HEADER_VALUE;
  }

}
