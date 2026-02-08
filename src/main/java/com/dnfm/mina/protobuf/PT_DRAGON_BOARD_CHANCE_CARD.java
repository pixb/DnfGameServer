package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_DRAGON_BOARD_CHANCE_CARD {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_DRAGON_BOARD_CHANGE_DICE changedice;
   @Protobuf(
      order = 3
   )
   public List<PT_DRAGON_BOARD_CHANGE_SLOT> changeslots;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_DRAGON_BOARD_HOLD hold;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_DRAGON_BOARD_CHANGE_SLOT moveslot;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_DRAGON_BOARD_APPENDAGE debuff;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_DRAGON_BOARD_APPENDAGE dotdamage;
   @Protobuf(
      order = 8,
      required = false
   )
   public PT_DRAGON_BOARD_APPENDAGE boss;
}
