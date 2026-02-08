package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameUtil {
   public static String lineToHump(String str) {
      str = str.toLowerCase();
      Pattern linePattern = Pattern.compile("_(\\w)");
      Matcher matcher = linePattern.matcher(str);
      StringBuffer sb = new StringBuffer();

      while(matcher.find()) {
         matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
      }

      matcher.appendTail(sb);
      return sb.toString();
   }

   public static void main(String[] args) {
      System.out.println(lineToHump("REQ_TLOG_CONTROLLER_DATA"));
   }
}
