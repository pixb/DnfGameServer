package com.dnfm.mina.codec;

import com.dnfm.mina.protobuf.Message;

public interface IMessageEncoder {
   byte[] writeMessageBody(Message var1);
}
