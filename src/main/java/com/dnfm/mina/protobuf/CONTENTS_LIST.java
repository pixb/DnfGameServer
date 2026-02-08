package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class CONTENTS_LIST {
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
         @Protobuf(
            fieldType = FieldType.UINT32,
            order = 3,
            required = false
         )
         public Integer page;
         @Protobuf(
            order = 4
         )
         public List<CONTENT_STREAM> contents;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer type;
         @Protobuf(
            fieldType = FieldType.UINT32,
            order = 2,
            required = false
         )
         public Integer page;
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
      @Protobuf(
         fieldType = FieldType.UINT32,
         order = 3,
         required = false
      )
      public Integer page;
      @Protobuf(
         order = 4
      )
      public List<CONTENT_STREAM> contents;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer type;
      @Protobuf(
         fieldType = FieldType.UINT32,
         order = 2,
         required = false
      )
      public Integer page;
   }
}
