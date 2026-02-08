package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17200,
   cmd = 1
)
public class RES_ADVENTURE_UNION_INFO extends Message {
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
   public Integer exp;
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
   public Integer day;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long typicalcharacterguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long updatetime;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 8,
      required = false
   )
   public Long lastchangenametime;
   @Protobuf(
      order = 9,
      required = false
   )
   public PT_ADVENTURE_UNION_EXPEDITION expedition;
   @Protobuf(
      order = 10
   )
   public List<PT_ADVENTURE_UNION_EXPEDITION> expeditions;
   @Protobuf(
      order = 11
   )
   public List<PT_ADVENTURE_UNION_COLLECTION> collections;
   @Protobuf(
      order = 12
   )
   public List<PT_ADVENTURE_UNION_COLLECTION_SLOT> collectionslots;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer shareboardbackground;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer shareboardframe;
   @Protobuf(
      order = 15
   )
   public List<PT_ADVENTURE_UNION_SHAREBOARD_SLOT> shareboardslotlist;
   @Protobuf(
      order = 16
   )
   public List<PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND> shareboardbackgroundlist;
   @Protobuf(
      order = 17
   )
   public List<PT_ADVENTURE_UNION_SHAREBOARD_FRAME> shareboardframelist;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 18,
      required = false
   )
   public Boolean shareboardshowantievilscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer autosearchcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20
   )
   public List<Integer> receivedcollectionrewards;
   @Protobuf(
      order = 21
   )
   public List<PT_ADVENTURE_UNION_LEVEL_REWARD> levelrewards;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer shareboardtotalantievilscore;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 23,
      required = false
   )
   public Boolean shareboardantievilscorerefresh;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 24,
      required = false
   )
   public Boolean isadventureCondition;
}
