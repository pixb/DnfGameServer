package com.dnfm.game.auction.model;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.utils.Util;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.protobuf.PT_AUCTION_EQUIP;
import com.dnfm.mina.protobuf.PT_AUCTION_ITEM_PRICE_INFO;
import com.dnfm.mina.protobuf.PT_AUCTION_STACKABLE;
import com.dnfm.mina.session.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuctionCache {
   private static final Logger log = LoggerFactory.getLogger(AuctionCache.class);
   public static Map<Integer, Integer> index2Category = new ConcurrentHashMap();
   public static Map<Integer, List<Integer>> priceMap = new ConcurrentHashMap();
   public static Map<Integer, Integer> avrPriceMap = new ConcurrentHashMap();
   public static Map<Integer, Map<Integer, Map<Long, PT_AUCTION_STACKABLE>>> indexStackableMap = new ConcurrentHashMap();
   public static Map<Integer, Map<Integer, Map<Long, PT_AUCTION_EQUIP>>> indexEquipMap = new ConcurrentHashMap();

   public static List<Integer> getPriceList(int index) {
      List<Integer> priceList = (List)priceMap.get(index);
      return priceList;
   }

   public static List<PT_AUCTION_ITEM_PRICE_INFO> GET_AUCTION_ITEM_PRICE_LIST(int index, int type) {
      List<PT_AUCTION_ITEM_PRICE_INFO> resList = new ArrayList();
      List<Integer> list = getPriceList(index);
      if (list == null) {
         return null;
      } else {
         int size = list.size();

         for(int i = 0; i < size; ++i) {
            int price = (Integer)list.get(i);
            PT_AUCTION_ITEM_PRICE_INFO info = new PT_AUCTION_ITEM_PRICE_INFO();
            info.price = price;
            if (type == 1) {
               info.count = getStackableItemCnt(index, price);
            } else {
               info.count = getEquipItemCnt(index, price);
            }

            if (info.count != 0) {
               resList.add(info);
            }
         }

         return resList;
      }
   }

   public static void removeAuction(long auid, int index) {
      log.error("removeAuction==auid=={}==index=={}", auid, index);
      int type = 1;
      if (ItemDataPool.consumeItemMap.get(index) == null) {
         type = 2;
      }

      if (type == 1) {
         Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map1 = (Map)indexStackableMap.get(index);
         if (map1 != null) {
            for(Map<Long, PT_AUCTION_STACKABLE> map2 : map1.values()) {
               map2.remove(auid);
            }
         }
      } else {
         Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map1 = (Map)indexEquipMap.get(index);
         if (map1 != null) {
            for(Map<Long, PT_AUCTION_EQUIP> map2 : map1.values()) {
               map2.remove(auid);
            }
         }
      }

   }

   public static void addAuctionStackable(PT_AUCTION_STACKABLE stackable) {
      if (indexStackableMap == null) {
         indexStackableMap = new ConcurrentHashMap();
      }

      Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map1 = (Map)indexStackableMap.get(stackable.index);
      if (map1 == null) {
         map1 = new ConcurrentSkipListMap();
      }

      Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)map1.get(stackable.price);
      if (auidMap == null) {
         auidMap = new ConcurrentHashMap();
      }

      auidMap.put(stackable.auid, stackable);
      map1.put(stackable.price, auidMap);
      indexStackableMap.put(stackable.index, map1);
   }

   public static void addAuctionEquip(PT_AUCTION_EQUIP equip) {
      if (indexEquipMap == null) {
         indexEquipMap = new ConcurrentHashMap();
      }

      Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map1 = (Map)indexEquipMap.get(equip.index);
      if (map1 == null) {
         map1 = new ConcurrentSkipListMap();
      }

      Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)map1.get(equip.price);
      if (auidMap == null) {
         auidMap = new ConcurrentHashMap();
      }

      auidMap.put(equip.auid, equip);
      map1.put(equip.price, auidMap);
      indexEquipMap.put(equip.index, map1);
   }

   public static int getStackableCnt(int index) {
      Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)indexStackableMap.get(index);
      if (map == null) {
         return 0;
      } else {
         int cnt = 0;

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_STACKABLE>> entry : map.entrySet()) {
            int price = (Integer)entry.getKey();
            Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)entry.getValue();
            cnt += auidMap.size();
         }

         return cnt;
      }
   }

   public static List<PT_AUCTION_STACKABLE> getStackableList(int index) {
      Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)indexStackableMap.get(index);
      if (map == null) {
         return null;
      } else {
         List<PT_AUCTION_STACKABLE> resList = new ArrayList();

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_STACKABLE>> entry : map.entrySet()) {
            int price = (Integer)entry.getKey();
            Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)entry.getValue();
            resList.addAll(auidMap.values());
         }

         return resList;
      }
   }

   public static int getEquipCnt(int index) {
      Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)indexEquipMap.get(index);
      if (map == null) {
         return 0;
      } else {
         int cnt = 0;

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_EQUIP>> entry : map.entrySet()) {
            int price = (Integer)entry.getKey();
            Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)entry.getValue();
            cnt += auidMap.size();
         }

         return cnt;
      }
   }

   public static int getStackableItemCnt(int index, int targetPrice) {
      Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)indexStackableMap.get(index);
      if (map == null) {
         return 0;
      } else {
         int cnt = 0;

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_STACKABLE>> entry : map.entrySet()) {
            int price = (Integer)entry.getKey();
            if (price == targetPrice) {
               Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)entry.getValue();

               for(PT_AUCTION_STACKABLE pas : auidMap.values()) {
                  cnt += pas.count;
               }
            }
         }

         return cnt;
      }
   }

   public static int getEquipItemCnt(int index, int targetPrice) {
      Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)indexEquipMap.get(index);
      if (map == null) {
         return 0;
      } else {
         int cnt = 0;

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_EQUIP>> entry : map.entrySet()) {
            int price = (Integer)entry.getKey();
            if (price == targetPrice) {
               Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)entry.getValue();

               for(PT_AUCTION_EQUIP pae : auidMap.values()) {
                  cnt += pae.count;
               }
            }
         }

         return cnt;
      }
   }

   public static List<PT_AUCTION_EQUIP> getEquipList(int index) {
      Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)indexEquipMap.get(index);
      if (map == null) {
         return null;
      } else {
         List<PT_AUCTION_EQUIP> resList = new ArrayList();

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_EQUIP>> entry : map.entrySet()) {
            int price = (Integer)entry.getKey();
            Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)entry.getValue();
            resList.addAll(auidMap.values());
         }

         return resList;
      }
   }

   public static List<Integer> getPriceList(int index, int type) {
      if (type == 1) {
         Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)indexStackableMap.get(index);
         if (map == null) {
            return getPriceList((List)null);
         } else {
            List<Integer> priceList = new ArrayList();

            for(Map.Entry<Integer, Map<Long, PT_AUCTION_STACKABLE>> entry : map.entrySet()) {
               int price = (Integer)entry.getKey();
               priceList.add(price);
            }

            return getPriceList(priceList);
         }
      } else {
         Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)indexEquipMap.get(index);
         if (map == null) {
            return getPriceList((List)null);
         } else {
            List<Integer> priceList = new ArrayList();

            for(Map.Entry<Integer, Map<Long, PT_AUCTION_EQUIP>> entry : map.entrySet()) {
               int price = (Integer)entry.getKey();
               priceList.add(price);
            }

            return getPriceList(priceList);
         }
      }
   }

   private static List<Integer> getPriceList(List<Integer> list) {
      if (Util.isEmpty(list)) {
         List<Integer> tmpList = new ArrayList();
         tmpList.add(100);
         tmpList.add(101);
         tmpList.add(102);
         tmpList.add(103);
         tmpList.add(104);
         tmpList.add(105);
         return tmpList;
      } else {
         List<Integer> resList = new ArrayList();
         if (list.size() <= 6) {
            int delta = 6 - list.size();

            for(int i = 0; i < delta; ++i) {
               list.add(200 + i);
            }

            return list;
         } else if (list.size() <= 8) {
            return list;
         } else {
            int size = list.size();

            for(int i = 0; i < 8; ++i) {
               resList.add(list.get(i));
            }

            return resList;
         }
      }
   }

   public static int getPriceListAllCnt(int index, int price, int type) {
      if (type == 1) {
         Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)indexStackableMap.get(index);
         if (map == null) {
            return 0;
         } else {
            int cnt = 0;
            Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)map.get(price);

            for(Map.Entry<Long, PT_AUCTION_STACKABLE> entry : auidMap.entrySet()) {
               cnt += ((PT_AUCTION_STACKABLE)entry.getValue()).count;
            }

            return cnt;
         }
      } else {
         Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)indexEquipMap.get(index);
         if (map == null) {
            return 0;
         } else {
            int cnt = 0;
            Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)map.get(price);

            for(Map.Entry<Long, PT_AUCTION_EQUIP> entry : auidMap.entrySet()) {
               cnt += ((PT_AUCTION_EQUIP)entry.getValue()).count;
            }

            return cnt;
         }
      }
   }

   private static void updateRoleAuctionBox() {
   }

   public static int buyStackable(int index, int price, int cnt) {
      Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)indexStackableMap.get(index);
      if (map == null) {
         log.error("buyStackable error, map==null");
         return -1;
      } else {
         Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)map.get(price);
         int totalCnt = 0;

         for(PT_AUCTION_STACKABLE pas : auidMap.values()) {
            totalCnt += pas.count;
         }

         if (totalCnt < cnt) {
            log.error("buyStackable error, totalCnt: {}, cnt: {}", totalCnt, cnt);
            return -1;
         } else {
            int removeCnt = cnt;
            new ArrayList();
            Iterator<Map.Entry<Long, PT_AUCTION_STACKABLE>> it = auidMap.entrySet().iterator();

            while(it.hasNext() && removeCnt > 0) {
               Map.Entry<Long, PT_AUCTION_STACKABLE> entry = (Map.Entry)it.next();
               long auid = (Long)entry.getKey();
               log.error("AuctionCache.buyStackable==price=={}==auid=={}", price, auid);
               PT_AUCTION_STACKABLE auction = (PT_AUCTION_STACKABLE)entry.getValue();
               long now = TimeUtil.currS();
               if (auction.enddate == null || now < auction.enddate) {
                  if (removeCnt >= auction.count) {
                     long charguid = auction.charguid;
                     IoSession session = SessionManager.INSTANCE.getSessionBy(charguid);
                     Role role = (Role)DataCache.AUCTION_ROLES.get(charguid);
                     Account account = SessionUtils.getAccountBySession(session);
                     AuctionBox auctionBox = role.getAuctionBox();
                     auctionBox.buyStackable(auid, auction.count);
                     role.save();
                     account.save();
                     log.error("AuctionCache.remove-auction=={}", auction.auid);
                     it.remove();
                     removeCnt -= auction.count;
                  } else {
                     long charguid = auction.charguid;
                     IoSession session = SessionManager.INSTANCE.getSessionBy(charguid);
                     Role role = (Role)DataCache.AUCTION_ROLES.get(charguid);
                     Account account = SessionUtils.getAccountBySession(session);
                     AuctionBox auctionBox = role.getAuctionBox();
                     auctionBox.buyStackable(auid, removeCnt);
                     long guid = 0L;
                     if (auction.guid != null) {
                        guid = auction.guid;
                     }

                     role.save();
                     account.save();
                     removeCnt = 0;
                  }
               }
            }

            return 0;
         }
      }
   }

   public static int buyEquip(int index, int price, int cnt) {
      Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)indexEquipMap.get(index);
      if (map == null) {
         log.error("buyEquip error==, map==null");
         return -1;
      } else {
         Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)map.get(price);
         int totalCnt = 0;

         for(PT_AUCTION_EQUIP pas : auidMap.values()) {
            totalCnt += pas.count;
         }

         if (totalCnt < cnt) {
            log.error("buyEquip error==totalCnt: {}, cnt: {}", totalCnt, cnt);
            return -1;
         } else {
            int removeCnt = cnt;
            new ArrayList();
            Iterator<Map.Entry<Long, PT_AUCTION_EQUIP>> it = auidMap.entrySet().iterator();

            while(it.hasNext() && removeCnt > 0) {
               Map.Entry<Long, PT_AUCTION_EQUIP> entry = (Map.Entry)it.next();
               long auid = (Long)entry.getKey();
               PT_AUCTION_EQUIP auction = (PT_AUCTION_EQUIP)entry.getValue();
               long now = TimeUtil.currS();
               if (auction.enddate == null || now < auction.enddate) {
                  if (removeCnt >= auction.count) {
                     long charguid = auction.charguid;
                     IoSession session = SessionManager.INSTANCE.getSessionBy(charguid);
                     Role role = (Role)DataCache.AUCTION_ROLES.get(charguid);
                     Account account = SessionUtils.getAccountBySession(session);
                     AuctionBox auctionBox = role.getAuctionBox();
                     auctionBox.buyEqu(auid, auction.count);
                     role.save();
                     account.save();
                     log.error("AuctionCache.remove-auction=={}", auction.auid);
                     it.remove();
                     removeCnt -= auction.count;
                  } else {
                     long charguid = auction.charguid;
                     IoSession session = SessionManager.INSTANCE.getSessionBy(charguid);
                     Role role = (Role)DataCache.AUCTION_ROLES.get(charguid);
                     Account account = SessionUtils.getAccountBySession(session);
                     AuctionBox auctionBox = role.getAuctionBox();
                     auctionBox.buyEqu(auid, removeCnt);
                     role.save();
                     account.save();
                     removeCnt = 0;
                  }
               }
            }

            return 0;
         }
      }
   }

   public static void main(String[] args) {
      Map<Integer, PT_AUCTION_EQUIP> map = new HashMap();
      PT_AUCTION_EQUIP p = new PT_AUCTION_EQUIP();
      p.count = 100;
      map.put(1, p);
      System.out.println(JSON.toJSON(map));
      int removeCnt = 5;

      for(Iterator<Map.Entry<Integer, PT_AUCTION_EQUIP>> it = map.entrySet().iterator(); it.hasNext() && removeCnt > 0; --removeCnt) {
         Map.Entry<Integer, PT_AUCTION_EQUIP> entry = (Map.Entry)it.next();
         Integer key = (Integer)entry.getKey();
         PT_AUCTION_EQUIP auction = (PT_AUCTION_EQUIP)entry.getValue();
         auction.count = 1;
      }

      System.out.println(JSON.toJSON(map));
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AuctionCache)) {
         return false;
      } else {
         AuctionCache other = (AuctionCache)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AuctionCache;
   }

   public int hashCode() {
      int result = 1;
      return result;
   }

   public String toString() {
      return "AuctionCache()";
   }
}
