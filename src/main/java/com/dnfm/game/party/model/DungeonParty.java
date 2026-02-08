package com.dnfm.game.party.model;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.protobuf.ENUM_TEAM;
import com.dnfm.mina.protobuf.PT_CUSTOM_DATA;
import com.dnfm.mina.protobuf.PT_GROUP_MEMBER;
import com.dnfm.mina.protobuf.PT_MAP_GUIDS;
import com.dnfm.mina.protobuf.RES_STAGE_INFO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonParty {
   public Integer publictype;
   public int startStageId = 0;
   public List<PT_MAP_GUIDS> map_guids = null;
   public List<RES_STAGE_INFO> stage_infos = null;
   public Long roomid;
   public String ip;
   public int port;
   public long charguid;
   public long partyguid;
   public int dungeonindex;
   public int area;
   public int subtype;
   public int stageindex;
   public String partyname;
   public int minlevel;
   public int maxlevel;
   public List<PT_GROUP_MEMBER> users = new ArrayList();

   public static void main(String[] args) {
      DungeonParty dungeonParty = new DungeonParty();
      PT_GROUP_MEMBER pt_group_member = new PT_GROUP_MEMBER();
      pt_group_member.equipscore = 1;
      dungeonParty.users.add(pt_group_member);
      System.out.println(dungeonParty.users.size());
      HashMap<Integer, DungeonParty> map = new HashMap();
      map.put(1, dungeonParty);
      DungeonParty dungeonParty1 = (DungeonParty)map.get(1);
      Role role = new Role();
      role.setUid(1L);
      dungeonParty1.addUser(role);
      System.out.println(((DungeonParty)map.get(1)).users.size());
   }

   public void addUser(Role role) {
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
      this.users.add(pt_group_member);
   }

   public void removeUser(Role role) {
      for(PT_GROUP_MEMBER pt_group_member : this.users) {
         if (pt_group_member.charguid == role.getUid()) {
            this.users.remove(pt_group_member);
            break;
         }
      }

   }

   public PT_GROUP_MEMBER getUser(long charguid) {
      for(PT_GROUP_MEMBER user : this.users) {
         if (user.charguid == charguid) {
            return user;
         }
      }

      return null;
   }
}
