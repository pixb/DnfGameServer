package com.test.game.utils;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
   private static final String ASCII = "ASCII";
   private static final String UTF8 = "UTF-8";
   private String keys;

   public String getKeys() {
      return this.keys;
   }

   public void setKeys(String keys) {
      this.keys = keys;
   }

   public DESUtil() {
   }

   public DESUtil(String keys) {
      this.keys = keys;
   }

   public byte[] desEncrypt(byte[] plainText) throws Exception {
      IvParameterSpec zeroIv = new IvParameterSpec(this.keys.getBytes(StandardCharsets.US_ASCII));
      SecretKeySpec key = new SecretKeySpec(this.keys.getBytes(StandardCharsets.US_ASCII), "DES");
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      cipher.init(1, key, zeroIv);
      return cipher.doFinal(plainText);
   }

   public byte[] desDecrypt(byte[] encryptText) throws Exception {
      IvParameterSpec zeroIv = new IvParameterSpec(this.keys.getBytes(StandardCharsets.US_ASCII));
      SecretKeySpec skey = new SecretKeySpec(this.keys.getBytes(StandardCharsets.US_ASCII), "DES");
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      cipher.init(2, skey, zeroIv);
      return cipher.doFinal(encryptText);
   }

   public String encrypt(String input) throws Exception {
      return this.parseByte2HexStr(this.desEncrypt(input.getBytes(StandardCharsets.UTF_8)));
   }

   public String decrypt(String input) throws Exception {
      byte[] result = this.parseHexStr2Byte(input);
      return new String(this.desDecrypt(result), StandardCharsets.UTF_8);
   }

   public byte[] parseHexStr2Byte(String hexStr) {
      if (hexStr.length() < 1) {
         return null;
      } else {
         byte[] result = new byte[hexStr.length() / 2];

         for(int i = 0; i < hexStr.length() / 2; ++i) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
         }

         return result;
      }
   }

   public String parseByte2HexStr(byte[] buf) {
      StringBuilder sb = new StringBuilder();

      for(byte b : buf) {
         String hex = Integer.toHexString(b & 255);
         if (hex.length() == 1) {
            hex = '0' + hex;
         }

         sb.append(hex.toUpperCase());
      }

      return sb.toString();
   }
}
