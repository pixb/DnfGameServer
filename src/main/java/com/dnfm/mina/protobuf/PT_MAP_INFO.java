package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_MAP_INFO {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean bossmap;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long cleartime;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long starttick;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long endtick;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long guardiandeal;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer floor;
   @Protobuf(
      order = 9
   )
   public List<PT_OBJECT_INFO> objects;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 10,
      required = false
   )
   public Boolean startmap;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer posx;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer posy;
   @Protobuf(
      order = 13
   )
   public List<PT_EXP_INFO> expinfos;
   @Protobuf(
      order = 14
   )
   public List<PT_OBJECT_INFO> waveobjects;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer crackgateindex;
}
