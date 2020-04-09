package com.streamsets.pipeline.kafka.impl;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinPartitioner implements Partitioner {

  private final AtomicInteger counter = new AtomicInteger((new Random()).nextInt(Integer.MAX_VALUE));

  @Override
  public int partition(
          String topic,
          Object key,
          byte[] keyBytes,
          Object value,
          byte[] valueBytes,
          Cluster cluster
  ) {
    int availablePartitions = cluster.partitionCountForTopic(topic);
    int partition = counter.getAndIncrement() % availablePartitions;
    if (counter.get() == Integer.MAX_VALUE) {
      counter.set(0);
    }
    return partition;
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> map) {

  }

}
