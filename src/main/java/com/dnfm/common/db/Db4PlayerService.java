package com.dnfm.common.db;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.common.utils.BlockingUniqueQueue;
import com.dnfm.cross.CrossServerConfig;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.model.Role;
import com.dnfm.logs.LoggerUtils;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.lang3.time.StopWatch;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Db4PlayerService {
   private static volatile Db4PlayerService instance;
   private final Logger logger = LoggerFactory.getLogger(Db4PlayerService.class);
   private final AtomicBoolean run = new AtomicBoolean(true);
   @Autowired
   CrossServerConfig crossServerConfig;
   @Autowired
   PlayerService playerService;
   @Autowired
   private Dao dao;
   private final BlockingQueue<Role> queue = new BlockingUniqueQueue<Role>();

   public static Db4PlayerService getInstance() {
      return instance;
   }

   @PostConstruct
   private void init() {
      this.logger.error("init Db4PlayerService");
      (new NamedThreadFactory("db-player-save-service")).newThread(new Worker()).start();
      instance = this;
   }

   public void add2Queue(Role entity) {
      if (entity.getExp() > 107836272) {
         entity.setExp(107836272);
      }

      if (!this.crossServerConfig.isCenterServer()) {
         this.queue.add(entity);
         this.playerService.saveToCache(entity);
      }
   }

   public int size() {
      return this.queue.size();
   }

   private void saveToDb(Role entity) {
      this.playerService.saveToCache(entity);
      entity.doBeforeSave();
      if (entity.isDelete()) {
         this.dao.delete(entity);
      } else {
         this.dao.insertOrUpdate(entity);
      }

   }

   @PreDestroy
   public void shutDown() {
      this.run.getAndSet(false);

      while(!this.queue.isEmpty()) {
         this.saveAllBeforeShutDown();
      }

      LoggerUtils.error("[db4Player] 执行全部命令后关闭");
   }

   private void saveAllBeforeShutDown() {
      label16:
      while(true) {
         if (!this.queue.isEmpty()) {
            Iterator<Role> it = this.queue.iterator();

            while(true) {
               if (!it.hasNext()) {
                  continue label16;
               }

               Role player = (Role)it.next();
               it.remove();
               this.saveToDb(player);
            }
         }

         return;
      }
   }

   private class Worker implements Runnable {
      private Worker() {
      }

      public void run() {
         while(Db4PlayerService.this.run.get()) {
            Role entity = null;

            try {
               entity = (Role)Db4PlayerService.this.queue.take();
               StopWatch stopWatch = new StopWatch();
               stopWatch.start();
               Db4PlayerService.this.saveToDb(entity);
               stopWatch.stop();
               if (stopWatch.getTime() > 500L) {
                  LoggerUtils.error("玩家数据持久化消耗毫秒：" + stopWatch.getTime());
                  LoggerUtils.error("玩家数据持久化队列长度：" + Db4PlayerService.this.queue.size());
               }
            } catch (Exception e) {
               LoggerUtils.error("", e);
               Db4PlayerService.this.add2Queue(entity);
            }
         }

      }
   }
}
