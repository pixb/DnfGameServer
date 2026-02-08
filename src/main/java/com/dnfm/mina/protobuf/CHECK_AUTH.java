package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CHECK_AUTH {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 2,
            required = false
         )
         public String authkey2;
         @Protobuf(
            fieldType = FieldType.BOOL,
            order = 3,
            required = false
         )
         public Boolean standby;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 1,
            required = false
         )
         public String authkey;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 2,
            required = false
         )
         public String openID;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer worldID;
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 4,
            required = false
         )
         public Long authIndex;
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
         fieldType = FieldType.STRING,
         order = 2,
         required = false
      )
      public String authkey2;
      @Protobuf(
         fieldType = FieldType.BOOL,
         order = 3,
         required = false
      )
      public Boolean standby;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 1,
         required = false
      )
      public String authkey;
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 2,
         required = false
      )
      public String openID;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer worldID;
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 4,
         required = false
      )
      public Long authIndex;
   }
}
