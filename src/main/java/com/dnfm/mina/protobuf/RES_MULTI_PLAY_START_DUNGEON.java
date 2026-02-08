package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 15700,
   cmd = 1
)
public class RES_MULTI_PLAY_START_DUNGEON extends Message {
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
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long partyleaderguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer fatigue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer matchtype;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 10,
      required = false
   )
   public Boolean apcmode;
   @Protobuf(
      order = 11
   )
   public List<PT_USER_INFO> characinfos;
   @Protobuf(
      order = 12,
      required = false
   )
   public PT_CHAMPION_INFO champion;
   @Protobuf(
      order = 13
   )
   public List<PT_MAP_GUIDS> mapguids;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer partyleaderjob;
   @Protobuf(
      order = 15,
      required = false
   )
   public PT_CUSTOM_PVP_ROOM_SETTING customdata;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer requestedindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer mazeinfoindex;
   @Protobuf(
      order = 18
   )
   public List<PT_BATTLE_OPTION> battleoptionlist;
}
