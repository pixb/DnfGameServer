package com.test.mina.udp.model;

import com.test.mina.annotation.MessageMeta;
import com.test.mina.udp.Message;

@MessageMeta(
   module = 1016
)
public class Start extends Message {
   public int intVal;
}
