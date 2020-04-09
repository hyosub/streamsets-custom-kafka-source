package com.streamsets.pipeline.kafka.impl;

import com.streamsets.pipeline.api.Source;

import java.util.Map;

public class KafkaConsumer11 extends BaseKafkaConsumer11 {

  public KafkaConsumer11(
          String bootStrapServers,
          String topic,
          String consumerGroup,
          Map<String, Object> kafkaConsumerConfigs,
          Source.Context context,
          int batchSize,
          boolean isTimestampEnabled,
          String kafkaAutoOffsetReset,
          long timestampToSearchOffsets
  ) {
    super(
            bootStrapServers,
            topic,
            consumerGroup,
            kafkaConsumerConfigs,
            context,
            batchSize,
            isTimestampEnabled,
            kafkaAutoOffsetReset,
            timestampToSearchOffsets
    );
  }

}
