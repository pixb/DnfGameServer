package com.dnfm.cross.login.cmessage;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.protobuf.Message;

@MessageMeta(
   module = -6
)
public class C2GLoginCross extends Message {
   int resultCode;

   public int getResultCode() {
      return this.resultCode;
   }

   public void setResultCode(int resultCode) {
      this.resultCode = resultCode;
   }
}
