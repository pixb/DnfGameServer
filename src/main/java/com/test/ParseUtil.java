package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseUtil {
   public static String reverseHexStr(String hexStr) {
      byte[] idBytes = hexStrToBytes(hexStr);
      byte[] idBytes2 = new byte[idBytes.length];

      for(int i = 0; i < idBytes.length; ++i) {
         idBytes2[i] = idBytes[idBytes.length - i - 1];
      }

      String idStr2 = bytesToHexStr(idBytes2);
      return idStr2;
   }

   public static String bytesToHexStr(byte[] bytes) {
      StringBuilder sb = new StringBuilder();

      for(byte aByte : bytes) {
         String hex = Integer.toHexString(aByte & 255);
         if (hex.length() < 2) {
            sb.append(0);
         }

         sb.append(hex);
      }

      return sb.toString();
   }

   public static byte[] hexStrToBytes(String hexString) {
      hexString = hexString.toUpperCase().replaceAll("\\s+", "");
      int len = hexString.length();
      int index = 0;

      byte[] bytes;
      for(bytes = new byte[len / 2]; index < len; index += 2) {
         String sub = hexString.substring(index, index + 2);
         bytes[index / 2] = (byte)Integer.parseInt(sub, 16);
      }

      return bytes;
   }

   public static double bytes2Double(byte[] arr) {
      long value = 0L;

      for(int i = 0; i < 8; ++i) {
         value |= (long)(arr[i] & 255) << 8 * i;
      }

      return Double.longBitsToDouble(value);
   }

   public static List<String> readToList(String path) {
      List<String> resList = new ArrayList();

      try {
         String encoding = "UTF-8";
         File file = new File(path);
         if (file.isFile() && file.exists()) {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String lineTxt;
            while((lineTxt = bufferedReader.readLine()) != null) {
               if (lineTxt.length() != 0) {
                  lineTxt = lineTxt.replaceAll(" ", "");
                  resList.add(lineTxt);
               }
            }

            reader.close();
            bufferedReader.close();
         } else {
            System.out.println("找不到指定的文件" + path);
         }
      } catch (Exception e) {
         System.out.println("读取文件内容出错：" + e.getMessage());
      }

      return resList;
   }
}
