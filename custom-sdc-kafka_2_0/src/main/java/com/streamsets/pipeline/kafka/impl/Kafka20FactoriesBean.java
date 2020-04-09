package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.kafka.api.*;

public class Kafka20FactoriesBean extends FactoriesBean {

  @Override
  public SdcKafkaProducerFactory createSdcKafkaProducerFactory() {
    return new Kafka20ProducerFactory();
  }

  @Override
  public SdcKafkaValidationUtilFactory createSdcKafkaValidationUtilFactory() {
    return new Kafka09ValidationUtilFactory();
  }

  @Override
  public SdcKafkaConsumerFactory createSdcKafkaConsumerFactory() {
    return new Kafka20ConsumerFactory();
  }

  @Override
  public SdcKafkaLowLevelConsumerFactory createSdcKafkaLowLevelConsumerFactory() {
    return new Kafka09LowLevelConsumerFactory();
  }

}
