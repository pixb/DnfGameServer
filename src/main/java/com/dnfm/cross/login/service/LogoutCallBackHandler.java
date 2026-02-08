package com.dnfm.cross.login.service;

import com.dnfm.cross.core.callback.CReqCallBack;
import com.dnfm.cross.core.callback.CallbackHandler;
import com.dnfm.cross.core.server.SCSession;
import org.springframework.stereotype.Component;

@Component
public class LogoutCallBackHandler extends CallbackHandler {
   public void onRequest(SCSession session, CReqCallBack req) {
   }

   public int cmdType() {
      return 3;
   }
}
