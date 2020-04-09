package com.streamsets.pipeline.kafka.api;

import com.google.common.net.HostAndPort;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;

import java.util.List;
import java.util.Map;

public interface SdcKafkaValidationUtil {

  public List<HostAndPort> validateKafkaBrokerConnectionString(
          List<Stage.ConfigIssue> issues,
          String connectionString,
          String configGroupName,
          String configName,
          Stage.Context context
  );

  public List<HostAndPort> validateZkConnectionString(
          List<Stage.ConfigIssue> issues,
          String connectString,
          String configGroupName,
          String configName,
          Stage.Context context
  );

  public boolean validateTopicExistence(
          Stage.Context context,
          String groupName,
          String configName,
          List<HostAndPort> kafkaBrokers,
          String metadataBrokerList,
          String topic,
          Map<String, Object> kafkaProducerConfigs,
          List<Stage.ConfigIssue> issues,
          boolean producer
  );

  public int getPartitionCount(
          String metadataBrokerList,
          String topic,
          Map<String, Object> kafkaClientConfigs,
          int messageSendMaxRetries,
          long retryBackoffMs
  ) throws StageException;

  public String getVersion();

  public void createTopicIfNotExists(String topic, Map<String, Object> kafkaClientConfigs, String metadataBrokerList)
          throws StageException;

}
