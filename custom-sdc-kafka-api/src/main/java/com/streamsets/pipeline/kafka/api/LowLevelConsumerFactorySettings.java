package com.streamsets.pipeline.kafka.api;

public class LowLevelConsumerFactorySettings {

  private final String topic;
  private final int partition;
  private final String brokerHost;
  private final int brokerPort;
  private final String clientName;
  private final int minFetchSize;
  private final int maxFetchSize;
  private final int maxWaitTime;

  public LowLevelConsumerFactorySettings(
          String topic,
          int partition,
          String brokerHost,
          int brokerPort,
          String clientName,
          int minFetchSize,
          int maxFetchSize,
          int maxWaitTime
  ) {
    this.topic = topic;
    this.partition = partition;
    this.brokerHost = brokerHost;
    this.brokerPort = brokerPort;
    this.clientName = clientName;
    this.minFetchSize = minFetchSize;
    this.maxFetchSize = maxFetchSize;
    this.maxWaitTime = maxWaitTime;
  }

  public String getTopic() {
    return topic;
  }

  public int getPartition() {
    return partition;
  }

  public String getBrokerHost() {
    return brokerHost;
  }

  public int getBrokerPort() {
    return brokerPort;
  }

  public String getClientName() {
    return clientName;
  }

  public int getMinFetchSize() {
    return minFetchSize;
  }

  public int getMaxFetchSize() {
    return maxFetchSize;
  }

  public int getMaxWaitTime() {
    return maxWaitTime;
  }

}
