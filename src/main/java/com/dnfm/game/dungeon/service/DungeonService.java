package com.dnfm.game.dungeon.service;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.BookmarkBox;
import com.dnfm.game.bag.model.CardBox;
import com.dnfm.game.bag.model.CharFrameBox;
import com.dnfm.game.bag.model.ChatFrameBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.DamageBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.EpicPieceBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.dungeon.model.Dungeon;
import com.dnfm.game.dungeon.model.DungeonCache;
import com.dnfm.game.dungeon.model.DungeonMap;
import com.dnfm.game.dungeon.model.HellPartyInfo;
import com.dnfm.game.dungeon.model.MapSpec;
import com.dnfm.game.dungeon.model.Monster;
import com.dnfm.game.dungeon.model.RewardItem;
import com.dnfm.game.dungeon.model.RewardList;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.party.model.DungeonParty;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_DROP_OBJECT_ITEM;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EXP_INFO;
import com.dnfm.mina.protobuf.PT_GROUP_MEMBER;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEM_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_MAP_INFO;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_MONSTER_EXP;
import com.dnfm.mina.protobuf.PT_OBJECT_INFO;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.RES_STAGE_INFO;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DungeonService {
   public static final int SHANJI = 2002120370;
   public static final int BXSN = 2002120390;
   public static final int SKSZC = 2002120440;
   public static final int BINGGONG = 2002120410;
   public static final int BSFX = 2002120400;
   public static final int BWJDXLC = 2002120420;
   private static final Logger logger = LoggerFactory.getLogger(DungeonService.class);

   public static PT_CONTENTS_REWARD_INFO getRewards(Account account, Role role, String rewardItemStr) {
      PT_CONTENTS_REWARD_INFO rewards = new PT_CONTENTS_REWARD_INFO();
      rewards.items = new PT_ITEM_REWARD_INFO();
      PT_ITEMS rewardItems = new PT_ITEMS();
      String[] rewardItemArr = rewardItemStr.split(" ");

      for(String rewardItem : rewardItemArr) {
         String[] rewardItemInfo = rewardItem.split("\\|");
         String rewardType = rewardItemInfo[0];
         int index = Integer.parseInt(rewardItemInfo[1]);
         int cnt = Integer.parseInt(rewardItemInfo[2]);
         AvatarBox avatarBox = role.getAvatarBox();
         EquipBox equipBox = role.getEquipBox();
         TitleBox titleBox = role.getTitleBox();
         MaterialBox materialBox = role.getMaterialBox();
         ConsumableBox consumableBox = role.getConsumableBox();
         AccountMoneyBox accountMoneyBox = account.getMoneyBox();
         MoneyBox moneyBox = role.getMoneyBox();
         EmblemBox emblemBox = role.getEmblemBox();
         EpicPieceBox epicPieceBox = account.getEpicPieceBox();
         BookmarkBox bookmarkBox = role.getBookmarkBox();
         CardBox cardBox = role.getCardBox();
         ChatFrameBox chatFrameBox = role.getChatFrameBox();
         CharFrameBox charFrameBox = role.getCharFrameBox();
         DamageBox damageBox = role.getDamageBox();
         int type = BagService.getIndexType(index);
         switch (type) {
            case 1:
               if (rewardItems.avataritems == null) {
                  rewardItems.avataritems = new ArrayList();
               }

               PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
               pt_avatar_item.index = index;
               pt_avatar_item.guid = IdGenerator.getNextId();
               rewardItems.avataritems.add(pt_avatar_item);
               avatarBox.addAvatar(index);
               break;
            case 2:
            case 100:
               if (rewardItems.equipitems == null) {
                  rewardItems.equipitems = new ArrayList();
               }

               PT_EQUIP pt_equip = EquipDataPool.createEquip(index);
               rewardItems.equipitems.add(pt_equip);
               equipBox.addEquip(pt_equip);
               break;
            case 3:
               if (rewardItems.titleitems == null) {
                  rewardItems.titleitems = new ArrayList();
               }

               PT_EQUIP title = EquipDataPool.createTitle(index);
               rewardItems.titleitems.add(title);
               titleBox.addTitle(title);
               break;
            case 1000:
               if (rewardItems.materialitems == null) {
                  rewardItems.materialitems = new ArrayList();
               }

               PT_STACKABLE ptStackableMaterial = new PT_STACKABLE();
               ptStackableMaterial.index = index;
               ptStackableMaterial.count = cnt;
               rewardItems.materialitems.add(ptStackableMaterial);
               materialBox.updateMaterialAdd(index, cnt);
               break;
            case 1001:
               if (rewardItems.consumeitems == null) {
                  rewardItems.consumeitems = new ArrayList();
               }

               PT_STACKABLE ptStackableConsume = new PT_STACKABLE();
               ptStackableConsume.index = index;
               ptStackableConsume.count = cnt;
               rewardItems.consumeitems.add(ptStackableConsume);
               consumableBox.updateConsumeAdd(index, cnt);
               break;
            case 1003:
               logger.error("UNREACH==getRewards==type==ACCOUNT_CURRENCY==index==" + index);
               break;
            case 1004:
               if (rewards.currency == null) {
                  rewards.currency = new PT_CURRENCY_REWARD_INFO();
               }

               if (rewards.currency.currency == null) {
                  rewards.currency.currency = new ArrayList();
               }

               if (rewards.paymentcurrency == null) {
                  rewards.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
               }

               if (rewards.paymentcurrency.currency == null) {
                  rewards.paymentcurrency.currency = new ArrayList();
               }

               PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
               ptMoneyItem.index = index;
               ptMoneyItem.count = cnt;
               rewards.paymentcurrency.currency.add(ptMoneyItem);
               moneyBox.addCnt(index, cnt);
               PT_MONEY_ITEM pt_money_item = moneyBox.getMoneyItem(index);
               rewards.currency.currency.add(pt_money_item);
               break;
            case 1005:
               if (rewardItems.emblemitems == null) {
                  rewardItems.emblemitems = new ArrayList();
               }

               PT_STACKABLE ptStackableEmblem = new PT_STACKABLE();
               ptStackableEmblem.index = index;
               ptStackableEmblem.count = cnt;
               rewardItems.emblemitems.add(ptStackableEmblem);
               emblemBox.updateEmblemAdd(index, cnt);
               break;
            case 1006:
               if (rewardItems.epicpieceitems == null) {
                  rewardItems.epicpieceitems = new ArrayList();
               }

               PT_STACKABLE ptStackableEpicPiece = new PT_STACKABLE();
               ptStackableEpicPiece.index = index;
               ptStackableEpicPiece.count = cnt;
               rewardItems.epicpieceitems.add(ptStackableEpicPiece);
               epicPieceBox.updateEpicPieceAdd(index, cnt);
               break;
            case 1007:
               if (rewardItems.bookmarkitems == null) {
                  rewardItems.bookmarkitems = new ArrayList();
               }

               PT_STACKABLE ptStackableBookmark = new PT_STACKABLE();
               ptStackableBookmark.index = index;
               ptStackableBookmark.count = cnt;
               rewardItems.bookmarkitems.add(ptStackableBookmark);
               bookmarkBox.updateBookmarkAdd(index, cnt);
               break;
            case 1009:
               if (rewardItems.carditems == null) {
                  rewardItems.carditems = new ArrayList();
               }

               PT_STACKABLE ptStackableCard = new PT_STACKABLE();
               ptStackableCard.index = index;
               ptStackableCard.count = cnt;
               rewardItems.carditems.add(ptStackableCard);
               cardBox.updateCardAdd(index, cnt);
               break;
            case 10000:
               if (rewardItems.damagefontitems == null) {
                  rewardItems.damagefontitems = new ArrayList();
               }

               PT_SKIN_ITEM damageFont = new PT_SKIN_ITEM();
               damageFont.index = index;
               damageFont.guid = IdGenerator.getNextId();
               rewardItems.damagefontitems.add(damageFont);
               damageBox.addDamageFont(damageFont);
               break;
            case 10001:
               if (rewardItems.chatframeitems == null) {
                  rewardItems.chatframeitems = new ArrayList();
               }

               PT_SKIN_ITEM chatFrame = new PT_SKIN_ITEM();
               chatFrame.index = index;
               chatFrame.guid = IdGenerator.getNextId();
               rewardItems.chatframeitems.add(chatFrame);
               chatFrameBox.addChatFrame(chatFrame);
               break;
            case 10002:
               if (rewardItems.characterframeitems == null) {
                  rewardItems.characterframeitems = new ArrayList();
               }

               PT_SKIN_ITEM characterFrame = new PT_SKIN_ITEM();
               characterFrame.index = index;
               characterFrame.guid = IdGenerator.getNextId();
               rewardItems.characterframeitems.add(characterFrame);
               charFrameBox.addCharFrame(characterFrame);
               break;
            default:
               logger.error("UNREACH==getRewards==type==" + type + "==index==" + index);
         }
      }

      rewards.items.inventory = rewardItems;
      return rewards;
   }

   private static void readTxtFileint(List<String> list) {
      try {
         String encoding = "UTF-8";
         Resource resource = new ClassPathResource("Quest.tbl");
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

   public static void main(String[] args) {
      List<String> resList = new ArrayList();
      readTxtFileint(resList);
      System.out.println(resList.size());

      for(int i = 0; i < resList.size(); i += 2) {
         String lineTxt = (String)resList.get(i);
         if (i + 1 == resList.size()) {
            break;
         }

         String lineTxt2 = (String)resList.get(i + 1);
         int id = Integer.parseInt(lineTxt);
         int index = Integer.parseInt(lineTxt2);
         System.out.println(id + "," + index);
      }

   }

   public boolean isEmergencyTask(int index) {
      switch (index) {
         case 101901:
         case 109952:
         case 109959:
         case 2002111003:
         case 2002180000:
         case 2002400004:
         case 2002400212:
            return true;
         default:
            return false;
      }
   }

   public boolean isHellParty(String dungeonType) {
      return dungeonType.trim().equalsIgnoreCase("hell_bound") ? true : dungeonType.trim().equalsIgnoreCase("hell_party");
   }

   public List<RES_STAGE_INFO> getStageInfoListTower(long startId) {
      List<RES_STAGE_INFO> res_stage_infos = new ArrayList();

      for(int i = 0; i < 80; ++i) {
         RES_STAGE_INFO res_stage_info = new RES_STAGE_INFO();
         res_stage_info.map = new PT_MAP_INFO();
         res_stage_info.map.guid = startId++;
         res_stage_info.map.index = 290000 + i;
         res_stage_info.map.bossmap = true;
         if (i == 0) {
            res_stage_info.map.startmap = true;
         }

         res_stage_info.map.floor = i + 1;
         res_stage_info.mapsize = 80;
         res_stage_infos.add(res_stage_info);
      }

      return res_stage_infos;
   }

   public List<RES_STAGE_INFO> getStageInfoList(boolean isFull, int grade, int dungeonIndex, IoSession session, List<MapSpec> mapSpecs, long startId, long charguid) {
      Role role = SessionUtils.getRoleBySession(session);
      int level = role.getLevel();
      Map<Long, Integer> monster_exp = (Map)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.MONSTER_EXP, Map.class);
      List<RES_STAGE_INFO> resList = new ArrayList();
      long mapGuid = startId;
      long monsterStartId = (long)Util.randInt(10, 100);
      Dungeon dungeon = (Dungeon)DataCache.DUNGEON_MAP.get(dungeonIndex);
      String dungeonType = dungeon.getDungeonType();
      String hellMap = dungeon.getHellmap();

      for(MapSpec mapSpec : mapSpecs) {
         String mapType = mapSpec.type;
         int posx = mapSpec.posx;
         int posy = mapSpec.posy;
         int mapIndex = mapSpec.index;
         DungeonMap var10000 = (DungeonMap)DataCache.DMAP_MAP.get(mapIndex);
         List<Monster> monsters = (List)DataCache.DMAP_MONSTER.get(mapIndex);
         Integer monsterLevel = 1;
         if (grade == 0) {
            monsterLevel = (Integer)DataCache.dungeonNormalLevelMap.get(dungeonIndex);
         } else if (grade == 1) {
            monsterLevel = (Integer)DataCache.dungeonExpertLevelMap.get(dungeonIndex);
         } else if (grade == 2) {
            monsterLevel = (Integer)DataCache.dungeonMasterLevelMap.get(dungeonIndex);
         } else if (grade == 3) {
            monsterLevel = (Integer)DataCache.dungeonKingLevelMap.get(dungeonIndex);
         } else if (grade == 4) {
            monsterLevel = (Integer)DataCache.dungeonSplayerLevelMap.get(dungeonIndex);
         } else {
            logger.error("UNREACH==grade==" + grade + "==dungeonIndex==" + dungeonIndex);
         }

         RES_STAGE_INFO res_stage_info = new RES_STAGE_INFO();
         res_stage_info.map = new PT_MAP_INFO();
         res_stage_info.map.guid = mapGuid++;
         res_stage_info.map.index = mapIndex;
         if ("boss".equals(mapType)) {
            res_stage_info.map.bossmap = true;
         }

         res_stage_info.map.objects = new ArrayList();
         String mapStr = posx + "|" + posy;
         if (!Util.isEmpty(hellMap) && hellMap.trim().equalsIgnoreCase(mapStr.trim())) {
            res_stage_info.isHellMap = true;
            session.setAttribute(SessionProperties.CUR_HELL_STAGE_GUID, res_stage_info.map.guid);
         }

         for(Monster monster : monsters) {
            int monsterId = monster.index;
            String aiType = monster.type0;
            boolean isAI = monster.isAI;
            String monsterType = monster.type;
            PT_OBJECT_INFO pt_object_info = new PT_OBJECT_INFO();
            pt_object_info.guid = monsterStartId++;
            pt_object_info.level = monsterLevel;
            pt_object_info.teamtype = 1;
            pt_object_info.index = monsterId;
            if (level != 55) {
               pt_object_info.exps = new ArrayList();
            }

            PT_MONSTER_EXP pt_monster_exp = new PT_MONSTER_EXP();
            pt_monster_exp.playercount = 1;
            pt_monster_exp.charguid = charguid;
            if (!isAI) {
               if ("named".equals(monsterType)) {
                  pt_object_info.type = 2;
                  pt_monster_exp.exp = 150;
               } else if ("normal".equals(monsterType)) {
                  pt_monster_exp.exp = 50;
               } else {
                  pt_object_info.type = 3;
                  pt_monster_exp.exp = 200;
               }
            } else if ("normal".equals(monsterType)) {
               pt_object_info.type = 5;
            } else {
               pt_object_info.type = 8;
            }

            monster_exp.put(pt_object_info.guid, pt_monster_exp.exp);
            if (level != 55) {
               pt_object_info.exps.add(pt_monster_exp);
            }

            res_stage_info.map.objects.add(pt_object_info);
         }

         if (res_stage_info.map.guid == startId) {
            res_stage_info.map.startmap = true;
         }

         if (posx != 0) {
            res_stage_info.map.posx = posx;
         }

         if (posy != 0) {
            res_stage_info.map.posy = posy;
         }

         res_stage_info.map.expinfos = new ArrayList();
         PT_EXP_INFO pt_exp_info = new PT_EXP_INFO();
         pt_exp_info.charguid = charguid;
         res_stage_info.map.expinfos.add(pt_exp_info);
         res_stage_info.mapsize = mapSpecs.size();
         resList.add(res_stage_info);
      }

      if (DungeonCache.isAdvDungeon(dungeonIndex)) {
         PT_DROP_OBJECT_ITEM hellRoleBind = new PT_DROP_OBJECT_ITEM();
         hellRoleBind.charguid = charguid;
         hellRoleBind.itemindex = 2013104182;
         hellRoleBind.count = 1;
         PT_DROP_OBJECT_ITEM lyt = new PT_DROP_OBJECT_ITEM();
         lyt.charguid = charguid;
         lyt.itemindex = 2013103578;
         lyt.count = 1;
         PT_DROP_OBJECT_ITEM summerCoin = new PT_DROP_OBJECT_ITEM();
         summerCoin.charguid = charguid;
         summerCoin.itemindex = 2013106215;
         summerCoin.count = 1;
         PT_DROP_OBJECT_ITEM lether = new PT_DROP_OBJECT_ITEM();
         lether.charguid = charguid;
         lether.itemindex = 2013100822;
         lether.count = 1;
         PT_DROP_OBJECT_ITEM lytRoleBind = new PT_DROP_OBJECT_ITEM();
         lytRoleBind.charguid = charguid;
         lytRoleBind.itemindex = 2013104187;
         lytRoleBind.count = 1;
         PT_DROP_OBJECT_ITEM hell = new PT_DROP_OBJECT_ITEM();
         hell.charguid = charguid;
         hell.itemindex = 2013100709;
         hell.count = 1;
         PT_DROP_OBJECT_ITEM iron = new PT_DROP_OBJECT_ITEM();
         iron.charguid = charguid;
         iron.itemindex = 2013100822;
         iron.count = 1;
         PT_DROP_OBJECT_ITEM miracleStone = new PT_DROP_OBJECT_ITEM();
         miracleStone.charguid = charguid;
         miracleStone.itemindex = 2013105773;
         miracleStone.count = 1;
         PT_DROP_OBJECT_ITEM bonePiece = new PT_DROP_OBJECT_ITEM();
         bonePiece.charguid = charguid;
         bonePiece.itemindex = 2013100826;
         bonePiece.count = 1;
         PT_DROP_OBJECT_ITEM diamond = new PT_DROP_OBJECT_ITEM();
         diamond.charguid = charguid;
         diamond.itemindex = 2013101276;
         diamond.count = 1;
         PT_DROP_OBJECT_ITEM eleCrystal = new PT_DROP_OBJECT_ITEM();
         eleCrystal.charguid = charguid;
         eleCrystal.itemindex = 2013103333;
         eleCrystal.count = 1;
         PT_DROP_OBJECT_ITEM messi = new PT_DROP_OBJECT_ITEM();
         messi.charguid = charguid;
         messi.itemindex = 1990000103;
         messi.count = 1;
         PT_DROP_OBJECT_ITEM influence = new PT_DROP_OBJECT_ITEM();
         influence.charguid = charguid;
         influence.itemindex = 2013100841;
         influence.count = 1;
         PT_DROP_OBJECT_ITEM ring = new PT_DROP_OBJECT_ITEM();
         ring.charguid = charguid;
         ring.itemindex = 2000320005;
         ring.count = 1;
         PT_DROP_OBJECT_ITEM magic = new PT_DROP_OBJECT_ITEM();
         magic.charguid = charguid;
         magic.itemindex = 2013102100;
         magic.count = 1;
         List<RewardItem> advRewardList = null;
         if (dungeonIndex == 2002120370) {
            advRewardList = (List)DungeonCache.adventureItemMap.get(2002120370);
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            PT_OBJECT_INFO s1_m1 = (PT_OBJECT_INFO)stage1.map.objects.get(0);
            s1_m1.items = new ArrayList();
            s1_m1.items.add(lyt);
            RES_STAGE_INFO stage3 = (RES_STAGE_INFO)resList.get(2);
            PT_OBJECT_INFO s3_m1 = (PT_OBJECT_INFO)stage3.map.objects.get(0);
            s3_m1.items = new ArrayList();
            s3_m1.items.add(lyt);
            PT_OBJECT_INFO s3_m3 = (PT_OBJECT_INFO)stage3.map.objects.get(2);
            s3_m3.items = new ArrayList();
            s3_m3.items.add(lyt);
            RES_STAGE_INFO stage4 = (RES_STAGE_INFO)resList.get(3);
            PT_OBJECT_INFO s4_m3 = (PT_OBJECT_INFO)stage4.map.objects.get(2);
            s4_m3.items = new ArrayList();
            s4_m3.items.add(lyt);
            PT_OBJECT_INFO s4_m4 = (PT_OBJECT_INFO)stage4.map.objects.get(3);
            s4_m4.items = new ArrayList();
            s4_m4.items.add(hellRoleBind);
            RES_STAGE_INFO stage5 = (RES_STAGE_INFO)resList.get(4);
            PT_OBJECT_INFO s5_m4 = (PT_OBJECT_INFO)stage5.map.objects.get(3);
            s5_m4.items = new ArrayList();
            s5_m4.items.add(hellRoleBind);
            PT_OBJECT_INFO s5_m3 = (PT_OBJECT_INFO)stage5.map.objects.get(2);
            s5_m3.items = new ArrayList();
            s5_m3.items.add(lyt);
            RES_STAGE_INFO stage6 = (RES_STAGE_INFO)resList.get(5);
            PT_OBJECT_INFO s6_m1 = (PT_OBJECT_INFO)stage6.map.objects.get(0);
            s6_m1.items = new ArrayList();
            s6_m1.items.add(lyt);
            PT_OBJECT_INFO s6_m3 = (PT_OBJECT_INFO)stage6.map.objects.get(2);
            s6_m3.items = new ArrayList();
            s6_m3.items.add(lether);
            RES_STAGE_INFO stage7 = (RES_STAGE_INFO)resList.get(6);
            PT_OBJECT_INFO s7_m1 = (PT_OBJECT_INFO)stage7.map.objects.get(0);
            s7_m1.items = new ArrayList();
            s7_m1.items.add(lyt);
            RES_STAGE_INFO stage8 = (RES_STAGE_INFO)resList.get(7);
            PT_OBJECT_INFO s8_m1 = (PT_OBJECT_INFO)stage8.map.objects.get(0);
            s8_m1.items = new ArrayList();
            s8_m1.items.add(lyt);
            PT_OBJECT_INFO s8_m3 = (PT_OBJECT_INFO)stage8.map.objects.get(2);
            s8_m3.items = new ArrayList();
            s8_m3.items.add(lyt);
            PT_OBJECT_INFO s8_m4 = (PT_OBJECT_INFO)stage8.map.objects.get(3);
            s8_m4.items = new ArrayList();
            s8_m4.items.add(lether);
            RES_STAGE_INFO stage9 = (RES_STAGE_INFO)resList.get(8);
            PT_OBJECT_INFO s9_m1 = (PT_OBJECT_INFO)stage9.map.objects.get(0);
            s9_m1.items = new ArrayList();
            s9_m1.items.add(lyt);
            PT_OBJECT_INFO s9_m3 = (PT_OBJECT_INFO)stage9.map.objects.get(2);
            s9_m3.items = new ArrayList();
            s9_m3.items.add(lyt);
            RES_STAGE_INFO stage10 = (RES_STAGE_INFO)resList.get(9);
            PT_OBJECT_INFO s10_m1 = (PT_OBJECT_INFO)stage10.map.objects.get(0);
            s10_m1.items = new ArrayList();
            s10_m1.items.add(lyt);
            PT_OBJECT_INFO s10_m2 = (PT_OBJECT_INFO)stage10.map.objects.get(1);
            s10_m2.items = new ArrayList();
            s10_m2.items.add(lyt);
            RES_STAGE_INFO stage11 = (RES_STAGE_INFO)resList.get(10);
            PT_OBJECT_INFO s11_m1 = (PT_OBJECT_INFO)stage11.map.objects.get(0);
            s11_m1.items = new ArrayList();
            s11_m1.items.add(lether);
            PT_OBJECT_INFO s11_m2 = (PT_OBJECT_INFO)stage11.map.objects.get(1);
            s11_m2.items = new ArrayList();
            s11_m2.items.add(summerCoin);
            RES_STAGE_INFO stage12 = (RES_STAGE_INFO)resList.get(11);
            PT_OBJECT_INFO s12_m3 = (PT_OBJECT_INFO)stage12.map.objects.get(2);
            s12_m3.items = new ArrayList();
            s12_m3.items.add(hellRoleBind);
            PT_OBJECT_INFO s12_m1 = (PT_OBJECT_INFO)stage12.map.objects.get(0);
            s12_m1.items = new ArrayList();
            s12_m1.items.add(summerCoin);
         } else if (dungeonIndex == 2002120390) {
            advRewardList = (List)DungeonCache.adventureItemMap.get(2002120390);
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);
            PT_OBJECT_INFO s2_m4 = (PT_OBJECT_INFO)stage2.map.objects.get(3);
            s2_m4.items = new ArrayList();
            s2_m4.items.add(lytRoleBind);
            RES_STAGE_INFO stage3 = (RES_STAGE_INFO)resList.get(2);
            RES_STAGE_INFO stage4 = (RES_STAGE_INFO)resList.get(3);
            PT_OBJECT_INFO s4_m2 = (PT_OBJECT_INFO)stage4.map.objects.get(1);
            s4_m2.items = new ArrayList();
            s4_m2.items.add(hell);
            RES_STAGE_INFO stage5 = (RES_STAGE_INFO)resList.get(4);
            PT_OBJECT_INFO s5_m3 = (PT_OBJECT_INFO)stage5.map.objects.get(2);
            s5_m3.items = new ArrayList();
            s5_m3.items.add(lytRoleBind);
            RES_STAGE_INFO stage6 = (RES_STAGE_INFO)resList.get(5);
            PT_OBJECT_INFO s6_m1 = (PT_OBJECT_INFO)stage6.map.objects.get(0);
            s6_m1.items = new ArrayList();
            s6_m1.items.add(iron);
            RES_STAGE_INFO stage7 = (RES_STAGE_INFO)resList.get(6);
            PT_OBJECT_INFO s7_m2 = (PT_OBJECT_INFO)stage7.map.objects.get(1);
            s7_m2.items = new ArrayList();
            s7_m2.items.add(summerCoin);
            PT_OBJECT_INFO s7_m4 = (PT_OBJECT_INFO)stage7.map.objects.get(3);
            s7_m4.items = new ArrayList();
            s7_m4.items.add(iron);
            RES_STAGE_INFO stage8 = (RES_STAGE_INFO)resList.get(7);
            PT_OBJECT_INFO s8_m1 = (PT_OBJECT_INFO)stage8.map.objects.get(0);
            s8_m1.items = new ArrayList();
            s8_m1.items.add(hell);
            PT_OBJECT_INFO s8_m3 = (PT_OBJECT_INFO)stage8.map.objects.get(2);
            s8_m3.items = new ArrayList();
            s8_m3.items.add(hell);
            RES_STAGE_INFO stage9 = (RES_STAGE_INFO)resList.get(8);
            PT_OBJECT_INFO s9_m1 = (PT_OBJECT_INFO)stage9.map.objects.get(0);
            s9_m1.items = new ArrayList();
            s9_m1.items.add(hell);
            PT_OBJECT_INFO s9_m3 = (PT_OBJECT_INFO)stage9.map.objects.get(2);
            s9_m3.items = new ArrayList();
            s9_m3.items.add(hell);
            RES_STAGE_INFO stage10 = (RES_STAGE_INFO)resList.get(9);
            PT_OBJECT_INFO s10_m1 = (PT_OBJECT_INFO)stage10.map.objects.get(0);
            s10_m1.items = new ArrayList();
            s10_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s10_m2 = (PT_OBJECT_INFO)stage10.map.objects.get(1);
            s10_m2.items = new ArrayList();
            s10_m2.items.add(summerCoin);
            RES_STAGE_INFO stage11 = (RES_STAGE_INFO)resList.get(10);
            PT_OBJECT_INFO s11_m1 = (PT_OBJECT_INFO)stage11.map.objects.get(0);
            s11_m1.items = new ArrayList();
            s11_m1.items.add(hell);
            PT_OBJECT_INFO s11_m2 = (PT_OBJECT_INFO)stage11.map.objects.get(1);
            s11_m2.items = new ArrayList();
            s11_m2.items.add(hell);
            PT_OBJECT_INFO s11_m3 = (PT_OBJECT_INFO)stage11.map.objects.get(2);
            s11_m3.items = new ArrayList();
            s11_m3.items.add(iron);
            PT_OBJECT_INFO s11_m4 = (PT_OBJECT_INFO)stage11.map.objects.get(3);
            s11_m4.items = new ArrayList();
            s11_m4.items.add(iron);
            RES_STAGE_INFO stage12 = (RES_STAGE_INFO)resList.get(11);
            PT_OBJECT_INFO s12_m1 = (PT_OBJECT_INFO)stage12.map.objects.get(0);
            s12_m1.items = new ArrayList();
            s12_m1.items.add(hell);
            PT_OBJECT_INFO s12_m2 = (PT_OBJECT_INFO)stage12.map.objects.get(1);
            s12_m2.items = new ArrayList();
            s12_m2.items.add(hell);
            PT_OBJECT_INFO s12_m3 = (PT_OBJECT_INFO)stage12.map.objects.get(2);
            s12_m3.items = new ArrayList();
            s12_m3.items.add(hell);
         } else if (dungeonIndex == 2002120440) {
            advRewardList = (List)DungeonCache.adventureItemMap.get(2002120440);
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            int randIntVal = Util.randIndex(stage1.map.objects.size());
            PT_OBJECT_INFO s1_m1 = (PT_OBJECT_INFO)stage1.map.objects.get(randIntVal);
            s1_m1.items = new ArrayList();
            s1_m1.items.add(lytRoleBind);
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);
            randIntVal = Util.randIndex(stage2.map.objects.size());
            PT_OBJECT_INFO s2_m1 = (PT_OBJECT_INFO)stage2.map.objects.get(randIntVal);
            s2_m1.items = new ArrayList();
            s2_m1.items.add(lytRoleBind);
            RES_STAGE_INFO stage3 = (RES_STAGE_INFO)resList.get(2);
            randIntVal = Util.randIndex(stage3.map.objects.size());
            PT_OBJECT_INFO s3_m1 = (PT_OBJECT_INFO)stage3.map.objects.get(randIntVal);
            s3_m1.items = new ArrayList();
            s3_m1.items.add(hellRoleBind);
            RES_STAGE_INFO stage4 = (RES_STAGE_INFO)resList.get(3);
            randIntVal = Util.randIndex(stage4.map.objects.size());
            PT_OBJECT_INFO s4_m1 = (PT_OBJECT_INFO)stage4.map.objects.get(randIntVal);
            s4_m1.items = new ArrayList();
            s4_m1.items.add(bonePiece);
            RES_STAGE_INFO stage5 = (RES_STAGE_INFO)resList.get(4);
            randIntVal = Util.randIndex(stage5.map.objects.size());
            PT_OBJECT_INFO s5_m1 = (PT_OBJECT_INFO)stage5.map.objects.get(randIntVal);
            s5_m1.items = new ArrayList();
            s5_m1.items.add(hellRoleBind);
            RES_STAGE_INFO stage6 = (RES_STAGE_INFO)resList.get(5);
            randIntVal = Util.randIndex(stage6.map.objects.size());
            PT_OBJECT_INFO s6_m1 = (PT_OBJECT_INFO)stage6.map.objects.get(randIntVal);
            s6_m1.items = new ArrayList();
            s6_m1.items.add(bonePiece);
            RES_STAGE_INFO stage7 = (RES_STAGE_INFO)resList.get(6);
            RES_STAGE_INFO stage8 = (RES_STAGE_INFO)resList.get(7);
            randIntVal = Util.randIndex(stage8.map.objects.size());
            PT_OBJECT_INFO s8_m1 = (PT_OBJECT_INFO)stage8.map.objects.get(randIntVal);
            s8_m1.items = new ArrayList();
            s8_m1.items.add(bonePiece);
            RES_STAGE_INFO stage9 = (RES_STAGE_INFO)resList.get(8);
            randIntVal = Util.randIndex(stage9.map.objects.size());
            PT_OBJECT_INFO s9_m1 = (PT_OBJECT_INFO)stage9.map.objects.get(randIntVal);
            s9_m1.items = new ArrayList();
            s9_m1.items.add(hellRoleBind);
            RES_STAGE_INFO stage10 = (RES_STAGE_INFO)resList.get(9);
            randIntVal = Util.randIndex(stage10.map.objects.size());
            PT_OBJECT_INFO s10_m1 = (PT_OBJECT_INFO)stage10.map.objects.get(randIntVal);
            s10_m1.items = new ArrayList();
            s10_m1.items.add(lytRoleBind);
            RES_STAGE_INFO stage11 = (RES_STAGE_INFO)resList.get(10);
            randIntVal = Util.randIndex(stage11.map.objects.size());
            PT_OBJECT_INFO s11_m1 = (PT_OBJECT_INFO)stage11.map.objects.get(randIntVal);
            s11_m1.items = new ArrayList();
            s11_m1.items.add(bonePiece);
            randIntVal = Util.randIndex(100);
            if (randIntVal > 20) {
               RES_STAGE_INFO stage12 = (RES_STAGE_INFO)resList.get(11);
               PT_OBJECT_INFO s12_m4 = (PT_OBJECT_INFO)stage12.map.objects.get(3);
               s12_m4.items = new ArrayList();
               s12_m4.items.add(miracleStone);
            }
         } else if (dungeonIndex == 2002120410) {
            advRewardList = (List)DungeonCache.adventureItemMap.get(2002120410);
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            PT_OBJECT_INFO s1_m1 = (PT_OBJECT_INFO)stage1.map.objects.get(0);
            s1_m1.items = new ArrayList();
            s1_m1.items.add(iron);
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);
            PT_OBJECT_INFO s2_m1 = (PT_OBJECT_INFO)stage2.map.objects.get(0);
            s2_m1.items = new ArrayList();
            s2_m1.items.add(iron);
            PT_OBJECT_INFO s2_m2 = (PT_OBJECT_INFO)stage2.map.objects.get(1);
            s2_m2.items = new ArrayList();
            s2_m2.items.add(hellRoleBind);
            PT_OBJECT_INFO s2_m3 = (PT_OBJECT_INFO)stage2.map.objects.get(2);
            s2_m3.items = new ArrayList();
            s2_m3.items.add(lytRoleBind);
            RES_STAGE_INFO stage3 = (RES_STAGE_INFO)resList.get(2);
            PT_OBJECT_INFO s3_m1 = (PT_OBJECT_INFO)stage3.map.objects.get(0);
            s3_m1.items = new ArrayList();
            s3_m1.items.add(iron);
            PT_OBJECT_INFO s3_m2 = (PT_OBJECT_INFO)stage3.map.objects.get(1);
            s3_m2.items = new ArrayList();
            s3_m2.items.add(hellRoleBind);
            PT_OBJECT_INFO s3_m3 = (PT_OBJECT_INFO)stage3.map.objects.get(2);
            s3_m3.items = new ArrayList();
            s3_m3.items.add(lytRoleBind);
            RES_STAGE_INFO stage4 = (RES_STAGE_INFO)resList.get(3);
            PT_OBJECT_INFO s4_m1 = (PT_OBJECT_INFO)stage4.map.objects.get(0);
            s4_m1.items = new ArrayList();
            s4_m1.items.add(iron);
            PT_OBJECT_INFO s4_m2 = (PT_OBJECT_INFO)stage4.map.objects.get(1);
            s4_m2.items = new ArrayList();
            s4_m2.items.add(hellRoleBind);
            PT_OBJECT_INFO s4_m3 = (PT_OBJECT_INFO)stage4.map.objects.get(2);
            s4_m3.items = new ArrayList();
            s4_m3.items.add(lytRoleBind);
            RES_STAGE_INFO stage5 = (RES_STAGE_INFO)resList.get(4);
            PT_OBJECT_INFO s5_m1 = (PT_OBJECT_INFO)stage5.map.objects.get(0);
            s5_m1.items = new ArrayList();
            s5_m1.items.add(eleCrystal);
            PT_OBJECT_INFO s5_m2 = (PT_OBJECT_INFO)stage5.map.objects.get(1);
            s5_m2.items = new ArrayList();
            s5_m2.items.add(bonePiece);
            PT_OBJECT_INFO s5_m3 = (PT_OBJECT_INFO)stage5.map.objects.get(2);
            s5_m3.items = new ArrayList();
            s5_m3.items.add(lether);
            RES_STAGE_INFO stage6 = (RES_STAGE_INFO)resList.get(5);
            PT_OBJECT_INFO s6_m1 = (PT_OBJECT_INFO)stage6.map.objects.get(0);
            s6_m1.items = new ArrayList();
            s6_m1.items.add(eleCrystal);
            PT_OBJECT_INFO s6_m2 = (PT_OBJECT_INFO)stage6.map.objects.get(1);
            s6_m2.items = new ArrayList();
            s6_m2.items.add(bonePiece);
            PT_OBJECT_INFO s6_m3 = (PT_OBJECT_INFO)stage6.map.objects.get(2);
            s6_m3.items = new ArrayList();
            s6_m3.items.add(lether);
            RES_STAGE_INFO stage7 = (RES_STAGE_INFO)resList.get(6);
            PT_OBJECT_INFO s7_m1 = (PT_OBJECT_INFO)stage7.map.objects.get(0);
            s7_m1.items = new ArrayList();
            s7_m1.items.add(eleCrystal);
            PT_OBJECT_INFO s7_m2 = (PT_OBJECT_INFO)stage7.map.objects.get(1);
            s7_m2.items = new ArrayList();
            s7_m2.items.add(bonePiece);
            PT_OBJECT_INFO s7_m3 = (PT_OBJECT_INFO)stage7.map.objects.get(2);
            s7_m3.items = new ArrayList();
            s7_m3.items.add(lether);
            RES_STAGE_INFO stage8 = (RES_STAGE_INFO)resList.get(7);
            PT_OBJECT_INFO s8_m1 = (PT_OBJECT_INFO)stage8.map.objects.get(0);
            s8_m1.items = new ArrayList();
            s8_m1.items.add(eleCrystal);
            PT_OBJECT_INFO s8_m2 = (PT_OBJECT_INFO)stage8.map.objects.get(1);
            s8_m2.items = new ArrayList();
            s8_m2.items.add(bonePiece);
            PT_OBJECT_INFO s8_m3 = (PT_OBJECT_INFO)stage8.map.objects.get(2);
            s8_m3.items = new ArrayList();
            s8_m3.items.add(lether);
            RES_STAGE_INFO stage9 = (RES_STAGE_INFO)resList.get(8);
            PT_OBJECT_INFO s9_m1 = (PT_OBJECT_INFO)stage9.map.objects.get(0);
            s9_m1.items = new ArrayList();
            s9_m1.items.add(influence);
            PT_OBJECT_INFO s9_m2 = (PT_OBJECT_INFO)stage9.map.objects.get(1);
            s9_m2.items = new ArrayList();
            s9_m2.items.add(diamond);
            PT_OBJECT_INFO s9_m3 = (PT_OBJECT_INFO)stage9.map.objects.get(2);
            s9_m3.items = new ArrayList();
            s9_m3.items.add(messi);
            RES_STAGE_INFO stage10 = (RES_STAGE_INFO)resList.get(9);
            PT_OBJECT_INFO s10_m1 = (PT_OBJECT_INFO)stage10.map.objects.get(0);
            s10_m1.items = new ArrayList();
            s10_m1.items.add(influence);
            PT_OBJECT_INFO s10_m2 = (PT_OBJECT_INFO)stage10.map.objects.get(1);
            s10_m2.items = new ArrayList();
            s10_m2.items.add(diamond);
            PT_OBJECT_INFO s10_m3 = (PT_OBJECT_INFO)stage10.map.objects.get(2);
            s10_m3.items = new ArrayList();
            s10_m3.items.add(messi);
            RES_STAGE_INFO stage11 = (RES_STAGE_INFO)resList.get(10);
            PT_OBJECT_INFO s11_m1 = (PT_OBJECT_INFO)stage11.map.objects.get(0);
            s11_m1.items = new ArrayList();
            s11_m1.items.add(influence);
            PT_OBJECT_INFO s11_m2 = (PT_OBJECT_INFO)stage11.map.objects.get(1);
            s11_m2.items = new ArrayList();
            s11_m2.items.add(diamond);
            PT_OBJECT_INFO s11_m3 = (PT_OBJECT_INFO)stage11.map.objects.get(2);
            s11_m3.items = new ArrayList();
            s11_m3.items.add(messi);
            RES_STAGE_INFO stage12 = (RES_STAGE_INFO)resList.get(11);
            PT_OBJECT_INFO s12_m1 = (PT_OBJECT_INFO)stage12.map.objects.get(0);
            s12_m1.items = new ArrayList();
            s12_m1.items.add(influence);
            s12_m1.items.add(diamond);
            s12_m1.items.add(messi);
         } else if (dungeonIndex == 2002120400) {
            advRewardList = (List)DungeonCache.adventureItemMap.get(2002120400);
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            PT_OBJECT_INFO s1_m1 = (PT_OBJECT_INFO)stage1.map.objects.get(2);
            s1_m1.items = new ArrayList();
            s1_m1.items.add(bonePiece);
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);
            PT_OBJECT_INFO s2_m1 = (PT_OBJECT_INFO)stage2.map.objects.get(2);
            s2_m1.items = new ArrayList();
            s2_m1.items.add(lether);
            RES_STAGE_INFO stage3 = (RES_STAGE_INFO)resList.get(2);
            PT_OBJECT_INFO s3_m1 = (PT_OBJECT_INFO)stage3.map.objects.get(2);
            s3_m1.items = new ArrayList();
            s3_m1.items.add(iron);
            RES_STAGE_INFO stage4 = (RES_STAGE_INFO)resList.get(3);
            PT_OBJECT_INFO s4_m1 = (PT_OBJECT_INFO)stage4.map.objects.get(0);
            s4_m1.items = new ArrayList();
            s4_m1.items.add(bonePiece);
            PT_OBJECT_INFO s4_m2 = (PT_OBJECT_INFO)stage4.map.objects.get(1);
            s4_m2.items = new ArrayList();
            s4_m2.items.add(lether);
            PT_OBJECT_INFO s4_m3 = (PT_OBJECT_INFO)stage4.map.objects.get(2);
            s4_m3.items = new ArrayList();
            s4_m3.items.add(iron);
            RES_STAGE_INFO stage5 = (RES_STAGE_INFO)resList.get(4);
            PT_OBJECT_INFO s5_m1 = (PT_OBJECT_INFO)stage5.map.objects.get(0);
            s5_m1.items = new ArrayList();
            s5_m1.items.add(bonePiece);
            PT_OBJECT_INFO s5_m2 = (PT_OBJECT_INFO)stage5.map.objects.get(1);
            s5_m2.items = new ArrayList();
            s5_m2.items.add(lether);
            PT_OBJECT_INFO s5_m3 = (PT_OBJECT_INFO)stage5.map.objects.get(2);
            s5_m3.items = new ArrayList();
            s5_m3.items.add(iron);
            RES_STAGE_INFO stage6 = (RES_STAGE_INFO)resList.get(5);
            PT_OBJECT_INFO s6_m1 = (PT_OBJECT_INFO)stage6.map.objects.get(0);
            s6_m1.items = new ArrayList();
            s6_m1.items.add(messi);
            PT_OBJECT_INFO s6_m2 = (PT_OBJECT_INFO)stage6.map.objects.get(1);
            s6_m2.items = new ArrayList();
            s6_m2.items.add(eleCrystal);
            PT_OBJECT_INFO s6_m3 = (PT_OBJECT_INFO)stage6.map.objects.get(2);
            s6_m3.items = new ArrayList();
            s6_m3.items.add(diamond);
            RES_STAGE_INFO stage7 = (RES_STAGE_INFO)resList.get(6);
            PT_OBJECT_INFO s7_m1 = (PT_OBJECT_INFO)stage7.map.objects.get(0);
            s7_m1.items = new ArrayList();
            s7_m1.items.add(messi);
            PT_OBJECT_INFO s7_m2 = (PT_OBJECT_INFO)stage7.map.objects.get(1);
            s7_m2.items = new ArrayList();
            s7_m2.items.add(eleCrystal);
            PT_OBJECT_INFO s7_m3 = (PT_OBJECT_INFO)stage7.map.objects.get(2);
            s7_m3.items = new ArrayList();
            s7_m3.items.add(diamond);
            RES_STAGE_INFO stage8 = (RES_STAGE_INFO)resList.get(7);
            PT_OBJECT_INFO s8_m1 = (PT_OBJECT_INFO)stage8.map.objects.get(0);
            s8_m1.items = new ArrayList();
            s8_m1.items.add(messi);
            PT_OBJECT_INFO s8_m2 = (PT_OBJECT_INFO)stage8.map.objects.get(1);
            s8_m2.items = new ArrayList();
            s8_m2.items.add(eleCrystal);
            PT_OBJECT_INFO s8_m3 = (PT_OBJECT_INFO)stage8.map.objects.get(2);
            s8_m3.items = new ArrayList();
            s8_m3.items.add(diamond);
            RES_STAGE_INFO stage9 = (RES_STAGE_INFO)resList.get(8);
            PT_OBJECT_INFO s9_m1 = (PT_OBJECT_INFO)stage9.map.objects.get(0);
            s9_m1.items = new ArrayList();
            s9_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s9_m2 = (PT_OBJECT_INFO)stage9.map.objects.get(1);
            s9_m2.items = new ArrayList();
            s9_m2.items.add(hellRoleBind);
            PT_OBJECT_INFO s9_m3 = (PT_OBJECT_INFO)stage9.map.objects.get(2);
            s9_m3.items = new ArrayList();
            s9_m3.items.add(influence);
            RES_STAGE_INFO stage10 = (RES_STAGE_INFO)resList.get(9);
            PT_OBJECT_INFO s10_m1 = (PT_OBJECT_INFO)stage10.map.objects.get(0);
            s10_m1.items = new ArrayList();
            s10_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s10_m2 = (PT_OBJECT_INFO)stage10.map.objects.get(1);
            s10_m2.items = new ArrayList();
            s10_m2.items.add(hellRoleBind);
            PT_OBJECT_INFO s10_m3 = (PT_OBJECT_INFO)stage10.map.objects.get(2);
            s10_m3.items = new ArrayList();
            s10_m3.items.add(influence);
            RES_STAGE_INFO stage11 = (RES_STAGE_INFO)resList.get(10);
            PT_OBJECT_INFO s11_m1 = (PT_OBJECT_INFO)stage11.map.objects.get(0);
            s11_m1.items = new ArrayList();
            s11_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s11_m2 = (PT_OBJECT_INFO)stage11.map.objects.get(1);
            s11_m2.items = new ArrayList();
            s11_m2.items.add(hellRoleBind);
            PT_OBJECT_INFO s11_m3 = (PT_OBJECT_INFO)stage11.map.objects.get(2);
            s11_m3.items = new ArrayList();
            s11_m3.items.add(influence);
            int rand = Util.randIndex(1000);
            if (rand < 6) {
               RES_STAGE_INFO stage12 = (RES_STAGE_INFO)resList.get(11);
               PT_OBJECT_INFO s12_m2 = (PT_OBJECT_INFO)stage12.map.objects.get(1);
               s12_m2.items = new ArrayList();
               s12_m2.items.add(ring);
            }
         } else {
            if (dungeonIndex != 2002120420) {
               logger.error("UNREACH==adventure map: " + dungeonIndex);
               return null;
            }

            advRewardList = (List)DungeonCache.adventureItemMap.get(2002120420);
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            PT_OBJECT_INFO s1_m1 = (PT_OBJECT_INFO)stage1.map.objects.get(0);
            s1_m1.items = new ArrayList();
            s1_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s1_m2 = (PT_OBJECT_INFO)stage1.map.objects.get(1);
            s1_m2.items = new ArrayList();
            s1_m2.items.add(hellRoleBind);
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);
            PT_OBJECT_INFO s2_m1 = (PT_OBJECT_INFO)stage2.map.objects.get(0);
            s2_m1.items = new ArrayList();
            s2_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s2_m2 = (PT_OBJECT_INFO)stage2.map.objects.get(1);
            s2_m2.items = new ArrayList();
            s2_m2.items.add(hellRoleBind);
            RES_STAGE_INFO stage3 = (RES_STAGE_INFO)resList.get(2);
            PT_OBJECT_INFO s3_m1 = (PT_OBJECT_INFO)stage3.map.objects.get(0);
            s3_m1.items = new ArrayList();
            s3_m1.items.add(lytRoleBind);
            PT_OBJECT_INFO s3_m2 = (PT_OBJECT_INFO)stage3.map.objects.get(1);
            s3_m2.items = new ArrayList();
            s3_m2.items.add(hellRoleBind);
            RES_STAGE_INFO stage4 = (RES_STAGE_INFO)resList.get(3);
            PT_OBJECT_INFO s4_m1 = (PT_OBJECT_INFO)stage4.map.objects.get(0);
            s4_m1.items = new ArrayList();
            s4_m1.items.add(hell);
            PT_OBJECT_INFO s4_m2 = (PT_OBJECT_INFO)stage4.map.objects.get(1);
            s4_m2.items = new ArrayList();
            s4_m2.items.add(lyt);
            RES_STAGE_INFO stage5 = (RES_STAGE_INFO)resList.get(4);
            PT_OBJECT_INFO s5_m1 = (PT_OBJECT_INFO)stage5.map.objects.get(0);
            s5_m1.items = new ArrayList();
            s5_m1.items.add(hell);
            PT_OBJECT_INFO s5_m2 = (PT_OBJECT_INFO)stage5.map.objects.get(1);
            s5_m2.items = new ArrayList();
            s5_m2.items.add(lyt);
            RES_STAGE_INFO stage6 = (RES_STAGE_INFO)resList.get(5);
            PT_OBJECT_INFO s6_m1 = (PT_OBJECT_INFO)stage6.map.objects.get(0);
            s6_m1.items = new ArrayList();
            s6_m1.items.add(hell);
            PT_OBJECT_INFO s6_m2 = (PT_OBJECT_INFO)stage6.map.objects.get(1);
            s6_m2.items = new ArrayList();
            s6_m2.items.add(lyt);
            RES_STAGE_INFO stage7 = (RES_STAGE_INFO)resList.get(6);
            PT_OBJECT_INFO s7_m1 = (PT_OBJECT_INFO)stage7.map.objects.get(0);
            s7_m1.items = new ArrayList();
            s7_m1.items.add(magic);
            RES_STAGE_INFO stage8 = (RES_STAGE_INFO)resList.get(7);
            PT_OBJECT_INFO s8_m1 = (PT_OBJECT_INFO)stage8.map.objects.get(0);
            s8_m1.items = new ArrayList();
            s8_m1.items.add(magic);
            RES_STAGE_INFO stage9 = (RES_STAGE_INFO)resList.get(8);
            PT_OBJECT_INFO s9_m1 = (PT_OBJECT_INFO)stage9.map.objects.get(0);
            s9_m1.items = new ArrayList();
            s9_m1.items.add(magic);
            s9_m1.items.add(magic);
            RES_STAGE_INFO stage10 = (RES_STAGE_INFO)resList.get(9);
            PT_OBJECT_INFO s10_m1 = (PT_OBJECT_INFO)stage10.map.objects.get(0);
            s10_m1.items = new ArrayList();
            s10_m1.items.add(magic);
            s10_m1.items.add(magic);
            RES_STAGE_INFO stage11 = (RES_STAGE_INFO)resList.get(10);
            PT_OBJECT_INFO s11_m1 = (PT_OBJECT_INFO)stage11.map.objects.get(0);
            s11_m1.items = new ArrayList();
            s11_m1.items.add(magic);
            s11_m1.items.add(magic);
            RES_STAGE_INFO stage12 = (RES_STAGE_INFO)resList.get(11);
            PT_OBJECT_INFO s12_m1 = (PT_OBJECT_INFO)stage12.map.objects.get(0);
            s12_m1.items = new ArrayList();
            s12_m1.items.add(magic);
            s12_m1.items.add(magic);
         }
      }

      if (this.isEmergencyTask(dungeonIndex)) {
         PT_DROP_OBJECT_ITEM lytRoleBind = new PT_DROP_OBJECT_ITEM();
         lytRoleBind.charguid = charguid;
         lytRoleBind.itemindex = 2013104187;
         lytRoleBind.count = 1;
         PT_DROP_OBJECT_ITEM summerCoin = new PT_DROP_OBJECT_ITEM();
         summerCoin.charguid = charguid;
         summerCoin.itemindex = 2013106215;
         summerCoin.count = 1;
         PT_DROP_OBJECT_ITEM magic = new PT_DROP_OBJECT_ITEM();
         magic.charguid = charguid;
         magic.itemindex = 2013102100;
         magic.count = 1;
         PT_DROP_OBJECT_ITEM hell = new PT_DROP_OBJECT_ITEM();
         hell.charguid = charguid;
         hell.itemindex = 2013100709;
         hell.count = 1;
         PT_DROP_OBJECT_ITEM hellRoleBind = new PT_DROP_OBJECT_ITEM();
         hellRoleBind.charguid = charguid;
         hellRoleBind.itemindex = 2013104182;
         hellRoleBind.count = 1;
         PT_DROP_OBJECT_ITEM hellAccBind = new PT_DROP_OBJECT_ITEM();
         hellAccBind.charguid = charguid;
         hellAccBind.itemindex = 2013104499;
         hellAccBind.count = 1;
         if (dungeonIndex == 2002400212) {
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);

            for(int i = 5; i < 15; ++i) {
               PT_OBJECT_INFO s2_m6 = (PT_OBJECT_INFO)stage2.map.objects.get(i);
               s2_m6.items = new ArrayList();
               s2_m6.items.add(summerCoin);
            }
         } else if (dungeonIndex == 2002111003) {
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            PT_OBJECT_INFO s1_m1 = (PT_OBJECT_INFO)stage1.map.objects.get(0);
            s1_m1.items = new ArrayList();
            PT_DROP_OBJECT_ITEM coin = new PT_DROP_OBJECT_ITEM();
            coin.charguid = charguid;
            coin.count = 500000;
            s1_m1.items.add(coin);
         } else if (dungeonIndex == 109959) {
            RES_STAGE_INFO stage = (RES_STAGE_INFO)resList.get(resList.size() - 1);
            PT_OBJECT_INFO m1 = (PT_OBJECT_INFO)stage.map.objects.get(0);
            m1.items = new ArrayList();
            lytRoleBind.count = 16;
            m1.items.add(lytRoleBind);
         } else if (dungeonIndex == 109952) {
            RES_STAGE_INFO stage = (RES_STAGE_INFO)resList.get(resList.size() - 1);
            PT_OBJECT_INFO m1 = (PT_OBJECT_INFO)stage.map.objects.get(stage.map.objects.size() - 1);
            m1.items = new ArrayList();
            magic.count = 16;
            m1.items.add(magic);
         } else if (dungeonIndex == 2002400004) {
            RES_STAGE_INFO stage = (RES_STAGE_INFO)resList.get(resList.size() - 1);
            PT_OBJECT_INFO m1 = (PT_OBJECT_INFO)stage.map.objects.get(stage.map.objects.size() - 1);
            m1.items = new ArrayList();
            hellRoleBind.count = 10;
            m1.items.add(magic);
         } else if (dungeonIndex == 101901) {
            RES_STAGE_INFO stage1 = (RES_STAGE_INFO)resList.get(0);
            PT_OBJECT_INFO m1 = (PT_OBJECT_INFO)stage1.map.objects.get(0);
            m1.items = new ArrayList();
            hell.count = 15;
            m1.items.add(hell);
            RES_STAGE_INFO stage2 = (RES_STAGE_INFO)resList.get(1);
            PT_OBJECT_INFO m2 = (PT_OBJECT_INFO)stage2.map.objects.get(0);
            m2.items = new ArrayList();
            hellAccBind.count = 22;
            m2.items.add(hellAccBind);
         }
      }

      if (!Util.isEmpty(dungeonType) && this.isHellParty(dungeonType)) {
         List<RewardItem> curHellSelList = (List)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CUR_HELL_SEL_LIST, List.class);
         if (curHellSelList == null) {
            logger.error("UNREACH==curHellSelList is null");
            return null;
         }

         HellPartyInfo hellPartyInfo = (HellPartyInfo)DungeonCache.hellPartyInfoMap.get(dungeonIndex);
         if (hellPartyInfo != null) {
            RES_STAGE_INFO hellStage = null;

            for(RES_STAGE_INFO res_stage_info : resList) {
               if (res_stage_info.isHellMap) {
                  hellStage = res_stage_info;
                  break;
               }
            }

            if (hellStage == null) {
               logger.error("UNREACH====hellStage is null, dungeonIndex={}", dungeonIndex);
               return null;
            }

            List<Integer> monsters1 = hellPartyInfo.hellMonsters;
            int randomIndex = Util.randIndex(4);
            if (randomIndex < 3) {
               PT_OBJECT_INFO m1 = new PT_OBJECT_INFO();
               m1.guid = monsterStartId++;
               m1.level = 29;
               m1.teamtype = 1;
               m1.index = (Integer)monsters1.get(randomIndex);
               hellStage.map.objects.add(m1);
            } else {
               for(int i = 0; i < 4; ++i) {
                  PT_OBJECT_INFO m1 = new PT_OBJECT_INFO();
                  m1.guid = monsterStartId++;
                  m1.level = 29;
                  m1.teamtype = 1;
                  m1.index = (Integer)monsters1.get(i);
                  hellStage.map.objects.add(m1);
               }
            }

            List<Integer> monsters2 = hellPartyInfo.hellMonsters2;
            int randomIndex2 = Util.randIndex(4);
            if (randomIndex2 == 0) {
               for(int i = 0; i < 2; ++i) {
                  PT_OBJECT_INFO m1 = new PT_OBJECT_INFO();
                  m1.guid = monsterStartId++;
                  m1.level = 55;
                  m1.teamtype = 1;
                  m1.index = (Integer)monsters2.get(i);
                  hellStage.map.objects.add(m1);
               }
            } else if (randomIndex2 == 1) {
               PT_OBJECT_INFO m1 = new PT_OBJECT_INFO();
               m1.guid = monsterStartId++;
               m1.level = 55;
               m1.teamtype = 1;
               m1.index = (Integer)monsters2.get(randomIndex2);
               hellStage.map.objects.add(m1);
            } else if (randomIndex2 == 2) {
               for(int i = 0; i < 3; ++i) {
                  PT_OBJECT_INFO m1 = new PT_OBJECT_INFO();
                  m1.guid = monsterStartId++;
                  m1.level = 55;
                  m1.teamtype = 1;
                  m1.index = (Integer)monsters2.get(i);
                  hellStage.map.objects.add(m1);
               }
            } else {
               for(int i = 0; i < 6; ++i) {
                  PT_OBJECT_INFO m1 = new PT_OBJECT_INFO();
                  m1.guid = monsterStartId++;
                  m1.level = 55;
                  m1.teamtype = 1;
                  m1.index = (Integer)monsters2.get(i);
                  hellStage.map.objects.add(m1);
               }
            }

            int dropObjIndex = hellPartyInfo.dropObjectIndex;
            PT_OBJECT_INFO pt_object_info1 = new PT_OBJECT_INFO();
            pt_object_info1.guid = monsterStartId++;
            pt_object_info1.level = 1;
            pt_object_info1.teamtype = 1;
            pt_object_info1.type = 9;
            pt_object_info1.index = dropObjIndex;
            pt_object_info1.items = new ArrayList();
            RewardList rewardList = (RewardList)DungeonCache.hellItemMap.get(1);
            if (rewardList == null) {
               logger.error("UNREACH====rewardList is null, dungeonIndex={}", dungeonIndex);
               return null;
            }

            List<RewardItem> fullMustList = rewardList.fullMustList;
            List<RewardItem> rareList = rewardList.rareList;
            List<RewardItem> everyMustList = rewardList.everyMustList;
            List<RewardItem> selList = rewardList.selList;
            if (isFull) {
               logger.error("ReqStageList==isFull==true");
               int randIndex = Util.randIndex(fullMustList.size());
               RewardItem rewardItem = (RewardItem)fullMustList.get(randIndex);
               int index = rewardItem.index;
               PT_DROP_OBJECT_ITEM item = new PT_DROP_OBJECT_ITEM();
               item.charguid = charguid;
               item.itemindex = index;
               item.count = 1;
               pt_object_info1.items.add(item);
               curHellSelList.add(rewardItem);
            } else {
               int rand = Util.randIndex(100);
               if (rand < 5) {
                  int randIndex = Util.randIndex(fullMustList.size());
                  RewardItem rewardItem = (RewardItem)fullMustList.get(randIndex);
                  int index = rewardItem.index;
                  PT_DROP_OBJECT_ITEM item = new PT_DROP_OBJECT_ITEM();
                  item.charguid = charguid;
                  item.itemindex = index;
                  item.count = 1;
                  pt_object_info1.items.add(item);
                  curHellSelList.add(rewardItem);
               }
            }

            for(RewardItem rewardItem : rareList) {
               int index = rewardItem.index;
               int rand = Util.randIndex(100);
               if ((double)rand < rewardItem.minRate) {
                  PT_DROP_OBJECT_ITEM item = new PT_DROP_OBJECT_ITEM();
                  item.charguid = charguid;
                  item.itemindex = index;
                  item.count = 1;
                  pt_object_info1.items.add(item);
               }
            }

            for(RewardItem rewardItem : everyMustList) {
               int index = rewardItem.index;
               int cnt = Util.randInt(3, 8);
               PT_DROP_OBJECT_ITEM item = new PT_DROP_OBJECT_ITEM();
               item.charguid = charguid;
               item.itemindex = index;
               item.count = cnt;
               pt_object_info1.items.add(item);
            }

            for(RewardItem rewardItem : selList) {
               int cnt = Util.randInt(1, 4);
               int index = rewardItem.index;
               int rand = Util.randIndex(100);
               if (rand < 50) {
                  PT_DROP_OBJECT_ITEM item = new PT_DROP_OBJECT_ITEM();
                  item.charguid = charguid;
                  item.itemindex = index;
                  item.count = cnt;
                  pt_object_info1.items.add(item);
                  rewardItem.finalCnt = cnt;
                  curHellSelList.add(rewardItem);
               }
            }

            session.setAttribute(SessionProperties.CUR_HELL_SEL_LIST, curHellSelList);
            hellStage.map.objects.add(pt_object_info1);
            int hellObjIndex = hellPartyInfo.hellObjectIndex;
            PT_OBJECT_INFO pt_object_info2 = new PT_OBJECT_INFO();
            pt_object_info2.guid = monsterStartId++;
            pt_object_info2.level = 1;
            pt_object_info2.teamtype = 1;
            pt_object_info2.type = 9;
            pt_object_info2.index = hellObjIndex;
            hellStage.map.objects.add(pt_object_info2);
         }
      }

      session.setAttribute(SessionProperties.MONSTER_EXP, monster_exp);
      return resList;
   }

   public List<RES_STAGE_INFO> getMultiStageInfoList(int dungeonIndex, IoSession session, List<MapSpec> mapSpecs, long startId, long charguid) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      List<RES_STAGE_INFO> resList = new ArrayList();
      long mapGuid = startId;
      long monsterStartId = (long)Util.randInt(10, 100);

      for(MapSpec mapSpec : mapSpecs) {
         String mapType = mapSpec.type;
         int posx = mapSpec.posx;
         int posy = mapSpec.posy;
         int mapIndex = mapSpec.index;
         logger.error("mapType={}, posx={}, posy={}, mapIndex={}", new Object[]{mapType, posx, posy, mapIndex});
         DungeonMap var10000 = (DungeonMap)DataCache.DMAP_MAP.get(mapIndex);
         List<Monster> monsters = (List)DataCache.DMAP_MONSTER.get(mapIndex);
         Integer monsterLevel = (Integer)DataCache.dungeonNormalLevelMap.get(dungeonIndex);
         if (monsterLevel == null) {
            logger.error("UNREACH====monsterLevel is null, dungeonIndex={}", dungeonIndex);
            monsterLevel = 1;
         }

         RES_STAGE_INFO res_stage_info = new RES_STAGE_INFO();
         res_stage_info.map = new PT_MAP_INFO();
         res_stage_info.map.guid = mapGuid++;
         res_stage_info.map.index = mapIndex;
         if ("boss".equals(mapType)) {
            res_stage_info.map.bossmap = true;
         }

         res_stage_info.map.objects = new ArrayList();

         for(Monster monster : monsters) {
            int monsterId = monster.index;
            String aiType = monster.type0;
            String monsterType = monster.type;
            PT_OBJECT_INFO pt_object_info = new PT_OBJECT_INFO();
            pt_object_info.guid = monsterStartId++;
            pt_object_info.level = monsterLevel;
            pt_object_info.teamtype = 1;
            pt_object_info.index = monsterId;
            res_stage_info.map.objects.add(pt_object_info);
         }

         res_stage_info.map.startmap = true;
         res_stage_info.map.posx = posx;
         res_stage_info.map.posy = posy;
         res_stage_info.map.expinfos = new ArrayList();

         for(int i = 0; i < dungeonParty.users.size(); ++i) {
            PT_GROUP_MEMBER member = (PT_GROUP_MEMBER)dungeonParty.users.get(i);
            PT_EXP_INFO pt_exp_info = new PT_EXP_INFO();
            pt_exp_info.charguid = member.charguid;
            res_stage_info.map.expinfos.add(pt_exp_info);
         }

         res_stage_info.mapsize = mapSpecs.size();
         resList.add(res_stage_info);
      }

      return resList;
   }
}
