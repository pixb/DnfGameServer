package com.dnfm.mina.udp.message;

import com.dnfm.mina.udp.Message;
import org.apache.mina.core.session.IoSession;

public interface IMessageDispatcher {
   void dispatch(IoSession var1, Message var2);
}
