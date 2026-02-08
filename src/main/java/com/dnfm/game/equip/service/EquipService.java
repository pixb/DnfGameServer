package com.dnfm.game.equip.service;

import com.alibaba.fastjson.JSON;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.model.RoleEquip;
import com.dnfm.game.equip.model.UpgradeInfo;
import com.dnfm.game.role.model.Role;
import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.ChangeEquipEvent;
import com.dnfm.listener.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EquipService {
   private final Logger logger = LoggerFactory.getLogger(EquipService.class);

   public static void main(String[] args) {
      EquipDataPool.initUpgradeConfig();
      new EquipService();
      UpgradeInfo upgradeInfo = EquipDataPool.getUpgradeInfo(12, 11, 20, 3);
      System.out.println(JSON.toJSONString(upgradeInfo));
   }

   @EventHandler({EventType.LOGIN})
   public void handleLoginEvent(LoginEvent loginEvent) {
   }

   @EventHandler({EventType.CHANGE_EQUIP})
   public void handleOnEquipEvent(ChangeEquipEvent changeEquipEvent) {
   }

   public void delRoleEquip(RoleEquip roleEquip, Role role) {
   }
}
