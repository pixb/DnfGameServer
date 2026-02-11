package com.dnfm.common.start;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.common.utils.Util;
import com.dnfm.game.ServerService;
import com.dnfm.game.auction.model.AuctionCache;
import com.dnfm.game.auction.model.AuctionItem;
import com.dnfm.game.auction.model.AuctionModel;
import com.dnfm.game.bag.model.ClearDungeonBox;
import com.dnfm.game.config.ConsumeItem;
import com.dnfm.game.config.Equip;
import com.dnfm.game.config.PetExp;
import com.dnfm.game.config.RoleExp;
import com.dnfm.game.config.Server;
import com.dnfm.game.config.Skin;
import com.dnfm.game.config.itemShop;
import com.dnfm.game.config.skillatdemoniclancer;
import com.dnfm.game.config.skillatfighter;
import com.dnfm.game.config.skillatpriest;
import com.dnfm.game.config.skillatswordman;
import com.dnfm.game.config.skillfighter;
import com.dnfm.game.config.skillgunner;
import com.dnfm.game.config.skillmage;
import com.dnfm.game.config.skillpriest;
import com.dnfm.game.config.skillswordman;
import com.dnfm.game.dungeon.model.Dungeon;
import com.dnfm.game.dungeon.model.DungeonCache;
import com.dnfm.game.dungeon.model.DungeonMap;
import com.dnfm.game.dungeon.model.HellPartyInfo;
import com.dnfm.game.dungeon.model.MapSpec;
import com.dnfm.game.dungeon.model.Monster;
import com.dnfm.game.dungeon.model.PartyBoard;
import com.dnfm.game.dungeon.model.RewardItem;
import com.dnfm.game.dungeon.model.RewardList;
import com.dnfm.game.dungeon.model.TowerRewardItem;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.item.model.GiftContentMap;
import com.dnfm.game.item.model.giftContent;
import com.dnfm.game.make.model.AvatarCompoundRes;
import com.dnfm.game.make.model.MakeModel;
import com.dnfm.game.make.model.StackableModel;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.shop.model.GachaRecipeModel;
import com.dnfm.game.shop.model.ShopCache;
import com.dnfm.game.skill.bean.SkillObject;
import com.dnfm.game.skill.model.SkillBox;
import com.dnfm.game.skill.model.SkillslotBox;
import com.dnfm.game.task.model.TaskInfo;
import com.dnfm.game.utils.ProtocalSet;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.protobuf.PT_AUCTION_EQUIP;
import com.dnfm.mina.protobuf.PT_AUCTION_STACKABLE;
import com.dnfm.mina.protobuf.PT_SKILL;
import com.dnfm.mina.protobuf.RES_ITEM_AVR_PRICE;
import com.dnfm.mina.protobuf.RES_SKILL_LIST;
import com.dnfm.mina.session.SessionManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
   Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);
   @Autowired
   Dao dao;

   public static void main(String[] args) {
      MyApplicationRunner myApplicationRunner = new MyApplicationRunner();
      myApplicationRunner.initGachaRecipe();
   }

   public void run(ApplicationArguments var1) {
      this.logger.error("MyApplicationRunner.run...");
      this.initTotal();
      this.initLv28();
      this.initAvatarCompoundResultList();
      this.initgiftUseMap();
      this.initMakeEqu();
      this.initMakeOther();
      this.initRecipeIndex();
      this.initTradeItemList();
      ((ServerService)SpringUtils.getBean(ServerService.class)).init();
      this.initDungeonBalanceTbl();
      this.initMap();
      this.initHellPartyInfo();
      this.initAuctionCache();
      this.initGachaRecipe();
      this.initPriceMap();
      this.initAdventureItems();
      this.initHellItems();
      this.clearCache();
      this.initAllRoles();
      this.initTowerReward();
   }

   private void initTowerReward() {
      List<String> lines = new ArrayList();
      this.readAsList(lines, "TowerReward.txt");

      for(int i = 0; i < lines.size(); i += 4) {
         TowerRewardItem towerRewardItem = new TowerRewardItem();
         int id = Integer.parseInt((String)lines.get(i));
         towerRewardItem.index = Integer.parseInt((String)lines.get(i + 1));
         towerRewardItem.cnt = Integer.parseInt((String)lines.get(i + 2));
         DataCache.towerRewardItemMap.put(id - 1000, towerRewardItem);
      }

      this.logger.error("towerRewardItemMap.size=={}", DataCache.towerRewardItemMap.size());
   }

   private void clearCache() {
      DataCache.ONLINE_ROLES.clear();
      DataCache.ONLINE_ACC_MAP.clear();
      SessionManager.INSTANCE.player2sessions.clear();
   }

   private void initAdventureItems() {
      DungeonCache.adventureItemMap.clear();
      RewardItem hellInviteRoleBind = new RewardItem(2013104182, (double)300.0F, (double)300.0F);
      RewardItem lyt = new RewardItem(2013103578, (double)800.0F, (double)1300.0F);
      RewardItem summerCoin = new RewardItem(2013106215, (double)200.0F, (double)200.0F);
      RewardItem aLether = new RewardItem(2013100822, (double)400.0F, (double)400.0F);
      RewardItem lytRoleBind = new RewardItem(2013104187, (double)300.0F, (double)300.0F);
      RewardItem hellInvite = new RewardItem(2013100709, (double)800.0F, (double)1300.0F);
      RewardItem aIron = new RewardItem(2013104417, (double)400.0F, (double)400.0F);
      RewardItem miracleStone = new RewardItem(2013105726, (double)80.0F, (double)80.0F);
      RewardItem bonePiece = new RewardItem(2013100826, (double)400.0F, (double)400.0F);
      RewardItem diamond = new RewardItem(2013101276, (double)400.0F, (double)400.0F);
      RewardItem eleCrystal = new RewardItem(2013103333, (double)400.0F, (double)400.0F);
      RewardItem messiSeal = new RewardItem(1990000103, (double)400.0F, (double)400.0F);
      RewardItem influence = new RewardItem(2013100841, (double)400.0F, (double)400.0F);
      RewardItem haiqiRing = new RewardItem(2000320005, 0.6, 0.6);
      RewardItem magicCystal = new RewardItem(2013102100, (double)400.0F, (double)400.0F);
      int indexShanji = 2002120370;
      int indexBxsn = 2002120390;
      int indexSkszc = 2002120440;
      int indexBinggong = 2002120410;
      int indexBsfx = 2002120400;
      int indexBwjdxlc = 2002120420;
      List<RewardItem> listShanji = new ArrayList();
      listShanji.add(hellInviteRoleBind);
      listShanji.add(lyt);
      listShanji.add(summerCoin);
      listShanji.add(aLether);
      List<RewardItem> listBxsn = new ArrayList();
      listBxsn.add(lytRoleBind);
      listBxsn.add(hellInvite);
      listBxsn.add(summerCoin);
      listBxsn.add(aIron);
      List<RewardItem> listSkszc = new ArrayList();
      listSkszc.add(lytRoleBind);
      listSkszc.add(hellInviteRoleBind);
      listSkszc.add(miracleStone);
      listSkszc.add(bonePiece);
      List<RewardItem> listBinggong = new ArrayList();
      listBinggong.add(aIron);
      listBinggong.add(aLether);
      listBinggong.add(bonePiece);
      listBinggong.add(diamond);
      listBinggong.add(eleCrystal);
      listBinggong.add(messiSeal);
      listBinggong.add(influence);
      listBinggong.add(hellInviteRoleBind);
      listBinggong.add(lytRoleBind);
      List<RewardItem> listBsfx = new ArrayList();
      listBsfx.add(haiqiRing);
      listBsfx.add(aIron);
      listBsfx.add(aLether);
      listBsfx.add(bonePiece);
      listBsfx.add(diamond);
      listBsfx.add(eleCrystal);
      listBsfx.add(messiSeal);
      listBsfx.add(influence);
      listBsfx.add(hellInviteRoleBind);
      listBsfx.add(lytRoleBind);
      List<RewardItem> listBwjdxlc = new ArrayList();
      listBwjdxlc.add(hellInvite);
      listBwjdxlc.add(hellInviteRoleBind);
      listBwjdxlc.add(lytRoleBind);
      listBwjdxlc.add(lyt);
      listBwjdxlc.add(magicCystal);
      DungeonCache.adventureItemMap.put(indexShanji, listShanji);
      DungeonCache.adventureItemMap.put(indexBxsn, listBxsn);
      DungeonCache.adventureItemMap.put(indexSkszc, listSkszc);
      DungeonCache.adventureItemMap.put(indexBinggong, listBinggong);
      DungeonCache.adventureItemMap.put(indexBsfx, listBsfx);
      DungeonCache.adventureItemMap.put(indexBwjdxlc, listBwjdxlc);
   }

   private void initHellItems() {
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "HellRewards.txt");
      List<RewardItem> rareList = new ArrayList();
      List<RewardItem> everyMustList = new ArrayList();
      List<RewardItem> selList = new ArrayList();
      List<RewardItem> fullMustList = new ArrayList();

      for(String line : lineList) {
         if (line.contains("rare==")) {
            String content = line.split("rare==")[1];
            int index = Integer.parseInt(content.split(" ,")[0]);
            double rate = Double.parseDouble(content.split(" ,")[1]);
            rareList.add(new RewardItem(index, rate, rate));
         } else if (line.contains("must==")) {
            String content = line.split("must==")[1];
            int index = Integer.parseInt(content.split(" ,")[0]);
            String[] split = content.split(" ,")[1].split("-");
            double minRate = Double.parseDouble(split[0]);
            double maxRate = Double.parseDouble(split[1]);
            everyMustList.add(new RewardItem(index, minRate, maxRate));
         } else if (line.contains("sel==")) {
            String content = line.split("sel==")[1];
            int index = Integer.parseInt(content.split(" ,")[0]);
            String[] split = content.split(" ,")[1].split("-");
            double minRate = Double.parseDouble(split[0]);
            double maxRate = Double.parseDouble(split[1]);
            selList.add(new RewardItem(index, minRate, maxRate));
         } else {
            int index = Integer.parseInt(line.split(" ,")[0]);
            double rate = Double.parseDouble(line.split(" ,")[1]);
            fullMustList.add(new RewardItem(index, rate, rate));
         }
      }

      RewardList rewardList = new RewardList();
      rewardList.rareList = rareList;
      rewardList.fullMustList = fullMustList;
      rewardList.everyMustList = everyMustList;
      rewardList.selList = selList;
      DungeonCache.hellItemMap.put(1, rewardList);
   }

   private void initPriceMap() {
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "auction_price.txt");

      for(String line : lineList) {
         RES_ITEM_AVR_PRICE res_item_avr_price = (RES_ITEM_AVR_PRICE)JSON.parseObject(line, RES_ITEM_AVR_PRICE.class);
         AuctionCache.priceMap.put(res_item_avr_price.index, res_item_avr_price.prices);
         AuctionCache.avrPriceMap.put(res_item_avr_price.index, res_item_avr_price.price);
      }

      this.logger.error("auctionPriceMap.size=={}", AuctionCache.priceMap.size());
      this.logger.error("auctionAvrPriceMap.size=={}", AuctionCache.avrPriceMap.size());
   }

   private void readGachaRecipeTbl(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);
         boolean start = false;

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (lineTxt.contains("[recipe]")) {
               start = true;
            } else if (start) {
               if (lineTxt.contains("[/recipe]")) {
                  break;
               }

               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void initGachaRecipe() {
      ShopCache.GACHA_RECIPE_MAP.clear();
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "GachaRecipeItem.txt");
      int rateNum = 0;

      for(int i = 0; i < lineList.size(); ++i) {
         String[] strArr = ((String)lineList.get(i)).split("\\|");
         GachaRecipeModel gachaRecipeModel = new GachaRecipeModel();
         gachaRecipeModel.recipe = Integer.parseInt(strArr[0]);
         gachaRecipeModel.index = Integer.parseInt(strArr[1]);
         gachaRecipeModel.count = Integer.parseInt(strArr[2]);
         gachaRecipeModel.rate = Integer.parseInt(strArr[3]);
         gachaRecipeModel.grade = Integer.parseInt(strArr[4]);
         rateNum += gachaRecipeModel.rate;
         gachaRecipeModel.rateNum = rateNum;
         List<GachaRecipeModel> gList = (List)ShopCache.GACHA_RECIPE_MAP.get(1);
         if (gList == null) {
            gList = new ArrayList();
         }

         gList.add(gachaRecipeModel);
         ShopCache.GACHA_RECIPE_MAP.put(1, gList);
      }

      ShopCache.TOTAL_RATE = rateNum;
      this.logger.error("ShopCache.GACHA_RECIPE_MAP.size=={}", ShopCache.GACHA_RECIPE_MAP.size());
   }

   private void initAuctionCache() {
      List<AuctionItem> auctionItemList = this.dao.query(AuctionItem.class, Cnd.NEW());
      if (!Util.isEmpty(auctionItemList)) {
         this.logger.error("拍卖行.size=={}", auctionItemList.size());
      } else {
         this.logger.error("拍卖行.size==0");
      }

      Map<Integer, Map<Integer, Map<Long, PT_AUCTION_STACKABLE>>> indexStackableMap = new ConcurrentHashMap();
      Map<Integer, Map<Integer, Map<Long, PT_AUCTION_EQUIP>>> indexEquipMap = new ConcurrentHashMap();

      for(AuctionItem auctionItem : auctionItemList) {
         long auid = auctionItem.getAuid();
         int type = auctionItem.getType();
         int price = auctionItem.getPrice();
         int index1 = auctionItem.getIndex1();
         if (type == 1) {
            Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> priceMap = (Map)indexStackableMap.get(index1);
            if (priceMap == null) {
               priceMap = new ConcurrentSkipListMap();
            }

            Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)priceMap.get(price);
            if (auidMap == null) {
               auidMap = new ConcurrentHashMap();
            }

            PT_AUCTION_STACKABLE stackable = auctionItem.getStackable();
            auidMap.put(auid, stackable);
            priceMap.put(price, auidMap);
            indexStackableMap.put(index1, priceMap);
         } else {
            Map<Integer, Map<Long, PT_AUCTION_EQUIP>> priceMap = (Map)indexEquipMap.get(index1);
            if (priceMap == null) {
               priceMap = new ConcurrentSkipListMap();
            }

            Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)priceMap.get(price);
            if (auidMap == null) {
               auidMap = new ConcurrentHashMap();
            }

            PT_AUCTION_EQUIP equip = auctionItem.getEquip();
            auidMap.put(auid, equip);
            priceMap.put(price, auidMap);
            indexEquipMap.put(index1, priceMap);
         }
      }

      AuctionCache.indexEquipMap = indexEquipMap;
      AuctionCache.indexStackableMap = indexStackableMap;
   }

   private void initHellPartyInfo() {
      DungeonCache.hellPartyInfoMap.clear();
      HellPartyInfo hellPartyInfo1 = new HellPartyInfo();
      hellPartyInfo1.hellObjectIndex = 2009000017;
      hellPartyInfo1.dropObjectIndex = 2009000018;
      hellPartyInfo1.hellMonsters = new ArrayList();
      hellPartyInfo1.hellMonsters.add(201050);
      hellPartyInfo1.hellMonsters.add(201051);
      hellPartyInfo1.hellMonsters.add(201052);
      hellPartyInfo1.hellMonsters.add(201053);
      hellPartyInfo1.hellMonsters2 = new ArrayList();
      hellPartyInfo1.hellMonsters2.add(2007000011);
      hellPartyInfo1.hellMonsters2.add(2007000012);
      hellPartyInfo1.hellMonsters2.add(2007000013);
      hellPartyInfo1.hellMonsters2.add(2007000014);
      hellPartyInfo1.hellMonsters2.add(2007000015);
      hellPartyInfo1.hellMonsters2.add(2007000016);
      DungeonCache.hellPartyInfoMap.put(2002140002, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140003, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140004, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140005, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140006, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140007, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140008, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140009, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140010, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140011, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140100, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140101, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140102, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140103, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140104, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140105, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140106, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140107, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140108, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140109, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140110, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140111, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140112, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140113, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140114, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140115, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140116, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140117, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140118, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140119, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140120, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140121, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140122, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140123, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140124, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140125, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002140126, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002141001, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002141002, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002141003, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002141004, hellPartyInfo1);
      DungeonCache.hellPartyInfoMap.put(2002141005, hellPartyInfo1);
   }

   private void initRecipeIndex() {
      DataCache.RECIPE_INDEX.clear();
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "MakeStackable.txt");

      for(int i = 0; i < lineList.size(); i += 10) {
         StackableModel stackableModel = new StackableModel();
         if (i < lineList.size()) {
            stackableModel.recipeindex = Integer.parseInt((String)lineList.get(i));
         }

         if (i + 1 < lineList.size()) {
            stackableModel.index = Integer.parseInt((String)lineList.get(i + 1));
         }

         if (i + 6 < lineList.size()) {
            stackableModel.price = Integer.parseInt((String)lineList.get(i + 6));
         }

         DataCache.RECIPE_INDEX.put(stackableModel.recipeindex, stackableModel);
      }

      this.logger.error("RECIPE_INDEX size: {}", DataCache.RECIPE_INDEX.size());
   }

   private void initAvatarCompoundResultList() {
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "AvatarCompoundResultList.tbl");
      int state = -1;
      String job = "[swordman]";
      String rarity = "uncommon";
      String type = "[hat avatar]";
      List<AvatarCompoundRes> list = new ArrayList();

      for(int i = 0; i < lineList.size(); ++i) {
         String line = (String)lineList.get(i);
         if (state == -1) {
            if (line.contains("[job]")) {
               state = 1;
            } else if (line.contains("[rarity]")) {
               state = 2;
            } else if (line.contains("[type]")) {
               state = 3;
            }
         } else if (state == 5) {
            if (line.contains("[/type]")) {
               if ("[swordman]".equals(job)) {
                  if ("uncommon".equals(rarity)) {
                     DataCache.avatarCompoundResSwordman.put(type, list);
                  } else {
                     DataCache.avatarCompoundResSwordman2.put(type, list);
                  }
               } else if ("[fighter]".equals(job)) {
                  if ("uncommon".equals(rarity)) {
                     DataCache.avatarCompoundResFighter.put(type, list);
                  } else {
                     DataCache.avatarCompoundResFighter2.put(type, list);
                  }
               } else if ("[gunner]".equals(job)) {
                  if ("uncommon".equals(rarity)) {
                     DataCache.avatarCompoundResGunner.put(type, list);
                  } else {
                     DataCache.avatarCompoundResGunner2.put(type, list);
                  }
               } else if ("[mage]".equals(job)) {
                  if ("uncommon".equals(rarity)) {
                     DataCache.avatarCompoundResMage.put(type, list);
                  } else {
                     DataCache.avatarCompoundResMage2.put(type, list);
                  }
               } else if ("[at priest]".equals(job)) {
                  if ("uncommon".equals(rarity)) {
                     DataCache.avatarCompoundResPriest.put(type, list);
                  } else {
                     DataCache.avatarCompoundResPriest2.put(type, list);
                  }
               }

               state = -1;
            } else {
               int index = Integer.parseInt(line.trim());
               int cnt = Integer.parseInt(((String)lineList.get(i + 1)).trim());
               ++i;
               AvatarCompoundRes avatarCompoundRes = new AvatarCompoundRes();
               avatarCompoundRes.index = index;
               avatarCompoundRes.cnt = cnt;
               list.add(avatarCompoundRes);
            }
         } else if (state == 1) {
            job = line.trim();
            state = -1;
         } else if (state == 2) {
            rarity = line.trim();
            state = -1;
         } else if (state == 3) {
            type = line.trim();
            list = new ArrayList();
            state = 5;
         }
      }

   }

   private void initLv28_swordman() {
      int job = 0;
      int growtype = 1;
      String key = job + "_" + growtype;
      String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox);
      String skillStr = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 67, \"level\": 1}, {\"index\": 33, \"level\": 1}, {\"index\": 94, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 67, \"slot\": 6}, {\"index\": 67, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillStr, RES_SKILL_LIST.class);
      String skillStrPk = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 67, \"level\": 1}, {\"index\": 33, \"level\": 1}, {\"index\": 94, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 67, \"slot\": 6}, {\"index\": 67, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillStrPk, RES_SKILL_LIST.class);
      SkillBox skillBox = this.getSkillBox(skillList, skillListPk);
      DataCache.lv28SkillBox.put(key, skillBox);
      SkillslotBox skillslotBox = this.getSkillslotBox(skillList, skillListPk);
      DataCache.lv28SkillslotBox.put(key, skillslotBox);
      growtype = 2;
      key = job + "_" + growtype;
      String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox2 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox2);
      String skillStr2 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 35, \"level\": 1}, {\"index\": 29, \"level\": 1}, {\"index\": 25, \"level\": 1}, {\"index\": 93, \"level\": 1}, {\"index\": 18, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 25, \"slot\": 6}, {\"index\": 25, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillStr2, RES_SKILL_LIST.class);
      String skillStrPk2 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 35, \"level\": 1}, {\"index\": 29, \"level\": 1}, {\"index\": 25, \"level\": 1}, {\"index\": 93, \"level\": 1}, {\"index\": 18, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 25, \"slot\": 6}, {\"index\": 25, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk2, RES_SKILL_LIST.class);
      SkillBox skillBox2 = this.getSkillBox(skillList2, skillListPk2);
      DataCache.lv28SkillBox.put(key, skillBox2);
      SkillslotBox skillslotBox2 = this.getSkillslotBox(skillList2, skillListPk2);
      DataCache.lv28SkillslotBox.put(key, skillslotBox2);
      growtype = 3;
      key = job + "_" + growtype;
      String clearDungeonStr3 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox3 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr3, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox3);
      String skillStr3 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 56, \"level\": 1}, {\"index\": 63, \"level\": 1}, {\"index\": 76, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList3 = (RES_SKILL_LIST)JSON.parseObject(skillStr3, RES_SKILL_LIST.class);
      String skillStrPk3 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 56, \"level\": 1}, {\"index\": 63, \"level\": 1}, {\"index\": 76, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk3 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk3, RES_SKILL_LIST.class);
      SkillBox skillBox3 = this.getSkillBox(skillList3, skillListPk3);
      DataCache.lv28SkillBox.put(key, skillBox3);
      SkillslotBox skillslotBox3 = this.getSkillslotBox(skillList3, skillListPk3);
      DataCache.lv28SkillslotBox.put(key, skillslotBox3);
      growtype = 4;
      key = job + "_" + growtype;
      String clearDungeonStr4 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox4 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr4, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox4);
      String skillStr4 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 47, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 61, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList4 = (RES_SKILL_LIST)JSON.parseObject(skillStr4, RES_SKILL_LIST.class);
      String skillStrPk4 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 47, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 61, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 46, \"slot\": 1}, {\"index\": 46, \"slot\": 21}, {\"index\": 5, \"slot\": 2}, {\"index\": 5, \"slot\": 22}, {\"index\": 58, \"slot\": 3}, {\"index\": 58, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 20, \"slot\": 5}, {\"index\": 20, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk4 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk4, RES_SKILL_LIST.class);
      SkillBox skillBox4 = this.getSkillBox(skillList4, skillListPk4);
      DataCache.lv28SkillBox.put(key, skillBox4);
      SkillslotBox skillslotBox4 = this.getSkillslotBox(skillList4, skillListPk4);
      DataCache.lv28SkillslotBox.put(key, skillslotBox4);
   }

   private void initLv28_fighter() {
      int job = 1;
      int growtype = 1;
      String key = job + "_" + growtype;
      String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox);
      String skillStr = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 86, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 35, \"level\": 1}, {\"index\": 29, \"level\": 1}, {\"index\": 97, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 79, \"level\": 1}, {\"index\": 341, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 5, \"slot\": 1}, {\"index\": 5, \"slot\": 21}, {\"index\": 46, \"slot\": 2}, {\"index\": 46, \"slot\": 22}, {\"index\": 9, \"slot\": 3}, {\"index\": 9, \"slot\": 23}, {\"index\": 86, \"slot\": 4}, {\"index\": 86, \"slot\": 24}, {\"index\": 12, \"slot\": 5}, {\"index\": 12, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillStr, RES_SKILL_LIST.class);
      String skillStrPk = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 86, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 35, \"level\": 1}, {\"index\": 29, \"level\": 1}, {\"index\": 97, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 79, \"level\": 1}, {\"index\": 341, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 5, \"slot\": 1}, {\"index\": 5, \"slot\": 21}, {\"index\": 46, \"slot\": 2}, {\"index\": 46, \"slot\": 22}, {\"index\": 9, \"slot\": 3}, {\"index\": 9, \"slot\": 23}, {\"index\": 86, \"slot\": 4}, {\"index\": 86, \"slot\": 24}, {\"index\": 12, \"slot\": 5}, {\"index\": 12, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillStrPk, RES_SKILL_LIST.class);
      SkillBox skillBox = this.getSkillBox(skillList, skillListPk);
      DataCache.lv28SkillBox.put(key, skillBox);
      SkillslotBox skillslotBox = this.getSkillslotBox(skillList, skillListPk);
      DataCache.lv28SkillslotBox.put(key, skillslotBox);
      growtype = 2;
      key = job + "_" + growtype;
      String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox2 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox2);
      String skillStr2 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 86, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 56, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 342, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 5, \"slot\": 1}, {\"index\": 5, \"slot\": 21}, {\"index\": 46, \"slot\": 2}, {\"index\": 46, \"slot\": 22}, {\"index\": 9, \"slot\": 3}, {\"index\": 9, \"slot\": 23}, {\"index\": 86, \"slot\": 4}, {\"index\": 86, \"slot\": 24}, {\"index\": 12, \"slot\": 5}, {\"index\": 12, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillStr2, RES_SKILL_LIST.class);
      String skillStrPk2 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 86, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 56, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 342, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 5, \"slot\": 1}, {\"index\": 5, \"slot\": 21}, {\"index\": 46, \"slot\": 2}, {\"index\": 46, \"slot\": 22}, {\"index\": 9, \"slot\": 3}, {\"index\": 9, \"slot\": 23}, {\"index\": 86, \"slot\": 4}, {\"index\": 86, \"slot\": 24}, {\"index\": 12, \"slot\": 5}, {\"index\": 12, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk2, RES_SKILL_LIST.class);
      SkillBox skillBox2 = this.getSkillBox(skillList2, skillListPk2);
      DataCache.lv28SkillBox.put(key, skillBox2);
      SkillslotBox skillslotBox2 = this.getSkillslotBox(skillList2, skillListPk2);
      DataCache.lv28SkillslotBox.put(key, skillslotBox2);
   }

   private void initLv28_gunner() {
      int job = 2;
      int growtype = 1;
      String key = job + "_" + growtype;
      String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox);
      String skillStr = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 22, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 27, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 32, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillStr, RES_SKILL_LIST.class);
      String skillStrPk = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 22, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 27, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 32, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillStrPk, RES_SKILL_LIST.class);
      SkillBox skillBox = this.getSkillBox(skillList, skillListPk);
      DataCache.lv28SkillBox.put(key, skillBox);
      SkillslotBox skillslotBox = this.getSkillslotBox(skillList, skillListPk);
      DataCache.lv28SkillslotBox.put(key, skillslotBox);
      growtype = 2;
      key = job + "_" + growtype;
      String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox2 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox2);
      String skillStr2 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 27, \"level\": 1}, {\"index\": 92, \"level\": 1}, {\"index\": 335, \"level\": 1}, {\"index\": 32, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillStr2, RES_SKILL_LIST.class);
      String skillStrPk2 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 27, \"level\": 1}, {\"index\": 92, \"level\": 1}, {\"index\": 335, \"level\": 1}, {\"index\": 32, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk2, RES_SKILL_LIST.class);
      SkillBox skillBox2 = this.getSkillBox(skillList2, skillListPk2);
      DataCache.lv28SkillBox.put(key, skillBox2);
      SkillslotBox skillslotBox2 = this.getSkillslotBox(skillList2, skillListPk2);
      DataCache.lv28SkillslotBox.put(key, skillslotBox2);
      growtype = 3;
      key = job + "_" + growtype;
      String clearDungeonStr3 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox3 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr3, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox3);
      String skillStr3 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 27, \"level\": 1}, {\"index\": 292, \"level\": 1}, {\"index\": 91, \"level\": 1}, {\"index\": 32, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList3 = (RES_SKILL_LIST)JSON.parseObject(skillStr3, RES_SKILL_LIST.class);
      String skillStrPk3 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 27, \"level\": 1}, {\"index\": 292, \"level\": 1}, {\"index\": 91, \"level\": 1}, {\"index\": 32, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk3 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk3, RES_SKILL_LIST.class);
      SkillBox skillBox3 = this.getSkillBox(skillList3, skillListPk3);
      DataCache.lv28SkillBox.put(key, skillBox3);
      SkillslotBox skillslotBox3 = this.getSkillslotBox(skillList3, skillListPk3);
      DataCache.lv28SkillslotBox.put(key, skillslotBox3);
      growtype = 4;
      key = job + "_" + growtype;
      String clearDungeonStr4 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox4 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr4, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox4);
      String skillStr4 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 61, \"level\": 1}, {\"index\": 112, \"level\": 1}, {\"index\": 30, \"level\": 1}, {\"index\": 259, \"level\": 1}, {\"index\": 277, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 27, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}], \"buff\": [{\"index\": 30}, {\"index\": 30, \"slot\": 5}, {\"index\": 277, \"slot\": 1}, {\"index\": 277, \"slot\": 6}]}}";
      RES_SKILL_LIST skillList4 = (RES_SKILL_LIST)JSON.parseObject(skillStr4, RES_SKILL_LIST.class);
      String skillStrPk4 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 61, \"level\": 1}, {\"index\": 112, \"level\": 1}, {\"index\": 30, \"level\": 1}, {\"index\": 259, \"level\": 1}, {\"index\": 277, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 27, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}], \"buff\": [{\"index\": 30}, {\"index\": 30, \"slot\": 5}, {\"index\": 277, \"slot\": 1}, {\"index\": 277, \"slot\": 6}]}}";
      RES_SKILL_LIST skillListPk4 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk4, RES_SKILL_LIST.class);
      SkillBox skillBox4 = this.getSkillBox(skillList4, skillListPk4);
      DataCache.lv28SkillBox.put(key, skillBox4);
      SkillslotBox skillslotBox4 = this.getSkillslotBox(skillList4, skillListPk4);
      DataCache.lv28SkillslotBox.put(key, skillslotBox4);
   }

   private void initLv28_mage() {
      int job = 3;
      int growtype = 1;
      String key = job + "_" + growtype;
      String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox);
      String skillStr = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 43, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 107, \"level\": 1}, {\"index\": 342, \"level\": 1}, {\"index\": 42, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 12, \"slot\": 1}, {\"index\": 12, \"slot\": 21}, {\"index\": 15, \"slot\": 2}, {\"index\": 15, \"slot\": 22}, {\"index\": 16, \"slot\": 3}, {\"index\": 16, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 17, \"slot\": 5}, {\"index\": 17, \"slot\": 25}, {\"index\": 43, \"slot\": 6}, {\"index\": 43, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillStr, RES_SKILL_LIST.class);
      String skillStrPk = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 43, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 107, \"level\": 1}, {\"index\": 342, \"level\": 1}, {\"index\": 42, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 12, \"slot\": 1}, {\"index\": 12, \"slot\": 21}, {\"index\": 15, \"slot\": 2}, {\"index\": 15, \"slot\": 22}, {\"index\": 16, \"slot\": 3}, {\"index\": 16, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 17, \"slot\": 5}, {\"index\": 17, \"slot\": 25}, {\"index\": 43, \"slot\": 6}, {\"index\": 43, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillStrPk, RES_SKILL_LIST.class);
      SkillBox skillBox = this.getSkillBox(skillList, skillListPk);
      DataCache.lv28SkillBox.put(key, skillBox);
      SkillslotBox skillslotBox = this.getSkillslotBox(skillList, skillListPk);
      DataCache.lv28SkillslotBox.put(key, skillslotBox);
      growtype = 4;
      key = job + "_" + growtype;
      String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox2 = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox2);
      String skillStr2 = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 43, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 91, \"level\": 1}, {\"index\": 102, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 89, \"level\": 1}, {\"index\": 93, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 12, \"slot\": 1}, {\"index\": 12, \"slot\": 21}, {\"index\": 15, \"slot\": 2}, {\"index\": 15, \"slot\": 22}, {\"index\": 16, \"slot\": 3}, {\"index\": 16, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 17, \"slot\": 5}, {\"index\": 17, \"slot\": 25}, {\"index\": 43, \"slot\": 6}, {\"index\": 43, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillStr2, RES_SKILL_LIST.class);
      String skillStrPk2 = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 43, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 91, \"level\": 1}, {\"index\": 102, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 89, \"level\": 1}, {\"index\": 93, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 12, \"slot\": 1}, {\"index\": 12, \"slot\": 21}, {\"index\": 15, \"slot\": 2}, {\"index\": 15, \"slot\": 22}, {\"index\": 16, \"slot\": 3}, {\"index\": 16, \"slot\": 23}, {\"index\": 65, \"slot\": 4}, {\"index\": 65, \"slot\": 24}, {\"index\": 17, \"slot\": 5}, {\"index\": 17, \"slot\": 25}, {\"index\": 43, \"slot\": 6}, {\"index\": 43, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillStrPk2, RES_SKILL_LIST.class);
      SkillBox skillBox2 = this.getSkillBox(skillList2, skillListPk2);
      DataCache.lv28SkillBox.put(key, skillBox2);
      SkillslotBox skillslotBox2 = this.getSkillslotBox(skillList2, skillListPk2);
      DataCache.lv28SkillslotBox.put(key, skillslotBox2);
   }

   private void initLv28_priest() {
      int job = 14;
      int growtype = 1;
      String key = job + "_" + growtype;
      String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}]}";
      ClearDungeonBox clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
      DataCache.lv28ClearDungeonBox.put(key, clearDungeonBox);
      String skillStr = "{\"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 1, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 18, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 19, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 1, \"slot\": 1}, {\"index\": 1, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 9, \"slot\": 3}, {\"index\": 9, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 11, \"slot\": 5}, {\"index\": 11, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillStr, RES_SKILL_LIST.class);
      String skillStrPk = "{\"type\": 1, \"sp\": 1590, \"skilllist\": [{\"index\": 174, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 1, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 18, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 19, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 1, \"slot\": 1}, {\"index\": 1, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 9, \"slot\": 3}, {\"index\": 9, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 11, \"slot\": 5}, {\"index\": 11, \"slot\": 25}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}]}}";
      RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillStrPk, RES_SKILL_LIST.class);
      SkillBox skillBox = this.getSkillBox(skillList, skillListPk);
      DataCache.lv28SkillBox.put(key, skillBox);
      SkillslotBox skillslotBox = this.getSkillslotBox(skillList, skillListPk);
      DataCache.lv28SkillslotBox.put(key, skillslotBox);
   }

   private void initTradeItemList() {
      DataCache.auctionCategoryMap.clear();
      List<String> resList = new ArrayList();
      this.readTradeItemTbl(resList, "TradeItemList.tbl");

      for(int i = 0; i < resList.size(); i += 5) {
         AuctionModel auctionModel = new AuctionModel();
         auctionModel.category = Integer.parseInt((String)resList.get(i));
         auctionModel.index = Integer.parseInt((String)resList.get(i + 1));
         auctionModel.name = (String)resList.get(i + 2);
         auctionModel.intVal = Integer.parseInt((String)resList.get(i + 3));
         auctionModel.rarity = (String)resList.get(i + 4);
         List<AuctionModel> auctionModelList = (List)DataCache.auctionCategoryMap.get(auctionModel.category);
         if (auctionModelList == null) {
            auctionModelList = new ArrayList();
         }

         auctionModelList.add(auctionModel);
         DataCache.auctionCategoryMap.put(auctionModel.category, auctionModelList);
         AuctionCache.index2Category.put(auctionModel.index, auctionModel.category);
      }

      this.logger.error("auctionCategoryMap.size=={}", DataCache.auctionCategoryMap.size());

      for(Map.Entry<Integer, List<AuctionModel>> entry : DataCache.auctionCategoryMap.entrySet()) {
         int category = (Integer)entry.getKey();
         List<AuctionModel> auctionModelList = (List)entry.getValue();
         int index = ((AuctionModel)auctionModelList.get(0)).index;
         ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(index);
         if (consumeItem != null) {
            DataCache.auctionCategoryType.put(category, 1);
         } else {
            DataCache.auctionCategoryType.put(category, 2);
         }
      }

   }

   private void initMakeEqu() {
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "make_equ.json");
      StringBuilder sb = new StringBuilder();

      for(String line : lineList) {
         sb.append(line);
      }

      String str = sb.toString();
      JSONObject jsonObject = new JSONObject(str);
      Iterator<String> iterator = jsonObject.keys();

      while(iterator.hasNext()) {
         String key = (String)iterator.next();
         JSONObject obj = jsonObject.getJSONObject(key);
         MakeModel model = new MakeModel();
         model.needs = new HashMap();
         Iterator<String> objIterator = obj.keys();

         while(objIterator.hasNext()) {
            String objKey = (String)objIterator.next();
            int objValue = obj.getInt(objKey);
            if (objKey.contains("price")) {
               model.price = objValue;
            } else {
               model.needs.put(Integer.parseInt(objKey), objValue);
            }
         }

         DataCache.MAKE_EQU_MAP.put(Integer.parseInt(key), model);
      }

   }

   public void initgiftUseMap() {
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "BoxItemRecipe.json");
      StringBuilder sb = new StringBuilder();

      for(String line : lineList) {
         sb.append(line);
      }

      String str = sb.toString();
      JSONObject jsonObject = new JSONObject(str);
      Iterator<String> iterator = jsonObject.keys();

      while(iterator.hasNext()) {
         String key = (String)iterator.next();
         JSONObject obj = jsonObject.getJSONObject(key);
         JSONArray array = obj.getJSONArray("item_list");
         List<giftContent> list = new ArrayList();
         GiftContentMap giftContentMap = new GiftContentMap();
         int count = 0;

         for(int i = 0; i < array.toList().size(); ++i) {
            giftContent content = new giftContent();
            content.id = array.getJSONObject(i).getInt("id");
            content.count = array.getJSONObject(i).getInt("count");
            content.probability = array.getJSONObject(i).getInt("probability");
            if (content.probability > 1) {
               count += content.probability;
            }

            list.add(content);
         }

         giftContentMap.allCount = count;
         giftContentMap.giftContents = list;
         DataCache.GIFT_CONTENT_MAP.put(Integer.parseInt(key), giftContentMap);
      }

   }

   private void initMakeOther() {
      List<String> lineList = new ArrayList();
      this.readAsList(lineList, "make_other.json");
      StringBuilder sb = new StringBuilder();

      for(String line : lineList) {
         sb.append(line);
      }

      String str = sb.toString();
      JSONObject jsonObject = new JSONObject(str);
      Iterator<String> iterator = jsonObject.keys();

      while(iterator.hasNext()) {
         String key = (String)iterator.next();
         JSONObject obj = jsonObject.getJSONObject(key);
         MakeModel model = new MakeModel();
         model.needs = new HashMap();
         Iterator<String> objIterator = obj.keys();

         while(objIterator.hasNext()) {
            String objKey = (String)objIterator.next();
            int objValue = obj.getInt(objKey);
            if (objKey.contains("price")) {
               model.price = objValue;
            } else {
               model.needs.put(Integer.parseInt(objKey), objValue);
            }
         }

         DataCache.MAKE_OTHER_MAP.put(Integer.parseInt(key), model);
      }

   }

   private void printAvatarCompoundResultList(Map<String, List<AvatarCompoundRes>> map) {
      this.logger.error(map.size() + "");
      this.logger.error("========");
   }

   public void initLv28() {
      this.initLv28_swordman();
      this.initLv28_fighter();
      this.initLv28_gunner();
      this.initLv28_mage();
      this.initLv28_priest();
   }

   public void initLv55() {
   }

   public void initTotal() {
      this.logger.error("初始化游戏配置数据...");
      this.initExp();
      this.initConsumeItem();
      this.initShopItem();
      this.initItemList();
      this.initSkill();
      this.initServer();
      this.initProtocal();
      this.initEquip();
      this.initTaskInfo();
      this.initDungeon();
      this.initDungeonMap();
      this.inintEquipSkin();
      EquipDataPool.initUpgradeConfig();
      this.initDungeIdMap();
      this.initHellSlotDungeon();
   }

   private void readQuestTbl(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (!lineTxt.contains("[quest sequence]")) {
               if (lineTxt.contains("[/quest sequence]")) {
                  break;
               }

               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void readHellDungeonTbl(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (!lineTxt.contains("[HellSlotDungeon]")) {
               if (lineTxt.contains("[/HellSlotDungeon]")) {
                  break;
               }

               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void initHellSlotDungeon() {
      DungeonCache.hellSlotMap.clear();
      List<String> lineList = new ArrayList();
      this.readHellDungeonTbl(lineList, "HellSlotDungeon.tbl");

      for(int i = 0; i < lineList.size(); i += 3) {
         int dungeonIndex = Integer.parseInt((String)lineList.get(i + 1));
         int slot = Integer.parseInt((String)lineList.get(i + 2));
         DungeonCache.hellSlotMap.put(dungeonIndex, slot);
      }

   }

   private void readAsList(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (!Util.isEmpty(lineTxt.trim())) {
               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void readTradeItemTbl(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);
         boolean start = false;

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (lineTxt.contains("[trade item list]")) {
               start = true;
            } else if (start) {
               if (lineTxt.contains("[/trade item list]")) {
                  break;
               }

               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void readDungeonArea(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);
         boolean start = false;

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (lineTxt.contains("[Dungeon Area]")) {
               start = true;
            } else if (start) {
               if (lineTxt.contains("[/Dungeon Area]")) {
                  break;
               }

               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void readAreaDungeon(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);
         boolean start = false;

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            if (lineTxt.contains("[Area Dungeon]")) {
               start = true;
            } else if (start) {
               if (lineTxt.contains("[/Area Dungeon]")) {
                  break;
               }

               list.add(lineTxt);
            }
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void readDungeonBalanceTbl(List<String> list, String name) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource(name);
         InputStream io = resource.getInputStream();
         InputStreamReader read = new InputStreamReader(io);
         BufferedReader bufferedReader = new BufferedReader(read);

         String lineTxt;
         while((lineTxt = bufferedReader.readLine()) != null) {
            list.add(lineTxt);
         }

         read.close();
      } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
      }

   }

   private void initDungeonBalanceTbl() {
      List<String> list = new ArrayList();
      this.readDungeonBalanceTbl(list, "DungeonBalanceTable.tbl");

      for(int i = 0; i < list.size(); i += 5) {
         int index = Integer.parseInt((String)list.get(i));
         String type = (String)list.get(i + 1);
         int level = Integer.parseInt((String)list.get(i + 2));
         if (type.contains("normal")) {
            DataCache.dungeonNormalLevelMap.put(index, level);
         } else if (type.contains("expert")) {
            DataCache.dungeonExpertLevelMap.put(index, level);
         } else if (type.contains("master")) {
            DataCache.dungeonMasterLevelMap.put(index, level);
         } else if (type.contains("king")) {
            DataCache.dungeonKingLevelMap.put(index, level);
         } else if (type.contains("slayer")) {
            DataCache.dungeonSplayerLevelMap.put(index, level);
         } else {
            this.logger.error("UNREACH==initDungeonBalanceTbl==type==" + type);
         }
      }

      this.logger.error("dungeonNormalLevelMap size:{}", DataCache.dungeonNormalLevelMap.size());
   }

   private void initDungeIdMap() {
      List<String> resList = new ArrayList();
      this.readQuestTbl(resList, "Quest.tbl");

      for(int i = 0; i < resList.size(); i += 2) {
         String lineTxt = (String)resList.get(i);
         if (i + 1 >= resList.size()) {
            break;
         }

         String lineTxt2 = (String)resList.get(i + 1);
         int id = Integer.parseInt(lineTxt);
         int index = Integer.parseInt(lineTxt2);
         DataCache.QUEST_INDEX2ID_MAP.put(index, id);
      }

      this.logger.error("初始化dungeonIdMap=={}", DataCache.QUEST_INDEX2ID_MAP.size());
   }

   private void initPartyBoard() {
      List<String> resList = new ArrayList();
      this.readDungeonArea(resList, "PartyBoard.tbl");

      for(int i = 0; i < resList.size(); i += 4) {
         String areaStr = (String)resList.get(i);
         String subtypeStr = (String)resList.get(i + 1);
         String stageindexStr = (String)resList.get(i + 2);
         String dungeonindexStr = (String)resList.get(i + 3);
         int area = Integer.parseInt(areaStr);
         int subtype = Integer.parseInt(subtypeStr);
         int stageindex = Integer.parseInt(stageindexStr);
         int dungeonindex = Integer.parseInt(dungeonindexStr);
         PartyBoard partyBoard = new PartyBoard();
         partyBoard.type = 1;
         partyBoard.area = area;
         partyBoard.subtype = subtype;
         partyBoard.stageindex = stageindex;
         partyBoard.dungeonindex = dungeonindex;
         String key = area + "_" + subtype;
         String key2 = area + "_" + subtype + "_" + stageindex;
         List<PartyBoard> list1 = (List)DataCache.partyBoardMap1.get(key);
         if (list1 == null) {
            list1 = new ArrayList();
         }

         list1.add(partyBoard);
         DataCache.partyBoardMap1.put(key, list1);
         List<PartyBoard> list2 = (List)DataCache.partyBoardMap1s.get(key2);
         if (list2 == null) {
            list2 = new ArrayList();
         }

         list2.add(partyBoard);
         DataCache.partyBoardMap1s.put(key2, list2);
      }

      this.logger.error("初始化partyBoardMap1=={}", DataCache.partyBoardMap1.size());
      this.logger.error("初始化partyBoardMap1s=={}", DataCache.partyBoardMap1s.size());
      List<String> resList2 = new ArrayList();
      this.readDungeonArea(resList2, "PartyBoard.tbl");

      for(int i = 0; i < resList2.size(); i += 4) {
         String areaStr = (String)resList2.get(i);
         String subtypeStr = (String)resList2.get(i + 1);
         String stageindexStr = (String)resList2.get(i + 2);
         String dungeonindexStr = (String)resList2.get(i + 3);
         int area = Integer.parseInt(areaStr);
         int subtype = Integer.parseInt(subtypeStr);
         int stageindex = Integer.parseInt(stageindexStr);
         int dungeonindex = Integer.parseInt(dungeonindexStr);
         PartyBoard partyBoard = new PartyBoard();
         partyBoard.type = 1;
         partyBoard.area = area;
         partyBoard.subtype = subtype;
         partyBoard.stageindex = stageindex;
         partyBoard.dungeonindex = dungeonindex;
         String key = area + "_" + subtype;
         String key2 = area + "_" + subtype + "_" + stageindex;
         List<PartyBoard> list1 = (List)DataCache.partyBoardMap1_backup.get(key);
         if (list1 == null) {
            list1 = new ArrayList();
         }

         list1.add(partyBoard);
         DataCache.partyBoardMap1_backup.put(key, list1);
         List<PartyBoard> list2 = (List)DataCache.partyBoardMap1s_backup.get(key2);
         if (list2 == null) {
            list2 = new ArrayList();
         }

         list2.add(partyBoard);
         DataCache.partyBoardMap1s_backup.put(key2, list2);
      }

      this.logger.error("初始化partyBoardMap1_backup=={}", DataCache.partyBoardMap1_backup.size());
      this.logger.error("初始化partyBoardMap1s_backup=={}", DataCache.partyBoardMap1s_backup.size());
   }

   public void tmpTest() {
      int dungeonIndex = 2002925001;

      for(MapSpec mapSpec : (List<MapSpec>)DataCache.DUNGEON_MAP_SPEC.get(dungeonIndex)) {
         for(Monster monster : (List<Monster>)DataCache.DMAP_MONSTER.get(mapSpec.index)) {
            this.logger.error("{}=={}=={}", new Object[]{monster.index, monster.type0, monster.type});
         }
      }

   }

   public void inintEquipSkin() {
      List<Skin> list = this.dao.query(Skin.class, Cnd.NEW());
      Map<Integer, Skin> skinItemMap = new HashMap(list.size());

      for(Skin skinItem : list) {
         skinItemMap.put(skinItem.getIndex(), skinItem);
         ItemDataPool.index2TbMap.put(skinItem.getIndex(), 2);
      }

      ItemDataPool.skinItemMap = skinItemMap;
   }

   public void initDungeon() {
      DataCache.DUNGEON_MAP.clear();

      for(Dungeon dungeon : this.dao.query(Dungeon.class, Cnd.NEW())) {
         DataCache.DUNGEON_MAP.put(dungeon.getIndex(), dungeon);
         int index = dungeon.getIndex();
         String mapSpecStr = dungeon.getMapSpec();
         if (!mapSpecStr.contains("null")) {
            List<MapSpec> mapList = new ArrayList();
            String[] mapSpecArr = mapSpecStr.split(" ");

            for(String mapStr : mapSpecArr) {
               String[] mapArr = mapStr.split("\\|");
               MapSpec mapSpec = new MapSpec();
               mapSpec.type = mapArr[0];
               mapSpec.posx = Integer.parseInt(mapArr[1]);
               mapSpec.posy = Integer.parseInt(mapArr[2]);
               mapSpec.index = Integer.parseInt(mapArr[3]);
               mapList.add(mapSpec);
            }

            DataCache.DUNGEON_MAP_SPEC.put(dungeon.getIndex(), mapList);
         }
      }

      this.logger.error("初始化DUNGEON_MAP=={}", DataCache.DUNGEON_MAP.size());
      this.logger.error("初始化DUNGEON_MAP_SPEC=={}", DataCache.DUNGEON_MAP_SPEC.size());
   }

   public void initTaskInfo() {
      DataCache.TASK_MAP.clear();

      for(TaskInfo t : this.dao.query(TaskInfo.class, Cnd.NEW())) {
         DataCache.TASK_MAP.put(t.getIndex(), t);
      }

   }

   public void initDungeonMap() {
      DataCache.DMAP_MAP.clear();

      for(DungeonMap dungeonMap : this.dao.query(DungeonMap.class, Cnd.NEW())) {
         DataCache.DMAP_MAP.put(dungeonMap.getIndex(), dungeonMap);
         int mapIndex = dungeonMap.getIndex();
         String monsterSpecStr = dungeonMap.getMonster();
         String[] monsterStrArr = monsterSpecStr.split(" ");
         List<Monster> monsterList = new ArrayList();

         for(String mStr : monsterStrArr) {
            String[] mArr = mStr.split("\\|");
            if (mArr.length == 3) {
               Monster monster = new Monster();
               monster.index = Integer.parseInt(mArr[0]);
               monster.type0 = mArr[1];
               monster.type = mArr[2];
               monster.isAI = false;
               monsterList.add(monster);
            } else if (mArr.length == 4) {
               Monster monster = new Monster();
               monster.index = Integer.parseInt(mArr[0]);
               monster.type0 = mArr[1];
               monster.dummy = mArr[2];
               monster.type = mArr[3];
               monster.isAI = false;
               monsterList.add(monster);
            }
         }

         String aiStr = dungeonMap.getAi();
         if (aiStr != null && aiStr.trim().length() != 0 && !aiStr.contains("null")) {
            String[] aiStrArr = aiStr.split(" ");

            for(String ai : aiStrArr) {
               String[] aiArr = ai.split("\\|");
               int id = Integer.parseInt(aiArr[0]);
               String s = aiArr[1];
               String type = aiArr[2];
               Monster monster = new Monster();
               monster.index = id;
               monster.type0 = s;
               monster.type = type;
               monster.isAI = true;
               monsterList.add(monster);
            }

            DataCache.DMAP_MONSTER.put(dungeonMap.getIndex(), monsterList);
         } else {
            DataCache.DMAP_MONSTER.put(dungeonMap.getIndex(), monsterList);
         }
      }

      this.logger.error("初始化DMAP_MAP=={}", DataCache.DMAP_MAP.size());
      this.logger.error("初始化DMAP_MONSTER=={}", DataCache.DMAP_MONSTER.size());
   }

   public void initPetShop() {
   }

   private void initMedicine() {
   }

   public void initMonster() {
   }

   private void initEquip() {
      List<Equip> list = this.dao.query(Equip.class, Cnd.NEW());
      EquipDataPool.initEquipData(list);
   }

   private void initExp() {
      List<RoleExp> list = this.dao.query(RoleExp.class, Cnd.NEW());
      List<PetExp> petExps = this.dao.query(PetExp.class, Cnd.NEW());
      SpringUtils.getRoleService().initUpgradeNeedExp(list);
      SpringUtils.getRoleService().initPetUpgradeNeedExp(petExps);

      for(RoleExp roleExp : list) {
         DataCache.ROLE_EXP_MAP.put(roleExp.getLevel(), roleExp.getExp());
      }

   }

   private void initAllRoles() {
      for(Role role : this.dao.query(Role.class, Cnd.NEW())) {
         DataCache.AUCTION_ROLES.put(role.getUid(), role);
      }

   }

   public void initConsumeItem() {
      List<ConsumeItem> list = this.dao.query(ConsumeItem.class, Cnd.NEW());
      Map<Integer, ConsumeItem> consumeItemMap = new HashMap(list.size());
      DataCache.CARD_RARITY_MAP.clear();

      for(ConsumeItem consumeItem : list) {
         consumeItemMap.put(consumeItem.getIndex(), consumeItem);
         ItemDataPool.index2TbMap.put(consumeItem.getIndex(), 1);
         if (consumeItem.getStackabletype() == 2) {
            List<Integer> indexList = (List)DataCache.CARD_RARITY_MAP.get(consumeItem.getRarity());
            if (indexList == null) {
               indexList = new ArrayList();
            }

            indexList.add(consumeItem.getIndex());
            DataCache.CARD_RARITY_MAP.put(consumeItem.getRarity(), indexList);
         }
      }

      ItemDataPool.consumeItemMap = consumeItemMap;
   }

   public void initShopItem() {
      List<itemShop> list = this.dao.query(itemShop.class, Cnd.NEW());
      Map<Integer, itemShop> itemShopMap = new HashMap(list.size());

      for(itemShop itemShop : list) {
         itemShopMap.put(itemShop.getGoodsid(), itemShop);
      }

      ItemDataPool.itemShopMap = itemShopMap;
   }

   public void initItemList() {
      new HashMap();
   }

   private void initSkill() {
      List<skillatdemoniclancer> skillatdemoniclancer = this.dao.query(skillatdemoniclancer.class, Cnd.NEW());
      List<skillatfighter> skillatfighter = this.dao.query(skillatfighter.class, Cnd.NEW());
      List<skillatpriest> skillatpriest = this.dao.query(skillatpriest.class, Cnd.NEW());
      List<skillatswordman> skillatswordman = this.dao.query(skillatswordman.class, Cnd.NEW());
      List<skillfighter> skillfighter = this.dao.query(skillfighter.class, Cnd.NEW());
      List<skillgunner> skillgunner = this.dao.query(skillgunner.class, Cnd.NEW());
      List<skillmage> skillmage = this.dao.query(skillmage.class, Cnd.NEW());
      List<skillpriest> skillpriest = this.dao.query(skillpriest.class, Cnd.NEW());
      List<skillswordman> skillswordman = this.dao.query(skillswordman.class, Cnd.NEW());
      DataCache.SKILLATDEMONICLANCER.clear();
      DataCache.SKILLATFIGHTER.clear();
      DataCache.SKILLATPRIEST.clear();
      DataCache.SKILLATSWORDMAN.clear();
      DataCache.SKILLFIGHTER.clear();
      DataCache.SKILLGUNNER.clear();
      DataCache.SKILLMAGE.clear();
      DataCache.SKILLPRIEST.clear();
      DataCache.SKILLSWORDMAN.clear();

      for(skillatdemoniclancer skill : skillatdemoniclancer) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLATDEMONICLANCER.put(skill.getIndex(), skillobj);
      }

      for(skillatfighter skill : skillatfighter) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLATFIGHTER.put(skill.getIndex(), skillobj);
      }

      for(skillatpriest skill : skillatpriest) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLATPRIEST.put(skill.getIndex(), skillobj);
      }

      for(skillatswordman skill : skillatswordman) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLATSWORDMAN.put(skill.getIndex(), skillobj);
      }

      for(skillfighter skill : skillfighter) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLFIGHTER.put(skill.getIndex(), skillobj);
      }

      for(skillgunner skill : skillgunner) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLGUNNER.put(skill.getIndex(), skillobj);
      }

      for(skillmage skill : skillmage) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLMAGE.put(skill.getIndex(), skillobj);
      }

      for(skillpriest skill : skillpriest) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLPRIEST.put(skill.getIndex(), skillobj);
      }

      for(skillswordman skill : skillswordman) {
         SkillObject skillobj = new SkillObject();
         skillobj.setCost(skill.getCost());
         skillobj.setGrowtype(skill.getGrowtype());
         skillobj.setType(skill.getType());
         skillobj.setLevelrange(skill.getLevelrange());
         skillobj.setMaxlevel(skill.getMaxlevel());
         skillobj.setReqlevel(skill.getReqlevel());
         skillobj.setIndex(skill.getIndex());
         DataCache.SKILLSWORDMAN.put(skill.getIndex(), skillobj);
      }

   }

   public void initServer() {
      List<Server> list = this.dao.query(Server.class, Cnd.NEW().orderBy("id", "asc"));
      DataCache.ID_SERVER.clear();

      for(Server server : list) {
         DataCache.ID_SERVER.put(server.getId(), server);
      }

   }

   public void initNpc() {
   }

   private SkillBox getSkillBox(RES_SKILL_LIST skillList, RES_SKILL_LIST skillListPk) {
      Integer sp = skillList.sp;
      Integer tp = skillList.tp;
      Integer addsp = skillList.addsp;
      Integer addtp = skillList.addtp;
      List<PT_SKILL> list = skillList.skilllist;
      Integer sp_pk = skillListPk.sp;
      Integer tp_pk = skillListPk.tp;
      Integer addsp_pk = skillListPk.addsp;
      Integer addtp_pk = skillListPk.addtp;
      List<PT_SKILL> listPk = skillListPk.skilllist;
      SkillBox skillBox = new SkillBox();
      if (sp != null) {
         skillBox.setSp(sp);
      }

      if (tp != null) {
         skillBox.setTp(tp);
      }

      if (addsp != null) {
         skillBox.setAddsp(addsp);
      }

      if (addtp != null) {
         skillBox.setAddtp(addtp);
      }

      if (sp_pk != null) {
         skillBox.setSp_pk(sp_pk);
      }

      if (tp_pk != null) {
         skillBox.setTp_pk(tp_pk);
      }

      if (addsp_pk != null) {
         skillBox.setAddsp_pk(addsp_pk);
      }

      if (addtp_pk != null) {
         skillBox.setAddtp_pk(addtp_pk);
      }

      if (list != null) {
         skillBox.setSkilllist(list);
      }

      if (listPk != null) {
         skillBox.setSkilllist_pk(listPk);
      }

      return skillBox;
   }

   private SkillslotBox getSkillslotBox(RES_SKILL_LIST skillList, RES_SKILL_LIST skillListPk) {
      SkillslotBox skillslotBox = new SkillslotBox();
      skillslotBox.setSkillslot(skillList.skillslot);
      skillslotBox.setSkillslot_pk(skillListPk.skillslot);
      return skillslotBox;
   }

   private void initMap() {
   }

   private void initProtocal() {
      ProtocalSet.loginSet.add(10000);
      ProtocalSet.loginSet.add(10001);
      ProtocalSet.loginSet.add(10008);
      ProtocalSet.loginSet.add(10006);
      ProtocalSet.loginSet.add(10011);
      ProtocalSet.loginSet.add(10018);
      ProtocalSet.loginSet.add(10017);
      ProtocalSet.loginSet.add(10002);
      ProtocalSet.ignoreSet.add(10006);
      ProtocalSet.ignoreSet.add(10125);
      ProtocalSet.ignoreSet.add(10106);
      ProtocalSet.ignoreSet.add(10103);
   }
}
