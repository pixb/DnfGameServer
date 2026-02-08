package com.dnfm.cross.core;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.cross.core.callback.CallbackTask;
import com.dnfm.cross.core.client.C2SSessionPoolFactory;
import com.dnfm.cross.core.client.CCSession;
import com.dnfm.mina.protobuf.Message;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrossTransportManager {
   private final int defaultCoreSum = Runtime.getRuntime().availableProcessors();
   private ExecutorService[] services;
   private ExecutorService asynService;
   @Autowired
   private C2SSessionPoolFactory sessionFactory;

   @PostConstruct
   private void init() {
      this.services = new ExecutorService[this.defaultCoreSum];

      for(int i = 0; i < this.defaultCoreSum; ++i) {
         this.services[i] = Executors.newSingleThreadExecutor(new NamedThreadFactory("rpc-transport" + i));
      }

      this.asynService = Executors.newFixedThreadPool(this.defaultCoreSum);
   }

   public void sendMessageSync(String ip, int port, Message message) {
      CCSession session = this.sessionFactory.borrowSession(ip, port);

      try {
         session.sendMessage(message);
      } finally {
         this.sessionFactory.returnSession(session);
      }

   }

   public void sendMessageAsync(String ip, int port, Message message) {
      String key = ip + port;
      int index = key.hashCode() % this.defaultCoreSum;
      this.services[index].submit(() -> this.sendMessageSync(ip, port, message));
      CCSession session = this.sessionFactory.borrowSession(ip, port);
      this.sendMessageAsync(session, message);
   }

   public void sendMessageAsync(CCSession session, Message message) {
      try {
         session.sendMessage(message);
      } finally {
         this.sessionFactory.returnSession(session);
      }

   }

   public Message callBack(CCSession session, Message message) throws ExecutionException, InterruptedException {
      CallbackTask task = CallbackTask.valueOf(session, message);
      return (Message)this.asynService.submit(task).get();
   }
}
