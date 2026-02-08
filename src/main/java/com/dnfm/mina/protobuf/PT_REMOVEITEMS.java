package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_REMOVEITEMS {
   @Protobuf(
      order = 1
   )
   public List<PT_STACKABLE> materialitems;
   @Protobuf(
      order = 2
   )
   public List<PT_STACKABLE> emblemitems;
   @Protobuf(
      order = 3
   )
   public List<PT_STACKABLE> carditems;
   @Protobuf(
      order = 4
   )
   public List<PT_STACKABLE> consumeitems;
   @Protobuf(
      order = 5
   )
   public List<PT_STACKABLE> epicpieceitems;
   @Protobuf(
      order = 6
   )
   public List<PT_STACKABLE> crackitems;
   @Protobuf(
      order = 7
   )
   public List<PT_STACKABLE> bookmarkitems;
}
