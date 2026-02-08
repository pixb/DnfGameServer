package com.dnfm.cross.demo;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.cross.core.CrossTransportManager;
import com.dnfm.cross.core.callback.CReqCallBack;
import com.dnfm.cross.core.client.C2SSessionPoolFactory;
import com.dnfm.cross.core.client.CCSession;
import com.dnfm.mina.protobuf.Message;
import org.springframework.stereotype.Service;

@Service
public class CrossDemoGameService {
   public void sayHello() {
      CReqCallBack req = new CReqCallBack();
      req.addParam("name", "Lily");
      req.setCmd(1);
      CCSession session = C2SSessionPoolFactory.getInstance().borrowCrossSession();

      try {
         Message callBack = ((CrossTransportManager)SpringUtils.getBean(CrossTransportManager.class)).callBack(session, req);
         System.out.println(callBack);
      } catch (Exception var4) {
      }

   }
}
