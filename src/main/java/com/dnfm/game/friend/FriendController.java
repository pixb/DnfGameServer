package com.dnfm.game.friend;

import com.dnfm.game.friend.service.FriendService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.REQ_DELETE_FRIEND;
import com.dnfm.mina.protobuf.REQ_FRIEND_FIND;
import com.dnfm.mina.protobuf.REQ_FRIEND_RECOMMEND;
import com.dnfm.mina.protobuf.REQ_FRIEND_RECOMMEND_WITH_ME;
import com.dnfm.mina.protobuf.REQ_REPLY_FRIEND_INVITE;
import com.dnfm.mina.protobuf.REQ_REQUEST_FRIEND_INVITE;
import com.dnfm.mina.protobuf.RES_DELETE_FRIEND;
import com.dnfm.mina.protobuf.RES_FRIEND_FIND;
import com.dnfm.mina.protobuf.RES_FRIEND_RECOMMEND;
import com.dnfm.mina.protobuf.RES_FRIEND_RECOMMEND_WITH_ME;
import com.dnfm.mina.protobuf.RES_REPLY_FRIEND_INVITE;
import com.dnfm.mina.protobuf.RES_REQUEST_FRIEND_INVITE;
import java.util.ArrayList;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FriendController {
   Logger logger = LoggerFactory.getLogger(FriendController.class);
   @Autowired
   public RoleService roleService;
   @Autowired
   public FriendService friendService;

   @RequestMapping
   public void ReqFriendRecommendWithMe(IoSession session, REQ_FRIEND_RECOMMEND_WITH_ME reqFriendRecommendWithMe) {
      RES_FRIEND_RECOMMEND_WITH_ME resFriendRecommendWithMe = new RES_FRIEND_RECOMMEND_WITH_ME();
      resFriendRecommendWithMe.flist = new ArrayList();
      MessagePusher.pushMessage((IoSession)session, resFriendRecommendWithMe);
   }

   @RequestMapping
   public void reqFriendRecommend(IoSession session, REQ_FRIEND_RECOMMEND reqFriendRecommend) {
      RES_FRIEND_RECOMMEND resFriendRecommend = new RES_FRIEND_RECOMMEND();
      resFriendRecommend.flist = new ArrayList();
      MessagePusher.pushMessage((IoSession)session, resFriendRecommend);
   }

   @RequestMapping
   public void reqFriendFind(IoSession session, REQ_FRIEND_FIND reqFriendFind) {
      Role role = this.roleService.getPlayerBy(reqFriendFind.fname);
      RES_FRIEND_FIND resFriendFind = new RES_FRIEND_FIND();
      if (role != null) {
         resFriendFind.fguid = role.getUid();
         resFriendFind.name = role.getName();
         resFriendFind.level = role.getLevel();
         resFriendFind.job = role.getJob();
         resFriendFind.growtype = role.getGrowtype();
         resFriendFind.secgrowtype = role.getSecgrowtype();
         resFriendFind.creditscore = role.getCerascore();
         resFriendFind.world = 1;
         resFriendFind.characterframe = role.getCharacterframe();
      } else {
         resFriendFind.error = 1;
      }

      this.logger.error("reqFriendFind = {}", resFriendFind.toString());
      MessagePusher.pushMessage((IoSession)session, resFriendFind);
   }

   @RequestMapping
   public void reqRequestFriendInvite(IoSession session, REQ_REQUEST_FRIEND_INVITE reqRequestFriendInvite) {
      RES_REQUEST_FRIEND_INVITE resRequestFriendInvite = new RES_REQUEST_FRIEND_INVITE();
      Role role = SessionUtils.getRoleBySession(session);
      Role targetRole = this.roleService.getPlayerBy(reqRequestFriendInvite.fguid);
      if (targetRole != null) {
         resRequestFriendInvite.fguid = role.getUid();
         resRequestFriendInvite.name = role.getName();
         resRequestFriendInvite.level = role.getLevel();
         resRequestFriendInvite.job = role.getJob();
         resRequestFriendInvite.growtype = role.getGrowtype();
         resRequestFriendInvite.creditscore = role.getCerascore();
         resRequestFriendInvite.world = 1;
         this.friendService.sendFriendInvite(role, targetRole);
      } else {
         resRequestFriendInvite.error = 1;
      }

      MessagePusher.pushMessage((IoSession)session, resRequestFriendInvite);
   }

   @RequestMapping
   public void replyFriendInvite(IoSession session, REQ_REPLY_FRIEND_INVITE reqReplyFriendInvite) {
      Role role = SessionUtils.getRoleBySession(session);
      int type = 0;
      if (reqReplyFriendInvite.type != null) {
         type = reqReplyFriendInvite.type;
      }

      this.friendService.operateFriendInvite(type, role, reqReplyFriendInvite.fguid);
      RES_REPLY_FRIEND_INVITE resReplyFriendInvite = new RES_REPLY_FRIEND_INVITE();
      resReplyFriendInvite.world = 1;
      resReplyFriendInvite.fguid = reqReplyFriendInvite.fguid;
      resReplyFriendInvite.type = reqReplyFriendInvite.type;
      MessagePusher.pushMessage((IoSession)session, resReplyFriendInvite);
   }

   @RequestMapping
   public void reqDeleteFriend(IoSession session, REQ_DELETE_FRIEND reqDeleteFriend) {
      Role role = SessionUtils.getRoleBySession(session);
      int result = this.friendService.deleteFriend(role, reqDeleteFriend.fguid);
      RES_DELETE_FRIEND resDeleteFriend = new RES_DELETE_FRIEND();
      resDeleteFriend.fguid = reqDeleteFriend.fguid;
      if (result != 0) {
         resDeleteFriend.error = result;
      }

      MessagePusher.pushMessage((IoSession)session, resDeleteFriend);
   }
}
