package com.streamsets.pipeline.kafka.impl;

import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class ExpressionPartitioner implements Partitioner, kafka.producer.Partitioner {

  public ExpressionPartitioner(VerifiableProperties props) {
  }

  public ExpressionPartitioner() {
  }

  @Override
  public int partition(
          String topic,
          Object key,
          byte[] keyBytes,
          Object value,
          byte[] valueBytes,
          Cluster cluster
  ) {
    return Integer.parseInt((String)key);
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> map) {

  }

  @Override
  public int partition(Object key, int numPartitions) {
    return Integer.parseInt((String)key);
  }

}
