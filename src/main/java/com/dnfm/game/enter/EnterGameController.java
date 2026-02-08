package com.dnfm.game.enter;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.model.Pos;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.activity.model.ActivityBox;
import com.dnfm.game.adventure.model.AdventureReapInfo;
import com.dnfm.game.adventure.model.AdventureUnionInfo;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.model.AdvBookBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.CharFrameBox;
import com.dnfm.game.bag.model.ChatFrameBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.CreatureErrandBox;
import com.dnfm.game.bag.model.DamageBox;
import com.dnfm.game.bag.model.DungeonTicketsBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.EpicPieceBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.SysBuffBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.bag.model.TowerInfoBox;
import com.dnfm.game.bag.model.TutoBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.config.Server;
import com.dnfm.game.config.ServerSimpleData;
import com.dnfm.game.config.ServerSimpleDataBox;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.mail.model.MailItem;
import com.dnfm.game.map.service.MapService;
import com.dnfm.game.party.PartyService;
import com.dnfm.game.party.model.DungeonParty;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.AccountLogin;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.skill.model.SkillBox;
import com.dnfm.game.skill.model.SkillslotBox;
import com.dnfm.game.utils.DateUtils;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.DEF_ITEM_CONSUMABLE;
import com.dnfm.mina.protobuf.ENUM_DUNGEON_GAUGE_TYPE;
import com.dnfm.mina.protobuf.NOTIFY_CONTENTS_NOTIFY_REMOVE;
import com.dnfm.mina.protobuf.NOTIFY_LOAD_SERVER_SIMPLE_DATA;
import com.dnfm.mina.protobuf.NOTIFY_RECONNECT_INFO;
import com.dnfm.mina.protobuf.PT_ADVENTUREBOOK_INFO;
import com.dnfm.mina.protobuf.PT_ADVENTUREBOOK_OPEN_CONDITION;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_BATTLE_PASS_INFO;
import com.dnfm.mina.protobuf.PT_CHANNEL;
import com.dnfm.mina.protobuf.PT_CHANNEL_INFO;
import com.dnfm.mina.protobuf.PT_CHARACTER;
import com.dnfm.mina.protobuf.PT_CHARACTER_EQUIP_ONLY_INDEX;
import com.dnfm.mina.protobuf.PT_CHARACTER_GUID;
import com.dnfm.mina.protobuf.PT_CHARACTER_INFO;
import com.dnfm.mina.protobuf.PT_CHARGUID_ENTRANCE_ITEM;
import com.dnfm.mina.protobuf.PT_CHARGUID_FATIGUE;
import com.dnfm.mina.protobuf.PT_CHARGUID_TICKET;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CREATURE_COMMUNION;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_DINING_FOOD_BUFF_INFO;
import com.dnfm.mina.protobuf.PT_ENTRANCE_ITEM;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EVENT_SELECT_INFO;
import com.dnfm.mina.protobuf.PT_GAUGE;
import com.dnfm.mina.protobuf.PT_INIT_SKILL;
import com.dnfm.mina.protobuf.PT_INTEGRATION_WORLD;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEM_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_LOAD_SERVER_SIMPLE_DATA;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_POST_ALL_LIST;
import com.dnfm.mina.protobuf.PT_POST_PACKAGE;
import com.dnfm.mina.protobuf.PT_PROTOCOL_TRANSACTION;
import com.dnfm.mina.protobuf.PT_REAP_REWARD;
import com.dnfm.mina.protobuf.PT_RETURN_USER_INFO;
import com.dnfm.mina.protobuf.PT_SELECTED_ITEM;
import com.dnfm.mina.protobuf.PT_SKILL_INFO;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.PT_SYSTEM_BUFF_APPENDAGE;
import com.dnfm.mina.protobuf.PT_TOWN_INTERVAL;
import com.dnfm.mina.protobuf.PT_WARDROBE_INFO;
import com.dnfm.mina.protobuf.REQ_ACHIEVEMENT_INFO;
import com.dnfm.mina.protobuf.REQ_ACHIEVEMENT_LIST;
import com.dnfm.mina.protobuf.REQ_ADVENTUREBOOK_INFO;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_REAP_INFO;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_INFO;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_INFO_OTHER;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_SUBDUE_INFO;
import com.dnfm.mina.protobuf.REQ_ARTIFACT_LIST;
import com.dnfm.mina.protobuf.REQ_AUTHKEY_REFRESH;
import com.dnfm.mina.protobuf.REQ_AVATAR_ITEM_LIST;
import com.dnfm.mina.protobuf.REQ_BATTLE_PASS_RANKING;
import com.dnfm.mina.protobuf.REQ_BILING_KR_BALANCE;
import com.dnfm.mina.protobuf.REQ_BLACK_DIAMON_INFO;
import com.dnfm.mina.protobuf.REQ_BOOKMARK_LIST;
import com.dnfm.mina.protobuf.REQ_CARD_LIST;
import com.dnfm.mina.protobuf.REQ_CERA_INTERESTING_GOODS_LIST;
import com.dnfm.mina.protobuf.REQ_CHANNEL_LIST;
import com.dnfm.mina.protobuf.REQ_CHARACTER_FRAME_TAB_LIST;
import com.dnfm.mina.protobuf.REQ_CHARACTER_INFO;
import com.dnfm.mina.protobuf.REQ_CHARAC_LIST;
import com.dnfm.mina.protobuf.REQ_CHAT_FRAME_TAB_LIST;
import com.dnfm.mina.protobuf.REQ_CHIVALRY_INFO;
import com.dnfm.mina.protobuf.REQ_COLLECTION_LOAD;
import com.dnfm.mina.protobuf.REQ_CONSUMABLE_LIST;
import com.dnfm.mina.protobuf.REQ_CONTENTS_PREVIEW_LOAD;
import com.dnfm.mina.protobuf.REQ_CONTENTS_PREVIEW_REWARD;
import com.dnfm.mina.protobuf.REQ_CRACKEQUIP_LIST;
import com.dnfm.mina.protobuf.REQ_CRACK_LIST;
import com.dnfm.mina.protobuf.REQ_CREATURE_COMMUNION_INFO;
import com.dnfm.mina.protobuf.REQ_CREATURE_ERRAND_LIST;
import com.dnfm.mina.protobuf.REQ_CREATURE_FEEDING;
import com.dnfm.mina.protobuf.REQ_CREATURE_LIST;
import com.dnfm.mina.protobuf.REQ_DAILY_RESET;
import com.dnfm.mina.protobuf.REQ_DAMAGE_FONT_TAB_LIST;
import com.dnfm.mina.protobuf.REQ_DUNGEON_TICKETS;
import com.dnfm.mina.protobuf.REQ_EMBLEM_LIST;
import com.dnfm.mina.protobuf.REQ_ENTER_CHANNEL;
import com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN;
import com.dnfm.mina.protobuf.REQ_EPIC_PIECE_LIST;
import com.dnfm.mina.protobuf.REQ_EQUIP_LIST;
import com.dnfm.mina.protobuf.REQ_ESSENCE_LIST;
import com.dnfm.mina.protobuf.REQ_EVENT_LIST;
import com.dnfm.mina.protobuf.REQ_FAME_AND_CHARM_POINT;
import com.dnfm.mina.protobuf.REQ_FLAG_LIST;
import com.dnfm.mina.protobuf.REQ_GAME_FLOW_TLOG;
import com.dnfm.mina.protobuf.REQ_GET_LOCAL_REWARD;
import com.dnfm.mina.protobuf.REQ_GET_PRIVATESTORE_GOODSLIST;
import com.dnfm.mina.protobuf.REQ_GUILD_CONTENTS_BUFF_LOAD;
import com.dnfm.mina.protobuf.REQ_HIDDEN_CHATTING_LOAD;
import com.dnfm.mina.protobuf.REQ_IDIP_CHECK_REWARD;
import com.dnfm.mina.protobuf.REQ_IDIP_NOTICES;
import com.dnfm.mina.protobuf.REQ_IDIP_PROHIBIT_LIST;
import com.dnfm.mina.protobuf.REQ_INTERACTION_MENU;
import com.dnfm.mina.protobuf.REQ_ITEM_USABLE_LIST;
import com.dnfm.mina.protobuf.REQ_LEAVE_FROM_TOWN;
import com.dnfm.mina.protobuf.REQ_LOAD_BLACKLIST;
import com.dnfm.mina.protobuf.REQ_LOAD_FRIENDS_INFO;
import com.dnfm.mina.protobuf.REQ_LOAD_SERVER_SIMPLE_DATA;
import com.dnfm.mina.protobuf.REQ_LOAD_SHARE_REWARD;
import com.dnfm.mina.protobuf.REQ_LOAD_WEDDING_INVITATION_LIST;
import com.dnfm.mina.protobuf.REQ_LOCAL_REWARD_HISTORY;
import com.dnfm.mina.protobuf.REQ_LOGIN;
import com.dnfm.mina.protobuf.REQ_MAIL_ALL_DELETE;
import com.dnfm.mina.protobuf.REQ_MAIL_DELETE;
import com.dnfm.mina.protobuf.REQ_MAIL_GET;
import com.dnfm.mina.protobuf.REQ_MAIL_LIST;
import com.dnfm.mina.protobuf.REQ_MAIL_READ;
import com.dnfm.mina.protobuf.REQ_MATERIAL_LIST;
import com.dnfm.mina.protobuf.REQ_MINIGAME_BREAKING_LOAD;
import com.dnfm.mina.protobuf.REQ_MINIGAME_CHEMICAL_LOAD;
import com.dnfm.mina.protobuf.REQ_MINIGAME_LOTTERY_LIST;
import com.dnfm.mina.protobuf.REQ_MINIGAME_MINI_PONG_LOAD;
import com.dnfm.mina.protobuf.REQ_MINIGAME_RESTAURANT_LOAD;
import com.dnfm.mina.protobuf.REQ_MONEY_ITEM_LIST;
import com.dnfm.mina.protobuf.REQ_NEW_CONTENTS_LIST;
import com.dnfm.mina.protobuf.REQ_NOTE_MESSENGER_LOAD;
import com.dnfm.mina.protobuf.REQ_NOT_TRANSACTION_CHARACTER_STATE_INFO;
import com.dnfm.mina.protobuf.REQ_NPC_COUNT;
import com.dnfm.mina.protobuf.REQ_PING;
import com.dnfm.mina.protobuf.REQ_PVP_RECORD_INFO;
import com.dnfm.mina.protobuf.REQ_RECEIVED_INVITE_FRIEND_LIST;
import com.dnfm.mina.protobuf.REQ_RECOMMEND_GUILD_LIST;
import com.dnfm.mina.protobuf.REQ_REFRESH_TARGET_CHARACTER_TOWN_INFO;
import com.dnfm.mina.protobuf.REQ_SAVE_SERVER_SIMPLE_DATA;
import com.dnfm.mina.protobuf.REQ_SCROLL_LIST;
import com.dnfm.mina.protobuf.REQ_SD_AVATAR_LIST;
import com.dnfm.mina.protobuf.REQ_SENDING_INVITE_FRIEND_LIST;
import com.dnfm.mina.protobuf.REQ_SERVER_NOTI_ACK;
import com.dnfm.mina.protobuf.REQ_STANDBY;
import com.dnfm.mina.protobuf.REQ_START_GAME;
import com.dnfm.mina.protobuf.REQ_STORAGE_STEP;
import com.dnfm.mina.protobuf.REQ_SYSTEM_BUFF_APPENDAGE_LIST;
import com.dnfm.mina.protobuf.REQ_TARGET_USER_DETAIL_INFO;
import com.dnfm.mina.protobuf.REQ_TITLE_LIST;
import com.dnfm.mina.protobuf.REQ_TOWER_INFO;
import com.dnfm.mina.protobuf.REQ_TOWN_USER_GUID_LIST;
import com.dnfm.mina.protobuf.REQ_TUTORIAL_LIST;
import com.dnfm.mina.protobuf.REQ_WARDROBE_INFO;
import com.dnfm.mina.protobuf.RES_ACHIEVEMENT_INFO;
import com.dnfm.mina.protobuf.RES_ACHIEVEMENT_LIST;
import com.dnfm.mina.protobuf.RES_ADVENTUREBOOK_INFO;
import com.dnfm.mina.protobuf.RES_ADVENTURE_REAP_INFO;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_INFO;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_INFO_OTHER;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_SUBDUE_INFO;
import com.dnfm.mina.protobuf.RES_ARTIFACT_LIST;
import com.dnfm.mina.protobuf.RES_AUTHKEY_REFRESH;
import com.dnfm.mina.protobuf.RES_AVATAR_ITEM_LIST;
import com.dnfm.mina.protobuf.RES_BATTLE_PASS_RANKING;
import com.dnfm.mina.protobuf.RES_BILING_KR_BALANCE;
import com.dnfm.mina.protobuf.RES_BLACK_DIAMON_INFO;
import com.dnfm.mina.protobuf.RES_BOOKMARK_LIST;
import com.dnfm.mina.protobuf.RES_CARD_LIST;
import com.dnfm.mina.protobuf.RES_CERA_INTERESTING_GOODS_LIST;
import com.dnfm.mina.protobuf.RES_CHANNEL_LIST;
import com.dnfm.mina.protobuf.RES_CHARACTER_FRAME_TAB_LIST;
import com.dnfm.mina.protobuf.RES_CHARACTER_INFO;
import com.dnfm.mina.protobuf.RES_CHARAC_LIST;
import com.dnfm.mina.protobuf.RES_CHAT_FRAME_TAB_LIST;
import com.dnfm.mina.protobuf.RES_CHIVALRY_INFO;
import com.dnfm.mina.protobuf.RES_COLLECTION_LOAD;
import com.dnfm.mina.protobuf.RES_CONSUMABLE_LIST;
import com.dnfm.mina.protobuf.RES_CONTENTS_PREVIEW_LOAD;
import com.dnfm.mina.protobuf.RES_CONTENTS_PREVIEW_REWARD;
import com.dnfm.mina.protobuf.RES_CRACKEQUIP_LIST;
import com.dnfm.mina.protobuf.RES_CRACK_LIST;
import com.dnfm.mina.protobuf.RES_CREATURE_COMMUNION_INFO;
import com.dnfm.mina.protobuf.RES_CREATURE_ERRAND_LIST;
import com.dnfm.mina.protobuf.RES_CREATURE_FEEDING;
import com.dnfm.mina.protobuf.RES_CREATURE_LIST;
import com.dnfm.mina.protobuf.RES_DAILY_RESET;
import com.dnfm.mina.protobuf.RES_DAMAGE_FONT_TAB_LIST;
import com.dnfm.mina.protobuf.RES_DUNGEON_TICKETS;
import com.dnfm.mina.protobuf.RES_EMBLEM_LIST;
import com.dnfm.mina.protobuf.RES_ENTER_CHANNEL;
import com.dnfm.mina.protobuf.RES_ENTER_TO_TOWN;
import com.dnfm.mina.protobuf.RES_EPIC_PIECE_LIST;
import com.dnfm.mina.protobuf.RES_EQUIP_LIST;
import com.dnfm.mina.protobuf.RES_ESSENCE_LIST;
import com.dnfm.mina.protobuf.RES_EVENT_LIST;
import com.dnfm.mina.protobuf.RES_FAME_AND_CHARM_POINT;
import com.dnfm.mina.protobuf.RES_FLAG_LIST;
import com.dnfm.mina.protobuf.RES_GET_LOCAL_REWARD;
import com.dnfm.mina.protobuf.RES_GET_PRIVATESTORE_GOODSLIST;
import com.dnfm.mina.protobuf.RES_GUILD_CONTENTS_BUFF_LOAD;
import com.dnfm.mina.protobuf.RES_HIDDEN_CHATTING_LOAD;
import com.dnfm.mina.protobuf.RES_IDIP_CHECK_REWARD;
import com.dnfm.mina.protobuf.RES_IDIP_NOTICES;
import com.dnfm.mina.protobuf.RES_IDIP_PROHIBIT_LIST;
import com.dnfm.mina.protobuf.RES_INTERACTION_MENU;
import com.dnfm.mina.protobuf.RES_ITEM_USABLE_LIST;
import com.dnfm.mina.protobuf.RES_LEAVE_FROM_TOWN;
import com.dnfm.mina.protobuf.RES_LOAD_BLACKLIST;
import com.dnfm.mina.protobuf.RES_LOAD_FRIENDS_INFO;
import com.dnfm.mina.protobuf.RES_LOAD_SERVER_SIMPLE_DATA;
import com.dnfm.mina.protobuf.RES_LOAD_SHARE_REWARD;
import com.dnfm.mina.protobuf.RES_LOAD_WEDDING_INVITATION_LIST;
import com.dnfm.mina.protobuf.RES_LOCAL_REWARD_HISTORY;
import com.dnfm.mina.protobuf.RES_LOGIN;
import com.dnfm.mina.protobuf.RES_MAIL_ALL_DELETE;
import com.dnfm.mina.protobuf.RES_MAIL_DELETE;
import com.dnfm.mina.protobuf.RES_MAIL_GET;
import com.dnfm.mina.protobuf.RES_MAIL_LIST;
import com.dnfm.mina.protobuf.RES_MAIL_READ;
import com.dnfm.mina.protobuf.RES_MATERIAL_LIST;
import com.dnfm.mina.protobuf.RES_MINIGAME_BREAKING_LOAD;
import com.dnfm.mina.protobuf.RES_MINIGAME_CHEMICAL_LOAD;
import com.dnfm.mina.protobuf.RES_MINIGAME_LOTTERY_LIST;
import com.dnfm.mina.protobuf.RES_MINIGAME_MINI_PONG_LOAD;
import com.dnfm.mina.protobuf.RES_MINIGAME_RESTAURANT_LOAD;
import com.dnfm.mina.protobuf.RES_MONEY_ITEM_LIST;
import com.dnfm.mina.protobuf.RES_NEW_CONTENTS_LIST;
import com.dnfm.mina.protobuf.RES_NOTE_MESSENGER_LOAD;
import com.dnfm.mina.protobuf.RES_NOT_TRANSACTION_CHARACTER_STATE_INFO;
import com.dnfm.mina.protobuf.RES_NPC_COUNT;
import com.dnfm.mina.protobuf.RES_PING;
import com.dnfm.mina.protobuf.RES_PVP_RECORD_INFO;
import com.dnfm.mina.protobuf.RES_RECEIVED_INVITE_FRIEND_LIST;
import com.dnfm.mina.protobuf.RES_RECOMMEND_GUILD_LIST;
import com.dnfm.mina.protobuf.RES_REFRESH_TARGET_CHARACTER_TOWN_INFO;
import com.dnfm.mina.protobuf.RES_SAVE_SERVER_SIMPLE_DATA;
import com.dnfm.mina.protobuf.RES_SCROLL_LIST;
import com.dnfm.mina.protobuf.RES_SD_AVATAR_LIST;
import com.dnfm.mina.protobuf.RES_SENDING_INVITE_FRIEND_LIST;
import com.dnfm.mina.protobuf.RES_SERVER_NOTI_ACK;
import com.dnfm.mina.protobuf.RES_SERVER_RESPONSE_PACKET;
import com.dnfm.mina.protobuf.RES_STANDBY;
import com.dnfm.mina.protobuf.RES_START_GAME;
import com.dnfm.mina.protobuf.RES_STORAGE_STEP;
import com.dnfm.mina.protobuf.RES_SYSTEM_BUFF_APPENDAGE_LIST;
import com.dnfm.mina.protobuf.RES_TARGET_USER_DETAIL_INFO;
import com.dnfm.mina.protobuf.RES_TENCENT_CREDITSCORE_INFO;
import com.dnfm.mina.protobuf.RES_TITLE_LIST;
import com.dnfm.mina.protobuf.RES_TOWER_INFO;
import com.dnfm.mina.protobuf.RES_TOWN_USER_GUID_LIST;
import com.dnfm.mina.protobuf.RES_TUTORIAL_LIST;
import com.dnfm.mina.protobuf.RES_WARDROBE_INFO;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.mina.core.session.IoSession;
import org.nutz.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnterGameController {
   @Autowired
   PlayerService playerService;
   @Autowired
   AccountService accountService;
   @Autowired
   RoleService roleService;
   @Autowired
   PartyService partyService;
   @Autowired
   MapService mapService;
   private final Logger logger = LoggerFactory.getLogger(EnterGameController.class);

   public static void main(String[] args) {
   }

   @RequestMapping
   public void REQ_INTERACTION_MENU(IoSession session, REQ_INTERACTION_MENU req_interaction_menu) {
      RES_INTERACTION_MENU res_interaction_menu = new RES_INTERACTION_MENU();
      Role role = (Role)DataCache.ONLINE_ROLES.get(req_interaction_menu.charguid);
      if (role == null) {
         res_interaction_menu.error = 3;
         MessagePusher.pushMessage((IoSession)session, res_interaction_menu);
      } else {
         res_interaction_menu.charguid = req_interaction_menu.charguid;
         res_interaction_menu.online = 1;
         res_interaction_menu.status = 1;
         res_interaction_menu.openmenutype = req_interaction_menu.openmenutype;
         res_interaction_menu.level = role.getLevel();
         if (role.getJob() != 0) {
            res_interaction_menu.job = role.getJob();
         }

         if (role.getGrowtype() != 0) {
            res_interaction_menu.growtype = role.getGrowtype();
         }

         if (role.getSecgrowtype() != 0) {
            res_interaction_menu.secgrowtype = role.getSecgrowtype();
         }

         res_interaction_menu.name = role.getName();
         res_interaction_menu.transId = req_interaction_menu.transId;
         MessagePusher.pushMessage((IoSession)session, res_interaction_menu);
      }
   }

   @RequestMapping
   public void REQ_NOT_TRANSACTION_CHARACTER_STATE_INFO(IoSession session, REQ_NOT_TRANSACTION_CHARACTER_STATE_INFO req_not_transaction_character_state_info) {
      RES_NOT_TRANSACTION_CHARACTER_STATE_INFO res_not_transaction_character_state_info = new RES_NOT_TRANSACTION_CHARACTER_STATE_INFO();
      if (req_not_transaction_character_state_info.ison != null && req_not_transaction_character_state_info.ison) {
         res_not_transaction_character_state_info.status = 9;
      } else {
         res_not_transaction_character_state_info.status = 1;
      }

      res_not_transaction_character_state_info.transId = req_not_transaction_character_state_info.transId;
      MessagePusher.pushMessage((IoSession)session, res_not_transaction_character_state_info);
   }

   @RequestMapping
   public void REQ_MINIGAME_BREAKING_LOAD(IoSession session, REQ_MINIGAME_BREAKING_LOAD req_minigame_breaking_load) {
      RES_MINIGAME_BREAKING_LOAD res_minigame_breaking_load = new RES_MINIGAME_BREAKING_LOAD();
      res_minigame_breaking_load.ticketcount = 1;
      res_minigame_breaking_load.transId = req_minigame_breaking_load.transId;
      MessagePusher.pushMessage((IoSession)session, res_minigame_breaking_load);
   }

   @RequestMapping
   public void REQ_GET_PRIVATESTORE_GOODSLIST(IoSession session, REQ_GET_PRIVATESTORE_GOODSLIST req_get_privatestore_goodslist) {
      RES_GET_PRIVATESTORE_GOODSLIST res_get_privatestore_goodslist = new RES_GET_PRIVATESTORE_GOODSLIST();
      res_get_privatestore_goodslist.shopid = req_get_privatestore_goodslist.shopid;
      res_get_privatestore_goodslist.remaincostresetcount = 3;
      res_get_privatestore_goodslist.lastfreeresettime = 1660137826L;
      res_get_privatestore_goodslist.goods = new ArrayList();
      res_get_privatestore_goodslist.isrefresh = true;
      res_get_privatestore_goodslist.transId = req_get_privatestore_goodslist.transId;
      MessagePusher.pushMessage((IoSession)session, res_get_privatestore_goodslist);
   }

   @RequestMapping
   public void REQ_RECOMMEND_GUILD_LIST(IoSession session, REQ_RECOMMEND_GUILD_LIST req_recommend_guild_list) {
      RES_RECOMMEND_GUILD_LIST res_recommend_guild_list = new RES_RECOMMEND_GUILD_LIST();
      res_recommend_guild_list.list = new ArrayList();
      res_recommend_guild_list.transId = req_recommend_guild_list.transId;
      MessagePusher.pushMessage((IoSession)session, res_recommend_guild_list);
   }

   @RequestMapping
   public void REQ_ADVENTURE_UNION_INFO_OTHER(IoSession session, REQ_ADVENTURE_UNION_INFO_OTHER req_adventure_union_info_other) {
      long charguid = req_adventure_union_info_other.charguid;
      Account account = SessionUtils.getAccountBySession(session);
      Role role = SessionUtils.getRoleBySession(session);
      AdventureUnionInfo adventureUnionInfo = account.getAdventureUnionInfo();
      RES_ADVENTURE_UNION_INFO_OTHER res_adventure_union_info_other = new RES_ADVENTURE_UNION_INFO_OTHER();
      res_adventure_union_info_other.level = adventureUnionInfo.level;
      res_adventure_union_info_other.adventureunionexp = adventureUnionInfo.exp;
      res_adventure_union_info_other.day = adventureUnionInfo.day;
      res_adventure_union_info_other.name = adventureUnionInfo.name;
      res_adventure_union_info_other.typicalcharacter = new PT_CHARACTER();
      res_adventure_union_info_other.typicalcharacter.charguid = charguid;
      res_adventure_union_info_other.typicalcharacter.growtype = role.getGrowtype();
      res_adventure_union_info_other.typicalcharacter.secgrowtype = role.getSecgrowtype();
      res_adventure_union_info_other.typicalcharacter.job = role.getJob();
      res_adventure_union_info_other.typicalcharacter.level = role.getLevel();
      res_adventure_union_info_other.typicalcharacter.name = role.getName();
      res_adventure_union_info_other.typicalcharacter.equipscore = role.getEquipscore();
      res_adventure_union_info_other.typicalcharacter.equips = new PT_CHARACTER_EQUIP_ONLY_INDEX();
      res_adventure_union_info_other.typicalcharacter.equips.equiplist = new ArrayList();
      res_adventure_union_info_other.typicalcharacter.equips.avatarlist = new ArrayList();
      res_adventure_union_info_other.charactercount = 1;
      res_adventure_union_info_other.world = 1;
      res_adventure_union_info_other.achievementinfolist = new ArrayList();
      res_adventure_union_info_other.transId = req_adventure_union_info_other.transId;
      MessagePusher.pushMessage((IoSession)session, res_adventure_union_info_other);
   }

   @RequestMapping
   public void ReqEssenceList(IoSession session, REQ_ESSENCE_LIST req_essence_list) {
      RES_ESSENCE_LIST res_essence_list = new RES_ESSENCE_LIST();
      res_essence_list.maxcount = 40;
      res_essence_list.transId = req_essence_list.transId;
      MessagePusher.pushMessage((IoSession)session, res_essence_list);
   }

   @RequestMapping
   public void REQ_GUILD_CONTENTS_BUFF_LOAD(IoSession session, REQ_GUILD_CONTENTS_BUFF_LOAD req_guild_contents_buff_load) {
      RES_GUILD_CONTENTS_BUFF_LOAD res_guild_contents_buff_load = new RES_GUILD_CONTENTS_BUFF_LOAD();
      res_guild_contents_buff_load.transId = req_guild_contents_buff_load.transId;
      MessagePusher.pushMessage((IoSession)session, res_guild_contents_buff_load);
   }

   @RequestMapping
   public void REQ_NPC_COUNT(IoSession session, REQ_NPC_COUNT req_npc_count) {
      RES_NPC_COUNT res_npc_count = new RES_NPC_COUNT();
      res_npc_count.transId = req_npc_count.transId;
      MessagePusher.pushMessage((IoSession)session, res_npc_count);
   }

   @RequestMapping
   public void REQ_MAIL_DELETE(IoSession session, REQ_MAIL_DELETE req_mail_delete) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      RES_MAIL_DELETE res_mail_delete = new RES_MAIL_DELETE();
      res_mail_delete.type = req_mail_delete.type;
      res_mail_delete.guid = req_mail_delete.guid;
      int type = 0;
      if (req_mail_delete.type != null) {
         type = req_mail_delete.type;
      }

      if (type == 0) {
         role.getMailBox().removeMail(req_mail_delete.guid);
         role.save();
      } else if (type == 1) {
         account.getMailBox().removeMail(req_mail_delete.guid);
         account.save();
      } else {
         this.logger.error("UNREACH==REQ_MAIL_DELETE type = " + type);
      }

      res_mail_delete.transId = req_mail_delete.transId;
      MessagePusher.pushMessage((IoSession)session, res_mail_delete);
   }

   @RequestMapping
   public void ReqMailGet(IoSession session, REQ_MAIL_GET reqMailGet) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      Long guid = reqMailGet.guid;
      RES_MAIL_GET resMailGet = new RES_MAIL_GET();
      resMailGet.guid = guid;
      PT_ITEMS inventory = new PT_ITEMS();
      int mailtype = 0;
      if (reqMailGet.type != null) {
         mailtype = reqMailGet.type;
      }

      PT_POST_ALL_LIST mail = null;
      if (mailtype == 0) {
         mail = role.getMailBox().getMail(guid);
      } else {
         if (mailtype != 1) {
            this.logger.error("UNREACH==REQ_MAIL_GET mailtype = " + mailtype);
            return;
         }

         mail = account.getMailBox().getMail(guid);
      }

      PT_CONTENTS_REWARD_INFO ptContentsRewardInfo = new PT_CONTENTS_REWARD_INFO();
      PT_CURRENCY_REWARD_INFO ptCurrencyRewardInfo = new PT_CURRENCY_REWARD_INFO();
      List<PT_MONEY_ITEM> ptMoneyItems = new ArrayList();
      List<PT_MONEY_ITEM> ptMoneyItemsNew = new ArrayList();
      PT_CURRENCY_REWARD_INFO ptCurrencyRewardInfo1 = new PT_CURRENCY_REWARD_INFO();
      List<PT_SELECTED_ITEM> selectedItems = new ArrayList();
      resMailGet.remaineditems = selectedItems;
      List<PT_POST_PACKAGE> ptPostPackages = new ArrayList();
      resMailGet.remainedpackages = ptPostPackages;
      if (mail.package0 != null) {
         for(PT_POST_PACKAGE ptPostPackage : mail.package0) {
            int index = 0;
            if (ptPostPackage.index != null) {
               index = ptPostPackage.index;
            }

            int count = ptPostPackage.value;
            int type = BagService.getIndexType(index);
            switch (type) {
               case 1000:
                  if (inventory.materialitems == null) {
                     inventory.materialitems = new ArrayList();
                  }

                  PT_STACKABLE ptStackable = role.getMaterialBox().getMaterial(index);
                  if (ptStackable != null) {
                     ptStackable.count = ptStackable.count + count;
                  } else {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = index;
                     ptStackable.count = count;
                  }

                  PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                  ptStackable1.index = index;
                  ptStackable1.count = count;
                  role.getMaterialBox().addMaterial(ptStackable);
                  inventory.materialitems.add(ptStackable1);
                  break;
               case 1001:
                  if (inventory.consumeitems == null) {
                     inventory.consumeitems = new ArrayList();
                  }

                  DEF_ITEM_CONSUMABLE defItemConsumable = role.getConsumableBox().getConsumable(index);
                  if (defItemConsumable != null) {
                     defItemConsumable.count = defItemConsumable.count + count;
                  } else {
                     defItemConsumable = new DEF_ITEM_CONSUMABLE();
                     defItemConsumable.index = index;
                     defItemConsumable.count = count;
                  }

                  role.getConsumableBox().addConsumable(defItemConsumable);
                  PT_STACKABLE ptStackableConsume = new PT_STACKABLE();
                  ptStackableConsume.index = index;
                  ptStackableConsume.count = count;
                  inventory.consumeitems.add(ptStackableConsume);
                  break;
               case 1003:
                  account.getMoneyBox().addCnt(index, count);
                  PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                  ptMoneyItem.index = index;
                  ptMoneyItem.count = count;
                  ptMoneyItemsNew.add(ptMoneyItem);
                  ptMoneyItems.add(account.getMoneyBox().getMoneyItem(index));
                  break;
               case 1004:
                  role.getMoneyBox().addCnt(index, count);
                  PT_MONEY_ITEM ptMoneyItemCurrency = new PT_MONEY_ITEM();
                  ptMoneyItemCurrency.index = index;
                  ptMoneyItemCurrency.count = count;
                  ptMoneyItemsNew.add(ptMoneyItemCurrency);
                  ptMoneyItems.add(role.getMoneyBox().getMoneyItem(index));
                  break;
               case 1005:
                  if (inventory.emblemitems == null) {
                     inventory.emblemitems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableEmblem = role.getEmblemBox().getEmblem(index);
                  if (ptStackableEmblem != null) {
                     ptStackableEmblem.count = ptStackableEmblem.count + count;
                  } else {
                     ptStackableEmblem = new PT_STACKABLE();
                     ptStackableEmblem.index = index;
                     ptStackableEmblem.count = count;
                  }

                  role.getEmblemBox().putEmblem(ptStackableEmblem);
                  PT_STACKABLE ptStackableEmblem1 = new PT_STACKABLE();
                  ptStackableEmblem1.index = index;
                  ptStackableEmblem1.count = count;
                  inventory.emblemitems.add(ptStackableEmblem1);
                  break;
               case 1006:
                  if (inventory.epicpieceitems == null) {
                     inventory.epicpieceitems = new ArrayList();
                  }

                  PT_STACKABLE ptEpicPiece = account.getEpicPieceBox().getEpicPiece(index);
                  if (ptEpicPiece != null) {
                     ptEpicPiece.count = ptEpicPiece.count + count;
                  } else {
                     ptEpicPiece = new PT_STACKABLE();
                     ptEpicPiece.index = index;
                     ptEpicPiece.count = count;
                  }

                  account.getEpicPieceBox().putEpicPiece(ptEpicPiece);
                  PT_STACKABLE ptEpicPiece1 = new PT_STACKABLE();
                  ptEpicPiece1.count = count;
                  ptEpicPiece1.index = index;
                  inventory.epicpieceitems.add(ptEpicPiece1);
                  break;
               case 1007:
                  if (inventory.bookmarkitems == null) {
                     inventory.bookmarkitems = new ArrayList();
                  }

                  PT_STACKABLE ptBookmark = role.getBookmarkBox().getBookmark(index);
                  if (ptBookmark != null) {
                     ptBookmark.count = ptBookmark.count + count;
                  } else {
                     ptBookmark = new PT_STACKABLE();
                     ptBookmark.index = index;
                     ptBookmark.count = count;
                  }

                  role.getBookmarkBox().putBookmark(ptBookmark);
                  PT_STACKABLE ptBookmark1 = new PT_STACKABLE();
                  ptBookmark1.count = count;
                  ptBookmark1.index = index;
                  inventory.bookmarkitems.add(ptBookmark1);
                  break;
               case 1009:
                  if (inventory.carditems == null) {
                     inventory.carditems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableCard = role.getCardBox().getCard(index);
                  if (ptStackableCard != null) {
                     ptStackableCard.count = ptStackableCard.count + count;
                  } else {
                     ptStackableCard = new PT_STACKABLE();
                     ptStackableCard.index = index;
                     ptStackableCard.count = count;
                  }

                  role.getCardBox().addCard(ptStackableCard);
                  PT_STACKABLE ptStackableCard1 = new PT_STACKABLE();
                  ptStackableCard1.count = count;
                  ptStackableCard1.index = index;
                  inventory.carditems.add(ptStackableCard1);
                  break;
               case 10000:
                  this.logger.error("UNREACH==mailGet 不应该有伤害字体==" + index);
                  break;
               case 10001:
                  this.logger.error("UNREACH==mailGet 不应该有聊天框==" + index);
                  break;
               case 10002:
                  this.logger.error("UNREACH==mailGet 不应该有角色框==" + index);
                  break;
               default:
                  this.logger.error("UNREACH==mailGet==type==" + type + "==index==" + index);
            }
         }
      } else {
         int slotindex = 1;
         if (mail.equipitems != null) {
            for(PT_EQUIP ptPostEquip : mail.equipitems) {
               role.getEquipBox().addEquip(ptPostEquip);
               if (inventory.equipitems == null) {
                  inventory.equipitems = new ArrayList();
               }

               inventory.equipitems.add(ptPostEquip);
               PT_SELECTED_ITEM psi = new PT_SELECTED_ITEM();
               psi.slotindex = slotindex++;
               psi.count = 1;
               psi.index = ptPostEquip.index;
               psi.guid = ptPostEquip.guid;
               resMailGet.remaineditems.add(psi);
            }
         }

         if (mail.avataritems != null) {
            for(PT_AVATAR_ITEM ptAvatarItem : mail.avataritems) {
               role.getAvatarBox().addAvatar(ptAvatarItem);
               if (inventory.avataritems == null) {
                  inventory.avataritems = new ArrayList();
               }

               inventory.avataritems.add(ptAvatarItem);
               PT_SELECTED_ITEM psi = new PT_SELECTED_ITEM();
               psi.slotindex = slotindex++;
               psi.count = 1;
               psi.index = ptAvatarItem.index;
               psi.guid = ptAvatarItem.guid;
               resMailGet.remaineditems.add(psi);
            }
         }

         if (mail.titleitems != null) {
            for(PT_EQUIP title : mail.titleitems) {
               role.getTitleBox().addTitle(title);
               if (inventory.titleitems == null) {
                  inventory.titleitems = new ArrayList();
               }

               inventory.titleitems.add(title);
               PT_SELECTED_ITEM psi = new PT_SELECTED_ITEM();
               psi.slotindex = slotindex++;
               psi.count = 1;
               psi.index = title.index;
               psi.guid = title.guid;
               resMailGet.remaineditems.add(psi);
            }
         }
      }

      PT_ITEM_REWARD_INFO rewards = new PT_ITEM_REWARD_INFO();
      rewards.inventory = inventory;
      ptContentsRewardInfo.items = rewards;
      ptCurrencyRewardInfo.currency = ptMoneyItems;
      ptCurrencyRewardInfo1.currency = ptMoneyItemsNew;
      ptContentsRewardInfo.paymentcurrency = ptCurrencyRewardInfo1;
      ptContentsRewardInfo.currency = ptCurrencyRewardInfo;
      resMailGet.rewards = ptContentsRewardInfo;
      if (reqMailGet.type == null) {
         role.getMailBox().removeMail(guid);
      } else if (reqMailGet.type == 1) {
         resMailGet.type = reqMailGet.type;
         account.getMailBox().removeMail(guid);
      }

      if (mailtype == 0) {
         role.save();
      } else {
         role.save();
         account.save();
      }

      resMailGet.transId = reqMailGet.transId;
      MessagePusher.pushMessage((IoSession)session, resMailGet);
   }

   @RequestMapping
   public void ReqMailRead(IoSession session, REQ_MAIL_READ reqMailRead) {
      Long guid = reqMailRead.guid;
      RES_MAIL_READ resMailRead = new RES_MAIL_READ();
      resMailRead.guid = guid;
      resMailRead.transId = reqMailRead.transId;
      MessagePusher.pushMessage((IoSession)session, resMailRead);
   }

   @RequestMapping
   public void ReqLoadWeddingInvitationList(IoSession session, REQ_LOAD_WEDDING_INVITATION_LIST reqLoadWeddingInvitationList) {
      RES_LOAD_WEDDING_INVITATION_LIST resLoadWeddingInvitationList = new RES_LOAD_WEDDING_INVITATION_LIST();
      resLoadWeddingInvitationList.transId = reqLoadWeddingInvitationList.transId;
      MessagePusher.pushMessage((IoSession)session, resLoadWeddingInvitationList);
   }

   @RequestMapping
   public void ReqMailList(IoSession session, REQ_MAIL_LIST reqMailList) {
      Role role = SessionUtils.getRoleBySession(session);
      if (role != null) {
         if (reqMailList.type == null) {
            RES_MAIL_LIST resMailList0 = new RES_MAIL_LIST();
            Map<Long, PT_POST_ALL_LIST> mailBox = role.getMailBox().getAllMail();
            List<PT_POST_ALL_LIST> ptPostAllLists = new ArrayList();
            if (mailBox.size() > 0) {
               for(Map.Entry<Long, PT_POST_ALL_LIST> entry : mailBox.entrySet()) {
                  PT_POST_ALL_LIST ptPostAllList = (PT_POST_ALL_LIST)entry.getValue();
                  ptPostAllLists.add(ptPostAllList);
               }
            }

            resMailList0.count = ptPostAllLists.size();
            resMailList0.postallist = ptPostAllLists;
            resMailList0.transId = reqMailList.transId;
            MessagePusher.pushMessage((IoSession)session, resMailList0);
         } else if (reqMailList.type == 1) {
            Account account = SessionUtils.getAccountBySession(session);
            RES_MAIL_LIST resMailList0 = new RES_MAIL_LIST();
            new HashMap();
            Object mailBox;
            if (account.getMailBox() == null) {
               mailBox = new HashMap();
            } else {
               mailBox = account.getMailBox().getAllMail();
            }

            if (mailBox == null) {
               mailBox = new HashMap();
            }

            List<PT_POST_ALL_LIST> ptPostAllLists = new ArrayList();
            if (((Map)mailBox).size() > 0) {
               for(Map.Entry<Long, PT_POST_ALL_LIST> entry : ((Map<Long, PT_POST_ALL_LIST>)mailBox).entrySet()) {
                  PT_POST_ALL_LIST ptPostAllList = (PT_POST_ALL_LIST)entry.getValue();
                  ptPostAllLists.add(ptPostAllList);
               }
            }

            resMailList0.count = ptPostAllLists.size();
            resMailList0.postallist = ptPostAllLists;
            resMailList0.type = 1;
            resMailList0.transId = reqMailList.transId;
            MessagePusher.pushMessage((IoSession)session, resMailList0);
         } else if (reqMailList.type == 2) {
            RES_MAIL_LIST resMailList0 = new RES_MAIL_LIST();
            List<PT_POST_ALL_LIST> ptPostAllListList1 = new ArrayList();
            resMailList0.postallist = ptPostAllListList1;
            resMailList0.type = 2;
            resMailList0.transId = reqMailList.transId;
            MessagePusher.pushMessage((IoSession)session, resMailList0);
         }

      }
   }

   @RequestMapping
   public void REQ_MAIL_ALL_DELETE(IoSession session, REQ_MAIL_ALL_DELETE reqMailAllDelete) {
      RES_MAIL_ALL_DELETE resMailAllDelete = new RES_MAIL_ALL_DELETE();
      resMailAllDelete.transId = reqMailAllDelete.transId;
      MessagePusher.pushMessage((IoSession)session, resMailAllDelete);
   }

   @RequestMapping
   public void ReqTargetUserDetailInfo(IoSession session, REQ_TARGET_USER_DETAIL_INFO req_target_user_detail_info) {
      long guid = req_target_user_detail_info.targetguid;
      IoSession session1 = SessionManager.INSTANCE.getSessionBy(guid);
      Role targetRole = (Role)DataCache.ONLINE_ROLES.get(guid);
      RES_TARGET_USER_DETAIL_INFO res_target_user_detail_info = new RES_TARGET_USER_DETAIL_INFO();
      res_target_user_detail_info.guid = targetRole.getUid();
      res_target_user_detail_info.world = 1;
      res_target_user_detail_info.name = targetRole.getName();
      res_target_user_detail_info.exp = targetRole.getExp();
      res_target_user_detail_info.level = targetRole.getLevel();
      res_target_user_detail_info.job = targetRole.getJob();
      res_target_user_detail_info.growtype = targetRole.getGrowtype();
      res_target_user_detail_info.secgrowtype = targetRole.getSecgrowtype();
      res_target_user_detail_info.equipscore = targetRole.getEquipscore();
      res_target_user_detail_info.adventureunionlevel = 1;
      res_target_user_detail_info.characlistcount = 1;
      res_target_user_detail_info.equiplist = targetRole.getEquippedBox().getEquipList(targetRole);
      res_target_user_detail_info.avatarlist = targetRole.getEquippedBox().getAvatarlist();
      res_target_user_detail_info.avatarskinlist = targetRole.getEquippedBox().getAvatarskinlist();
      PT_SKILL_INFO pt_skill_info = new PT_SKILL_INFO();
      SkillBox skillBox = targetRole.getSkillBox();
      SkillslotBox skilllotBox = targetRole.getSkillslotBox();
      pt_skill_info.sp = skillBox.getSp();
      pt_skill_info.skilllist = skillBox.getSkills();
      pt_skill_info.skillslot = skilllotBox.getSkillslot();
      res_target_user_detail_info.skill = pt_skill_info;
      res_target_user_detail_info.creditscore = 351;
      res_target_user_detail_info.communionlevel = 1;
      res_target_user_detail_info.transId = req_target_user_detail_info.transId;
      MessagePusher.pushMessage((IoSession)session, res_target_user_detail_info);
   }

   @RequestMapping
   public void ReqLeaveFromTown(IoSession session, REQ_LEAVE_FROM_TOWN reqLeaveFromTown) {
      RES_LEAVE_FROM_TOWN res_leave_from_town = new RES_LEAVE_FROM_TOWN();
      res_leave_from_town.transId = reqLeaveFromTown.transId;
      MessagePusher.pushMessage((IoSession)session, res_leave_from_town);
      Role role = SessionUtils.getRoleBySession(session);
      this.mapService.leaveTown(role);
   }

   @RequestMapping
   public void ReqTownUserGuidList(IoSession session, REQ_TOWN_USER_GUID_LIST reqTownUserGuidList) {
      int posx = 0;
      if (reqTownUserGuidList.posx != null) {
         posx = reqTownUserGuidList.posx;
      }

      int posy = 0;
      if (reqTownUserGuidList.posy != null) {
         posy = reqTownUserGuidList.posy;
      }

      int count = 0;
      if (reqTownUserGuidList.count != null) {
         count = reqTownUserGuidList.count;
      }

      Role role = SessionUtils.getRoleBySession(session);
      role.getPos().setX(posx);
      role.getPos().setY(posy);
      this.mapService.move(role);
      List<Role> userList = this.mapService.getNearbyRoles(role);
      RES_TOWN_USER_GUID_LIST res_town_user_guid_list = new RES_TOWN_USER_GUID_LIST();
      res_town_user_guid_list.charlist = new ArrayList();
      int index = 0;

      for(Role r : userList) {
         int x = r.getPos().getX();
         int y = r.getPos().getY();
         PT_CHARACTER_GUID pt_character_guid = new PT_CHARACTER_GUID();
         pt_character_guid.charguid = r.getUid();
         pt_character_guid.type = 3;
         pt_character_guid.posx = x;
         pt_character_guid.posy = y;
         res_town_user_guid_list.charlist.add(pt_character_guid);
         ++index;
         if (index >= count) {
            break;
         }
      }

      res_town_user_guid_list.transId = reqTownUserGuidList.transId;
      MessagePusher.pushMessage((IoSession)session, res_town_user_guid_list);
   }

   @RequestMapping
   public void ReqCharacterInfo(IoSession session, REQ_CHARACTER_INFO req_character_info) {
      RES_CHARACTER_INFO res_character_info = new RES_CHARACTER_INFO();
      res_character_info.charlist = new ArrayList();

      for(PT_CHARACTER_GUID pt : req_character_info.charlist) {
         long charguid = pt.charguid;
         PT_CHARACTER_INFO pt_character_info = this.roleService.getPtCharacterInfo(charguid);
         if (pt_character_info != null) {
            res_character_info.charlist.add(pt_character_info);
         }
      }

      res_character_info.transId = req_character_info.transId;
      MessagePusher.pushMessage((IoSession)session, res_character_info);
   }

   @RequestMapping
   public void ReqGameFlowTlog(IoSession session, REQ_GAME_FLOW_TLOG req_game_flow_tlog) {
   }

   @RequestMapping
   public void REQ_STANDBY(IoSession session, REQ_STANDBY req_standby) {
      RES_STANDBY resStandby = new RES_STANDBY();
      resStandby.standby = 1;
      resStandby.transId = req_standby.transId;
      MessagePusher.pushMessage((IoSession)session, resStandby);
   }

   @RequestMapping
   public void ReqLogin(IoSession session, REQ_LOGIN reqLogin) {
      Integer agetype = reqLogin.agetype;
      String clientIP = reqLogin.clientIP;
      String countrycode = reqLogin.countrycode;
      String openid = reqLogin.openid;
      Integer platID = reqLogin.platID;
      String token = reqLogin.token;
      Integer toyplatid = reqLogin.toyplatid;
      int type = reqLogin.type;
      String version = reqLogin.version;
      Account account = this.accountService.getAccount(openid);
      if (account == null) {
         this.logger.error("首次登录=={}", openid);
         AccountLogin accountLogin = this.accountService.queryAccount(openid);
         if (accountLogin == null) {
            this.logger.error("ERROR==登录服AccountLogin为空=={}", openid);
            return;
         }

         account = new Account();
         account.setId(accountLogin.getId());
         account.setAccountkey(IdGenerator.getNextId() + "");
         account.setAccumulatecera(0L);
         account.setUserID(accountLogin.getUserID());
         account.setChannelNo(accountLogin.getChannelNo());
         account.setIsStop(accountLogin.getIsStop());
         account.setPasswd(accountLogin.getPasswd());
         account.setPrivilege(accountLogin.getPrivilege());
         account.setZhanlingexp(0);
         AccountMoneyBox accountMoneyBox = new AccountMoneyBox();
         accountMoneyBox.putCurrency(2013103791, 10);
         accountMoneyBox.putCurrency(2013105115, 10);
         account.setMoneyBox(accountMoneyBox);
         AdventureUnionInfo adventureUnionInfo = new AdventureUnionInfo();
         adventureUnionInfo.day = 1;
         adventureUnionInfo.level = 1;
         adventureUnionInfo.exp = 0L;
         account.setAdventureUnionInfo(adventureUnionInfo);
         account = this.accountService.addAccount(account);
      }

      this.logger.error("openid=={}==account=={}", openid, account);
      session.setAttribute(SessionProperties.ACCOUNT_OPENID, openid);
      RES_LOGIN resLogin = new RES_LOGIN();
      resLogin.authkey = UUID.randomUUID().toString().toLowerCase();
      resLogin.accountkey = account.getAccountkey();
      session.setAttribute(SessionProperties.AUTH_KEY, resLogin.authkey);
      resLogin.encrypt = true;
      resLogin.servertime = TimeUtil.currS();
      resLogin.localtime = TimeUtil.localTime();
      List<PT_CHANNEL_INFO> channel = new ArrayList();
      int size = DataCache.ID_SERVER.size();

      for(int i = 0; i < size; ++i) {
         Server server = (Server)DataCache.ID_SERVER.get(i + 1);
         PT_CHANNEL_INFO ptChannelInfo = new PT_CHANNEL_INFO();
         ptChannelInfo.channel = i + 1;
         ptChannelInfo.ip = server.getIp();
         ptChannelInfo.port = server.getPort();
         ptChannelInfo.world = 1;
         channel.add(ptChannelInfo);
      }

      resLogin.channel = channel;
      resLogin.worldid = 1;
      resLogin.transId = reqLogin.transId;
      resLogin.seeds = new ArrayList();
      MessagePusher.pushMessage((IoSession)session, resLogin);
      // RES_PING resPing = new RES_PING();
      // MessagePusher.pushMessage((IoSession)session, resPing);
   }

   @RequestMapping
   public void REQ_REFRESH_TARGET_CHARACTER_TOWN_INFO(IoSession session, REQ_REFRESH_TARGET_CHARACTER_TOWN_INFO req_refresh_target_character_town_info) {
      RES_REFRESH_TARGET_CHARACTER_TOWN_INFO res_refresh_target_character_town_info = new RES_REFRESH_TARGET_CHARACTER_TOWN_INFO();
      Role role = SessionUtils.getRoleBySession(session);
      res_refresh_target_character_town_info.charguid = req_refresh_target_character_town_info.charguid;
      DungeonParty dungeonParty = role.getDungeonParty();
      if (dungeonParty != null) {
         res_refresh_target_character_town_info.partyguid = dungeonParty.partyguid;
         res_refresh_target_character_town_info.partyleaderguid = dungeonParty.charguid;
      }

      res_refresh_target_character_town_info.transId = req_refresh_target_character_town_info.transId;
      MessagePusher.pushMessage((IoSession)session, res_refresh_target_character_town_info);
   }

   @RequestMapping
   public void ReqChannelList(IoSession session, REQ_CHANNEL_LIST reqChannelList) {
      RES_CHANNEL_LIST resChannelList = new RES_CHANNEL_LIST();
      new ArrayList();
      List<PT_INTEGRATION_WORLD> intergrations = new ArrayList();
      PT_INTEGRATION_WORLD ptIntegrationWorld = new PT_INTEGRATION_WORLD();
      ptIntegrationWorld.world = 2021;
      intergrations.add(ptIntegrationWorld);
      resChannelList.integrations = intergrations;
      resChannelList.count = 1;
      List<PT_CHANNEL> list = new ArrayList();
      int size = DataCache.ID_SERVER.size();

      for(int i = 0; i < size; ++i) {
         Server server = (Server)DataCache.ID_SERVER.get(i + 1);
         PT_CHANNEL ptChannel = new PT_CHANNEL();
         ptChannel.channel = i + 1;
         ptChannel.iP = server.getIp();
         ptChannel.port = server.getPort();
         ptChannel.saturation = server.getSaturation();
         ptChannel.world = 1;
         list.add(ptChannel);
      }

      resChannelList.list = list;
      new ArrayList();
      resChannelList.transId = reqChannelList.transId;
      MessagePusher.pushMessage((IoSession)session, resChannelList);
   }

   @RequestMapping
   public void ReqContentsPreviewLoad(IoSession session, REQ_CONTENTS_PREVIEW_LOAD reqContentsPreviewLoad) {
      RES_CONTENTS_PREVIEW_LOAD resContentsPreviewLoad0 = new RES_CONTENTS_PREVIEW_LOAD();
      resContentsPreviewLoad0.transId = reqContentsPreviewLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resContentsPreviewLoad0);
   }

   @RequestMapping
   public void ReqTowerInfo(IoSession session, REQ_TOWER_INFO reqTowerInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      TowerInfoBox towerInfoBox = role.getTowerInfoBox();
      RES_TOWER_INFO res_tower_info = new RES_TOWER_INFO();
      int clearFloor = towerInfoBox.getClearfloor();
      if (clearFloor != 0) {
         res_tower_info.clearfloor = clearFloor;
      }

      res_tower_info.clearrate = towerInfoBox.getClearrate();
      res_tower_info.type = reqTowerInfo.type;
      res_tower_info.transId = reqTowerInfo.transId;
      MessagePusher.pushMessage((IoSession)session, res_tower_info);
   }

   @RequestMapping
   public void ReqHiddenChattingLoad(IoSession session, REQ_HIDDEN_CHATTING_LOAD reqHiddenChattingLoad) {
      RES_HIDDEN_CHATTING_LOAD resHiddenChattingLoad0 = new RES_HIDDEN_CHATTING_LOAD();
      resHiddenChattingLoad0.transId = reqHiddenChattingLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resHiddenChattingLoad0);
   }

   @RequestMapping
   public void ReqCollectionLoad(IoSession session, REQ_COLLECTION_LOAD reqCollectionLoad) {
      RES_COLLECTION_LOAD resCollectionLoad0 = new RES_COLLECTION_LOAD();
      resCollectionLoad0.level = 1;
      resCollectionLoad0.transId = reqCollectionLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resCollectionLoad0);
   }

   @RequestMapping
   public void ReqItemUsableList(IoSession session, REQ_ITEM_USABLE_LIST reqItemUsableList) {
      RES_ITEM_USABLE_LIST resItemUsableList0 = new RES_ITEM_USABLE_LIST();
      resItemUsableList0.transId = reqItemUsableList.transId;
      MessagePusher.pushMessage((IoSession)session, resItemUsableList0);
   }

   @RequestMapping
   public void ReqAchievementInfo(IoSession session, REQ_ACHIEVEMENT_INFO reqAchievementInfo) {
      RES_ACHIEVEMENT_INFO resAchievementInfo0 = new RES_ACHIEVEMENT_INFO();
      if (reqAchievementInfo.type != null) {
         resAchievementInfo0.type = reqAchievementInfo.type;
      }

      resAchievementInfo0.transId = reqAchievementInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resAchievementInfo0);
   }

   @RequestMapping
   public void ReqDamageFontTabList(IoSession session, REQ_DAMAGE_FONT_TAB_LIST reqDamageFontTabList) {
      Role role = SessionUtils.getRoleBySession(session);
      DamageBox damageBox = role.getDamageBox();
      List<PT_SKIN_ITEM> list = damageBox.getFonts();
      RES_DAMAGE_FONT_TAB_LIST resDamageFontTabList = new RES_DAMAGE_FONT_TAB_LIST();
      if (!Util.isEmpty(list)) {
         resDamageFontTabList.list = list;
      }

      resDamageFontTabList.transId = reqDamageFontTabList.transId;
      MessagePusher.pushMessage((IoSession)session, resDamageFontTabList);
   }

   @RequestMapping
   public void ReqLoadServerSimpleData(IoSession session, REQ_LOAD_SERVER_SIMPLE_DATA reqLoadServerSimpleData) {
      List<PT_LOAD_SERVER_SIMPLE_DATA> list = reqLoadServerSimpleData.list;
      Role role = SessionUtils.getRoleBySession(session);
      ServerSimpleDataBox serverSimpleDataBox = role.getServerSimpleDataBox();

      for(int i = 0; i < list.size(); ++i) {
         PT_LOAD_SERVER_SIMPLE_DATA ptLoadServerSimpleData = (PT_LOAD_SERVER_SIMPLE_DATA)list.get(i);
         Integer type = ptLoadServerSimpleData.type;
         Integer enumvalue = ptLoadServerSimpleData.enumvalue;
         if (type == null) {
            type = 0;
         }

         ServerSimpleData serverSimpleData = serverSimpleDataBox.getData(type, enumvalue);
         NOTIFY_LOAD_SERVER_SIMPLE_DATA notify_load_server_simple_data = new NOTIFY_LOAD_SERVER_SIMPLE_DATA();
         if (type != 0) {
            notify_load_server_simple_data.type = type;
         }

         notify_load_server_simple_data.enumvalue = enumvalue;
         if (serverSimpleData != null && serverSimpleData.getValue() != null && serverSimpleData.getValue().length() != 0) {
            notify_load_server_simple_data.value = serverSimpleData.getValue();
         }

         notify_load_server_simple_data.transId = i;
         MessagePusher.pushMessage((IoSession)session, notify_load_server_simple_data);
      }

      session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, list.size());
      RES_LOAD_SERVER_SIMPLE_DATA resLoadServerSimpleData = new RES_LOAD_SERVER_SIMPLE_DATA();
      resLoadServerSimpleData.transId = reqLoadServerSimpleData.transId;
      MessagePusher.pushMessage((IoSession)session, resLoadServerSimpleData);
   }

   @RequestMapping
   public void ReqNewContentsList(IoSession session, REQ_NEW_CONTENTS_LIST reqNewContentsList) {
      if (reqNewContentsList.type == null) {
         RES_NEW_CONTENTS_LIST res_new_contents_list = new RES_NEW_CONTENTS_LIST();
         res_new_contents_list.transId = reqNewContentsList.transId;
         MessagePusher.pushMessage((IoSession)session, res_new_contents_list);
      } else if (reqNewContentsList.type == 1) {
         RES_NEW_CONTENTS_LIST res_new_contents_list1 = new RES_NEW_CONTENTS_LIST();
         res_new_contents_list1.type = 1;
         res_new_contents_list1.transId = reqNewContentsList.transId;
         MessagePusher.pushMessage((IoSession)session, res_new_contents_list1);
      }

   }

   @RequestMapping
   public void ReqAchievementList(IoSession session, REQ_ACHIEVEMENT_LIST reqAchievementList) {
      Integer type = reqAchievementList.type;
      if (type == null) {
         String str = "{\"total\": 133, \"list\": [{\"index\": 120801}, {\"index\": 2002120022}, {\"index\": 2002120001}, {\"index\": 2002120002}, {\"index\": 2002120003}, {\"index\": 2002120004}, {\"index\": 2002120005}, {\"index\": 2002120006}, {\"index\": 2002120007}, {\"index\": 2002120008}, {\"index\": 2002120009}, {\"index\": 2002120011}, {\"index\": 2002120012}, {\"index\": 2002120013}, {\"index\": 2002120014}, {\"index\": 2002120015}, {\"index\": 2002120016}, {\"index\": 2002120017}, {\"index\": 2002120018}, {\"index\": 120711}, {\"index\": 120712}, {\"index\": 120713}, {\"index\": 120714}, {\"index\": 120721}, {\"index\": 120722}, {\"index\": 120741}, {\"index\": 120742}, {\"index\": 120743}, {\"index\": 120744}, {\"index\": 120751}, {\"index\": 120752}, {\"index\": 120753}, {\"index\": 120754}, {\"index\": 120755}, {\"index\": 120781}, {\"index\": 120791}, {\"index\": 120792}, {\"index\": 120793}, {\"index\": 120794}, {\"index\": 120795}, {\"index\": 120901}, {\"index\": 1090001}, {\"index\": 1090002}, {\"index\": 101090002}, {\"index\": 101090003}, {\"index\": 101090004}, {\"index\": 101090005}, {\"index\": 101090006}, {\"index\": 101090007}, {\"index\": 101090008}, {\"index\": 101090009}, {\"index\": 101090010}, {\"index\": 101090011}, {\"index\": 101090012}, {\"index\": 101090013}, {\"index\": 101090014}, {\"index\": 101090015}, {\"index\": 101090016}, {\"index\": 200412001}, {\"index\": 200412002}, {\"index\": 200412003}, {\"index\": 200412004}, {\"index\": 2002120019}, {\"index\": 2002120020}, {\"index\": 2002130013}, {\"index\": 2002130014}, {\"index\": 2002130015}, {\"index\": 2002130016}, {\"index\": 2002130017}, {\"index\": 2003130001}, {\"index\": 2003130002}, {\"index\": 2003130003}, {\"index\": 2003130004}, {\"index\": 2003130005}, {\"index\": 2003130006}, {\"index\": 2003130007}, {\"index\": 2003130008}, {\"index\": 2003130009}, {\"index\": 2003130010}, {\"index\": 2003130011}, {\"index\": 130100}, {\"index\": 130800, \"count\": 1, \"clear\": 1, \"cleartime\": \"1658657398\", \"values\": [{\"value\": 1}]}, {\"index\": 2002130002}, {\"index\": 2002130003}, {\"index\": 2002130005}, {\"index\": 2002130006}, {\"index\": 2002130007}, {\"index\": 2002130008}, {\"index\": 2002130009}, {\"index\": 2002130010}, {\"index\": 2002130011}, {\"index\": 110201}, {\"index\": 110202}, {\"index\": 110203}, {\"index\": 110204}, {\"index\": 110205}, {\"index\": 110206}, {\"index\": 110207}, {\"index\": 110208}, {\"index\": 110209}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
         String str2 = "{\"page\": 1, \"total\": 133, \"list\": [{\"index\": 110210}, {\"index\": 2002110001}, {\"index\": 2002110002}, {\"index\": 2002110003}, {\"index\": 2002110004}, {\"index\": 2002110005}, {\"index\": 2002110006}, {\"index\": 2002110007}, {\"index\": 2002110008}, {\"index\": 2002110009}, {\"index\": 2002110010}, {\"index\": 2002110011}, {\"index\": 2002110012}, {\"index\": 2002110013}, {\"index\": 2002110014}, {\"index\": 2002110015}, {\"index\": 2002110016}, {\"index\": 2002110017}, {\"index\": 2002110018}, {\"index\": 2002110019}, {\"index\": 2002110020}, {\"index\": 130801, \"count\": 1, \"clear\": 1, \"cleartime\": \"1658657418\", \"values\": [{\"value\": 1}]}, {\"index\": 130802, \"count\": 1, \"clear\": 1, \"cleartime\": \"1658657418\", \"values\": [{\"value\": 1}]}, {\"index\": 130803}, {\"index\": 130804}, {\"index\": 130805}, {\"index\": 130806}, {\"index\": 130807}, {\"index\": 130808}, {\"index\": 130809}, {\"index\": 130810}, {\"index\": 130811}, {\"index\": 130812}]}";
         RES_ACHIEVEMENT_LIST resAchievementList12 = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str2, RES_ACHIEVEMENT_LIST.class);
         resAchievementList12.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList12);
      } else if (type == 2) {
         String str = "{\"type\": 2, \"total\": 16, \"list\": [{\"index\": 2002360001, \"values\": [{}]}, {\"index\": 300010, \"values\": [{}]}, {\"index\": 200436001, \"values\": [{}]}, {\"index\": 300016, \"values\": [{}]}, {\"index\": 300007, \"values\": [{}]}, {\"index\": 300021, \"values\": [{}]}, {\"index\": 300004, \"values\": [{}]}, {\"index\": 300023, \"values\": [{}]}, {\"index\": 300055, \"values\": [{}]}, {\"index\": 300013, \"values\": [{}]}, {\"index\": 103020001, \"values\": [{}]}, {\"index\": 103020002, \"values\": [{}]}, {\"index\": 210312001, \"values\": [{}]}, {\"index\": 2004340003, \"values\": [{}]}, {\"index\": 2004340002, \"values\": [{}]}, {\"index\": 2004340001, \"values\": [{}]}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
      } else if (type == 3) {
         String str = "{\"type\": 3, \"total\": 32, \"list\": [{\"index\": 200843003, \"values\": [{}]}, {\"index\": 200843004, \"values\": [{}]}, {\"index\": 200843005, \"values\": [{}]}, {\"index\": 200843006, \"values\": [{}]}, {\"index\": 200843011, \"values\": [{}]}, {\"index\": 200843009, \"values\": [{}]}, {\"index\": 200843012, \"values\": [{}]}, {\"index\": 200843014, \"values\": [{}]}, {\"index\": 200843013, \"values\": [{}]}, {\"index\": 200843015, \"values\": [{}]}, {\"index\": 210344001, \"values\": [{}]}, {\"index\": 104040023, \"values\": [{}]}, {\"index\": 101190006, \"values\": [{}]}, {\"index\": 101190002, \"values\": [{}]}, {\"index\": 104040001, \"values\": [{}]}, {\"index\": 101190005, \"values\": [{}]}, {\"index\": 101190003, \"values\": [{}]}, {\"index\": 101190004, \"values\": [{}]}, {\"index\": 101190001, \"values\": [{}]}, {\"index\": 101190018, \"values\": [{}]}, {\"index\": 104040002, \"values\": [{}]}, {\"index\": 101190020, \"values\": [{}]}, {\"index\": 101190019, \"values\": [{}]}, {\"index\": 101190017, \"values\": [{}]}, {\"index\": 104040016, \"values\": [{}]}, {\"index\": 104040017, \"values\": [{}]}, {\"index\": 104040015, \"values\": [{}]}, {\"index\": 104040019, \"values\": [{}]}, {\"index\": 104040013, \"values\": [{}]}, {\"index\": 104040020, \"values\": [{}]}, {\"index\": 104040018, \"values\": [{}]}, {\"index\": 104040014, \"values\": [{}]}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
      } else if (type == 1) {
         String str = "{\"type\": 1, \"total\": 230, \"list\": [{\"index\": 220091}, {\"index\": 220092}, {\"index\": 220093}, {\"index\": 220094}, {\"index\": 220095}, {\"index\": 220096}, {\"index\": 220097}, {\"index\": 220098}, {\"index\": 220099}, {\"index\": 220100}, {\"index\": 900011}, {\"index\": 900013}, {\"index\": 900014}, {\"index\": 102090001}, {\"index\": 102090002}, {\"index\": 102090003}, {\"index\": 102090004}, {\"index\": 102090005}, {\"index\": 102090006}, {\"index\": 102090007}, {\"index\": 102090008}, {\"index\": 102090009}, {\"index\": 102090010}, {\"index\": 102090011}, {\"index\": 102090012}, {\"index\": 102090013}, {\"index\": 102090014}, {\"index\": 102090015}, {\"index\": 102090016}, {\"index\": 102090017}, {\"index\": 102090018}, {\"index\": 102090019}, {\"index\": 102090020}, {\"index\": 102090021}, {\"index\": 102090022}, {\"index\": 102090023}, {\"index\": 102090024}, {\"index\": 102090025}, {\"index\": 102090026}, {\"index\": 102090027}, {\"index\": 102090028}, {\"index\": 102090029}, {\"index\": 102090030}, {\"index\": 102090031}, {\"index\": 102090032}, {\"index\": 102090033}, {\"index\": 102090034}, {\"index\": 102090035}, {\"index\": 102090036}, {\"index\": 102090037}, {\"index\": 102090038}, {\"index\": 102090039}, {\"index\": 102090040}, {\"index\": 102090041}, {\"index\": 102090042}, {\"index\": 102090043}, {\"index\": 102090044}, {\"index\": 102090045}, {\"index\": 102090046}, {\"index\": 102090047}, {\"index\": 102090048}, {\"index\": 102090049}, {\"index\": 102090050}, {\"index\": 102090051}, {\"index\": 102090052}, {\"index\": 102090053}, {\"index\": 102090054}, {\"index\": 102090055}, {\"index\": 102090056}, {\"index\": 102090057}, {\"index\": 102090058}, {\"index\": 102090059}, {\"index\": 102090060}, {\"index\": 2002220001}, {\"index\": 2002220002}, {\"index\": 2002220003}, {\"index\": 2002220004}, {\"index\": 2002220005}, {\"index\": 2002220006}, {\"index\": 2002220007}, {\"index\": 2002220008}, {\"index\": 2002220009}, {\"index\": 2002220010}, {\"index\": 220001}, {\"index\": 220002}, {\"index\": 220003}, {\"index\": 220004}, {\"index\": 220005}, {\"index\": 220006}, {\"index\": 220007}, {\"index\": 220008}, {\"index\": 220009}, {\"index\": 220010}, {\"index\": 220021}, {\"index\": 220022}, {\"index\": 220023}, {\"index\": 220024}, {\"index\": 220025}, {\"index\": 220026}, {\"index\": 220027}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
         String str2 = "{\"type\": 1, \"page\": 1, \"total\": 230, \"list\": [{\"index\": 220028}, {\"index\": 220029}, {\"index\": 220030}, {\"index\": 220041}, {\"index\": 220042}, {\"index\": 220043}, {\"index\": 220044}, {\"index\": 220045}, {\"index\": 220046}, {\"index\": 220047}, {\"index\": 220048}, {\"index\": 220049}, {\"index\": 220050}, {\"index\": 220061}, {\"index\": 220062}, {\"index\": 220063}, {\"index\": 220064}, {\"index\": 220065}, {\"index\": 220066}, {\"index\": 220067}, {\"index\": 220068}, {\"index\": 220069}, {\"index\": 220070}, {\"index\": 220071}, {\"index\": 220072}, {\"index\": 220073}, {\"index\": 220074}, {\"index\": 220075}, {\"index\": 220076}, {\"index\": 220077}, {\"index\": 220078}, {\"index\": 220079}, {\"index\": 220080}, {\"index\": 220081}, {\"index\": 220082}, {\"index\": 220083}, {\"index\": 220084}, {\"index\": 220085}, {\"index\": 220086}, {\"index\": 220087}, {\"index\": 220088}, {\"index\": 220089}, {\"index\": 220090}, {\"index\": 900015}, {\"index\": 200422001}, {\"index\": 200422002}, {\"index\": 200422003}, {\"index\": 200422004}, {\"index\": 200422005}, {\"index\": 200422006}, {\"index\": 200422007}, {\"index\": 200422008}, {\"index\": 200422009}, {\"index\": 200422010}, {\"index\": 250110, \"values\": [{\"value\": 1010}]}, {\"index\": 250111, \"values\": [{\"value\": 1010}]}, {\"index\": 250112, \"values\": [{\"value\": 1010}]}, {\"index\": 250113, \"values\": [{\"value\": 1010}]}, {\"index\": 250114}, {\"index\": 250115}, {\"index\": 250116}, {\"index\": 250117}, {\"index\": 200822014}, {\"index\": 230130}, {\"index\": 230131}, {\"index\": 230132}, {\"index\": 230133}, {\"index\": 230134}, {\"index\": 230135}, {\"index\": 230136}, {\"index\": 230137}, {\"index\": 230138}, {\"index\": 230139}, {\"index\": 230140, \"values\": [{\"value\": 1}]}, {\"index\": 230141, \"values\": [{\"value\": 1}]}, {\"index\": 230142, \"values\": [{\"value\": 1}]}, {\"index\": 230143, \"values\": [{\"value\": 1}]}, {\"index\": 230144, \"values\": [{\"value\": 1}]}, {\"index\": 230145, \"values\": [{\"value\": 1}]}, {\"index\": 230146, \"values\": [{\"value\": 1}]}, {\"index\": 230147, \"values\": [{\"value\": 1}]}, {\"index\": 230148, \"values\": [{\"value\": 1}]}, {\"index\": 230149, \"values\": [{\"value\": 1}]}, {\"index\": 900002}, {\"index\": 900010, \"values\": [{\"value\": 1}]}, {\"index\": 2002230015}, {\"index\": 2002230016}, {\"index\": 2002230017}, {\"index\": 2002230018}, {\"index\": 2002230019}, {\"index\": 2002230020}, {\"index\": 2002230021}, {\"index\": 2002230022}, {\"index\": 2002230023}, {\"index\": 2002230024}, {\"index\": 2002230025}, {\"index\": 2002230026}, {\"index\": 2002230027}, {\"index\": 2002230028}, {\"index\": 2002230029}]}";
         RES_ACHIEVEMENT_LIST resAchievementList2 = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str2, RES_ACHIEVEMENT_LIST.class);
         resAchievementList2.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList2);
         String str3 = "{\"type\": 1, \"page\": 2, \"total\": 230, \"list\": [{\"index\": 2002230030}, {\"index\": 2002230031}, {\"index\": 2002230032}, {\"index\": 2002230033}, {\"index\": 2002230034}, {\"index\": 2002230035}, {\"index\": 2002230036}, {\"index\": 2002230037}, {\"index\": 2002230038}, {\"index\": 2002230039}, {\"index\": 2002230040}, {\"index\": 2002230041}, {\"index\": 2002230042}, {\"index\": 2002230043}, {\"index\": 2002230044}, {\"index\": 2002230045}, {\"index\": 2003230004}, {\"index\": 2003230005}, {\"index\": 2003230006}, {\"index\": 2003230007}, {\"index\": 2003230008}, {\"index\": 2003230009}, {\"index\": 2003230010}, {\"index\": 2003230011}, {\"index\": 2003230012}, {\"index\": 2003230013}, {\"index\": 2003230014}, {\"index\": 2003230015}, {\"index\": 2003230016}, {\"index\": 230150}]}";
         RES_ACHIEVEMENT_LIST resAchievementList3 = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str3, RES_ACHIEVEMENT_LIST.class);
         resAchievementList3.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList3);
      } else if (type == 4) {
         String str = "{\"type\": 4}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
      } else if (type == 5) {
         String str = "{\"type\": 5, \"total\": 7, \"list\": [{\"index\": 106170001, \"values\": [{}]}, {\"index\": 106170016, \"values\": [{}]}, {\"index\": 106170009, \"values\": [{}]}, {\"index\": 106170015, \"values\": [{}]}, {\"index\": 106220006, \"values\": [{}]}, {\"index\": 106220005, \"values\": [{}]}, {\"index\": 106220004, \"values\": [{}]}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
      } else if (type == 6) {
         String str = "{\"type\": 6, \"total\": 4, \"list\": [{\"index\": 107170001, \"values\": [{}]}, {\"index\": 107170013, \"values\": [{\"value\": 1010}]}, {\"index\": 107170002, \"values\": [{}]}, {\"index\": 107170004, \"values\": [{}]}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
      } else if (type == 7) {
         String str = "{\"type\": 7, \"total\": 5, \"list\": [{\"index\": 108170000, \"values\": [{}]}, {\"index\": 108170007, \"values\": [{}]}, {\"index\": 108170001, \"values\": [{}]}, {\"index\": 108170004, \"values\": [{}]}, {\"index\": 108170002, \"values\": [{}]}]}";
         RES_ACHIEVEMENT_LIST resAchievementList = (RES_ACHIEVEMENT_LIST)JSON.parseObject(str, RES_ACHIEVEMENT_LIST.class);
         resAchievementList.transId = reqAchievementList.transId;
         MessagePusher.pushMessage((IoSession)session, resAchievementList);
      }

   }

   @RequestMapping
   public void ReqDailyReset(IoSession session, REQ_DAILY_RESET reqDailyReset) {
      RES_DAILY_RESET resDailyReset0 = new RES_DAILY_RESET();
      resDailyReset0.error = 2;
      resDailyReset0.transId = reqDailyReset.transId;
      MessagePusher.pushMessage((IoSession)session, resDailyReset0);
   }

   @RequestMapping
   public void ReqEventList(IoSession session, REQ_EVENT_LIST reqEventList) {
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      Long charguid = role.getUid();
      String jsonStr = "{\"account\": [{\"index\": 191001100, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}, {\"index\": 31013, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}, {\"index\": 191001001, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\", \"sub\": [{\"index\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}, {\"index\": 2, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}, {\"index\": 3, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}]}, {\"index\": 191001002, \"status\": 1, \"dateStart\": \"1657141200\", \"dateEnd\": \"1660165199\", \"sub\": [{\"index\": 106220006, \"dateStart\": \"1657141200\", \"dateEnd\": \"1660165199\"}, {\"index\": 106220005, \"dateStart\": \"1657141200\", \"dateEnd\": \"1660165199\"}, {\"index\": 106220004, \"dateStart\": \"1657141200\", \"dateEnd\": \"1660165199\"}]}, {\"index\": 19002, \"value\": 3, \"dateStart\": \"1658657643\", \"dateEnd\": \"4086449999\", \"sub\": [{\"index\": 1, \"status\": 1, \"dateStart\": \"1648047600\", \"dateEnd\": \"4086449999\"}, {\"index\": 2, \"status\": 1, \"dateStart\": \"1648047600\", \"dateEnd\": \"4086449999\"}, {\"index\": 3, \"status\": 1, \"dateStart\": \"1648047600\", \"dateEnd\": \"4086449999\"}]}, {\"index\": 191001003, \"status\": 1, \"count\": 1, \"value\": 2022062418, \"dateStart\": \"1657746000\", \"dateEnd\": \"1660165199\", \"sub\": [{\"index\": 1, \"status\": 2, \"dateStart\": \"1657746000\", \"dateEnd\": \"1660165199\"}]}, {\"index\": 19005, \"status\": 1, \"dateStart\": \"1648069200\", \"dateEnd\": \"4102347599\", \"sub\": [{\"index\": 1, \"status\": 1, \"dateStart\": \"1648069200\", \"dateEnd\": \"4102347599\"}, {\"index\": 2, \"status\": 2, \"dateStart\": \"1648069200\", \"dateEnd\": \"4102347599\"}]}, {\"index\": 191001000, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}, {\"index\": 191000802, \"status\": 1, \"dateStart\": \"1658091600\", \"dateEnd\": \"1658955599\"}, {\"index\": 191000701, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}, {\"index\": 31011, \"status\": 1, \"dateStart\": \"1657746000\", \"dateEnd\": \"1658955600\"}, {\"index\": 31010, \"status\": 1, \"dateStart\": \"1651093200\", \"dateEnd\": \"4086449999\"}, {\"index\": 31006, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1664398799\"}, {\"index\": 191000601, \"status\": 1, \"count\": 1, \"value\": 2022062418, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\", \"sub\": [{\"index\": 1, \"status\": 2, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 2, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 3, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 4, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 5, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 6, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 7, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}]}, {\"index\": 191000100, \"status\": 1, \"count\": 1, \"value\": 2022062418, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\", \"sub\": [{\"index\": 1, \"status\": 2, \"value\": 25, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 2, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 3, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 4, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 5, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 6, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 7, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 8, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 9, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 10, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 11, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 12, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 13, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 14, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 15, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 16, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 17, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 18, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 19, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 20, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 21, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 22, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 23, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 24, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 25, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 26, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 27, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 28, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 29, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 30, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 31, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 32, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}]}, {\"index\": 19003, \"status\": 1, \"count\": 1, \"value\": 2022062418, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\", \"sub\": [{\"index\": 1, \"status\": 2, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 2, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 3, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 4, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 5, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 6, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}, {\"index\": 7, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1658955599\"}]}, {\"index\": 31014, \"status\": 1, \"dateStart\": \"1656536400\", \"dateEnd\": \"1660165199\"}], \"characters\": [{\"guid\": \"" + charguid + "\", \"eventlist\": [{\"index\": 8001, \"dateStart\": \"1651093200\", \"dateEnd\": \"4086449999\"}]}], \"scheduler\": [{\"index\": 191001100, \"startdate\": \"1656536400\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 31013, \"startdate\": \"1656536400\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 191001001, \"startdate\": \"1656536400\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 191001000, \"startdate\": \"1656536400\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 191000801, \"startdate\": \"1658091600\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 191000802, \"startdate\": \"1658091600\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 20001, \"startdate\": \"1651352400\", \"enddate\": \"1669841999\", \"active\": true}, {\"index\": 19007}, {\"index\": 191000701, \"startdate\": \"1656536400\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 190000001, \"startdate\": \"1652907600\", \"enddate\": \"1655326799\", \"active\": true}, {\"index\": 31011, \"startdate\": \"1657746000\", \"enddate\": \"1658955600\", \"active\": true}, {\"index\": 31010, \"startdate\": \"1651093200\", \"enddate\": \"4086449999\", \"active\": true}, {\"index\": 31007, \"startdate\": \"1648069200\", \"enddate\": \"1653944399\", \"active\": true}, {\"index\": 31006, \"startdate\": \"1656536400\", \"enddate\": \"1664398799\", \"active\": true}, {\"index\": 31005, \"startdate\": \"1649883600\", \"enddate\": \"1651093200\", \"active\": true}, {\"index\": 191001002, \"startdate\": \"1657141200\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 31012, \"startdate\": \"1653944400\", \"enddate\": \"1656536399\", \"active\": true}, {\"index\": 31008, \"startdate\": \"1652907600\", \"enddate\": \"1653944399\", \"active\": true}, {\"index\": 31004, \"startdate\": \"1649278800\", \"enddate\": \"1651093199\", \"active\": true}, {\"index\": 30001, \"startdate\": \"1656536400\", \"enddate\": \"1664398799\", \"active\": true}, {\"index\": 19002, \"startdate\": \"1648047600\", \"enddate\": \"4086449999\", \"active\": true}, {\"index\": 191000901, \"startdate\": \"1648069200\", \"enddate\": \"1656536399\", \"active\": true}, {\"index\": 19001, \"startdate\": \"1648069200\", \"enddate\": \"1656536399\", \"active\": true}, {\"index\": 191001003, \"startdate\": \"1657746000\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 191000601, \"startdate\": \"1656536400\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 191000100, \"startdate\": \"1656536400\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 19006, \"startdate\": \"1653944400\", \"enddate\": \"1656536399\", \"active\": true}, {\"index\": 19003, \"startdate\": \"1656536400\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 10002, \"startdate\": \"1656536400\", \"enddate\": \"1661893199\", \"active\": true}, {\"index\": 10001, \"startdate\": \"1656018000\", \"enddate\": \"1656536399\", \"active\": true}, {\"index\": 10000, \"startdate\": \"1648069200\", \"enddate\": \"1653944399\", \"active\": true}, {\"index\": 7005, \"startdate\": \"1655931600\", \"enddate\": \"1656514799\", \"active\": true}, {\"index\": 7004, \"startdate\": \"1649883600\", \"enddate\": \"1652302801\", \"active\": true}, {\"index\": 9000, \"startdate\": \"1651093200\", \"enddate\": \"1653944399\", \"active\": true}, {\"index\": 7001, \"startdate\": \"1656536400\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 31009, \"startdate\": \"1651093200\", \"enddate\": \"4086449999\", \"active\": true}, {\"index\": 8001, \"startdate\": \"1651093200\", \"enddate\": \"4086449999\", \"active\": true}, {\"index\": 8000, \"startdate\": \"1651093200\", \"enddate\": \"4086449999\", \"active\": true}, {\"index\": 7000, \"startdate\": \"1656536400\", \"enddate\": \"1658955599\", \"active\": true}, {\"index\": 19005, \"startdate\": \"1648069200\", \"enddate\": \"4102347599\", \"active\": true}, {\"index\": 31014, \"startdate\": \"1656536400\", \"enddate\": \"1660165199\", \"active\": true}, {\"index\": 3001, \"startdate\": \"1655931600\", \"enddate\": \"1661893199\", \"active\": true}, {\"index\": 3000, \"startdate\": \"1648069200\", \"enddate\": \"1653944399\", \"active\": true}]}";
      RES_EVENT_LIST resEventList = (RES_EVENT_LIST)JSON.parseObject(jsonStr, RES_EVENT_LIST.class);
      resEventList.transId = reqEventList.transId;
      MessagePusher.pushMessage((IoSession)session, resEventList);
   }

   @RequestMapping
   public void ReqCreatureCommunionInfo(IoSession session, REQ_CREATURE_COMMUNION_INFO reqCreatureCommunionInfo) {
      RES_CREATURE_COMMUNION_INFO resCreatureCommunionInfo0 = new RES_CREATURE_COMMUNION_INFO();
      PT_CREATURE_COMMUNION info1 = new PT_CREATURE_COMMUNION();
      info1.level = 1;
      resCreatureCommunionInfo0.info = info1;
      resCreatureCommunionInfo0.transId = reqCreatureCommunionInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resCreatureCommunionInfo0);
   }

   @RequestMapping
   public void ReqCrackList(IoSession session, REQ_CRACK_LIST reqCrackList) {
      RES_CRACK_LIST resCrackList0 = new RES_CRACK_LIST();
      resCrackList0.maxcount = 40;
      resCrackList0.transId = reqCrackList.transId;
      MessagePusher.pushMessage((IoSession)session, resCrackList0);
   }

   @RequestMapping
   public void ReqAdventureReapInfo(IoSession session, REQ_ADVENTURE_REAP_INFO reqAdventureReapInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      AdventureReapInfo adventureReapInfo = account.getAdventureReapInfo();
      RES_ADVENTURE_REAP_INFO resAdventureReapInfo0 = new RES_ADVENTURE_REAP_INFO();
      List<PT_REAP_REWARD> rewards = adventureReapInfo.getRewards();
      long startTime = adventureReapInfo.getStarttime();
      if (startTime != -1L) {
         resAdventureReapInfo0.starttime = startTime;
         resAdventureReapInfo0.rewards = rewards;
      }

      resAdventureReapInfo0.transId = reqAdventureReapInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureReapInfo0);
   }

   @RequestMapping
   public void ReqEmblemList(IoSession session, REQ_EMBLEM_LIST reqEmblemList) {
      Role role = SessionUtils.getRoleBySession(session);
      EmblemBox emblemBox = role.getEmblemBox();
      RES_EMBLEM_LIST resEmblemList0 = new RES_EMBLEM_LIST();
      resEmblemList0.maxcount = 40;
      resEmblemList0.emblem = role.getEmblemBox().getEmblems();
      resEmblemList0.transId = reqEmblemList.transId;
      MessagePusher.pushMessage((IoSession)session, resEmblemList0);
   }

   @RequestMapping
   public void ReqCreatureErrandList(IoSession session, REQ_CREATURE_ERRAND_LIST reqCreatureErrandList) {
      Role role = SessionUtils.getRoleBySession(session);
      CreatureErrandBox creatureErrandBox = role.getCreatureErrandBox();
      RES_CREATURE_ERRAND_LIST res_creature_errand_list = new RES_CREATURE_ERRAND_LIST();
      res_creature_errand_list.errandinfo = creatureErrandBox.getErrandinfo();
      res_creature_errand_list.transId = reqCreatureErrandList.transId;
      MessagePusher.pushMessage((IoSession)session, res_creature_errand_list);
   }

   @RequestMapping
   public void ReqMinigameRestaurantLoad(IoSession session, REQ_MINIGAME_RESTAURANT_LOAD reqMinigameRestaurantLoad) {
      RES_MINIGAME_RESTAURANT_LOAD resMinigameRestaurantLoad0 = new RES_MINIGAME_RESTAURANT_LOAD();
      resMinigameRestaurantLoad0.state = 1;
      resMinigameRestaurantLoad0.ticketcount = 2;
      resMinigameRestaurantLoad0.transId = reqMinigameRestaurantLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resMinigameRestaurantLoad0);
   }

   @RequestMapping
   public void ReqIdipCheckReward(IoSession session, REQ_IDIP_CHECK_REWARD reqIdipCheckReward) {
      RES_IDIP_CHECK_REWARD res_idip_check_reward = new RES_IDIP_CHECK_REWARD();
      res_idip_check_reward.transId = reqIdipCheckReward.transId;
      MessagePusher.pushMessage((IoSession)session, res_idip_check_reward);
   }

   @RequestMapping
   public void ReqAvatarItemList(IoSession session, REQ_AVATAR_ITEM_LIST reqAvatarItemList) {
      Role role = SessionUtils.getRoleBySession(session);
      AvatarBox avatarBox = role.getAvatarBox();
      List<PT_AVATAR_ITEM> avatar_items = avatarBox.getAvatars();
      RES_AVATAR_ITEM_LIST resAvatarItemList0 = new RES_AVATAR_ITEM_LIST();
      if (!Util.isEmpty(avatar_items)) {
         resAvatarItemList0.avatar = avatar_items;
      }

      resAvatarItemList0.maxcount = 40;
      resAvatarItemList0.transId = reqAvatarItemList.transId;
      MessagePusher.pushMessage((IoSession)session, resAvatarItemList0);
   }

   @RequestMapping
   public void ReqSaveServerSimpleData(IoSession session, REQ_SAVE_SERVER_SIMPLE_DATA req_save_server_simple_data) {
      RES_SAVE_SERVER_SIMPLE_DATA res_save_server_simple_data = new RES_SAVE_SERVER_SIMPLE_DATA();
      Role role = SessionUtils.getRoleBySession(session);
      ServerSimpleDataBox serverSimpleDataBox = role.getServerSimpleDataBox();
      int type = 0;
      if (req_save_server_simple_data.type != null) {
         type = req_save_server_simple_data.type;
      }

      int enumValue = req_save_server_simple_data.enumvalue;
      ServerSimpleData serverSimpleData = new ServerSimpleData();
      serverSimpleData.setType(type);
      serverSimpleData.setEnumvalue(enumValue);
      serverSimpleData.setValue(req_save_server_simple_data.value);
      serverSimpleData.setTransId(serverSimpleDataBox.nextTransId++);
      serverSimpleDataBox.addServerSimpleData(type, enumValue, serverSimpleData);
      role.setServerSimpleDataBox(serverSimpleDataBox);
      role.save();
      res_save_server_simple_data.transId = req_save_server_simple_data.transId;
      MessagePusher.pushMessage((IoSession)session, res_save_server_simple_data);
   }

   @RequestMapping
   public void ReqStorageStep(IoSession session, REQ_STORAGE_STEP reqStorageStep) {
      RES_STORAGE_STEP resStorageStep = new RES_STORAGE_STEP();
      resStorageStep.line = 1;
      resStorageStep.transId = reqStorageStep.transId;
      MessagePusher.pushMessage((IoSession)session, resStorageStep);
   }

   @RequestMapping
   public void ReqAdventurebookInfo(IoSession session, REQ_ADVENTUREBOOK_INFO reqAdventurebookInfo) {
      Account account = SessionUtils.getAccountBySession(session);
      AdvBookBox advBookBox = account.getAdvBookBox();
      List<PT_ADVENTUREBOOK_INFO> adventurebooks = advBookBox.getAdventurebooks();
      List<PT_ADVENTUREBOOK_OPEN_CONDITION> open_conditions = advBookBox.getOpenconditions();
      RES_ADVENTUREBOOK_INFO resAdventurebookInfo = new RES_ADVENTUREBOOK_INFO();
      if (adventurebooks != null) {
         resAdventurebookInfo.adventurebooks = adventurebooks;
      }

      if (open_conditions != null) {
         resAdventurebookInfo.openconditions = open_conditions;
      }

      resAdventurebookInfo.transId = reqAdventurebookInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventurebookInfo);
   }

   @RequestMapping
   public void ReqBattlePassRanking(IoSession session, REQ_BATTLE_PASS_RANKING reqBattlePassRanking) {
      new ArrayList();
      String jsonStr = "{\"rankinginfos\": [{\"guid\": \"25673079388128411\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"我是你爸爸\", \"score\": \"72875\", \"characterframe\": 20038}, {\"guid\": \"25673098867406877\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"처닐임당\", \"rank\": \"1\", \"score\": \"66125\", \"characterframe\": 20062}, {\"guid\": \"4704084154648112\", \"level\": 55, \"job\": 2, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"건방진샤넬\", \"rank\": \"2\", \"score\": \"60450\"}, {\"guid\": \"4703378912451066\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"mandu111\", \"rank\": \"3\", \"score\": \"59525\", \"characterframe\": 20059}, {\"guid\": \"11740064106296806\", \"level\": 55, \"job\": 2, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"Bangzideba\", \"rank\": \"4\", \"score\": \"58700\", \"characterframe\": 20018}, {\"guid\": \"25673130736922082\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"RoxGhost\", \"rank\": \"5\", \"score\": \"56500\", \"characterframe\": 1920006}, {\"guid\": \"36650612530505593\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"요정\", \"rank\": \"6\", \"score\": \"55900\", \"characterframe\": 20000}, {\"guid\": \"30740040593572332\", \"level\": 55, \"job\": 2, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"지속전투\", \"rank\": \"7\", \"score\": \"55825\", \"characterframe\": 20059}, {\"guid\": \"9347643512599570\", \"level\": 55, \"job\": 2, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"레이투\", \"rank\": \"8\", \"score\": \"55600\", \"characterframe\": 1920002}, {\"guid\": \"30740731056096115\", \"level\": 55, \"job\": 11, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"loIo\", \"rank\": \"9\", \"score\": \"55250\", \"characterframe\": 20062}, {\"guid\": \"11740563855381344\", \"level\": 55, \"job\": 1, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"파프닐\", \"rank\": \"10\", \"score\": \"54825\", \"characterframe\": 1920004}, {\"guid\": \"9910490872569913\", \"level\": 55, \"job\": 3, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"키힣\", \"rank\": \"11\", \"score\": \"54800\", \"characterframe\": 20005}, {\"guid\": \"17510301669275327\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"킴돈파\", \"rank\": \"12\", \"score\": \"54600\", \"characterframe\": 20002}, {\"guid\": \"9910567227364418\", \"level\": 55, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"꺼꿀핑\", \"rank\": \"13\", \"score\": \"54025\", \"characterframe\": 20038}, {\"guid\": \"25673161441545390\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"에베베볩\", \"rank\": \"14\", \"score\": \"53900\", \"characterframe\": 20000}, {\"guid\": \"36650595273676351\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"미동\", \"rank\": \"15\", \"score\": \"53375\", \"characterframe\": 20083}, {\"guid\": \"30740731427816986\", \"level\": 55, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"체령e\", \"rank\": \"16\", \"score\": \"53275\", \"characterframe\": 1920003}, {\"guid\": \"3156289847166861\", \"level\": 51, \"job\": 1, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"열쨩\", \"rank\": \"17\", \"score\": \"53025\", \"characterframe\": 20038}, {\"guid\": \"25674310420663675\", \"level\": 40, \"job\": 11, \"growtype\": 3, \"name\": \"루데레\", \"rank\": \"18\", \"score\": \"52650\", \"characterframe\": 20038}, {\"guid\": \"9910481508254702\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"택이장님\", \"rank\": \"19\", \"score\": \"52650\", \"characterframe\": 20038}, {\"guid\": \"33554587542236856\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"JJgYY\", \"rank\": \"20\", \"score\": \"52650\", \"characterframe\": 20059}, {\"guid\": \"29191661026691934\", \"level\": 55, \"job\": 1, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"조력하다\", \"rank\": \"21\", \"score\": \"52375\", \"characterframe\": 20038}, {\"guid\": \"36228384704047334\", \"level\": 55, \"job\": 3, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"탐욕의마몬\", \"rank\": \"22\", \"score\": \"52350\", \"characterframe\": 20002}, {\"guid\": \"25674181147754740\", \"level\": 55, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"디어사OI드\", \"rank\": \"23\", \"score\": \"51975\", \"characterframe\": 1920006}, {\"guid\": \"30739637911706380\", \"level\": 55, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"김수현AE\", \"rank\": \"24\", \"score\": \"51575\", \"characterframe\": 1920006}, {\"guid\": \"13570757501256560\", \"level\": 55, \"job\": 11, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"소주여신\", \"rank\": \"25\", \"score\": \"51425\", \"characterframe\": 1920003}, {\"guid\": \"29191581719380428\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"Risong\", \"rank\": \"26\", \"score\": \"51075\", \"characterframe\": 1920004}, {\"guid\": \"26798973109021303\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"피카미\", \"rank\": \"27\", \"score\": \"51075\", \"characterframe\": 20059}, {\"guid\": \"24829087755932361\", \"level\": 55, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"씩맨\", \"rank\": \"28\", \"score\": \"50900\", \"characterframe\": 20000}, {\"guid\": \"33554919996788427\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"븝퍼출시기원\", \"rank\": \"29\", \"score\": \"50850\"}, {\"guid\": \"20185421951799388\", \"level\": 41, \"job\": 11, \"growtype\": 3, \"name\": \"미다킥\", \"rank\": \"30\", \"score\": \"50625\", \"characterframe\": 20038}, {\"guid\": \"30739759721759250\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"흡정공주\", \"rank\": \"31\", \"score\": \"50450\", \"characterframe\": 20062}, {\"guid\": \"24828846164026348\", \"level\": 55, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"HimeSA\", \"rank\": \"32\", \"score\": \"50200\"}, {\"guid\": \"7517939414086424\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"내동댕냥이\", \"rank\": \"33\", \"score\": \"50000\", \"characterframe\": 20002}, {\"guid\": \"32006264091461496\", \"level\": 55, \"job\": 3, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"마탑장인\", \"rank\": \"34\", \"score\": \"50000\", \"characterframe\": 20038}, {\"guid\": \"14836728146563463\", \"level\": 55, \"job\": 3, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"숙또캐\", \"rank\": \"35\", \"score\": \"49950\", \"characterframe\": 20038}, {\"guid\": \"36651705193988121\", \"level\": 55, \"job\": 11, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"부덩\", \"rank\": \"36\", \"score\": \"49675\", \"characterframe\": 1920003}, {\"guid\": \"4703189606347208\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"수룡검\", \"rank\": \"37\", \"score\": \"49450\", \"characterframe\": 1920006}, {\"guid\": \"9910498495852316\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"겁내지말게\", \"rank\": \"38\", \"score\": \"49225\", \"characterframe\": 20059}, {\"guid\": \"29192618441441725\", \"level\": 55, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"공격\", \"rank\": \"39\", \"score\": \"49200\", \"characterframe\": 1920003}, {\"guid\": \"13570759191562946\", \"level\": 55, \"job\": 11, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"다큰템플짱\", \"rank\": \"40\", \"score\": \"49050\", \"characterframe\": 20059}, {\"guid\": \"11741169751031822\", \"level\": 55, \"job\": 11, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"쌍검마렵네\", \"rank\": \"41\", \"score\": \"49025\", \"characterframe\": 1920006}, {\"guid\": \"341622059566507\", \"level\": 23, \"job\": 11, \"growtype\": 2, \"name\": \"암제다앗\", \"rank\": \"42\", \"score\": \"48425\"}, {\"guid\": \"38058247888050773\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"PleaseDraw\", \"rank\": \"43\", \"score\": \"48400\", \"characterframe\": 1920002}, {\"guid\": \"36651705741870243\", \"level\": 55, \"job\": 11, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"WYM암제\", \"rank\": \"44\", \"score\": \"48375\", \"characterframe\": 1920003}, {\"guid\": \"25674182453758647\", \"level\": 55, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"너굴이양\", \"rank\": \"45\", \"score\": \"48300\", \"characterframe\": 1920003}, {\"guid\": \"30739794933189674\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"GoodHei\", \"rank\": \"46\", \"score\": \"48300\", \"characterframe\": 20000}, {\"guid\": \"4704295343751234\", \"level\": 55, \"job\": 11, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"문차베가\", \"rank\": \"47\", \"score\": \"48200\", \"characterframe\": 20038}, {\"guid\": \"33554369863093394\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"TianMing\", \"rank\": \"48\", \"score\": \"48125\", \"characterframe\": 20000}, {\"guid\": \"26799633393387416\", \"level\": 55, \"job\": 3, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"빗자루애용\", \"rank\": \"49\", \"score\": \"48050\", \"characterframe\": 20038}, {\"guid\": \"13569646388710239\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"MoLdevil\", \"rank\": \"50\", \"score\": \"48025\", \"characterframe\": 20000}, {\"guid\": \"7517963807522461\", \"level\": 55, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"황두희\", \"rank\": \"51\", \"score\": \"48000\"}, {\"guid\": \"21451361602635421\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"헤비블로\", \"rank\": \"52\", \"score\": \"47925\", \"characterframe\": 20083}, {\"guid\": \"29191526655878116\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"아까본그녀\", \"rank\": \"53\", \"score\": \"47850\"}, {\"guid\": \"16384400837062398\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"떡상가\", \"rank\": \"54\", \"score\": \"47800\", \"characterframe\": 1920000}, {\"guid\": \"5688339618204690\", \"level\": 55, \"job\": 3, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"자낫\", \"rank\": \"55\", \"score\": \"47650\", \"characterframe\": 20002}, {\"guid\": \"29192022954350400\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"오오우오우\", \"rank\": \"56\", \"score\": \"47525\", \"characterframe\": 20038}, {\"guid\": \"24829862267586619\", \"level\": 49, \"job\": 1, \"growtype\": 1, \"name\": \"박묵묵\", \"rank\": \"57\", \"score\": \"47475\", \"characterframe\": 20038}, {\"guid\": \"4704295988888989\", \"level\": 55, \"job\": 11, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"곤라니0\", \"rank\": \"58\", \"score\": \"47425\", \"characterframe\": 20038}, {\"guid\": \"13569648394378486\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"악마래균\", \"rank\": \"59\", \"score\": \"47400\", \"characterframe\": 20038}, {\"guid\": \"3155689245904790\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"지성이오떤데\", \"rank\": \"60\", \"score\": \"47300\"}, {\"guid\": \"36229493127906209\", \"level\": 52, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"disconsolate\", \"rank\": \"61\", \"score\": \"47150\"}, {\"guid\": \"29192618510778926\", \"level\": 55, \"job\": 11, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"검호주의보\", \"rank\": \"62\", \"score\": \"46975\", \"characterframe\": 20062}, {\"guid\": \"3156183299523463\", \"level\": 55, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"애긔여왕님\", \"rank\": \"63\", \"score\": \"46650\", \"characterframe\": 20038}, {\"guid\": \"21452062468740460\", \"level\": 55, \"job\": 11, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"ZGSpdxggg\", \"rank\": \"64\", \"score\": \"46250\"}, {\"guid\": \"17511407570388893\", \"level\": 55, \"job\": 11, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"사나\", \"rank\": \"65\", \"score\": \"46150\", \"characterframe\": 20038}, {\"guid\": \"17510426053327845\", \"level\": 55, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"건방진로렉스\", \"rank\": \"66\", \"score\": \"46150\", \"characterframe\": 20002}, {\"guid\": \"7518130561097071\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"ㄴ검신\", \"rank\": \"67\", \"score\": \"46025\", \"characterframe\": 20083}, {\"guid\": \"32006259801204452\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"xiaoayun\", \"rank\": \"68\", \"score\": \"45925\", \"characterframe\": 20059}, {\"guid\": \"9347574059411801\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"양산수라\", \"rank\": \"69\", \"score\": \"45775\", \"characterframe\": 20059}, {\"guid\": \"4704308075174535\", \"level\": 55, \"job\": 11, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"베본퍼그\", \"rank\": \"70\", \"score\": \"45750\", \"characterframe\": 1920003}, {\"guid\": \"21450969600387167\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"창이스\", \"rank\": \"71\", \"score\": \"45725\", \"characterframe\": 20059}, {\"guid\": \"21451484491417621\", \"level\": 55, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"111kuangzhan\", \"rank\": \"72\", \"score\": \"45725\"}, {\"guid\": \"9348632990319706\", \"level\": 55, \"job\": 11, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"소드맛껌\", \"rank\": \"73\", \"score\": \"45575\", \"characterframe\": 1920006}, {\"guid\": \"5689457748869129\", \"level\": 55, \"job\": 11, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"소마푸른불\", \"rank\": \"74\", \"score\": \"45525\", \"characterframe\": 1920003}, {\"guid\": \"14836273435116792\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"피빨강\", \"rank\": \"75\", \"score\": \"45500\", \"characterframe\": 20062}, {\"guid\": \"13569875996194039\", \"level\": 55, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"MairAndy\", \"rank\": \"76\", \"score\": \"45350\"}, {\"guid\": \"11740325593223840\", \"level\": 55, \"job\": 3, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"꺔꺔\", \"rank\": \"77\", \"score\": \"45325\"}, {\"guid\": \"17510330790871923\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"구라용\", \"rank\": \"78\", \"score\": \"45300\", \"characterframe\": 20002}, {\"guid\": \"24828649188239543\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"St크루\", \"rank\": \"79\", \"score\": \"45225\", \"characterframe\": 20002}, {\"guid\": \"4703199427841781\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"신발암\", \"rank\": \"80\", \"score\": \"45200\", \"characterframe\": 20059}, {\"guid\": \"4703362369457883\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"JayO6\", \"rank\": \"81\", \"score\": \"45175\"}, {\"guid\": \"13570395442121677\", \"level\": 48, \"growtype\": 4, \"name\": \"Xluo9999\", \"rank\": \"82\", \"score\": \"45125\"}, {\"guid\": \"36228371739314390\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"킥의정석\", \"rank\": \"83\", \"score\": \"45075\", \"characterframe\": 20000}, {\"guid\": \"33554371511852579\", \"level\": 55, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"돋석\", \"rank\": \"84\", \"score\": \"45075\", \"characterframe\": 1920000}, {\"guid\": \"32006264352557295\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"Qcou\", \"rank\": \"85\", \"score\": \"45000\", \"characterframe\": 20059}, {\"guid\": \"3155166955835681\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"miqiqi\", \"rank\": \"86\", \"score\": \"44900\", \"characterframe\": 20059}, {\"guid\": \"24689025823152023\", \"level\": 55, \"job\": 11, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"syy114s\", \"rank\": \"87\", \"score\": \"44625\", \"characterframe\": 20059}, {\"guid\": \"3156183081550269\", \"level\": 55, \"job\": 11, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"진가검\", \"rank\": \"88\", \"score\": \"44600\", \"characterframe\": 20062}, {\"guid\": \"21450950073134313\", \"level\": 55, \"job\": 3, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"실리콘웨이퍼\", \"rank\": \"89\", \"score\": \"44500\"}, {\"guid\": \"16384514662872278\", \"level\": 55, \"growtype\": 3, \"secgrowtype\": 1, \"name\": \"qimeimei\", \"rank\": \"90\", \"score\": \"44450\", \"characterframe\": 1920000}, {\"guid\": \"14836298920516079\", \"level\": 55, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"음마왕\", \"rank\": \"91\", \"score\": \"44400\", \"characterframe\": 20005}, {\"guid\": \"5688639343303909\", \"level\": 55, \"job\": 2, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"달려라김말출\", \"rank\": \"92\", \"score\": \"44400\", \"characterframe\": 1920002}, {\"guid\": \"13570283441881244\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"잔다라큐\", \"rank\": \"93\", \"score\": \"44375\"}, {\"guid\": \"32006264409573705\", \"level\": 55, \"job\": 1, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"Just1JOE\", \"rank\": \"94\", \"score\": \"44175\", \"characterframe\": 1920004}, {\"guid\": \"11740088502353011\", \"level\": 55, \"job\": 2, \"growtype\": 2, \"secgrowtype\": 1, \"name\": \"입럭할\", \"rank\": \"95\", \"score\": \"44150\", \"characterframe\": 1920004}, {\"guid\": \"30739786016098059\", \"level\": 55, \"growtype\": 4, \"secgrowtype\": 1, \"name\": \"aLei\", \"rank\": \"96\", \"score\": \"44100\", \"characterframe\": 20002}, {\"guid\": \"20184321787317347\", \"level\": 55, \"job\": 2, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"쾌속의낭만\", \"rank\": \"97\", \"score\": \"43800\", \"characterframe\": 20059}, {\"guid\": \"7517987087101672\", \"level\": 55, \"job\": 14, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"진나빛\", \"rank\": \"98\", \"score\": \"43800\"}, {\"guid\": \"24688412226753542\", \"level\": 55, \"growtype\": 1, \"secgrowtype\": 1, \"name\": \"Ylda\", \"rank\": \"99\", \"score\": \"43800\", \"characterframe\": 20038}]}";
      Object resBattlePassRanking = JSON.parseObject(jsonStr, RES_BATTLE_PASS_RANKING.class);
      MessagePusher.pushMessage((IoSession)session, (RES_BATTLE_PASS_RANKING)resBattlePassRanking);
   }

   @RequestMapping
   public void ReqEnterToTown(IoSession session, REQ_ENTER_TO_TOWN reqEnterToTown) {
      Integer town = reqEnterToTown.town;
      Integer area = reqEnterToTown.area;
      Integer posx = reqEnterToTown.posx;
      Integer posy = reqEnterToTown.posy;
      Role role = SessionUtils.getRoleBySession(session);
      Pos pos = new Pos();
      if (posx != null) {
         pos.setX(posx);
      } else {
         pos.setX(0);
      }

      if (posy != null) {
         pos.setY(posy);
      } else {
         pos.setY(0);
      }

      if (posy != null) {
         pos.setTown(town);
      } else {
         pos.setTown(0);
      }

      if (area != null) {
         pos.setArea(area);
      } else {
         pos.setArea(0);
      }

      role.setPos(pos);
      role.save();
      if (town == 17) {
         this.logger.error("UNREACH==ReqEnterToTown==town==17");
      } else {
         RES_ENTER_TO_TOWN resEnterToTown0 = new RES_ENTER_TO_TOWN();
         resEnterToTown0.town = town;
         resEnterToTown0.area = area;
         resEnterToTown0.posx = posx;
         resEnterToTown0.posy = posy;
         resEnterToTown0.servertime = TimeUtil.currS();
         resEnterToTown0.expratio = role.getExpratio();
         resEnterToTown0.fatigueratio = role.getFatigueratio();
         Integer deviceType = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.DEVICE_TYPE, Integer.class);
         if (deviceType == 1) {
            resEnterToTown0.version = "5.7.10.0.114";
         } else {
            resEnterToTown0.version = "5.7.10.0.209";
         }

         resEnterToTown0.transId = reqEnterToTown.transId;
         MessagePusher.pushMessage((IoSession)session, resEnterToTown0);
         DungeonParty dungeonParty = role.getDungeonParty();
         if (dungeonParty != null && dungeonParty.charguid == role.getUid()) {
            this.partyService.enterToTown(dungeonParty, session, reqEnterToTown);
         }
      }

      this.mapService.enterTown(role);
   }

   @RequestMapping
   public void ReqSdAvatarList(IoSession session, REQ_SD_AVATAR_LIST reqSdAvatarList) {
      RES_SD_AVATAR_LIST resSdAvatarList0 = new RES_SD_AVATAR_LIST();
      resSdAvatarList0.maxcount = 40;
      resSdAvatarList0.transId = reqSdAvatarList.transId;
      MessagePusher.pushMessage((IoSession)session, resSdAvatarList0);
   }

   @RequestMapping
   public void ReqSystemBuffAppendageList(IoSession session, REQ_SYSTEM_BUFF_APPENDAGE_LIST reqSystemBuffAppendageList) {
      RES_SYSTEM_BUFF_APPENDAGE_LIST resSystemBuffAppendageList0 = new RES_SYSTEM_BUFF_APPENDAGE_LIST();
      Role role = SessionUtils.getRoleBySession(session);
      resSystemBuffAppendageList0.time = TimeUtil.currS();
      resSystemBuffAppendageList0.appendages = role.getSysBuffBox().getAppendages();
      resSystemBuffAppendageList0.transId = reqSystemBuffAppendageList.transId;
      MessagePusher.pushMessage((IoSession)session, resSystemBuffAppendageList0);
   }

   public void sendSystemBuffAppendageList(IoSession session, int level, int index) {
      RES_SYSTEM_BUFF_APPENDAGE_LIST resSystemBuffAppendageList0 = new RES_SYSTEM_BUFF_APPENDAGE_LIST();
      resSystemBuffAppendageList0.time = TimeUtil.currS();
      resSystemBuffAppendageList0.appendages = new ArrayList();
      Role role = SessionUtils.getRoleBySession(session);
      List<Integer> buffList = new ArrayList();
      buffList.add(level);
      PT_SYSTEM_BUFF_APPENDAGE ptSystemBuffAppendage = role.getSysBuffBox().createAppendage(index, 0L, buffList);
      role.getSysBuffBox().addAppendages(ptSystemBuffAppendage);
      resSystemBuffAppendageList0.appendages.add(ptSystemBuffAppendage);
      resSystemBuffAppendageList0.transId = SessionUtils.getNotiTransId(session);
      MessagePusher.pushMessage((IoSession)session, resSystemBuffAppendageList0);
   }

   @RequestMapping
   public void ReqCreatureFeeding(IoSession session, REQ_CREATURE_FEEDING req_creature_feeding) {
      long creature = req_creature_feeding.creature;
      List<PT_STACKABLE> stackables = req_creature_feeding.stackables;
      RES_CREATURE_FEEDING res_creature_feeding = new RES_CREATURE_FEEDING();
      res_creature_feeding.creature = creature;
      res_creature_feeding.exp = 5000;
      res_creature_feeding.removestackables = stackables;
      res_creature_feeding.transId = req_creature_feeding.transId;
      MessagePusher.pushMessage((IoSession)session, res_creature_feeding);
   }

   @RequestMapping
   public void ReqCeraInterestingGoodsList(IoSession session, REQ_CERA_INTERESTING_GOODS_LIST reqCeraInterestingGoodsList) {
      RES_CERA_INTERESTING_GOODS_LIST resCeraInterestingGoodsList0 = new RES_CERA_INTERESTING_GOODS_LIST();
      resCeraInterestingGoodsList0.transId = reqCeraInterestingGoodsList.transId;
      MessagePusher.pushMessage((IoSession)session, resCeraInterestingGoodsList0);
   }

   @RequestMapping
   public void ReqEnterChannel(IoSession session, REQ_ENTER_CHANNEL reqEnterChannel) {
      String openid = reqEnterChannel.openid;
      String authkey = reqEnterChannel.authkey;
      Long charguid = reqEnterChannel.charguid;
      if (charguid != null) {
      }

      String idfv = reqEnterChannel.idfv;
      if (idfv != null) {
         session.setAttribute(SessionProperties.DEVICE_TYPE, 2);
      } else {
         session.setAttribute(SessionProperties.DEVICE_TYPE, 1);
      }

      session.setAttribute(SessionProperties.ACCOUNT_OPENID, openid);
      session.setAttribute(SessionProperties.AUTH_KEY, authkey);
      RES_ENTER_CHANNEL resEnterChannel0 = new RES_ENTER_CHANNEL();
      resEnterChannel0.encrypt = true;
      resEnterChannel0.servertime = TimeUtil.currS();
      resEnterChannel0.authkey = authkey;
      resEnterChannel0.areatype = 2;
      resEnterChannel0.returnUserInfo = new PT_RETURN_USER_INFO();
      Server server = (Server)DataCache.ID_SERVER.get(1);
      PT_CHANNEL channelinfo2 = new PT_CHANNEL();
      channelinfo2.channel = 1;
      channelinfo2.iP = server.getIp();
      channelinfo2.port = server.getPort();
      channelinfo2.saturation = server.getSaturation();
      channelinfo2.world = 1;
      resEnterChannel0.channelinfo = channelinfo2;
      List<Integer> integerList3 = new ArrayList();
      resEnterChannel0.seeds = integerList3;
      resEnterChannel0.eventSelectInfo = new PT_EVENT_SELECT_INFO();
      resEnterChannel0.transId = reqEnterChannel.transId;
      MessagePusher.pushMessage((IoSession)session, resEnterChannel0);
      Account account = this.accountService.getAccount(openid);
      session.setAttribute(SessionProperties.ACCOUNT, account);
   }

   @RequestMapping
   public void ReqStartGame(IoSession session, REQ_START_GAME reqStartGame) {
      long charguid = reqStartGame.charguid;
      Account account = SessionUtils.getAccountBySession(session);
      if (!account.getIsStop()) {
         String authkey = reqStartGame.authkey;
         String accesstoken = reqStartGame.accesstoken;
         List<PT_PROTOCOL_TRANSACTION> request = reqStartGame.request;
         Integer reqtown = reqStartGame.town;
         Role role = null;
         if (reqtown != null) {
            role = (Role)DataCache.AUCTION_ROLES.get(charguid);
            if (role == null) {
               this.logger.error("UNREACH==重连时，角色不存在");
               return;
            }
         } else {
            role = this.playerService.getPlayerBy(charguid);
         }

         session.setAttribute(SessionProperties.PLAYER_UID, charguid);
         SysBuffBox sysBuffBox = role.getSysBuffBox();
         List<PT_SYSTEM_BUFF_APPENDAGE> buffList = sysBuffBox.getAppendages();
         session.setAttribute(SessionProperties.PLAYER, role);
         DataCache.AUCTION_ROLES.put(charguid, role);
         Pos pos = role.getPos();
         int town = 2;
         int area = 5;
         RES_START_GAME resStartGame1 = new RES_START_GAME();
         resStartGame1.world = 1;
         resStartGame1.currentworld = 1;
         resStartGame1.town = town;
         resStartGame1.area = area;
         resStartGame1.level = role.getLevel();
         resStartGame1.exp = role.getExp();
         resStartGame1.fatigue = role.getFatigue();
         if (!Util.isEmpty(buffList)) {
            resStartGame1.bufflist = new ArrayList();

            for(PT_SYSTEM_BUFF_APPENDAGE pt_system_buff_appendage : buffList) {
               PT_DINING_FOOD_BUFF_INFO pt_dining_food_buff_info = new PT_DINING_FOOD_BUFF_INFO();
               pt_dining_food_buff_info.index = pt_system_buff_appendage.index;
               resStartGame1.bufflist.add(pt_dining_food_buff_info);
            }
         }

         resStartGame1.partydisturb = 1;
         resStartGame1.battletutorial = 2;
         resStartGame1.qindex = role.getQindex();
         resStartGame1.areaId = 2;
         resStartGame1.expratio = role.getExpratio();
         resStartGame1.fatigueratio = role.getFatigueratio();
         resStartGame1.chatchnidx = 1;
         resStartGame1.createcount = 1838611L;
         resStartGame1.returnUserInfo = new PT_RETURN_USER_INFO();
         resStartGame1.battlePassInfo = new PT_BATTLE_PASS_INFO();
         resStartGame1.pvpBattlePassInfo = new PT_BATTLE_PASS_INFO();
         resStartGame1.intervaltowncharacterinfoms = 10000;
         PT_INIT_SKILL initskillversion6 = new PT_INIT_SKILL();
         initskillversion6.currentVersion = 1;
         initskillversion6.latestVersion = 1;
         resStartGame1.initskillversion = initskillversion6;
         resStartGame1.towninterval = new ArrayList();
         PT_TOWN_INTERVAL ptTownInterval8 = new PT_TOWN_INTERVAL();
         ptTownInterval8.townindex = 17;
         ptTownInterval8.refreshinterval = 500;
         resStartGame1.towninterval.add(ptTownInterval8);
         resStartGame1.adventurebookOpen = true;
         Integer deviceType = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.DEVICE_TYPE, Integer.class);
         if (deviceType == 1) {
            resStartGame1.svnrevision = 174307;
            resStartGame1.gitrevision = "174307";
         } else {
            resStartGame1.svnrevision = 188783;
            resStartGame1.gitrevision = "188783";
         }

         resStartGame1.eventSelectInfo = new PT_EVENT_SELECT_INFO();
         if (role.getQuestInfoBox().getQueststate() != 0) {
            resStartGame1.queststate = role.getQuestInfoBox().getQueststate();
         }

         resStartGame1.transId = reqStartGame.transId;
         this.roleService.startGame(session, role);
         MessagePusher.pushMessage((IoSession)session, resStartGame1);
         if (reqtown != null) {
            ServerSimpleDataBox serverSimpleDataBox = role.getServerSimpleDataBox();
            ServerSimpleData serverSimpleData = serverSimpleDataBox.getData(1, 12);
            NOTIFY_LOAD_SERVER_SIMPLE_DATA notify_load_server_simple_data = new NOTIFY_LOAD_SERVER_SIMPLE_DATA();
            notify_load_server_simple_data.type = 1;
            notify_load_server_simple_data.enumvalue = 12;
            if (serverSimpleData == null) {
               this.logger.error("UNREACH==重连时，serverSimpleData为空");
               return;
            }

            if (serverSimpleData.getValue() != null && serverSimpleData.getValue().length() != 0) {
               notify_load_server_simple_data.value = serverSimpleData.getValue();
            }

            notify_load_server_simple_data.transId = serverSimpleData.getTransId();
            MessagePusher.pushMessage((IoSession)session, notify_load_server_simple_data);
            RES_SERVER_RESPONSE_PACKET resServerResponsePacket9 = new RES_SERVER_RESPONSE_PACKET();
            MessagePusher.pushMessage((IoSession)session, resServerResponsePacket9);
            NOTIFY_RECONNECT_INFO notifyReconnectInfo10 = new NOTIFY_RECONNECT_INFO();
            notifyReconnectInfo10.town = town;
            notifyReconnectInfo10.area = area;
            MessagePusher.pushMessage((IoSession)session, notifyReconnectInfo10);
            RES_TENCENT_CREDITSCORE_INFO resTencentCreditscoreInfo0 = new RES_TENCENT_CREDITSCORE_INFO();
            resTencentCreditscoreInfo0.level = 1;
            resTencentCreditscoreInfo0.score = 351;
            resTencentCreditscoreInfo0.tagblack = 1;
            resTencentCreditscoreInfo0.tagugc = 1;
            MessagePusher.pushMessage((IoSession)session, resTencentCreditscoreInfo0);
            int lastNotiTransId = role.lastNotiTransId;
            this.logger.error("重连==lastNotiTransId==" + lastNotiTransId);
            session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, lastNotiTransId + 1);
         } else {
            RES_SERVER_RESPONSE_PACKET resServerResponsePacket9 = new RES_SERVER_RESPONSE_PACKET();
            resServerResponsePacket9.error = 3;
            MessagePusher.pushMessage((IoSession)session, resServerResponsePacket9);
            NOTIFY_RECONNECT_INFO notifyReconnectInfo10 = new NOTIFY_RECONNECT_INFO();
            notifyReconnectInfo10.town = town;
            notifyReconnectInfo10.area = area;
            MessagePusher.pushMessage((IoSession)session, notifyReconnectInfo10);
            RES_TENCENT_CREDITSCORE_INFO resTencentCreditscoreInfo0 = new RES_TENCENT_CREDITSCORE_INFO();
            resTencentCreditscoreInfo0.level = 1;
            resTencentCreditscoreInfo0.score = 351;
            resTencentCreditscoreInfo0.tagblack = 1;
            resTencentCreditscoreInfo0.tagugc = 1;
            MessagePusher.pushMessage((IoSession)session, resTencentCreditscoreInfo0);
         }

         if (account.getActivityBox() == null) {
            account.setActivityBox(new ActivityBox());
         }

         if (account.getActivityBox().getTylorBagExpireTime() > 0L) {
            long lastLoginTime = account.getLastLoginTime();
            if (DateUtils.getDaysBetween(account.getActivityBox().getTylorBagExpireTime()) < 0 && DateUtils.getDaysBetween(lastLoginTime) >= 1) {
               String title = "泰拉充电包";
               String msg = "泰拉充电包 每日邮件";
               List<MailItem> mailItemList = new ArrayList();
               MailItem mailItem = new MailItem();
               mailItem.index = 2013000001;
               mailItem.cnt = 2000;
               mailItemList.add(mailItem);
               account.getMailBox().addMail(title, msg, mailItemList);
               this.logger.error("泰拉充电包 邮件发送 {}", mailItem);
            }
         }

         long sixTime = DateUtils.getToDaySixDate().getTime() / 1000L;
         long yesterdaySixTime = DateUtils.getYesterdaySixDate().getTime() / 1000L;
         if (role.getLastlogout() < yesterdaySixTime || TimeUtil.currS() > sixTime && sixTime > role.getLastlogout()) {
            role.setFatigue(100);
            role.getRoleShopInfoBox().getBuy().clear();
         }

         account.setLastLoginTime(TimeUtil.currMs());
         DataCache.ONLINE_ACC_MAP.put(account.getId(), role.getUid());
      }
   }

   @RequestMapping
   public void ReqAdventureUnionSubdueInfo(IoSession session, REQ_ADVENTURE_UNION_SUBDUE_INFO reqAdventureUnionSubdueInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_ADVENTURE_UNION_SUBDUE_INFO res_adventure_union_subdue_info = new RES_ADVENTURE_UNION_SUBDUE_INFO();
      res_adventure_union_subdue_info.fatigues = new ArrayList();
      PT_CHARGUID_FATIGUE pt_charguid_fatigue = new PT_CHARGUID_FATIGUE();
      pt_charguid_fatigue.charguid = role.getUid();
      pt_charguid_fatigue.fatigue = role.getFatigue();
      res_adventure_union_subdue_info.fatigues.add(pt_charguid_fatigue);
      res_adventure_union_subdue_info.tickets = new ArrayList();
      PT_CHARGUID_TICKET pt_charguid_ticket = new PT_CHARGUID_TICKET();
      pt_charguid_ticket.charguid = role.getUid();
      res_adventure_union_subdue_info.tickets.add(pt_charguid_ticket);
      res_adventure_union_subdue_info.entranceitems = new ArrayList();
      PT_CHARGUID_ENTRANCE_ITEM pt_charguid_entrance_item = new PT_CHARGUID_ENTRANCE_ITEM();
      pt_charguid_entrance_item.charguid = role.getUid();
      pt_charguid_entrance_item.items = new ArrayList();
      PT_ENTRANCE_ITEM pt_entrance_item1 = new PT_ENTRANCE_ITEM();
      pt_entrance_item1.index = 2013104267;
      pt_charguid_entrance_item.items.add(pt_entrance_item1);
      PT_ENTRANCE_ITEM pt_entrance_item2 = new PT_ENTRANCE_ITEM();
      pt_entrance_item2.index = 2013106097;
      pt_charguid_entrance_item.items.add(pt_entrance_item2);
      res_adventure_union_subdue_info.entranceitems.add(pt_charguid_entrance_item);
      res_adventure_union_subdue_info.transId = reqAdventureUnionSubdueInfo.transId;
      MessagePusher.pushMessage((IoSession)session, res_adventure_union_subdue_info);
   }

   @RequestMapping
   public void ReqPvpRecordInfo(IoSession session, REQ_PVP_RECORD_INFO reqPvpRecordInfo) {
      int matchType = reqPvpRecordInfo.matchtype;
      if (matchType == 1) {
         String str = "{\"matchtype\": 1, \"pvpinfos\": [{\"maxpr\": 1000, \"performancerating\": 1000, \"ranking\": 2, \"type\": 1, \"rank\": -1}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info);
      } else if (matchType == 2) {
         String str = "{\"matchtype\": 2, \"pvpinfos\": [{\"type\": 2}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info);
      } else if (matchType == 34) {
         String str = "{\"matchtype\": 34, \"pvpinfos\": [{\"maxpr\": 1000, \"performancerating\": 1000, \"ranking\": 2, \"type\": 34, \"rank\": -1}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info);
      } else if (matchType == 16) {
         String str = "{\"matchtype\": 16, \"pvpinfos\": [{\"performancerating\": 1000, \"type\": 16}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info0 = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info0.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info0);
      } else if (matchType == 17) {
         String str = "{\"matchtype\": 17, \"pvpinfos\": [{\"maxpr\": 1000, \"performancerating\": 1000, \"ranking\": 303, \"type\": 17}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info);
      } else if (matchType == 35) {
         String str = "{\"matchtype\": 35, \"pvpinfos\": [{\"maxpr\": 1000, \"performancerating\": 1000, \"ranking\": 3, \"type\": 35, \"rank\": -1}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info);
      } else if (matchType == 4) {
         String str = "{\"matchtype\": 4, \"pvpinfos\": [{\"maxpr\": 1000, \"performancerating\": 1000, \"ranking\": 3, \"type\": 4, \"rank\": -1}]}";
         RES_PVP_RECORD_INFO res_pvp_record_info = (RES_PVP_RECORD_INFO)JSON.parseObject(str, RES_PVP_RECORD_INFO.class);
         res_pvp_record_info.transId = reqPvpRecordInfo.transId;
         MessagePusher.pushMessage((IoSession)session, res_pvp_record_info);
      }

   }

   @RequestMapping
   public void ReqIdipNotices(IoSession session, REQ_IDIP_NOTICES reqIdipNotices) {
      RES_IDIP_NOTICES resIdipNotices0 = new RES_IDIP_NOTICES();
      resIdipNotices0.transId = reqIdipNotices.transId;
      MessagePusher.pushMessage((IoSession)session, resIdipNotices0);
   }

   @RequestMapping
   public void ReqBlackDiamonInfo(IoSession session, REQ_BLACK_DIAMON_INFO reqBlackDiamonInfo) {
      RES_BLACK_DIAMON_INFO resBlackDiamonInfo0 = new RES_BLACK_DIAMON_INFO();
      resBlackDiamonInfo0.transId = reqBlackDiamonInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resBlackDiamonInfo0);
   }

   @RequestMapping
   public void ReqSendingInviteFriendList(IoSession session, REQ_SENDING_INVITE_FRIEND_LIST reqSendingInviteFriendList) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_SENDING_INVITE_FRIEND_LIST resSendingInviteFriendList0 = new RES_SENDING_INVITE_FRIEND_LIST();
      resSendingInviteFriendList0.transId = reqSendingInviteFriendList.transId;
      resSendingInviteFriendList0.flist = role.getFriendBox().getSends();
      MessagePusher.pushMessage((IoSession)session, resSendingInviteFriendList0);
   }

   @RequestMapping
   public void ReqCharacList(IoSession session, REQ_CHARAC_LIST req_charac_list) {
      String openid = (String)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ACCOUNT_OPENID, String.class);
      if (openid == null) {
         this.logger.error("ReqCharacList==ERROR==openid 为空");
      } else {
         Long charguid = (Long)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER_UID, Long.class);

         try {
            Account account = SessionUtils.getAccountBySession(session);
            if (account == null) {
               account = this.accountService.getAccount(openid);
            }

            if (account.getIsStop()) {
               this.logger.error("ReqCharacList==封号==openid=={}", openid);
               return;
            }

            RES_CHARAC_LIST res_charac_list = this.roleService.getCharList(account, account.getAccountkey());
            res_charac_list.transId = req_charac_list.transId;
            MessagePusher.pushMessage((IoSession)session, res_charac_list);
            RES_PING resPing = new RES_PING();
            MessagePusher.pushMessage((IoSession)session, resPing);
         } catch (Exception e) {
            this.logger.error(Lang.getStackTrace(e));
         }

      }
   }

   @RequestMapping
   public void ReqMinigameLotteryList(IoSession session, REQ_MINIGAME_LOTTERY_LIST reqMinigameLotteryList) {
      RES_MINIGAME_LOTTERY_LIST resMinigameLotteryList0 = new RES_MINIGAME_LOTTERY_LIST();
      resMinigameLotteryList0.lotteryfreecount = 1;
      resMinigameLotteryList0.transId = reqMinigameLotteryList.transId;
      MessagePusher.pushMessage((IoSession)session, resMinigameLotteryList0);
   }

   @RequestMapping
   public void ReqPing(IoSession session, REQ_PING reqPing) {
      RES_PING resPing = new RES_PING();
      resPing.error = 0;
      resPing.responsetime = (int)(System.currentTimeMillis() % 1000);
      resPing.transId = reqPing.transId;
      MessagePusher.pushMessage((IoSession)session, resPing);
   }

   @RequestMapping
   public void ReqNoteMessengerLoad(IoSession session, REQ_NOTE_MESSENGER_LOAD reqNoteMessengerLoad) {
      RES_NOTE_MESSENGER_LOAD resNoteMessengerLoad0 = new RES_NOTE_MESSENGER_LOAD();
      resNoteMessengerLoad0.transId = reqNoteMessengerLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resNoteMessengerLoad0);
   }

   @RequestMapping
   public void ReqReceivedInviteFriendList(IoSession session, REQ_RECEIVED_INVITE_FRIEND_LIST reqReceivedInviteFriendList) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_RECEIVED_INVITE_FRIEND_LIST resReceivedInviteFriendList0 = new RES_RECEIVED_INVITE_FRIEND_LIST();
      resReceivedInviteFriendList0.transId = reqReceivedInviteFriendList.transId;
      resReceivedInviteFriendList0.flist = role.getFriendBox().getReceived();
      MessagePusher.pushMessage((IoSession)session, resReceivedInviteFriendList0);
   }

   @RequestMapping
   public void ReqArtifactList(IoSession session, REQ_ARTIFACT_LIST reqArtifactList) {
      RES_ARTIFACT_LIST resArtifactList = new RES_ARTIFACT_LIST();
      resArtifactList.maxcount = 80;
      resArtifactList.transId = reqArtifactList.transId;
      MessagePusher.pushMessage((IoSession)session, resArtifactList);
   }

   @RequestMapping
   public void ReqEpicPieceList(IoSession session, REQ_EPIC_PIECE_LIST reqEpicPieceList) {
      Account account = SessionUtils.getAccountBySession(session);
      EpicPieceBox epicPieceBox = account.getEpicPieceBox();
      List<PT_STACKABLE> epicPieces = epicPieceBox.getEpicPieceList();
      int size = epicPieces.size();
      int pageNum = size / 20;
      int remainder = size % 20;
      if (remainder != 0) {
         ++pageNum;
      }

      int count = 0;
      if (pageNum == 0) {
         RES_EPIC_PIECE_LIST resEpicPieceList0 = new RES_EPIC_PIECE_LIST();
         resEpicPieceList0.maxcount = 200;
         resEpicPieceList0.transId = reqEpicPieceList.transId;
         MessagePusher.pushMessage((IoSession)session, resEpicPieceList0);
      } else {
         pageNum = 0;
         RES_EPIC_PIECE_LIST resEpicPieceList0 = new RES_EPIC_PIECE_LIST();
         resEpicPieceList0.epicpiece = new ArrayList();

         for(PT_STACKABLE epic : epicPieces) {
            resEpicPieceList0.epicpiece.add(epic);
            ++count;
            resEpicPieceList0.page = pageNum;
            if (count == 20) {
               resEpicPieceList0.maxcount = 200;
               resEpicPieceList0.transId = reqEpicPieceList.transId;
               MessagePusher.pushMessage((IoSession)session, resEpicPieceList0);
               resEpicPieceList0 = new RES_EPIC_PIECE_LIST();
               resEpicPieceList0.epicpiece = new ArrayList();
               count = 0;
               ++pageNum;
            }
         }

         if (resEpicPieceList0 != null) {
            resEpicPieceList0.maxcount = 200;
            resEpicPieceList0.transId = reqEpicPieceList.transId;
            MessagePusher.pushMessage((IoSession)session, resEpicPieceList0);
         }
      }

   }

   @RequestMapping
   public void ReqLocalRewardHistory(IoSession session, REQ_LOCAL_REWARD_HISTORY reqLocalRewardHistory) {
      Integer localRewardsStatus = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.LOCAL_REWARDS_STATUS, Integer.class);
      if (localRewardsStatus == null) {
         session.setAttribute(SessionProperties.LOCAL_REWARDS_STATUS, 1);
         String str1 = "{\"page\": 1, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 1002001}, {\"rewardkey\": 1002002}, {\"rewardkey\": 1002003}, {\"rewardkey\": 1002004}, {\"rewardkey\": 1002005}, {\"rewardkey\": 1003001}, {\"rewardkey\": 1003002}, {\"rewardkey\": 1003003}, {\"rewardkey\": 1003004}, {\"rewardkey\": 1003005}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history1 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str1, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history1);
         String str2 = "{\"page\": 2, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 1004001}, {\"rewardkey\": 1004002}, {\"rewardkey\": 1004003}, {\"rewardkey\": 1004004}, {\"rewardkey\": 1004005}, {\"rewardkey\": 1005001}, {\"rewardkey\": 1005002}, {\"rewardkey\": 1005003}, {\"rewardkey\": 1009001}, {\"rewardkey\": 1009002}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history2 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str2, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history2);
         String str3 = "{\"page\": 3, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 1009003}, {\"rewardkey\": 1009004}, {\"rewardkey\": 1009005}, {\"rewardkey\": 1006001}, {\"rewardkey\": 1006002}, {\"rewardkey\": 1006003}, {\"rewardkey\": 1006004}, {\"rewardkey\": 1006005}, {\"rewardkey\": 1007001}, {\"rewardkey\": 1007002}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history3 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str3, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history3);
         String str4 = "{\"page\": 4, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 1007003}, {\"rewardkey\": 1001001}, {\"rewardkey\": 1001002}, {\"rewardkey\": 1001003}, {\"rewardkey\": 1008001}, {\"rewardkey\": 1008002}, {\"rewardkey\": 1008003}, {\"rewardkey\": 1008004}, {\"rewardkey\": 1008005}, {\"rewardkey\": 1010001}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history4 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str4, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history4);
         String str5 = "{\"page\": 5, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 1010002}, {\"rewardkey\": 1010003}, {\"rewardkey\": 1011001}, {\"rewardkey\": 1011002}, {\"rewardkey\": 1011003}, {\"rewardkey\": 1012001}, {\"rewardkey\": 1012002}, {\"rewardkey\": 1012003}, {\"rewardkey\": 1012004}, {\"rewardkey\": 1012005}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history5 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str5, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history5);
         String str6 = "{\"page\": 6, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 2009001}, {\"rewardkey\": 2009002}, {\"rewardkey\": 2009003}, {\"rewardkey\": 2009004}, {\"rewardkey\": 2009005}, {\"rewardkey\": 2006001}, {\"rewardkey\": 2006002}, {\"rewardkey\": 2006003}, {\"rewardkey\": 2007001}, {\"rewardkey\": 2007002}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history6 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str6, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history6);
         String str7 = "{\"page\": 7, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 2007003}, {\"rewardkey\": 2001001}, {\"rewardkey\": 2001002}, {\"rewardkey\": 2001003}, {\"rewardkey\": 2008001}, {\"rewardkey\": 2008002}, {\"rewardkey\": 2008003}, {\"rewardkey\": 2008004}, {\"rewardkey\": 2008005}, {\"rewardkey\": 2011001}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history7 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str7, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history7);
         String str8 = "{\"page\": 8, \"totalpage\": 8, \"rewards\": [{\"rewardkey\": 2011002}, {\"rewardkey\": 2011003}, {\"rewardkey\": 2012001}, {\"rewardkey\": 2012002}, {\"rewardkey\": 2012003}, {\"rewardkey\": 2012004}, {\"rewardkey\": 2012005}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history8 = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str8, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history8);
      } else {
         String str = "{\"page\": 1, \"totalpage\": 1, \"rewards\": [{\"rewardkey\": 1002001}, {\"rewardkey\": 1002002}, {\"rewardkey\": 1002003}, {\"rewardkey\": 1002004}, {\"rewardkey\": 1002005}]}";
         RES_LOCAL_REWARD_HISTORY res_local_reward_history = (RES_LOCAL_REWARD_HISTORY)JSON.parseObject(str, RES_LOCAL_REWARD_HISTORY.class);
         MessagePusher.pushMessage((IoSession)session, res_local_reward_history);
      }

   }

   @RequestMapping
   public void ReqCardList(IoSession session, REQ_CARD_LIST reqCardList) {
      RES_CARD_LIST resCardList0 = new RES_CARD_LIST();
      Role role = SessionUtils.getRoleBySession(session);
      resCardList0.maxcount = 40;
      resCardList0.card = role.getCardBox().getCards();
      resCardList0.transId = reqCardList.transId;
      MessagePusher.pushMessage((IoSession)session, resCardList0);
   }

   @RequestMapping
   public void ReqMinigameMiniPongLoad(IoSession session, REQ_MINIGAME_MINI_PONG_LOAD reqMinigameMiniPongLoad) {
      RES_MINIGAME_MINI_PONG_LOAD resMinigameMiniPongLoad0 = new RES_MINIGAME_MINI_PONG_LOAD();
      resMinigameMiniPongLoad0.error = 2;
      resMinigameMiniPongLoad0.transId = reqMinigameMiniPongLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resMinigameMiniPongLoad0);
   }

   @RequestMapping
   public void ReqCreatureList(IoSession session, REQ_CREATURE_LIST reqCreatureList) {
      RES_CREATURE_LIST resCreatureList0 = new RES_CREATURE_LIST();
      resCreatureList0.maxcount = 80;
      resCreatureList0.transId = reqCreatureList.transId;
      MessagePusher.pushMessage((IoSession)session, resCreatureList0);
   }

   @RequestMapping
   public void ReqMaterialList(IoSession session, REQ_MATERIAL_LIST reqMaterialList) {
      Role role = SessionUtils.getRoleBySession(session);
      MaterialBox matrialBox = role.getMaterialBox();
      Map<Integer, PT_STACKABLE> materials = matrialBox.getMaterialsMap();
      int size = materials.size();
      int pageNum = size / 20;
      int remainder = size % 20;
      if (remainder != 0) {
         ++pageNum;
      }

      int count = 0;
      if (pageNum == 0) {
         RES_MATERIAL_LIST res_material_list = new RES_MATERIAL_LIST();
         res_material_list.maxcount = 40;
         res_material_list.transId = reqMaterialList.transId;
         MessagePusher.pushMessage((IoSession)session, res_material_list);
      } else {
         pageNum = 0;
         RES_MATERIAL_LIST res_material_list = new RES_MATERIAL_LIST();
         res_material_list.material = new ArrayList();

         for(PT_STACKABLE material : materials.values()) {
            res_material_list.material.add(material);
            ++count;
            res_material_list.page = pageNum;
            if (count == 20) {
               res_material_list.maxcount = 40;
               res_material_list.transId = reqMaterialList.transId;
               MessagePusher.pushMessage((IoSession)session, res_material_list);
               res_material_list = new RES_MATERIAL_LIST();
               res_material_list.material = new ArrayList();
               count = 0;
               ++pageNum;
            }
         }

         if (res_material_list != null) {
            res_material_list.maxcount = 40;
            res_material_list.transId = reqMaterialList.transId;
            MessagePusher.pushMessage((IoSession)session, res_material_list);
         }
      }

   }

   @RequestMapping
   public void ReqTutorialList(IoSession session, REQ_TUTORIAL_LIST reqTutorialList) {
      Role role = SessionUtils.getRoleBySession(session);
      TutoBox tutoBox = role.getTutoBox();
      RES_TUTORIAL_LIST resTutorialList = new RES_TUTORIAL_LIST();
      resTutorialList.list = new ArrayList();
      resTutorialList.list.add(tutoBox.getTutos0());
      resTutorialList.list.add(tutoBox.getTutos1());
      resTutorialList.list.add(tutoBox.getTutos2());
      resTutorialList.list.add(tutoBox.getTutos3());
      resTutorialList.transId = reqTutorialList.transId;
      MessagePusher.pushMessage((IoSession)session, resTutorialList);
   }

   @RequestMapping
   public void ReqLoadFriendsInfo(IoSession session, REQ_LOAD_FRIENDS_INFO reqLoadFriendsInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_LOAD_FRIENDS_INFO resLoadFriendsInfo0 = new RES_LOAD_FRIENDS_INFO();
      if (role.getFriendBox().getFriends().isEmpty()) {
         resLoadFriendsInfo0.transId = reqLoadFriendsInfo.transId;
      } else {
         resLoadFriendsInfo0.flist = role.getFriendBox().getFriends();
      }

      MessagePusher.pushMessage((IoSession)session, resLoadFriendsInfo0);
   }

   @RequestMapping
   public void ReqFameAndCharmPoint(IoSession session, REQ_FAME_AND_CHARM_POINT reqFameAndCharmPoint) {
      RES_FAME_AND_CHARM_POINT resFameAndCharmPoint0 = new RES_FAME_AND_CHARM_POINT();
      resFameAndCharmPoint0.transId = reqFameAndCharmPoint.transId;
      MessagePusher.pushMessage((IoSession)session, resFameAndCharmPoint0);
   }

   @RequestMapping
   public void ReqServerNotiAck(IoSession session, REQ_SERVER_NOTI_ACK reqServerNotiAck) {
      RES_SERVER_NOTI_ACK resServerNotiAck = new RES_SERVER_NOTI_ACK();
      resServerNotiAck.transId = reqServerNotiAck.transId;
      MessagePusher.pushMessage((IoSession)session, resServerNotiAck);
   }

   @RequestMapping
   public void ReqEquipList(IoSession session, REQ_EQUIP_LIST reqEquipList) {
      Role role = SessionUtils.getRoleBySession(session);
      EquipBox equipBox = role.getEquipBox();
      List<PT_EQUIP> equips = equipBox.getEquipList();
      int size = equips.size();
      int pageNum = size / 20;
      int remainder = size % 20;
      if (remainder != 0) {
         ++pageNum;
      }

      if (pageNum == 0) {
         RES_EQUIP_LIST resEquipList = new RES_EQUIP_LIST();
         resEquipList.maxcount = 40;
         resEquipList.transId = reqEquipList.transId;
         MessagePusher.pushMessage((IoSession)session, resEquipList);
      } else {
         for(int i = 0; i < pageNum; ++i) {
            RES_EQUIP_LIST resEquipList = new RES_EQUIP_LIST();
            if (i != 0) {
               resEquipList.page = i;
            }

            resEquipList.equip = new ArrayList();
            if (i == pageNum - 1 && remainder != 0) {
               for(int j = 0; j < remainder; ++j) {
                  int k = j + 20 * i;
                  resEquipList.equip.add(equips.get(k));
               }
            } else {
               for(int j = 0; j < 20; ++j) {
                  int k = j + 20 * i;
                  resEquipList.equip.add(equips.get(k));
               }
            }

            resEquipList.maxcount = role.getEquipBox().getMaxcount();
            resEquipList.transId = reqEquipList.transId;
            MessagePusher.pushMessage((IoSession)session, resEquipList);
         }
      }

   }

   @RequestMapping
   public void ReqLoadBlacklist(IoSession session, REQ_LOAD_BLACKLIST reqLoadBlacklist) {
      RES_LOAD_BLACKLIST resLoadBlacklist0 = new RES_LOAD_BLACKLIST();
      resLoadBlacklist0.transId = reqLoadBlacklist.transId;
      MessagePusher.pushMessage((IoSession)session, resLoadBlacklist0);
   }

   @RequestMapping
   public void ReqTitleList(IoSession session, REQ_TITLE_LIST reqTitleList) {
      Role role = SessionUtils.getRoleBySession(session);
      TitleBox titleBox = role.getTitleBox();
      RES_TITLE_LIST resTitleList0 = new RES_TITLE_LIST();
      if (!Util.isEmpty(titleBox.getTitles())) {
         resTitleList0.title = titleBox.getTitles();
      }

      resTitleList0.maxcount = titleBox.getMaxcount();
      resTitleList0.transId = reqTitleList.transId;
      MessagePusher.pushMessage((IoSession)session, resTitleList0);
   }

   @RequestMapping
   public void ReqCrackequipList(IoSession session, REQ_CRACKEQUIP_LIST reqCrackequipList) {
      RES_CRACKEQUIP_LIST resCrackequipList0 = new RES_CRACKEQUIP_LIST();
      resCrackequipList0.maxcount = 40;
      resCrackequipList0.transId = reqCrackequipList.transId;
      MessagePusher.pushMessage((IoSession)session, resCrackequipList0);
   }

   @RequestMapping
   public void ReqConsumableList(IoSession session, REQ_CONSUMABLE_LIST reqConsumableList) {
      Role role = SessionUtils.getRoleBySession(session);
      ConsumableBox matrialBox = role.getConsumableBox();
      List<DEF_ITEM_CONSUMABLE> consumables = matrialBox.getConsumeList();
      int size = consumables.size();
      int pageNum = size / 20;
      int remainder = size % 20;
      if (remainder != 0) {
         ++pageNum;
      }

      if (pageNum == 0) {
         RES_CONSUMABLE_LIST res_consumable_list = new RES_CONSUMABLE_LIST();
         res_consumable_list.maxcount = 40;
         res_consumable_list.transId = reqConsumableList.transId;
         MessagePusher.pushMessage((IoSession)session, res_consumable_list);
      } else {
         for(int i = 0; i < pageNum; ++i) {
            RES_CONSUMABLE_LIST res_consumable_list = new RES_CONSUMABLE_LIST();
            if (i != 0) {
               res_consumable_list.page = i;
            }

            res_consumable_list.consume = new ArrayList();
            if (i == pageNum - 1 && remainder != 0) {
               for(int j = 0; j < remainder; ++j) {
                  int k = j + 20 * i;
                  res_consumable_list.consume.add(consumables.get(k));
               }
            } else {
               for(int j = 0; j < 20; ++j) {
                  int k = j + 20 * i;
                  res_consumable_list.consume.add(consumables.get(k));
               }
            }

            res_consumable_list.maxcount = 40;
            res_consumable_list.transId = reqConsumableList.transId;
            MessagePusher.pushMessage((IoSession)session, res_consumable_list);
         }
      }

   }

   @RequestMapping
   public void ReqCharacterFrameTabList(IoSession session, REQ_CHARACTER_FRAME_TAB_LIST reqCharacterFrameTabList) {
      Role role = SessionUtils.getRoleBySession(session);
      CharFrameBox charFrameBox = role.getCharFrameBox();
      List<PT_SKIN_ITEM> list = charFrameBox.getCharFrames();
      RES_CHARACTER_FRAME_TAB_LIST resCharacterFrameTabList0 = new RES_CHARACTER_FRAME_TAB_LIST();
      if (!Util.isEmpty(list)) {
         resCharacterFrameTabList0.list = list;
      }

      resCharacterFrameTabList0.transId = reqCharacterFrameTabList.transId;
      MessagePusher.pushMessage((IoSession)session, resCharacterFrameTabList0);
   }

   @RequestMapping
   public void ReqBilingKrBalance(IoSession session, REQ_BILING_KR_BALANCE reqBilingKrBalance) {
      Account account = SessionUtils.getAccountBySession(session);
      RES_BILING_KR_BALANCE resBilingKrBalance0 = new RES_BILING_KR_BALANCE();
      resBilingKrBalance0.accumulatecera = account.getAccumulatecera();
      resBilingKrBalance0.cera = account.getMoneyBox().getCeraCnt();
      resBilingKrBalance0.transId = reqBilingKrBalance.transId;
      MessagePusher.pushMessage((IoSession)session, resBilingKrBalance0);
      NOTIFY_CONTENTS_NOTIFY_REMOVE notify_contents_notify_remove1 = new NOTIFY_CONTENTS_NOTIFY_REMOVE();
      MessagePusher.pushMessage((IoSession)session, notify_contents_notify_remove1);
      NOTIFY_CONTENTS_NOTIFY_REMOVE notify_contents_notify_remove2 = new NOTIFY_CONTENTS_NOTIFY_REMOVE();
      notify_contents_notify_remove2.content = 2;
      MessagePusher.pushMessage((IoSession)session, notify_contents_notify_remove2);
      NOTIFY_CONTENTS_NOTIFY_REMOVE notify_contents_notify_remove3 = new NOTIFY_CONTENTS_NOTIFY_REMOVE();
      notify_contents_notify_remove3.content = 1;
      MessagePusher.pushMessage((IoSession)session, notify_contents_notify_remove3);
   }

   @RequestMapping
   public void ReqAdventureUnionInfo(IoSession session, REQ_ADVENTURE_UNION_INFO reqAdventureUnionInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      AdventureUnionInfo adventureUnionInfo = account.getAdventureUnionInfo();
      this.logger.error("adventureUnionInfo = {}", adventureUnionInfo);
      RES_ADVENTURE_UNION_INFO res_adventure_union_info = new RES_ADVENTURE_UNION_INFO();
      res_adventure_union_info.level = adventureUnionInfo.level;
      if (adventureUnionInfo.exp != null) {
         res_adventure_union_info.exp = adventureUnionInfo.exp.intValue();
      }

      res_adventure_union_info.day = adventureUnionInfo.day;
      res_adventure_union_info.name = adventureUnionInfo.name;
      res_adventure_union_info.updatetime = adventureUnionInfo.updatetime;
      res_adventure_union_info.expedition = adventureUnionInfo.expedition;
      if (adventureUnionInfo.isadventureCondition != null) {
         res_adventure_union_info.isadventureCondition = adventureUnionInfo.isadventureCondition;
      }

      res_adventure_union_info.transId = reqAdventureUnionInfo.transId;
      MessagePusher.pushMessage((IoSession)session, res_adventure_union_info);
   }

   @RequestMapping
   public void ReqIdipProhibitList(IoSession session, REQ_IDIP_PROHIBIT_LIST reqIdipProhibitList) {
      RES_IDIP_PROHIBIT_LIST resIdipProhibitList0 = new RES_IDIP_PROHIBIT_LIST();
      resIdipProhibitList0.transId = reqIdipProhibitList.transId;
      MessagePusher.pushMessage((IoSession)session, resIdipProhibitList0);
   }

   @RequestMapping
   public void ReqBookMarkList(IoSession session, REQ_BOOKMARK_LIST reqBookMarkList) {
      RES_BOOKMARK_LIST resBookMarkList0 = new RES_BOOKMARK_LIST();
      resBookMarkList0.maxcount = 40;
      resBookMarkList0.transId = reqBookMarkList.transId;
      MessagePusher.pushMessage((IoSession)session, resBookMarkList0);
   }

   @RequestMapping
   public void ReqMinigameChemicalLoad(IoSession session, REQ_MINIGAME_CHEMICAL_LOAD reqMinigameChemicalLoad) {
      RES_MINIGAME_CHEMICAL_LOAD resMinigameChemicalLoad0 = new RES_MINIGAME_CHEMICAL_LOAD();
      resMinigameChemicalLoad0.error = 4;
      resMinigameChemicalLoad0.transId = reqMinigameChemicalLoad.transId;
      MessagePusher.pushMessage((IoSession)session, resMinigameChemicalLoad0);
   }

   @RequestMapping
   public void ReqDungeonTickets(IoSession session, REQ_DUNGEON_TICKETS reqDungeonTickets) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonTicketsBox dungeonTicketsBox = role.getDungeonTicketsBox();
      int hellGauge = 250;
      RES_DUNGEON_TICKETS resDungeonTickets = new RES_DUNGEON_TICKETS();
      resDungeonTickets.ticket = dungeonTicketsBox.getTicketList();
      resDungeonTickets.jarofgreed = dungeonTicketsBox.getJarofgreed();
      resDungeonTickets.lotteryfreecount = dungeonTicketsBox.getLotteryfreecount();
      resDungeonTickets.recvfriendcount = dungeonTicketsBox.getRecvfriendcount();
      resDungeonTickets.sendfriendcount = dungeonTicketsBox.getSendfriendcount();
      resDungeonTickets.recvplatformfriendcount = dungeonTicketsBox.getRecvplatformfriendcount();
      resDungeonTickets.sendplatformfriendcount = dungeonTicketsBox.getSendplatformfriendcount();
      resDungeonTickets.guildredpacketrecvcount = dungeonTicketsBox.getGuildredpacketrecvcount();
      resDungeonTickets.guildredpacketsendcount = dungeonTicketsBox.getGuildredpacketsendcount();
      resDungeonTickets.battleleaguerewardlimit = dungeonTicketsBox.getBattleleaguerewardlimit();
      resDungeonTickets.clearsweepdungeonlist = dungeonTicketsBox.getClearsweepdungeonlist();
      resDungeonTickets.gauges = new ArrayList();
      PT_GAUGE pt_gauge = new PT_GAUGE();
      pt_gauge.gauge = hellGauge;
      PT_GAUGE pt_gauge2 = new PT_GAUGE();
      pt_gauge2.type = ENUM_DUNGEON_GAUGE_TYPE.T.RANDOMHELL;
      resDungeonTickets.gauges.add(pt_gauge);
      resDungeonTickets.gauges.add(pt_gauge2);
      resDungeonTickets.battleleagueguildrewardlimit = dungeonTicketsBox.getBattleleagueguildrewardlimit();
      resDungeonTickets.transId = reqDungeonTickets.transId;
      MessagePusher.pushMessage((IoSession)session, resDungeonTickets);
   }

   @RequestMapping
   public void ReqWardrobeInfo(IoSession session, REQ_WARDROBE_INFO reqWardrobeInfo) {
      RES_WARDROBE_INFO resWardrobeInfo0 = new RES_WARDROBE_INFO();
      PT_WARDROBE_INFO wardrobe1 = new PT_WARDROBE_INFO();
      resWardrobeInfo0.wardrobe = wardrobe1;
      resWardrobeInfo0.transId = reqWardrobeInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resWardrobeInfo0);
   }

   @RequestMapping
   public void ReqFlagList(IoSession session, REQ_FLAG_LIST reqFlagList) {
      RES_FLAG_LIST resFlagList0 = new RES_FLAG_LIST();
      resFlagList0.maxcount = 40;
      resFlagList0.transId = reqFlagList.transId;
      MessagePusher.pushMessage((IoSession)session, resFlagList0);
   }

   @RequestMapping
   public void ReqChatFrameTabList(IoSession session, REQ_CHAT_FRAME_TAB_LIST reqChatFrameTabList) {
      Role role = SessionUtils.getRoleBySession(session);
      ChatFrameBox chatFrameBox = role.getChatFrameBox();
      List<PT_SKIN_ITEM> list = chatFrameBox.getChatFrames();
      RES_CHAT_FRAME_TAB_LIST resChatFrameTabList0 = new RES_CHAT_FRAME_TAB_LIST();
      if (!Util.isEmpty(list)) {
         resChatFrameTabList0.list = list;
      }

      resChatFrameTabList0.transId = reqChatFrameTabList.transId;
      MessagePusher.pushMessage((IoSession)session, resChatFrameTabList0);
   }

   @RequestMapping
   public void ReqLoadShareReward(IoSession session, REQ_LOAD_SHARE_REWARD reqLoadShareReward) {
      RES_LOAD_SHARE_REWARD resLoadShareReward0 = new RES_LOAD_SHARE_REWARD();
      resLoadShareReward0.transId = reqLoadShareReward.transId;
      MessagePusher.pushMessage((IoSession)session, resLoadShareReward0);
   }

   @RequestMapping
   public void ReqMoneyItemList(IoSession session, REQ_MONEY_ITEM_LIST reqMoneyItemList) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      RES_MONEY_ITEM_LIST res_money_item_list = new RES_MONEY_ITEM_LIST();
      res_money_item_list.currency = new ArrayList();

      for(Map.Entry<Integer, PT_MONEY_ITEM> entry : moneyBox.getCurrency().entrySet()) {
         res_money_item_list.currency.add(entry.getValue());
      }

      res_money_item_list.accountcurrency = new ArrayList();

      for(Map.Entry<Integer, PT_MONEY_ITEM> entry : accountMoneyBox.getAccountcurrency().entrySet()) {
         res_money_item_list.accountcurrency.add(entry.getValue());
      }

      res_money_item_list.transId = reqMoneyItemList.transId;
      MessagePusher.pushMessage((IoSession)session, res_money_item_list);
   }

   @RequestMapping
   public void REQ_AUTHKEY_REFRESH(IoSession session, REQ_AUTHKEY_REFRESH reqAuthkeyRefresh) {
      RES_AUTHKEY_REFRESH resAuthkeyRefresh = new RES_AUTHKEY_REFRESH();
      resAuthkeyRefresh.authkey = reqAuthkeyRefresh.authkey;
      resAuthkeyRefresh.transId = reqAuthkeyRefresh.transId;
      MessagePusher.pushMessage((IoSession)session, resAuthkeyRefresh);
   }

   @RequestMapping
   public void ReqChivalryInfo(IoSession session, REQ_CHIVALRY_INFO reqChivalryInfo) {
      RES_CHIVALRY_INFO resChivalryInfo0 = new RES_CHIVALRY_INFO();
      resChivalryInfo0.transId = reqChivalryInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resChivalryInfo0);
   }

   @RequestMapping
   public void ReqScrollList(IoSession session, REQ_SCROLL_LIST reqScrollList) {
      RES_SCROLL_LIST resScrollList0 = new RES_SCROLL_LIST();
      resScrollList0.maxcount = 40;
      resScrollList0.transId = reqScrollList.transId;
      MessagePusher.pushMessage((IoSession)session, resScrollList0);
   }

   @RequestMapping
   public void ReqContentsPreviewReward(IoSession session, REQ_CONTENTS_PREVIEW_REWARD reqContentsPreviewReward) {
      RES_CONTENTS_PREVIEW_REWARD resContentsPreviewReward0 = new RES_CONTENTS_PREVIEW_REWARD();
      MessagePusher.pushMessage((IoSession)session, resContentsPreviewReward0);
   }

   @RequestMapping
   public void ReqGetLocalReward(IoSession session, REQ_GET_LOCAL_REWARD reqGetLocalReward) {
      RES_GET_LOCAL_REWARD resGetLocalReward = new RES_GET_LOCAL_REWARD();
      MessagePusher.pushMessage((IoSession)session, resGetLocalReward);
   }
}
