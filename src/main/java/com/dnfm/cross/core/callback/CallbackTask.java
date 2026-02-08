package com.dnfm.cross.core.callback;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.cross.core.client.C2SSessionPoolFactory;
import com.dnfm.cross.core.client.CCSession;
import com.dnfm.mina.protobuf.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CallbackTask implements Callable<Message> {
   private CCSession session;
   private Message request;

   public static CallbackTask valueOf(CCSession session, Message message) {
      CallbackTask task = new CallbackTask();
      task.request = message;
      task.session = session;
      return task;
   }

   public Message call() throws Exception {
      try {
         CReqCallBack reqCallBack = (CReqCallBack)this.request;
         int index = CallBack.nextMsgId();
         reqCallBack.setIndex(index);
         reqCallBack.serialize();
         this.session.sendMessage(reqCallBack);
         ReentrantLock lock = new ReentrantLock();
         Condition condition = lock.newCondition();
         long maxTimeOut = 5L;
         long startTime = System.currentTimeMillis();
         CallBackService callBackService = SpringUtils.getCallBackService();

         while(System.currentTimeMillis() - startTime <= maxTimeOut) {
            CallBack c = callBackService.removeCallBack(index);
            if (c != null) {
               Message var11 = c.getData();
               return var11;
            }

            try {
               lock.lockInterruptibly();
               condition.await(10L, TimeUnit.MILLISECONDS);
            } catch (Exception var20) {
            } finally {
               lock.unlock();
            }
         }

         return null;
      } finally {
         ((C2SSessionPoolFactory)SpringUtils.getBean(C2SSessionPoolFactory.class)).returnSession(this.session);
      }
   }
}
