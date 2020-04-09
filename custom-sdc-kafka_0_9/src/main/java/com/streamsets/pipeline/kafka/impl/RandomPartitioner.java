package com.streamsets.pipeline.kafka.impl;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomPartitioner implements Partitioner {

  private final Random random = new Random();

  @Override
  public int partition(
          String topic,
          Object key,
          byte[] keyBytes,
          Object value,
          byte[] valueBytes,
          Cluster cluster
  ) {
    List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
    int availablePartitions = partitionInfos.size();
    return random.nextInt(availablePartitions);
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> map) {

  }

}
