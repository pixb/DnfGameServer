package com.test.mina.udp.model;

import com.test.mina.annotation.MessageMeta;
import com.test.mina.annotation.StringField;
import com.test.mina.udp.Message;

@MessageMeta(
   module = 1004
)
public class LoginRequest extends Message {
   @StringField(1)
   public String accessToken;
}
