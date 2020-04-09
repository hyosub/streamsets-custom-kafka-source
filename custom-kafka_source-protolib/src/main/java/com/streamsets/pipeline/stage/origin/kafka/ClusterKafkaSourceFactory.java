package com.streamsets.pipeline.stage.origin.kafka;

public class ClusterKafkaSourceFactory extends KafkaSourceFactory {

  private static final String CLUSTER_MODE_CLASS = "io.hyosub.streamsets.pipeline.stage.origin.kafka.cluster.ClusterKafkaSource";

  public ClusterKafkaSourceFactory(KafkaConfigBean config) {
    super(config);
  }

  @Override
  public BaseKafkaSource create() {
    try {
      Class clusterModeClazz = Class.forName(CLUSTER_MODE_CLASS);
      return (BaseKafkaSource) clusterModeClazz.getConstructor(KafkaConfigBean.class)
              .newInstance(new Object[]{conf});
    } catch (Exception e) {
      throw new IllegalStateException("Exception while invoking kafka instance in cluster mode: " + e, e);
    }
  }

}
