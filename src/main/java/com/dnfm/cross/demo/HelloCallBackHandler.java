package com.dnfm.cross.demo;

import com.dnfm.cross.core.callback.CReqCallBack;
import com.dnfm.cross.core.callback.CallbackHandler;
import com.dnfm.cross.core.server.SCSession;
import org.springframework.stereotype.Component;

@Component
public class HelloCallBackHandler extends CallbackHandler {
   public void onRequest(SCSession session, CReqCallBack req) {
      CRespCrossHeartBeat resp = new CRespCrossHeartBeat();
      this.sendBack(session, req, resp);
   }

   public int cmdType() {
      return 1;
   }
}
