package com.dnfm.listener;

import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.logs.LoggerUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.ReflectionUtils;

public enum ListenerManager {
   INSTANCE;

   private final Map<String, Method> map = new HashMap();

   public void registerEventHandler(Object handler) {
      Method[] methods = handler.getClass().getDeclaredMethods();

      for(Method method : methods) {
         EventHandler mapperAnnotation = (EventHandler)method.getAnnotation(EventHandler.class);
         if (mapperAnnotation != null) {
            EventType[] eventTypes = mapperAnnotation.value();

            for(EventType eventType : eventTypes) {
               EventDispatcher.getInstance().registerEvent(eventType, handler);
               this.map.put(this.getKey(handler, eventType), method);
            }
         }
      }

   }

   public void fireEvent(Object handler, BaseGameEvent event) {
      try {
         Method method = (Method)this.map.get(this.getKey(handler, event.getEventType()));
         ReflectionUtils.makeAccessible(method);
         ReflectionUtils.invokeMethod(method, handler, new Object[]{event});
      } catch (Exception e) {
         LoggerUtils.error("监听器执行异常,handler[{}] event[{}] error{}", handler.getClass().getSimpleName(), event.getEventType(), e);
      }

   }

   private String getKey(Object handler, EventType eventType) {
      return handler.getClass().getName() + "-" + eventType.toString();
   }
}
