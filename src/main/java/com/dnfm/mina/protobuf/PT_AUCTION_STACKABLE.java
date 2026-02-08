package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_AUCTION_STACKABLE {
   public Long charguid;
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
   public Integer upgrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer quality;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer endurance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer reforge;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer amplify;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer aoption;
   @Protobuf(
      order = 8
   )
   public List<PT_EMBLEM> emblem;
   @Protobuf(
      order = 9
   )
   public List<PT_STACKABLE> card;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer scount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer tcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 12,
      required = false
   )
   public Long expiretime;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 13,
      required = false
   )
   public Boolean rappearance;
   @Protobuf(
      order = 14
   )
   public List<PT_RANDOMOPTION_ITEM> roption;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer skin;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 16,
      required = false
   )
   public Long skinguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 17,
      required = false
   )
   public Boolean locked;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 18,
      required = false
   )
   public Boolean seal;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer enchantindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20,
      required = false
   )
   public Integer enchant;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 22,
      required = false
   )
   public Long auid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 23,
      required = false
   )
   public Long bidder;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer price;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 25,
      required = false
   )
   public Long enddate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer registcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer tera;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer buyprice;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 29,
      required = false
   )
   public Integer flag;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 31,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 32,
      required = false
   )
   public Integer season;
}
