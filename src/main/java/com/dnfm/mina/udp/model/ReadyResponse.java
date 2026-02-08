package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.udp.Message;

@MessageMeta(
   module = 1009
)
public class ReadyResponse extends Message {
   public byte errorCode;
}
