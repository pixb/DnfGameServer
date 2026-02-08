package com.test.game.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
   public static String readText(String fileName) throws IOException {
      File file = new File(fileName);
      if (!file.exists()) {
         throw new FileNotFoundException();
      } else {
         FileInputStream in = new FileInputStream(file);
         StringBuilder result = new StringBuilder();
         BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
         Throwable var5 = null;

         try {
            String line;
            try {
               while((line = br.readLine()) != null) {
                  result.append(line).append("\n");
               }
            } catch (Throwable var14) {
               var5 = var14;
               throw var14;
            }
         } finally {
            if (br != null) {
               if (var5 != null) {
                  try {
                     br.close();
                  } catch (Throwable var13) {
                     var5.addSuppressed(var13);
                  }
               } else {
                  br.close();
               }
            }

         }

         in.close();
         return result.toString();
      }
   }

   public static List<String> readLines(String fileName) throws IOException {
      File file = new File(fileName);
      if (!file.exists()) {
         throw new FileNotFoundException();
      } else {
         List<String> result = new ArrayList();
         FileInputStream in = new FileInputStream(file);
         BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
         Throwable var5 = null;

         try {
            String line;
            try {
               while((line = br.readLine()) != null) {
                  result.add(line);
               }
            } catch (Throwable var14) {
               var5 = var14;
               throw var14;
            }
         } finally {
            if (br != null) {
               if (var5 != null) {
                  try {
                     br.close();
                  } catch (Throwable var13) {
                     var5.addSuppressed(var13);
                  }
               } else {
                  br.close();
               }
            }

         }

         in.close();
         return result;
      }
   }

   public static List<File> listFiles(String path) {
      List<File> result = new ArrayList();

      try {
         File file = new File(path);
         if (file.isFile()) {
            result.add(file);
         } else {
            File[] files = file.listFiles();
            result.addAll(Arrays.asList(files));
         }
      } catch (Exception var4) {
      }

      return result;
   }
}
