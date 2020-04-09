package com.streamsets.pipeline.kafka.impl;

import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class ExpressionPartitioner20 implements Partitioner {

  public ExpressionPartitioner20(VerifiableProperties props) {

  }

  public ExpressionPartitioner20() {

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

}
