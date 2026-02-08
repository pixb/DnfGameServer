package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CHARACTER_EQUIP_ONLY_INDEX {
   @Protobuf(
      order = 1
   )
   public List<PT_EQUIP_INDEX_SLOT> equiplist;
   @Protobuf(
      order = 2
   )
   public List<PT_AVATAR_INDEX_SLOT> avatarlist;
}
