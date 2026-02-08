package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class POS_SYNC {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long charguid;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer posx;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer posy;
      }

      public static class NOTI {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long charguid;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer posx;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer posy;
      }
   }

   public static class RES {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer error;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long charguid;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer posx;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer posy;
   }

   public static class NOTI {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long charguid;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer posx;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer posy;
   }
}
