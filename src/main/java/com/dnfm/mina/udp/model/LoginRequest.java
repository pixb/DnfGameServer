package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.annotation.StringField;
import com.dnfm.mina.udp.Message;

@MessageMeta(
   module = 1004
)
public class LoginRequest extends Message {
   @StringField(1)
   public String accessToken;
}
