package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum KafkaDestinationGroups implements Label {

  KAFKA("Kafka"),
  DATA_FORMAT("Data Format"),
  RESPONSE("Response");

  private final String label;

  private KafkaDestinationGroups(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return this.label;
  }

}
