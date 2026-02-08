package com.dnfm.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;

public class CodecUtils {
   public static byte[] unsignedShortToByte2(int s) {
      byte[] targets = new byte[2];
      targets[0] = (byte)(s >> 8 & 255);
      targets[1] = (byte)(s & 255);
      return targets;
   }

   public static byte[] unsignedIntToByte4(long s) {
      byte[] targets = new byte[4];
      targets[0] = (byte)((int)(s >> 24 & 255L));
      targets[1] = (byte)((int)(s >> 16 & 255L));
      targets[2] = (byte)((int)(s >> 8 & 255L));
      targets[3] = (byte)((int)(s & 255L));
      return targets;
   }

   public static byte[] intToBytes(int value) {
      byte[] src = new byte[4];
      src[0] = (byte)(value & 255);
      src[1] = (byte)(value >> 8 & 255);
      src[2] = (byte)(value >> 16 & 255);
      src[3] = (byte)(value >> 24 & 255);
      return src;
   }

   public static String bytesToHex(byte[] bytes) {
      StringBuffer sb = new StringBuffer();

      for(int i = 0; i < bytes.length; ++i) {
         String hex = Integer.toHexString(bytes[i] & 255);
         if (hex.length() < 2) {
            sb.append(0);
         }

         sb.append(hex);
      }

      return sb.toString().toUpperCase();
   }

   public static String byteToHex(byte b) {
      String hex = Integer.toHexString(b & 255);
      if (hex.length() < 2) {
         hex = "0" + hex;
      }

      return hex;
   }

   public static byte[] hexToByteArray(String inHex) {
      int hexlen = inHex.length();
      byte[] result;
      if (hexlen % 2 == 1) {
         ++hexlen;
         result = new byte[hexlen / 2];
         inHex = "0" + inHex;
      } else {
         result = new byte[hexlen / 2];
      }

      int j = 0;

      for(int i = 0; i < hexlen; i += 2) {
         result[j] = hexToByte(inHex.substring(i, i + 2));
         ++j;
      }

      return result;
   }

   public static byte hexToByte(String inHex) {
      return (byte)Integer.parseInt(inHex, 16);
   }

   public static byte[] ioBufferToByte(Object message) {
      if (!(message instanceof IoBuffer)) {
         return null;
      } else {
         IoBuffer ioBuffer = (IoBuffer)message;
         byte[] b = new byte[ioBuffer.limit()];
         ioBuffer.get(b);
         return b;
      }
   }
}
