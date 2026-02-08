package com.test.mina.cache;

import com.test.common.spring.SpringUtils;
import com.test.common.start.GameServer;
import com.test.mina.session.SessionManager;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.session.IoSession;

public class SessionUtils {
   public static IoSession getSession(int roleId) {
      return SessionManager.INSTANCE.getSessionBy(roleId);
   }

   public static Map<Long, IoSession> getSessionList() {
      GameServer gameServer = (GameServer)SpringUtils.getBean(GameServer.class);
      return (Map<Long, IoSession>)(gameServer.getAcceptor() == null ? new HashMap() : gameServer.getAcceptor().getManagedSessions());
   }
}
