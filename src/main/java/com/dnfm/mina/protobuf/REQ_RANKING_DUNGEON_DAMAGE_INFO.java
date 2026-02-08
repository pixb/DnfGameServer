package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 11515,
   cmd = 0
)
public class REQ_RANKING_DUNGEON_DAMAGE_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 1,
      required = false
   )
   public byte[] damagehistory;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 2,
      required = false
   )
   public Long damage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer monstercount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer damageseq;
}
