package com.dnfm.cross;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource({"classpath:cross.properties"})
public class CrossServerConfig {
   @Value("${cross.center.ip}")
   private String centerIp;
   @Value("${cross.center.rpcPort}")
   private int centerPort;
   @Value("${cross.center.gamePort}")
   private int gamePort;
   @Value("${cross.center}")
   private int isCenter;

   public boolean isCenterServer() {
      return this.isCenter > 0;
   }

   public String getCenterIp() {
      return this.centerIp;
   }

   public int getCenterPort() {
      return this.centerPort;
   }

   public int getGamePort() {
      return this.gamePort;
   }
}
