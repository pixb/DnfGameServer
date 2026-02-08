package com.test.mina.codec;

import com.test.mina.udp.Message;

public interface IMessageEncoder {
   byte[] writeMessageBody(Message var1);
}
