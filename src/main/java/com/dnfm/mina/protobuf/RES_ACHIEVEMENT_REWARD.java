package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10701,
   cmd = 1
)
public class RES_ACHIEVEMENT_REWARD extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_EQUIP> equipitems;
   @Protobuf(
      order = 3
   )
   public List<PT_EQUIP> titleitems;
   @Protobuf(
      order = 4
   )
   public List<PT_EQUIP> flagitems;
   @Protobuf(
      order = 5
   )
   public List<PT_STACKABLE> materialitems;
   @Protobuf(
      order = 6
   )
   public List<PT_STACKABLE> consumeitems;
   @Protobuf(
      order = 7
   )
   public List<PT_STACKABLE> emblemitems;
   @Protobuf(
      order = 8
   )
   public List<PT_STACKABLE> carditems;
   @Protobuf(
      order = 9
   )
   public List<PT_STACKABLE> epicpieceitems;
   @Protobuf(
      order = 10
   )
   public List<PT_ARTIFACT> cartifactitems;
   @Protobuf(
      order = 11
   )
   public List<PT_CREATURE> creatureitems;
   @Protobuf(
      order = 12
   )
   public List<PT_AVATAR_ITEM> avataritems;
   @Protobuf(
      order = 13
   )
   public List<PT_SKIN_ITEM> damagefontitems;
   @Protobuf(
      order = 14
   )
   public List<PT_SKIN_ITEM> chatframeitems;
   @Protobuf(
      order = 15
   )
   public List<PT_SKIN_ITEM> characterframeitems;
   @Protobuf(
      order = 16
   )
   public List<PT_STACKABLE> crackitems;
   @Protobuf(
      order = 17
   )
   public List<AchievementInfoPacketData> achievement;
   @Protobuf(
      order = 18
   )
   public List<PT_ACHIEVEMENT_REWARD> rewards;
   @Protobuf(
      order = 19
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 20
   )
   public List<PT_MONEY_ITEM> accountcurrency;
   @Protobuf(
      order = 21,
      required = false
   )
   public PT_ITEMS invenitems;
   @Protobuf(
      order = 22
   )
   public List<PT_EQUIP> crackequipitems;
   @Protobuf(
      order = 23
   )
   public List<PT_AVATAR_ITEM> sdavataritems;
   @Protobuf(
      order = 24
   )
   public List<PT_STACKABLE> bookmarkitems;
   @Protobuf(
      order = 25
   )
   public List<PT_EQUIP> scrollitems;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 27,
      required = false
   )
   public Long adventureunionexp;
}
