package com.streamsets.pipeline.kafka.impl;

import com.google.common.net.HostAndPort;
import com.streamsets.pipeline.kafka.api.LowLevelConsumerFactorySettings;
import com.streamsets.pipeline.kafka.api.SdcKafkaLowLevelConsumer;
import com.streamsets.pipeline.kafka.api.SdcKafkaLowLevelConsumerFactory;

public class Kafka09LowLevelConsumerFactory extends SdcKafkaLowLevelConsumerFactory {

  private LowLevelConsumerFactorySettings settings;

  @Override
  protected void init(LowLevelConsumerFactorySettings settings) {
    this.settings = settings;
  }

  @Override
  public SdcKafkaLowLevelConsumer create() {
    return new KafkaLowLevelConsumer09(
            settings.getTopic(),
            settings.getPartition(),
            HostAndPort.fromParts(settings.getBrokerHost(), settings.getBrokerPort()),
            settings.getMinFetchSize(),
            settings.getMaxFetchSize(),
            settings.getMaxWaitTime(),
            settings.getClientName()
    );
  }

}
