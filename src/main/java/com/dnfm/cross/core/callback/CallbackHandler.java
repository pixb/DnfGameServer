package com.dnfm.cross.core.callback;

import com.dnfm.cross.core.server.SCSession;
import com.dnfm.mina.protobuf.Message;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

public abstract class CallbackHandler {
   private static final Map<Integer, CallbackHandler> handlers = new HashMap();

   public static CallbackHandler queryHandler(int type) {
      return (CallbackHandler)handlers.get(type);
   }

   @PostConstruct
   private void init() {
      handlers.put(this.cmdType(), this);
   }

   public abstract void onRequest(SCSession var1, CReqCallBack var2);

   public void sendBack(SCSession session, CReqCallBack req, Message response) {
      CRespCallBack callBack = CRespCallBack.valueOf(response);
      callBack.setIndex(req.getIndex());
      session.sendMessage(callBack);
   }

   public abstract int cmdType();
}
