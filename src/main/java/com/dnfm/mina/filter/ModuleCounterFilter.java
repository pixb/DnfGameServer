package com.dnfm.mina.filter;

import com.dnfm.common.utils.ConcurrentHashSet;
import com.dnfm.mina.ModuleCounter;
import com.dnfm.mina.ModuleCounterSchedule;
import com.dnfm.mina.protobuf.Message;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.constraints.NotNull;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleCounterFilter extends IoFilterAdapter {
   private static final Logger log = LoggerFactory.getLogger(ModuleCounterFilter.class);
   public static final Integer G_IDLE_TIME = 3000;
   public static final String BAN_SHELL = "iptables -I INPUT -s %s -j DROP";
   public static final Set<String> G_BAN_IP = new ConcurrentHashSet<String>(100000);
   public static final AtomicInteger G_UNCHECKED_PLAYER = new AtomicInteger(0);
   public static final Integer G_MAX_UNCHECKED_CONN = 100;
   public static final Integer G_MAX_WAIT_TIME = 30000;
   public static final Integer G_MIN_PACKET_COUNT = 5;
   public static final Integer G_JUMP_HEARTBEAT_CHECK = 7;
   public static final Integer R_MIN_MODULE_COUNT = 2;
   public static final Set<Integer> R_MUST_CMD = new HashSet();

   public static void banIp(@NotNull IoSession session) {
      String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
      ModuleCounterSchedule.disconnected(session);
   }

   public static void execShell(String shell) {
      try {
         Runtime.getRuntime().exec(shell);
      } catch (Exception var2) {
      }

   }

   public static void dumpUnchecked() {
   }

   public static void dumpAll() {
   }

   private static int count(int cmd, ModuleCounter counter) {
      counter.setLastPackTime(System.currentTimeMillis());
      counter.getCount().getAndIncrement();
      Map<Integer, AtomicInteger> counterMap = counter.getCounterMap();
      if (!counterMap.containsKey(cmd)) {
         synchronized(counterMap) {
            if (!counterMap.containsKey(cmd)) {
               counterMap.put(cmd, new AtomicInteger(0));
            }
         }
      }

      return ((AtomicInteger)counterMap.get(cmd)).incrementAndGet();
   }

   public static boolean rule(ModuleCounter counter) {
      Map<Integer, AtomicInteger> counterMap = counter.getCounterMap();
      if (counterMap.size() < R_MIN_MODULE_COUNT) {
         return false;
      } else if (counter.getCount().get() < (long)G_MIN_PACKET_COUNT) {
         return false;
      } else {
         long cmdCount = R_MUST_CMD.parallelStream().filter((e) -> !counterMap.containsKey(e)).count();
         return cmdCount == 0L;
      }
   }

   public static boolean canConnect() {
      return G_UNCHECKED_PLAYER.get() < G_MAX_UNCHECKED_CONN;
   }

   public void messageReceived(IoFilter.NextFilter nextFilter, IoSession session, Object packet) {
      Message message = (Message)packet;
      nextFilter.messageReceived(session, message);
   }
}
