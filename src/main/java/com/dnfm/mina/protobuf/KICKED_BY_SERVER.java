package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class KICKED_BY_SERVER {
   public static class Types {
      public static class NOTI {
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 1,
            required = false
         )
         public String mGuid;
         @Protobuf(
            order = 2,
            required = false
         )
         public REASON_MSG mKicked;
      }
   }

   public static class NOTI {
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 1,
         required = false
      )
      public String mGuid;
      @Protobuf(
         order = 2,
         required = false
      )
      public REASON_MSG mKicked;
   }
}
