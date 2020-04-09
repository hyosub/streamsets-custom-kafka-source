package com.streamsets.pipeline.kafka.api;

import java.util.Map;

public class FullMessageAndOffsetWithTimestamp extends FullMessageAndOffset {

  private final long timestamp;
  private final String timestampType;

  public FullMessageAndOffsetWithTimestamp(
          Object messageKey,
          Object payload,
          long offset,
          int partition,
          long timestamp,
          String timestampType
  ) {
    super(messageKey, payload, offset, partition);
    this.timestamp = timestamp;
    this.timestampType = timestampType;
  }

  public FullMessageAndOffsetWithTimestamp(
          Map<String, byte[]> headers,
          Object messageKey,
          Object payload,
          long offset,
          int partition,
          long timestamp,
          String timestampType
  ) {
    super(headers, messageKey, payload, offset, partition);
    this.timestamp = timestamp;
    this.timestampType = timestampType;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getTimestampType() {
    return timestampType;
  }

}
