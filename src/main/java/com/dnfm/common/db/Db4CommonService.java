package com.dnfm.common.db;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.common.utils.BlockingUniqueQueue;
import com.dnfm.logs.LoggerUtils;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Db4CommonService {
   private static volatile Db4CommonService instance;
   private final Logger logger = LoggerFactory.getLogger(Db4CommonService.class);
   private final AtomicBoolean run = new AtomicBoolean(true);
   @Autowired
   private Dao dao;
   private final BlockingQueue<BaseEntity> queue = new BlockingUniqueQueue<BaseEntity>();

   public static Db4CommonService getInstance() {
      return instance;
   }

   @PostConstruct
   private void init() {
      this.logger.error("init Db4CommonService");
      (new NamedThreadFactory("db-common-save-service")).newThread(new Worker()).start();
      instance = this;
   }

   public void add2Queue(BaseEntity entity) {
      if (entity != null) {
         this.queue.add(entity);
      }
   }

   private void saveToDb(BaseEntity entity) {
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

      LoggerUtils.error("[Db4Common] 执行全部命令后关闭");
   }

   private void saveAllBeforeShutDown() {
      label16:
      while(true) {
         if (!this.queue.isEmpty()) {
            Iterator<BaseEntity> it = this.queue.iterator();

            while(true) {
               if (!it.hasNext()) {
                  continue label16;
               }

               BaseEntity next = (BaseEntity)it.next();
               it.remove();
               this.saveToDb(next);
            }
         }

         return;
      }
   }

   private class Worker implements Runnable {
      private Worker() {
      }

      public void run() {
         while(Db4CommonService.this.run.get()) {
            BaseEntity entity = null;

            try {
               entity = (BaseEntity)Db4CommonService.this.queue.take();
               Db4CommonService.this.saveToDb(entity);
            } catch (Exception e) {
               LoggerUtils.error("", e);
               Db4CommonService.this.add2Queue(entity);
            }
         }

      }
   }
}
