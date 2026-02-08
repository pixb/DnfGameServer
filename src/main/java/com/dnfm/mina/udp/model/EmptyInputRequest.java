package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.udp.Message;

@MessageMeta(
   module = 1012
)
public class EmptyInputRequest extends Message {
   public int resendInputId;
}
