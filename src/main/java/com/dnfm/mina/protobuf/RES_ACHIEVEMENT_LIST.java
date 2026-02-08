package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10704,
   cmd = 1
)
public class RES_ACHIEVEMENT_LIST extends Message {
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
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer page;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer total;
   @Protobuf(
      order = 5
   )
   public List<AchievementInfoPacketData> list;
   @Protobuf(
      order = 6
   )
   public List<AchievementBonusPacketData> rewardbonus;
   @Protobuf(
      order = 7
   )
   public List<AchievementBonusPacketDataList> bonus;
}
