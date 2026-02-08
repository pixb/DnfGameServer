package com.dnfm.mina.udp.codec;

import com.dnfm.ParseUtil;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import net.jpountz.lz4.LZ4SafeDecompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enc {
   static Logger logger = LoggerFactory.getLogger(Enc.class);
   static final int xor1 = 10764;
   static final int rot1 = 11;
   static final int xor2 = 1836;
   static final int rot2 = 39;
   public static final int[] seeds1 = new int[]{213, 953, 551, 79, 433, 91, 139, 975, 983, 511};
   public static final int[] seeds2 = new int[]{212, 354, 103, 934, 149, 473, 491, 156, 19, 76};
   static LZ4Factory lz4Factory = LZ4Factory.fastestInstance();
   static LZ4Compressor lz4Compressor;
   static LZ4FastDecompressor lz4FastDecompressor;
   static LZ4SafeDecompressor lz4SafeDecompressor;
   static String testBStr;

   public static byte[] short2Bs(int s) {
      byte[] res = new byte[2];
      res[1] = (byte)(s >> 8 & 255);
      res[0] = (byte)(s & 255);
      return res;
   }

   public static byte[] int2Bs(int s) {
      byte[] res = new byte[4];
      res[3] = (byte)(s >> 24 & 255);
      res[2] = (byte)(s >> 16 & 255);
      res[1] = (byte)(s >> 8 & 255);
      res[0] = (byte)(s & 255);
      return res;
   }

   public static int bs2Short(byte[] bs) {
      int bs0 = bs[0];
      int bs1 = bs[1];
      if (bs0 < 0) {
         bs0 += 256;
      }

      if (bs1 < 0) {
         bs1 += 256;
      }

      return bs0 + (bs1 << 8);
   }

   public static int bs2Int(byte[] bs) {
      int bs0 = bs[0];
      int bs1 = bs[1];
      int bs2 = bs[2];
      int bs3 = bs[3];
      if (bs0 < 0) {
         bs0 += 256;
      }

      if (bs1 < 0) {
         bs1 += 256;
      }

      if (bs2 < 0) {
         bs2 += 256;
      }

      if (bs3 < 0) {
         bs3 += 256;
      }

      return bs0 + (bs1 << 8) + (bs2 << 16) + (bs3 << 24);
   }

   public static byte[] xor(byte[] in, byte xorByte) {
      byte[] res = new byte[in.length];

      for(int i = 0; i < in.length; ++i) {
         res[i] = (byte)(in[i] ^ xorByte);
      }

      return res;
   }

   public static void main(String[] args) {
      byte[] decs = decHeader1(41064, 22046720);
      String decS = ParseUtil.bytesToHexStr(decs);
      logger.error("decS=={}", decS);
   }

   static boolean isPrime(int n) {
      int i;
      for(i = 2; n % i != 0 && (double)i < Math.sqrt((double)n); ++i) {
      }

      return (double)i > Math.sqrt((double)n);
   }

   static int ror4(int value, int count) {
      return value << 32 - count | value >> count;
   }

   static int bit_rotate_left_16(int a, int b) {
      return a >> (16 - b & 31) | a << (b & 31) & '\uffff';
   }

   static int bit_rotate_left_32(int a, int b) {
      return ror4(a, -b & 31) & -1;
   }

   static int bit_rotate_right_16(int a, int b) {
      return a << (16 - b & 31) | a >> (b & 31) & '\uffff';
   }

   static int bit_rotate_right_32(int a, int b) {
      return ror4(a, b & 31) & -1;
   }

   static byte[] mergeBs(byte[] bs1, byte[] bs2) {
      byte[] res = new byte[bs1.length + bs2.length];
      System.arraycopy(bs1, 0, res, 0, bs1.length);
      System.arraycopy(bs2, 0, res, bs1.length, bs2.length);
      return res;
   }

   public static byte[] encHeader1(int seq, int packetId) {
      int a = bit_rotate_left_16(packetId ^ 10764, 11);
      int b = bit_rotate_left_32(seq ^ 10764, 11);
      byte[] aBs = short2Bs(a);
      byte[] bBs = int2Bs(b);
      return mergeBs(aBs, bBs);
   }

   public static final byte[] decHeader1(int a0, int b0) {
      int a = (bit_rotate_right_16(a0, 11) ^ 10764) & '\uffff';
      int b = (bit_rotate_right_32(b0, 11) ^ 10764) & -1;
      byte[] aBs2 = short2Bs(a);
      byte[] bBs2 = int2Bs(b);
      return mergeBs(aBs2, bBs2);
   }

   public static byte[] encHeader2(int seq, int packetId) {
      int a = bit_rotate_left_16(packetId ^ 1836, 7);
      int b = bit_rotate_left_32(seq ^ 1836, 7);
      byte[] aBs = short2Bs(a);
      byte[] bBs = int2Bs(b);
      return mergeBs(aBs, bBs);
   }

   public static byte[] decHeader2(int a0, int b0) {
      int a = (bit_rotate_right_16(a0, 7) ^ 1836) & '\uffff';
      int b = (bit_rotate_right_32(b0, 7) ^ 1836) & -1;
      byte[] aBs2 = short2Bs(a);
      byte[] bBs2 = int2Bs(b);
      return mergeBs(aBs2, bBs2);
   }

   public static byte[] shuffle1(byte[] orgBs, byte seq) {
      int v10 = Integer.MAX_VALUE;
      int v11 = Integer.MAX_VALUE;
      int v12 = Integer.MAX_VALUE;
      int v13 = Integer.MAX_VALUE;
      int v14 = Integer.MAX_VALUE;
      int index = 0;
      int v26 = 0;
      int v25 = 0;

      do {
         v25 = seeds2[index++];
         if (isPrime(v25)) {
            if (v25 <= v14) {
               v13 = v14;
               v14 = v25;
            } else if (v25 <= v13) {
               v13 = v25;
            }
         }

         if (v25 <= v12) {
            v10 = v11;
            v11 = v12;
            v12 = v25;
         } else if (v25 > v11) {
            if (v25 <= v10) {
               v10 = v25;
            }
         } else {
            v10 = v11;
            v11 = v25;
         }
      } while(index < seeds2.length);

      int xor = (v13 - 1) * (v14 - 1);
      int rot = v10 % 64;
      System.out.println("xor = " + xor + ", rot = " + rot);
      return null;
   }

   public static byte[] encrypt(byte seq, byte[] in) {
      if (in != null && in.length != 0) {
         if (seq == 0) {
            seq = 10;
         }

         byte[] out1 = lz4Compress(in, in.length);
         byte[] res = xor(out1, seq);
         return res;
      } else {
         return null;
      }
   }

   public static byte[] decrypt(byte seq, byte[] in, int orginalLen) {
      if (seq == 0) {
         seq = 10;
      }

      byte[] out1 = xor(in, seq);
      byte[] res = lz4Decompress(out1, orginalLen);
      return res;
   }

   public static byte[] decrypt2(byte seq, byte[] in) {
      if (seq == 0) {
         seq = 10;
      }

      byte[] out1 = xor(in, seq);
      byte[] res = lz4Decompress2(out1, in.length);
      return res;
   }

   public static byte[] lz4Compress(byte[] in, int decompressedLength) {
      int maxCompressedLength = lz4Compressor.maxCompressedLength(decompressedLength);
      byte[] compressedData = new byte[maxCompressedLength];
      int compressedLength = lz4Compressor.compress(in, 0, decompressedLength, compressedData, 0, maxCompressedLength);
      byte[] res = new byte[compressedLength];
      System.arraycopy(compressedData, 0, res, 0, compressedLength);
      return res;
   }

   public static byte[] lz4Decompress(byte[] in, int originalLen) {
      byte[] restoredData = new byte[originalLen];
      lz4FastDecompressor.decompress(in, 0, restoredData, 0, originalLen);
      return restoredData;
   }

   public static byte[] lz4Decompress2(byte[] in, int compressedLen) {
      byte[] restoredData = new byte[compressedLen * 20];
      int decompressedLength = lz4SafeDecompressor.decompress(in, 0, compressedLen, restoredData, 0);
      byte[] res = new byte[decompressedLength];
      System.arraycopy(restoredData, 0, res, 0, decompressedLength);
      return res;
   }

   static {
      lz4Compressor = lz4Factory.fastCompressor();
      lz4FastDecompressor = lz4Factory.fastDecompressor();
      lz4SafeDecompressor = lz4Factory.safeDecompressor();
      testBStr = "a4011b270000000068001b3b3d393a0b0afaf549393e383f3f3c3a102e3f696c3a323d393c27693c333c273e396f3c27686b3d3a273e3d393e6968686e3b3a3b3928013e243c2433243a243b3b3c20225e455f396c48737b4f737e704b487e46333e684d42326b6c437966473d4d486161736c726d387b60488108020b18394b646e7865636e2a45592a3b3a2a252a4b5a432738332a225b5b394b24383a3a323a3f243a3a3b25393d3d333d38323c693d23100152636b6567632a47432a32280b3a200e5d434c433ab80632f00f4f0a8ac74940284b58473c3e2a4c5a2a4b5943474e2a4b4f592a322a4965786f2a38323a392a4742705afb2150054b6e786f64652a225e47232a3c393a6846457a6f644d462a4f592a3924382a5c4a3e3c3e243a2a224d435e4a3f3e6f3a393d3c262a43686e6b396b6b3d3d3f6c262a3b3f323e3b3e3a32393a232a224e6b7e6f303a39253b3925383a2378053b3338243b3c32243b3f160bfa26397202820b08980b1b496263646f796f5963677a66636c636f6e920b0268063a4c3f3a384f4e3b3a333a5c620b78084158700f61655541589a0b0b";
   }
}
