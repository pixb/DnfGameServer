package com.dnfm.logs;

import org.slf4j.Logger;

public class LoggerUtils {
   public static void error(String errMsg, Exception e) {
      Logger logger = LoggerSystem.EXCEPTION.getLogger();
      logger.error(errMsg, e);
   }

   public static void error(String format, Object... arguments) {
      Logger logger = LoggerSystem.EXCEPTION.getLogger();
      logger.error(format, arguments);
   }
}
