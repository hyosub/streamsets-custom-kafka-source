package com.streamsets.pipeline.stage.origin.kafka;

import com.streamsets.pipeline.api.base.BaseEnumChooserValues;
import com.streamsets.pipeline.config.DataFormat;

public class DataFormatChooserValues extends BaseEnumChooserValues<DataFormat> {

  public DataFormatChooserValues() {
    super(
            DataFormat.AVRO,
            DataFormat.BINARY,
            DataFormat.DATAGRAM,
            DataFormat.DELIMITED,
            DataFormat.JSON,
            DataFormat.LOG,
            DataFormat.PROTOBUF,
            DataFormat.SDC_JSON,
            DataFormat.TEXT,
            DataFormat.XML
    );
  }

}
