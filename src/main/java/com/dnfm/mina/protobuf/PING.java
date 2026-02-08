package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PING {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.INT64,
            order = 2,
            required = false
         )
         public Long timestamp;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.INT64,
            order = 1,
            required = false
         )
         public Long timestamp;
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
         fieldType = FieldType.INT64,
         order = 2,
         required = false
      )
      public Long timestamp;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.INT64,
         order = 1,
         required = false
      )
      public Long timestamp;
   }
}
