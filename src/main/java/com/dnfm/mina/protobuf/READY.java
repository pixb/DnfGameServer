package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class READY {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.BOOL,
            order = 2,
            required = false
         )
         public Boolean restart;
      }

      public static class REQ {
      }
   }

   public static class RES {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer error;
      @Protobuf(
         fieldType = FieldType.BOOL,
         order = 2,
         required = false
      )
      public Boolean restart;
   }

   public static class REQ {
   }
}
