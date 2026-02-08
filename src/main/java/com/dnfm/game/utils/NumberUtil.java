package com.dnfm.game.utils;

public final class NumberUtil {
   public static boolean booleanValue(Object object) {
      return booleaneValue(object, Boolean.FALSE);
   }

   public static boolean booleaneValue(Object object, boolean defaultValue) {
      if (object == null) {
         return defaultValue;
      } else if (object.getClass() != Boolean.TYPE && object.getClass() != Boolean.class) {
         try {
            return Boolean.valueOf(object.toString());
         } catch (Exception var3) {
            return defaultValue;
         }
      } else {
         return (Boolean)object;
      }
   }

   public static byte byteValue(Object object) {
      return byteValue(object, Byte.valueOf("0"));
   }

   public static byte byteValue(Object object, byte defaultValue) {
      if (object == null) {
         return defaultValue;
      } else if (object.getClass() != Byte.TYPE && object.getClass() != Byte.class) {
         try {
            return Byte.valueOf(object.toString());
         } catch (Exception var3) {
            return defaultValue;
         }
      } else {
         return (Byte)object;
      }
   }

   public static short shortValue(Object object) {
      return shortValue(object, (short)0);
   }

   public static short shortValue(Object object, short defaultValue) {
      if (object == null) {
         return defaultValue;
      } else if (object.getClass() != Short.TYPE && object.getClass() != Short.class) {
         try {
            return Short.valueOf(object.toString());
         } catch (Exception var3) {
            return defaultValue;
         }
      } else {
         return (Short)object;
      }
   }

   public static int intValue(Object object) {
      return intValue(object, 0);
   }

   public static int intValue(Object object, int defaultValue) {
      if (object == null) {
         return defaultValue;
      } else if (object.getClass() != Integer.TYPE && object.getClass() != Integer.class) {
         try {
            return Integer.valueOf(object.toString());
         } catch (Exception var3) {
            return defaultValue;
         }
      } else {
         return (Integer)object;
      }
   }

   public static long longValue(Object object) {
      return longValue(object, 0L);
   }

   public static long longValue(Object object, long defaultValue) {
      if (object == null) {
         return defaultValue;
      } else if (object.getClass() != Long.TYPE && object.getClass() != Long.class) {
         try {
            return Long.valueOf(object.toString());
         } catch (Exception var4) {
            return defaultValue;
         }
      } else {
         return (Long)object;
      }
   }

   public static double doubleValue(Object object) {
      return doubleValue(object, (double)0.0F);
   }

   public static double doubleValue(Object object, double defaultValue) {
      if (object == null) {
         return defaultValue;
      } else if (object.getClass() != Double.TYPE && object.getClass() != Double.class) {
         try {
            return Double.valueOf(object.toString());
         } catch (Exception var4) {
            return defaultValue;
         }
      } else {
         return (double)(Long)object;
      }
   }
}
