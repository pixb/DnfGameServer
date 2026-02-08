package com.dnfm.game.mail;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.mail.model.MailItem;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_POST_ALL_LIST;
import com.dnfm.mina.protobuf.PT_POST_PACKAGE;
import com.dnfm.mina.protobuf.PT_SELECTED_ITEM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailBox {
   private static final Logger log = LoggerFactory.getLogger(MailBox.class);
   private Map<Long, PT_POST_ALL_LIST> allMails = new HashMap();

   public Map<Long, PT_POST_ALL_LIST> getAllMail() {
      return this.allMails;
   }

   public void setMails(Map<Long, PT_POST_ALL_LIST> mails) {
      this.allMails = mails;
   }

   public PT_POST_ALL_LIST getMail(Long mailId) {
      return (PT_POST_ALL_LIST)this.allMails.get(mailId);
   }

   public PT_POST_ALL_LIST removeMail(Long mailId) {
      return (PT_POST_ALL_LIST)this.allMails.remove(mailId);
   }

   public void addMail(PT_POST_ALL_LIST mail) {
      this.allMails.put(mail.guid, mail);
   }

   public void addMail(String title, String msg, List<MailItem> mailItemList) {
      if (!Util.isEmpty(mailItemList)) {
         PT_POST_ALL_LIST mail = new PT_POST_ALL_LIST();
         mail.guid = IdGenerator.getNextId();
         mail.title = title;
         mail.msg = msg;

         for(int i = 0; i < mailItemList.size(); ++i) {
            MailItem mailItem = (MailItem)mailItemList.get(i);
            int index = mailItem.index;
            int cnt = mailItem.cnt;
            int type = BagService.getIndexType(index);
            switch (type) {
               case 1:
                  if (mail.avataritems == null) {
                     mail.avataritems = new ArrayList();
                  }

                  PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
                  pt_avatar_item.guid = IdGenerator.getNextId();
                  pt_avatar_item.index = index;
                  pt_avatar_item.count = cnt;
                  mail.avataritems.add(pt_avatar_item);
                  if (mail.remaineditems == null) {
                     mail.remaineditems = new ArrayList();
                  }

                  PT_SELECTED_ITEM pt_remained_item = new PT_SELECTED_ITEM();
                  pt_remained_item.index = index;
                  pt_remained_item.count = cnt;
                  pt_remained_item.slotindex = i + 1;
                  mail.remaineditems.add(pt_remained_item);
                  break;
               case 2:
               case 100:
                  if (mail.equipitems == null) {
                     mail.equipitems = new ArrayList();
                  }

                  if (mail.index == null) {
                     mail.index = index;
                  }

                  PT_EQUIP equip = EquipDataPool.createEquip(index);
                  mail.equipitems.add(equip);
                  if (mail.remaineditems == null) {
                     mail.remaineditems = new ArrayList();
                  }

                  PT_SELECTED_ITEM pt_remained_item_equ = new PT_SELECTED_ITEM();
                  pt_remained_item_equ.index = index;
                  pt_remained_item_equ.guid = equip.guid;
                  mail.remaineditems.add(pt_remained_item_equ);
                  break;
               case 3:
                  if (mail.titleitems == null) {
                     mail.titleitems = new ArrayList();
                  }

                  PT_EQUIP titleItem = EquipDataPool.createTitle(index);
                  mail.titleitems.add(titleItem);
                  if (mail.remaineditems == null) {
                     mail.remaineditems = new ArrayList();
                  }

                  PT_SELECTED_ITEM pt_remained_item_title = new PT_SELECTED_ITEM();
                  pt_remained_item_title.index = index;
                  pt_remained_item_title.count = cnt;
                  pt_remained_item_title.slotindex = i + 1;
                  mail.remaineditems.add(pt_remained_item_title);
                  break;
               case 1000:
               case 1001:
               case 1003:
               case 1004:
               case 1005:
               case 1006:
               case 1007:
               case 1009:
                  if (mail.package0 == null) {
                     mail.package0 = new ArrayList();
                  }

                  PT_POST_PACKAGE ptPostPackage = new PT_POST_PACKAGE();
                  ptPostPackage.index = index;
                  ptPostPackage.value = cnt;
                  ptPostPackage.slotindex = i + 1;
                  mail.package0.add(ptPostPackage);
                  break;
               case 10000:
                  log.error("UNREACH==邮件中不应该有伤害字体==" + index);
                  break;
               case 10001:
                  log.error("UNREACH==邮件中不应该有聊天框==" + index);
                  break;
               case 10002:
                  log.error("UNREACH==邮件中不应该有角色框==" + index);
                  break;
               default:
                  log.error("UNREACH==addMail==type==" + type + "==index==" + index);
            }
         }

         this.allMails.put(mail.guid, mail);
      }
   }

   public Map<Long, PT_POST_ALL_LIST> getAllMails() {
      return this.allMails;
   }

   public void setAllMails(Map<Long, PT_POST_ALL_LIST> allMails) {
      this.allMails = allMails;
   }

   public void addnewMail(PT_POST_ALL_LIST mail, Long guid) {
      this.allMails.put(guid, mail);
   }

   public PT_POST_ALL_LIST modifyMail(Long mailId) {
      PT_POST_ALL_LIST mail = this.getMail(mailId);
      return mail;
   }
}
