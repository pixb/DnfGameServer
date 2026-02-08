package com.dnfm.logs;

import org.slf4j.Logger;

public enum LoggerFunction {
   PAY,
   SCENE,
   MONOIOR,
   MARKET,
   FIGHT,
   MAIL;

   public Logger getLogger() {
      return LoggerBuilder.getLogger(this.name().toLowerCase());
   }
}
