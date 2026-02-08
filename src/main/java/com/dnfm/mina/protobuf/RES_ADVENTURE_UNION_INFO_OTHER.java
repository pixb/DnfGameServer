package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17216,
   cmd = 1
)
public class RES_ADVENTURE_UNION_INFO_OTHER extends Message {
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
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer day;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String name;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_CHARACTER typicalcharacter;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer charactercount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer world;
   @Protobuf(
      order = 9
   )
   public List<PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO> characterlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer shareboardbackground;
   @Protobuf(
      order = 11
   )
   public List<AchievementInfoPacketData> achievementinfolist;
}
