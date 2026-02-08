package com.test.mina.udp.model;

import com.test.mina.annotation.MessageMeta;
import com.test.mina.udp.Message;

@MessageMeta(
   module = 1005
)
public class LoginResponse extends Message {
   public byte errorCode;
   public short sessionId;
   public int playerId;
}
