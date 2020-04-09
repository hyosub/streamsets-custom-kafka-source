package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.config.DataFormat;

import java.util.Map;

public class ProducerFactorySettings {

  private final Map<String, Object> kafkaProducerConfigs;
  private final PartitionStrategy partitionStrategy;
  private final String metadataBrokerList;
  private final DataFormat dataFormat;
  private final boolean sendWriteResponse;

  public ProducerFactorySettings(
          Map<String, Object> kafkaProducerConfigs,
          PartitionStrategy partitionStrategy,
          String metadataBrokerList,
          DataFormat dataFormat,
          boolean sendWriteResponse
  ) {
    this.kafkaProducerConfigs = kafkaProducerConfigs;
    this.partitionStrategy = partitionStrategy;
    this.metadataBrokerList = metadataBrokerList;
    this.dataFormat = dataFormat;
    this.sendWriteResponse = sendWriteResponse;
  }

  public Map<String, Object> getKafkaProducerConfigs() {
    return kafkaProducerConfigs;
  }

  public PartitionStrategy getPartitionStrategy() {
    return partitionStrategy;
  }

  public String getMetadataBrokerList() {
    return metadataBrokerList;
  }

  public DataFormat getDataFormat() {
    return dataFormat;
  }

  public boolean isSendWriteResponse() {
    return sendWriteResponse;
  }

}
