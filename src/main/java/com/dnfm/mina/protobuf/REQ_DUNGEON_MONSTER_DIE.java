package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11042,
   cmd = 0
)
public class REQ_DUNGEON_MONSTER_DIE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> list;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer stageguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer playercount;
   @Protobuf(
      order = 5
   )
   public List<PT_QUEST> quest;
   @Protobuf(
      order = 6
   )
   public List<PT_MONSTERKILL_LAST_SHOT> lastshot;
}
