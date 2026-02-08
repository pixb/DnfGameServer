package com.test.mina.udp.model;

import com.test.mina.annotation.ListField;

public class RelayData {
   public int playerId;
   public int recvDelayMs;
   @ListField(3)
   public byte[] data;
   public long sendTime;
}
