package com.test.mina.codec;

import com.test.mina.udp.Message;

public interface IMessageDecoder {
   Message readMessage(int var1, int var2, byte[] var3);
}
