package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class FRAME_DATA {
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
            order = 1
         )
         public List<INPUT_REQ> frame;
      }

      public static class NOTI {
         @Protobuf(
            order = 1
         )
         public List<FRAME> frame;
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
         order = 1
      )
      public List<INPUT_REQ> frame;
   }

   public static class NOTI {
      @Protobuf(
         order = 1
      )
      public List<FRAME> frame;
   }
}
