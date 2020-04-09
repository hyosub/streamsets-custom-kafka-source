package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.kafka.api.SdcKafkaValidationUtilFactory;

public class Kafka09ValidationUtilFactory extends SdcKafkaValidationUtilFactory {

  public Kafka09ValidationUtilFactory() {
  }

  @Override
  public KafkaValidationUtil09 create() {
    return new KafkaValidationUtil09();
  }
}
