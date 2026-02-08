package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.ListField;

public class InputInfo {
   @ListField(3)
   public byte[] inputData;
   public byte inputFlag;
   public long sendTime;
}
