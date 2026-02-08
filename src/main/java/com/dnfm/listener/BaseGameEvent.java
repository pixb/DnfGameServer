package com.dnfm.listener;

public abstract class BaseGameEvent {
   private final EventType eventType;
   private final long createTime = System.currentTimeMillis();

   public BaseGameEvent(EventType evtType) {
      this.eventType = evtType;
   }

   public long getCreateTime() {
      return this.createTime;
   }

   public EventType getEventType() {
      return this.eventType;
   }

   public boolean isSynchronized() {
      return true;
   }
}
