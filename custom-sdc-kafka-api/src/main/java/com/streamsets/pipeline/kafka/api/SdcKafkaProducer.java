package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;

import java.util.List;

public interface SdcKafkaProducer {

  public void init() throws StageException;

  public void destroy();

  public void enqueueMessage(String topic, Object message, Object messageKey);

  public List<Record> write(Stage.Context context) throws StageException;

  public void clearMessages();

  public String getVersion();

}
