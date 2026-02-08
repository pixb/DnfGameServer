package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CUSTOM_PVP_ROOM_SETTING {
   @Protobuf(
      order = 1
   )
   public List<PT_CUSTOM_PVP_DATA> data;
}
