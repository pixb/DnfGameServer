package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_REPURCHASE_STORAGE_LIST {
   @Protobuf(
      order = 1
   )
   public List<PT_REPURCHASE_EQUIP> equipitems;
   @Protobuf(
      order = 2
   )
   public List<PT_REPURCHASE_EQUIP> titleitems;
   @Protobuf(
      order = 3
   )
   public List<PT_REPURCHASE_EQUIP> flagitems;
   @Protobuf(
      order = 4
   )
   public List<PT_REPURCHASE_STACKABLE> materialitems;
   @Protobuf(
      order = 5
   )
   public List<PT_REPURCHASE_STACKABLE> consumeitems;
   @Protobuf(
      order = 6
   )
   public List<PT_REPURCHASE_STACKABLE> emblemitems;
   @Protobuf(
      order = 7
   )
   public List<PT_REPURCHASE_STACKABLE> carditems;
   @Protobuf(
      order = 8
   )
   public List<PT_REPURCHASE_STACKABLE> epicpieceitems;
   @Protobuf(
      order = 9
   )
   public List<PT_REPURCHASE_ARTIFACT> cartifactitems;
   @Protobuf(
      order = 10
   )
   public List<PT_REPURCHASE_CRETURE> creatureitems;
   @Protobuf(
      order = 11
   )
   public List<PT_REPURCHASE_AVATAR_ITEM> avataritems;
}
