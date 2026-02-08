package com.dnfm.game.utils;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.SessionUtils;
import org.apache.mina.core.session.IoSession;
import org.nutz.lang.util.NutMap;

public class SessionDataUtils {
   public static NutMap getData(Role role) {
      IoSession session = SessionUtils.getSessionBy(role.getUid());
      Object object = session.getAttribute("data");
      if (object == null) {
         NutMap nutMap = new NutMap();
         session.setAttribute("data", nutMap);
      }

      object = session.getAttribute("data");
      return (NutMap)object;
   }
}
