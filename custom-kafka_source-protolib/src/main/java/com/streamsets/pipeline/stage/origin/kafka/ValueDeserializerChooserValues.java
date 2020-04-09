package com.streamsets.pipeline.stage.origin.kafka;

import com.streamsets.pipeline.api.base.BaseEnumChooserValues;

public class ValueDeserializerChooserValues extends BaseEnumChooserValues<Deserializer> {

  public ValueDeserializerChooserValues() {
    super(Deserializer.DEFAULT, Deserializer.CONFLUENT);
  }

}

