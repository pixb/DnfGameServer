package com.dnfm.mina.codec;

import com.dnfm.mina.protobuf.Message;

public interface IMessageDecoder {
   Message readMessage(int var1, int var2, byte[] var3);
}
