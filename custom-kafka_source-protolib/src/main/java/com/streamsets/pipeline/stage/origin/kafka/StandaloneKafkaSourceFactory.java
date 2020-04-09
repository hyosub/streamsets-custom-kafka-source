package com.streamsets.pipeline.stage.origin.kafka;

public class StandaloneKafkaSourceFactory extends KafkaSourceFactory {

  public StandaloneKafkaSourceFactory(KafkaConfigBean conf) {
    super(conf);
  }

  @Override
  public BaseKafkaSource create() {
    return new StandaloneKafkaSource(conf);
  }

}
