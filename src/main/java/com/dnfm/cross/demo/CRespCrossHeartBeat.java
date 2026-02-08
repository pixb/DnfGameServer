package com.dnfm.cross.demo;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.protobuf.Message;

@MessageMeta(
   module = -2
)
public class CRespCrossHeartBeat extends Message {
   private long time = System.currentTimeMillis();

   public long getTime() {
      return this.time;
   }

   public void setTime(long time) {
      this.time = time;
   }
}
