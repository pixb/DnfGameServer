package com.dnfm.game.tlog;

import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.REQ_TLOG_CONTROLLER_DATA;
import com.dnfm.mina.protobuf.RES_TLOG_CONTROLLER_DATA;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;

@Controller
public class TlogController {
   @RequestMapping
   public void reqTlogControllerData(IoSession session, REQ_TLOG_CONTROLLER_DATA req_tlog_controller_data) {
      RES_TLOG_CONTROLLER_DATA res_tlog_controller_data = new RES_TLOG_CONTROLLER_DATA();
      res_tlog_controller_data.transId = req_tlog_controller_data.transId;
      MessagePusher.pushMessage((IoSession)session, res_tlog_controller_data);
   }
}
