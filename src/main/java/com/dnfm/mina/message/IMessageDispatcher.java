package com.dnfm.mina.message;

import com.dnfm.mina.protobuf.Message;
import org.apache.mina.core.session.IoSession;

public interface IMessageDispatcher {
   void dispatch(IoSession var1, Message var2);
}
