package com.dnfm.mina;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.SessionUtils;
import java.net.InetSocketAddress;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ModuleCounterSchedule {
   private static final Logger log = LoggerFactory.getLogger(ModuleCounterSchedule.class);

   public static void disconnected(IoSession session) {
      Role role = SessionUtils.getRoleBySession(session);
      String name = role == null ? "没有角色" : role.getName();
      String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
      log.error("[MODULE_COUNTER] 超时踢出 {} 玩家信息: {} Counter数据: {}", clientIP, name);
      session.closeNow();
   }
}
