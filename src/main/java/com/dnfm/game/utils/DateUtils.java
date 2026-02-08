package com.dnfm.game.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
   public static String dayForWeek(Date tmpDate) {
      Calendar cal = Calendar.getInstance();
      String[] weekDays = new String[]{"7", "1", "2", "3", "4", "5", "6"};

      try {
         cal.setTime(tmpDate);
      } catch (Exception var4) {
      }

      int w = cal.get(7) - 1;
      if (w < 0) {
         w = 0;
      }

      return weekDays[w];
   }

   public static String formatDate(Date date) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
      return simpleDateFormat.format(date);
   }

   public static Date getAfterDayEndDate(int days) {
      Calendar canlendar = Calendar.getInstance();
      canlendar.add(5, days);
      canlendar.set(11, 23);
      canlendar.set(12, 59);
      canlendar.set(13, 59);
      Date date = canlendar.getTime();
      return date;
   }

   public static int getDaysBetween(long beginTime) {
      Calendar aCalendar = Calendar.getInstance();
      aCalendar.set(11, 0);
      aCalendar.set(12, 0);
      aCalendar.set(13, 0);
      aCalendar.set(14, 0);
      Calendar bCalendar = Calendar.getInstance();
      bCalendar.setTime(new Date(beginTime));
      bCalendar.set(11, 0);
      bCalendar.set(12, 0);
      bCalendar.set(13, 0);
      bCalendar.set(14, 0);
      int days = 0;

      while(aCalendar.before(bCalendar)) {
         --days;
         aCalendar.add(6, 1);
      }

      while(bCalendar.before(aCalendar)) {
         ++days;
         bCalendar.add(6, 1);
      }

      return days;
   }

   public static Date getToDaySixDate() {
      Calendar canlendar = Calendar.getInstance();
      canlendar.set(11, 6);
      canlendar.set(12, 0);
      canlendar.set(13, 0);
      Date date = canlendar.getTime();
      return date;
   }

   public static Date getYesterdaySixDate() {
      Calendar canlendar = Calendar.getInstance();
      canlendar.add(5, -1);
      canlendar.set(11, 6);
      canlendar.set(12, 0);
      canlendar.set(13, 0);
      Date date = canlendar.getTime();
      return date;
   }

   public static Date getToDayBeginDate() {
      Calendar canlendar = Calendar.getInstance();
      canlendar.set(11, 0);
      canlendar.set(12, 0);
      canlendar.set(13, 0);
      Date date = canlendar.getTime();
      return date;
   }

   public static void main(String[] args) {
      System.out.println(getAfterDayEndDate(30).getTime());
      System.out.println(getDaysBetween(1681658377000L));
      System.out.println(getToDayBeginDate().getTime() / 1000L);
      long toDaySixTime = getToDaySixDate().getTime() / 1000L;
      long YesterDaySixDate = getYesterdaySixDate().getTime() / 1000L;
      System.out.println(YesterDaySixDate);
      long lastLogoutTime = 1680020034L;
      if (lastLogoutTime < YesterDaySixDate || TimeUtil.currS() > toDaySixTime && toDaySixTime > lastLogoutTime) {
         System.out.println("重置");
      }

   }
}
