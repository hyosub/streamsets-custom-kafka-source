package com.streamsets.pipeline.kafka.api;

public abstract class SdcKafkaLowLevelConsumerFactory {

  public static SdcKafkaLowLevelConsumerFactory create(LowLevelConsumerFactorySettings settings) {
    SdcKafkaLowLevelConsumerFactory factory = FactoriesBean.getKafkaLowLevelConsumerFactory();
    factory.init(settings);
    return factory;
  }

  protected abstract void init(LowLevelConsumerFactorySettings settings);

  public abstract SdcKafkaLowLevelConsumer create();

}
