package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.udp.Message;

@MessageMeta(
   module = 1013
)
public class Ping extends Message {
   public int checkData1;
   public long checkData2;
}
