package com.dnfm.cross.core.client;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.cross.CrossServerConfig;
import com.dnfm.logs.LoggerUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.PostConstruct;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

@Component
public class C2SSessionPoolFactory {
   private static volatile C2SSessionPoolFactory self;
   private GenericObjectPoolConfig config;
   private final ConcurrentMap<String, GenericObjectPool<CCSession>> pools = new ConcurrentHashMap();

   public static C2SSessionPoolFactory getInstance() {
      return self;
   }

   @PostConstruct
   private void init() {
      this.config = new GenericObjectPoolConfig();
      this.config.setMaxTotal(Runtime.getRuntime().availableProcessors());
      this.config.setMaxWaitMillis(5000L);
      self = this;
   }

   public CCSession borrowSession(String ip, int port) {
      String key = this.buildKey(ip, port);

      try {
         C2SSessionFactory factory = new C2SSessionFactory(ip, port);
         GenericObjectPool<CCSession> pool = (GenericObjectPool)this.pools.get(key);
         if (pool == null) {
            pool = new GenericObjectPool(factory, this.config);
            this.pools.put(key, pool);
         }

         return (CCSession)pool.borrowObject();
      } catch (Exception e) {
         LoggerUtils.error("", e);
         return null;
      }
   }

   public CCSession borrowCrossSession() {
      CrossServerConfig config = (CrossServerConfig)SpringUtils.getBean(CrossServerConfig.class);
      String ip = config.getCenterIp();
      int port = config.getCenterPort();
      return this.borrowSession(ip, port);
   }

   public void returnSession(CCSession session) {
      String key = this.buildKey(session.getIpAddr(), session.getPort());
      GenericObjectPool<CCSession> pool = (GenericObjectPool)this.pools.get(key);
      if (pool != null) {
         pool.returnObject(session);
      }

   }

   private String buildKey(String ip, int port) {
      return ip + "-" + port;
   }
}
