package com.test.mina.udp.model;

import com.test.mina.annotation.ListField;

public class InputInfo {
   @ListField(3)
   public byte[] inputData;
   public byte inputFlag;
   public long sendTime;
}
