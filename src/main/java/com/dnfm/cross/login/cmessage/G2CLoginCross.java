package com.dnfm.cross.login.cmessage;

import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.protobuf.Message;

@MessageMeta(
   module = -5
)
public class G2CLoginCross extends Message {
   private byte type;
   private String playerJson;

   public byte getType() {
      return this.type;
   }

   public void setType(byte type) {
      this.type = type;
   }

   public String getPlayerJson() {
      return this.playerJson;
   }

   public void setPlayerJson(String playerJson) {
      this.playerJson = playerJson;
   }
}
