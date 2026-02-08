package com.test.game.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {
   public static String compress(String str) {
      if (str != null && str.length() != 0) {
         ByteArrayOutputStream out = null;
         GZIPOutputStream gzip = null;
         String compress = "";

         try {
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            byte[] compressed = out.toByteArray();
            compress = Base64.getEncoder().encodeToString(compressed);
         } catch (IOException var13) {
         } finally {
            if (null != out) {
               try {
                  out.close();
               } catch (IOException var12) {
               }
            }

         }

         return compress;
      } else {
         return str;
      }
   }

   public static String uncompress(String str) {
      if (str != null && str.length() != 0) {
         ByteArrayOutputStream out = null;
         ByteArrayInputStream in = null;
         GZIPInputStream gzip = null;
         String uncompress = "";

         try {
            out = new ByteArrayOutputStream();
            byte[] compressed = Base64.getDecoder().decode(str);
            in = new ByteArrayInputStream(compressed);
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;

            while((offset = gzip.read(buffer)) != -1) {
               out.write(buffer, 0, offset);
            }

            uncompress = out.toString();
         } catch (IOException var24) {
         } finally {
            if (null != gzip) {
               try {
                  gzip.close();
               } catch (IOException var23) {
               }
            }

            if (null != in) {
               try {
                  in.close();
               } catch (IOException var22) {
               }
            }

            if (null != out) {
               try {
                  out.close();
               } catch (IOException var21) {
               }
            }

         }

         return uncompress;
      } else {
         return str;
      }
   }
}
