package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.udp.Message;

@MessageMeta(
   module = 1005
)
public class LoginResponse extends Message {
   public byte errorCode;
   public short sessionId;
   public int playerId;
}
