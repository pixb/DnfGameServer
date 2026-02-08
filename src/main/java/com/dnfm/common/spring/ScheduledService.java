package com.dnfm.common.spring;

import com.dnfm.common.start.GameServer;
import com.dnfm.game.ServerService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.RES_PING;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.task.ThreadLocalUtil;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.mina.core.session.IoSession;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {
   static int tmpIndex = 1;
   private final Logger logger = LoggerFactory.getLogger(ScheduledService.class);
   @Autowired
   Dao dao;
   @Autowired
   ServerService serverService;
   @Autowired
   EquipService equipService;
   @Autowired
   GameServer gameServer;
   @Autowired
   RoleService roleService;
   @Autowired
   PlayerService playerService;

   @Scheduled(
      cron = "0 0 6 * * ?"
   )
   public void dailyReset() {
      StopWatch stopWatch = new StopWatch();
      stopWatch.start();

      for(Role role : DataCache.ONLINE_ROLES.values()) {
         ThreadLocalUtil.addLocalTask((Role)role, () -> this.roleService.doDailyFiveReset(role));
      }

      stopWatch.stop();
      this.logger.warn("6点重置玩家数据，用时{}毫秒", stopWatch.getTime());
   }

   @Scheduled(
      cron = "*/2 * * * * ?"
   )
   public void sendHeartbeat() {
      for(IoSession session : SessionManager.INSTANCE.player2sessions.values()) {
         RES_PING resPing = new RES_PING();
         resPing.responsetime = 90;
         MessagePusher.pushMessage((IoSession)session, resPing);
      }

   }

   @Scheduled(
      cron = "*/60 * * * * ?"
   )
   public void printOnlineRole() {
      this.logger.error("========在线玩家人数-开始========");
      this.logger.error("========{}========", DataCache.ONLINE_ROLES.size());
      this.logger.error("========在线玩家人数-结束========");
   }
}
