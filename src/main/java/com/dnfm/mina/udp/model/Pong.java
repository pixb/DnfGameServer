package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.udp.Message;

@MessageMeta(
   module = 1014
)
public class Pong extends Message {
   public int checkData1;
   public long checkData2;
}
