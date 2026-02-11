package com.dnfm.mina.codec;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.mina.codec.reflect.ReflectDecoder;
import com.dnfm.mina.codec.reflect.ReflectEncoder;
import com.dnfm.mina.message.MessageFactory;
import com.dnfm.mina.protobuf.Message;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializerHelper {
   public static volatile SerializerHelper instance;
   private static final Logger logger = LoggerFactory.getLogger(SerializerHelper.class);
   private MessageCodecFactory codecFactory;
   private IMessageDecoder decoder;
   private IMessageEncoder encoder;

   public static SerializerHelper getInstance() {
      if (instance != null) {
         return instance;
      } else {
         synchronized(SerializerHelper.class) {
            if (instance == null) {
               instance = new SerializerHelper();
               instance.initialize();
            }
         }

         return instance;
      }
   }

   public static byte[] protobufEncodeObject(Object obj) {
      byte[] body = null;
      Class<?> msgClazz = obj.getClass();

      try {
         @SuppressWarnings("unchecked")
         Codec<Object> codec = (Codec<Object>) ProtobufProxy.create(msgClazz);
         body = codec.encode(obj);
      } catch (IOException e) {
         logger.error("", e);
      }

      return body;
   }

   public static byte[] protobufEncode(Message message) {
      byte[] body = null;
      Class<?> msgClazz = message.getClass();

      try {
         @SuppressWarnings("unchecked")
         Codec<Message> codec = (Codec<Message>) ProtobufProxy.create(msgClazz);
         body = codec.encode(message);
      } catch (IOException e) {
         logger.error("", e);
      }

      return body;
   }

   public static Object protobufDecodeObject(Class<?> objClazz, byte[] body) {
      if (body == null) {
         return null;
      } else {
         try {
            Codec<?> codec = ProtobufProxy.create(objClazz);
            Object obj = codec.decode(body);
            return obj;
         } catch (IOException e) {
            logger.error("读取消息出错,异常{}", e);
            return null;
         }
      }
   }

   public static Message protobufDecode(short module, short cmd, byte[] body) throws Exception {
      Class<?> msgClazz = MessageFactory.INSTANCE.getMessage(module, cmd);
      if (msgClazz == null) {
         return null;
      } else if (body == null) {
         return (Message)msgClazz.newInstance();
      } else {
         try {
            Codec<?> codec = ProtobufProxy.create(msgClazz);
            Message message = (Message)codec.decode(body);
            return message;
         } catch (IOException e) {
            logger.error("读取消息出错,模块号{}，类型{},异常{}", new Object[]{module, cmd, e});
            return null;
         }
      }
   }

   private void initialize() {
      this.decoder = new ReflectDecoder();
      this.encoder = new ReflectEncoder();
      this.codecFactory = new MessageCodecFactory();
   }

   public MessageCodecFactory getCodecFactory() {
      return this.codecFactory;
   }

   public IMessageDecoder getDecoder() {
      return this.decoder;
   }

   public IMessageEncoder getEncoder() {
      return this.encoder;
   }
}
