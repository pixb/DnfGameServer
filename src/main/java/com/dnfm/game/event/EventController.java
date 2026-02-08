package com.dnfm.game.event;

import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.REQ_EVENT_GET_REWARD;
import com.dnfm.mina.protobuf.REQ_QUERY_ACCESS_TIME_BY_EVENT;
import com.dnfm.mina.protobuf.RES_EVENT_GET_REWARD;
import com.dnfm.mina.protobuf.RES_QUERY_ACCESS_TIME_BY_EVENT;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
   @RequestMapping
   public void ReqQueryAccessTimeByEvent(IoSession session, REQ_QUERY_ACCESS_TIME_BY_EVENT reqContentsPreviewReward) {
      RES_QUERY_ACCESS_TIME_BY_EVENT resQueryAccessTimeByEvent0 = new RES_QUERY_ACCESS_TIME_BY_EVENT();
      resQueryAccessTimeByEvent0.accesstime = TimeUtil.currS();
      MessagePusher.pushMessage((IoSession)session, resQueryAccessTimeByEvent0);
   }

   @RequestMapping
   public void ReqEventGetReward(IoSession session, REQ_EVENT_GET_REWARD reqEventGetReward) {
      RES_EVENT_GET_REWARD resEventGetReward = new RES_EVENT_GET_REWARD();
      resEventGetReward.error = 3;
      MessagePusher.pushMessage((IoSession)session, resEventGetReward);
   }
}
