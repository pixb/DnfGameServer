package com.dnfm.game.party;

import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.dungeon.model.Dungeon;
import com.dnfm.game.dungeon.model.MapSpec;
import com.dnfm.game.dungeon.service.DungeonService;
import com.dnfm.game.groupdungeon.model.GroupDungeonCache;
import com.dnfm.game.party.model.DungeonParty;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.ENUM_BATTLE_OPTION;
import com.dnfm.mina.protobuf.ENUM_TEAM;
import com.dnfm.mina.protobuf.NOTIFY_CHANGE_STATUS;
import com.dnfm.mina.protobuf.NOTIFY_CONTROL_GROUP;
import com.dnfm.mina.protobuf.NOTIFY_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON;
import com.dnfm.mina.protobuf.NOTIFY_REQUEST_TO_RE_ENTER_DUNGEON;
import com.dnfm.mina.protobuf.NOTIFY_SUGGEST_MOVE_PARTY;
import com.dnfm.mina.protobuf.PT_ACCOUNT_TICKET;
import com.dnfm.mina.protobuf.PT_BATTLE_OPTION;
import com.dnfm.mina.protobuf.PT_CHAMPION_INFO;
import com.dnfm.mina.protobuf.PT_CUSTOM_DATA;
import com.dnfm.mina.protobuf.PT_CUSTOM_PVP_ROOM_SETTING;
import com.dnfm.mina.protobuf.PT_GROUP_MEMBER;
import com.dnfm.mina.protobuf.PT_MAP_GUIDS;
import com.dnfm.mina.protobuf.PT_MEMBER_AREA_INFO;
import com.dnfm.mina.protobuf.PT_OBJECT_INFO;
import com.dnfm.mina.protobuf.PT_RES_PARTY_LIST;
import com.dnfm.mina.protobuf.PT_USER_INFO;
import com.dnfm.mina.protobuf.PT_USER_LOADING_STATUS;
import com.dnfm.mina.protobuf.PT_USER_MINIMUM_INFO;
import com.dnfm.mina.protobuf.REQ_CHECK_PROHIBITED_WORD;
import com.dnfm.mina.protobuf.REQ_CONNECT_BATTLE_SERVER;
import com.dnfm.mina.protobuf.REQ_CONTROL_GROUP;
import com.dnfm.mina.protobuf.REQ_CONTROL_GROUP_CUSTOM;
import com.dnfm.mina.protobuf.REQ_CONTROL_GROUP_QUERYAREA;
import com.dnfm.mina.protobuf.REQ_HALF_OPEN_PARTY_ACCEPT;
import com.dnfm.mina.protobuf.REQ_HALF_OPEN_PARTY_JOIN;
import com.dnfm.mina.protobuf.REQ_HALF_OPEN_PARTY_REFUSE;
import com.dnfm.mina.protobuf.REQ_MULTI_PLAY_DUNGEON_ENTER_COMPLETE;
import com.dnfm.mina.protobuf.REQ_MULTI_PLAY_START_DUNGEON;
import com.dnfm.mina.protobuf.REQ_MULTI_PLAY_SYNC_DUNGEON;
import com.dnfm.mina.protobuf.REQ_PARTY_DUNGEON_CONDITION;
import com.dnfm.mina.protobuf.REQ_PARTY_LOADING_STATUS;
import com.dnfm.mina.protobuf.REQ_READY_TO_LOCKSTEP;
import com.dnfm.mina.protobuf.REQ_RECOMMAND_GROUP;
import com.dnfm.mina.protobuf.REQ_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON;
import com.dnfm.mina.protobuf.REQ_REQUEST_TO_RE_ENTER_DUNGEON;
import com.dnfm.mina.protobuf.REQ_SEARCH_PARTY_LIST;
import com.dnfm.mina.protobuf.REQ_START_MULTI_PLAY;
import com.dnfm.mina.protobuf.REQ_SUGGEST_MOVE_PARTY;
import com.dnfm.mina.protobuf.REQ_TARGET_USER_PARTY_INFO;
import com.dnfm.mina.protobuf.REQ_VOTE_KICK_OUT_USER;
import com.dnfm.mina.protobuf.REQ_WAITING_TO_USERS_LOADING;
import com.dnfm.mina.protobuf.RES_CHECK_PROHIBITED_WORD;
import com.dnfm.mina.protobuf.RES_CONNECT_BATTLE_SERVER;
import com.dnfm.mina.protobuf.RES_CONTROL_GROUP;
import com.dnfm.mina.protobuf.RES_CONTROL_GROUP_CUSTOM;
import com.dnfm.mina.protobuf.RES_CONTROL_GROUP_QUERYAREA;
import com.dnfm.mina.protobuf.RES_HALF_OPEN_PARTY_ACCEPT;
import com.dnfm.mina.protobuf.RES_HALF_OPEN_PARTY_JOIN;
import com.dnfm.mina.protobuf.RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE;
import com.dnfm.mina.protobuf.RES_MULTI_PLAY_START_DUNGEON;
import com.dnfm.mina.protobuf.RES_MULTI_PLAY_SYNC_DUNGEON;
import com.dnfm.mina.protobuf.RES_PARTY_DUNGEON_CONDITION;
import com.dnfm.mina.protobuf.RES_PARTY_LOADING_STATUS;
import com.dnfm.mina.protobuf.RES_READY_TO_LOCKSTEP;
import com.dnfm.mina.protobuf.RES_RECOMMAND_GROUP;
import com.dnfm.mina.protobuf.RES_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON;
import com.dnfm.mina.protobuf.RES_REQUEST_TO_RE_ENTER_DUNGEON;
import com.dnfm.mina.protobuf.RES_SEARCH_PARTY_LIST;
import com.dnfm.mina.protobuf.RES_STAGE_INFO;
import com.dnfm.mina.protobuf.RES_START_MULTI_PLAY;
import com.dnfm.mina.protobuf.RES_SUGGEST_MOVE_PARTY;
import com.dnfm.mina.protobuf.RES_TARGET_USER_PARTY_INFO;
import com.dnfm.mina.protobuf.RES_VOTE_KICK_OUT_USER;
import com.dnfm.mina.protobuf.RES_WAITING_TO_USERS_LOADING;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import com.dnfm.mina.udp.Global;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PartyController {
   @Autowired
   RoleService roleService;
   @Autowired
   DungeonService dungeonService;
   private final Logger logger = LoggerFactory.getLogger(PartyController.class);
   @Autowired
   private PartyService partyService;

   @RequestMapping
   public void REQ_HALF_OPEN_PARTY_ACCEPT(IoSession session, REQ_HALF_OPEN_PARTY_ACCEPT req_half_open_party_accept) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      long charguid = req_half_open_party_accept.charguid;
      Role targetRole = (Role)DataCache.ONLINE_ROLES.get(charguid);
      RES_HALF_OPEN_PARTY_ACCEPT res_half_open_party_accept = new RES_HALF_OPEN_PARTY_ACCEPT();
      res_half_open_party_accept.leaderguid = dungeonParty.charguid;
      res_half_open_party_accept.partyguid = dungeonParty.partyguid;
      res_half_open_party_accept.ip = dungeonParty.ip;
      res_half_open_party_accept.port = dungeonParty.port;
      res_half_open_party_accept.world = 1;
      res_half_open_party_accept.channel = 1;
      MessagePusher.pushMessage((Role)targetRole, res_half_open_party_accept);
   }

   @RequestMapping
   public void REQ_HALF_OPEN_PARTY_REFUSE(IoSession session, REQ_HALF_OPEN_PARTY_REFUSE req_half_open_party_refuse) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
   }

   @RequestMapping
   public void REQ_CONTROL_GROUP_CUSTOM(IoSession session, REQ_CONTROL_GROUP_CUSTOM req_control_group_custom) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_CONTROL_GROUP_CUSTOM res_control_group_custom = new RES_CONTROL_GROUP_CUSTOM();
      res_control_group_custom.customdata = req_control_group_custom.customdata;
      DungeonParty dungeonParty = role.getDungeonParty();

      for(PT_GROUP_MEMBER member : dungeonParty.users) {
         Role targetRole = (Role)DataCache.ONLINE_ROLES.get(member.charguid);
         if (targetRole != null) {
            MessagePusher.pushMessage((Role)targetRole, res_control_group_custom);
         }
      }

   }

   @RequestMapping
   public void REQ_START_MULTI_PLAY(IoSession session, REQ_START_MULTI_PLAY req_start_multi_play) {
      Role role = SessionUtils.getRoleBySession(session);
      Integer input = req_start_multi_play.input;
      Integer type = req_start_multi_play.type;
      Integer index = req_start_multi_play.index;
      Long partyguid = req_start_multi_play.partyguid;
      DungeonParty dungeonParty = role.getDungeonParty();
      dungeonParty.roomid = 101725L;
      Dungeon var10000 = (Dungeon)DataCache.DUNGEON_MAP.get(dungeonParty.dungeonindex);
      List<MapSpec> mapSpecs = (List)DataCache.DUNGEON_MAP_SPEC.get(dungeonParty.dungeonindex);
      int startStageGuid = Util.randInt(15, 90);
      dungeonParty.startStageId = startStageGuid;
      dungeonParty.map_guids = new ArrayList();

      for(int i = 0; i < mapSpecs.size(); ++i) {
         PT_MAP_GUIDS pt_map_guids = new PT_MAP_GUIDS();
         pt_map_guids.guid = startStageGuid + i;
         dungeonParty.map_guids.add(pt_map_guids);
      }

      List<RES_STAGE_INFO> stage_infos = this.dungeonService.getMultiStageInfoList(dungeonParty.dungeonindex, session, mapSpecs, (long)dungeonParty.startStageId, role.getUid());
      dungeonParty.stage_infos = stage_infos;
      int resSzie = stage_infos.size();
      int lastMapObjSize = ((RES_STAGE_INFO)stage_infos.get(resSzie - 1)).map.objects.size();
      long lastMonsterId = ((PT_OBJECT_INFO)((RES_STAGE_INFO)stage_infos.get(resSzie - 1)).map.objects.get(lastMapObjSize - 1)).guid;
      long cobjectId = lastMonsterId + 1L;
      int playerId = 1725;
      RES_START_MULTI_PLAY res_start_multi_play = new RES_START_MULTI_PLAY();
      res_start_multi_play.matchingguid = 32007981672746209L;
      res_start_multi_play.dungeonguid = res_start_multi_play.matchingguid;
      res_start_multi_play.battleworld = 1;
      res_start_multi_play.bchannel = 1;
      res_start_multi_play.bip = "101.43.14.204";
      res_start_multi_play.bport = 10001;
      res_start_multi_play.matchtype = 3;
      res_start_multi_play.world = 1;
      res_start_multi_play.channel = 1;
      res_start_multi_play.users = new ArrayList();
      res_start_multi_play.detail = new ArrayList();

      for(PT_GROUP_MEMBER member : dungeonParty.users) {
         member.playerid = playerId;
         member.objectgroupid = (int)cobjectId;
         member.ready = null;
         member.waiting = null;
         res_start_multi_play.users.add(member.charguid);
         PT_USER_MINIMUM_INFO pt_user_minimum_info = new PT_USER_MINIMUM_INFO();
         pt_user_minimum_info.charguid = member.charguid;
         pt_user_minimum_info.job = member.job;
         pt_user_minimum_info.growtype = member.growtype;
         pt_user_minimum_info.secgrowtype = member.secondgrowtype;
         pt_user_minimum_info.level = member.level;
         pt_user_minimum_info.name = member.name;
         pt_user_minimum_info.teamtype = ENUM_TEAM.T.BLUE;
         pt_user_minimum_info.world = 1;
         res_start_multi_play.detail.add(pt_user_minimum_info);
         ++playerId;
         cobjectId += 2L;
      }

      res_start_multi_play.dungeonindex = index;

      for(PT_GROUP_MEMBER member : dungeonParty.users) {
         Role targetRole = (Role)DataCache.ONLINE_ROLES.get(member.charguid);
         if (targetRole != null) {
            IoSession session1 = SessionManager.INSTANCE.getSessionBy(targetRole.getUid());
            res_start_multi_play.transId = SessionUtils.getNotiTransId(session1);
            MessagePusher.pushMessage((IoSession)session1, res_start_multi_play);
         }
      }

   }

   @RequestMapping
   public void REQ_PARTY_LOADING_STATUS(IoSession session, REQ_PARTY_LOADING_STATUS req_party_loading_status) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      RES_PARTY_LOADING_STATUS res_party_loading_status = new RES_PARTY_LOADING_STATUS();
      res_party_loading_status.userstatus = new ArrayList();

      for(PT_GROUP_MEMBER member : dungeonParty.users) {
         Role targetRole = (Role)DataCache.ONLINE_ROLES.get(member.charguid);
         PT_USER_LOADING_STATUS pt_user_loading_status = new PT_USER_LOADING_STATUS();
         pt_user_loading_status.charguid = member.charguid;
         pt_user_loading_status.kickoutvotes = new ArrayList();
         pt_user_loading_status.kickoutvotes.add(0L);
         res_party_loading_status.userstatus.add(pt_user_loading_status);
      }

      for(PT_GROUP_MEMBER m : dungeonParty.users) {
         Role targetRole = (Role)DataCache.ONLINE_ROLES.get(m.charguid);
         if (targetRole != null) {
            MessagePusher.pushMessage((Role)targetRole, res_party_loading_status);
         }
      }

   }

   @RequestMapping
   public void REQ_VOTE_KICK_OUT_USER(IoSession session, REQ_VOTE_KICK_OUT_USER req_vote_kick_out_user) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_VOTE_KICK_OUT_USER res_vote_kick_out_user = new RES_VOTE_KICK_OUT_USER();
      MessagePusher.pushMessage((Role)role, res_vote_kick_out_user);
   }

   @RequestMapping
   public void REQ_CONNECT_BATTLE_SERVER(IoSession session, REQ_CONNECT_BATTLE_SERVER req_connect_battle_server) {
      Role role = (Role)DataCache.ONLINE_ROLES.get(req_connect_battle_server.charguid);
      session.setAttribute(SessionProperties.PLAYER, role);
      RES_CONNECT_BATTLE_SERVER res_connect_battle_server = new RES_CONNECT_BATTLE_SERVER();
      res_connect_battle_server.authkey = req_connect_battle_server.authkey;
      res_connect_battle_server.bchannel = 1;
      res_connect_battle_server.servertime = TimeUtil.currS();
      res_connect_battle_server.encrypt = true;
      res_connect_battle_server.seeds = new ArrayList();
      res_connect_battle_server.transId = req_connect_battle_server.transId;
      MessagePusher.pushMessage((IoSession)session, res_connect_battle_server);
   }

   @RequestMapping
   public void REQ_MULTI_PLAY_START_DUNGEON(IoSession session, REQ_MULTI_PLAY_START_DUNGEON req_multi_play_start_dungeon) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      session.setAttribute(SessionProperties.CONSUME_FATIGUE, 0);
      session.setAttribute(SessionProperties.CUR_DUNGEON_INDEX, dungeonParty.dungeonindex);
      RES_MULTI_PLAY_START_DUNGEON res_multi_play_start_dungeon = new RES_MULTI_PLAY_START_DUNGEON();
      res_multi_play_start_dungeon.dungeonguid = req_multi_play_start_dungeon.dungeonguid;
      res_multi_play_start_dungeon.partyguid = dungeonParty.partyguid;
      res_multi_play_start_dungeon.partyleaderguid = dungeonParty.charguid;
      res_multi_play_start_dungeon.fatigue = role.getFatigue();
      res_multi_play_start_dungeon.index = dungeonParty.dungeonindex;
      res_multi_play_start_dungeon.matchtype = 3;
      res_multi_play_start_dungeon.champion = new PT_CHAMPION_INFO();
      res_multi_play_start_dungeon.mapguids = dungeonParty.map_guids;
      res_multi_play_start_dungeon.customdata = new PT_CUSTOM_PVP_ROOM_SETTING();
      res_multi_play_start_dungeon.requestedindex = dungeonParty.dungeonindex;
      List<RES_STAGE_INFO> stage_infos = dungeonParty.stage_infos;
      res_multi_play_start_dungeon.characinfos = new ArrayList();

      for(PT_GROUP_MEMBER member : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(member.charguid);
         IoSession ioSession = SessionManager.INSTANCE.getSessionBy(r.getUid());
         PT_USER_INFO pt_user_info = this.roleService.getPtUserInfo(ioSession, (long)member.objectgroupid, member.playerid);
         res_multi_play_start_dungeon.characinfos.add(pt_user_info);
      }

      res_multi_play_start_dungeon.transId = req_multi_play_start_dungeon.transId;
      MessagePusher.pushMessage((IoSession)session, res_multi_play_start_dungeon);

      for(RES_STAGE_INFO stage_info : stage_infos) {
         this.logger.error("RES_MULTI_PLAY_START_DUNGEON stage_info");
         stage_info.transId = SessionUtils.getNotiTransId(session);
         MessagePusher.pushMessage((IoSession)session, stage_info);
      }

   }

   @RequestMapping
   public void REQ_MULTI_PLAY_DUNGEON_ENTER_COMPLETE(IoSession session, REQ_MULTI_PLAY_DUNGEON_ENTER_COMPLETE req_multi_play_dungeon_enter_complete) {
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      MoneyBox moneyBox = role.getMoneyBox();
      DungeonParty dungeonParty = role.getDungeonParty();
      Dungeon dungeon = (Dungeon)DataCache.DUNGEON_MAP.get(dungeonParty.dungeonindex);
      int fatigue = role.getFatigue();
      String dungeon_type = dungeon.getDungeonType();
      RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE res_multi_play_dungeon_enter_complete = new RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE();
      res_multi_play_dungeon_enter_complete.consumefatigue = 1;
      session.setAttribute(SessionProperties.CONSUME_FATIGUE, 2);
      res_multi_play_dungeon_enter_complete.fatigue = fatigue - 1;
      res_multi_play_dungeon_enter_complete.gold = moneyBox.getMoneyCnt();
      res_multi_play_dungeon_enter_complete.partyguid = dungeonParty.partyguid;
      List<PT_ACCOUNT_TICKET> list = new ArrayList();
      PT_ACCOUNT_TICKET pt_account_ticket = new PT_ACCOUNT_TICKET();
      pt_account_ticket.dungeontype = dungeon_type;
      list.add(pt_account_ticket);
      res_multi_play_dungeon_enter_complete.adventureticket = list;
      res_multi_play_dungeon_enter_complete.transId = req_multi_play_dungeon_enter_complete.transId;
      MessagePusher.pushMessage((IoSession)session, res_multi_play_dungeon_enter_complete);
      role.setFatigue(res_multi_play_dungeon_enter_complete.fatigue);
      role.save();
      Integer notifyTransId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.NOTIFY_TRANS_ID, Integer.class);
      NOTIFY_CHANGE_STATUS notify_change_status = new NOTIFY_CHANGE_STATUS();
      notify_change_status.status = 7;
      notify_change_status.transId = notifyTransId;
      notifyTransId = notifyTransId + 1;
      session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, notifyTransId);
      MessagePusher.pushMessage((IoSession)session, notify_change_status);
   }

   @RequestMapping
   public void REQ_READY_TO_LOCKSTEP(IoSession session, REQ_READY_TO_LOCKSTEP req_ready_to_lockstep) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      dungeonParty.getUser(role.getUid()).ready = true;
      RES_READY_TO_LOCKSTEP res_ready_to_lockstep = new RES_READY_TO_LOCKSTEP();
      res_ready_to_lockstep.transId = req_ready_to_lockstep.transId;
      MessagePusher.pushMessage((IoSession)session, res_ready_to_lockstep);
   }

   @RequestMapping
   public void REQ_WAITING_TO_USERS_LOADING(IoSession session, REQ_WAITING_TO_USERS_LOADING req_waiting_to_users_loading) {
      RES_WAITING_TO_USERS_LOADING res_waiting_to_users_loading = new RES_WAITING_TO_USERS_LOADING();
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      dungeonParty.getUser(role.getUid()).waiting = true;
      res_waiting_to_users_loading.transId = req_waiting_to_users_loading.transId;
      MessagePusher.pushMessage((IoSession)session, res_waiting_to_users_loading);
   }

   @RequestMapping
   public void REQ_MULTI_PLAY_SYNC_DUNGEON(IoSession session, REQ_MULTI_PLAY_SYNC_DUNGEON req_multi_play_sync_dungeon) {
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      int playerid = -1;

      for(PT_GROUP_MEMBER member : dungeonParty.users) {
         if (member.charguid.equals(role.getUid())) {
            playerid = member.playerid;
            break;
         }
      }

      RES_MULTI_PLAY_SYNC_DUNGEON res_multi_play_sync_dungeon = new RES_MULTI_PLAY_SYNC_DUNGEON();
      res_multi_play_sync_dungeon.matchingguid = req_multi_play_sync_dungeon.matchingguid;
      res_multi_play_sync_dungeon.dungeonguid = res_multi_play_sync_dungeon.matchingguid;
      res_multi_play_sync_dungeon.index = dungeonParty.dungeonindex;
      res_multi_play_sync_dungeon.roomid = dungeonParty.roomid;
      if (playerid != -1) {
         res_multi_play_sync_dungeon.playerid = playerid;
         GroupDungeonCache.matchingGuidMap.put(1725L, res_multi_play_sync_dungeon.matchingguid);
         GroupDungeonCache.createRoom(1725L);
         GroupDungeonCache.addPlayer(1725L, role.getUid());
         GroupDungeonCache.addPlayerId(role.getUid(), playerid);
         Global.ROOM_CUR_FRAME_MAP.put(1725, 0);
         String uuid = UUID.randomUUID().toString().toLowerCase();
         String ip = "101.43.14.204";
         short port = 31001;
         String s = "1:" + ip + ":" + port + ":" + uuid;
         res_multi_play_sync_dungeon.accesstoken = Util.b64Encode(s);
         res_multi_play_sync_dungeon.createtime = TimeUtil.currS();
         res_multi_play_sync_dungeon.transId = req_multi_play_sync_dungeon.transId;
         MessagePusher.pushMessage((IoSession)session, res_multi_play_sync_dungeon);
      } else {
         this.logger.error("UNREACH==playerid==" + playerid);
      }
   }

   @RequestMapping
   public void REQ_HALF_OPEN_PARTY_JOIN(IoSession session, REQ_HALF_OPEN_PARTY_JOIN req_half_open_party_join) {
      RES_HALF_OPEN_PARTY_JOIN res_half_open_party_join = new RES_HALF_OPEN_PARTY_JOIN();
      res_half_open_party_join.transId = req_half_open_party_join.transId;
      MessagePusher.pushMessage((IoSession)session, res_half_open_party_join);
      this.partyService.halfOpenPartyJoin(session, req_half_open_party_join);
   }

   @RequestMapping
   public void REQ_TARGET_USER_PARTY_INFO(IoSession session, REQ_TARGET_USER_PARTY_INFO req_target_user_party_info) {
      long charguid = req_target_user_party_info.charguid;
      long partyguid = req_target_user_party_info.partyguid;
      DungeonParty dungeonParty = this.partyService.getDungeonParty(partyguid);
      RES_TARGET_USER_PARTY_INFO res_target_user_party_info = new RES_TARGET_USER_PARTY_INFO();
      res_target_user_party_info.ip = dungeonParty.ip;
      res_target_user_party_info.port = dungeonParty.port;
      res_target_user_party_info.area = dungeonParty.area;
      res_target_user_party_info.subtype = dungeonParty.subtype;
      res_target_user_party_info.stageindex = dungeonParty.stageindex;
      res_target_user_party_info.dungeonindex = dungeonParty.dungeonindex;
      res_target_user_party_info.minlevel = dungeonParty.minlevel;
      res_target_user_party_info.maxlevel = dungeonParty.maxlevel;
      res_target_user_party_info.channel = 1;
      res_target_user_party_info.partyname = dungeonParty.partyname;
      res_target_user_party_info.partyguid = dungeonParty.partyguid;
      res_target_user_party_info.partyleaderguid = dungeonParty.charguid;
      res_target_user_party_info.world = 1;
      res_target_user_party_info.users = dungeonParty.users;
      res_target_user_party_info.transId = req_target_user_party_info.transId;
      MessagePusher.pushMessage((IoSession)session, res_target_user_party_info);
   }

   @RequestMapping
   public void REQ_SEARCH_PARTY_LIST(IoSession session, REQ_SEARCH_PARTY_LIST reqSearchPartyList) {
      RES_SEARCH_PARTY_LIST res_search_party_list = new RES_SEARCH_PARTY_LIST();
      Integer area = reqSearchPartyList.area;
      Integer subtype = reqSearchPartyList.subtype;
      Integer stageindex = reqSearchPartyList.stageindex;
      Integer dungeonindex = reqSearchPartyList.dungeonindex;
      if (stageindex != null && dungeonindex != null) {
         res_search_party_list.type = 1;
         res_search_party_list.area = area;
         res_search_party_list.subtype = subtype;
         res_search_party_list.detail = 1;
         res_search_party_list.dungeonindex = dungeonindex;
         res_search_party_list.stageindex = stageindex;
         res_search_party_list.list = new ArrayList();

         for(DungeonParty dungeonParty : this.partyService.getDungeonPartyListByDungeonindex(area, subtype, dungeonindex)) {
            PT_RES_PARTY_LIST pt_res_party_list = new PT_RES_PARTY_LIST();
            pt_res_party_list.ip = "101.43.14.204";
            pt_res_party_list.port = 10001;
            pt_res_party_list.area = area;
            pt_res_party_list.subtype = subtype;
            pt_res_party_list.stageindex = stageindex;
            pt_res_party_list.dungeonindex = dungeonindex;
            pt_res_party_list.minlevel = dungeonParty.minlevel;
            pt_res_party_list.maxlevel = dungeonParty.maxlevel;
            pt_res_party_list.channel = 1;
            pt_res_party_list.partyname = dungeonParty.partyname;
            pt_res_party_list.partyguid = dungeonParty.partyguid;
            pt_res_party_list.world = 1;
            pt_res_party_list.users = dungeonParty.users;
            pt_res_party_list.partyping = 181;
            if (dungeonParty.publictype != null) {
               pt_res_party_list.publictype = dungeonParty.publictype;
            }

            res_search_party_list.list.add(pt_res_party_list);
         }
      } else {
         if (area == null || subtype == null) {
            this.logger.error("UNREACH==area or subtype is null");
            return;
         }

         res_search_party_list.type = reqSearchPartyList.type;
         res_search_party_list.area = area;
         res_search_party_list.subtype = subtype;
         res_search_party_list.dungeonindexcount = this.partyService.getDungeonIndexCount(area, subtype);
         res_search_party_list.stagecount = this.partyService.getStagePartyCount(area, subtype);
      }

      res_search_party_list.transId = reqSearchPartyList.transId;
      MessagePusher.pushMessage((IoSession)session, res_search_party_list);
   }

   @RequestMapping
   public void REQ_CHECK_PROHIBITED_WORD(IoSession session, REQ_CHECK_PROHIBITED_WORD req_check_prohibited_word) {
      RES_CHECK_PROHIBITED_WORD res_check_prohibited_word = new RES_CHECK_PROHIBITED_WORD();
      res_check_prohibited_word.msg = req_check_prohibited_word.msg;
      res_check_prohibited_word.transId = req_check_prohibited_word.transId;
      MessagePusher.pushMessage((IoSession)session, res_check_prohibited_word);
   }

   @RequestMapping
   public void REQ_CONTROL_GROUP_QUERYAREA(IoSession session, REQ_CONTROL_GROUP_QUERYAREA req_control_group_queryarea) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_CONTROL_GROUP_QUERYAREA res_control_group_queryarea = new RES_CONTROL_GROUP_QUERYAREA();
      res_control_group_queryarea.members = new ArrayList();
      long partyguid = req_control_group_queryarea.partyguid;
      DungeonParty dungeonParty = this.partyService.getDungeonParty(partyguid);

      for(PT_GROUP_MEMBER pt_group_member : dungeonParty.users) {
         long charguid = pt_group_member.charguid;
         Role r = (Role)DataCache.ONLINE_ROLES.get(charguid);
         PT_MEMBER_AREA_INFO pt_member_area_info = new PT_MEMBER_AREA_INFO();
         pt_member_area_info.charguid = charguid;
         pt_member_area_info.area = r.getPos().getArea();
         pt_member_area_info.town = r.getPos().getTown();
         res_control_group_queryarea.members.add(pt_member_area_info);
      }

      res_control_group_queryarea.transId = req_control_group_queryarea.transId;
      MessagePusher.pushMessage((IoSession)session, res_control_group_queryarea);
   }

   @RequestMapping
   public void REQ_PARTY_DUNGEON_CONDITION(IoSession session, REQ_PARTY_DUNGEON_CONDITION req_party_dungeon_condition) {
      RES_PARTY_DUNGEON_CONDITION res_party_dungeon_condition = new RES_PARTY_DUNGEON_CONDITION();
      res_party_dungeon_condition.type = req_party_dungeon_condition.type;
      res_party_dungeon_condition.index = req_party_dungeon_condition.index;
      res_party_dungeon_condition.transId = req_party_dungeon_condition.transId;
      MessagePusher.pushMessage((IoSession)session, res_party_dungeon_condition);
   }

   @RequestMapping
   public void REQ_SUGGEST_MOVE_PARTY(IoSession session, REQ_SUGGEST_MOVE_PARTY req_suggest_move_party) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_SUGGEST_MOVE_PARTY res_suggest_move_party = new RES_SUGGEST_MOVE_PARTY();
      res_suggest_move_party.transId = req_suggest_move_party.transId;
      MessagePusher.pushMessage((IoSession)session, res_suggest_move_party);
      DungeonParty dungeonParty = role.getDungeonParty();
      NOTIFY_SUGGEST_MOVE_PARTY notify_suggest_move_party = new NOTIFY_SUGGEST_MOVE_PARTY();

      for(PT_GROUP_MEMBER pt_group_member : dungeonParty.users) {
         long charguid = pt_group_member.charguid;
         Role r = (Role)DataCache.ONLINE_ROLES.get(charguid);
         if (r != null) {
            MessagePusher.pushMessage((Role)r, notify_suggest_move_party);
         }
      }

   }

   @RequestMapping
   public void REQ_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON(IoSession session, REQ_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON req_request_to_re_enter_accept_dungeon) {
      Role role = SessionUtils.getRoleBySession(session);
      int subtype = 0;
      if (req_request_to_re_enter_accept_dungeon.subtype != null && req_request_to_re_enter_accept_dungeon.subtype == 1) {
         subtype = 1;
      } else {
         this.logger.error("UNREACH==subtype is UNREACH=={}", req_request_to_re_enter_accept_dungeon.subtype);
      }

      RES_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON res_request_to_re_enter_accept_dungeon = new RES_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON();
      if (subtype == 1) {
         res_request_to_re_enter_accept_dungeon.subtype = 1;
      }

      res_request_to_re_enter_accept_dungeon.transId = req_request_to_re_enter_accept_dungeon.transId;
      MessagePusher.pushMessage((IoSession)session, res_request_to_re_enter_accept_dungeon);
      DungeonParty dungeonParty = this.partyService.getDungeonParty(req_request_to_re_enter_accept_dungeon.guid);
      NOTIFY_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON notify_request_to_re_enter_accept_dungeon = new NOTIFY_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON();
      if (subtype == 1) {
         notify_request_to_re_enter_accept_dungeon.subtype = 1;
      }

      notify_request_to_re_enter_accept_dungeon.guid = role.getUid();

      for(PT_GROUP_MEMBER pt_group_member : dungeonParty.users) {
         long charguid = pt_group_member.charguid;
         Role r = (Role)DataCache.ONLINE_ROLES.get(charguid);
         if (r != null) {
            IoSession session1 = SessionManager.INSTANCE.getSessionBy(r.getUid());
            notify_request_to_re_enter_accept_dungeon.transId = SessionUtils.getNotiTransId(session1);
            MessagePusher.pushMessage((IoSession)session1, notify_request_to_re_enter_accept_dungeon);
         }
      }

   }

   @RequestMapping
   public void REQ_REQUEST_TO_RE_ENTER_DUNGEON(IoSession session, REQ_REQUEST_TO_RE_ENTER_DUNGEON req_request_to_re_enter_dungeon) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_REQUEST_TO_RE_ENTER_DUNGEON res_request_to_re_enter_dungeon = new RES_REQUEST_TO_RE_ENTER_DUNGEON();
      res_request_to_re_enter_dungeon.transId = req_request_to_re_enter_dungeon.transId;
      DungeonParty dungeonParty = role.getDungeonParty();
      this.logger.error("dungeonParty: dungeonindex = {}", dungeonParty.dungeonindex);
      this.logger.error("dungeonParty: {}", dungeonParty);
      NOTIFY_REQUEST_TO_RE_ENTER_DUNGEON notify_request_to_re_enter_dungeon = new NOTIFY_REQUEST_TO_RE_ENTER_DUNGEON();
      notify_request_to_re_enter_dungeon.dungeonindex = dungeonParty.dungeonindex;
      notify_request_to_re_enter_dungeon.guid = dungeonParty.partyguid;

      for(PT_GROUP_MEMBER pt_group_member : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member.charguid);
         IoSession session1 = SessionManager.INSTANCE.getSessionBy(r.getUid());
         notify_request_to_re_enter_dungeon.transId = SessionUtils.getNotiTransId(session1);
         MessagePusher.pushMessage((IoSession)session1, notify_request_to_re_enter_dungeon);
      }

   }

   @RequestMapping
   public void REQ_RECOMMAND_GROUP(IoSession session, REQ_RECOMMAND_GROUP req_recommand_group) {
      RES_RECOMMAND_GROUP res_recommand_group = new RES_RECOMMAND_GROUP();
      res_recommand_group.flist = new ArrayList();
      res_recommand_group.transId = req_recommand_group.transId;
      MessagePusher.pushMessage((IoSession)session, res_recommand_group);
   }

   @RequestMapping
   public void REQ_CONTROL_GROUP(IoSession session, REQ_CONTROL_GROUP req_control_group) {
      Role role = SessionUtils.getRoleBySession(session);
      Role leaderRole = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.LEADER_ROLE, Role.class);
      Integer type = req_control_group.type;
      if (type == null) {
         long targetguid = req_control_group.targetguid;
         DungeonParty dungeonParty = this.partyService.getRoleParty(role.getUid());
         RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
         res_control_group.transId = req_control_group.transId;
         MessagePusher.pushMessage((IoSession)session, res_control_group);
         NOTIFY_CONTROL_GROUP notify_control_group1 = new NOTIFY_CONTROL_GROUP();
         notify_control_group1.partyguid = dungeonParty.partyguid;
         notify_control_group1.transId = SessionUtils.getNotiTransId(session);
         MessagePusher.pushMessage((IoSession)session, notify_control_group1);
      } else if (type == 13) {
         int area = -1;
         int subtype = -1;
         int stageindex = -1;
         if (req_control_group.isplayer != null && req_control_group.isplayer) {
            NOTIFY_CHANGE_STATUS notify_change_status = new NOTIFY_CHANGE_STATUS();
            notify_change_status.status = 69;
            notify_change_status.transId = req_control_group.transId;
            MessagePusher.pushMessage((IoSession)session, notify_change_status);
            NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
            notify_control_group.partyguid = 39466795435429301L;
            notify_control_group.partyleaderguid = role.getUid();
            notify_control_group.type = 18;
            notify_control_group.area = 7;
            notify_control_group.dungeonindex = 109922;
            notify_control_group.subtype = 1;
            notify_control_group.stageindex = 1;
            notify_control_group.partyname = req_control_group.partyname;
            notify_control_group.isobserver = req_control_group.observer;
            notify_control_group.isobserverchat = req_control_group.observerchat;
            notify_control_group.battleoptionlist = new ArrayList();
            PT_BATTLE_OPTION pt_battle_option = new PT_BATTLE_OPTION();
            pt_battle_option.type = ENUM_BATTLE_OPTION.T.EVADE;
            notify_control_group.battleoptionlist.add(pt_battle_option);
            notify_control_group.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_control_group);
            RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
            res_control_group.type = req_control_group.type;
            res_control_group.transId = req_control_group.transId;
            MessagePusher.pushMessage((IoSession)session, res_control_group);
            session.setAttribute(SessionProperties.PARTY_NAME, req_control_group.partyname);
            session.setAttribute(SessionProperties.LEADER_ROLE, role);
            DataCache.PVP_ROLE_MAP.putIfAbsent(role.getUid(), role);
         } else {
            area = req_control_group.area;
            subtype = req_control_group.subtype;
            stageindex = req_control_group.stageindex;
            int dungeonindex = req_control_group.dungeonindex;
            NOTIFY_CHANGE_STATUS notify_change_status = new NOTIFY_CHANGE_STATUS();
            notify_change_status.status = 5;
            notify_change_status.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_change_status);
            NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
            notify_control_group.partyguid = this.partyService.createDungeonParty(role, req_control_group);
            notify_control_group.partyleaderguid = role.getUid();
            notify_control_group.type = 18;
            notify_control_group.minlevel = req_control_group.minlevel;
            notify_control_group.maxlevel = req_control_group.maxlevel;
            notify_control_group.area = area;
            notify_control_group.dungeonindex = dungeonindex;
            notify_control_group.subtype = subtype;
            notify_control_group.stageindex = stageindex;
            notify_control_group.partyname = req_control_group.partyname;
            notify_control_group.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_control_group);
            RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
            res_control_group.type = type;
            res_control_group.transId = req_control_group.transId;
            MessagePusher.pushMessage((IoSession)session, res_control_group);
         }
      } else if (type == 18) {
         RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
         res_control_group.type = type;
         res_control_group.transId = req_control_group.transId;
         MessagePusher.pushMessage((IoSession)session, res_control_group);
         DungeonParty dungeonParty = this.partyService.getRoleParty(role.getUid());
         this.partyService.modifyPartySetting(dungeonParty, session, req_control_group);
      } else if (type == 5) {
         RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
         res_control_group.type = type;
         res_control_group.transId = req_control_group.transId;
         MessagePusher.pushMessage((IoSession)session, res_control_group);
         DungeonParty dungeonParty = role.getDungeonParty();
         this.partyService.leaveParty(dungeonParty, session, req_control_group);
         NOTIFY_CHANGE_STATUS notify_change_status = new NOTIFY_CHANGE_STATUS();
         notify_change_status.status = 1;
         notify_change_status.transId = SessionUtils.getNotiTransId(session);
         MessagePusher.pushMessage((IoSession)session, notify_change_status);
      } else if (req_control_group.type == 20 && req_control_group.partyguid != null) {
         RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
         res_control_group.type = 20;
         res_control_group.dungeonindex = req_control_group.dungeonindex;
         res_control_group.transId = req_control_group.transId;
         MessagePusher.pushMessage((IoSession)session, res_control_group);
         DungeonParty dungeonParty = this.partyService.getDungeonParty(req_control_group.partyguid);
         this.partyService.joinParty(dungeonParty, session, req_control_group);
      } else if (type == 17) {
         RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
         res_control_group.type = req_control_group.type;
         res_control_group.transId = req_control_group.transId;
         MessagePusher.pushMessage((IoSession)session, res_control_group);
         Role reqRole = (Role)DataCache.ONLINE_ROLES.get(req_control_group.charguid);
         DungeonParty dungeonParty = reqRole.getDungeonParty();
         this.partyService.afterJoinParty(dungeonParty, session, req_control_group);
      } else if (type == 7) {
         RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
         res_control_group.type = 7;
         res_control_group.transId = req_control_group.transId;
         MessagePusher.pushMessage((IoSession)session, res_control_group);
         DungeonParty dungeonParty = this.partyService.getDungeonParty(req_control_group.partyguid);
         this.partyService.type7(dungeonParty, session, req_control_group);
      } else if (role.getUid() == leaderRole.getUid()) {
         if (req_control_group.type == 7) {
            RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
            res_control_group.type = req_control_group.type;
            res_control_group.transId = req_control_group.transId;
            MessagePusher.pushMessage((IoSession)session, res_control_group);
            NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
            notify_control_group.partyguid = 39466795435429301L;
            notify_control_group.type = req_control_group.type;
            notify_control_group.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_control_group);
         } else if (req_control_group.type == 18) {
            RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
            res_control_group.type = req_control_group.type;
            res_control_group.transId = req_control_group.transId;
            MessagePusher.pushMessage((IoSession)session, res_control_group);
            NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
            notify_control_group.partyguid = 39466795435429301L;
            notify_control_group.type = req_control_group.type;
            notify_control_group.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage((IoSession)session, notify_control_group);
         } else {
            this.logger.error("REQ_CONTROL_GROUP==leaderRole==not match");
         }
      } else {
         String partyName = (String)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PARTY_NAME, String.class);
         if (req_control_group.type != 7) {
            if (req_control_group.type == 17) {
               RES_CONTROL_GROUP res_control_group = new RES_CONTROL_GROUP();
               res_control_group.type = 17;
               res_control_group.transId = req_control_group.transId;
               MessagePusher.pushMessage((IoSession)session, res_control_group);
               NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
               notify_control_group.world = 2021;
               notify_control_group.channel = 1;
               notify_control_group.partyguid = 39466795435429301L;
               notify_control_group.partyleaderguid = leaderRole.getUid();
               notify_control_group.ip = "101.43.14.204";
               notify_control_group.port = 10001;
               notify_control_group.type = 17;
               notify_control_group.area = 7;
               notify_control_group.dungeonindex = 109922;
               notify_control_group.subtype = 1;
               notify_control_group.stageindex = 1;
               notify_control_group.partyname = partyName;
               notify_control_group.users = new ArrayList();
               PT_GROUP_MEMBER pt_group_member = new PT_GROUP_MEMBER();
               pt_group_member.charguid = leaderRole.getUid();
               pt_group_member.equipscore = leaderRole.getEquipscore();
               pt_group_member.level = leaderRole.getLevel();
               pt_group_member.name = leaderRole.getName();
               pt_group_member.gguid = 13569722131808749L;
               pt_group_member.gname = "아라드형제단";
               pt_group_member.partyleader = true;
               pt_group_member.fatigue = leaderRole.getFatigue();
               pt_group_member.world = 2021;
               pt_group_member.creditscore = 351;
               pt_group_member.teamtype = ENUM_TEAM.T.RED;
               pt_group_member.customdata = new PT_CUSTOM_DATA();
               pt_group_member.customdata.type = 3;
               pt_group_member.customdata.senderguid = leaderRole.getUid();
               pt_group_member.customdata.iValue = 100;
               pt_group_member.ping = 52;
               pt_group_member.mineworld = 1;
               notify_control_group.users.add(pt_group_member);
               PT_GROUP_MEMBER pt_group_member1 = new PT_GROUP_MEMBER();
               pt_group_member1.charguid = role.getUid();
               pt_group_member1.equipscore = role.getEquipscore();
               pt_group_member1.level = role.getLevel();
               pt_group_member1.name = role.getName();
               pt_group_member1.fatigue = role.getFatigue();
               pt_group_member1.world = 2021;
               pt_group_member1.creditscore = 351;
               pt_group_member1.specialcategoryitemindex = 2000330062;
               pt_group_member1.teamtype = ENUM_TEAM.T.BLUE;
               pt_group_member1.customdata = new PT_CUSTOM_DATA();
               pt_group_member1.customdata.type = 3;
               pt_group_member1.customdata.senderguid = role.getUid();
               pt_group_member1.customdata.iValue = 100;
               pt_group_member1.mineworld = 1;
               notify_control_group.users.add(pt_group_member1);
               notify_control_group.isobserver = true;
               notify_control_group.isobserverchat = true;
               notify_control_group.battleoptionlist = new ArrayList();
               PT_BATTLE_OPTION pt_battle_option = new PT_BATTLE_OPTION();
               pt_battle_option.type = ENUM_BATTLE_OPTION.T.EVADE;
               notify_control_group.battleoptionlist.add(pt_battle_option);
               notify_control_group.transId = SessionUtils.getNotiTransId(session);
               MessagePusher.pushMessage((IoSession)session, notify_control_group);
            } else {
               this.logger.error("REQ_CONTROL_GROUP==not-leaderRole==not match");
            }
         }
      }

   }
}
