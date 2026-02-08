package com.dnfm.mina.task;

import com.dnfm.mina.protobuf.Message;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageTask extends AbstractDistributeTask {
   private static final Logger logger = LoggerFactory.getLogger(MessageTask.class);
   private long playerId;
   private Message message;
   private Object handler;
   private Method method;
   private Object[] params;

   public static MessageTask valueOf(int distributeKey, Object handler, Method method, Object[] params, Message message) {
      MessageTask msgTask = new MessageTask();
      msgTask.distributeKey = distributeKey;
      msgTask.handler = handler;
      msgTask.method = method;
      msgTask.params = params;
      msgTask.message = message;
      return msgTask;
   }

   public void action() {
      try {
         this.method.invoke(this.handler, this.params);
      } catch (Throwable e) {
         logger.error("message task execute failed=协议执行失败={}", this.message.getModule(), e);
         InvocationTargetException targetEx = (InvocationTargetException)e;
         Throwable trowEx = targetEx.getTargetException();
         logger.error("异常：{}", trowEx.getMessage());
      }

   }

   public long getPlayerId() {
      return this.playerId;
   }

   public Message getMessage() {
      return this.message;
   }

   public Object getHandler() {
      return this.handler;
   }

   public Method getMethod() {
      return this.method;
   }

   public Object[] getParams() {
      return this.params;
   }

   public String toString() {
      return this.getName() + "[" + this.handler.getClass().getName() + "@" + this.method.getName() + "]";
   }
}
