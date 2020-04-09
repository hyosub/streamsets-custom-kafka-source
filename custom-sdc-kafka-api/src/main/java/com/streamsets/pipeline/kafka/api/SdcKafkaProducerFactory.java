package com.streamsets.pipeline.kafka.api;

public abstract class SdcKafkaProducerFactory {

  public static SdcKafkaProducerFactory create(ProducerFactorySettings settings) {
    SdcKafkaProducerFactory factory = FactoriesBean.getKafkaProducerFactory();
    factory.init(settings);
    return factory;

  }

  protected abstract void init(ProducerFactorySettings settings);

  public abstract SdcKafkaProducer create();

}
