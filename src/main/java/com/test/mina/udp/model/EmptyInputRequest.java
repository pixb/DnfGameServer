package com.test.mina.udp.model;

import com.test.mina.annotation.MessageMeta;
import com.test.mina.udp.Message;

@MessageMeta(
   module = 1012
)
public class EmptyInputRequest extends Message {
   public int resendInputId;
}
