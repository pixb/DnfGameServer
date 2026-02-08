package com.dnfm.game.role.service;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.model.Pos;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.game.ServerService;
import com.dnfm.game.adventure.model.AdventureReapInfo;
import com.dnfm.game.adventure.model.AdventureUnionInfo;
import com.dnfm.game.bag.model.AdvBookBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.ClearDungeonBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.DungeonTicketsBox;
import com.dnfm.game.bag.model.EquippedBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.QuestInfoBox;
import com.dnfm.game.bag.model.SysBuffBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.config.Equip;
import com.dnfm.game.config.PetExp;
import com.dnfm.game.config.RoleExp;
import com.dnfm.game.config.Server;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.fight.service.BroadcastService;
import com.dnfm.game.identity.IdentityType;
import com.dnfm.game.map.service.MapService;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.player.model.PlayerProfile;
import com.dnfm.game.role.RoleDataPool;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.skill.model.SkillBox;
import com.dnfm.game.skill.model.SkillslotBox;
import com.dnfm.game.skill.service.SkillService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.game.utils.dirtywords.WordService;
import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.CreateRoleEvent;
import com.dnfm.listener.event.LoginEvent;
import com.dnfm.listener.event.LogoutEvent;
import com.dnfm.listener.event.RoleLevelUpEvent;
import com.dnfm.logs.Reason;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.protobuf.DEF_ITEM_CONSUMABLE;
import com.dnfm.mina.protobuf.ENUM_TEAM;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_COLLECTION;
import com.dnfm.mina.protobuf.PT_AVATAR_INDEX_SLOT;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CHARACTER;
import com.dnfm.mina.protobuf.PT_CHARACTER_EQUIP_ONLY_INDEX;
import com.dnfm.mina.protobuf.PT_CHARACTER_INFO;
import com.dnfm.mina.protobuf.PT_CONSUME_LIST;
import com.dnfm.mina.protobuf.PT_CREATURE_LEARN_SKILL_INFOS;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import com.dnfm.mina.protobuf.PT_EQUIP_INDEX_SLOT;
import com.dnfm.mina.protobuf.PT_EQUIP_LIST;
import com.dnfm.mina.protobuf.PT_MATERIAL_LIST;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_QUEST_INFO;
import com.dnfm.mina.protobuf.PT_SKILL;
import com.dnfm.mina.protobuf.PT_SKILLS;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.PT_USER_INFO;
import com.dnfm.mina.protobuf.REQ_CREATE_CHARACTER;
import com.dnfm.mina.protobuf.RES_CHARAC_LIST;
import com.dnfm.mina.protobuf.RES_SKILL_LIST;
import com.dnfm.mina.session.SessionManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.mina.core.session.IoSession;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
   private static final Set<Integer> giftLevels = new HashSet(Arrays.asList(80, 90, 100, 110, 120, 130));
   private final Logger logger = LoggerFactory.getLogger(RoleService.class);
   @Autowired
   Dao dao;
   @Autowired
   MapService mapService;
   @Autowired
   WordService wordService;
   @Autowired
   AccountService accountService;
   @Autowired
   PlayerService playerService;
   @Autowired
   ServerService serverService;
   @Autowired
   BroadcastService broadcastService;
   @Autowired
   SkillService skillService;
   @Autowired
   EquipService equipService;
   @Autowired
   BagService bagService;
   @Autowired
   private GateService gateService;

   public boolean checkRoleName(String roleName) {
      int count = this.dao.count(Role.class, Cnd.where("name", "=", roleName));
      return count > 0;
   }

   public void addConsumable(Role role, PT_STACKABLE ptStackable) {
      ConsumableBox consumableBox = role.getConsumableBox();
      consumableBox.addConsumable(ptStackable);
      role.setConsumableBox(consumableBox);
   }

   public void addMaterial(Role role, PT_STACKABLE ptStackable) {
      MaterialBox materialBox = role.getMaterialBox();
      materialBox.updateMaterial(ptStackable);
      role.setMaterialBox(materialBox);
   }

   private String key(int job, int growType) {
      return job + "_" + growType;
   }

   public void lvTo28(IoSession session, int job, int growType) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      role.setGrowtype(growType);
      role.setExp(4416735);
      role.setLevel(28);
      role.setQindex(500110);
      role.setEquipscore(2200);
      AdventureReapInfo adventureReapInfo = account.getAdventureReapInfo();
      adventureReapInfo.setStarttime(TimeUtil.currS());
      account.setAdventureReapInfo(adventureReapInfo);
      AdventureUnionInfo adventureUnionInfo = account.getAdventureUnionInfo();
      adventureUnionInfo.exp = 1324L;
      adventureUnionInfo.level = 1;
      adventureUnionInfo.day = 1;
      adventureUnionInfo.isadventureCondition = true;
      account.setAdventureUnionInfo(adventureUnionInfo);
      String advBookBoxStr = "{\"adventurebooks\": [{\"bindex\": 10039}, {\"bindex\": 10040}, {\"bindex\": 10041}, {\"bindex\": 1001201, \"bstate\": 3}, {\"bindex\": 1100101, \"bstate\": 3}], \"openconditions\": [{\"cindex\": 100, \"cstate\": 1}, {\"cindex\": 101, \"cstate\": 1}, {\"cindex\": 102, \"cstate\": 1}]}";
      AdvBookBox advBookBox = (AdvBookBox)JSON.parseObject(advBookBoxStr, AdvBookBox.class);
      account.setAdvBookBox(advBookBox);
      String key = this.key(job, growType);
      ClearDungeonBox clearDungeonBox = (ClearDungeonBox)DataCache.lv28ClearDungeonBox.get(key);
      if (clearDungeonBox == null) {
         this.logger.error("ERROR==clearDungeonBox is null, key={}", key);
      } else {
         role.setClearDungeonBox(clearDungeonBox);
      }

      SkillBox skillBox = (SkillBox)DataCache.lv28SkillBox.get(key);
      if (skillBox == null) {
         this.logger.error("ERROR==skillBox is null, key={}", key);
      } else {
         role.setSkillBox(skillBox);
      }

      SkillslotBox skillslotBox = (SkillslotBox)DataCache.lv28SkillslotBox.get(key);
      if (skillslotBox == null) {
         this.logger.error("ERROR==skillslotBox is null, key={}", key);
      } else {
         role.setSkillslotBox(skillslotBox);
      }

      account.save();
   }

   public void lvTo55(IoSession session, int job, int growtype) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      role.setGrowtype(growtype);
      role.setExp(107836272);
      role.setLevel(55);
      role.setQindex(1100556);
      role.setEquipscore(4192);
      long starttime = account.getAdventureReapInfo().getStarttime();
      String adventureReapInfoStr = "{\"starttime\": \"" + starttime + "\", \"rewards\": [{\"itemindex\": 2013100883, \"count\": 48}, {\"itemindex\": 2013102924, \"count\": 12}, {\"itemindex\": 2013106066, \"count\": 5}, {\"itemindex\": 2013102100, \"count\": 3}, {\"itemindex\": 2013101275, \"count\": 1}, {\"itemindex\": 2013101305, \"count\": 2}, {\"itemindex\": 2013106178, \"count\": 1}, {\"itemindex\": 2013103867, \"count\": 1}, {\"itemindex\": 2013106097, \"count\": 1}]}";
      AdventureReapInfo adventureReapInfo = (AdventureReapInfo)JSON.parseObject(adventureReapInfoStr, AdventureReapInfo.class);
      account.setAdventureReapInfo(adventureReapInfo);
      AdventureUnionInfo adventureUnionInfo = account.getAdventureUnionInfo();
      adventureUnionInfo.level = 8;
      adventureUnionInfo.exp = 32344L;
      adventureUnionInfo.day = 1;
      adventureUnionInfo.collections = new ArrayList();
      PT_ADVENTURE_UNION_COLLECTION pt_adventure_union_collection = new PT_ADVENTURE_UNION_COLLECTION();
      pt_adventure_union_collection.job = job;
      pt_adventure_union_collection.growtype = growtype;
      pt_adventure_union_collection.equipscore = 4192;
      adventureUnionInfo.collections.add(pt_adventure_union_collection);
      adventureUnionInfo.isadventureCondition = true;
      account.setAdventureUnionInfo(adventureUnionInfo);
      String advBookBoxStr = "{\"adventurebooks\": [{\"bindex\": 10039}, {\"bindex\": 10040}, {\"bindex\": 10041, \"bstate\": 3}, {\"bindex\": 1001201, \"bstate\": 3}, {\"bindex\": 1100101, \"bstate\": 3}, {\"bindex\": 10034, \"bstate\": 3}, {\"bindex\": 10035, \"bstate\": 3}, {\"bindex\": 1000602, \"bstate\": 3}, {\"bindex\": 2006301, \"bstate\": 3}, {\"bindex\": 10062}, {\"bindex\": 11059}, {\"bindex\": 11060}, {\"bindex\": 11061}, {\"bindex\": 11062}, {\"bindex\": 11057}, {\"bindex\": 11058}, {\"bindex\": 20098}, {\"bindex\": 20099}, {\"bindex\": 20100}, {\"bindex\": 20101}, {\"bindex\": 20102}, {\"bindex\": 20103}, {\"bindex\": 20104}, {\"bindex\": 20105}, {\"bindex\": 20106}, {\"bindex\": 20107}, {\"bindex\": 20108}, {\"bindex\": 20109}, {\"bindex\": 20110}, {\"bindex\": 20111}, {\"bindex\": 20112}, {\"bindex\": 20113}, {\"bindex\": 20114}, {\"bindex\": 20115}, {\"bindex\": 20116}, {\"bindex\": 20117}], \"openconditions\": [{\"cindex\": 100, \"cstate\": 1}, {\"cindex\": 101, \"cstate\": 1}, {\"cindex\": 102, \"cstate\": 2}, {\"cindex\": 103, \"cstate\": 1}, {\"cindex\": 104, \"cstate\": 1}, {\"cindex\": 105, \"cstate\": 1}, {\"cindex\": 106, \"cstate\": 1}, {\"cindex\": 107, \"cstate\": 1}, {\"cindex\": 113, \"cstate\": 1}, {\"cindex\": 114, \"cstate\": 1}, {\"cindex\": 115, \"cstate\": 1}, {\"cindex\": 116, \"cstate\": 1}, {\"cindex\": 117, \"cstate\": 1}, {\"cindex\": 118, \"cstate\": 1}, {\"cindex\": 119, \"cstate\": 1}, {\"cindex\": 120, \"cstate\": 1}, {\"cindex\": 121, \"cstate\": 1}, {\"cindex\": 122, \"cstate\": 1}, {\"cindex\": 123, \"cstate\": 1}, {\"cindex\": 124, \"cstate\": 1}, {\"cindex\": 125, \"cstate\": 1}, {\"cindex\": 126, \"cstate\": 1}, {\"cindex\": 127, \"cstate\": 1}, {\"cindex\": 128, \"cstate\": 1}, {\"cindex\": 129, \"cstate\": 1}, {\"cindex\": 130, \"cstate\": 1}, {\"cindex\": 131, \"cstate\": 1}, {\"cindex\": 132, \"cstate\": 1}, {\"cindex\": 133, \"cstate\": 1}, {\"cindex\": 134, \"cstate\": 1}]}";
      AdvBookBox advBookBox = (AdvBookBox)JSON.parseObject(advBookBoxStr, AdvBookBox.class);
      account.setAdvBookBox(advBookBox);
      QuestInfoBox questInfoBox = new QuestInfoBox();
      ClearDungeonBox clearDungeonBox = role.getClearDungeonBox();
      if (clearDungeonBox == null) {
         new ClearDungeonBox();
      }

      EquippedBox equippedBox = role.getEquippedBox();
      equippedBox.removeAllEquip();
      if (role.getJob() == 0 && role.getGrowtype() == 1) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90101, 2);
         questInfoBox.addQuest(90117, 2);
         questInfoBox.addQuest(90141, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
         String skillListStr = "{\"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 64, \"level\": 6}, {\"index\": 98, \"level\": 5}, {\"index\": 68, \"level\": 7}, {\"index\": 72, \"level\": 2}, {\"index\": 8, \"level\": 17}, {\"index\": 110, \"level\": 7}, {\"index\": 105, \"level\": 1}, {\"index\": 109, \"level\": 12}, {\"index\": 58, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 108, \"level\": 15}, {\"index\": 65, \"level\": 1}, {\"index\": 94, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 107, \"level\": 17}, {\"index\": 49, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 67, \"level\": 1}, {\"index\": 9, \"level\": 17}, {\"index\": 38, \"level\": 7}, {\"index\": 186, \"level\": 10}, {\"index\": 33, \"level\": 10}, {\"index\": 169, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 67, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 72, \"slot\": 8}, {\"index\": 98, \"slot\": 9}, {\"index\": 68, \"slot\": 10}, {\"index\": 109, \"slot\": 11}], \"buff\": [{\"index\": 38}]}}";
         String skillListPkStr = "{\"type\": 1, \"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 94, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 64, \"level\": 14}, {\"index\": 98, \"level\": 5}, {\"index\": 174, \"level\": 1}, {\"index\": 68, \"level\": 7}, {\"index\": 72, \"level\": 2}, {\"index\": 8, \"level\": 1}, {\"index\": 110, \"level\": 7}, {\"index\": 105, \"level\": 1}, {\"index\": 109, \"level\": 12}, {\"index\": 3, \"level\": 8}, {\"index\": 20, \"level\": 1}, {\"index\": 1, \"level\": 1}, {\"index\": 107, \"level\": 1}, {\"index\": 49, \"level\": 1}, {\"index\": 108, \"level\": 15}, {\"index\": 58, \"level\": 10}, {\"index\": 5, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 67, \"level\": 1}, {\"index\": 9, \"level\": 17}, {\"index\": 38, \"level\": 7}, {\"index\": 16, \"level\": 2}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 33, \"level\": 10}, {\"index\": 169, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 67, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 72, \"slot\": 8}, {\"index\": 98, \"slot\": 9}, {\"index\": 68, \"slot\": 10}, {\"index\": 109, \"slot\": 11}], \"buff\": [{\"index\": 38}, {\"index\": 3, \"slot\": 1}]}}";
         RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillListStr, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList, skillListPk));
         role.setSkillslotBox(this.getSkillslotBox(skillList, skillListPk));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000070118, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000120118, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000170109, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000220110, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000270115, 16));
      } else if (role.getJob() == 0 && role.getGrowtype() == 2) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90101, 2);
         questInfoBox.addQuest(90105, 2);
         questInfoBox.addQuest(90123, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
         String skillListStr2 = "{\"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 188, \"level\": 10}, {\"index\": 18, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 8, \"level\": 1}, {\"index\": 25, \"level\": 10}, {\"index\": 41, \"level\": 10}, {\"index\": 77, \"level\": 17}, {\"index\": 280, \"level\": 10}, {\"index\": 169, \"level\": 1}, {\"index\": 111, \"level\": 12}, {\"index\": 6, \"level\": 1}, {\"index\": 93, \"level\": 1}, {\"index\": 35, \"level\": 5}, {\"index\": 36, \"level\": 7}, {\"index\": 65, \"level\": 1}, {\"index\": 58, \"level\": 17}, {\"index\": 29, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 5, \"level\": 22}, {\"index\": 17, \"level\": 1}, {\"index\": 75, \"level\": 2}, {\"index\": 46, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 263, \"level\": 2}, {\"index\": 60, \"level\": 5}, {\"index\": 44, \"level\": 17}, {\"index\": 334, \"level\": 1}, {\"index\": 20, \"level\": 3}, {\"index\": 190, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 25, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 263, \"slot\": 8}, {\"index\": 75, \"slot\": 9}, {\"index\": 60, \"slot\": 10}, {\"index\": 36, \"slot\": 11}]}}";
         String skillListPkStr2 = "{\"type\": 1, \"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 1, \"level\": 1}, {\"index\": 18, \"level\": 1}, {\"index\": 41, \"level\": 10}, {\"index\": 29, \"level\": 1}, {\"index\": 188, \"level\": 10}, {\"index\": 17, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 75, \"level\": 2}, {\"index\": 8, \"level\": 1}, {\"index\": 25, \"level\": 10}, {\"index\": 64, \"level\": 17}, {\"index\": 11, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 93, \"level\": 1}, {\"index\": 35, \"level\": 5}, {\"index\": 111, \"level\": 12}, {\"index\": 58, \"level\": 17}, {\"index\": 5, \"level\": 2}, {\"index\": 280, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 36, \"level\": 7}, {\"index\": 65, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 77, \"level\": 10}, {\"index\": 350, \"level\": 1}, {\"index\": 263, \"level\": 2}, {\"index\": 60, \"level\": 5}, {\"index\": 44, \"level\": 17}, {\"index\": 16, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 20, \"level\": 17}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 25, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 263, \"slot\": 8}, {\"index\": 75, \"slot\": 9}, {\"index\": 60, \"slot\": 10}, {\"index\": 36, \"slot\": 11}]}}";
         RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillListStr2, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr2, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList2, skillListPk2));
         role.setSkillslotBox(this.getSkillslotBox(skillList2, skillListPk2));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000050126, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000100127, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000150120, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000200114, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000250119, 16));
      } else if (role.getJob() == 0 && role.getGrowtype() == 3) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90101, 2);
         questInfoBox.addQuest(90109, 2);
         questInfoBox.addQuest(90129, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr3 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr3, ClearDungeonBox.class);
         String skillListStr3 = "{\"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 186, \"level\": 10}, {\"index\": 8, \"level\": 1}, {\"index\": 76, \"level\": 1}, {\"index\": 64, \"level\": 17}, {\"index\": 79, \"level\": 7}, {\"index\": 23, \"level\": 15}, {\"index\": 40, \"level\": 7}, {\"index\": 46, \"level\": 1}, {\"index\": 278, \"level\": 5}, {\"index\": 63, \"level\": 1}, {\"index\": 5, \"level\": 2}, {\"index\": 34, \"level\": 10}, {\"index\": 56, \"level\": 1}, {\"index\": 19, \"level\": 10}, {\"index\": 58, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 65, \"level\": 9}, {\"index\": 350, \"level\": 1}, {\"index\": 31, \"level\": 12}, {\"index\": 112, \"level\": 17}, {\"index\": 24, \"level\": 17}, {\"index\": 169, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 103, \"level\": 2}, {\"index\": 20, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 103, \"slot\": 6}, {\"index\": 278, \"slot\": 8}, {\"index\": 79, \"slot\": 9}, {\"index\": 31, \"slot\": 10}, {\"index\": 24, \"slot\": 11}], \"buff\": [{\"index\": 40}, {\"index\": 23, \"slot\": 1}, {\"index\": 34, \"slot\": 2}]}}";
         String skillListPkStr3 = "{\"type\": 1, \"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 1, \"level\": 1}, {\"index\": 8, \"level\": 1}, {\"index\": 79, \"level\": 7}, {\"index\": 76, \"level\": 1}, {\"index\": 23, \"level\": 15}, {\"index\": 11, \"level\": 1}, {\"index\": 64, \"level\": 13}, {\"index\": 40, \"level\": 7}, {\"index\": 19, \"level\": 10}, {\"index\": 65, \"level\": 10}, {\"index\": 56, \"level\": 1}, {\"index\": 5, \"level\": 2}, {\"index\": 58, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 34, \"level\": 10}, {\"index\": 169, \"level\": 1}, {\"index\": 63, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 278, \"level\": 5}, {\"index\": 46, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 31, \"level\": 12}, {\"index\": 334, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 103, \"level\": 2}, {\"index\": 112, \"level\": 17}, {\"index\": 24, \"level\": 17}, {\"index\": 186, \"level\": 10}, {\"index\": 20, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 103, \"slot\": 6}, {\"index\": 278, \"slot\": 8}, {\"index\": 79, \"slot\": 9}, {\"index\": 31, \"slot\": 10}, {\"index\": 24, \"slot\": 11}], \"buff\": [{\"index\": 40}, {\"index\": 23, \"slot\": 1}, {\"index\": 34, \"slot\": 2}]}}";
         RES_SKILL_LIST skillList3 = (RES_SKILL_LIST)JSON.parseObject(skillListStr3, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk3 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr3, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList3, skillListPk3));
         role.setSkillslotBox(this.getSkillslotBox(skillList3, skillListPk3));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000080116, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000130116, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000180109, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000230108, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000280114, 16));
      } else if (role.getJob() == 0 && role.getGrowtype() == 4) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90101, 2);
         questInfoBox.addQuest(90113, 2);
         questInfoBox.addQuest(90135, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr4 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr4, ClearDungeonBox.class);
         String skillListStr4 = "{\"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 188, \"level\": 10}, {\"index\": 52, \"level\": 7}, {\"index\": 62, \"level\": 5}, {\"index\": 57, \"level\": 2}, {\"index\": 5, \"level\": 2}, {\"index\": 51, \"level\": 1}, {\"index\": 22, \"level\": 17}, {\"index\": 169, \"level\": 1}, {\"index\": 53, \"level\": 7}, {\"index\": 47, \"level\": 10}, {\"index\": 65, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 58, \"level\": 11}, {\"index\": 46, \"level\": 1}, {\"index\": 61, \"level\": 1}, {\"index\": 32, \"level\": 10}, {\"index\": 2, \"level\": 12}, {\"index\": 350, \"level\": 1}, {\"index\": 20, \"level\": 14}, {\"index\": 339, \"level\": 1}, {\"index\": 21, \"level\": 7}, {\"index\": 50, \"level\": 15}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 55, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 57, \"slot\": 6}, {\"index\": 62, \"slot\": 8}, {\"index\": 21, \"slot\": 9}, {\"index\": 32, \"slot\": 10}, {\"index\": 2, \"slot\": 11}], \"buff\": [{\"index\": 52}]}}";
         String skillListPkStr4 = "{\"type\": 1, \"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 1, \"level\": 1}, {\"index\": 52, \"level\": 7}, {\"index\": 188, \"level\": 10}, {\"index\": 62, \"level\": 5}, {\"index\": 11, \"level\": 1}, {\"index\": 64, \"level\": 1}, {\"index\": 47, \"level\": 10}, {\"index\": 61, \"level\": 1}, {\"index\": 8, \"level\": 1}, {\"index\": 57, \"level\": 2}, {\"index\": 22, \"level\": 17}, {\"index\": 51, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 53, \"level\": 7}, {\"index\": 65, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 17}, {\"index\": 17, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 32, \"level\": 10}, {\"index\": 350, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 2, \"level\": 12}, {\"index\": 50, \"level\": 15}, {\"index\": 339, \"level\": 1}, {\"index\": 21, \"level\": 7}, {\"index\": 16, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 20, \"level\": 3}, {\"index\": 190, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 46, \"slot\": 1}, {\"index\": 5, \"slot\": 2}, {\"index\": 58, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 20, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 57, \"slot\": 6}, {\"index\": 62, \"slot\": 8}, {\"index\": 21, \"slot\": 9}, {\"index\": 32, \"slot\": 10}, {\"index\": 2, \"slot\": 11}], \"buff\": [{\"index\": 52}]}}";
         RES_SKILL_LIST skillList4 = (RES_SKILL_LIST)JSON.parseObject(skillListStr4, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk4 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr4, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList4, skillListPk4));
         role.setSkillslotBox(this.getSkillslotBox(skillList4, skillListPk4));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000090090, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000140090, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000190087, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000240087, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000290090, 16));
      } else if (role.getJob() == 1 && role.getGrowtype() == 1) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90201, 2);
         questInfoBox.addQuest(90205, 2);
         questInfoBox.addQuest(90217, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
         String skillListStr = "{\"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 337, \"level\": 8}, {\"index\": 42, \"level\": 12}, {\"index\": 188, \"level\": 10}, {\"index\": 35, \"level\": 1}, {\"index\": 86, \"level\": 1}, {\"index\": 346, \"level\": 12}, {\"index\": 11, \"level\": 1}, {\"index\": 40, \"level\": 12}, {\"index\": 69, \"level\": 10}, {\"index\": 30, \"level\": 7}, {\"index\": 97, \"level\": 1}, {\"index\": 341, \"level\": 1}, {\"index\": 109, \"level\": 15}, {\"index\": 9, \"level\": 1}, {\"index\": 29, \"level\": 10}, {\"index\": 174, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 46, \"level\": 2}, {\"index\": 12, \"level\": 17}, {\"index\": 5, \"level\": 1}, {\"index\": 339, \"level\": 7}, {\"index\": 78, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 15, \"level\": 5}, {\"index\": 79, \"level\": 1}, {\"index\": 16, \"level\": 2}, {\"index\": 190, \"level\": 1}, {\"index\": 169, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 5, \"slot\": 1}, {\"index\": 46, \"slot\": 2}, {\"index\": 9, \"slot\": 3}, {\"index\": 86, \"slot\": 4}, {\"index\": 12, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 16, \"slot\": 6}, {\"index\": 15, \"slot\": 8}, {\"index\": 339, \"slot\": 9}, {\"index\": 69, \"slot\": 10}, {\"index\": 42, \"slot\": 11}], \"buff\": [{\"index\": 30}, {\"index\": 40, \"slot\": 1}]}}";
         String skillListPkStr = "{\"type\": 1, \"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 7, \"level\": 12}, {\"index\": 350, \"level\": 1}, {\"index\": 2, \"level\": 1}, {\"index\": 337, \"level\": 1}, {\"index\": 42, \"level\": 12}, {\"index\": 341, \"level\": 1}, {\"index\": 109, \"level\": 1}, {\"index\": 346, \"level\": 12}, {\"index\": 11, \"level\": 1}, {\"index\": 40, \"level\": 12}, {\"index\": 16, \"level\": 2}, {\"index\": 334, \"level\": 1}, {\"index\": 69, \"level\": 10}, {\"index\": 30, \"level\": 7}, {\"index\": 29, \"level\": 10}, {\"index\": 188, \"level\": 5}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 15, \"level\": 5}, {\"index\": 174, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 35, \"level\": 1}, {\"index\": 86, \"level\": 1}, {\"index\": 9, \"level\": 20}, {\"index\": 97, \"level\": 1}, {\"index\": 12, \"level\": 17}, {\"index\": 339, \"level\": 7}, {\"index\": 78, \"level\": 1}, {\"index\": 79, \"level\": 1}, {\"index\": 84, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 169, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 5, \"slot\": 1}, {\"index\": 46, \"slot\": 2}, {\"index\": 9, \"slot\": 3}, {\"index\": 86, \"slot\": 4}, {\"index\": 12, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 16, \"slot\": 6}, {\"index\": 15, \"slot\": 8}, {\"index\": 339, \"slot\": 9}, {\"index\": 69, \"slot\": 10}, {\"index\": 42, \"slot\": 11}], \"buff\": [{\"index\": 30}, {\"index\": 40, \"slot\": 1}]}}";
         RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillListStr, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList, skillListPk));
         role.setSkillslotBox(this.getSkillslotBox(skillList, skillListPk));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000050126, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000100127, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000150120, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000200114, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000250119, 16));
      } else if (role.getJob() == 1 && role.getGrowtype() == 2) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90201, 2);
         questInfoBox.addQuest(90209, 2);
         questInfoBox.addQuest(90218, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]},{\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
         String skillListStr2 = "{\"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 6, \"level\": 20}, {\"index\": 1, \"level\": 15}, {\"index\": 83, \"level\": 2}, {\"index\": 19, \"level\": 5}, {\"index\": 342, \"level\": 1}, {\"index\": 80, \"level\": 2}, {\"index\": 20, \"level\": 10}, {\"index\": 37, \"level\": 7}, {\"index\": 17, \"level\": 1}, {\"index\": 336, \"level\": 1}, {\"index\": 46, \"level\": 2}, {\"index\": 4, \"level\": 10}, {\"index\": 91, \"level\": 5}, {\"index\": 28, \"level\": 7}, {\"index\": 86, \"level\": 17}, {\"index\": 9, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 12, \"level\": 1}, {\"index\": 5, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 39, \"level\": 8}, {\"index\": 68, \"level\": 17}, {\"index\": 56, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 174, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 5, \"slot\": 1}, {\"index\": 46, \"slot\": 2}, {\"index\": 9, \"slot\": 3}, {\"index\": 86, \"slot\": 4}, {\"index\": 12, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 83, \"slot\": 6}, {\"index\": 19, \"slot\": 8}, {\"index\": 28, \"slot\": 9}, {\"index\": 4, \"slot\": 10}, {\"index\": 1, \"slot\": 11}], \"buff\": [{\"index\": 37}, {\"index\": 20, \"slot\": 1}]}}";
         String skillListPkStr2 = "{\"type\": 1, \"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 7, \"level\": 11}, {\"index\": 20, \"level\": 1}, {\"index\": 37, \"level\": 7}, {\"index\": 190, \"level\": 1}, {\"index\": 84, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 1, \"level\": 15}, {\"index\": 83, \"level\": 2}, {\"index\": 19, \"level\": 5}, {\"index\": 342, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 80, \"level\": 12}, {\"index\": 186, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 46, \"level\": 1}, {\"index\": 336, \"level\": 1}, {\"index\": 4, \"level\": 10}, {\"index\": 91, \"level\": 5}, {\"index\": 5, \"level\": 1}, {\"index\": 58, \"level\": 1}, {\"index\": 68, \"level\": 17}, {\"index\": 174, \"level\": 1}, {\"index\": 39, \"level\": 6}, {\"index\": 34, \"level\": 1}, {\"index\": 28, \"level\": 7}, {\"index\": 86, \"level\": 17}, {\"index\": 9, \"level\": 20}, {\"index\": 56, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 334, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 5, \"slot\": 1}, {\"index\": 46, \"slot\": 2}, {\"index\": 9, \"slot\": 3}, {\"index\": 86, \"slot\": 4}, {\"index\": 12, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 83, \"slot\": 6}, {\"index\": 19, \"slot\": 8}, {\"index\": 28, \"slot\": 9}, {\"index\": 4, \"slot\": 10}, {\"index\": 58, \"slot\": 11}], \"buff\": [{\"index\": 37}, {\"index\": 20, \"slot\": 1}]}}";
         RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillListStr2, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr2, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList2, skillListPk2));
         role.setSkillslotBox(this.getSkillslotBox(skillList2, skillListPk2));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000070118, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000120118, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000170109, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000220110, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000270115, 16));
      } else if (role.getJob() == 2 && role.getGrowtype() == 1) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90301, 2);
         questInfoBox.addQuest(90305, 2);
         questInfoBox.addQuest(90317, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
         String skillListStr = "{\"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 199, \"level\": 1}, {\"index\": 257, \"level\": 7}, {\"index\": 9, \"level\": 17}, {\"index\": 132, \"level\": 9}, {\"index\": 26, \"level\": 1}, {\"index\": 47, \"level\": 1}, {\"index\": 30, \"level\": 13}, {\"index\": 13, \"level\": 1}, {\"index\": 14, \"level\": 1}, {\"index\": 72, \"level\": 2}, {\"index\": 20, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 3, \"level\": 17}, {\"index\": 22, \"level\": 1}, {\"index\": 271, \"level\": 10}, {\"index\": 6, \"level\": 1}, {\"index\": 35, \"level\": 1}, {\"index\": 60, \"level\": 7}, {\"index\": 7, \"level\": 10}, {\"index\": 27, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 12, \"level\": 1}, {\"index\": 111, \"level\": 10}, {\"index\": 5, \"level\": 15}, {\"index\": 34, \"level\": 1}, {\"index\": 10, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 15, \"level\": 5}, {\"index\": 11, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 4, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 4, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 6, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 13, \"slot\": 5}, {\"index\": 27, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 72, \"slot\": 8}, {\"index\": 15, \"slot\": 9}, {\"index\": 257, \"slot\": 10}, {\"index\": 271, \"slot\": 11}], \"buff\": [{\"index\": 60}, {\"index\": 30, \"slot\": 1}]}}";
         String skillListPkStr = "{\"type\": 1, \"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 33, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 20, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 257, \"level\": 7}, {\"index\": 9, \"level\": 17}, {\"index\": 132, \"level\": 1}, {\"index\": 26, \"level\": 1}, {\"index\": 47, \"level\": 1}, {\"index\": 30, \"level\": 13}, {\"index\": 13, \"level\": 1}, {\"index\": 14, \"level\": 1}, {\"index\": 72, \"level\": 2}, {\"index\": 22, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 56, \"level\": 1}, {\"index\": 3, \"level\": 16}, {\"index\": 35, \"level\": 1}, {\"index\": 271, \"level\": 1}, {\"index\": 6, \"level\": 20}, {\"index\": 60, \"level\": 7}, {\"index\": 7, \"level\": 17}, {\"index\": 27, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 12, \"level\": 1}, {\"index\": 111, \"level\": 10}, {\"index\": 5, \"level\": 15}, {\"index\": 34, \"level\": 1}, {\"index\": 10, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 15, \"level\": 5}, {\"index\": 11, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 55, \"level\": 1}, {\"index\": 4, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 4, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 6, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 13, \"slot\": 5}, {\"index\": 27, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 72, \"slot\": 8}, {\"index\": 15, \"slot\": 9}, {\"index\": 257, \"slot\": 10}, {\"index\": 271, \"slot\": 11}], \"buff\": [{\"index\": 60}, {\"index\": 33, \"slot\": 1}, {\"index\": 30, \"slot\": 2}]}}";
         RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillListStr, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList, skillListPk));
         role.setSkillslotBox(this.getSkillslotBox(skillList, skillListPk));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000060120, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000110119, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000160112, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000210112, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000260117, 16));
      } else if (role.getJob() == 2 && role.getGrowtype() == 2) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90301, 2);
         questInfoBox.addQuest(90309, 2);
         questInfoBox.addQuest(90323, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
         String skillListStr2 = "{\"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 334, \"level\": 1}, {\"index\": 73, \"level\": 17}, {\"index\": 335, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 45, \"level\": 17}, {\"index\": 199, \"level\": 1}, {\"index\": 40, \"level\": 15}, {\"index\": 6, \"level\": 1}, {\"index\": 75, \"level\": 5}, {\"index\": 36, \"level\": 12}, {\"index\": 7, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 39, \"level\": 10}, {\"index\": 92, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 275, \"level\": 6}, {\"index\": 14, \"level\": 1}, {\"index\": 13, \"level\": 11}, {\"index\": 273, \"level\": 2}, {\"index\": 41, \"level\": 1}, {\"index\": 12, \"level\": 2}, {\"index\": 27, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 32, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 4, \"level\": 1}, {\"index\": 62, \"level\": 7}, {\"index\": 38, \"level\": 7}, {\"index\": 270, \"level\": 1}, {\"index\": 21, \"level\": 10}, {\"index\": 174, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 4, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 6, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 13, \"slot\": 5}, {\"index\": 27, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 273, \"slot\": 8}, {\"index\": 75, \"slot\": 9}, {\"index\": 38, \"slot\": 10}, {\"index\": 39, \"slot\": 11}], \"buff\": [{\"index\": 62}]}}";
         String skillListPkStr2 = "{\"type\": 1, \"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 33, \"level\": 1}, {\"index\": 75, \"level\": 5}, {\"index\": 12, \"level\": 1}, {\"index\": 41, \"level\": 1}, {\"index\": 30, \"level\": 13}, {\"index\": 47, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 40, \"level\": 15}, {\"index\": 34, \"level\": 1}, {\"index\": 39, \"level\": 10}, {\"index\": 92, \"level\": 1}, {\"index\": 14, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 275, \"level\": 1}, {\"index\": 38, \"level\": 7}, {\"index\": 32, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 3, \"level\": 10}, {\"index\": 56, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 27, \"level\": 1}, {\"index\": 273, \"level\": 2}, {\"index\": 13, \"level\": 1}, {\"index\": 6, \"level\": 12}, {\"index\": 36, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 45, \"level\": 17}, {\"index\": 190, \"level\": 1}, {\"index\": 335, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 73, \"level\": 17}, {\"index\": 4, \"level\": 1}, {\"index\": 62, \"level\": 7}, {\"index\": 21, \"level\": 10}, {\"index\": 174, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 4, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 6, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 13, \"slot\": 5}, {\"index\": 27, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 273, \"slot\": 8}, {\"index\": 75, \"slot\": 9}, {\"index\": 38, \"slot\": 10}, {\"index\": 39, \"slot\": 11}], \"buff\": [{\"index\": 62}, {\"index\": 33, \"slot\": 1}, {\"index\": 30, \"slot\": 2}]}}";
         RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillListStr2, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr2, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList2, skillListPk2));
         role.setSkillslotBox(this.getSkillslotBox(skillList2, skillListPk2));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000080116, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000130116, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000180109, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000230108, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000280114, 16));
      } else if (role.getJob() == 2 && role.getGrowtype() == 3) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90301, 2);
         questInfoBox.addQuest(90327, 2);
         questInfoBox.addQuest(90337, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr3 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr3, ClearDungeonBox.class);
         String skillListStr3 = "{\"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 50, \"level\": 1}, {\"index\": 46, \"level\": 7}, {\"index\": 43, \"level\": 1}, {\"index\": 14, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 59, \"level\": 2}, {\"index\": 292, \"level\": 1}, {\"index\": 27, \"level\": 14}, {\"index\": 56, \"level\": 10}, {\"index\": 288, \"level\": 10}, {\"index\": 4, \"level\": 1}, {\"index\": 265, \"level\": 10}, {\"index\": 91, \"level\": 1}, {\"index\": 28, \"level\": 15}, {\"index\": 293, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 264, \"level\": 7}, {\"index\": 13, \"level\": 1}, {\"index\": 42, \"level\": 8}, {\"index\": 7, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 63, \"level\": 17}, {\"index\": 34, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 188, \"level\": 10}, {\"index\": 29, \"level\": 12}, {\"index\": 290, \"level\": 5}, {\"index\": 174, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 4, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 6, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 13, \"slot\": 5}, {\"index\": 27, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 59, \"slot\": 8}, {\"index\": 290, \"slot\": 9}, {\"index\": 264, \"slot\": 10}, {\"index\": 288, \"slot\": 11}], \"buff\": [{\"index\": 46}, {\"index\": 43, \"slot\": 1}]}}";
         String skillListPkStr3 = "{\"type\": 1, \"sp\": 2130, \"tp\": 6, \"skilllist\": [{\"index\": 37, \"level\": 1}, {\"index\": 50, \"level\": 1}, {\"index\": 91, \"level\": 1}, {\"index\": 265, \"level\": 10}, {\"index\": 4, \"level\": 1}, {\"index\": 33, \"level\": 1}, {\"index\": 47, \"level\": 1}, {\"index\": 264, \"level\": 7}, {\"index\": 293, \"level\": 1}, {\"index\": 28, \"level\": 15}, {\"index\": 59, \"level\": 2}, {\"index\": 6, \"level\": 1}, {\"index\": 30, \"level\": 1}, {\"index\": 46, \"level\": 7}, {\"index\": 14, \"level\": 1}, {\"index\": 29, \"level\": 7}, {\"index\": 188, \"level\": 10}, {\"index\": 43, \"level\": 10}, {\"index\": 56, \"level\": 1}, {\"index\": 3, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 42, \"level\": 8}, {\"index\": 13, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 63, \"level\": 17}, {\"index\": 34, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 27, \"level\": 18}, {\"index\": 292, \"level\": 1}, {\"index\": 288, \"level\": 10}, {\"index\": 334, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 290, \"level\": 5}, {\"index\": 174, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 4, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 6, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 13, \"slot\": 5}, {\"index\": 27, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 59, \"slot\": 8}, {\"index\": 290, \"slot\": 9}, {\"index\": 264, \"slot\": 10}, {\"index\": 288, \"slot\": 11}], \"buff\": [{\"index\": 46}, {\"index\": 43, \"slot\": 1}, {\"index\": 33, \"slot\": 2}, {\"index\": 30, \"slot\": 3}]}}";
         RES_SKILL_LIST skillList3 = (RES_SKILL_LIST)JSON.parseObject(skillListStr3, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk3 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr3, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList3, skillListPk3));
         role.setSkillslotBox(this.getSkillslotBox(skillList3, skillListPk3));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000050126, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000100127, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000150120, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000200114, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000250119, 16));
      } else if (role.getJob() == 2 && role.getGrowtype() == 4) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90301, 2);
         questInfoBox.addQuest(90331, 2);
         questInfoBox.addQuest(90343, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr4 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr4, ClearDungeonBox.class);
         String skillListStr4 = "{\"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 169, \"level\": 1}, {\"index\": 53, \"level\": 17}, {\"index\": 27, \"level\": 1}, {\"index\": 56, \"level\": 1}, {\"index\": 109, \"level\": 7}, {\"index\": 279, \"level\": 2}, {\"index\": 57, \"level\": 17}, {\"index\": 4, \"level\": 1}, {\"index\": 77, \"level\": 5}, {\"index\": 260, \"level\": 8}, {\"index\": 48, \"level\": 10}, {\"index\": 52, \"level\": 8}, {\"index\": 190, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 277, \"level\": 1}, {\"index\": 30, \"level\": 13}, {\"index\": 1, \"level\": 7}, {\"index\": 13, \"level\": 1}, {\"index\": 61, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 267, \"level\": 7}, {\"index\": 2, \"level\": 10}, {\"index\": 199, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 112, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 58, \"level\": 7}, {\"index\": 259, \"level\": 10}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}], \"buff\": [{\"index\": 30}, {\"index\": 30, \"slot\": 5}, {\"index\": 277, \"slot\": 1}, {\"index\": 277, \"slot\": 6}]}}";
         String skillListPkStr4 = "{\"type\": 1, \"sp\": 2130, \"tp\": 6, \"skilllist\": [{\"index\": 37, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 61, \"level\": 1}, {\"index\": 53, \"level\": 17}, {\"index\": 56, \"level\": 1}, {\"index\": 109, \"level\": 1}, {\"index\": 3, \"level\": 1}, {\"index\": 14, \"level\": 1}, {\"index\": 279, \"level\": 1}, {\"index\": 77, \"level\": 5}, {\"index\": 260, \"level\": 8}, {\"index\": 48, \"level\": 10}, {\"index\": 4, \"level\": 1}, {\"index\": 57, \"level\": 17}, {\"index\": 33, \"level\": 1}, {\"index\": 52, \"level\": 7}, {\"index\": 12, \"level\": 1}, {\"index\": 277, \"level\": 1}, {\"index\": 1, \"level\": 7}, {\"index\": 30, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 32, \"level\": 1}, {\"index\": 267, \"level\": 15}, {\"index\": 2, \"level\": 10}, {\"index\": 112, \"level\": 1}, {\"index\": 6, \"level\": 1}, {\"index\": 199, \"level\": 1}, {\"index\": 34, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 259, \"level\": 10}, {\"index\": 47, \"level\": 1}, {\"index\": 186, \"level\": 10}, {\"index\": 27, \"level\": 1}, {\"index\": 169, \"level\": 1}, {\"index\": 58, \"level\": 7}, {\"index\": 174, \"level\": 1}, {\"index\": 7, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 334, \"slot\": 20}, {\"index\": 4, \"slot\": 1}, {\"index\": 4, \"slot\": 21}, {\"index\": 7, \"slot\": 2}, {\"index\": 7, \"slot\": 22}, {\"index\": 6, \"slot\": 3}, {\"index\": 6, \"slot\": 23}, {\"index\": 12, \"slot\": 4}, {\"index\": 12, \"slot\": 24}, {\"index\": 13, \"slot\": 5}, {\"index\": 13, \"slot\": 25}, {\"index\": 27, \"slot\": 6}, {\"index\": 27, \"slot\": 26}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10001, \"slot\": 38}, {\"index\": 10002, \"slot\": 17}, {\"index\": 10002, \"slot\": 37}, {\"index\": 169, \"slot\": 7}], \"buff\": [{\"index\": 30}, {\"index\": 30, \"slot\": 5}, {\"index\": 277, \"slot\": 1}, {\"index\": 277, \"slot\": 6}]}}";
         RES_SKILL_LIST skillList4 = (RES_SKILL_LIST)JSON.parseObject(skillListStr4, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk4 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr4, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList4, skillListPk4));
         role.setSkillslotBox(this.getSkillslotBox(skillList4, skillListPk4));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000060120, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000110119, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000160112, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000210112, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000260117, 16));
      } else if (role.getJob() == 3 && role.getGrowtype() == 1) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90401, 2);
         questInfoBox.addQuest(90405, 2);
         questInfoBox.addQuest(90417, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
         String skillListStr = "{\"sp\": 2130, \"tp\": 6, \"skilllist\": [{\"index\": 341, \"level\": 6}, {\"index\": 57, \"level\": 15}, {\"index\": 56, \"level\": 10}, {\"index\": 3, \"level\": 1}, {\"index\": 27, \"level\": 17}, {\"index\": 42, \"level\": 1}, {\"index\": 342, \"level\": 1}, {\"index\": 339, \"level\": 7}, {\"index\": 107, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 15, \"level\": 2}, {\"index\": 174, \"level\": 1}, {\"index\": 188, \"level\": 10}, {\"index\": 29, \"level\": 7}, {\"index\": 58, \"level\": 2}, {\"index\": 16, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 43, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 55, \"level\": 5}, {\"index\": 2, \"level\": 8}, {\"index\": 190, \"level\": 1}, {\"index\": 31, \"level\": 10}, {\"index\": 26, \"level\": 12}, {\"index\": 316, \"level\": 10}, {\"index\": 169, \"level\": 1}, {\"index\": 37, \"level\": 1}, {\"index\": 38, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 12, \"slot\": 1}, {\"index\": 15, \"slot\": 2}, {\"index\": 16, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 17, \"slot\": 5}, {\"index\": 43, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 58, \"slot\": 8}, {\"index\": 55, \"slot\": 9}, {\"index\": 339, \"slot\": 10}, {\"index\": 56, \"slot\": 11}], \"buff\": [{\"index\": 29}, {\"index\": 2, \"slot\": 1}]}}";
         String skillListPkStr = "{\"type\": 1, \"sp\": 2120, \"tp\": 6, \"skilllist\": [{\"index\": 25, \"level\": 1}, {\"index\": 42, \"level\": 1}, {\"index\": 18, \"level\": 1}, {\"index\": 341, \"level\": 1}, {\"index\": 57, \"level\": 12}, {\"index\": 56, \"level\": 10}, {\"index\": 3, \"level\": 10}, {\"index\": 27, \"level\": 17}, {\"index\": 342, \"level\": 1}, {\"index\": 339, \"level\": 7}, {\"index\": 107, \"level\": 1}, {\"index\": 43, \"level\": 1}, {\"index\": 29, \"level\": 7}, {\"index\": 188, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 16, \"level\": 17}, {\"index\": 334, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 55, \"level\": 5}, {\"index\": 2, \"level\": 8}, {\"index\": 190, \"level\": 1}, {\"index\": 31, \"level\": 10}, {\"index\": 26, \"level\": 1}, {\"index\": 316, \"level\": 10}, {\"index\": 169, \"level\": 1}, {\"index\": 37, \"level\": 10}, {\"index\": 58, \"level\": 2}, {\"index\": 38, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 12, \"slot\": 1}, {\"index\": 15, \"slot\": 2}, {\"index\": 16, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 17, \"slot\": 5}, {\"index\": 43, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 58, \"slot\": 8}, {\"index\": 55, \"slot\": 9}, {\"index\": 339, \"slot\": 10}, {\"index\": 56, \"slot\": 11}], \"buff\": [{\"index\": 29}, {\"index\": 2, \"slot\": 1}]}}";
         RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillListStr, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList, skillListPk));
         role.setSkillslotBox(this.getSkillslotBox(skillList, skillListPk));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000050126, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000100127, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000150120, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000200114, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000250119, 16));
      } else if (role.getJob() == 3 && role.getGrowtype() == 4) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90401, 2);
         questInfoBox.addQuest(90409, 2);
         questInfoBox.addQuest(90423, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr2 = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr2, ClearDungeonBox.class);
         String skillListStr2 = "{\"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 3, \"level\": 1}, {\"index\": 337, \"level\": 1}, {\"index\": 105, \"level\": 2}, {\"index\": 95, \"level\": 10}, {\"index\": 92, \"level\": 1}, {\"index\": 25, \"level\": 1}, {\"index\": 2, \"level\": 8}, {\"index\": 373, \"level\": 1}, {\"index\": 98, \"level\": 5}, {\"index\": 100, \"level\": 15}, {\"index\": 371, \"level\": 10}, {\"index\": 106, \"level\": 17}, {\"index\": 97, \"level\": 7}, {\"index\": 12, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 41, \"level\": 10}, {\"index\": 99, \"level\": 12}, {\"index\": 93, \"level\": 1}, {\"index\": 91, \"level\": 1}, {\"index\": 101, \"level\": 7}, {\"index\": 188, \"level\": 10}, {\"index\": 43, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 102, \"level\": 1}, {\"index\": 16, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 89, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 103, \"level\": 2}, {\"index\": 169, \"level\": 1}, {\"index\": 37, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 12, \"slot\": 1}, {\"index\": 15, \"slot\": 2}, {\"index\": 16, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 17, \"slot\": 5}, {\"index\": 43, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 103, \"slot\": 8}, {\"index\": 98, \"slot\": 9}, {\"index\": 105, \"slot\": 10}, {\"index\": 101, \"slot\": 11}], \"buff\": [{\"index\": 371}, {\"index\": 97, \"slot\": 1}, {\"index\": 2, \"slot\": 2}]}}";
         String skillListPkStr2 = "{\"type\": 1, \"sp\": 2130, \"tp\": 6, \"skilllist\": [{\"index\": 92, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 65, \"level\": 1}, {\"index\": 99, \"level\": 12}, {\"index\": 41, \"level\": 10}, {\"index\": 3, \"level\": 10}, {\"index\": 18, \"level\": 1}, {\"index\": 105, \"level\": 5}, {\"index\": 337, \"level\": 1}, {\"index\": 25, \"level\": 1}, {\"index\": 2, \"level\": 8}, {\"index\": 373, \"level\": 1}, {\"index\": 98, \"level\": 5}, {\"index\": 100, \"level\": 15}, {\"index\": 95, \"level\": 1}, {\"index\": 42, \"level\": 1}, {\"index\": 371, \"level\": 1}, {\"index\": 106, \"level\": 1}, {\"index\": 97, \"level\": 4}, {\"index\": 93, \"level\": 17}, {\"index\": 91, \"level\": 1}, {\"index\": 101, \"level\": 7}, {\"index\": 188, \"level\": 4}, {\"index\": 43, \"level\": 1}, {\"index\": 102, \"level\": 1}, {\"index\": 16, \"level\": 17}, {\"index\": 334, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 15, \"level\": 1}, {\"index\": 17, \"level\": 1}, {\"index\": 89, \"level\": 1}, {\"index\": 350, \"level\": 1}, {\"index\": 190, \"level\": 1}, {\"index\": 103, \"level\": 2}, {\"index\": 169, \"level\": 1}, {\"index\": 37, \"level\": 10}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 12, \"slot\": 1}, {\"index\": 15, \"slot\": 2}, {\"index\": 16, \"slot\": 3}, {\"index\": 65, \"slot\": 4}, {\"index\": 17, \"slot\": 5}, {\"index\": 43, \"slot\": 6}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 103, \"slot\": 8}, {\"index\": 105, \"slot\": 9}, {\"index\": 98, \"slot\": 10}, {\"index\": 101, \"slot\": 11}], \"buff\": [{\"index\": 97}, {\"index\": 371, \"slot\": 1}, {\"index\": 2, \"slot\": 2}]}}";
         RES_SKILL_LIST skillList2 = (RES_SKILL_LIST)JSON.parseObject(skillListStr2, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk2 = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr2, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList2, skillListPk2));
         role.setSkillslotBox(this.getSkillslotBox(skillList2, skillListPk2));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000060120, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000110119, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000160112, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000210112, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000260117, 16));
      } else if (role.getJob() == 14 && role.getGrowtype() == 1) {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90501, 2);
         questInfoBox.addQuest(90505, 2);
         questInfoBox.addQuest(90517, 2);
         questInfoBox.addQuest(100600201);
         String clearDungeonStr = "{\"dungeoninfos\": [{\"index\": 2002120031, \"list\": [{\"score\": 7}]}, {\"index\": 2002120032, \"list\": [{\"score\": 7}]}, {\"index\": 2002120033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120041, \"list\": [{\"score\": 7}]}, {\"index\": 2002120042, \"list\": [{\"score\": 7}]}, {\"index\": 2002120043, \"list\": [{\"score\": 7}]}, {\"index\": 2002120051, \"list\": [{\"score\": 7}]}, {\"index\": 2002120052, \"list\": [{\"score\": 7}]}, {\"index\": 2002120053, \"list\": [{\"score\": 7}]}, {\"index\": 2002120071, \"list\": [{\"score\": 7}]}, {\"index\": 2002120072, \"list\": [{\"score\": 7}]}, {\"index\": 2002120073, \"list\": [{\"score\": 7}]}, {\"index\": 2002120081, \"list\": [{\"score\": 7}]}, {\"index\": 2002120082, \"list\": [{\"score\": 7}]}, {\"index\": 2002120091, \"list\": [{\"score\": 7}]}, {\"index\": 2002120092, \"list\": [{\"score\": 7}]}, {\"index\": 2002120093, \"list\": [{\"score\": 7}]}, {\"index\": 2002120101, \"list\": [{\"score\": 7}]}, {\"index\": 2002120102, \"list\": [{\"score\": 7}]}, {\"index\": 2002120103, \"list\": [{\"score\": 7}]}, {\"index\": 2002120104, \"list\": [{\"score\": 7}]}, {\"index\": 2002120105, \"list\": [{\"score\": 7}]}, {\"index\": 2002120111, \"list\": [{\"score\": 7}]}, {\"index\": 2002120112, \"list\": [{\"score\": 7}]}, {\"index\": 2002120121, \"list\": [{\"score\": 7}]}, {\"index\": 2002120122, \"list\": [{\"score\": 7}]}, {\"index\": 2002120123, \"list\": [{\"score\": 7}]}, {\"index\": 2002120131, \"list\": [{\"score\": 7}]}, {\"index\": 2002120132, \"list\": [{\"score\": 7}]}, {\"index\": 2002120133, \"list\": [{\"score\": 7}]}, {\"index\": 2002120161, \"list\": [{\"score\": 7}]}, {\"index\": 2002120162, \"list\": [{\"score\": 7}]}, {\"index\": 2002120163, \"list\": [{\"score\": 7}]}, {\"index\": 2002120151, \"list\": [{\"score\": 7}]}, {\"index\": 2002120152, \"list\": [{\"score\": 7}]}, {\"index\": 2002120153, \"list\": [{\"score\": 7}]}, {\"index\": 2002120171, \"list\": [{\"score\": 7}]}, {\"index\": 2002120172, \"list\": [{\"score\": 7}]}, {\"index\": 2002120181, \"list\": [{\"score\": 7}]}, {\"index\": 2002120182, \"list\": [{\"score\": 7}]}, {\"index\": 2002120191, \"list\": [{\"score\": 7}]}, {\"index\": 2002120192, \"list\": [{\"score\": 7}]}, {\"index\": 2002120193, \"list\": [{\"score\": 7}]}, {\"index\": 2002120201, \"list\": [{\"score\": 7}]}, {\"index\": 2002120202, \"list\": [{\"score\": 7}]}, {\"index\": 2002120203, \"list\": [{\"score\": 7}]}, {\"index\": 2002120211, \"list\": [{\"score\": 7}]}, {\"index\": 2002120212, \"list\": [{\"score\": 7}]}, {\"index\": 2002120213, \"list\": [{\"score\": 7}]}, {\"index\": 2002120221, \"list\": [{\"score\": 7}]}, {\"index\": 2002120222, \"list\": [{\"score\": 7}]}, {\"index\": 2002120223, \"list\": [{\"score\": 7}]}, {\"index\": 2002120224, \"list\": [{\"score\": 7}]}, {\"index\": 2002120431, \"list\": [{\"score\": 7}]}, {\"index\": 2002120241, \"list\": [{\"score\": 7}]}, {\"index\": 2002120242, \"list\": [{\"score\": 7}]}, {\"index\": 2002120243, \"list\": [{\"score\": 7}]}, {\"index\": 2002120251, \"list\": [{\"score\": 7}]}, {\"index\": 2002120252, \"list\": [{\"score\": 7}]}, {\"index\": 2002120253, \"list\": [{\"score\": 7}]}, {\"index\": 2002120261, \"list\": [{\"score\": 7}]}, {\"index\": 2002120262, \"list\": [{\"score\": 7}]}, {\"index\": 2002120263, \"list\": [{\"score\": 7}]}, {\"index\": 2002120432, \"list\": [{\"score\": 7}]}, {\"index\": 2002120371, \"list\": [{\"score\": 7}]}, {\"index\": 2002120372, \"list\": [{\"score\": 7}]}, {\"index\": 2002120373, \"list\": [{\"score\": 7}]}, {\"index\": 2002120401, \"list\": [{\"score\": 7}]}, {\"index\": 2002120402, \"list\": [{\"score\": 7}]}, {\"index\": 2002120403, \"list\": [{\"score\": 7}]}, {\"index\": 2002120404, \"list\": [{\"score\": 7}]}, {\"index\": 2002120391, \"list\": [{\"score\": 7}]}, {\"index\": 2002120392, \"list\": [{\"score\": 7}]}, {\"index\": 2002120411, \"list\": [{\"score\": 7}]}, {\"index\": 2002120412, \"list\": [{\"score\": 7}]}, {\"index\": 2002120413, \"list\": [{\"score\": 7}]}, {\"index\": 2002120414, \"list\": [{\"score\": 7}]}, {\"index\": 2002120415, \"list\": [{\"score\": 7}]}, {\"index\": 2002120421, \"list\": [{\"score\": 7}]}, {\"index\": 2002120422, \"list\": [{\"score\": 7}]}, {\"index\": 2002120423, \"list\": [{\"score\": 7}]}, {\"index\": 2002120441, \"list\": [{\"score\": 7}]}, {\"index\": 2002120442, \"list\": [{\"score\": 7}]}, {\"index\": 2002120443, \"list\": [{\"score\": 7}]}, {\"index\": 2002120444, \"list\": [{\"score\": 7}]}, {\"index\": 2002120271, \"list\": [{\"score\": 7}]}, {\"index\": 2002120272, \"list\": [{\"score\": 7}]}, {\"index\": 2002120273, \"list\": [{\"score\": 7}]}, {\"index\": 2002120274, \"list\": [{\"score\": 7}]}, {\"index\": 2002120275, \"list\": [{\"score\": 7}]}, {\"index\": 2002120281, \"list\": [{\"score\": 7}]}, {\"index\": 2002120282, \"list\": [{\"score\": 7}]}, {\"index\": 2002120283, \"list\": [{\"score\": 7}]}, {\"index\": 2002120284, \"list\": [{\"score\": 7}]}, {\"index\": 2002120301, \"list\": [{\"score\": 7}]}, {\"index\": 2002120302, \"list\": [{\"score\": 7}]}, {\"index\": 2002120303, \"list\": [{\"score\": 7}]}, {\"index\": 2002120304, \"list\": [{\"score\": 7}]}, {\"index\": 2002120305, \"list\": [{\"score\": 7}]}, {\"index\": 2002120321, \"list\": [{\"score\": 7}]}, {\"index\": 2002120322, \"list\": [{\"score\": 7}]}, {\"index\": 2002120323, \"list\": [{\"score\": 7}]}, {\"index\": 2002120324, \"list\": [{\"score\": 7}]}, {\"index\": 2002120325, \"list\": [{\"score\": 7}]}, {\"index\": 2002122031, \"list\": [{\"score\": 7}]}, {\"index\": 2002122032, \"list\": [{\"score\": 7}]}, {\"index\": 2002122033, \"list\": [{\"score\": 7}]}, {\"index\": 2002120311, \"list\": [{\"score\": 7}]}, {\"index\": 2002120312, \"list\": [{\"score\": 7}]}, {\"index\": 2002120313, \"list\": [{\"score\": 7}]}, {\"index\": 2002120291, \"list\": [{\"score\": 7}]}, {\"index\": 2002120292, \"list\": [{\"score\": 7}]}, {\"index\": 2002120293, \"list\": [{\"score\": 7}]}]}";
         clearDungeonBox = (ClearDungeonBox)JSON.parseObject(clearDungeonStr, ClearDungeonBox.class);
         String skillListStr = "{\"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 3, \"level\": 8}, {\"index\": 20, \"level\": 1}, {\"index\": 27, \"level\": 5}, {\"index\": 26, \"level\": 7}, {\"index\": 9, \"level\": 2}, {\"index\": 22, \"level\": 10}, {\"index\": 28, \"level\": 1}, {\"index\": 23, \"level\": 7}, {\"index\": 15, \"level\": 10}, {\"index\": 334, \"level\": 1}, {\"index\": 17, \"level\": 10}, {\"index\": 14, \"level\": 10}, {\"index\": 19, \"level\": 1}, {\"index\": 13, \"level\": 1}, {\"index\": 1, \"level\": 1}, {\"index\": 30, \"level\": 1}, {\"index\": 18, \"level\": 1}, {\"index\": 7, \"level\": 2}, {\"index\": 169, \"level\": 1}, {\"index\": 24, \"level\": 2}, {\"index\": 89, \"level\": 5}, {\"index\": 350, \"level\": 1}, {\"index\": 11, \"level\": 1}, {\"index\": 16, \"level\": 15}, {\"index\": 190, \"level\": 1}, {\"index\": 12, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 29, \"level\": 17}, {\"index\": 21, \"level\": 8}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 1, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 9, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 11, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 24, \"slot\": 6}, {\"index\": 89, \"slot\": 8}, {\"index\": 27, \"slot\": 9}, {\"index\": 26, \"slot\": 10}, {\"index\": 22, \"slot\": 11}], \"buff\": [{\"index\": 28}, {\"index\": 23, \"slot\": 1}, {\"index\": 14, \"slot\": 2}]}}";
         String skillListPkStr = "{\"type\": 1, \"sp\": 2125, \"tp\": 6, \"skilllist\": [{\"index\": 5, \"level\": 5}, {\"index\": 22, \"level\": 10}, {\"index\": 19, \"level\": 1}, {\"index\": 27, \"level\": 5}, {\"index\": 89, \"level\": 5}, {\"index\": 350, \"level\": 1}, {\"index\": 2, \"level\": 1}, {\"index\": 8, \"level\": 1}, {\"index\": 20, \"level\": 12}, {\"index\": 3, \"level\": 1}, {\"index\": 26, \"level\": 7}, {\"index\": 28, \"level\": 1}, {\"index\": 23, \"level\": 1}, {\"index\": 334, \"level\": 1}, {\"index\": 16, \"level\": 15}, {\"index\": 190, \"level\": 1}, {\"index\": 17, \"level\": 10}, {\"index\": 14, \"level\": 10}, {\"index\": 13, \"level\": 1}, {\"index\": 9, \"level\": 1}, {\"index\": 18, \"level\": 1}, {\"index\": 1, \"level\": 1}, {\"index\": 24, \"level\": 2}, {\"index\": 169, \"level\": 1}, {\"index\": 7, \"level\": 1}, {\"index\": 174, \"level\": 1}, {\"index\": 15, \"level\": 10}, {\"index\": 11, \"level\": 12}, {\"index\": 29, \"level\": 17}, {\"index\": 21, \"level\": 1}, {\"index\": 12, \"level\": 1}], \"skillslot\": {\"active\": [{\"index\": 334}, {\"index\": 1, \"slot\": 1}, {\"index\": 7, \"slot\": 2}, {\"index\": 9, \"slot\": 3}, {\"index\": 12, \"slot\": 4}, {\"index\": 11, \"slot\": 5}, {\"index\": 10001, \"slot\": 18}, {\"index\": 10002, \"slot\": 17}, {\"index\": 169, \"slot\": 7}, {\"index\": 24, \"slot\": 6}, {\"index\": 89, \"slot\": 8}, {\"index\": 27, \"slot\": 9}, {\"index\": 26, \"slot\": 10}, {\"index\": 22, \"slot\": 11}], \"buff\": [{\"index\": 28}, {\"index\": 23, \"slot\": 1}, {\"index\": 14, \"slot\": 2}]}}";
         RES_SKILL_LIST skillList = (RES_SKILL_LIST)JSON.parseObject(skillListStr, RES_SKILL_LIST.class);
         RES_SKILL_LIST skillListPk = (RES_SKILL_LIST)JSON.parseObject(skillListPkStr, RES_SKILL_LIST.class);
         role.setSkillBox(this.getSkillBox(skillList, skillListPk));
         role.setSkillslotBox(this.getSkillslotBox(skillList, skillListPk));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000320092, 20));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000310088, 19));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000300092, 18));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000090090, 13));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000140090, 15));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000190087, 14));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000240087, 17));
         equippedBox.addEquip(EquipDataPool.createEquipped(2000290090, 16));
      } else {
         questInfoBox.addQuest(1100556, 2);
         questInfoBox.addQuest(10010);
         questInfoBox.addQuest(10020);
         questInfoBox.addQuest(10030);
         questInfoBox.addQuest(20010);
         questInfoBox.addQuest(90201, 2);
         questInfoBox.addQuest(90209, 2);
         questInfoBox.addQuest(90223, 2);
         questInfoBox.addQuest(100600201);
      }

      questInfoBox.setQueststate(2);
      role.setQuestInfoBox(questInfoBox);
      TitleBox titleBox = role.getTitleBox();
      if (titleBox == null) {
         titleBox = new TitleBox();
      }

      titleBox.addTitle(2000330010);
      titleBox.addTitle(2000330011);
      titleBox.addTitle(2000330012);
      titleBox.addTitle(2000330013);
      titleBox.addTitle(2000330015);
      titleBox.addTitle(2000330018);
      long curTime = TimeUtil.currS();
      String sysBuffBoxStr = "{\"time\": \"" + curTime + "\", \"appendages\": [{\"index\": 2000}]}";
      SysBuffBox sysBuffBox = (SysBuffBox)JSON.parseObject(sysBuffBoxStr, SysBuffBox.class);
      role.setSysBuffBox(sysBuffBox);
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = 1990000051;
      ptStackable.count = 1;
      ptStackable.bind = true;
      this.addConsumable(role, ptStackable);
      PT_STACKABLE ptStackable2 = new PT_STACKABLE();
      ptStackable2.index = 1990000052;
      ptStackable2.count = 1;
      ptStackable2.bind = true;
      this.addConsumable(role, ptStackable2);
      account.save();
   }

   public void lvTo43(IoSession session, int growType) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      role.setGrowtype(growType);
      role.setExp(30621837);
      role.setLevel(43);
      role.setQindex(800108);
      role.setEquipscore(3316);
      account.getAdventureUnionInfo().level = 5;
      account.getAdventureUnionInfo().exp = 9184L;
      QuestInfoBox questInfoBox = new QuestInfoBox();
      PT_QUEST_INFO pt_quest_info1 = new PT_QUEST_INFO();
      pt_quest_info1.qindex = 700733;
      pt_quest_info1.isminequest = true;
      pt_quest_info1.state = 1;
      questInfoBox.addQuest(pt_quest_info1);
      PT_QUEST_INFO pt_quest_info2 = new PT_QUEST_INFO();
      pt_quest_info2.qindex = 90201;
      pt_quest_info2.state = 2;
      pt_quest_info2.isminequest = true;
      questInfoBox.addQuest(pt_quest_info2);
      PT_QUEST_INFO pt_quest_info3 = new PT_QUEST_INFO();
      pt_quest_info3.qindex = 90209;
      pt_quest_info3.state = 2;
      pt_quest_info3.isminequest = true;
      questInfoBox.addQuest(pt_quest_info3);
      role.setQuestInfoBox(questInfoBox);
      role.save();
      account.save();
   }

   public void addMoney(Role role, int index, int cnt) {
      MoneyBox moneyBox = role.getMoneyBox();
      moneyBox.addCnt(index, cnt);
      role.setMoneyBox(moneyBox);
      role.save();
   }

   public void costMoney(Role role, int index, int cnt) {
      MoneyBox moneyBox = role.getMoneyBox();
      moneyBox.subCnt(index, cnt);
      role.setMoneyBox(moneyBox);
      role.save();
   }

   public void saveEqu(Role role, int index) {
      Equip equip = EquipDataPool.getEquip(index);
      if (equip == null) {
         this.logger.error("ERROR====equip is null, index={}", index);
      } else {
         int equipType = equip.getEquiptype();
         equipType = EquipDataPool.getEquType(equipType);
         if (equipType == 1) {
            AvatarBox avatarBox = role.getAvatarBox();
            PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
            pt_avatar_item.index = index;
            pt_avatar_item.guid = IdGenerator.getNextId();
            avatarBox.addAvatar(pt_avatar_item);
            role.setAvatarBox(avatarBox);
            role.save();
         }

      }
   }

   public int getLevel(int curLevel, int curExp) {
      if (curLevel == 55) {
         return 55;
      } else {
         int i = curLevel;

         int exp;
         for(exp = (Integer)DataCache.ROLE_EXP_MAP.get(curLevel); exp < curExp; exp = (Integer)DataCache.ROLE_EXP_MAP.get(i)) {
            ++i;
         }

         return exp == curExp ? i + 1 : i;
      }
   }

   public PT_CHARACTER_INFO getPtCharacterInfo(long charguid) {
      Role role = (Role)DataCache.ONLINE_ROLES.get(charguid);
      if (role != null) {
         PT_CHARACTER_INFO pt_character_info = new PT_CHARACTER_INFO();
         pt_character_info.charguid = role.getUid();
         if (role.getJob() != 0) {
            pt_character_info.job = role.getJob();
         }

         if (role.getGrowtype() != 0) {
            pt_character_info.growtype = role.getGrowtype();
         }

         if (role.getSecgrowtype() != 0) {
            pt_character_info.secondgrowtype = role.getSecgrowtype();
         }

         pt_character_info.level = role.getLevel();
         pt_character_info.name = role.getName();
         pt_character_info.posx = role.getPos().getX();
         pt_character_info.posy = role.getPos().getY();
         pt_character_info.equiplist = role.getEquippedBox().getEquippedlist();
         pt_character_info.avatarlist = new ArrayList();
         Map<Integer, Integer> avatarMap = new HashMap();
         if (role.getEquippedBox().getAvatarskinlist() != null && role.getEquippedBox().getAvatarskinlist().size() > 0) {
            for(PT_AVATAR_ITEM pe : role.getEquippedBox().getAvatarskinlist()) {
               avatarMap.put(pe.slot, pe.index);
               pt_character_info.avatarlist.add(pe);
            }
         }

         if (role.getEquippedBox().getAvatarlist() != null && role.getEquippedBox().getAvatarlist().size() > 0) {
            for(PT_AVATAR_ITEM pe : role.getEquippedBox().getAvatarlist()) {
               if (avatarMap.get(pe.slot + 2000) == null) {
                  pt_character_info.avatarlist.add(pe);
               }
            }
         }

         pt_character_info.partydisturb = 1;
         pt_character_info.adventureunionlevel = 1;
         pt_character_info.type = 3;
         return pt_character_info;
      } else {
         return null;
      }
   }

   public PT_USER_INFO getPtUserInfo(IoSession session, long cobjectId, int playerId) {
      Account account = SessionUtils.getAccountBySession(session);
      Role role = SessionUtils.getRoleBySession(session);
      SysBuffBox sysBuffBox = role.getSysBuffBox();
      AdventureUnionInfo adventureUnionInfo = account.getAdventureUnionInfo();
      PT_USER_INFO pt_user_info = new PT_USER_INFO();
      if (playerId != -1) {
         pt_user_info.playerid = playerId;
      }

      pt_user_info.charguid = role.getUid();
      pt_user_info.objectgroupid = (int)cobjectId;
      pt_user_info.cobjectid = (int)cobjectId;
      pt_user_info.job = role.getJob();
      pt_user_info.level = role.getLevel();
      pt_user_info.exp = role.getExp();
      pt_user_info.growtype = role.getGrowtype();
      pt_user_info.secgrowtype = role.getSecgrowtype();
      pt_user_info.name = role.getName();
      pt_user_info.appendages = sysBuffBox.getAppendages();
      EquippedBox equippedBox = role.getEquippedBox();
      pt_user_info.equiplist = new PT_EQUIP_LIST();
      pt_user_info.equiplist.equiplist = new ArrayList();

      for(PT_EQUIPPED pt_equipped : equippedBox.getEquiplist()) {
         PT_EQUIP pt_equip = new PT_EQUIP();
         pt_equip.index = pt_equipped.index;
         pt_equip.guid = pt_equipped.guid;
         pt_equip.upgrade = pt_equipped.upgrade;
         pt_equip.quality = pt_equipped.quality;
         pt_equip.endurance = pt_equipped.endurance;
         pt_equip.enchant = pt_equipped.enchant;
         pt_equip.emblem = pt_equipped.emblem;
         pt_equip.rappearance = pt_equipped.rappearance;
         pt_equip.roption = pt_equipped.roption;
         pt_equip.enchantindex = pt_equipped.enchantindex;
         pt_equip.slot = pt_equipped.slot;
         pt_user_info.equiplist.equiplist.add(pt_equip);
      }

      pt_user_info.equiplist.equipskinlist = new ArrayList();

      for(PT_EQUIPPED pt_equipped : equippedBox.getEquipskinlist()) {
         PT_EQUIP pt_equip = new PT_EQUIP();
         pt_equip.index = pt_equipped.index;
         pt_equip.guid = pt_equipped.guid;
         pt_equip.quality = pt_equipped.quality;
         pt_equip.slot = pt_equipped.slot;
         pt_user_info.equiplist.equipskinlist.add(pt_equip);
      }

      pt_user_info.equiplist.avatarlist = equippedBox.getAvatarlist();
      pt_user_info.equiplist.avatarskinlist = equippedBox.getAvatarskinlist();
      pt_user_info.skilllist = new PT_SKILLS();
      pt_user_info.skilllist.skilllist = role.getSkillBox().getSkills();
      pt_user_info.skilllist.sp = role.getSkillBox().getSp();
      pt_user_info.skilllist.tp = role.getSkillBox().getTp();
      pt_user_info.skillslot = role.getSkillslotBox().getSkillslot();
      pt_user_info.material = new PT_MATERIAL_LIST();
      pt_user_info.material.material = role.getMaterialBox().getMaterials();
      pt_user_info.consume = new PT_CONSUME_LIST();
      pt_user_info.consume.consume = new ArrayList();

      for(DEF_ITEM_CONSUMABLE def_item_consumable : role.getConsumableBox().getConsumeList()) {
         PT_STACKABLE ptStackable = new PT_STACKABLE();
         ptStackable.index = def_item_consumable.index;
         ptStackable.count = def_item_consumable.count;
         ptStackable.expiretime = def_item_consumable.expiretime;
         ptStackable.bind = def_item_consumable.bind;
         pt_user_info.consume.consume.add(ptStackable);
      }

      pt_user_info.teamtype = ENUM_TEAM.T.BLUE;
      pt_user_info.adventureunionlevel = adventureUnionInfo.level;
      pt_user_info.adventureunionexp = adventureUnionInfo.exp;
      pt_user_info.time = TimeUtil.currS();
      pt_user_info.world = 1;
      pt_user_info.currency = new ArrayList();
      pt_user_info.accountcurrency = new ArrayList();

      for(Map.Entry<Integer, PT_MONEY_ITEM> entry : role.getMoneyBox().getCurrency().entrySet()) {
         PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)entry.getValue();
         pt_user_info.currency.add(pt_money_item);
      }

      for(Map.Entry<Integer, PT_MONEY_ITEM> entry : account.getMoneyBox().getAccountcurrency().entrySet()) {
         PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)entry.getValue();
         pt_user_info.accountcurrency.add(pt_money_item);
      }

      pt_user_info.characoptionsyncdata = role.getServerSimpleDataBox().getData(1, 6).getValue();
      pt_user_info.accountoptionsyncdata = role.getServerSimpleDataBox().getData(0, 2).getValue();
      pt_user_info.equipscore = role.getEquipscore();
      pt_user_info.equipitems = role.getEquipBox().getEquipList();
      pt_user_info.creditscore = 351;
      pt_user_info.adventureunionname = role.getAdventurename();
      pt_user_info.creturecommunionskillinfos = new PT_CREATURE_LEARN_SKILL_INFOS();
      return pt_user_info;
   }

   public RES_CHARAC_LIST getCharList(Account account, String accountKey) {
      RES_CHARAC_LIST res_charac_list = new RES_CHARAC_LIST();
      List<PlayerProfile> roleList = this.playerService.getPlayersBy(account.getId());
      this.logger.error("roleList: id == {} -------- {}", account.getId(), JSON.toJSONString(roleList));
      long advExp = -1L;
      int advLevel = -1;
      if (roleList.size() > 0) {
         res_charac_list.count = 0;
         res_charac_list.dailycreatecharcount = 1;
         res_charac_list.characlist = new ArrayList();

         for(PlayerProfile profile : roleList) {
            long uid = profile.getUid();
            Role role = this.playerService.getPlayerBy(uid);
            if (role != null && !role.isDelete()) {
               res_charac_list.count = res_charac_list.count + 1;
               EquippedBox equipBox = role.getEquippedBox();
               if (advExp == -1L) {
                  advExp = account.getAdventureUnionInfo().exp;
               }

               if (advLevel == -1) {
                  advLevel = account.getAdventureUnionInfo().level;
               }

               PT_CHARACTER pt_character = new PT_CHARACTER();
               pt_character.charguid = uid;
               pt_character.lastlogout = role.getLastlogout();
               pt_character.level = role.getLevel();
               pt_character.name = role.getName();
               pt_character.fatigue = role.getFatigue();
               pt_character.equipscore = role.getEquipscore();
               pt_character.job = role.getJob();
               pt_character.growtype = role.getGrowtype();
               pt_character.secgrowtype = role.getSecgrowtype();
               pt_character.equips = new PT_CHARACTER_EQUIP_ONLY_INDEX();
               pt_character.equips.avatarlist = new ArrayList();
               Map<Integer, Integer> avatarMap = new HashMap();
               if (role.getEquippedBox().getAvatarskinlist() != null && role.getEquippedBox().getAvatarskinlist().size() > 0) {
                  for(PT_AVATAR_ITEM pe : role.getEquippedBox().getAvatarskinlist()) {
                     PT_AVATAR_INDEX_SLOT pt_equip_index_slot = new PT_AVATAR_INDEX_SLOT();
                     pt_equip_index_slot.index = pe.index;
                     pt_equip_index_slot.slot = pe.slot;
                     avatarMap.put(pe.slot, pe.index);
                     pt_character.equips.avatarlist.add(pt_equip_index_slot);
                  }
               }

               if (role.getEquippedBox().getAvatarlist() != null && role.getEquippedBox().getAvatarlist().size() > 0) {
                  for(PT_AVATAR_ITEM pe : role.getEquippedBox().getAvatarlist()) {
                     if (avatarMap.get(pe.slot + 2000) == null) {
                        PT_AVATAR_INDEX_SLOT pt_equip_index_slot = new PT_AVATAR_INDEX_SLOT();
                        pt_equip_index_slot.index = pe.index;
                        pt_equip_index_slot.slot = pe.slot;
                        pt_character.equips.avatarlist.add(pt_equip_index_slot);
                     }
                  }
               }

               pt_character.equips.equiplist = new ArrayList();

               for(PT_EQUIPPED pe : equipBox.getEquiplist()) {
                  PT_EQUIP_INDEX_SLOT pt_equip_index_slot = new PT_EQUIP_INDEX_SLOT();
                  pt_equip_index_slot.index = pe.index;
                  pt_equip_index_slot.slot = pe.slot;
                  pt_equip_index_slot.upgrade = pe.upgrade;
                  if (pe.slot == 11) {
                     pt_character.equips.equiplist.add(pt_equip_index_slot);
                  }
               }

               pt_character.createtime = role.getCreatetime();
               res_charac_list.characlist.add(pt_character);
            }
         }
      }

      if (advLevel == -1) {
         advLevel = 1;
      }

      if (advExp == -1L) {
         advExp = 0L;
      }

      res_charac_list.adventureunionlevel = advLevel;
      if (advExp != 0L) {
         res_charac_list.adventureunionexp = advExp;
      }

      res_charac_list.adventureunionname = "모험단##" + accountKey;
      res_charac_list.dailycreatecharmaxcount = 4;
      res_charac_list.maxcount = 10;
      return res_charac_list;
   }

   @EventHandler({EventType.LOGOUT})
   public void handleLogoutEvent(LogoutEvent logoutEvent) {
   }

   @EventHandler({EventType.LOGIN})
   public void handleLoginEvent(LoginEvent loginEvent) {
   }

   @EventHandler({EventType.ROLE_LEVEL_UP})
   public void handleRoleLevelUpEvent(RoleLevelUpEvent roleLevelUpEvent) {
   }

   public int getRoleId() {
      return SpringUtils.getIdentityService().getNextId(IdentityType.ROLE);
   }

   public void updateRoleMoney(Role role) {
   }

   public void subtractMoney(Role role, int money) {
   }

   public void subtractMoney(Role role, int money, Reason reason) {
   }

   public long getRoleUpgradeNeedExp(int level) {
      RoleExp roleExp = (RoleExp)RoleDataPool.level2RoleExp.get((short)level);
      return roleExp == null ? 0L : (long)roleExp.getExp();
   }

   public long getPetUpgradeNeedExp(int level) {
      PetExp petExp = (PetExp)RoleDataPool.level2PetExp.get(level);
      return petExp == null ? 0L : (long)petExp.getNextLevelExp();
   }

   private String replaceName(String name) {
      name = name.replace("#", "");
      name = name.replace("～", "");
      name = name.replace("~", "");
      name = name.replace("-", "");
      name = name.replace("_", "");
      name = name.replace("@", "");
      name = name.replace("G", "");
      name = name.replace("M", "");
      name = name.replace("g", "");
      name = name.replace("m", "");
      name = name.replace("Ｇ", "");
      name = name.replace("Ｍ", "");
      name = name.replace("ｇ", "");
      name = name.replace("ｍ", "");
      name = name.replace("全体", "");
      name = name.replace("运维", "");
      name = name.replace("。", "");
      name = name.replace("丶", "");
      name = name.replace("=", "");
      name = name.replace(":", "");
      name = name.replace("：", "");
      name = name.replace("\\", "");
      name = name.replace("、", "");
      name = name.replace("＝", "");
      name = name.replace("丶", "");
      name = name.replace("￡", "");
      name = name.replace("゛", "");
      name = name.replace(".", "");
      name = name.replace("\ue814", "");
      name = name.replace("『", "");
      name = name.replace("』", "");
      name = name.replace("群", "");
      name = name.replace("裙", "");
      name = name.replace("裙", "");
      name = name.replace("Q", "");
      name = name.replace("q", "");
      name = name.replace("V", "");
      name = name.replace("X", "");
      name = name.replace("x", "");
      name = name.replace("w", "");
      name = name.replace("W", "");
      name = name.replace("信", "");
      name = name.replace("微", "");
      name = name.replace("利", "");
      name = name.replace("返", "");
      name = name.replace("术", "");
      name = name.replace("技", "");
      name = name.replace("v", "");
      Pattern p = Pattern.compile("[\\d]");
      Matcher matcher = p.matcher(name);
      String result = matcher.replaceAll("");
      return result.trim();
   }

   private boolean canCreateRole(IoSession session, String name) {
      name = this.replaceName(name);
      if (name.length() <= 1) {
         return false;
      } else {
         List<String> set = this.wordService.checkDirtyWords(name);
         return set.size() < 1;
      }
   }

   public Role createRole(IoSession session, String openid, String accountKey, REQ_CREATE_CHARACTER reqCreateCharacter) {
      Server server = this.serverService.getServer();
      String name = reqCreateCharacter.name.trim();
      int job = 0;
      if (reqCreateCharacter.job != null) {
         job = reqCreateCharacter.job;
      }

      Role role = new Role();
      role.setJob(job);
      role.setOpenid(openid);
      role.setRoleId(this.getRoleId());
      long uid = IdGenerator.getNextId();
      role.setUid(uid);
      role.setName(name);
      role.setQindex(100110);
      role.setCreatetime(TimeUtil.currS());
      role.setLevel(1);
      role.setFatigue(100);
      role.setExp(200);
      role.setPos(new Pos());
      role.setAdventurename("모험단##" + accountKey);
      role.setEquipscore(229);
      role.setServerSimpleDataBox(BoxGenerator.getServerSimpleData());
      role.setSkillBox(BoxGenerator.getSkillBox(job));
      role.setDistName(server.getName());
      role.setMoneyBox(BoxGenerator.getMoneyBox());
      role.setEquippedBox(BoxGenerator.getEquippedBox(job));
      role.setTutoBox(BoxGenerator.getTutoBox());
      role.setSp(40);
      role.setSkillslotBox(BoxGenerator.getSkillslotBox(job));
      role.setDungeonTicketsBox(BoxGenerator.getDungeonTicketsBox());
      role.setTonicBox(BoxGenerator.getTonicBox());
      role.setMailBox(BoxGenerator.getmailbox());
      role.setTowerInfoBox(BoxGenerator.getTowerInfoBox());
      role.setQuestInfoBox(BoxGenerator.getQuestInfoBox());
      role.setClearDungeonBox(BoxGenerator.getClearDungeonBox());
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = 1990000051;
      ptStackable.count = 1;
      ptStackable.bind = true;
      this.addConsumable(role, ptStackable);
      PT_STACKABLE ptStackable2 = new PT_STACKABLE();
      ptStackable2.index = 1990000052;
      ptStackable2.count = 1;
      ptStackable2.bind = true;
      this.addConsumable(role, ptStackable2);
      role.doAfterInit();
      SpringUtils.getPlayerService().saveToCache(role);
      role.save();
      DataCache.AUCTION_ROLES.put(role.getUid(), role);
      SpringUtils.getPlayerService().addPlayerProfile(new PlayerProfile(role));
      return role;
   }

   @EventHandler({EventType.CREATE_ROLE})
   public void handleCreateRoleEvent(CreateRoleEvent createRoleEvent) {
      Role role = createRoleEvent.getRole();
   }

   public void refreshRole(Role member) {
   }

   public void refreshRoleBasic(Role member) {
   }

   public void addOnline(Role role) {
      if (role != null) {
         DataCache.ONLINE_ROLES.put(role.getUid(), role);
      }
   }

   public void removeOnline(Role role) {
      if (role != null) {
         DataCache.ONLINE_ROLES.remove(role.getUid());
      }
   }

   public boolean isOnline(Role role) {
      return DataCache.ONLINE_ROLES.containsKey(role.getUid());
   }

   public boolean isOnline(long roleUid) {
      return DataCache.ONLINE_ROLES.containsKey(roleUid);
   }

   public boolean isOnline(int roleId) {
      long uid = SpringUtils.getPlayerService().getUidBy(roleId);
      return this.isOnline(uid);
   }

   public void initUpgradeNeedExp(List<RoleExp> list) {
      list.forEach((exp) -> {
         RoleExp var10000 = (RoleExp)RoleDataPool.level2RoleExp.put(exp.getLevel(), exp);
      });
   }

   public void initPetUpgradeNeedExp(List<PetExp> petExps) {
      petExps.forEach((petExp) -> {
         PetExp var10000 = (PetExp)RoleDataPool.level2PetExp.put(petExp.getLevel(), petExp);
      });
   }

   public void substractExp(Role role, int exp) {
   }

   public Role getOnlinePlayer(long uid) {
      return (Role)DataCache.ONLINE_ROLES.get(uid);
   }

   public Role getOnlinePlayer(String name) {
      long uid = SpringUtils.getPlayerService().getUidBy(name);
      return this.getOnlinePlayer(uid);
   }

   public Role getPlayerBy(long uid) {
      return SpringUtils.getPlayerService().getPlayerBy(uid);
   }

   public Role getPlayerBy(String name) {
      long uid = SpringUtils.getPlayerService().getUidBy(name);
      return this.getPlayerBy(uid);
   }

   public Role getPlayerBy(int roleId) {
      long uid = SpringUtils.getPlayerService().getUidBy(roleId);
      return this.getPlayerBy(uid);
   }

   public void addOffline(Role role) {
      if (role != null) {
         DataCache.OFFLINE_ROLES.put(role.getUid(), role);
      }
   }

   public void removeOffline(Role role) {
      if (role != null) {
         DataCache.OFFLINE_ROLES.remove(role.getUid());
      }
   }

   public void checkOffline() {
      for(Role role : DataCache.OFFLINE_ROLES.values()) {
         this.checkRoleOffline(role);
      }

   }

   private void checkRoleOffline(Role role) {
   }

   public boolean canLogin(String sid, IoSession session) {
      return true;
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

   public void startGame(IoSession session, Role role) {
      this.addOnline(role);
      SessionManager.INSTANCE.registerSession(role.getUid(), session);
      role.setDistributeKey(SessionManager.INSTANCE.getNextDistributeKey());
   }

   private void sendPacket(Role role) {
   }

   public void doDailyFiveReset(Role role) {
      DungeonTicketsBox dungeonTicketsBox = BoxGenerator.getDungeonTicketsBox();
      int curHellGauge = role.getDungeonTicketsBox().hellGauge;
      dungeonTicketsBox.hellGauge = curHellGauge;
      role.setDungeonTicketsBox(dungeonTicketsBox);
      this.logger.error("重置疲劳 角色id == {}", role.getUid());
      role.setFatigue(100);
      role.save();
   }
}
