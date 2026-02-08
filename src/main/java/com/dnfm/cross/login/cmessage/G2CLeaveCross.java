package com.dnfm.cross.login.cmessage;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.protobuf.Message;

@MessageMeta(
   module = -7
)
public class G2CLeaveCross extends Message {
   private int code;

   public int getCode() {
      return this.code;
   }

   public void setCode(int code) {
      this.code = code;
   }
}
