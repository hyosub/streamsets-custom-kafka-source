package com.streamsets.pipeline.stage.origin.kafka;

import com.streamsets.pipeline.api.*;
import com.streamsets.pipeline.api.base.configurablestage.DClusterSourceOffsetCommitter;
import com.streamsets.pipeline.api.impl.ClusterSource;
import com.streamsets.pipeline.kafka.api.KafkaOriginGroups;

@StageDef(
  version = 9,
  label = "Kafka Consumer",
  description = "Reads data from Kafka",
  execution = {ExecutionMode.CLUSTER_YARN_STREAMING, ExecutionMode.CLUSTER_MESOS_STREAMING, ExecutionMode.STANDALONE},
  libJarsRegex = {"spark-streaming-kafka.*", "kafka_\\d+.*", "kafka-clients-\\d+.*", "metrics-core-\\d+.*"},
  icon = "kafka.png",
  recordsByRef = true,
  upgrader = KafkaSourceUpgrader.class,
  upgraderDef = "upgrader/KafkaDSource.yaml",
  onlineHelpRefUrl ="index.html?contextID=task_npx_xgf_vq"
)
@RawSource(rawSourcePreviewer = KafkaRawSourcePreviewer.class, mimeType = "*/*")
@ConfigGroups(value = KafkaOriginGroups.class)
@HideConfigs(value = {"kafkaConfigBean.dataFormatConfig.compression"})
@GenerateResourceBundle
public class KafkaDSource extends DClusterSourceOffsetCommitter implements ErrorListener {

  @ConfigDefBean
  public KafkaConfigBean kafkaConfigBean;

  private DelegatingKafkaSource delegatingKafkaSource;

  @Override
  protected Source createSource() {
    delegatingKafkaSource = new DelegatingKafkaSource(new StandaloneKafkaSourceFactory(kafkaConfigBean),
            new ClusterKafkaSourceFactory(kafkaConfigBean));
    return delegatingKafkaSource;
  }

  @Override
  public Source getSource() {
    return source != null?  delegatingKafkaSource.getSource(): null;
  }

  @Override
  public void errorNotification(Throwable throwable) {
    DelegatingKafkaSource delegatingSource = delegatingKafkaSource;
    if (delegatingSource != null) {
      Source source = delegatingSource.getSource();
      if (source instanceof ErrorListener) {
        ((ErrorListener)source).errorNotification(throwable);
      }
    }
  }

  @Override
  public void shutdown() {
    DelegatingKafkaSource delegatingSource = delegatingKafkaSource;
    if (delegatingSource != null) {
      Source source = delegatingSource.getSource();
      if (source instanceof ClusterSource) {
        ((ClusterSource)source).shutdown();
      }
    }
  }

}
