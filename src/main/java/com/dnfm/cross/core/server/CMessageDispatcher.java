package com.dnfm.cross.core.server;

import com.dnfm.cross.core.client.CCSession;
import com.dnfm.mina.protobuf.Message;

public interface CMessageDispatcher {
   void serverDispatch(SCSession var1, Message var2);

   void clientDispatch(CCSession var1, Message var2);
}
