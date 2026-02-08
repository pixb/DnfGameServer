package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27083,
   cmd = 1
)
public class RES_GUILD_ACCUMULATE_ATTENDANCE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer gattendance;
   @Protobuf(
      order = 4
   )
   public List<PT_GUILD_ATTEND_REWARD> garewards;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
