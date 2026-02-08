package com.dnfm.game.task;

import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.HeartBeatEvent;
import com.dnfm.listener.event.LoginEvent;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
   @EventHandler({EventType.HEART_BEAT})
   public void handleHeartBeat(HeartBeatEvent heartBeatEvent) {
   }

   @EventHandler({EventType.LOGIN})
   public void handleLoginEvent(LoginEvent loginEvent) {
   }
}
