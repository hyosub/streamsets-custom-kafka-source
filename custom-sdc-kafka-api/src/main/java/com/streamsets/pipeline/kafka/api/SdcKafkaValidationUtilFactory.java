package com.streamsets.pipeline.kafka.api;

public abstract class SdcKafkaValidationUtilFactory {

  public static SdcKafkaValidationUtilFactory getInstance() {
    return FactoriesBean.getKafkaValidationUtilFactory();
  }

  public abstract SdcKafkaValidationUtil create();

}
