package com.dnfm.mina.codec.reflect.serializer;

import com.dnfm.game.utils.ByteBuffUtil;
import org.apache.mina.core.buffer.IoBuffer;

public class LongSerializer extends Serializer {
   public Long decode(IoBuffer in, Class<?> type, Class<?> wrapper) {
      return ByteBuffUtil.readLong(in);
   }

   public void encode(IoBuffer out, Object value, Class<?> wrapper) {
      ByteBuffUtil.writeLong(out, (Long)value);
   }

   public void encodeUnsignedInt(IoBuffer out, Object value, Class<?> wrapper) {
      ByteBuffUtil.writeUnsignedInt(out, (Long)value);
   }
}
