package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum PartitionStrategy implements Label {

  RANDOM("Random"),
  ROUND_ROBIN("Round Robin"),
  EXPRESSION("Expression"),
  DEFAULT("Default");

  private final String label;

  PartitionStrategy(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return label;
  }

}
