package com.dnfm.cross.core.callback;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.cross.core.client.CCSession;
import com.dnfm.cross.core.server.RpcRequestMapping;
import com.dnfm.cross.core.server.SCSession;
import com.dnfm.game.utils.JsonUtils;
import com.dnfm.mina.protobuf.Message;
import org.springframework.stereotype.Controller;

@Controller
public class CallbackController {
   @RpcRequestMapping
   public void onReqCallBack(SCSession session, CReqCallBack req) {
      int cmdType = req.getCmd();
      CallbackHandler handler = CallbackHandler.queryHandler(cmdType);
      if (handler != null) {
         req.deserialize();
         handler.onRequest(session, req);
      }

   }

   @RpcRequestMapping
   public void onRespCallBack(CCSession session, CRespCallBack respCallBack) {
      String json = respCallBack.getData();

      try {
         Message message = (Message)JsonUtils.string2Object(json, Class.forName(respCallBack.getMsgClass()));
         ((CallBackService)SpringUtils.getBean(CallBackService.class)).fillCallBack(respCallBack.getIndex(), message);
      } catch (ClassNotFoundException var5) {
      }

   }
}
