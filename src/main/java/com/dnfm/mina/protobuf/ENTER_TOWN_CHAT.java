package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ENTER_TOWN_CHAT {
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
         public Integer type;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer worldID;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer channel;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer town;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 4,
            required = false
         )
         public Integer area;
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
      public Integer type;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer worldID;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer channel;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer town;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 4,
         required = false
      )
      public Integer area;
   }
}
