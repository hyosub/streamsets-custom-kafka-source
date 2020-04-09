package com.streamsets.pipeline.kafka.api;

import java.util.HashMap;
import java.util.Map;

public class FullMessageAndOffset {

  private final Map<String, byte[]> headers;
  private final Object messageKey;
  private final Object payload;
  private final long offset;
  private final int partition;

  public FullMessageAndOffset(Object messageKey, Object payload, long offset, int partition) {
    this(new HashMap<>(), messageKey, payload, offset, partition);
  }

  public FullMessageAndOffset(Map<String, byte[]> headers, Object messageKey, Object payload, long offset, int partition) {
    this.headers = headers;
    this.messageKey = messageKey;
    this.payload = payload;
    this.offset = offset;
    this.partition = partition;
  }

  public byte[] getHeaderValue(String headerKey) {
    if (isExistHeader(headerKey)) {
      return headers.get(headerKey);
    }
    return null;
  }

  public boolean isExistHeader(String headerKey) {
    return headers.containsKey(headerKey);
  }

  public Map<String, byte[]> getHeaders() {
    return headers;
  }

  public Object getMessageKey() {
    return messageKey;
  }

  public Object getPayload() {
    return payload;
  }

  public long getOffset() {
    return offset;
  }

  public int getPartition() {
    return partition;
  }

}
