package com.test.mina.udp.model;

import com.test.mina.annotation.MessageMeta;
import com.test.mina.udp.Message;

@MessageMeta(
   module = 1013
)
public class Ping extends Message {
   public int checkData1;
   public long checkData2;
}
