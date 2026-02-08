package com.test.mina.udp;

import com.test.game.utils.ClassScanner;
import com.test.mina.annotation.MessageMeta;
import java.util.HashMap;
import java.util.Map;

public enum UdpMessageFactory {
   INSTANCE;

   private Map<String, Class<?>> id2Clazz = new HashMap();
   private Map<Class<?>, String> clazz2Id = new HashMap();

   public void initMessagePool(String scanPath) {
      for(Class<?> clazz : ClassScanner.listAllSubclasses(scanPath, Message.class)) {
         MessageMeta meta = (MessageMeta)clazz.getAnnotation(MessageMeta.class);
         if (meta == null) {
            throw new RuntimeException("messages[" + clazz.getSimpleName() + "] missed MessageMeta annotation");
         }

         if (meta.module() != 0) {
            String key = this.buildKey(meta.module(), meta.cmd());
            if (this.id2Clazz.containsKey(key) && !key.contains("-1")) {
               System.out.println("消息号重复：" + clazz.getSimpleName());
               throw new RuntimeException("message meta [" + key + "] duplicate！！");
            }

            this.id2Clazz.put(key, clazz);
            this.clazz2Id.put(clazz, key);
         }
      }

      System.out.println("id2Clazz.size==" + this.id2Clazz.size());
      System.out.println("clazz2Id.size==" + this.clazz2Id.size());
   }

   public Class<?> getMessage(int module, int cmd) {
      return (Class)this.id2Clazz.get(this.buildKey(module, cmd));
   }

   public Class<?> getMessage(int id) {
      short module = (short)(id / 1000);
      short cmd = (short)(id % 1000);
      return (Class)this.id2Clazz.get(this.buildKey(module, cmd));
   }

   public String getMessageId(Class<?> clazz) {
      return (String)this.clazz2Id.get(clazz);
   }

   private String buildKey(int module, int cmd) {
      return module + "_" + cmd;
   }
}
