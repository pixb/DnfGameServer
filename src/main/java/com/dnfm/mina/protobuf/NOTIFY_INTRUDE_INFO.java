package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class NOTIFY_INTRUDE_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_ATTACK_SQUAD_DETAIL_INFO raidparty;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_PARTY_DETAIL_INFO party;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_DUNGEON_START_INFO startinfo;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_INTRUDE_WEDDINGHALL_INFO weddinghallinfo;
}
