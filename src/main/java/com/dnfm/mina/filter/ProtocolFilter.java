package com.dnfm.mina.filter;

import com.dnfm.common.utils.ConcurrentHashSet;

public class ProtocolFilter {
   private static final ProtocolFilter instance = new ProtocolFilter();
   private final ConcurrentHashSet<String> bannedList = new ConcurrentHashSet<String>();

   public static ProtocolFilter getInstance() {
      return instance;
   }

   public void openProcotol(String messageId) {
      this.bannedList.add(messageId);
   }

   public void closeProcotol(String messageId) {
      this.bannedList.add(messageId);
   }

   public boolean canVisit(String messageId) {
      return !this.bannedList.contains(messageId);
   }
}
