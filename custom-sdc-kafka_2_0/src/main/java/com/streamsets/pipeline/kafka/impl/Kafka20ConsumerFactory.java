package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.kafka.api.SdcKafkaConsumer;
import com.streamsets.pipeline.kafka.api.ConsumerFactorySettings;
import com.streamsets.pipeline.kafka.api.SdcKafkaConsumerFactory;

public class Kafka20ConsumerFactory extends SdcKafkaConsumerFactory {

  private ConsumerFactorySettings settings;

  public Kafka20ConsumerFactory() {
  }

  @Override
  protected void init(ConsumerFactorySettings settings) {
    this.settings = settings;
  }

  @Override
  public SdcKafkaConsumer create() {
    return new KafkaConsumer20(
            settings.getBootstrapServers(),
            settings.getTopic(),
            settings.getConsumerGroup(),
            settings.getKafkaConsumerConfigs(),
            settings.getContext(),
            settings.getBatchSize(),
            settings.isTimestampsEnabled(),
            settings.getKafkaAutoOffsetReset(),
            settings.getTimestampToSearchOffsets()
    );
  }

}
