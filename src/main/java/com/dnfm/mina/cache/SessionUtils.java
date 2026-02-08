package com.dnfm.mina.cache;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.common.start.GameServer;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.session.IoSession;

public class SessionUtils {
   public static Integer getNotiTransId(IoSession session) {
      Integer res = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.NOTIFY_TRANS_ID, Integer.class);
      if (res == null) {
      }

      session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, res + 1);
      return res;
   }

   public static IoSession getSession(long uid) {
      return SessionManager.INSTANCE.getSessionBy(uid);
   }

   public static IoSession getSessionBy(long uid) {
      return getSession(uid);
   }

   public static Map<Long, IoSession> getSessionList() {
      GameServer gameServer = (GameServer)SpringUtils.getBean(GameServer.class);
      return (Map<Long, IoSession>)(gameServer.getAcceptor() == null ? new HashMap() : gameServer.getAcceptor().getManagedSessions());
   }

   public static Role getRoleBySession(IoSession session) {
      return session == null ? null : (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
   }

   public static Account getAccountBySession(IoSession session) {
      return session == null ? null : (Account)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ACCOUNT, Account.class);
   }
}
