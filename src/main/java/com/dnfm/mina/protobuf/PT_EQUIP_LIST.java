package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_EQUIP_LIST {
   @Protobuf(
      order = 1
   )
   public List<PT_EQUIP> equiplist;
   @Protobuf(
      order = 2
   )
   public List<PT_AVATAR_ITEM> avatarlist;
   @Protobuf(
      order = 3
   )
   public List<PT_CREATURE> creaturelist;
   @Protobuf(
      order = 4
   )
   public List<PT_ARTIFACT> cartifactlist;
   @Protobuf(
      order = 5
   )
   public List<PT_SKIN_ITEM> skinlist;
   @Protobuf(
      order = 6
   )
   public List<PT_EQUIP> equipskinlist;
   @Protobuf(
      order = 7
   )
   public List<PT_AVATAR_ITEM> avatarskinlist;
   @Protobuf(
      order = 8
   )
   public List<PT_AVATAR_ITEM> sdavatarlist;
}
