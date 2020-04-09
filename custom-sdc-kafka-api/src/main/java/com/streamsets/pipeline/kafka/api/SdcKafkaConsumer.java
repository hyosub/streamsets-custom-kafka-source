package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;

import java.util.List;

public interface SdcKafkaConsumer {

  public void validate(List<Stage.ConfigIssue> issues, Stage.Context context);

  public void init() throws StageException;

  public void destroy();

  public void commit();

  public FullMessageAndOffset read() throws StageException;

  public String getVersion();

}
