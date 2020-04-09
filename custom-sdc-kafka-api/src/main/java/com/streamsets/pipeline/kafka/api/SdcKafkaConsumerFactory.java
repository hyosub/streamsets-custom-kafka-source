package com.streamsets.pipeline.kafka.api;

public abstract class SdcKafkaConsumerFactory {

  public static SdcKafkaConsumerFactory create(ConsumerFactorySettings settings) {
    SdcKafkaConsumerFactory kafkaConsumerFactory = FactoriesBean.getKafkaConsumerFactory();
    kafkaConsumerFactory.init(settings);
    return kafkaConsumerFactory;
  }

  protected abstract void init(ConsumerFactorySettings settings);

  public abstract SdcKafkaConsumer create();

}
