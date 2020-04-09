package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.kafka.api.*;

public class Kafka09FactoriesBean extends FactoriesBean {

  @Override
  public SdcKafkaProducerFactory createSdcKafkaProducerFactory() {
    return new Kafka09ProducerFactory();
  }

  @Override
  public SdcKafkaValidationUtilFactory createSdcKafkaValidationUtilFactory() {
    return new Kafka09ValidationUtilFactory();
  }

  @Override
  public SdcKafkaConsumerFactory createSdcKafkaConsumerFactory() {
    return new Kafka09ConsumerFactory();
  }

  @Override
  public SdcKafkaLowLevelConsumerFactory createSdcKafkaLowLevelConsumerFactory() {
    return new Kafka09LowLevelConsumerFactory();
  }

}
