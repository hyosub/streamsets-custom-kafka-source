package com.streamsets.pipeline.stage.origin.kafka;

public abstract class KafkaSourceFactory {

  protected final KafkaConfigBean conf;

  public KafkaSourceFactory(KafkaConfigBean conf) {
    this.conf = conf;
  }

  public abstract BaseKafkaSource create();

}
