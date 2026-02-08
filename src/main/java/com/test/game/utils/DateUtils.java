package com.test.game.utils;

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
}
