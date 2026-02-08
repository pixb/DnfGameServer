package com.dnfm.game.player;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class GuavaCacheConfig {
   @Bean
   public CacheManager cacheManager() {
      GuavaCacheManager cacheManager = new GuavaCacheManager();
      cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(30L, TimeUnit.MINUTES).maximumSize(8000L));
      return cacheManager;
   }
}
