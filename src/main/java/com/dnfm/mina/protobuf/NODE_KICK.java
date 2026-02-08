package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class NODE_KICK {
   public static class Types {
      public static class NOTI {
         @Protobuf(
            fieldType = FieldType.INT64,
            order = 1,
            required = false
         )
         public Long targetNode;
      }
   }

   public static class NOTI {
      @Protobuf(
         fieldType = FieldType.INT64,
         order = 1,
         required = false
      )
      public Long targetNode;
   }
}
