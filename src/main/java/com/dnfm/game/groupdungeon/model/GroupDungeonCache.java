package com.dnfm.game.groupdungeon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupDungeonCache {
   private static final Map<Long, List<Long>> ALL_UDP_ROOMS = new ConcurrentHashMap();
   private static final Map<Long, Integer> playerIdMap = new ConcurrentHashMap();
   public static final Map<Long, Long> matchingGuidMap = new ConcurrentHashMap();

   public static void addPlayerId(long charGuid, int playerId) {
      playerIdMap.put(charGuid, playerId);
   }

   public static int getPlayerId(long charGuid) {
      return (Integer)playerIdMap.get(charGuid);
   }

   public static void createRoom(long roomId) {
      List<Long> charguidList = (List)ALL_UDP_ROOMS.get(roomId);
      if (charguidList == null) {
         List<Long> var3 = new ArrayList();
         ALL_UDP_ROOMS.put(roomId, var3);
      }

   }

   public static void removeRoom(long roomId) {
      ALL_UDP_ROOMS.remove(roomId);
   }

   public static void addPlayer(long roomId, long charguid) {
      List<Long> charguidList = (List)ALL_UDP_ROOMS.get(roomId);
      if (charguidList == null) {
         charguidList = new ArrayList();
      }

      charguidList.add(charguid);
      ALL_UDP_ROOMS.put(roomId, charguidList);
   }

   public static void removePlayer(long roomId, long charguid) {
      List<Long> charguidList = (List)ALL_UDP_ROOMS.get(roomId);
      if (charguidList != null) {
         charguidList.remove(charguid);
         ALL_UDP_ROOMS.put(roomId, charguidList);
      }
   }

   public static List<Long> getCharguidList(long roomId) {
      return (List)ALL_UDP_ROOMS.get(roomId);
   }
}
