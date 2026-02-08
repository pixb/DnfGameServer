package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10780,
   cmd = 1
)
public class RES_SEASON_PASS_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_RETURN_USER_INFO returnUserInfo;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_BATTLE_PASS_INFO battlePassInfo;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_BATTLE_PASS_INFO pvpBattlePassInfo;
}
