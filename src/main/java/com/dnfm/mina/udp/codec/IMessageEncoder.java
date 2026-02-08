package com.dnfm.mina.udp.codec;

import com.dnfm.mina.udp.Message;

public interface IMessageEncoder {
   byte[] writeMessageBody(Message var1);
}
