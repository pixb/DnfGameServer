package com.dnfm.cross.login.util;

import java.io.StringWriter;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

public class PlayerJsonUtil {
   private static final ObjectMapper MAPPER;
   private static final TypeFactory typeFactory;

   public static String object2String(Object object) {
      StringWriter writer = new StringWriter();

      try {
         MAPPER.writeValue(writer, object);
      } catch (Exception var3) {
         return null;
      }

      return writer.toString();
   }

   public static String object2String(Object object, TypeReference<?> ref) {
      StringWriter writer = new StringWriter();

      try {
         MAPPER.typedWriter(ref).writeValue(writer, object);
      } catch (Exception var4) {
         return null;
      }

      return writer.toString();
   }

   public static <T> T cloneObject(Object object) {
      String json = object2String(object);
      return (T)string2Object(json, object.getClass());
   }

   public static <T> T string2Object(String json, Class<T> clazz) {
      JavaType type = typeFactory.constructType(clazz);

      try {
         return (T)MAPPER.readValue(json, type);
      } catch (Exception var4) {
         return null;
      }
   }

   static {
      MAPPER = (new ObjectMapper()).configure(Feature.ALLOW_SINGLE_QUOTES, true);
      typeFactory = TypeFactory.defaultInstance();
   }
}
