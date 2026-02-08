package com.dnfm.cross.login.service;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.cross.core.callback.CReqCallBack;
import com.dnfm.cross.core.callback.CallbackHandler;
import com.dnfm.cross.core.server.SCSession;
import com.dnfm.cross.login.cmessage.C2GLoginCross;
import com.dnfm.game.utils.NumberUtil;
import org.springframework.stereotype.Component;

@Component
public class LoginCallBackHandler extends CallbackHandler {
   public void onRequest(SCSession session, CReqCallBack req) {
      String playerJson = (String)req.getParams().get("player");
      String authJson = (String)req.getParams().get("auth");
      int fromServer = NumberUtil.intValue(req.getParams().get("fromServer"));
      ((PlayerRemoteTransferService)SpringUtils.getBean(PlayerRemoteTransferService.class)).receiverPlayerData(session.getClientIp(), fromServer, playerJson, authJson);
      C2GLoginCross resp = new C2GLoginCross();
      resp.setResultCode(1);
      this.sendBack(session, req, resp);
   }

   public int cmdType() {
      return 2;
   }
}
