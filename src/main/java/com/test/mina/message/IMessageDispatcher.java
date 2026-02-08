package com.test.mina.message;

import com.test.mina.udp.Message;
import org.apache.mina.core.session.IoSession;

public interface IMessageDispatcher {
   void dispatch(IoSession var1, Message var2);
}
