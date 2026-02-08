package com.dnfm.mina.codec.reflect.serializer;

import com.dnfm.game.utils.ByteBuffUtil;
import org.apache.mina.core.buffer.IoBuffer;

public class ShortSerializer extends Serializer {
   public Short decode(IoBuffer in, Class<?> type, Class<?> wrapper) {
      return ByteBuffUtil.readShort(in);
   }

   public void encode(IoBuffer out, Object value, Class<?> wrapper) {
      if (value instanceof Long) {
         ByteBuffUtil.writeShort(out, ((Long)value).shortValue());
      } else if (value instanceof Integer) {
         ByteBuffUtil.writeShort(out, ((Integer)value).shortValue());
      } else if (value instanceof Byte) {
         ByteBuffUtil.writeShort(out, ((Byte)value).shortValue());
      } else {
         ByteBuffUtil.writeShort(out, (Short)value);
      }

   }

   public void encodeUnsignedByte(IoBuffer out, Object value, Class<?> wrapper) {
      if (value instanceof Long) {
         ByteBuffUtil.writeUnsignedByte(out, ((Long)value).shortValue());
      } else if (value instanceof Integer) {
         ByteBuffUtil.writeUnsignedByte(out, ((Integer)value).shortValue());
      } else if (value instanceof Byte) {
         Object var4 = ((Byte)value).shortValue();
         ByteBuffUtil.writeUnsignedByte(out, ((Byte)var4).shortValue());
      } else {
         ByteBuffUtil.writeUnsignedByte(out, (Short)value);
      }

   }
}
