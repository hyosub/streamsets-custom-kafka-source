package com.streamsets.pipeline.kafka.impl;


import com.streamsets.pipeline.kafka.api.SdcKafkaProducer;
import com.streamsets.pipeline.kafka.api.SdcKafkaProducerFactory;
import com.streamsets.pipeline.kafka.api.ProducerFactorySettings;

public class Kafka09ProducerFactory extends SdcKafkaProducerFactory {

  private ProducerFactorySettings settings;

  public Kafka09ProducerFactory() {
  }

  @Override
  protected void init(ProducerFactorySettings settings) {
    this.settings = settings;
  }

  @Override
  public SdcKafkaProducer create() {
    return new KafkaProducer09(
            settings.getMetadataBrokerList(),
            settings.getKafkaProducerConfigs(),
            settings.getPartitionStrategy(),
            settings.isSendWriteResponse()
    );
  }

}
