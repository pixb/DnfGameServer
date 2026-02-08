package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_USER_INFO_VERIFICATION {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_USER_INFO userinfo;
   @Protobuf(
      order = 2,
      required = false
   )
   public Actor actor;
}
