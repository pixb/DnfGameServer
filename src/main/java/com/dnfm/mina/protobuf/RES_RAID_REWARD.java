package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11236,
   cmd = 1
)
public class RES_RAID_REWARD extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_RAID_USER_REWARDS> rewards;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_ITEMS items;
   @Protobuf(
      order = 4
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 5
   )
   public List<PT_MONEY_ITEM> accountcurrency;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long firstclearcount;
}
