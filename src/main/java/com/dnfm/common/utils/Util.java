package com.dnfm.common.utils;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Util {
   static Random random = new Random();
   static Base64.Encoder encoder = Base64.getEncoder();
   static Base64.Decoder decoder = Base64.getDecoder();

   public static String b64Encode(String str) {
      return encoder.encodeToString(str.getBytes());
   }

   public static String b64Decode(String str) {
      return new String(decoder.decode(str));
   }

   public static boolean isEmpty(List list) {
      return list == null || list.size() == 0;
   }

   public static boolean isEmpty(Map map) {
      return map == null || map.size() == 0;
   }

   public static boolean isEmpty(String str) {
      if (str == null) {
         return true;
      } else {
         return str.trim().length() == 0;
      }
   }

   public static int randInt(int begin, int end) {
      return random.nextInt(end - begin) + begin;
   }

   public static int randIndex(int size) {
      return random.nextInt(size);
   }

   public static void main(String[] args) {
      String s = "1:54.180.225.29:31001:4d1e4799-9e26-4b58-8bb3-7c8523746ab5";
      System.out.println(b64Encode(s));
   }
}
