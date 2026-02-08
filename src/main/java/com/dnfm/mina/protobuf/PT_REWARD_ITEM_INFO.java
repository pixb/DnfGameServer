package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_REWARD_ITEM_INFO {
   @Protobuf(
      order = 1
   )
   public List<PT_EQUIP> equips;
   @Protobuf(
      order = 2
   )
   public List<PT_EQUIP> titles;
   @Protobuf(
      order = 3
   )
   public List<PT_EQUIP> flags;
   @Protobuf(
      order = 4
   )
   public List<PT_STACKABLE> materials;
   @Protobuf(
      order = 5
   )
   public List<PT_STACKABLE> consumes;
   @Protobuf(
      order = 6
   )
   public List<PT_STACKABLE> emblems;
   @Protobuf(
      order = 7
   )
   public List<PT_STACKABLE> cards;
   @Protobuf(
      order = 8
   )
   public List<PT_STACKABLE> epicpieces;
   @Protobuf(
      order = 9
   )
   public List<PT_ARTIFACT> cartifacts;
   @Protobuf(
      order = 10
   )
   public List<PT_CREATURE> creatures;
   @Protobuf(
      order = 11
   )
   public List<PT_AVATAR_ITEM> avatars;
   @Protobuf(
      order = 12
   )
   public List<PT_SKIN_ITEM> damagefonts;
   @Protobuf(
      order = 13
   )
   public List<PT_SKIN_ITEM> chatframes;
   @Protobuf(
      order = 14
   )
   public List<PT_SKIN_ITEM> characterframes;
}
