package com.dnfm.game.fight.service;

import com.dnfm.game.ServerService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.map.service.MapService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.scene.SceneManager;
import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.LogoutEvent;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.Message;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BroadcastService {
   @Autowired
   MapService mapService;
   @Autowired
   ServerService serverService;
   @Autowired
   EquipService equipService;

   @EventHandler({EventType.LOGOUT})
   public void handleLogoutEvent(LogoutEvent logoutEvent) {
      Role role = logoutEvent.getRole();
      SceneManager.INSTANCE.leaveMap(role.getPos().getArea(), role);
   }

   private void cacheObjectInfo(NutMap nutMap) {
   }

   public void sendUpdateAppear(Role role) {
   }

   public void updateAppearance(Role role) {
      Message appear = this.getUpdateAppear(role);
      MessagePusher.pushMessage(role, appear);
   }

   public void sendAppear(Role role) {
   }

   public void updateAppearance(Role role, Role appearRole) {
      if (appearRole != null) {
         Message appear = this.getUpdateAppear(appearRole);
         MessagePusher.pushMessage(role, appear);
      }
   }

   public Message getUpdateAppear(Role role) {
      return null;
   }

   public Message getAppear(Role role) {
      return null;
   }

   public void sendLocalMessage(Role role, Message message) {
      SceneManager.INSTANCE.sendMessages(role, message);
   }

   public Message getDisAppear(int petId, int type) {
      return null;
   }

   public Message getMovePacket(Role role) {
      return null;
   }

   public boolean inSameScene(Role role, Role otherRole) {
      if (role != null && otherRole != null) {
         return role.getPos().getArea() == otherRole.getPos().getArea();
      } else {
         return false;
      }
   }
}
