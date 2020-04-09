package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.Source;

import java.util.Map;

public class ConsumerFactorySettings {

  private final String zookeeperConnect;
  private final String bootstrapServers;
  private final String topic;
  private final int maxWaitTime;
  private final Source.Context context;
  private final Map<String, Object> kafkaConsumerConfigs;
  private final String consumerGroup;
  private final int batchSize;
  private final boolean isTimestampsEnabled;
  private final String kafkaAutoOffsetReset;
  private final long timestampToSearchOffsets;

  public ConsumerFactorySettings(
          String zookeeperConnect,
          String bootstrapServers,
          String topic,
          int maxWaitTime,
          Source.Context context,
          Map<String, Object> kafkaConsumerConfigs,
          String consumerGroup,
          int batchSize,
          boolean isTimestampsEnabled,
          String kafkaAutoOffsetReset,
          long timestampToSearchOffsets

  ) {
    this.zookeeperConnect = zookeeperConnect;
    this.bootstrapServers = bootstrapServers;
    this.topic = topic;
    this.maxWaitTime = maxWaitTime;
    this.context = context;
    this.kafkaConsumerConfigs = kafkaConsumerConfigs;
    this.consumerGroup = consumerGroup;
    this.batchSize = batchSize;
    this.isTimestampsEnabled = isTimestampsEnabled;
    this.kafkaAutoOffsetReset = kafkaAutoOffsetReset;
    this.timestampToSearchOffsets = timestampToSearchOffsets;
  }

  public String getBootstrapServers() {
    return bootstrapServers;
  }

  public String getTopic() {
    return topic;
  }

  public int getMaxWaitTime() {
    return maxWaitTime;
  }

  public Source.Context getContext() {
    return context;
  }

  public Map<String, Object> getKafkaConsumerConfigs() {
    return kafkaConsumerConfigs;
  }

  public String getConsumerGroup() {
    return consumerGroup;
  }

  public String getZookeeperConnect() {
    return zookeeperConnect;
  }

  public int getBatchSize() {
    return batchSize;
  }

  public boolean isTimestampsEnabled() {
    return isTimestampsEnabled;
  }

  public String getKafkaAutoOffsetReset() {
    return kafkaAutoOffsetReset;
  }

  public long getTimestampToSearchOffsets() {
    return timestampToSearchOffsets;
  }

}
