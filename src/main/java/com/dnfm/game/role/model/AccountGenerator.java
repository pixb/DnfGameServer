package com.dnfm.game.role.model;

import com.alibaba.fastjson.JSON;
import com.dnfm.game.adventure.model.AdventureReapInfo;
import com.dnfm.game.adventure.model.AdventureUnionInfo;
import com.dnfm.game.bag.model.AdvBookBox;
import com.dnfm.game.bag.model.AdvUnionSubInfoBox;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_EXPEDITION;
import com.dnfm.mina.protobuf.PT_CHARGUID_ENTRANCE_ITEM;
import com.dnfm.mina.protobuf.PT_CHARGUID_FATIGUE;
import com.dnfm.mina.protobuf.PT_CHARGUID_TICKET;
import com.dnfm.mina.protobuf.PT_ENTRANCE_ITEM;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountGenerator {
   static Logger logger = LoggerFactory.getLogger(AccountGenerator.class);

   static AdventureReapInfo getAdventureReapInfo() {
      AdventureReapInfo adventureReapInfo = new AdventureReapInfo();
      adventureReapInfo.setStarttime(-1L);
      return adventureReapInfo;
   }

   static AdventureUnionInfo getAdventureUnionInfo(String accountKey) {
      AdventureUnionInfo info = new AdventureUnionInfo();
      info.level = 1;
      info.exp = 0L;
      info.day = 1;
      info.name = "모험단##" + accountKey;
      info.updatetime = TimeUtil.currS();
      info.expedition = new PT_ADVENTURE_UNION_EXPEDITION();
      return info;
   }

   static AdvUnionSubInfoBox getAdvUnionSubInfoBox(long[] charguids) {
      AdvUnionSubInfoBox advUnionSubInfoBox = new AdvUnionSubInfoBox();
      advUnionSubInfoBox.fatigues = new ArrayList();
      advUnionSubInfoBox.tickets = new ArrayList();
      advUnionSubInfoBox.entranceitems = new ArrayList();

      for(long charguid : charguids) {
         PT_CHARGUID_FATIGUE fatigue = new PT_CHARGUID_FATIGUE();
         fatigue.charguid = charguid;
         fatigue.fatigue = 100;
         advUnionSubInfoBox.fatigues.add(fatigue);
         PT_CHARGUID_TICKET ticket = new PT_CHARGUID_TICKET();
         ticket.charguid = charguid;
         advUnionSubInfoBox.tickets.add(ticket);
         PT_CHARGUID_ENTRANCE_ITEM entranceitem = new PT_CHARGUID_ENTRANCE_ITEM();
         entranceitem.charguid = charguid;
         entranceitem.items = new ArrayList();
         PT_ENTRANCE_ITEM item1 = new PT_ENTRANCE_ITEM();
         item1.index = 2013104267;
         entranceitem.items.add(item1);
         PT_ENTRANCE_ITEM item2 = new PT_ENTRANCE_ITEM();
         item2.index = 2013106097;
         entranceitem.items.add(item2);
         advUnionSubInfoBox.entranceitems.add(entranceitem);
      }

      return advUnionSubInfoBox;
   }

   static AdvBookBox getAdvBookBox() {
      AdvBookBox advBookBox = new AdvBookBox();
      advBookBox.setAdventurebooks((List)null);
      advBookBox.setOpenconditions((List)null);
      return advBookBox;
   }

   public static void main(String[] args) {
      AdventureReapInfo adventureReapInfo = getAdventureReapInfo();
      String str1 = JSON.toJSONString(adventureReapInfo);
      logger.error("adventureReapInfo=={}", str1);
      AdventureUnionInfo adventureUnionInfo = getAdventureUnionInfo("56575634533456");
      String str2 = JSON.toJSONString(adventureUnionInfo);
      logger.error("adventureUnionInfo=={}", str2);
      AdvBookBox advBookBox = getAdvBookBox();
      String str3 = JSON.toJSONString(advBookBox);
      logger.error("advBookBox=={}", str3);
   }
}
