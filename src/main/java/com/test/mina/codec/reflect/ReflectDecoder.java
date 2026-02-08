package com.test.mina.codec.reflect;

import com.test.mina.codec.IMessageDecoder;
import com.test.mina.codec.reflect.serializer.Serializer;
import com.test.mina.udp.Message;
import com.test.mina.udp.UdpMessageFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectDecoder implements IMessageDecoder {
   private static Logger logger = LoggerFactory.getLogger(ReflectDecoder.class);

   public Message readMessage(int module, int cmd, byte[] body) {
      IoBuffer in = IoBuffer.allocate(body.length);
      in.put(body);
      in.flip();
      Class<?> msgClazz = UdpMessageFactory.INSTANCE.getMessage(module, cmd);
      if (msgClazz == null) {
         return null;
      } else {
         try {
            Serializer messageCodec = Serializer.getSerializer(msgClazz);
            return (Message)messageCodec.decode(in, msgClazz, (Class)null);
         } catch (Exception e) {
            logger.error("读取消息出错,模块号{}，类型{},异常{}", new Object[]{module, cmd, e});
            return null;
         }
      }
   }
}
