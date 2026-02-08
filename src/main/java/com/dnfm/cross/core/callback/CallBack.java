package com.dnfm.cross.core.callback;

import com.dnfm.logs.LoggerUtils;
import com.dnfm.mina.protobuf.Message;
import java.util.concurrent.atomic.AtomicInteger;

public class CallBack {
   private static final AtomicInteger idFactory = new AtomicInteger();
   private int index;
   private Message data;

   public static int nextMsgId() {
      return idFactory.getAndIncrement();
   }

   protected void doTimeOut() {
      LoggerUtils.error("回调方法已过期");
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public Message getData() {
      return this.data;
   }

   public void setData(Message data) {
      this.data = data;
   }

   public boolean isDone() {
      return this.data != null;
   }
}
