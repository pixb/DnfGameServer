package com.dnfm.cross.demo;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.protobuf.Message;

@MessageMeta(
   module = -1
)
public class CReqCrossHeartBeat extends Message {
   private long time = System.currentTimeMillis();

   public long getTime() {
      return this.time;
   }

   public void setTime(long time) {
      this.time = time;
   }
}
