package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_HISTORICSITE_RESULT {
   public static enum T implements EnumReadable {
      NONE(0),
      WIN(1),
      LOSE(2),
      WIN_BY_DEFAULT(3),
      WIN_BY_DEFAULT_LIMIT(4),
      LOSE_BY_DEFAULT_LIMIT(5);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
