package com.test.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PropertiesUtils {
   private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

   public static String readJsonFile(String fileName) {
      Resource resource = new ClassPathResource(fileName);
      BufferedReader bufferedReader = null;
      InputStreamReader read = null;
      InputStream io = null;

      try {
         io = resource.getInputStream();
         read = new InputStreamReader(io);
         bufferedReader = new BufferedReader(read);
         StringBuilder sb = new StringBuilder();

         int ch;
         while((ch = bufferedReader.read()) != -1) {
            sb.append((char)ch);
         }

         String var7 = sb.toString();
         return var7;
      } catch (IOException var17) {
      } finally {
         try {
            io.close();
            read.close();
            bufferedReader.close();
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

      return null;
   }
}
