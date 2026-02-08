package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class LOGIN {
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
         public Integer playerId;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 3,
            required = false
         )
         public String udpHost;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 4,
            required = false
         )
         public Integer udpPort;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long userId;
         @Protobuf(
            fieldType = FieldType.UINT32,
            order = 2,
            required = false
         )
         public Integer roomId;
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 3,
            required = false
         )
         public Long accessToken;
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
      public Integer playerId;
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 3,
         required = false
      )
      public String udpHost;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 4,
         required = false
      )
      public Integer udpPort;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long userId;
      @Protobuf(
         fieldType = FieldType.UINT32,
         order = 2,
         required = false
      )
      public Integer roomId;
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 3,
         required = false
      )
      public Long accessToken;
   }
}
