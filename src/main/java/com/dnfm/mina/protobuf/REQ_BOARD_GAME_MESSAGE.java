package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13555,
   cmd = 0
)
public class REQ_BOARD_GAME_MESSAGE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long matchingguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer itemindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8
   )
   public List<Integer> intlist;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9
   )
   public List<String> strlist;
   @Protobuf(
      order = 10
   )
   public List<PT_BUFF> bufflist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer dice;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 12,
      required = false
   )
   public Boolean gmspecial;
}
