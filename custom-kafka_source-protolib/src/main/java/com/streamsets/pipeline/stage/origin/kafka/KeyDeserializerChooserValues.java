package com.streamsets.pipeline.stage.origin.kafka;

import com.streamsets.pipeline.api.base.BaseEnumChooserValues;

public class KeyDeserializerChooserValues extends BaseEnumChooserValues<Deserializer> {

  public KeyDeserializerChooserValues() {
    super(Deserializer.class);
  }

}