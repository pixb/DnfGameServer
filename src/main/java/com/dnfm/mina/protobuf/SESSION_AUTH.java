package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SESSION_AUTH {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer serverType;
         @Protobuf(
            fieldType = FieldType.INT64,
            order = 3,
            required = false
         )
         public Long serverKey;
         @Protobuf(
            fieldType = FieldType.INT64,
            order = 4,
            required = false
         )
         public Long authIndex;
         @Protobuf(
            fieldType = FieldType.BOOL,
            order = 5,
            required = false
         )
         public Boolean standby;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long authIndex;
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 2,
            required = false
         )
         public Long charguid;
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
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer serverType;
      @Protobuf(
         fieldType = FieldType.INT64,
         order = 3,
         required = false
      )
      public Long serverKey;
      @Protobuf(
         fieldType = FieldType.INT64,
         order = 4,
         required = false
      )
      public Long authIndex;
      @Protobuf(
         fieldType = FieldType.BOOL,
         order = 5,
         required = false
      )
      public Boolean standby;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long authIndex;
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 2,
         required = false
      )
      public Long charguid;
   }
}
