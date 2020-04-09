package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.kafka.api.ProducerFactorySettings;
import com.streamsets.pipeline.kafka.api.SdcKafkaProducer;
import com.streamsets.pipeline.kafka.api.SdcKafkaProducerFactory;

public class Kafka20ProducerFactory extends SdcKafkaProducerFactory {

  private ProducerFactorySettings settings;

  public Kafka20ProducerFactory() {
  }

  @Override
  protected void init(ProducerFactorySettings settings) {
    this.settings = settings;
  }

  @Override
  public SdcKafkaProducer create() {
    return new KafkaProducer20(
            settings.getMetadataBrokerList(),
            settings.getKafkaProducerConfigs(),
            settings.getPartitionStrategy(),
            settings.isSendWriteResponse()
    );
  }

}
