package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13027,
   cmd = 1
)
public class RES_START_MULTI_PLAY extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long matchingguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer battleworld;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer bchannel;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String bip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer bport;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer matchtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer channel;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 11,
      required = false
   )
   public Long targetguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 12
   )
   public List<Long> users;
   @Protobuf(
      order = 13
   )
   public List<PT_USER_MINIMUM_INFO> detail;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      order = 15
   )
   public List<PT_PVP_DICE_INFO> pvpdiceinfo;
   @Protobuf(
      order = 16
   )
   public List<PT_HISTORICSITE_RELIC_INFO> historicsiterelics;
}
