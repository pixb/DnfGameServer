package com.dnfm.game;

import com.dnfm.game.config.Server;
import com.dnfm.mina.cache.DataCache;
import java.util.Map;
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
   private static final Logger logger = LoggerFactory.getLogger(ServerService.class);
   public ConcurrentHashMap<Long, Long> moduleId_count = new ConcurrentHashMap();
   @Value("${game.serverPort}")
   int port;
   @Value("${neice}")
   int neice;
   @Value("${ceshi}")
   int ceshi;
   @Value("${start.day}")
   String startDay;
   @Value("${game.serverIp}")
   String ip;
   @Value("${game.serverInetIp}")
   String inetIp;
   @Value("${game.gate}")
   int isGate;
   @Value("${game.merge}")
   int merge;
   ScheduledExecutorService scheduledExecutorService;
   private int open = 1;
   @Value("${login_server_url}")
   private String loginServerUrl;
   @Value("${admin_url}")
   private String adminUrl;

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

   public int getCeshi() {
      return this.ceshi;
   }

   public void setCeshi(int ceshi) {
      this.ceshi = ceshi;
   }

   public int getNeice() {
      return this.neice;
   }

   public void setNeice(int neice) {
      this.neice = neice;
   }

   public String getStartDay() {
      return this.startDay;
   }

   public void setStartDay(String startDay) {
      this.startDay = startDay;
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

   public Server getKuafuServer() {
      for(Map.Entry<Integer, Server> entry : DataCache.ID_SERVER.entrySet()) {
         if (((Server)entry.getValue()).getId() == 10000) {
            return (Server)entry.getValue();
         }
      }

      return null;
   }

   public Server getServer() {
      for(Map.Entry<Integer, Server> entry : DataCache.ID_SERVER.entrySet()) {
         String entryIp = ((Server)entry.getValue()).getIp();
         int entryPort = ((Server)entry.getValue()).getPort();
         if (entryIp.equals(this.ip) && entryPort == this.port) {
            return (Server)entry.getValue();
         }
      }

      return null;
   }

   public Server getServerdx(int linenum) {
      for(Map.Entry<Integer, Server> entry : DataCache.ID_SERVER.entrySet()) {
         if (((Server)entry.getValue()).getId() == linenum) {
            return (Server)entry.getValue();
         }
      }

      return null;
   }

   public boolean isLocal() {
      return this.getServer().getSonIp().startsWith("192.168.1.") ? true : this.getServer().getSonIp().startsWith("192.168.");
   }

   public String getAdminUrl() {
      return this.adminUrl;
   }

   public String getLoginServerUrl() {
      return this.loginServerUrl;
   }
}
