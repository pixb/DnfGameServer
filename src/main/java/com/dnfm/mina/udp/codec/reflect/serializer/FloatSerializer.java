package com.dnfm.mina.udp.codec.reflect.serializer;

import com.dnfm.game.utils.ByteBuffUtil;
import org.apache.mina.core.buffer.IoBuffer;

public class FloatSerializer extends Serializer {
   public Float decode(IoBuffer in, Class<?> type, Class<?> wrapper) {
      return ByteBuffUtil.readFloat(in);
   }

   public void encode(IoBuffer out, Object value, Class<?> wrapper) {
      ByteBuffUtil.writeFloat(out, (Float)value);
   }
}
