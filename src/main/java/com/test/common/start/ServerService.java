package com.test.common.start;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource({"classpath:game.properties"})
public class ServerService {
   private static Logger logger = LoggerFactory.getLogger(ServerService.class);
   @Value("${game.serverPort}")
   int port;
   @Value("${game.serverIp}")
   String ip;
   @Value("${game.serverInetIp}")
   String inetIp;
   @Value("${game.gate}")
   int isGate;
   @Value("${game.merge}")
   int merge;
   private int open = 1;
   @Value("${login_server_url}")
   private String loginServerUrl;
   @Value("${admin_url}")
   private String adminUrl;
   public ConcurrentHashMap<Long, Long> moduleId_count = new ConcurrentHashMap();
   ScheduledExecutorService scheduledExecutorService;

   public void init() {
      ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(12);
      this.setScheduledExecutorService(scheduledExecutorService);
   }

   public int getMerge() {
      return this.merge;
   }

   public void setMerge(int merge) {
      this.merge = merge;
   }

   public ConcurrentHashMap<Long, Long> getModuleId_count() {
      return this.moduleId_count;
   }

   public void setModuleId_count(ConcurrentHashMap<Long, Long> moduleId_count) {
      this.moduleId_count = moduleId_count;
   }

   public ScheduledExecutorService getScheduledExecutorService() {
      return this.scheduledExecutorService;
   }

   public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
      this.scheduledExecutorService = scheduledExecutorService;
   }

   public boolean isOpen() {
      return this.open == 1;
   }

   public void setOpen(int open) {
      this.open = open;
   }

   public boolean isGate() {
      return this.isGate == 1;
   }

   public String getIpPort() {
      return this.ip + this.port;
   }

   public String getIp() {
      return this.ip;
   }

   public int getPort() {
      return this.port;
   }

   public String getInetIp() {
      return this.inetIp;
   }

   public String getAdminUrl() {
      return this.adminUrl;
   }

   public String getLoginServerUrl() {
      return this.loginServerUrl;
   }
}
