package com.dnfm.mina.codec.reflect;

import com.dnfm.mina.codec.IMessageEncoder;
import com.dnfm.mina.codec.reflect.serializer.Serializer;
import com.dnfm.mina.protobuf.Message;
import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectEncoder implements IMessageEncoder {
   private static final Logger logger = LoggerFactory.getLogger(ReflectEncoder.class);

   public byte[] writeMessageBody(Message message) {
      IoBuffer out = IoBuffer.allocate(32).setAutoShrink(true).setAutoExpand(true);

      try {
         Serializer messageCodec = Serializer.getSerializer(message.getClass());
         messageCodec.encode(out, message, (Class)null);
      } catch (Exception e) {
         logger.error("读取消息出错,模块号{}，类型{},异常{}", message.getModule(), e);
      }

      out.flip();
      byte[] body = new byte[out.remaining()];
      out.get(body);
      return body;
   }
}
