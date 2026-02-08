package com.dnfm.mina.task;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.SessionUtils;
import org.apache.mina.core.session.IoSession;

public class ThreadLocalUtil {
   public static void addLocalTask(IoSession session, Runnable task) {
      if (session == null) {
         throw new NullPointerException("session is null");
      } else {
         Role role = SessionUtils.getRoleBySession(session);
         addLocalTask(role, task);
      }
   }

   public static void addLocalTask(final Role role, final Runnable task) {
      if (role == null) {
         throw new NullPointerException("role is null");
      } else {
         TaskHandlerContext.INSTANCE.acceptTask(new AbstractDistributeTask() {
            final int distributeKey = role.getDistributeKey();

            public int distributeKey() {
               return this.distributeKey;
            }

            public void action() {
               task.run();
            }
         });
      }
   }
}
