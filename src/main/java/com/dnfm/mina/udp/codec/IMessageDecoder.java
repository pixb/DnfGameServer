package com.dnfm.mina.udp.codec;

import com.dnfm.mina.udp.Message;

public interface IMessageDecoder {
   Message readMessage(int var1, int var2, byte[] var3);
}
