package com.dnfm.game.pk;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.utils.Util;
import com.dnfm.game.enter.EnterGameController;
import com.dnfm.game.party.PartyService;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.ENUM_BATTLE_OPTION;
import com.dnfm.mina.protobuf.ENUM_TEAM;
import com.dnfm.mina.protobuf.NOTIFY_CONTROL_GROUP;
import com.dnfm.mina.protobuf.PT_BATTLE_OPTION;
import com.dnfm.mina.protobuf.PT_CUSTOM_DATA;
import com.dnfm.mina.protobuf.PT_GROUP_MEMBER;
import com.dnfm.mina.protobuf.REQ_CUSTOM_GAME_ROOM_SETTING;
import com.dnfm.mina.protobuf.REQ_DREAM_MAZE_BASICINFO;
import com.dnfm.mina.protobuf.REQ_HISTORICSITE_NOTI;
import com.dnfm.mina.protobuf.REQ_LOADING_PROGRESS;
import com.dnfm.mina.protobuf.REQ_LOAD_GUILD_DONATION_INFO_2;
import com.dnfm.mina.protobuf.REQ_MULTI_PLAY_REQUEST_MATCH;
import com.dnfm.mina.protobuf.REQ_MULTI_PLAY_REQUEST_MATCH_CANCEL;
import com.dnfm.mina.protobuf.REQ_RAID_ENTRANCE_COUNT;
import com.dnfm.mina.protobuf.REQ_RETURN_TO_TOWN_AT_MULTI_PLAY;
import com.dnfm.mina.protobuf.RES_CUSTOM_GAME_ROOM_SETTING;
import com.dnfm.mina.protobuf.RES_DREAM_MAZE_BASICINFO;
import com.dnfm.mina.protobuf.RES_HISTORICSITE_NOTI;
import com.dnfm.mina.protobuf.RES_LOADING_PROGRESS;
import com.dnfm.mina.protobuf.RES_LOAD_GUILD_DONATION_INFO_2;
import com.dnfm.mina.protobuf.RES_MULTI_PLAY_REQUEST_MATCH;
import com.dnfm.mina.protobuf.RES_MULTI_PLAY_REQUEST_MATCH_CANCEL;
import com.dnfm.mina.protobuf.RES_RAID_ENTRANCE_COUNT;
import com.dnfm.mina.protobuf.RES_RETURN_TO_TOWN_AT_MULTI_PLAY;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.util.ArrayList;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PkController {
   private final Logger logger = LoggerFactory.getLogger(EnterGameController.class);
   @Autowired
   private PartyService partyService;

   public static void main(String[] args) {
      String str = "MToxMy4xMjUuMTAxLjk1OjMxMDAxOmFiMmViYzAwLTc4ZDUtNGNmYy04MTIxLWYyYmYwYWM2ZDVjZQ==";
      String res = Util.b64Decode(str);
      System.out.println(res);
      String encoded = Util.b64Encode(res);
      System.out.println(encoded);
   }

   @RequestMapping
   public void reqMultiPlayRequestMatch(IoSession session, REQ_MULTI_PLAY_REQUEST_MATCH reqMultiPlayRequestMatch) {
      RES_MULTI_PLAY_REQUEST_MATCH resMultiPlayRequestMatch = new RES_MULTI_PLAY_REQUEST_MATCH();
      resMultiPlayRequestMatch.error = 3;
      resMultiPlayRequestMatch.transId = reqMultiPlayRequestMatch.transId;
      MessagePusher.pushMessage((IoSession)session, resMultiPlayRequestMatch);
   }

   @RequestMapping
   public void reqMultiPlayRequestMatchCancel(IoSession session, REQ_MULTI_PLAY_REQUEST_MATCH_CANCEL reqMultiPlayRequestMatchCancel) {
      RES_MULTI_PLAY_REQUEST_MATCH_CANCEL resMultiPlayRequestMatchCancel = new RES_MULTI_PLAY_REQUEST_MATCH_CANCEL();
      resMultiPlayRequestMatchCancel.error = 3;
      resMultiPlayRequestMatchCancel.transId = reqMultiPlayRequestMatchCancel.transId;
      MessagePusher.pushMessage((IoSession)session, resMultiPlayRequestMatchCancel);
   }

   @RequestMapping
   public void REQ_HISTORICSITE_NOTI(IoSession session, REQ_HISTORICSITE_NOTI req_historicsite_noti) {
      RES_HISTORICSITE_NOTI res_historicsite_noti = new RES_HISTORICSITE_NOTI();
      res_historicsite_noti.transId = req_historicsite_noti.transId;
      MessagePusher.pushMessage((IoSession)session, res_historicsite_noti);
   }

   @RequestMapping
   public void REQ_LOAD_GUILD_DONATION_INFO_2(IoSession session, REQ_LOAD_GUILD_DONATION_INFO_2 req_load_guild_donation_info_2) {
      String str = "{\"recipe\": [{\"index\": 5}, {\"index\": 13}, {\"index\": 15}, {\"index\": 11}, {\"index\": 1}, {\"index\": 9}, {\"index\": 17}, {\"index\": 4}]}";
      RES_LOAD_GUILD_DONATION_INFO_2 res_load_guild_donation_info_2 = (RES_LOAD_GUILD_DONATION_INFO_2)JSON.parseObject(str, RES_LOAD_GUILD_DONATION_INFO_2.class);
      res_load_guild_donation_info_2.transId = req_load_guild_donation_info_2.transId;
      MessagePusher.pushMessage((IoSession)session, res_load_guild_donation_info_2);
   }

   @RequestMapping
   public void REQ_DREAM_MAZE_BASICINFO(IoSession session, REQ_DREAM_MAZE_BASICINFO req_dream_maze_basicinfo) {
      RES_DREAM_MAZE_BASICINFO res_dream_maze_basicinfo = new RES_DREAM_MAZE_BASICINFO();
      res_dream_maze_basicinfo.transId = req_dream_maze_basicinfo.transId;
      MessagePusher.pushMessage((IoSession)session, res_dream_maze_basicinfo);
   }

   @RequestMapping
   public void REQ_RAID_ENTRANCE_COUNT(IoSession session, REQ_RAID_ENTRANCE_COUNT req_raid_entrance_count) {
      String str = "{\"entrance\": [{\"raidindex\": 1, \"dailycharacter\": 1, \"character\": 3, \"account\": 12, \"dailyrewardcharacter\": 1, \"rewardcharacter\": 3, \"rewardaccount\": 12}, {\"raidindex\": 2, \"dailycharacter\": 1, \"character\": 1, \"account\": 4, \"dailyrewardcharacter\": 1, \"rewardcharacter\": 1, \"rewardaccount\": 4}]}";
      RES_RAID_ENTRANCE_COUNT res_raid_entrance_count = (RES_RAID_ENTRANCE_COUNT)JSON.parseObject(str, RES_RAID_ENTRANCE_COUNT.class);
      res_raid_entrance_count.transId = req_raid_entrance_count.transId;
      MessagePusher.pushMessage((IoSession)session, res_raid_entrance_count);
   }

   @RequestMapping
   public void REQ_LOADING_PROGRESS(IoSession session, REQ_LOADING_PROGRESS req_loading_progress) {
      RES_LOADING_PROGRESS res_loading_progress = new RES_LOADING_PROGRESS();

      for(Map.Entry<Long, Role> entry : DataCache.PVP_ROLE_MAP.entrySet()) {
         Role role = (Role)entry.getValue();
         res_loading_progress.matchingguid = 39466795436615747L;
         res_loading_progress.charguid = role.getUid();
         res_loading_progress.value = req_loading_progress.value;
         res_loading_progress.transId = req_loading_progress.transId;
         MessagePusher.pushMessage((Role)role, res_loading_progress);
      }

   }

   @RequestMapping
   public void REQ_RETURN_TO_TOWN_AT_MULTI_PLAY(IoSession session, REQ_RETURN_TO_TOWN_AT_MULTI_PLAY req_return_to_town_at_multi_play) {
      RES_RETURN_TO_TOWN_AT_MULTI_PLAY res_return_to_town_at_multi_play = new RES_RETURN_TO_TOWN_AT_MULTI_PLAY();
      res_return_to_town_at_multi_play.transId = req_return_to_town_at_multi_play.transId;
      MessagePusher.pushMessage((IoSession)session, res_return_to_town_at_multi_play);
   }

   @RequestMapping
   public void REQ_CUSTOM_GAME_ROOM_SETTING(IoSession session, REQ_CUSTOM_GAME_ROOM_SETTING req_custom_game_room_setting) {
      Role leaderRole = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.LEADER_ROLE, Role.class);
      Role role = SessionUtils.getRoleBySession(session);
      String partyName = (String)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PARTY_NAME, String.class);
      if (req_custom_game_room_setting.customdata.senderguid == leaderRole.getUid()) {
         RES_CUSTOM_GAME_ROOM_SETTING res_custom_game_room_setting = new RES_CUSTOM_GAME_ROOM_SETTING();
         res_custom_game_room_setting.transId = req_custom_game_room_setting.transId;
         MessagePusher.pushMessage((IoSession)session, res_custom_game_room_setting);
         Integer notifyTransId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.NOTIFY_TRANS_ID, Integer.class);
         NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
         notify_control_group.world = 2021;
         notify_control_group.channel = 1;
         notify_control_group.partyguid = 39466795435429301L;
         notify_control_group.partyleaderguid = role.getUid();
         notify_control_group.ip = "192.168.3.155";
         notify_control_group.port = 19001;
         notify_control_group.type = 17;
         notify_control_group.area = 7;
         notify_control_group.dungeonindex = 109922;
         notify_control_group.subtype = 1;
         notify_control_group.stageindex = 1;
         notify_control_group.partyname = partyName;
         notify_control_group.users = new ArrayList();
         PT_GROUP_MEMBER pt_group_member = new PT_GROUP_MEMBER();
         pt_group_member.charguid = role.getUid();
         pt_group_member.equipscore = role.getEquipscore();
         pt_group_member.level = role.getLevel();
         pt_group_member.name = role.getName();
         pt_group_member.gguid = 13569722131808749L;
         pt_group_member.gname = "아라드형제단";
         pt_group_member.partyleader = true;
         pt_group_member.fatigue = role.getFatigue();
         pt_group_member.world = 2021;
         pt_group_member.creditscore = 351;
         pt_group_member.teamtype = ENUM_TEAM.T.RED;
         pt_group_member.customdata = new PT_CUSTOM_DATA();
         pt_group_member.customdata.type = 3;
         pt_group_member.customdata.senderguid = role.getUid();
         pt_group_member.customdata.iValue = 100;
         pt_group_member.ping = 52;
         pt_group_member.mineworld = 1;
         notify_control_group.users.add(pt_group_member);
         notify_control_group.isobserver = true;
         notify_control_group.isobserverchat = true;
         notify_control_group.battleoptionlist = new ArrayList();
         PT_BATTLE_OPTION pt_battle_option = new PT_BATTLE_OPTION();
         pt_battle_option.type = ENUM_BATTLE_OPTION.T.EVADE;
         notify_control_group.battleoptionlist.add(pt_battle_option);
         notify_control_group.transId = notifyTransId;
         notifyTransId = notifyTransId + 1;
         session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, notifyTransId);
         MessagePusher.pushMessage((IoSession)session, notify_control_group);
      } else {
         RES_CUSTOM_GAME_ROOM_SETTING res_custom_game_room_setting = new RES_CUSTOM_GAME_ROOM_SETTING();
         res_custom_game_room_setting.transId = req_custom_game_room_setting.transId;
         MessagePusher.pushMessage((IoSession)session, res_custom_game_room_setting);
         Integer notifyTransId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.NOTIFY_TRANS_ID, Integer.class);
         NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
         notify_control_group.world = 2021;
         notify_control_group.channel = 1;
         notify_control_group.partyguid = 39466795435429301L;
         notify_control_group.partyleaderguid = leaderRole.getUid();
         notify_control_group.ip = "192.168.3.155";
         notify_control_group.port = 19001;
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
         PT_GROUP_MEMBER pt_group_member2 = new PT_GROUP_MEMBER();
         pt_group_member2.charguid = role.getUid();
         pt_group_member2.equipscore = role.getEquipscore();
         pt_group_member2.level = role.getLevel();
         pt_group_member2.name = role.getName();
         pt_group_member2.fatigue = role.getFatigue();
         pt_group_member2.world = 2021;
         pt_group_member2.creditscore = 351;
         pt_group_member2.specialcategoryitemindex = 2000330062;
         pt_group_member2.teamtype = ENUM_TEAM.T.BLUE;
         pt_group_member2.customdata = new PT_CUSTOM_DATA();
         pt_group_member2.customdata.type = 3;
         pt_group_member2.customdata.senderguid = role.getUid();
         pt_group_member2.customdata.iValue = 100;
         pt_group_member2.mineworld = 1;
         notify_control_group.users.add(pt_group_member2);
         notify_control_group.isobserver = true;
         notify_control_group.isobserverchat = true;
         notify_control_group.battleoptionlist = new ArrayList();
         PT_BATTLE_OPTION pt_battle_option = new PT_BATTLE_OPTION();
         pt_battle_option.type = ENUM_BATTLE_OPTION.T.EVADE;
         notify_control_group.battleoptionlist.add(pt_battle_option);
         notify_control_group.transId = notifyTransId;
         notifyTransId = notifyTransId + 1;
         session.setAttribute(SessionProperties.NOTIFY_TRANS_ID, notifyTransId);
         MessagePusher.pushMessage((IoSession)session, notify_control_group);
      }

   }
}
