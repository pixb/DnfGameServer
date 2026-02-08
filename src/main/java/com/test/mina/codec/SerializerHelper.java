package com.test.mina.codec;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.test.mina.codec.reflect.ReflectDecoder;
import com.test.mina.codec.reflect.ReflectEncoder;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializerHelper {
   private static Logger logger = LoggerFactory.getLogger(SerializerHelper.class);
   public static volatile SerializerHelper instance;
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

   @SuppressWarnings({"unchecked", "rawtypes"})
   public static byte[] protobufEncodeObject(Object obj) {
      byte[] body = null;
      Class<?> msgClazz = obj.getClass();

      try {
         Codec codec = ProtobufProxy.create(msgClazz);
         body = codec.encode(obj);
      } catch (IOException e) {
         logger.error("", e);
      }

      return body;
   }
}
