package com.test.mina.codec.reflect.serializer;

import com.test.game.utils.ByteBuffUtil;
import org.apache.mina.core.buffer.IoBuffer;

public class BooleanSerializer extends Serializer {
   public Boolean decode(IoBuffer in, Class<?> type, Class<?> wrapper) {
      return ByteBuffUtil.readBoolean(in);
   }

   public void encode(IoBuffer out, Object value, Class<?> wrapper) {
      ByteBuffUtil.writeBoolean(out, (Boolean)value);
   }
}
