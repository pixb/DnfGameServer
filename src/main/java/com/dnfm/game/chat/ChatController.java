package com.dnfm.game.chat;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.utils.Util;
import com.dnfm.game.chat.model.ChatCache;
import com.dnfm.game.chat.service.ChatService;
import com.dnfm.game.mail.model.MailItem;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_CHAT;
import com.dnfm.mina.protobuf.PT_CHAT_SYNC;
import com.dnfm.mina.protobuf.PT_CHAT_SYNC_INFO;
import com.dnfm.mina.protobuf.PT_SKIN_CHAT_INFO;
import com.dnfm.mina.protobuf.REQ_NOTE_MESSENGER_ADD_USER;
import com.dnfm.mina.protobuf.REQ_TOWN_CHAT;
import com.dnfm.mina.protobuf.REQ_TOWN_CHAT_LIST;
import com.dnfm.mina.protobuf.RES_NOTE_MESSENGER_ADD_USER;
import com.dnfm.mina.protobuf.RES_TOWN_CHAT;
import com.dnfm.mina.protobuf.RES_TOWN_CHAT_LIST;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
   private static final Logger log = LoggerFactory.getLogger(ChatController.class);
   @Autowired
   AccountService accountService;
   @Autowired
   ChatService chatService;

   @RequestMapping
   public void ReqTownChat(IoSession session, REQ_TOWN_CHAT reqTownChat) {
      String chatText = reqTownChat.voice;
      Role role = SessionUtils.getRoleBySession(session);
      if (role.getWordTime() > 0L) {
         RES_TOWN_CHAT res_town_chat = new RES_TOWN_CHAT();
         res_town_chat.error = 1;
         res_town_chat.transId = reqTownChat.transId;
         MessagePusher.pushMessage((IoSession)session, res_town_chat);
      } else {
         Account account = SessionUtils.getAccountBySession(session);
         int type = 0;
         if (reqTownChat.type != null) {
            type = reqTownChat.type;
         }

         log.error("account.getUserID====={}", account.getUserID() + "");
         if (chatText.contains("@") && account.getPrivilege() >= 10) {
            String errorText = null;
            if (!chatText.contains("==")) {
               String title = "发送单个物品[测试专用]";
               String msg = "发送单个物品[测试专用]--请查收";
               String[] itemArr = chatText.split("@");
               String userID = account.getUserID();
               int index = Integer.parseInt(itemArr[1]);
               int count = Integer.parseInt(itemArr[2]);
               List<MailItem> list = new ArrayList();
               MailItem mailItem = new MailItem();
               mailItem.index = index;
               mailItem.cnt = count;
               log.error("add==userID=={}==mailItem=={}", userID, JSON.toJSON(mailItem));
               list.add(mailItem);
               role.getMailBox().addMail(title, msg, list);
               role.save();
            } else {
               String[] itemStrArr = chatText.split("==");
               String title = "批量发送物品[测试专用]";
               String msg = "批量发送物品[测试专用]--请查收";
               List<MailItem> list = new ArrayList();
               String userID = null;

               for(String itemStr : itemStrArr) {
                  String[] itemArr = itemStr.split("@");
                  if (userID == null) {
                     userID = itemArr[0];
                  }

                  int index = Integer.parseInt(itemArr[1]);
                  int count = Integer.parseInt(itemArr[2]);
                  MailItem mailItem = new MailItem();
                  mailItem.index = index;
                  mailItem.cnt = count;
                  list.add(mailItem);
               }

               role.getMailBox().addMail(title, msg, list);
               role.save();
            }

            RES_TOWN_CHAT res_town_chat = new RES_TOWN_CHAT();
            res_town_chat.charguid = role.getUid();
            if (role.getJob() != 0) {
               res_town_chat.job = role.getJob();
            }

            if (role.getGrowtype() != 0) {
               res_town_chat.growtype = role.getGrowtype();
            }

            if (role.getSecgrowtype() != 0) {
               res_town_chat.secgrowtype = role.getSecgrowtype();
            }

            res_town_chat.level = role.getLevel();
            res_town_chat.sender = role.getName();
            if (Util.isEmpty(errorText)) {
               res_town_chat.chat = chatText;
            } else {
               res_town_chat.chat = errorText;
            }

            res_town_chat.skinchatinfo = new PT_SKIN_CHAT_INFO();
            res_town_chat.date = TimeUtil.currS();
            res_town_chat.creditscore = 351;
            res_town_chat.transId = reqTownChat.transId;
            MessagePusher.pushMessage((IoSession)session, res_town_chat);
         } else if (type == 0) {
            PT_CHAT pt_chat = ChatCache.addTownChat(chatText, role);
            RES_TOWN_CHAT res_town_chat = new RES_TOWN_CHAT();
            res_town_chat.charguid = pt_chat.charguid;
            res_town_chat.job = pt_chat.job;
            res_town_chat.growtype = pt_chat.growtype;
            res_town_chat.secgrowtype = pt_chat.secgrowtype;
            res_town_chat.level = pt_chat.level;
            res_town_chat.sender = pt_chat.sender;
            res_town_chat.chat = pt_chat.chat;
            res_town_chat.skinchatinfo = pt_chat.skinchatinfo;
            res_town_chat.date = pt_chat.date;
            res_town_chat.creditscore = pt_chat.creditscore;
            res_town_chat.transId = reqTownChat.transId;
            MessagePusher.pushMessage((IoSession)session, res_town_chat);
         } else {
            PT_CHAT pt_chat = ChatCache.addChat(type, chatText, role);
            RES_TOWN_CHAT res_town_chat = new RES_TOWN_CHAT();
            res_town_chat.type = type;
            res_town_chat.charguid = pt_chat.charguid;
            res_town_chat.job = pt_chat.job;
            res_town_chat.growtype = pt_chat.growtype;
            res_town_chat.secgrowtype = pt_chat.secgrowtype;
            res_town_chat.level = pt_chat.level;
            res_town_chat.sender = pt_chat.sender;
            res_town_chat.chat = pt_chat.chat;
            res_town_chat.skinchatinfo = pt_chat.skinchatinfo;
            res_town_chat.date = pt_chat.date;
            res_town_chat.creditscore = pt_chat.creditscore;
            res_town_chat.transId = reqTownChat.transId;
            if (type == 5) {
            }

            MessagePusher.pushMessage((IoSession)session, res_town_chat);
         }

      }
   }

   @RequestMapping
   public void ReqTownChatList(IoSession session, REQ_TOWN_CHAT_LIST reqTownChatList) {
      List<PT_CHAT_SYNC> chat = reqTownChatList.chat;
      Role role = SessionUtils.getRoleBySession(session);
      if (chat != null) {
         int type = ((PT_CHAT_SYNC)chat.get(0)).type;
         long index = 0L;
         if (((PT_CHAT_SYNC)chat.get(0)).index != null) {
            index = ((PT_CHAT_SYNC)chat.get(0)).index;
         }

         if (type == 1) {
            RES_TOWN_CHAT_LIST resTownChatList = new RES_TOWN_CHAT_LIST();
            resTownChatList.chat = new ArrayList();
            PT_CHAT_SYNC_INFO ptChatSyncInfo = new PT_CHAT_SYNC_INFO();
            ptChatSyncInfo.type = 1;
            List<PT_CHAT> chatMsg = (List)((Map)ChatCache.chatMsgMap.get(1)).get(index);
            if (!Util.isEmpty(chatMsg)) {
               ptChatSyncInfo.totalcount = (long)chatMsg.size();
               ptChatSyncInfo.chatmsg = chatMsg;
               ptChatSyncInfo.lastindex = (Long)ChatCache.lastIndexMap.get(1) + (long)chatMsg.size();
               ChatCache.lastIndexMap.put(1, ptChatSyncInfo.lastindex);
            } else {
               ptChatSyncInfo.lastindex = (Long)ChatCache.lastIndexMap.get(1);
            }

            resTownChatList.chat.add(ptChatSyncInfo);
            resTownChatList.interval = 3000;
            resTownChatList.transId = reqTownChatList.transId;
            MessagePusher.pushMessage((IoSession)session, resTownChatList);
         } else if (type == 2) {
            RES_TOWN_CHAT_LIST resTownChatList = new RES_TOWN_CHAT_LIST();
            resTownChatList.chat = new ArrayList();
            PT_CHAT_SYNC_INFO ptChatSyncInfo = new PT_CHAT_SYNC_INFO();
            ptChatSyncInfo.type = 2;
            String key = role.getPos().getTown() + "_" + role.getPos().getArea();
            Map<Long, List<PT_CHAT>> chatMsgMap = (Map)ChatCache.townChatMsgMap.get(key);
            if (chatMsgMap == null) {
               chatMsgMap = new ConcurrentHashMap();
            }

            List<PT_CHAT> chatMsg = (List)chatMsgMap.get(index);
            if (!Util.isEmpty(chatMsg)) {
               ptChatSyncInfo.totalcount = (long)chatMsg.size();
               ptChatSyncInfo.chatmsg = chatMsg;
               ptChatSyncInfo.lastindex = (Long)ChatCache.lastIndexMap.get(2) + (long)chatMsg.size();
               ChatCache.lastIndexMap.put(2, ptChatSyncInfo.lastindex);
            } else {
               ptChatSyncInfo.lastindex = (Long)ChatCache.lastIndexMap.get(2);
            }

            resTownChatList.chat.add(ptChatSyncInfo);
            resTownChatList.interval = 3000;
            resTownChatList.transId = reqTownChatList.transId;
            MessagePusher.pushMessage((IoSession)session, resTownChatList);
            RES_TOWN_CHAT_LIST resTownChatList2 = new RES_TOWN_CHAT_LIST();
            resTownChatList2.chat = new ArrayList();
            PT_CHAT_SYNC_INFO ptChatSyncInfo2 = new PT_CHAT_SYNC_INFO();
            ptChatSyncInfo2.type = 2;
            List<PT_CHAT> chatNoticeList = (List)((Map)ChatCache.chatMsgMap.get(200)).get(index);
            if (Util.isEmpty(chatNoticeList)) {
               ptChatSyncInfo2.lastindex = (Long)ChatCache.lastIndexMap.get(200);
               return;
            }

            ptChatSyncInfo2.totalcount = (long)chatNoticeList.size();
            ptChatSyncInfo2.chatmsg = chatNoticeList;
            ptChatSyncInfo2.lastindex = (Long)ChatCache.lastIndexMap.get(200) + (long)chatNoticeList.size();
            ChatCache.lastIndexMap.put(200, ptChatSyncInfo2.lastindex);
            resTownChatList2.chat.add(ptChatSyncInfo2);
            resTownChatList2.interval = 3000;
            resTownChatList2.transId = reqTownChatList.transId;
            MessagePusher.pushMessage((IoSession)session, resTownChatList2);
         } else if (type == 4) {
            RES_TOWN_CHAT_LIST resTownChatList = new RES_TOWN_CHAT_LIST();
            resTownChatList.chat = new ArrayList();
            PT_CHAT_SYNC_INFO ptChatSyncInfo = new PT_CHAT_SYNC_INFO();
            ptChatSyncInfo.lastindex = 1L;
            ptChatSyncInfo.type = 4;
            resTownChatList.chat.add(ptChatSyncInfo);
            resTownChatList.interval = 3000;
            resTownChatList.transId = reqTownChatList.transId;
            MessagePusher.pushMessage((IoSession)session, resTownChatList);
         }
      }

   }

   @RequestMapping
   public void reqNoteMessengerAddUser(IoSession session, REQ_NOTE_MESSENGER_ADD_USER reqNoteMessengerAddUser) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_NOTE_MESSENGER_ADD_USER resNoteMessengerAddUser = this.chatService.userMsg(role, reqNoteMessengerAddUser.targetguid);
      MessagePusher.pushMessage((IoSession)session, resNoteMessengerAddUser);
   }
}
