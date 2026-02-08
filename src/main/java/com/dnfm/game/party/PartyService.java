package com.dnfm.game.party;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.game.party.model.DungeonParty;
import com.dnfm.game.party.model.DungeonPartyCache;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.ENUM_TEAM;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.NOTIFY_CHANGE_STATUS;
import com.dnfm.mina.protobuf.NOTIFY_CONTROL_GROUP;
import com.dnfm.mina.protobuf.PT_CUSTOM_DATA;
import com.dnfm.mina.protobuf.PT_DUNGEON_PARTY_COUNT;
import com.dnfm.mina.protobuf.PT_GROUP_MEMBER;
import com.dnfm.mina.protobuf.PT_STAGE_PARTY_COUNT;
import com.dnfm.mina.protobuf.REQ_CONTROL_GROUP;
import com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN;
import com.dnfm.mina.protobuf.REQ_HALF_OPEN_PARTY_JOIN;
import com.dnfm.mina.protobuf.RES_BROADCAST_GVOICE_MEMBER_LIST;
import com.dnfm.mina.protobuf.RES_HALF_OPEN_PARTY_JOIN;
import com.dnfm.mina.session.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
   private static final Logger log = LoggerFactory.getLogger(PartyService.class);
   private static AtomicLong roomId = new AtomicLong(1L);
   private final Logger logger = LoggerFactory.getLogger(PartyService.class);

   public static long getRoomId() {
      long res = roomId.getAndIncrement();
      if (res >= 65535L) {
         log.error("UNREACH==UDP房间数量超出上限制");
         return -1L;
      } else {
         return res;
      }
   }

   public List<PT_DUNGEON_PARTY_COUNT> getDungeonIndexCount(int area, int subtype) {
      List<PT_DUNGEON_PARTY_COUNT> resList = new ArrayList();
      String key = area + "_" + subtype;
      Map<Long, DungeonParty> partyMap = (Map)DungeonPartyCache.dungeonTeamMap.get(key);
      if (partyMap == null) {
         return null;
      } else {
         Map<Integer, Integer> dungeonIndexCountMap = new HashMap();

         for(DungeonParty dungeonParty : partyMap.values()) {
            int dungeonIndex = dungeonParty.dungeonindex;
            int count = (Integer)dungeonIndexCountMap.getOrDefault(dungeonIndex, 0);
            dungeonIndexCountMap.put(dungeonIndex, count + 1);
         }

         for(Map.Entry<Integer, Integer> entry : dungeonIndexCountMap.entrySet()) {
            PT_DUNGEON_PARTY_COUNT pt_dungeon_party_count = new PT_DUNGEON_PARTY_COUNT();
            pt_dungeon_party_count.dungeonindex = (Integer)entry.getKey();
            pt_dungeon_party_count.count = (Integer)entry.getValue();
            resList.add(pt_dungeon_party_count);
         }

         return resList;
      }
   }

   public List<PT_STAGE_PARTY_COUNT> getStagePartyCount(int area, int subtype) {
      List<PT_STAGE_PARTY_COUNT> resList = new ArrayList();
      String key = area + "_" + subtype;
      Map<Long, DungeonParty> partyMap = (Map)DungeonPartyCache.dungeonTeamMap.get(key);
      if (partyMap == null) {
         return null;
      } else {
         Map<Integer, Integer> stageCountMap = new HashMap();

         for(DungeonParty dungeonParty : partyMap.values()) {
            int stageindex = dungeonParty.stageindex;
            int count = (Integer)stageCountMap.getOrDefault(stageindex, 0);
            stageCountMap.put(stageindex, count + 1);
         }

         for(Map.Entry<Integer, Integer> entry : stageCountMap.entrySet()) {
            PT_STAGE_PARTY_COUNT pt_stage_party_count = new PT_STAGE_PARTY_COUNT();
            pt_stage_party_count.area = area;
            pt_stage_party_count.subtype = subtype;
            pt_stage_party_count.stageindex = (Integer)entry.getKey();
            pt_stage_party_count.count = (Integer)entry.getValue();
            resList.add(pt_stage_party_count);
         }

         return resList;
      }
   }

   public List<DungeonParty> getDungeonPartyListByDungeonindex(int area, int subtype, int dungeonindex) {
      List<DungeonParty> resList = new ArrayList();
      String key = area + "_" + subtype;
      Map<Long, DungeonParty> partyMap = (Map)DungeonPartyCache.dungeonTeamMap.get(key);
      if (partyMap == null) {
         return null;
      } else {
         for(DungeonParty party : partyMap.values()) {
            if (party.dungeonindex == dungeonindex) {
               resList.add(party);
            }
         }

         return resList;
      }
   }

   public List<DungeonParty> getDungeonPartyListByStageindex(int area, int subtype, int stageindex) {
      List<DungeonParty> resList = new ArrayList();
      String key = area + "_" + subtype;
      Map<Long, DungeonParty> partyMap = (Map)DungeonPartyCache.dungeonTeamMap.get(key);
      if (partyMap == null) {
         return null;
      } else {
         for(DungeonParty party : partyMap.values()) {
            if (party.stageindex == stageindex) {
               resList.add(party);
            }
         }

         return resList;
      }
   }

   public long createDungeonParty(Role role, REQ_CONTROL_GROUP req_control_group) {
      long charguid = role.getUid();
      DungeonParty dungeonParty = new DungeonParty();
      dungeonParty.ip = "101.43.14.204";
      dungeonParty.port = 10001;
      dungeonParty.charguid = charguid;
      dungeonParty.dungeonindex = req_control_group.dungeonindex;
      dungeonParty.area = req_control_group.area;
      dungeonParty.subtype = req_control_group.subtype;
      dungeonParty.stageindex = req_control_group.stageindex;
      dungeonParty.partyname = req_control_group.partyname;
      dungeonParty.minlevel = req_control_group.minlevel;
      dungeonParty.maxlevel = req_control_group.maxlevel;
      dungeonParty.partyguid = IdGenerator.getNextId();
      dungeonParty.users = new ArrayList();
      PT_GROUP_MEMBER pt_group_member = new PT_GROUP_MEMBER();
      pt_group_member.charguid = role.getUid();
      pt_group_member.equipscore = role.getEquipscore();
      pt_group_member.level = role.getLevel();
      pt_group_member.name = role.getName();
      pt_group_member.growtype = role.getGrowtype();
      pt_group_member.secondgrowtype = role.getSecgrowtype();
      pt_group_member.fatigue = role.getFatigue();
      pt_group_member.world = 1;
      pt_group_member.creditscore = 351;
      pt_group_member.teamtype = ENUM_TEAM.T.BLUE;
      pt_group_member.customdata = new PT_CUSTOM_DATA();
      pt_group_member.ping = 181;
      pt_group_member.mineworld = 1;
      dungeonParty.users.add(pt_group_member);
      DungeonPartyCache.addParty(dungeonParty);
      role.setDungeonParty(dungeonParty);
      role.save();
      return dungeonParty.partyguid;
   }

   public DungeonParty getRoleParty(long charguid) {
      Role role = (Role)DataCache.ONLINE_ROLES.get(charguid);
      return role == null ? null : role.getDungeonParty();
   }

   private String buildKey(int area, int subtype) {
      return area + "_" + subtype;
   }

   public DungeonParty getDungeonParty(long partyguid) {
      for(Map.Entry<String, Map<Long, DungeonParty>> entry : DungeonPartyCache.dungeonTeamMap.entrySet()) {
         Map<Long, DungeonParty> partyMap = (Map)entry.getValue();
         DungeonParty dungeonParty = (DungeonParty)partyMap.get(partyguid);
         if (dungeonParty != null) {
            return dungeonParty;
         }
      }

      return null;
   }

   public void removeDungeonParty(long partyguid) {
      for(Map.Entry<String, Map<Long, DungeonParty>> entry : DungeonPartyCache.dungeonTeamMap.entrySet()) {
         Map<Long, DungeonParty> partyMap = (Map)entry.getValue();
         DungeonParty dungeonParty = (DungeonParty)partyMap.get(partyguid);
         if (dungeonParty != null) {
            partyMap.remove(partyguid);
            return;
         }
      }

   }

   public void joinParty(DungeonParty dungeonParty, IoSession session, REQ_CONTROL_GROUP req_control_group) {
      Role role = SessionUtils.getRoleBySession(session);
      NOTIFY_CONTROL_GROUP notify_control_group1 = new NOTIFY_CONTROL_GROUP();
      notify_control_group1.partyguid = dungeonParty.partyguid;
      notify_control_group1.partyleaderguid = dungeonParty.charguid;
      notify_control_group1.type = 1;
      notify_control_group1.list = dungeonParty.users;
      notify_control_group1.targetguid = role.getUid();
      notify_control_group1.dungeonindex = req_control_group.dungeonindex;
      notify_control_group1.max = 3;
      notify_control_group1.transId = SessionUtils.getNotiTransId(session);
      MessagePusher.pushMessage((IoSession)session, notify_control_group1);
      NOTIFY_CHANGE_STATUS notify_change_status = new NOTIFY_CHANGE_STATUS();
      notify_change_status.status = 5;
      notify_change_status.transId = SessionUtils.getNotiTransId(session);
      MessagePusher.pushMessage((IoSession)session, notify_change_status);
      dungeonParty.addUser(role);

      for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
         if (r != null) {
            r.setDungeonParty(dungeonParty);
         }
      }

      NOTIFY_CONTROL_GROUP notify_control_group2 = new NOTIFY_CONTROL_GROUP();
      notify_control_group2.partyguid = dungeonParty.partyguid;
      notify_control_group2.partyleaderguid = dungeonParty.charguid;
      notify_control_group2.type = 1;
      notify_control_group2.list = new ArrayList();
      PT_GROUP_MEMBER pt_group_member = dungeonParty.getUser(role.getUid());
      notify_control_group2.list.add(pt_group_member);
      notify_control_group2.dungeonindex = req_control_group.dungeonindex;
      notify_control_group2.max = 3;

      for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
         if (r != null) {
            IoSession session1 = SessionManager.INSTANCE.getSessionBy(r.getUid());
            notify_control_group2.transId = SessionUtils.getNotiTransId(session1);
            MessagePusher.pushMessage((IoSession)session1, notify_control_group2);
         }
      }

   }

   public NOTIFY_CONTROL_GROUP getNotiCtrlGroup(IoSession session, REQ_CONTROL_GROUP req_control_group) {
      int type = req_control_group.type;
      NOTIFY_CONTROL_GROUP notify_control_group = new NOTIFY_CONTROL_GROUP();
      Role role = SessionUtils.getRoleBySession(session);
      DungeonParty dungeonParty = role.getDungeonParty();
      if (type != 13) {
         if (type == 18) {
            notify_control_group.world = 1;
            notify_control_group.channel = 1;
            notify_control_group.partyguid = dungeonParty.partyguid;
            notify_control_group.partyleaderguid = dungeonParty.charguid;
            notify_control_group.ip = dungeonParty.ip;
            notify_control_group.port = dungeonParty.port;
            notify_control_group.type = 18;
            notify_control_group.minlevel = req_control_group.minlevel;
            notify_control_group.maxlevel = req_control_group.maxlevel;
            notify_control_group.area = req_control_group.area;
            notify_control_group.dungeonindex = req_control_group.dungeonindex;
            notify_control_group.subtype = req_control_group.subtype;
            notify_control_group.stageindex = req_control_group.stageindex;
            notify_control_group.partyname = req_control_group.partyname;
            if (req_control_group.publictype != null) {
               notify_control_group.publictype = req_control_group.publictype;
            }

            notify_control_group.users = dungeonParty.users;
            DungeonPartyCache.modifyParty(req_control_group);
         } else if (type == 5) {
            notify_control_group.type = type;
            notify_control_group.charguid = role.getUid();
         } else if (type == 17) {
            notify_control_group.world = 1;
            notify_control_group.channel = 1;
            notify_control_group.partyguid = dungeonParty.partyguid;
            notify_control_group.partyleaderguid = dungeonParty.charguid;
            notify_control_group.ip = dungeonParty.ip;
            notify_control_group.port = dungeonParty.port;
            notify_control_group.type = 17;
            notify_control_group.minlevel = dungeonParty.minlevel;
            notify_control_group.maxlevel = dungeonParty.maxlevel;
            notify_control_group.area = dungeonParty.area;
            notify_control_group.dungeonindex = dungeonParty.dungeonindex;
            notify_control_group.subtype = dungeonParty.subtype;
            notify_control_group.stageindex = dungeonParty.stageindex;
            notify_control_group.partyname = dungeonParty.partyname;
            notify_control_group.users = dungeonParty.users;
         } else {
            if (type != 7) {
               this.logger.error("UNREACH==getNotiCtrlGroup type=" + type);
               return null;
            }

            notify_control_group.partyguid = dungeonParty.partyguid;
            notify_control_group.type = 7;
            notify_control_group.list = dungeonParty.users;
         }
      }

      return notify_control_group;
   }

   public void leaveParty(DungeonParty dungeonParty, IoSession session, REQ_CONTROL_GROUP req_control_group) {
      Role role = SessionUtils.getRoleBySession(session);
      if (dungeonParty != null && dungeonParty.charguid == role.getUid()) {
         role.setDungeonParty((DungeonParty)null);
         NOTIFY_CONTROL_GROUP notify = this.getNotiCtrlGroup(session, req_control_group);
         notify.transId = SessionUtils.getNotiTransId(session);
         MessagePusher.pushMessage((IoSession)session, notify);
         if (dungeonParty.users != null && dungeonParty.users.size() > 1) {
            dungeonParty.removeUser(role);
            NOTIFY_CONTROL_GROUP notify_control_group = this.getNotiCtrlGroup(session, req_control_group);

            for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
               Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
               if (r != null) {
                  r.setDungeonParty(dungeonParty);
                  IoSession session1 = SessionManager.INSTANCE.getSessionBy(r.getUid());
                  notify_control_group.transId = SessionUtils.getNotiTransId(session1);
                  MessagePusher.pushMessage((IoSession)session1, notify_control_group);
                  RES_BROADCAST_GVOICE_MEMBER_LIST res_broadcast_gvoice_member_list = new RES_BROADCAST_GVOICE_MEMBER_LIST();
                  MessagePusher.pushMessage((IoSession)session1, res_broadcast_gvoice_member_list);
                  NOTIFY_CONTROL_GROUP noti = new NOTIFY_CONTROL_GROUP();
                  noti.partyguid = dungeonParty.partyguid;
                  noti.partyleaderguid = ((PT_GROUP_MEMBER)dungeonParty.users.get(0)).charguid;
                  noti.type = 23;
                  noti.dungeonindex = dungeonParty.dungeonindex;
                  noti.transId = SessionUtils.getNotiTransId(session1);
                  MessagePusher.pushMessage((IoSession)session1, noti);
               }
            }
         } else {
            this.removeDungeonParty(dungeonParty.partyguid);
         }
      } else {
         dungeonParty.removeUser(role);
         NOTIFY_CONTROL_GROUP notify_control_group = this.getNotiCtrlGroup(session, req_control_group);

         for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
            Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
            if (r != null) {
               r.setDungeonParty(dungeonParty);
               IoSession session1 = SessionManager.INSTANCE.getSessionBy(r.getUid());
               notify_control_group.transId = SessionUtils.getNotiTransId(session1);
               MessagePusher.pushMessage((IoSession)session1, notify_control_group);
            }
         }

         role.setDungeonParty((DungeonParty)null);
      }

      NOTIFY_CONTROL_GROUP notify_control_group = this.getNotiCtrlGroup(session, req_control_group);
      notify_control_group.transId = SessionUtils.getNotiTransId(session);
      MessagePusher.pushMessage((IoSession)session, notify_control_group);
   }

   public void afterJoinParty(DungeonParty dungeonParty, IoSession session, REQ_CONTROL_GROUP req_control_group) {
      NOTIFY_CONTROL_GROUP notify_control_group = this.getNotiCtrlGroup(session, req_control_group);

      for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
         if (r != null) {
            IoSession session1 = SessionManager.INSTANCE.getSessionBy(r.getUid());
            notify_control_group.transId = SessionUtils.getNotiTransId(session1);
            MessagePusher.pushMessage((IoSession)session1, notify_control_group);
         }
      }

   }

   public void type7(DungeonParty dungeonParty, IoSession session, REQ_CONTROL_GROUP req_control_group) {
      NOTIFY_CONTROL_GROUP notify_control_group = this.getNotiCtrlGroup(session, req_control_group);
      this.broadParty(dungeonParty, notify_control_group);
   }

   public void modifyPartySetting(DungeonParty dungeonParty, IoSession session, REQ_CONTROL_GROUP req_control_group) {
      NOTIFY_CONTROL_GROUP notify_control_group = this.getNotiCtrlGroup(session, req_control_group);
      this.broadParty(dungeonParty, notify_control_group);
   }

   public void broadParty(DungeonParty dungeonParty, Message noti) {
      for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
         if (r != null) {
            IoSession session = SessionManager.INSTANCE.getSessionBy(r.getUid());
            noti.transId = SessionUtils.getNotiTransId(session);
            MessagePusher.pushMessage(session, noti);
         }
      }

   }

   public void halfOpenPartyJoin(IoSession session, REQ_HALF_OPEN_PARTY_JOIN req_half_open_party_join) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_HALF_OPEN_PARTY_JOIN res_half_open_party_join = new RES_HALF_OPEN_PARTY_JOIN();
      Role leader = (Role)DataCache.ONLINE_ROLES.get(req_half_open_party_join.leaderguid);
      DungeonParty dungeonParty = this.getDungeonParty(req_half_open_party_join.partyguid);
      res_half_open_party_join.charguid = role.getUid();
      res_half_open_party_join.job = role.getJob();
      res_half_open_party_join.growtype = role.getGrowtype();
      res_half_open_party_join.secondgrowtype = role.getSecgrowtype();
      res_half_open_party_join.equipscore = role.getEquipscore();
      res_half_open_party_join.level = role.getLevel();
      res_half_open_party_join.name = role.getName();
      res_half_open_party_join.dungeonindex = dungeonParty.dungeonindex;
      res_half_open_party_join.characterframe = role.getCharacterframe();
      MessagePusher.pushMessage((Role)leader, res_half_open_party_join);
   }

   public void enterToTown(DungeonParty dungeonParty, IoSession session, REQ_ENTER_TO_TOWN reqEnterToTown) {
      NOTIFY_CONTROL_GROUP control_group = new NOTIFY_CONTROL_GROUP();
      control_group.partyguid = dungeonParty.partyguid;
      control_group.type = 11;
      control_group.town = reqEnterToTown.town;
      control_group.area = reqEnterToTown.area;
      this.broadParty(dungeonParty, control_group);
   }

   private void updateMembersPos(DungeonParty dungeonParty, REQ_ENTER_TO_TOWN reqEnterToTown) {
      for(PT_GROUP_MEMBER pt_group_member1 : dungeonParty.users) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(pt_group_member1.charguid);
         if (r != null) {
            if (reqEnterToTown.town != null) {
               r.getPos().setTown(reqEnterToTown.town);
            }

            if (reqEnterToTown.area != null) {
               r.getPos().setArea(reqEnterToTown.area);
            }

            if (reqEnterToTown.posx != null) {
               r.getPos().setX(reqEnterToTown.posx);
            }

            if (reqEnterToTown.posy != null) {
               r.getPos().setY(reqEnterToTown.posy);
            }

            r.save();
         }
      }

   }
}
