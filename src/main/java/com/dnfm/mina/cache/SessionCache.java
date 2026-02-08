package com.dnfm.mina.cache;

import com.dnfm.common.utils.ConcurrentHashSet;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface SessionCache {
   ConcurrentHashMap<Integer, ConcurrentHashSet<Integer>> MAPID_ROLEIDS = new ConcurrentHashMap();
   Map<Integer, Map<Serializable, ConcurrentHashSet<Integer>>> GROUP_MAPID_ROLEIDS = new ConcurrentHashMap();
   ConcurrentHashMap<Integer, Integer> ROLE_TEAM = new ConcurrentHashMap();
}
