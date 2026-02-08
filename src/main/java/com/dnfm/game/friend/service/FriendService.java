package com.dnfm.game.friend.service;

import com.dnfm.game.ServerService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.map.service.MapService;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.LoginEvent;
import com.dnfm.listener.event.LogoutEvent;
import com.dnfm.mina.protobuf.PT_FRIEND_INFO;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
   Logger logger = LoggerFactory.getLogger(FriendService.class);
   @Autowired
   Dao dao;
   @Autowired
   MapService mapService;
   @Autowired
   RoleService roleService;
   @Autowired
   ServerService serverService;
   @Autowired
   EquipService equipService;
   @Autowired
   PlayerService playerService;

   @EventHandler({EventType.LOGOUT})
   public void handleLogoutEvent(LogoutEvent logoutEvent) {
   }

   @EventHandler({EventType.LOGIN})
   public void handleLoginEvent(LoginEvent loginEvent) {
   }

   public void sendFriendInvite(Role role, Role targetRole) {
      for(PT_FRIEND_INFO send : role.getFriendBox().getSends()) {
         if (send.fguid == targetRole.getUid()) {
            return;
         }
      }

      PT_FRIEND_INFO sendInfo = new PT_FRIEND_INFO();
      sendInfo.world = 1;
      sendInfo.fguid = targetRole.getUid();
      sendInfo.name = targetRole.getName();
      sendInfo.level = targetRole.getLevel();
      sendInfo.job = targetRole.getJob();
      sendInfo.growtype = targetRole.getGrowtype();
      sendInfo.secondgrowtype = targetRole.getSecgrowtype();
      sendInfo.date = TimeUtil.currS();
      sendInfo.online = 1;
      sendInfo.channel = 1;
      role.getFriendBox().addSendFriend(sendInfo);
      PT_FRIEND_INFO receivedInfo = new PT_FRIEND_INFO();
      receivedInfo.world = 1;
      receivedInfo.fguid = role.getUid();
      receivedInfo.name = role.getName();
      receivedInfo.level = role.getLevel();
      receivedInfo.job = role.getJob();
      receivedInfo.growtype = role.getGrowtype();
      receivedInfo.secondgrowtype = role.getSecgrowtype();
      receivedInfo.date = TimeUtil.currS();
      receivedInfo.online = 1;
      sendInfo.channel = 1;
      targetRole.getFriendBox().addReceivedFriend(receivedInfo);
      if (this.roleService.isOnline(targetRole)) {
         this.logger.error("目标角色在线");
      } else {
         targetRole.save();
      }

   }

   public void operateFriendInvite(int type, Role role, Long fguid) {
      Role sendRole = this.roleService.getPlayerBy(fguid);
      PT_FRIEND_INFO sendInvite = sendRole.getFriendBox().getSendFriendByGid(role.getUid());
      PT_FRIEND_INFO receivedInvite = role.getFriendBox().getReceivedFriendByGid(fguid);
      if (type == 1) {
         sendInvite.status = type;
         receivedInvite.status = type;
      } else {
         PT_FRIEND_INFO info = new PT_FRIEND_INFO();
         info.world = 1;
         info.fguid = sendRole.getUid();
         info.name = sendRole.getName();
         info.level = sendRole.getLevel();
         info.job = sendRole.getJob();
         info.growtype = sendRole.getGrowtype();
         info.secondgrowtype = sendRole.getSecgrowtype();
         info.date = TimeUtil.currMs();
         info.online = 1;
         info.channel = 1;
         role.getFriendBox().addFriend(info);
         PT_FRIEND_INFO info2 = new PT_FRIEND_INFO();
         info2.world = 1;
         info2.fguid = role.getUid();
         info2.name = role.getName();
         info2.level = role.getLevel();
         info2.job = role.getJob();
         info2.growtype = role.getGrowtype();
         info2.secondgrowtype = role.getSecgrowtype();
         info2.date = TimeUtil.currMs();
         info2.online = 1;
         info2.channel = 1;
         sendRole.getFriendBox().addFriend(info2);
      }

      sendRole.getFriendBox().removeSendFriend(sendInvite);
      role.getFriendBox().removeReceivedFriend(receivedInvite);
      if (!this.roleService.isOnline(sendRole)) {
         this.logger.error("目标角色离线");
         sendRole.save();
      }

   }

   public int deleteFriend(Role role, Long fguid) {
      PT_FRIEND_INFO info = role.getFriendBox().getFriendByGid(fguid);
      if (info != null) {
         role.getFriendBox().removeFriend(info);
         return 0;
      } else {
         return 1;
      }
   }
}
