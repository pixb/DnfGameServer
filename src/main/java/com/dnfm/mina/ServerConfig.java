package com.dnfm.mina;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
   @Value("${game.serverId}")
   private int serverId;

   public int getServerId() {
      return this.serverId;
   }

   public void setServerId(int serverId) {
      this.serverId = serverId;
   }
}
