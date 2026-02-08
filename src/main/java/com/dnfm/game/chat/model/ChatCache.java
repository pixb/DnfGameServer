package com.dnfm.game.chat.model;

import com.dnfm.game.role.model.Role;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.protobuf.PT_CHAT;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_HYPERLINK_SYSTEMMESSAGE_SUB;
import com.dnfm.mina.protobuf.PT_SKIN_CHAT_INFO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatCache {
   private static final Logger log = LoggerFactory.getLogger(ChatCache.class);
   public static final int CHAT_WORLD = 1;
   public static final int CHAT_TOWN = 2;
   public static final int CHAT_NOTICE = 200;
   public static Map<Integer, Map<Long, List<PT_CHAT>>> chatMsgMap = new ConcurrentHashMap();
   public static Map<String, Map<Long, List<PT_CHAT>>> townChatMsgMap = new ConcurrentHashMap();
   public static Map<Integer, Long> lastIndexMap = new ConcurrentHashMap();

   public static PT_CHAT addTownChat(String text, Role role) {
      long index = (Long)lastIndexMap.get(2);
      int town = role.getPos().getTown();
      int area = role.getPos().getArea();
      String key = town + "_" + area;
      PT_CHAT pt_chat = new PT_CHAT();
      pt_chat.charguid = role.getUid();
      if (role.getJob() != 0) {
         pt_chat.job = role.getJob();
      }

      if (role.getGrowtype() != 0) {
         pt_chat.growtype = role.getGrowtype();
      }

      if (role.getSecgrowtype() != 0) {
         pt_chat.secgrowtype = role.getSecgrowtype();
      }

      pt_chat.level = role.getLevel();
      pt_chat.sender = role.getName();
      pt_chat.chat = text;
      pt_chat.skinchatinfo = new PT_SKIN_CHAT_INFO();
      pt_chat.date = TimeUtil.currS();
      pt_chat.creditscore = 351;
      Map<Long, List<PT_CHAT>> listMap = (Map)townChatMsgMap.get(key);
      if (listMap == null) {
         listMap = new ConcurrentHashMap();
      }

      List<PT_CHAT> list = (List)listMap.get(index);
      if (list == null) {
         list = new ArrayList();
      }

      list.add(pt_chat);
      listMap.put(index, list);
      townChatMsgMap.put(key, listMap);
      return pt_chat;
   }

   public static PT_CHAT addChat(int type, String text, Role role) {
      if (type == 0) {
         log.error("UNREACH==addChat.type=" + type);
         return null;
      } else if (type == 5) {
         long index = (Long)lastIndexMap.get(1);
         PT_CHAT pt_chat = new PT_CHAT();
         pt_chat.type = 5;
         pt_chat.charguid = role.getUid();
         if (role.getJob() != 0) {
            pt_chat.job = role.getJob();
         }

         if (role.getGrowtype() != 0) {
            pt_chat.growtype = role.getGrowtype();
         }

         if (role.getSecgrowtype() != 0) {
            pt_chat.secgrowtype = role.getSecgrowtype();
         }

         pt_chat.level = role.getLevel();
         pt_chat.sender = role.getName();
         pt_chat.chat = text;
         pt_chat.skinchatinfo = new PT_SKIN_CHAT_INFO();
         pt_chat.date = TimeUtil.currS();
         pt_chat.creditscore = 351;
         Map<Long, List<PT_CHAT>> listMap = (Map)chatMsgMap.get(1);
         if (listMap == null) {
            listMap = new ConcurrentHashMap();
         }

         List<PT_CHAT> list = (List)listMap.get(index);
         if (list == null) {
            list = new ArrayList();
         }

         list.add(pt_chat);
         listMap.put(index, list);
         chatMsgMap.put(1, listMap);
         return pt_chat;
      } else {
         log.error("UNREACH==addChat.type=" + type);
         return null;
      }
   }

   public static void addNoticeMake(String roleName, String equipName, PT_EQUIP pt_equip) {
      long index = (Long)lastIndexMap.get(200);
      PT_CHAT pt_chat = new PT_CHAT();
      pt_chat.type = 2;
      pt_chat.chat = "{0} 모험가가 {1} 에픽 장비 제작에 성공하였습니다.";
      pt_chat.hyperlinktype = 6;
      pt_chat.hyperlinksubtype = 15;
      pt_chat.sub = new ArrayList();
      PT_HYPERLINK_SYSTEMMESSAGE_SUB sub1 = new PT_HYPERLINK_SYSTEMMESSAGE_SUB();
      PT_HYPERLINK_SYSTEMMESSAGE_SUB sub2 = new PT_HYPERLINK_SYSTEMMESSAGE_SUB();
      sub1.msg = roleName;
      sub2.msg = equipName;
      pt_chat.sub.add(sub1);
      pt_chat.sub.add(sub2);
      pt_chat.equip = pt_equip;
      Map<Long, List<PT_CHAT>> chatNoticeListMap = (Map)chatMsgMap.get(200);
      if (chatNoticeListMap == null) {
         chatNoticeListMap = new ConcurrentHashMap();
      }

      List<PT_CHAT> chatNoticeList = (List)chatNoticeListMap.get(index);
      if (chatNoticeList == null) {
         chatNoticeList = new ArrayList();
      }

      chatNoticeList.add(pt_chat);
      chatNoticeListMap.put(index, chatNoticeList);
      chatMsgMap.put(200, chatNoticeListMap);
   }

   static {
      chatMsgMap.put(1, new ConcurrentHashMap());
      chatMsgMap.put(200, new ConcurrentHashMap());
      lastIndexMap.put(1, 0L);
      lastIndexMap.put(2, 0L);
      lastIndexMap.put(200, 0L);
   }
}
