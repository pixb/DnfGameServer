package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11042,
   cmd = 1
)
public class RES_DUNGEON_MONSTER_DIE extends Message {
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
   public Integer totalexp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer earnexp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5
   )
   public List<Integer> list;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long sender;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_ITEMS items;
   @Protobuf(
      order = 8
   )
   public List<PT_BATTLELEAGUE_CONTRIBUTE> earnbattleleaguecontribution;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer publicbattleleaguecontribution;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer epicgauge;
}
