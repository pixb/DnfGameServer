package com.dnfm.game.item;

import com.dnfm.game.config.ConsumeItem;
import com.dnfm.game.config.Skin;
import com.dnfm.game.config.itemShop;
import com.dnfm.game.item.model.GiftContentMap;
import com.dnfm.game.item.model.giftContent;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemDataPool {
   private static final Logger log = LoggerFactory.getLogger(ItemDataPool.class);
   public static final int IN_P_EQUIP = 0;
   public static final int IN_P_CONSUME = 1;
   public static final int IN_P_SKIN = 2;
   public static final int MATERIAL = 1000;
   public static final int CONSUME = 1001;
   public static final int CONSUME_23 = 1002;
   public static final int ACCOUNT_CURRENCY = 1003;
   public static final int CURRENCY = 1004;
   public static final int EMBLEM = 1005;
   public static final int EPIC_PIECE = 1006;
   public static final int BOOKMARK = 1007;
   public static final int CONSUME_25 = 1008;
   public static final int CARD = 1009;
   public static final int DAMAGE_FONT = 10000;
   public static final int CHAT_FRAME = 10001;
   public static final int CHARACTER_FRAME = 10002;
   public static Map<Integer, ConsumeItem> consumeItemMap;
   public static Map<Integer, itemShop> itemShopMap;
   public static Map<Integer, Skin> skinItemMap;
   public static Map<Integer, Integer> index2TbMap = new HashMap();

   public static int getConsumeType(int stackableType) {
      switch (stackableType) {
         case 0:
         case 17:
         case 19:
         case 22:
            return 1001;
         case 1:
         case 24:
            return 1000;
         case 2:
            return 1009;
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 15:
         case 16:
         default:
            log.error("UNREACH==getConsumeType==stackableType==" + stackableType);
            return -1;
         case 14:
            return 1005;
         case 18:
            return 1006;
         case 20:
            return 1004;
         case 21:
            return 1003;
         case 23:
            return 1002;
         case 25:
            return 1008;
         case 26:
            return 1007;
      }
   }

   public static int getSkinType(int subtype) {
      if (subtype == 13) {
         return 10001;
      } else if (subtype == 14) {
         return 10000;
      } else if (subtype == 15) {
         return 10002;
      } else {
         log.error("UNREACH==getSkinType==subtype==" + subtype);
         return -1;
      }
   }

   public static PT_STACKABLE setMaterial(int index, int count, boolean bind) {
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = index;
      if (count > 0) {
         ptStackable.count = count;
      }

      if (bind) {
         ptStackable.bind = true;
      }

      return ptStackable;
   }

   public static PT_STACKABLE setConsumable(int index, int count, boolean bind) {
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = index;
      if (count > 0) {
         ptStackable.count = count;
      }

      if (bind) {
         ptStackable.bind = true;
      }

      return ptStackable;
   }

   public static List<giftContent> getGiftList(int index) {
      GiftContentMap giftContentMap = (GiftContentMap)DataCache.GIFT_CONTENT_MAP.get(index);
      List<giftContent> gift = new ArrayList();
      int currcount = 0;
      int lastcount = 0;
      if (((giftContent)giftContentMap.giftContents.get(0)).probability > 1) {
         int allcount = giftContentMap.allCount;
         int random = (int)(Math.random() * (double)allcount);

         for(giftContent giftContent : giftContentMap.giftContents) {
            currcount += giftContent.probability;
            if (lastcount <= random && random < currcount) {
               gift.add(giftContent);
               break;
            }

            lastcount = currcount;
         }
      } else {
         for(giftContent giftContent : giftContentMap.giftContents) {
            gift.add(giftContent);
         }
      }

      return gift;
   }
}
