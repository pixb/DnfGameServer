package com.dnfm.common.db;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.common.utils.BlockingUniqueQueue;
import com.dnfm.cross.CrossServerConfig;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.logs.LoggerUtils;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.lang3.time.StopWatch;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Db4AccountService {
   private static volatile Db4AccountService instance;
   private final Logger logger = LoggerFactory.getLogger(Db4AccountService.class);
   private final AtomicBoolean run = new AtomicBoolean(true);
   @Autowired
   CrossServerConfig crossServerConfig;
   @Autowired
   AccountService accountService;
   @Autowired
   private Dao dao;
   private final BlockingQueue<Account> queue = new BlockingUniqueQueue<Account>();

   public static Db4AccountService getInstance() {
      return instance;
   }

   @PostConstruct
   private void init() {
      this.logger.error("init Db4AccountService");
      (new NamedThreadFactory("db-account-save-service")).newThread(new Worker()).start();
      instance = this;
      Daos.migration(this.dao, Account.class, true, false, false);
   }

   public void add2Queue(Account entity) {
      this.queue.add(entity);
      this.accountService.saveToCache(entity);
   }

   public int size() {
      return this.queue.size();
   }

   private void saveToDb(Account entity) {
      this.accountService.saveToCache(entity);
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

      LoggerUtils.error("[db4Account] 执行全部命令后关闭");
   }

   private void saveAllBeforeShutDown() {
      label16:
      while(true) {
         if (!this.queue.isEmpty()) {
            Iterator<Account> it = this.queue.iterator();

            while(true) {
               if (!it.hasNext()) {
                  continue label16;
               }

               Account account = (Account)it.next();
               it.remove();
               this.saveToDb(account);
            }
         }

         return;
      }
   }

   private class Worker implements Runnable {
      private Worker() {
      }

      public void run() {
         while(Db4AccountService.this.run.get()) {
            Account entity = null;

            try {
               entity = (Account)Db4AccountService.this.queue.take();
               StopWatch stopWatch = new StopWatch();
               stopWatch.start();
               Db4AccountService.this.saveToDb(entity);
               stopWatch.stop();
               if (stopWatch.getTime() > 500L) {
                  LoggerUtils.error("账号数据持久化消耗毫秒：" + stopWatch.getTime());
                  LoggerUtils.error("账号数据持久化队列长度：" + Db4AccountService.this.queue.size());
               }
            } catch (Exception e) {
               LoggerUtils.error("", e);
               Db4AccountService.this.add2Queue(entity);
            }
         }

      }
   }
}
