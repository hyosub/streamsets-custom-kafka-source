package com.streamsets.pipeline.stage.origin.kafka;

import com.streamsets.pipeline.api.Label;

import static com.streamsets.pipeline.stage.origin.kafka.Deserializer.SerializerConstants.BYTE_ARRAY_DESERIALIZER;
import static com.streamsets.pipeline.stage.origin.kafka.Deserializer.SerializerConstants.KAFKA_AVRO_DESERIALIZER;
import static com.streamsets.pipeline.stage.origin.kafka.Deserializer.SerializerConstants.STRING_DESERIALIZER;

public enum Deserializer implements Label {

  STRING("String", STRING_DESERIALIZER, STRING_DESERIALIZER),
  DEFAULT("Default", BYTE_ARRAY_DESERIALIZER, BYTE_ARRAY_DESERIALIZER),
  CONFLUENT("Confluent", KAFKA_AVRO_DESERIALIZER, BYTE_ARRAY_DESERIALIZER);

  class SerializerConstants {
    static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    static final String BYTE_ARRAY_DESERIALIZER = "org.apache.kafka.common.serialization.ByteArrayDeserializer";
    static final String KAFKA_AVRO_DESERIALIZER = "io.confluent.kafka.serializers.KafkaAvroDeserializer";

    private SerializerConstants() {}
  }

  private final String label;
  private final String keyClass;
  private final String valueClass;

  Deserializer(String label, String keyClass, String valueClass) {
    this.label = label;
    this.keyClass = keyClass;
    this.valueClass = valueClass;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public String getKeyClass() {
    return keyClass;
  }

  public String getValueClass() {
    return valueClass;
  }

}
