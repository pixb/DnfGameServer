package com.dnfm.mina.message;

import com.dnfm.game.utils.ClassScanner;
import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.codec.SerializerHelper;
import com.dnfm.mina.protobuf.Message;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MessageFactory {
   INSTANCE;

   private final Map<String, Class<?>> id2Clazz = new HashMap();
   private final Map<Class<?>, String> clazz2Id = new HashMap();
   private final Map<Integer, String> ignoreMap = new HashMap();

   static String tf2_(String str) {
      Pattern compile = Pattern.compile("[A-Z]");
      Matcher matcher = compile.matcher(str);
      StringBuffer sb = new StringBuffer();

      while(matcher.find()) {
         matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
      }

      matcher.appendTail(sb);
      return sb.toString().substring(1).toUpperCase();
   }

   public static void main(String[] args) {
      String scanPath = "com.dnfm.mina.protobuf";
      Set<Class<?>> messages = ClassScanner.listAllSubclasses(scanPath, Message.class);
      System.out.println(messages.size());

      for(Class<?> clazz : messages) {
         MessageMeta meta = (MessageMeta)clazz.getAnnotation(MessageMeta.class);
         if (meta == null) {
            throw new RuntimeException("messages[" + clazz.getSimpleName() + "] missed MessageMeta annotation");
         }

         if (meta.module() != 0) {
            try {
               int module = meta.module();
               int cmd = meta.cmd();
               String msgName = tf2_(clazz.getSimpleName());
               Class packetIdClazz = Class.forName("com.dnfm.mina.protobuf.packetId");
               Map<Integer, String> id2NameMap = new HashMap();
               Field[] fields = packetIdClazz.getDeclaredFields();

               for(Field f : fields) {
                  String fName = f.getName();
                  int fValue = (Integer)f.get((Object)null);
                  id2NameMap.put(fValue, fName);
               }

               String fieldName = (String)id2NameMap.get(module);
               if (fieldName != null && module != -1) {
                  System.out.println(msgName + "==" + fieldName + "_" + cmd);
               } else {
                  System.out.println(msgName + "==UnHandle_" + cmd);
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }

   }

   public void initIgnoreMap() {
      this.ignoreMap.put(10018, "Y");
      this.ignoreMap.put(10006, "Y");
      this.ignoreMap.put(13012, "Y");
      this.ignoreMap.put(14085, "Y");
      this.ignoreMap.put(10300, "Y");
      this.ignoreMap.put(10302, "Y");
      this.ignoreMap.put(10301, "Y");
      this.ignoreMap.put(10092, "Y");
      this.ignoreMap.put(10031, "Y");
      this.ignoreMap.put(10022, "Y");
      this.ignoreMap.put(17212, "Y");
      this.ignoreMap.put(17200, "Y");
      this.ignoreMap.put(14049, "Y");
      this.ignoreMap.put(14015, "Y");
      this.ignoreMap.put(14016, "Y");
      this.ignoreMap.put(14019, "Y");
      this.ignoreMap.put(14029, "Y");
      this.ignoreMap.put(14030, "Y");
      this.ignoreMap.put(14061, "Y");
      this.ignoreMap.put(14001, "Y");
      this.ignoreMap.put(14002, "Y");
      this.ignoreMap.put(14072, "Y");
      this.ignoreMap.put(14073, "Y");
      this.ignoreMap.put(14097, "Y");
      this.ignoreMap.put(14096, "Y");
      this.ignoreMap.put(14092, "Y");
      this.ignoreMap.put(14093, "Y");
      this.ignoreMap.put(14094, "Y");
      this.ignoreMap.put(14107, "Y");
      this.ignoreMap.put(14012, "Y");
      this.ignoreMap.put(14013, "Y");
      this.ignoreMap.put(14200, "Y");
      this.ignoreMap.put(14000, "Y");
      this.ignoreMap.put(11101, "Y");
      this.ignoreMap.put(10800, "Y");
      this.ignoreMap.put(10250, "Y");
      this.ignoreMap.put(10150, "Y");
      this.ignoreMap.put(14105, "Y");
      this.ignoreMap.put(14100, "Y");
      this.ignoreMap.put(14101, "Y");
      this.ignoreMap.put(14114, "Y");
      this.ignoreMap.put(11039, "Y");
      this.ignoreMap.put(30001, "Y");
      this.ignoreMap.put(15001, "Y");
      this.ignoreMap.put(10260, "Y");
      this.ignoreMap.put(10650, "Y");
      this.ignoreMap.put(29015, "Y");
      this.ignoreMap.put(10051, "Y");
      this.ignoreMap.put(14080, "Y");
      this.ignoreMap.put(10115, "Y");
      this.ignoreMap.put(10131, "Y");
      this.ignoreMap.put(10210, "Y");
      this.ignoreMap.put(15715, "Y");
      this.ignoreMap.put(11080, "Y");
      this.ignoreMap.put(17300, "Y");
      this.ignoreMap.put(17206, "Y");
      this.ignoreMap.put(11046, "Y");
      this.ignoreMap.put(28102, "Y");
      this.ignoreMap.put(28110, "Y");
      this.ignoreMap.put(28132, "Y");
      this.ignoreMap.put(28140, "Y");
      this.ignoreMap.put(10124, "Y");
      this.ignoreMap.put(10200, "Y");
      this.ignoreMap.put(15305, "Y");
      this.ignoreMap.put(11015, "Y");
      this.ignoreMap.put(10700, "Y");
      this.ignoreMap.put(10704, "Y");
      this.ignoreMap.put(11107, "Y");
      this.ignoreMap.put(14081, "Y");
      this.ignoreMap.put(16000, "Y");
   }

   public boolean inIgnore(int msgId) {
      return this.ignoreMap.get(msgId) != null;
   }

   void preCompileProtobuf() {
      for(Map.Entry<String, Class<?>> entry : this.id2Clazz.entrySet()) {
         try {
            Class msgClass = (Class)entry.getValue();
            Message msg = (Message)msgClass.newInstance();
            SerializerHelper.protobufEncode(msg);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

   }

   public void initMessagePool(String scanPath) {
      this.initIgnoreMap();

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
