package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.StageException;

import java.util.List;

public interface SdcKafkaLowLevelConsumer {

  public void init() throws StageException;

  public void destroy();

  public long getOffsetToRead(boolean fromBeginning) throws StageException;

  public List<FullMessageAndOffset> read(long offset) throws StageException;

  public String getVersion();

}
