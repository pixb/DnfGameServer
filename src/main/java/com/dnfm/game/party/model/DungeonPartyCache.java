package com.dnfm.game.party.model;

import com.dnfm.mina.protobuf.REQ_CONTROL_GROUP;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DungeonPartyCache {
   public static Map<String, Map<Long, DungeonParty>> dungeonTeamMap = new ConcurrentHashMap();

   public static void addParty(DungeonParty dungeonParty) {
      String key = dungeonParty.area + "_" + dungeonParty.subtype;
      Map<Long, DungeonParty> partyMap = (Map)dungeonTeamMap.get(key);
      if (partyMap == null) {
         partyMap = new ConcurrentHashMap();
      }

      partyMap.put(dungeonParty.partyguid, dungeonParty);
      dungeonTeamMap.put(key, partyMap);
   }

   public static void modifyParty(REQ_CONTROL_GROUP req_control_group) {
      int area = req_control_group.area;
      int subtype = req_control_group.subtype;
      long partyguid = req_control_group.partyguid;
      String key = area + "_" + subtype;
      DungeonParty dungeonParty = (DungeonParty)((Map)dungeonTeamMap.get(key)).get(partyguid);
      dungeonParty.minlevel = req_control_group.minlevel;
      dungeonParty.maxlevel = req_control_group.maxlevel;
      dungeonParty.partyname = req_control_group.partyname;
      dungeonParty.publictype = req_control_group.publictype;
   }
}
