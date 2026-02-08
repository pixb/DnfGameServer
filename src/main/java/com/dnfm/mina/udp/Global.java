package com.dnfm.mina.udp;

import com.dnfm.common.utils.ConcurrentHashSet;
import com.dnfm.mina.udp.model.FrameInfo;
import com.dnfm.mina.udp.model.FrameNoti;
import com.dnfm.mina.udp.model.RelayData;
import com.dnfm.mina.udp.session.SessionManager;
import com.dnfm.mina.udp.session.SessionProperties;
import com.dnfm.mina.udp.utils.Timer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Global {
   static Logger logger = LoggerFactory.getLogger(Global.class);
   public static Map<Integer, Integer> ROOM_CUR_FRAME_MAP = new ConcurrentHashMap();
   public static Map<Integer, Map<Integer, FrameInfo>> ROOM_FRAME_MAP = new ConcurrentHashMap();
   public static volatile boolean INITED = false;
   public static volatile int session_id = 58987;
   public static volatile int pk_player_id = 49;
   public static Set<IoSession> ALL_SESSIONS = new ConcurrentHashSet<IoSession>();
   private static Timer logicThread = null;
   private static long prevTimeMs = 0L;
   public static long deltaTimeMs = 0L;
   public static float deltaTime = 0.0F;

   public static int getCurFrameId(int roomId) {
      return (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
   }

   public static void putFrameAndInc(int roomId, FrameInfo frameInfo) {
      Map<Integer, FrameInfo> frameInfoMap = (Map)ROOM_FRAME_MAP.get(roomId);
      if (frameInfoMap == null) {
         frameInfoMap = new ConcurrentHashMap();
      }

      int curFrameId = (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
      frameInfoMap.put(curFrameId, frameInfo);
      ROOM_FRAME_MAP.put(roomId, frameInfoMap);
      ROOM_CUR_FRAME_MAP.put(roomId, curFrameId + 1);
   }

   public static void putFrame(int roomId, FrameInfo frameInfo) {
      Map<Integer, FrameInfo> frameInfoMap = (Map)ROOM_FRAME_MAP.get(roomId);
      frameInfoMap.put(ROOM_CUR_FRAME_MAP.get(roomId), frameInfo);
   }

   public static void putRelayData(int roomId, RelayData relayData) {
      Map<Integer, FrameInfo> frameInfoMap = (Map)ROOM_FRAME_MAP.get(roomId);
      if (frameInfoMap == null) {
         frameInfoMap = new ConcurrentHashMap();
      }

      int curFrameId = (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
      FrameInfo frameInfo = (FrameInfo)frameInfoMap.get(curFrameId);
      if (frameInfo == null) {
         frameInfo = new FrameInfo();
      }

      frameInfo.frameId = curFrameId;
      frameInfo.recvTickMs = 0;
      if (frameInfo.relayDataList == null) {
         frameInfo.relayDataList = new ArrayList();
      }

      frameInfo.relayDataList.add(relayData);
      frameInfoMap.put(curFrameId, frameInfo);
      ROOM_FRAME_MAP.put(roomId, frameInfoMap);
   }

   public static FrameInfo getFrameInfo(int roomId) {
      Map<Integer, FrameInfo> frameInfoMap = (Map)ROOM_FRAME_MAP.get(roomId);
      int curFrameId = (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
      FrameInfo frameInfo = (FrameInfo)frameInfoMap.get(curFrameId);
      return frameInfo;
   }

   public static FrameInfo getFrameAndInc(int roomId) {
      Map<Integer, FrameInfo> frameInfoMap = (Map)ROOM_FRAME_MAP.get(roomId);
      int curFrameId = (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
      FrameInfo frameInfo = (FrameInfo)frameInfoMap.get(curFrameId);
      ROOM_CUR_FRAME_MAP.put(roomId, curFrameId + 1);
      return frameInfo;
   }

   public static List<FrameInfo> getFrames(int roomId) {
      int curFrameId = (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
      Map<Integer, FrameInfo> FRAME_MAP = (Map)ROOM_FRAME_MAP.get(roomId);
      List<FrameInfo> resList = new ArrayList();
      if (curFrameId >= 2) {
         resList.add(FRAME_MAP.get(curFrameId));
         resList.add(FRAME_MAP.get(curFrameId - 1));
         resList.add(FRAME_MAP.get(curFrameId - 2));
      } else {
         resList.add(FRAME_MAP.get(curFrameId));
         resList.add(FRAME_MAP.get(curFrameId - 1));
      }

      return resList;
   }

   public static void incCurFrameId(int roomId) {
      int curFrameId = (Integer)ROOM_CUR_FRAME_MAP.get(roomId);
      ROOM_CUR_FRAME_MAP.put(roomId, curFrameId + 1);
   }

   public static void initLoop() {
      logicThread = new Timer("LogicServerThread");
      Runnable logicTask = new Runnable() {
         public void run() {
            Global.gameLoop();
         }
      };
      prevTimeMs = System.currentTimeMillis();
      logicThread.scheduleAtFixedRate(logicTask, 0L, 50L);
      logger.error("initLoop finish");
   }

   private static void gameLoop() {
      long curMs = System.currentTimeMillis();
      deltaTimeMs = curMs - prevTimeMs;
      deltaTime = (float)deltaTimeMs / 1000.0F;
      prevTimeMs = curMs;
      onLogicUpdate();
   }

   static void onLogicUpdate() {
      if (INITED) {
         for(IoSession session : ALL_SESSIONS) {
            Integer roomId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ROOM_ID, Integer.class);
            logger.error("onLogicUpdate roomId:{}", roomId);
            FrameNoti frameNoti = new FrameNoti();
            List<FrameInfo> frames = getFrames(roomId);
            frameNoti.frameInfoList = frames;
            frameNoti.currentFrameId = getCurFrameId(roomId);
            session.write(frameNoti);
            incCurFrameId(roomId);
         }

      }
   }
}
