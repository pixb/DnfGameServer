package com.dnfm.listener;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.logs.LoggerUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class EventDispatcher {
   private static final EventDispatcher instance = new EventDispatcher();
   private final Map<EventType, Set<Object>> observers = new HashMap();
   private final LinkedBlockingQueue<BaseGameEvent> eventQueue = new LinkedBlockingQueue();

   private EventDispatcher() {
      (new NamedThreadFactory("event-dispatch")).newThread(new EventWorker()).start();
   }

   public static EventDispatcher getInstance() {
      return instance;
   }

   public void registerEvent(EventType evtType, Object listener) {
      Set<Object> listeners = (Set)this.observers.get(evtType);
      if (listeners == null) {
         listeners = new CopyOnWriteArraySet();
         this.observers.put(evtType, listeners);
      }

      listeners.add(listener);
   }

   public void fireEvent(BaseGameEvent event) {
      if (event == null) {
         throw new NullPointerException("event cannot be null");
      } else {
         if (event.isSynchronized()) {
            this.triggerEvent(event);
         } else {
            this.eventQueue.add(event);
         }

      }
   }

   private void triggerEvent(BaseGameEvent event) {
      EventType evtType = event.getEventType();
      Set<Object> listeners = (Set)this.observers.get(evtType);
      if (listeners != null) {
         listeners.forEach((listener) -> {
            try {
               ListenerManager.INSTANCE.fireEvent(listener, event);
            } catch (Exception e) {
               LoggerUtils.error("triggerEvent failed", e);
            }

         });
      }

   }

   private class EventWorker implements Runnable {
      private EventWorker() {
      }

      public void run() {
         while(true) {
            try {
               BaseGameEvent event = (BaseGameEvent)EventDispatcher.this.eventQueue.take();
               EventDispatcher.this.triggerEvent(event);
            } catch (Exception e) {
               LoggerUtils.error("EventWorker run failed", e);
            }
         }
      }
   }
}
