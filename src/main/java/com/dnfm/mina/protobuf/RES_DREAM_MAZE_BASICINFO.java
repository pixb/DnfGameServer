package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 33000,
   cmd = 1
)
public class RES_DREAM_MAZE_BASICINFO extends Message {
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
   public Integer state;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer opendungeonindex;
   @Protobuf(
      order = 4
   )
   public List<PT_DREAM_MAZE_DUNGEON> dungeons;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer rewardcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer clearrewardcount;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 7,
      required = false
   )
   public Boolean receptibleclearreward;
}
