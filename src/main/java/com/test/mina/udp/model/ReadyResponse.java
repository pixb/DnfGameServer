package com.test.mina.udp.model;

import com.test.mina.annotation.MessageMeta;
import com.test.mina.udp.Message;

@MessageMeta(
   module = 1009
)
public class ReadyResponse extends Message {
   public byte errorCode;
}
