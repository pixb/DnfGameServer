package com.dnfm.game.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
   private static final String[] strDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

   private static String byteToArrayString(byte bByte) {
      int iRet = bByte;
      if (bByte < 0) {
         iRet = bByte + 256;
      }

      int iD1 = iRet / 16;
      int iD2 = iRet % 16;
      return strDigits[iD1] + strDigits[iD2];
   }

   private static String byteToString(byte[] bByte) {
      StringBuilder sBuffer = new StringBuilder();

      for(byte b : bByte) {
         sBuffer.append(byteToArrayString(b));
      }

      return sBuffer.toString();
   }

   public static String GetMD5Code(String strObj) {
      String resultString = null;

      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         resultString = byteToString(md.digest(strObj.getBytes()));
      } catch (NoSuchAlgorithmException ex) {
         ex.printStackTrace();
      }

      return resultString;
   }
}
