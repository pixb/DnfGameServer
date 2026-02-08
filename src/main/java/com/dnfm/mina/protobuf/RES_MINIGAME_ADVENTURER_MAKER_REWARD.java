package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 28124,
   cmd = 1
)
public class RES_MINIGAME_ADVENTURER_MAKER_REWARD extends Message {
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
   public PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO info;
   @Protobuf(
      order = 3
   )
   public List<PT_MINIGAME_ADVENTURER_MAKER_CLEAR_HISTORY> history;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewardInfos;
}
