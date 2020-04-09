package com.streamsets.pipeline.kafka.api;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum ProducerKeyFormat implements Label {

  STRING("String"),
  AVRO("Avro");

  private final String label;

  ProducerKeyFormat(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return label;
  }

}
