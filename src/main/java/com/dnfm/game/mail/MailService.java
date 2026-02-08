package com.dnfm.game.mail;

import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.LoginEvent;
import com.dnfm.logs.LoggerFunction;
import java.util.ArrayList;
import org.nutz.dao.Dao;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {
   @Autowired
   Dao dao;
   @Autowired
   RoleService roleService;
   @Autowired
   BagService bagService;
   @Autowired
   EquipService equipService;
   private final Logger logger;

   public MailService() {
      this.logger = LoggerFunction.MAIL.getLogger();
   }

   @EventHandler({EventType.LOGIN})
   public void handleLoginEvent(LoginEvent loginEvent) {
   }

   private String getMailItemString(ArrayList<NutMap> maps) {
      StringBuilder stringBuilder = new StringBuilder();
      if (maps != null) {
         for(NutMap nutMap : maps) {
            stringBuilder.append(nutMap.getString("data"));
         }
      }

      return stringBuilder.toString();
   }
}
