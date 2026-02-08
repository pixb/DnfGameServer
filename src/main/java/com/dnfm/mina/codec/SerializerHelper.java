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
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SerializerHelper implements ApplicationContextAware {
   public static volatile SerializerHelper instance;
   private static final Logger logger = LoggerFactory.getLogger(SerializerHelper.class);
   private MessageCodecFactory codecFactory;
   private IMessageDecoder decoder;
   private IMessageEncoder encoder;
   private ApplicationContext applicationContext;

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

   @SuppressWarnings({"unchecked", "rawtypes"})
   public static byte[] protobufEncode(Message message) {
      byte[] body = null;
      Class<?> msgClazz = message.getClass();

      try {
         Codec codec = ProtobufProxy.create(msgClazz);
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
            @SuppressWarnings("unchecked")
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
      if (this.applicationContext != null && this.applicationContext.containsBean("messageCodecFactory")) {
         try {
            this.codecFactory = this.applicationContext.getBean(MessageCodecFactory.class);
            logger.info("使用Spring容器中的MessageCodecFactory，protobuf.mode={}", 
               this.applicationContext.getEnvironment().getProperty("protobuf.mode"));
         } catch (BeansException e) {
            logger.warn("无法从Spring容器获取MessageCodecFactory，使用默认实例", e);
            this.codecFactory = new MessageCodecFactory();
         }
      } else {
         this.codecFactory = new MessageCodecFactory();
      }
   }
   
   public void reinitialize() {
      if (this.applicationContext != null && this.applicationContext.containsBean("messageCodecFactory")) {
         try {
            this.codecFactory = this.applicationContext.getBean(MessageCodecFactory.class);
            logger.info("重新初始化MessageCodecFactory，protobuf.mode={}", 
               this.applicationContext.getEnvironment().getProperty("protobuf.mode"));
         } catch (BeansException e) {
            logger.warn("无法从Spring容器获取MessageCodecFactory", e);
         }
      }
   }
   
   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = applicationContext;
      logger.info("SerializerHelper接收到ApplicationContext");
   }

   public MessageCodecFactory getCodecFactory() {
      return this.codecFactory;
   }
   
   public void setCodecFactory(MessageCodecFactory codecFactory) {
      this.codecFactory = codecFactory;
      logger.info("设置MessageCodecFactory成功，protobuf.mode={}", 
         this.applicationContext != null ? this.applicationContext.getEnvironment().getProperty("protobuf.mode") : "unknown");
   }

   public IMessageDecoder getDecoder() {
      return this.decoder;
   }

   public IMessageEncoder getEncoder() {
      return this.encoder;
   }
}
