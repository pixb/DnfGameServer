package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.ListField;

public class RelayData {
   public int playerId;
   public int recvDelayMs;
   @ListField(3)
   public byte[] data;
   public long sendTime;
}
