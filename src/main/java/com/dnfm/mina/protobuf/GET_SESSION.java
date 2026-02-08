package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class GET_SESSION {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 2,
            required = false
         )
         public byte[] sessionID;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 1,
            required = false
         )
         public String openID;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 2,
            required = false
         )
         public String authkey;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer worldID;
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
         fieldType = FieldType.BYTES,
         order = 2,
         required = false
      )
      public byte[] sessionID;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 1,
         required = false
      )
      public String openID;
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 2,
         required = false
      )
      public String authkey;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer worldID;
   }
}
