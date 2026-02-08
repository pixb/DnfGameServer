package com.dnfm.game.achievement;

import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.REQ_ACHIEVEMENT_REWARD;
import com.dnfm.mina.protobuf.RES_ACHIEVEMENT_REWARD;
import java.util.ArrayList;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AchievementController {
   @Autowired
   private RoleService roleService;

   @RequestMapping
   public void ReqAchievementReward(IoSession session, REQ_ACHIEVEMENT_REWARD req_achievement_reward) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_ACHIEVEMENT_REWARD res_achievement_reward = new RES_ACHIEVEMENT_REWARD();
      res_achievement_reward.adventureunionlevel = 1;
      res_achievement_reward.adventureunionexp = 1L;
      res_achievement_reward.consumeitems = new ArrayList();
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = 2013106021;
      ptStackable.count = 1;
      ptStackable.bind = false;
      res_achievement_reward.consumeitems.add(ptStackable);
      res_achievement_reward.invenitems = new PT_ITEMS();
      res_achievement_reward.invenitems.consumeitems = new ArrayList();
      PT_STACKABLE ptStackable1 = new PT_STACKABLE();
      ptStackable1.index = 2013106021;
      ptStackable1.count = 1;
      ptStackable1.acquisitiontime = TimeUtil.currS();
      res_achievement_reward.invenitems.consumeitems.add(ptStackable1);
      role.getConsumableBox().addConsumable(ptStackable1);
      role.save();
      MessagePusher.pushMessage((IoSession)session, res_achievement_reward);
   }
}
