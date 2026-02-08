package com.dnfm.game.map;

import com.dnfm.game.config.GameMap;
import com.dnfm.game.config.Transfer;
import com.dnfm.game.role.model.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class MapDataPool {
   public static Map<Integer, GameMap> id2GameMaps;
   public static Map<String, Integer> mapName2Id;
   public static Map<Integer, HashSet<Short>> mapId2TrasferIdSet;
   public static Map<Short, Transfer> trasferId2Trasfer;
   public static Set<Integer> polarMaps;
   public static Map<Integer, Function<Role, Serializable>> MAP_KEY_GENERATERS = new ConcurrentHashMap();
   public static Set<Integer> NOT_CHANGE_MAPS = new HashSet();
}
