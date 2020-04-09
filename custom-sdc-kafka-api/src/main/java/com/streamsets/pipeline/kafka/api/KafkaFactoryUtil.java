package com.streamsets.pipeline.kafka.api;

import com.google.common.io.Resources;
import com.streamsets.pipeline.api.impl.Utils;

import java.util.Properties;

public class KafkaFactoryUtil {

  private KafkaFactoryUtil() {}

  public static String getFactoryClass(String factoryDefinitionFile, String factoryClassKey) {
    Properties def = new Properties();
    try {
      def.load(Resources.getResource(factoryDefinitionFile).openStream());
    } catch (Exception e) {
      throw new RuntimeException(
              Utils.format("Error creating an instance of SdcKafkaConsumerFactory : {}", e.toString()),
              e
      );
    }
    return def.getProperty(factoryClassKey);
  }

}
