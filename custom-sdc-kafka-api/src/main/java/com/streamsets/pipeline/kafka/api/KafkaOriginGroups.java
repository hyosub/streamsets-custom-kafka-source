package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum KafkaOriginGroups implements Label {

  KAFKA("Kafka"),
  DATA_FORMAT("Data Format");

  private final String label;

  private KafkaOriginGroups(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return this.label;
  }

}
