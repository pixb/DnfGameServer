package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13547,
   cmd = 1
)
public class RES_SEARCH_PARTY_LIST extends Message {
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
   public Integer area;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer subtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer detail;
   @Protobuf(
      order = 6
   )
   public List<PT_RES_PARTY_LIST> list;
   @Protobuf(
      order = 7
   )
   public List<PT_DUNGEON_PARTY_COUNT> dungeonindexcount;
   @Protobuf(
      order = 8
   )
   public List<PT_STAGE_PARTY_COUNT> stagecount;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 9,
      required = false
   )
   public Boolean containobserver;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer stageindex;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 12,
      required = false
   )
   public Boolean enablepartyping;
}
