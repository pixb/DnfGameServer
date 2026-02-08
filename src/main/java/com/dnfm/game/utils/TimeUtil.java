package com.dnfm.game.utils;

import com.dnfm.common.thread.IdGenerator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
   public static final int WEEK_S = 604800;
   public static final long ONE_MILLSECOND = 1L;
   public static final long ONE_SECOND = 1L;
   public static final long ONE_MINUTE = 60L;
   public static final long ONE_HOUR = 3600L;
   public static final long ONE_DAY = 86400L;
   public static final int HOURS_OF_DAY = 24;
   public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

   public static int getDaysOfMonth() {
      Calendar cl = Calendar.getInstance();
      int dayCount = cl.getActualMaximum(5);
      return dayCount;
   }

   public static String formatDate(Date date) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      return simpleDateFormat.format(date);
   }

   public static String formatDate(Date date, String formatPattern) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern);
      return simpleDateFormat.format(date);
   }

   public static String getDay(Date date) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
      return simpleDateFormat.format(date);
   }

   public static Date parse(String datestr) {
      return parse(datestr, "yyyy-MM-dd");
   }

   public static Date parseTime(String datestr) {
      return parse(datestr, "HH:mm:ss");
   }

   public static Date parse(String datestr, String datePattern) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

      try {
         return simpleDateFormat.parse(datestr);
      } catch (Exception var4) {
         return null;
      }
   }

   public static String getWeekOfDate(Date dt) {
      String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
      Calendar cal = Calendar.getInstance();
      cal.setTime(dt);
      int w = cal.get(7) - 1;
      if (w < 0) {
         w = 0;
      }

      return weekDays[w];
   }

   public static final String localTime() {
      Date date = new Date();
      StringBuilder sb = new StringBuilder();
      sb.append(String.format(Locale.US, "%tY", date));
      sb.append("-");
      sb.append(String.format(Locale.US, "%tb", date));
      sb.append("-");
      sb.append(String.format(Locale.US, "%td", date));
      sb.append(" ");
      sb.append(String.format(Locale.US, "%tH", date));
      sb.append(":");
      sb.append(String.format(Locale.US, "%tM", date));
      sb.append(":");
      sb.append(String.format(Locale.US, "%tS", date));
      return sb.toString();
   }

   public static final long currS() {
      return System.currentTimeMillis() / 1000L;
   }

   public static final long currMs() {
      return System.currentTimeMillis();
   }

   public static final void sleepMs(long ms) {
      try {
         Thread.sleep(ms);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

   }

   public static void main(String[] args) {
      long id = IdGenerator.getNextId();
      System.out.println(id);
   }
}
