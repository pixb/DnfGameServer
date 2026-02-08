package com.dnfm.game.chat.service;

import com.dnfm.common.utils.LimitedCacheMap;
import com.dnfm.game.ServerService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.friend.service.FriendService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.skill.service.SkillService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.protobuf.PT_CHAT;
import com.dnfm.mina.protobuf.PT_SKIN_CHAT_INFO;
import com.dnfm.mina.protobuf.RES_NOTE_MESSENGER_ADD_USER;
import java.util.ArrayList;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
   public boolean chatForbid = true;
   @Autowired
   EquipService equipService;
   @Autowired
   SkillService skillService;
   @Autowired
   ServerService serverService;
   @Autowired
   RoleService roleService;
   @Autowired
   FriendService friendService;
   private final Logger logger = LoggerFactory.getLogger(ChatService.class);
   private final LimitedCacheMap<String, NutMap> chatCache = new LimitedCacheMap<String, NutMap>(500, 900L);

   public RES_NOTE_MESSENGER_ADD_USER userMsg(Role role, long targetguid) {
      Role targetRole = this.roleService.getPlayerBy(targetguid);
      RES_NOTE_MESSENGER_ADD_USER resNoteMessengerAddUser = new RES_NOTE_MESSENGER_ADD_USER();
      if (targetRole != null) {
         PT_SKIN_CHAT_INFO pt_skin_chat_info = new PT_SKIN_CHAT_INFO();
         pt_skin_chat_info.chatframe = 900;
         pt_skin_chat_info.characterframe = 1920000;
         resNoteMessengerAddUser.charguid = targetRole.getUid();
         ArrayList<Long> deleteguids = new ArrayList();
         deleteguids.add(0L);
         resNoteMessengerAddUser.deleteguids = deleteguids;
         resNoteMessengerAddUser.name = targetRole.getName();
         resNoteMessengerAddUser.job = targetRole.getJob();
         resNoteMessengerAddUser.growtype = targetRole.getGrowtype();
         resNoteMessengerAddUser.level = targetRole.getLevel();
         resNoteMessengerAddUser.count = 0;
         resNoteMessengerAddUser.date = TimeUtil.currS();
         resNoteMessengerAddUser.creditscore = 351;
         resNoteMessengerAddUser.skinchatinfo = pt_skin_chat_info;
         ArrayList<PT_CHAT> pt_chats = new ArrayList();
         PT_CHAT pt_chat = new PT_CHAT();
         pt_chat.charguid = targetRole.getUid();
         if (targetRole.getJob() != 0) {
            pt_chat.job = targetRole.getJob();
         }

         if (targetRole.getGrowtype() != 0) {
            pt_chat.growtype = targetRole.getGrowtype();
         }

         if (targetRole.getSecgrowtype() != 0) {
            pt_chat.secgrowtype = targetRole.getSecgrowtype();
         }

         pt_chat.level = targetRole.getLevel();
         pt_chat.sender = targetRole.getName();
         pt_chat.chat = "112112";
         PT_SKIN_CHAT_INFO pt_skin_chat_info2 = new PT_SKIN_CHAT_INFO();
         pt_skin_chat_info2.chatframe = 900;
         pt_skin_chat_info2.characterframe = 1920000;
         pt_chat.skinchatinfo = pt_skin_chat_info2;
         pt_chat.date = TimeUtil.currS();
         pt_chat.creditscore = 351;
         pt_chats.add(pt_chat);
         resNoteMessengerAddUser.list = pt_chats;
         resNoteMessengerAddUser.error = 0;
      } else {
         resNoteMessengerAddUser.error = 1;
      }

      return resNoteMessengerAddUser;
   }
}
