package com.dnfm.mina.filter;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.utils.NumberUtil;
import com.dnfm.mina.FireWallConfig;
import com.dnfm.mina.FloodRecord;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloodFilter extends IoFilterAdapter {
   private static final Logger logger = LoggerFactory.getLogger(FloodFilter.class);

   private static FloodRecord getFloodRecordBy(IoSession session) {
      SessionManager sessionMgr = SessionManager.INSTANCE;
      FloodRecord record = (FloodRecord)sessionMgr.getSessionAttr(session, SessionProperties.FLOOD, FloodRecord.class);
      if (record == null) {
         record = new FloodRecord();
         session.setAttribute(SessionProperties.FLOOD, record);
      }

      return record;
   }

   private static void tryToResetFloodTimes(long now, FloodRecord record) {
      FireWallConfig config = (FireWallConfig)SpringUtils.getBean(FireWallConfig.class);
      long diffTime = now - record.getLastFloodTime();
      if (NumberUtil.intValue(diffTime / 1L) > config.getFloodWindowSeconds()) {
         record.setFloodTimes(0);
      }

   }

   private static boolean isMessageTooFast(int packageSum) {
      FireWallConfig config = (FireWallConfig)SpringUtils.getBean(FireWallConfig.class);
      return packageSum >= config.getMaxPackagePerSecond();
   }

   private static boolean isMeetFloodStandard(int floodTimes) {
      FireWallConfig config = (FireWallConfig)SpringUtils.getBean(FireWallConfig.class);
      return floodTimes > config.getMaxFloodTimes();
   }

   public void messageReceived(IoFilter.NextFilter nextFilter, IoSession session, Object message) throws Exception {
      FloodRecord record = getFloodRecordBy(session);
      long now = System.currentTimeMillis();
      long currSecond = (long)NumberUtil.intValue(now / 1L);
      long lastTime = record.getLastReceivedTime();
      int lastSecond = NumberUtil.intValue(lastTime / 1L);
      tryToResetFloodTimes(now, record);
      if (currSecond == (long)lastSecond) {
         int packageSum = record.addSecondReceivedPackage();
         if (isMessageTooFast(packageSum)) {
            int floodTimes = record.addFloodTimes();
            if (isMeetFloodStandard(floodTimes)) {
            }

            record.setLastFloodTime(now);
            record.setReceivedPacksLastSecond(0);
         }
      } else {
         record.setReceivedPacksLastSecond(0);
      }

      record.setLastReceivedTime(now);
      nextFilter.messageReceived(session, message);
   }
}
