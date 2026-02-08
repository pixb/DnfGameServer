package com.dnfm.logs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.OptionHelper;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.LoggerFactory;

public class LoggerBuilder {
   private static final Map<String, Logger> container = new HashMap();
   private static final String LOG_PATH = "log/";

   public static Logger getLogger(String name) {
      Logger logger = (Logger)container.get(name);
      if (logger != null) {
         return logger;
      } else {
         synchronized(LoggerBuilder.class) {
            logger = (Logger)container.get(name);
            if (logger != null) {
               return logger;
            } else {
               logger = build(name);
               container.put(name, logger);
               return logger;
            }
         }
      }
   }

   public static void main(String[] args) {
      LoggerFunction.FIGHT.getLogger().error("heelo");
   }

   private static Logger build(String name) {
      DateFormat format = DateFormat.getDateInstance(2, Locale.SIMPLIFIED_CHINESE);
      LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
      Logger logger = context.getLogger(name);
      RollingFileAppender appender = new RollingFileAppender();
      appender.setContext(context);
      appender.setName(name);
      appender.setAppend(true);
      appender.setPrudent(false);
      TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
      String fp = OptionHelper.substVars("log/" + name + "/" + name + ".log.%d{yyyy-MM-dd}", context);
      policy.setFileNamePattern(fp);
      policy.setMaxHistory(15);
      policy.setParent(appender);
      policy.setContext(context);
      policy.start();
      PatternLayoutEncoder encoder = new PatternLayoutEncoder();
      encoder.setContext(context);
      encoder.setPattern("%d %p (%file:%line\\)- %m%n");
      encoder.start();
      PatternLayoutEncoder encoder1 = new PatternLayoutEncoder();
      encoder1.setContext(context);
      encoder1.setPattern("%d %p [%t] %m%n");
      encoder1.start();
      appender.setRollingPolicy(policy);
      appender.setEncoder(encoder);
      appender.start();
      ConsoleAppender consoleAppender = new ConsoleAppender();
      consoleAppender.setContext(context);
      consoleAppender.setEncoder(encoder1);
      consoleAppender.start();
      logger.addAppender(consoleAppender);
      logger.setLevel(Level.INFO);
      logger.addAppender(appender);
      return logger;
   }
}
