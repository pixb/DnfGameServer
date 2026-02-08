package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_MATERIAL_LIST {
   @Protobuf(
      order = 1
   )
   public List<PT_STACKABLE> material;
}
