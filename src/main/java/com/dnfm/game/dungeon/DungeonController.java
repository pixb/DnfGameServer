package com.dnfm.game.dungeon;

import com.alibaba.fastjson.JSONArray;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.adventure.model.AdventureUnionInfo;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.BookmarkBox;
import com.dnfm.game.bag.model.CardBox;
import com.dnfm.game.bag.model.CharFrameBox;
import com.dnfm.game.bag.model.ChatFrameBox;
import com.dnfm.game.bag.model.ClearDungeonBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.DamageBox;
import com.dnfm.game.bag.model.DungeonTicketsBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.EpicPieceBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.QuestInfoBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.bag.model.TowerInfoBox;
import com.dnfm.game.bag.model.TutoBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.dungeon.model.Dungeon;
import com.dnfm.game.dungeon.model.DungeonCache;
import com.dnfm.game.dungeon.model.MapSpec;
import com.dnfm.game.dungeon.model.RewardItem;
import com.dnfm.game.dungeon.model.TowerRewardItem;
import com.dnfm.game.dungeon.service.DungeonService;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.task.model.TaskInfo;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.NOTIFY_CHANGE_STATUS;
import com.dnfm.mina.protobuf.NOTIFY_COMPLETE_QUEST;
import com.dnfm.mina.protobuf.NOTIFY_DUNGEON_RESULT;
import com.dnfm.mina.protobuf.PT_ACCOUNT_TICKET;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_COLLECTION;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_LEVEL_REWARD;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CHAMPION_INFO;
import com.dnfm.mina.protobuf.PT_CLEAR_DUNGEON_INFO_LIST;
import com.dnfm.mina.protobuf.PT_COMPLETE_QUEST_INFO;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_DUNGEON_RESULT_QUEST_INFO;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EXP_DETAILINFO;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEMS_TOWER;
import com.dnfm.mina.protobuf.PT_LOOTS;
import com.dnfm.mina.protobuf.PT_MAP_GUIDS;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_OBJECT_INFO;
import com.dnfm.mina.protobuf.PT_QUEST_INFO;
import com.dnfm.mina.protobuf.PT_REMOVEITEMS;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.PT_TUTORIAL;
import com.dnfm.mina.protobuf.PT_TUTORIAL_DATA;
import com.dnfm.mina.protobuf.PT_TUTORIAL_REWARD;
import com.dnfm.mina.protobuf.REQ_ACCEPT_QUEST;
import com.dnfm.mina.protobuf.REQ_BATTLE_TUTORIAL_SAVE;
import com.dnfm.mina.protobuf.REQ_CLEAR_DUNGEON_INFO_LIST;
import com.dnfm.mina.protobuf.REQ_COMPLETE_QUEST;
import com.dnfm.mina.protobuf.REQ_DUNGEON_MONSTER_DIE;
import com.dnfm.mina.protobuf.REQ_DUNGEON_RESULT;
import com.dnfm.mina.protobuf.REQ_ENTER_STAGE;
import com.dnfm.mina.protobuf.REQ_INQUIRE_PARTY_RANKING;
import com.dnfm.mina.protobuf.REQ_LOOTING;
import com.dnfm.mina.protobuf.REQ_QUEST_INFO;
import com.dnfm.mina.protobuf.REQ_REWARD_QUEST;
import com.dnfm.mina.protobuf.REQ_SELECT_REWARD_CARD;
import com.dnfm.mina.protobuf.REQ_STAGE_CLEAR;
import com.dnfm.mina.protobuf.REQ_START_DUNGEON;
import com.dnfm.mina.protobuf.REQ_START_DUNGEON_COMPLETE;
import com.dnfm.mina.protobuf.REQ_SYNC_DUNGEON_START_TIME;
import com.dnfm.mina.protobuf.REQ_TUTORIAL_SAVE;
import com.dnfm.mina.protobuf.REQ_USE_COIN;
import com.dnfm.mina.protobuf.RES_ACCEPT_QUEST;
import com.dnfm.mina.protobuf.RES_BATTLE_TUTORIAL_SAVE;
import com.dnfm.mina.protobuf.RES_CHANGE_JOB;
import com.dnfm.mina.protobuf.RES_CLEAR_DUNGEON_INFO_LIST;
import com.dnfm.mina.protobuf.RES_COMPLETE_QUEST;
import com.dnfm.mina.protobuf.RES_DUNGEON_MONSTER_DIE;
import com.dnfm.mina.protobuf.RES_DUNGEON_RESULT;
import com.dnfm.mina.protobuf.RES_ENTER_STAGE;
import com.dnfm.mina.protobuf.RES_INQUIRE_PARTY_RANKING;
import com.dnfm.mina.protobuf.RES_LOOTING;
import com.dnfm.mina.protobuf.RES_QUEST_INFO;
import com.dnfm.mina.protobuf.RES_REWARD_QUEST;
import com.dnfm.mina.protobuf.RES_SELECT_REWARD_CARD;
import com.dnfm.mina.protobuf.RES_STAGE_CLEAR;
import com.dnfm.mina.protobuf.RES_STAGE_INFO;
import com.dnfm.mina.protobuf.RES_START_DUNGEON;
import com.dnfm.mina.protobuf.RES_START_DUNGEON_COMPLETE;
import com.dnfm.mina.protobuf.RES_SYNC_DUNGEON_START_TIME;
import com.dnfm.mina.protobuf.RES_TUTORIAL_SAVE;
import com.dnfm.mina.protobuf.RES_USE_COIN;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DungeonController {
   private final Logger logger = LoggerFactory.getLogger(DungeonController.class);
   @Autowired
   RoleService roleService;
   @Autowired
   DungeonService dungeonService;
   @Autowired
   BagService bagService;

   @RequestMapping
   public void REQ_INQUIRE_PARTY_RANKING(IoSession session, REQ_INQUIRE_PARTY_RANKING req_inquire_party_ranking) {
      RES_INQUIRE_PARTY_RANKING res_inquire_party_ranking = new RES_INQUIRE_PARTY_RANKING();
      res_inquire_party_ranking.count = 0L;
      res_inquire_party_ranking.ranking = new ArrayList();
      res_inquire_party_ranking.type = 711;
      res_inquire_party_ranking.transId = req_inquire_party_ranking.transId;
      MessagePusher.pushMessage((IoSession)session, res_inquire_party_ranking);
   }

   @RequestMapping
   public void ReqBattleTutorialSave(IoSession session, REQ_BATTLE_TUTORIAL_SAVE req_battle_tutorial_save) {
      RES_BATTLE_TUTORIAL_SAVE res_battle_tutorial_save = new RES_BATTLE_TUTORIAL_SAVE();
      res_battle_tutorial_save.state = req_battle_tutorial_save.state;
      res_battle_tutorial_save.transId = req_battle_tutorial_save.transId;
      MessagePusher.pushMessage((IoSession)session, res_battle_tutorial_save);
   }

   @RequestMapping
   public void ReqCompleteQuest(IoSession session, REQ_COMPLETE_QUEST req_complete_quest) {
      Role role = SessionUtils.getRoleBySession(session);
      QuestInfoBox questInfoBox = role.getQuestInfoBox();
      RES_COMPLETE_QUEST res_complete_quest = new RES_COMPLETE_QUEST();
      res_complete_quest.qindex = req_complete_quest.qindex;
      questInfoBox.updateQuestState(req_complete_quest.qindex, 1);
      role.setQuestInfoBox(questInfoBox);
      role.save();
      res_complete_quest.transId = req_complete_quest.transId;
      MessagePusher.pushMessage((IoSession)session, res_complete_quest);
      NOTIFY_COMPLETE_QUEST notify_complete_quest = new NOTIFY_COMPLETE_QUEST();
      notify_complete_quest.qindex = new ArrayList();
      PT_COMPLETE_QUEST_INFO pt_complete_quest_info = new PT_COMPLETE_QUEST_INFO();
      pt_complete_quest_info.index = req_complete_quest.qindex;
      pt_complete_quest_info.state = 1;
      if (req_complete_quest.type != null) {
         pt_complete_quest_info.type = req_complete_quest.type;
      }

      pt_complete_quest_info.removeitems = new PT_REMOVEITEMS();
      notify_complete_quest.qindex.add(pt_complete_quest_info);
      notify_complete_quest.transId = SessionUtils.getNotiTransId(session);
      MessagePusher.pushMessage((IoSession)session, notify_complete_quest);
   }

   @RequestMapping
   public void REQ_USE_COIN(IoSession session, REQ_USE_COIN req_use_coin) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      int fuhuoCnt = moneyBox.getFuhuoCnt();
      if (fuhuoCnt < 1) {
         this.logger.error("ERROR====fuhuoCnt < 1");
      } else {
         int fuhuoRes = fuhuoCnt - 1;
         moneyBox.subFuhuoCnt();
         role.setMoneyBox(moneyBox);
         role.save();
         RES_USE_COIN res_use_coin = new RES_USE_COIN();
         res_use_coin.coin = fuhuoRes;
         res_use_coin.tera = accountMoneyBox.getTylorCnt();
         res_use_coin.guid = role.getUid();
         res_use_coin.transId = req_use_coin.transId;
         MessagePusher.pushMessage((IoSession)session, res_use_coin);
      }

   }

   @RequestMapping
   public void ReqAcceptQuest(IoSession session, REQ_ACCEPT_QUEST req_accept_quest) {
      Role role = SessionUtils.getRoleBySession(session);
      int qindex = req_accept_quest.qindex;
      QuestInfoBox questInfoBox = role.getQuestInfoBox();
      RES_ACCEPT_QUEST res_accept_quest = new RES_ACCEPT_QUEST();
      res_accept_quest.qindex = req_accept_quest.qindex;
      if (req_accept_quest.type != null) {
         res_accept_quest.type = req_accept_quest.type;
      }

      Map<Integer, Integer> index2IdMap = questInfoBox.getIndex2IdMap();
      Integer id = (Integer)index2IdMap.get(qindex);
      if (id != null) {
         questInfoBox.removeQuestById(id);
      }

      PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
      pt_quest_info.qindex = req_accept_quest.qindex;
      pt_quest_info.isminequest = true;
      questInfoBox.addQuest(pt_quest_info);
      if (req_accept_quest.qindex == 800108) {
         PT_QUEST_INFO pt_quest_info1 = new PT_QUEST_INFO();
         pt_quest_info1.qindex = 10010;
         pt_quest_info1.isminequest = true;
         questInfoBox.addQuest(pt_quest_info1);
      }

      role.setQuestInfoBox(questInfoBox);
      role.save();
      res_accept_quest.transId = req_accept_quest.transId;
      MessagePusher.pushMessage((IoSession)session, res_accept_quest);
   }

   @RequestMapping
   public void ReqRewardQuest(IoSession session, REQ_REWARD_QUEST req_reward_quest) {
      int qindex = req_reward_quest.qindex;
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      QuestInfoBox questInfoBox = role.getQuestInfoBox();
      questInfoBox.updateQuestState(qindex, 2);
      PT_MONEY_ITEM roleGold = moneyBox.getMoneyItem(0);
      int goldCnt = roleGold.count;
      TaskInfo taskInfo = (TaskInfo)DataCache.TASK_MAP.get(qindex);
      if (taskInfo == null) {
         this.logger.error("ERROR====任务不存在，qindex={}", qindex);
         RES_REWARD_QUEST res_reward_quest = new RES_REWARD_QUEST();
         res_reward_quest.exp = role.getExp();
         res_reward_quest.level = role.getLevel();
         res_reward_quest.quest = new ArrayList();
         PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
         pt_quest_info.qindex = qindex;
         pt_quest_info.state = 2;
         pt_quest_info.isminequest = true;
         res_reward_quest.quest.add(pt_quest_info);
         res_reward_quest.adventureunionlevel = account.getAdventureUnionInfo().level;
         if (account.getAdventureUnionInfo().exp != 0L) {
            res_reward_quest.adventureunionexp = account.getAdventureUnionInfo().exp;
         }

         res_reward_quest.rewards = new PT_CONTENTS_REWARD_INFO();
         role.setQuestInfoBox(questInfoBox);
         res_reward_quest.transId = req_reward_quest.transId;
         MessagePusher.pushMessage((IoSession)session, res_reward_quest);
      } else {
         int rewardexp = taskInfo.getRewardexp();
         int exp = role.getExp() + rewardexp;
         int level = this.roleService.getLevel(role.getLevel(), exp);
         int rewardgold = taskInfo.getRewardgold();
         String rewardItem = taskInfo.getRewarditem();
         RES_REWARD_QUEST res_reward_quest = new RES_REWARD_QUEST();
         res_reward_quest.exp = exp;
         res_reward_quest.level = level;
         res_reward_quest.quest = new ArrayList();
         PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
         pt_quest_info.qindex = qindex;
         pt_quest_info.state = 2;
         pt_quest_info.isminequest = true;
         res_reward_quest.quest.add(pt_quest_info);
         res_reward_quest.adventureunionlevel = account.getAdventureUnionInfo().level;
         if (account.getAdventureUnionInfo().exp != 0L) {
            res_reward_quest.adventureunionexp = account.getAdventureUnionInfo().exp;
         }

         res_reward_quest.rewards = new PT_CONTENTS_REWARD_INFO();
         if (!Util.isEmpty(rewardItem.trim())) {
            res_reward_quest.rewards = DungeonService.getRewards(account, role, rewardItem);
         }

         if (rewardgold != 0) {
            if (res_reward_quest.rewards.currency == null) {
               res_reward_quest.rewards.currency = new PT_CURRENCY_REWARD_INFO();
            }

            if (res_reward_quest.rewards.currency.currency == null) {
               res_reward_quest.rewards.currency.currency = new ArrayList();
            }

            PT_MONEY_ITEM pt_money_item = new PT_MONEY_ITEM();
            pt_money_item.count = goldCnt + rewardgold;
            res_reward_quest.rewards.currency.currency.add(pt_money_item);
            if (res_reward_quest.rewards.paymentcurrency == null) {
               res_reward_quest.rewards.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
            }

            if (res_reward_quest.rewards.paymentcurrency.currency == null) {
               res_reward_quest.rewards.paymentcurrency.currency = new ArrayList();
            }

            PT_MONEY_ITEM pt_money_item1 = new PT_MONEY_ITEM();
            pt_money_item1.count = rewardgold;
            res_reward_quest.rewards.paymentcurrency.currency.add(pt_money_item1);
         }

         roleGold.count = goldCnt + rewardgold;
         moneyBox.putCurrency(roleGold);
         role.setMoneyBox(moneyBox);
         role.setQuestInfoBox(questInfoBox);
         res_reward_quest.transId = req_reward_quest.transId;
         MessagePusher.pushMessage((IoSession)session, res_reward_quest);
         if (qindex == 90221) {
            RES_CHANGE_JOB res_change_job = new RES_CHANGE_JOB();
            res_change_job.secgrowtype = 1;
            if (role.getJob() == 0 && role.getGrowtype() == 1) {
               res_change_job.changecount = 327306L;
            } else {
               res_change_job.changecount = 71703L;
            }

            this.awake(role, account, res_change_job.secgrowtype);
            MessagePusher.pushMessage((IoSession)session, res_change_job);
         }
      }

      account.save();
      role.save();
   }

   private void awake(Role role, Account account, int secgrowtype) {
      AdventureUnionInfo adventureUnionInfo = account.getAdventureUnionInfo();
      if (adventureUnionInfo.collections == null) {
         adventureUnionInfo.collections = new ArrayList();
         PT_ADVENTURE_UNION_COLLECTION pt_adventure_union_collection = new PT_ADVENTURE_UNION_COLLECTION();
         pt_adventure_union_collection.job = role.getJob();
         pt_adventure_union_collection.growtype = role.getGrowtype();
         pt_adventure_union_collection.secgrowtype = role.getSecgrowtype();
         pt_adventure_union_collection.equipscore = role.getEquipscore();
         adventureUnionInfo.collections.add(pt_adventure_union_collection);
      } else {
         ((PT_ADVENTURE_UNION_COLLECTION)adventureUnionInfo.collections.get(0)).job = role.getJob();
         ((PT_ADVENTURE_UNION_COLLECTION)adventureUnionInfo.collections.get(0)).growtype = role.getGrowtype();
         ((PT_ADVENTURE_UNION_COLLECTION)adventureUnionInfo.collections.get(0)).secgrowtype = role.getSecgrowtype();
         ((PT_ADVENTURE_UNION_COLLECTION)adventureUnionInfo.collections.get(0)).equipscore = role.getEquipscore();
      }

      if (adventureUnionInfo.receivedcollectionrewards == null) {
         adventureUnionInfo.receivedcollectionrewards = new ArrayList();
         adventureUnionInfo.receivedcollectionrewards.add(4101);
      } else {
         adventureUnionInfo.receivedcollectionrewards.clear();
         adventureUnionInfo.receivedcollectionrewards.add(4101);
      }

      adventureUnionInfo.levelrewards = new ArrayList();

      for(int i = 0; i < 9; ++i) {
         PT_ADVENTURE_UNION_LEVEL_REWARD pt_adventure_union_level_reward = new PT_ADVENTURE_UNION_LEVEL_REWARD();
         pt_adventure_union_level_reward.level = i + 1;
         adventureUnionInfo.levelrewards.add(pt_adventure_union_level_reward);
      }

      adventureUnionInfo.isadventureCondition = true;
      account.setAdventureUnionInfo(adventureUnionInfo);
      role.setSecgrowtype(1);
   }

   @RequestMapping
   public void ReqSelectRewardCard(IoSession session, REQ_SELECT_REWARD_CARD reqSkillSlotList) {
      Role role = SessionUtils.getRoleBySession(session);
      Integer curDungeonIndex = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CUR_DUNGEON_INDEX, Integer.class);
      this.logger.error("curDungeonIndex: {}", curDungeonIndex);
      Dungeon dungeon = (Dungeon)DataCache.DUNGEON_MAP.get(curDungeonIndex);
      if (this.dungeonService.isEmergencyTask(curDungeonIndex)) {
         if (curDungeonIndex != 2002180000) {
         }
      } else if (!this.dungeonService.isHellParty(dungeon.getDungeonType())) {
         if (DungeonCache.isAdvDungeon(curDungeonIndex)) {
            int[] equip = new int[]{2008030098, 2008010098, 2008000098, 2006040050, 2004030076, 2004040079, 2002010076, 2004010075, 2001030076, 2002000078, 2004000081, 2001010076, 2002040073, 2006030079, 2006020074, 2006010080, 2006000078, 2004020073, 2001020074, 2001000079, 2002030064, 2002020081};
            int rand = Util.randIndex(equip.length - 1);
            int index = equip[rand];
            RES_SELECT_REWARD_CARD resSelectRewardCard0 = new RES_SELECT_REWARD_CARD();
            resSelectRewardCard0.earngold = 127;
            resSelectRewardCard0.premiumindex = 2000180054;
            resSelectRewardCard0.currency = new ArrayList();
            PT_MONEY_ITEM pt_money_item = new PT_MONEY_ITEM();
            pt_money_item.index = 2013000001;
            resSelectRewardCard0.currency.add(pt_money_item);
            PT_MONEY_ITEM pt_money_item1 = new PT_MONEY_ITEM();
            pt_money_item1.count = 127 + role.getMoneyBox().getMoneyCnt();
            resSelectRewardCard0.currency.add(pt_money_item1);
            resSelectRewardCard0.items = new PT_ITEMS();
            resSelectRewardCard0.items.equipitems = new ArrayList();
            PT_EQUIP pt_equip = new PT_EQUIP();
            pt_equip.index = index;
            pt_equip.guid = IdGenerator.getNextId();
            pt_equip.quality = 100;
            pt_equip.endurance = 30;
            role.getEquipBox().addEquip(pt_equip);
            resSelectRewardCard0.items.equipitems.add(pt_equip);
            resSelectRewardCard0.transId = reqSkillSlotList.transId;
            MessagePusher.pushMessage((IoSession)session, resSelectRewardCard0);
         } else {
            RES_SELECT_REWARD_CARD resSelectRewardCard0 = new RES_SELECT_REWARD_CARD();
            resSelectRewardCard0.earngold = 127;
            resSelectRewardCard0.premiumindex = 2000180054;
            resSelectRewardCard0.currency = new ArrayList();
            PT_MONEY_ITEM pt_money_item = new PT_MONEY_ITEM();
            pt_money_item.index = 2013000001;
            resSelectRewardCard0.currency.add(pt_money_item);
            PT_MONEY_ITEM pt_money_item1 = new PT_MONEY_ITEM();
            pt_money_item1.count = 1367 + role.getMoneyBox().getMoneyCnt();
            resSelectRewardCard0.currency.add(pt_money_item1);
            resSelectRewardCard0.items = new PT_ITEMS();
            resSelectRewardCard0.items.equipitems = new ArrayList();
            PT_EQUIP pt_equip = new PT_EQUIP();
            pt_equip.index = 2008010077;
            pt_equip.guid = 13571034839800227L;
            pt_equip.quality = 100;
            pt_equip.endurance = 30;
            resSelectRewardCard0.items.equipitems.add(pt_equip);
            resSelectRewardCard0.transId = reqSkillSlotList.transId;
            MessagePusher.pushMessage((IoSession)session, resSelectRewardCard0);
         }
      }

   }

   @RequestMapping
   public void ReqTutorialSave(IoSession session, REQ_TUTORIAL_SAVE req_tutorial_save) {
      List<PT_TUTORIAL> list = req_tutorial_save.list;
      Role role = SessionUtils.getRoleBySession(session);
      TutoBox tutoBox = role.getTutoBox();
      RES_TUTORIAL_SAVE res_tutorial_save = new RES_TUTORIAL_SAVE();
      res_tutorial_save.list = new ArrayList();

      for(PT_TUTORIAL pt_tutorial : list) {
         PT_TUTORIAL_DATA pt_tutorial_data = new PT_TUTORIAL_DATA();
         pt_tutorial_data.index = pt_tutorial.index;
         pt_tutorial_data.state = pt_tutorial.state;
         if (pt_tutorial.state == 2 && pt_tutorial.type == 1) {
            PT_TUTORIAL pt = new PT_TUTORIAL();
            pt.index = pt_tutorial.index;
            pt.state = 2;
            tutoBox.addTuto(pt);
         }

         pt_tutorial_data.type = pt_tutorial.type;
         pt_tutorial_data.enforce = pt_tutorial.enforce;
         pt_tutorial_data.tutorialreward = new PT_TUTORIAL_REWARD();
         res_tutorial_save.list.add(pt_tutorial_data);
      }

      role.setTutoBox(tutoBox);
      role.save();
      res_tutorial_save.transId = req_tutorial_save.transId;
      MessagePusher.pushMessage((IoSession)session, res_tutorial_save);
   }

   @RequestMapping
   public void ReqDungeonResult(IoSession session, REQ_DUNGEON_RESULT req_dungeon_result) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      Integer stage = req_dungeon_result.stage;
      Integer startStageGuid = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.START_STAGE_GUID, Integer.class);
      Integer cur_dungeon_index = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CUR_DUNGEON_INDEX, Integer.class);
      if (cur_dungeon_index == null) {
         this.logger.error("发生重连==cur_dungeon_index==null");
      } else {
         if (cur_dungeon_index == 2002180000) {
            int floor = stage - startStageGuid + 1;
            TowerInfoBox towerInfoBox = role.getTowerInfoBox();
            RES_DUNGEON_RESULT res_dungeon_result = new RES_DUNGEON_RESULT();
            res_dungeon_result.transId = req_dungeon_result.transId;
            MessagePusher.pushMessage((IoSession)session, res_dungeon_result);
            NOTIFY_DUNGEON_RESULT notify_dungeon_result = new NOTIFY_DUNGEON_RESULT();
            notify_dungeon_result.dungeonindex = cur_dungeon_index;
            notify_dungeon_result.matchtype = 3;
            notify_dungeon_result.exp = role.getExp();
            notify_dungeon_result.level = role.getLevel();
            notify_dungeon_result.type = 7;
            notify_dungeon_result.fatigue = role.getFatigue();
            notify_dungeon_result.dungeonguid = req_dungeon_result.dungeonguid;
            notify_dungeon_result.rewards = new ArrayList();
            PT_ITEMS_TOWER reward = new PT_ITEMS_TOWER();
            if (!towerInfoBox.hasReward(floor)) {
               TowerRewardItem towerRewardItem = (TowerRewardItem)DataCache.towerRewardItemMap.get(floor);
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
               int index = towerRewardItem.index;
               int cnt = towerRewardItem.cnt;
               if (index == 0) {
                  reward.index = 0;
                  reward.count = cnt;
                  role.getMoneyBox().addmoney(cnt);
               } else {
                  int type = BagService.getIndexType(index);
                  switch (type) {
                     case 1:
                        if (reward.avataritems == null) {
                           reward.avataritems = new ArrayList();
                        }

                        PT_AVATAR_ITEM avatar_item = avatarBox.addAvatar(index);
                        reward.avataritems.add(avatar_item);
                        break;
                     case 2:
                     case 100:
                        if (reward.equipitems == null) {
                           reward.equipitems = new ArrayList();
                        }

                        PT_EQUIP equip = EquipDataPool.createEquip(index);
                        equipBox.addEquip(equip);
                        reward.equipitems.add(equip);
                        break;
                     case 3:
                        if (reward.titleitems == null) {
                           reward.titleitems = new ArrayList();
                        }

                        PT_EQUIP title = titleBox.addTitle(index);
                        reward.titleitems.add(title);
                        break;
                     case 1000:
                        if (reward.materialitems == null) {
                           reward.materialitems = new ArrayList();
                        }

                        PT_STACKABLE material = new PT_STACKABLE();
                        material.index = index;
                        material.count = cnt;
                        reward.materialitems.add(material);
                        materialBox.updateMaterialAdd(index, cnt);
                        break;
                     case 1001:
                        if (reward.consumeitems == null) {
                           reward.consumeitems = new ArrayList();
                        }

                        PT_STACKABLE consume = new PT_STACKABLE();
                        consume.index = index;
                        consume.count = cnt;
                        reward.consumeitems.add(consume);
                        consumableBox.updateConsumeAdd(index, cnt);
                        break;
                     case 1005:
                        if (reward.emblemitems == null) {
                           reward.emblemitems = new ArrayList();
                        }

                        PT_STACKABLE emblem = new PT_STACKABLE();
                        emblem.index = index;
                        emblem.count = cnt;
                        reward.emblemitems.add(emblem);
                        emblemBox.updateEmblemAdd(index, cnt);
                        break;
                     case 1006:
                        if (reward.epicpieceitems == null) {
                           reward.epicpieceitems = new ArrayList();
                        }

                        PT_STACKABLE epicpiece = new PT_STACKABLE();
                        epicpiece.index = index;
                        epicpiece.count = cnt;
                        reward.epicpieceitems.add(epicpiece);
                        epicPieceBox.updateEpicPieceAdd(index, cnt);
                        account.save();
                        break;
                     case 1009:
                        if (reward.carditems == null) {
                           reward.carditems = new ArrayList();
                        }

                        PT_STACKABLE card = new PT_STACKABLE();
                        card.index = index;
                        card.count = cnt;
                        reward.carditems.add(card);
                        cardBox.updateCardAdd(index, cnt);
                        break;
                     default:
                        this.logger.error("UNREACH==TOWER_REWARD==type==" + type + "==index==" + index);
                  }
               }

               reward.floor = floor;
               notify_dungeon_result.rewards.add(reward);
               towerInfoBox.setReward(floor);
               role.save();
            }

            notify_dungeon_result.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_dungeon_result);
         } else {
            DungeonTicketsBox dungeonTicketsBox = role.getDungeonTicketsBox();
            int curHellGauge = dungeonTicketsBox.getHellGauge();
            int qindex = -1;
            int type = -1;
            if (!Util.isEmpty(req_dungeon_result.qindex)) {
               qindex = ((PT_DUNGEON_RESULT_QUEST_INFO)req_dungeon_result.qindex.get(0)).index;
               if (((PT_DUNGEON_RESULT_QUEST_INFO)req_dungeon_result.qindex.get(0)).type != null) {
                  type = ((PT_DUNGEON_RESULT_QUEST_INFO)req_dungeon_result.qindex.get(0)).type;
               }

               QuestInfoBox questInfoBox = role.getQuestInfoBox();
               questInfoBox.updateQuestState(qindex, 1);
               role.setQuestInfoBox(questInfoBox);
            }

            Dungeon dungeon = (Dungeon)DataCache.DUNGEON_MAP.get(cur_dungeon_index);
            String dungeonType = dungeon.getDungeonType();
            if (this.dungeonService.isHellParty(dungeonType)) {
               int finalHellGauge = curHellGauge + 25;
               if (finalHellGauge > 500) {
                  finalHellGauge = 0;
               }

               this.logger.error("深渊当前进度=={}", finalHellGauge);
               dungeonTicketsBox.setHellGauge(finalHellGauge);
               role.setDungeonTicketsBox(dungeonTicketsBox);
            }

            int clearExp = dungeon.getClearExp();
            Integer consume_fatigue = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CONSUME_FATIGUE, Integer.class);
            if (qindex != -1) {
               NOTIFY_COMPLETE_QUEST notify_complete_quest = new NOTIFY_COMPLETE_QUEST();
               notify_complete_quest.qindex = new ArrayList();
               PT_COMPLETE_QUEST_INFO pt_complete_quest_info = new PT_COMPLETE_QUEST_INFO();
               pt_complete_quest_info.index = qindex;
               pt_complete_quest_info.state = 1;
               if (type != -1) {
                  pt_complete_quest_info.type = type;
               }

               pt_complete_quest_info.removeitems = new PT_REMOVEITEMS();
               notify_complete_quest.qindex.add(pt_complete_quest_info);
               notify_complete_quest.transId = SessionUtils.getNotiTransId(session);
               MessagePusher.pushMessage((IoSession)session, notify_complete_quest);
            }

            RES_DUNGEON_RESULT res_dungeon_result = new RES_DUNGEON_RESULT();
            res_dungeon_result.transId = req_dungeon_result.transId;
            MessagePusher.pushMessage((IoSession)session, res_dungeon_result);
            NOTIFY_DUNGEON_RESULT notify_dungeon_result = new NOTIFY_DUNGEON_RESULT();
            if (role.getExp() < 107836272) {
               notify_dungeon_result.clearexp = new PT_EXP_DETAILINFO();
               notify_dungeon_result.clearexp.baseexp = clearExp;
            }

            int finalExp = role.getExp() + clearExp;
            notify_dungeon_result.dungeonindex = cur_dungeon_index;
            notify_dungeon_result.matchtype = 3;
            notify_dungeon_result.exp = finalExp;
            notify_dungeon_result.level = this.roleService.getLevel(role.getLevel(), finalExp);
            notify_dungeon_result.battleworld = 1;
            notify_dungeon_result.bchannel = 1;
            notify_dungeon_result.bip = "192.168.3.155";
            notify_dungeon_result.bport = 22000;
            notify_dungeon_result.authkey = UUID.randomUUID().toString().toLowerCase();
            if (qindex == 90219 || qindex == 90221) {
               notify_dungeon_result.adventureticket = new ArrayList();
               PT_ACCOUNT_TICKET pt_account_ticket = new PT_ACCOUNT_TICKET();
               pt_account_ticket.dungeontype = "first_awakening";
               notify_dungeon_result.adventureticket.add(pt_account_ticket);
            }

            session.setAttribute(SessionProperties.AUTH_KEY_BATTLE, notify_dungeon_result.authkey);
            role.setLevel(notify_dungeon_result.level);
            role.setExp(finalExp);
            role.save();
            notify_dungeon_result.adventureticket = new ArrayList();
            PT_ACCOUNT_TICKET pt_account_ticket = new PT_ACCOUNT_TICKET();
            pt_account_ticket.dungeontype = dungeonType;
            notify_dungeon_result.adventureticket.add(pt_account_ticket);
            notify_dungeon_result.fatigue = role.getFatigue();
            notify_dungeon_result.consumefatigue = consume_fatigue;
            notify_dungeon_result.dungeonguid = req_dungeon_result.dungeonguid;
            notify_dungeon_result.cardcost = 1000;
            notify_dungeon_result.type = req_dungeon_result.type;
            notify_dungeon_result.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_dungeon_result);
         }

      }
   }

   @RequestMapping
   public void ReqLooting(IoSession session, REQ_LOOTING req_looting) {
      int stage = req_looting.stage;
      List<PT_LOOTS> loots = req_looting.loots;
      List<PT_STACKABLE> consumeList = req_looting.consume;
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      RES_LOOTING res_looting = this.bagService.getResLooting(loots, consumeList, role, account);
      role.save();
      account.save();
      res_looting.transId = req_looting.transId;
      MessagePusher.pushMessage((IoSession)session, res_looting);
   }

   @RequestMapping
   public void ReqStageClear(IoSession session, REQ_STAGE_CLEAR req_stage_clear) {
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      Account account = SessionUtils.getAccountBySession(session);
      int stage = req_stage_clear.stage;
      int exp = role.getExp();
      int fatigue = role.getFatigue();
      Long curHellGuid = (Long)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CUR_HELL_STAGE_GUID, Long.class);
      List<RewardItem> curHellSelList = (List)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CUR_HELL_SEL_LIST, List.class);
      if (curHellGuid != null && stage == curHellGuid.intValue() && curHellSelList != null) {
         for(RewardItem selItem : curHellSelList) {
            BagService.addItem(role, account, selItem.index, selItem.finalCnt, 0L);
         }
      }

      account.save();
      RES_STAGE_CLEAR res_stage_clear = new RES_STAGE_CLEAR();
      res_stage_clear.fatigue = fatigue;
      res_stage_clear.exp = exp;
      res_stage_clear.reward = new PT_ITEMS();
      res_stage_clear.servertime = TimeUtil.currS();
      res_stage_clear.transId = req_stage_clear.transId;
      MessagePusher.pushMessage((IoSession)session, res_stage_clear);
      TowerInfoBox towerInfoBox = role.getTowerInfoBox();
      Integer curDungeonIndex = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CUR_DUNGEON_INDEX, Integer.class);
      if (curDungeonIndex != null && curDungeonIndex == 2002180000) {
         int stageGuid = req_stage_clear.stage;
         int startStageGuid = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.START_STAGE_GUID, Integer.class);
         int floor = stageGuid - startStageGuid + 1;
         if (role.getTowerInfoBox().getClearfloor() < floor) {
            role.getTowerInfoBox().setClearfloor(floor);
         }
      }

   }

   @RequestMapping
   public void ReqDungeonMonsterDie(IoSession session, REQ_DUNGEON_MONSTER_DIE req_dungeon_monster_die) {
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      Map<Long, Integer> monster_exp = (Map)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.MONSTER_EXP, Map.class);
      int myExp = role.getExp();
      RES_DUNGEON_MONSTER_DIE res_dungeon_monster_die = new RES_DUNGEON_MONSTER_DIE();
      long dungeonguid = req_dungeon_monster_die.dungeonguid;
      int stageguid = req_dungeon_monster_die.stageguid;
      List<Integer> list = req_dungeon_monster_die.list;
      int earnExp = 0;
      res_dungeon_monster_die.earnexp = earnExp;
      res_dungeon_monster_die.totalexp = myExp + earnExp;
      res_dungeon_monster_die.list = req_dungeon_monster_die.list;
      res_dungeon_monster_die.level = this.roleService.getLevel(role.getLevel(), res_dungeon_monster_die.totalexp);
      res_dungeon_monster_die.sender = role.getUid();
      role.setLevel(res_dungeon_monster_die.level);
      role.setExp(res_dungeon_monster_die.totalexp);
      role.save();
      res_dungeon_monster_die.transId = req_dungeon_monster_die.transId;
      MessagePusher.pushMessage((IoSession)session, res_dungeon_monster_die);
   }

   @RequestMapping
   public void ReqSyncDungeonStartTime(IoSession session, REQ_SYNC_DUNGEON_START_TIME req_sync_dungeon_start_time) {
      RES_SYNC_DUNGEON_START_TIME res_sync_dungeon_start_time = new RES_SYNC_DUNGEON_START_TIME();
      res_sync_dungeon_start_time.starttime = TimeUtil.currS();
      res_sync_dungeon_start_time.transId = req_sync_dungeon_start_time.transId;
      MessagePusher.pushMessage((IoSession)session, res_sync_dungeon_start_time);
   }

   @RequestMapping
   public void ReqStartDungeonComplete(IoSession session, REQ_START_DUNGEON_COMPLETE req_start_dungeon_complete) {
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      int fatigue = role.getFatigue();
      String dungeon_type = (String)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.DUNGEON_TYPE, String.class);
      RES_START_DUNGEON_COMPLETE res_start_dungeon_complete = new RES_START_DUNGEON_COMPLETE();
      res_start_dungeon_complete.consumefatigue = 1;
      session.setAttribute(SessionProperties.CONSUME_FATIGUE, 2);
      res_start_dungeon_complete.fatigue = fatigue;
      List<PT_ACCOUNT_TICKET> list = new ArrayList();
      PT_ACCOUNT_TICKET pt_account_ticket = new PT_ACCOUNT_TICKET();
      pt_account_ticket.dungeontype = dungeon_type;
      list.add(pt_account_ticket);
      res_start_dungeon_complete.adventureticket = list;
      res_start_dungeon_complete.transId = req_start_dungeon_complete.transId;
      MessagePusher.pushMessage((IoSession)session, res_start_dungeon_complete);
      role.save();
      Integer notifyTransId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.NOTIFY_TRANS_ID, Integer.class);
      NOTIFY_CHANGE_STATUS notify_change_status = new NOTIFY_CHANGE_STATUS();
      notify_change_status.status = 3;
      notify_change_status.transId = notifyTransId;
      notifyTransId = notifyTransId + 1;
      session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, notifyTransId);
      MessagePusher.pushMessage((IoSession)session, notify_change_status);
   }

   @RequestMapping
   public void ReqEnterStage(IoSession session, REQ_ENTER_STAGE req_enter_stage) {
      long dungeonguid = req_enter_stage.dungeonguid;
      int stageguid = req_enter_stage.stageguid;
      Role role = SessionUtils.getRoleBySession(session);
      Integer startStageId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.START_STAGE_GUID, Integer.class);
      Integer consume_fatigue = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CONSUME_FATIGUE, Integer.class);
      int fatigue = role.getFatigue();
      RES_ENTER_STAGE res_enter_stage = new RES_ENTER_STAGE();
      if (fatigue - 1 <= 0) {
         res_enter_stage.fatigue = 0;
      } else {
         res_enter_stage.fatigue = fatigue - 1;
      }

      if (stageguid != startStageId && consume_fatigue != null) {
         res_enter_stage.consumefatigue = consume_fatigue;
         role.setFatigue(res_enter_stage.fatigue);
         role.save();
         session.setAttribute(SessionProperties.CONSUME_FATIGUE, consume_fatigue + 1);
      }

      res_enter_stage.servertime = TimeUtil.currS();
      res_enter_stage.sender = role.getUid();
      res_enter_stage.transId = req_enter_stage.transId;
      MessagePusher.pushMessage((IoSession)session, res_enter_stage);
   }

   @RequestMapping
   public void ReqQuestInfo(IoSession session, REQ_QUEST_INFO reqQuestInfo) {
      Long charguid = (Long)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER_UID, Long.class);
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      RES_QUEST_INFO resQuestInfo0 = new RES_QUEST_INFO();
      resQuestInfo0.quest = role.getQuestInfoBox().getQuest();
      resQuestInfo0.transId = reqQuestInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resQuestInfo0);
   }

   @RequestMapping
   public void ReqClearDungeonInfoList(IoSession session, REQ_CLEAR_DUNGEON_INFO_LIST reqClearDungeonInfoList) {
      Integer index = reqClearDungeonInfoList.index;
      Integer difficulty = reqClearDungeonInfoList.difficulty;
      Role role = SessionUtils.getRoleBySession(session);
      ClearDungeonBox clearDungeonBox = role.getClearDungeonBox();
      Integer deviceType = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.DEVICE_TYPE, Integer.class);
      if (deviceType == 1) {
         if (index == -1 && difficulty == -1) {
            List<PT_CLEAR_DUNGEON_INFO_LIST> clearList = clearDungeonBox.getDungeoninfos();
            int size = clearList.size();
            int pageNum = size / 100;
            int remainder = size % 100;
            if (remainder != 0) {
               ++pageNum;
            }

            if (pageNum == 0) {
               RES_CLEAR_DUNGEON_INFO_LIST res_clear_dungeon_info_list = new RES_CLEAR_DUNGEON_INFO_LIST();
               res_clear_dungeon_info_list.dungeoninfos = new ArrayList();
               res_clear_dungeon_info_list.transId = reqClearDungeonInfoList.transId;
               MessagePusher.pushMessage((IoSession)session, res_clear_dungeon_info_list);
            } else {
               for(int i = 0; i < pageNum; ++i) {
                  RES_CLEAR_DUNGEON_INFO_LIST res_clear_dungeon_info_list = new RES_CLEAR_DUNGEON_INFO_LIST();
                  res_clear_dungeon_info_list.dungeoninfos = new ArrayList();
                  if (i == pageNum - 1 && remainder != 0) {
                     for(int j = 0; j < remainder; ++j) {
                        int k = j + 100 * i;
                        res_clear_dungeon_info_list.dungeoninfos.add(clearList.get(k));
                     }
                  } else {
                     for(int j = 0; j < 100; ++j) {
                        int k = j + 100 * i;
                        res_clear_dungeon_info_list.dungeoninfos.add(clearList.get(k));
                     }
                  }

                  res_clear_dungeon_info_list.transId = reqClearDungeonInfoList.transId;
                  MessagePusher.pushMessage((IoSession)session, res_clear_dungeon_info_list);
               }
            }
         } else {
            RES_CLEAR_DUNGEON_INFO_LIST res_clear_dungeon_info_list = new RES_CLEAR_DUNGEON_INFO_LIST();
            res_clear_dungeon_info_list.dungeoninfos = new ArrayList();
            String dungeonType = ((Dungeon)DataCache.DUNGEON_MAP.get(index)).getDungeonType();
            if (dungeonType.contains("first_awakening")) {
               clearDungeonBox.addDungeonInfo2(index);
               role.setClearDungeonBox(clearDungeonBox);
               role.save();
            }

            res_clear_dungeon_info_list.dungeoninfos.add(clearDungeonBox.getDungeoninfo(index));
            res_clear_dungeon_info_list.transId = reqClearDungeonInfoList.transId;
            MessagePusher.pushMessage((IoSession)session, res_clear_dungeon_info_list);
         }
      } else {
         RES_CLEAR_DUNGEON_INFO_LIST res_clear_dungeon_info_list = new RES_CLEAR_DUNGEON_INFO_LIST();
         res_clear_dungeon_info_list.dungeoninfos = new ArrayList();
         res_clear_dungeon_info_list.transId = reqClearDungeonInfoList.transId;
         MessagePusher.pushMessage((IoSession)session, res_clear_dungeon_info_list);
      }

   }

   @RequestMapping
   public void ReqStartDungeon(IoSession session, REQ_START_DUNGEON req_start_dungeon) {
      Role role = SessionUtils.getRoleBySession(session);
      Long charguid = role.getUid();
      Integer index = req_start_dungeon.index;
      session.setAttribute(SessionProperties.CUR_DUNGEON_INDEX, index);
      Map<Long, Integer> map = new HashMap();
      session.setAttribute(SessionProperties.MONSTER_EXP, map);
      session.setAttribute(SessionProperties.CONSUME_FATIGUE, 0);
      RES_START_DUNGEON res_start_dungeon = new RES_START_DUNGEON();
      if (req_start_dungeon.grade != null) {
         res_start_dungeon.grade = req_start_dungeon.grade;
      }

      res_start_dungeon.createtime = (int)TimeUtil.currS();
      res_start_dungeon.index = index;
      res_start_dungeon.dungeonguid = IdGenerator.getNextId();
      session.setAttribute(SessionProperties.DUNGEON_GUID, res_start_dungeon.dungeonguid);
      int startStageGuid = Util.randInt(15, 90);
      session.setAttribute(SessionProperties.START_STAGE_GUID, startStageGuid);
      res_start_dungeon.startstageguid = startStageGuid;
      res_start_dungeon.champion = new PT_CHAMPION_INFO();
      Dungeon dungeon = (Dungeon)DataCache.DUNGEON_MAP.get(index);
      List<MapSpec> mapSpecs = (List)DataCache.DUNGEON_MAP_SPEC.get(index);
      session.setAttribute(SessionProperties.DUNGEON_TYPE, dungeon.getDungeonType());
      if (this.dungeonService.isHellParty(dungeon.getDungeonType())) {
         List<RewardItem> curHellSelList = new ArrayList();
         session.setAttribute(SessionProperties.CUR_HELL_SEL_LIST, curHellSelList);
      }

      res_start_dungeon.mapguids = new ArrayList();

      for(int i = 0; i < mapSpecs.size(); ++i) {
         PT_MAP_GUIDS pt_map_guids = new PT_MAP_GUIDS();
         pt_map_guids.guid = startStageGuid + i;
         res_start_dungeon.mapguids.add(pt_map_guids);
      }

      res_start_dungeon.lastdungeonrankingscore = 2319298616168153107L;
      res_start_dungeon.requestedindex = req_start_dungeon.index;
      this.logger.error("dungeonService=={}", this.dungeonService);
      int grade = 0;
      if (req_start_dungeon.grade != null) {
         grade = req_start_dungeon.grade;
      }

      DungeonTicketsBox dungeonTicketsBox = role.getDungeonTicketsBox();
      boolean isFull = false;
      int hellGauge = role.getDungeonTicketsBox().hellGauge;
      this.logger.error("hellGauge=={}", hellGauge);
      if (hellGauge == 500) {
         isFull = true;
      }

      List<RES_STAGE_INFO> res_stage_infoList = null;
      if (index == 2002180000) {
         res_stage_infoList = this.dungeonService.getStageInfoListTower((long)startStageGuid);
      } else {
         this.logger.error("0");
         this.logger.error("isFull={}, grade={}, index={}, mapSpecs={}, startStageGuid={}, role.getUid={}", new Object[]{isFull, grade, index, JSONArray.toJSONString(mapSpecs), startStageGuid, role.getUid()});
         res_stage_infoList = this.dungeonService.getStageInfoList(isFull, grade, index, session, mapSpecs, (long)startStageGuid, role.getUid());
         this.logger.error("0" + res_stage_infoList.size());
      }

      if (res_stage_infoList == null) {
         this.logger.error("UNREACH==res_stage_infoList == null");
      } else {
         if (index != 2002180000) {
            int resSzie = res_stage_infoList.size();
            int lastMapObjSize = ((RES_STAGE_INFO)res_stage_infoList.get(resSzie - 1)).map.objects.size();
            long lastMonsterId = 0L;
            this.logger.error(JSONArray.toJSONString(res_stage_infoList));
            if (lastMapObjSize == 0) {
               this.logger.error("0-1");
               lastMonsterId = ((PT_OBJECT_INFO)((RES_STAGE_INFO)res_stage_infoList.get(0)).map.objects.get(0)).guid;
            } else {
               lastMonsterId = ((PT_OBJECT_INFO)((RES_STAGE_INFO)res_stage_infoList.get(resSzie - 1)).map.objects.get(lastMapObjSize - 1)).guid;
            }

            long cobjectId = lastMonsterId + 1L;
            res_start_dungeon.character = this.roleService.getPtUserInfo(session, cobjectId, -1);
            this.logger.error("1");
            res_start_dungeon.transId = req_start_dungeon.transId;
            MessagePusher.pushMessage((IoSession)session, res_start_dungeon);
            this.logger.error("2");

            for(RES_STAGE_INFO res_stage_info : res_stage_infoList) {
               res_stage_info.transId = SessionUtils.getNotiTransId(session);
               MessagePusher.pushMessage((IoSession)session, res_stage_info);
            }

            this.logger.error("3");
            if (this.dungeonService.isHellParty(dungeon.getDungeonType())) {
               MaterialBox materialBox = role.getMaterialBox();
               int res = materialBox.updateMaterialSub(2013104182, 22);
               if (res < 22) {
                  materialBox.updateMaterialSub(2013100709, 22 - res);
               }
            }

            this.logger.error("4");
            if (this.dungeonService.isEmergencyTask(index)) {
               dungeonTicketsBox.subDailyTicket(dungeon.getDungeonType());
            }
         } else {
            long cobjectId = 32L;
            res_start_dungeon.character = this.roleService.getPtUserInfo(session, cobjectId, -1);
            res_start_dungeon.transId = req_start_dungeon.transId;
            MessagePusher.pushMessage((IoSession)session, res_start_dungeon);

            for(RES_STAGE_INFO res_stage_info : res_stage_infoList) {
               res_stage_info.transId = SessionUtils.getNotiTransId(session);
               MessagePusher.pushMessage((IoSession)session, res_stage_info);
            }

            if (this.dungeonService.isEmergencyTask(index)) {
               dungeonTicketsBox.subDailyTicket(dungeon.getDungeonType());
            }
         }

         this.logger.error("5");
         role.save();
      }
   }
}
