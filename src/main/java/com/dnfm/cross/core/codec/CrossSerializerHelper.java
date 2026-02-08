package com.dnfm.cross.core.codec;

import com.dnfm.mina.codec.IMessageDecoder;
import com.dnfm.mina.codec.IMessageEncoder;
import com.dnfm.mina.codec.reflect.ReflectDecoder;
import com.dnfm.mina.codec.reflect.ReflectEncoder;

public class CrossSerializerHelper {
   public static volatile CrossSerializerHelper instance;
   private CrossMessageCodecFactory codecFactory;
   private IMessageDecoder decoder;
   private IMessageEncoder encoder;

   public static CrossSerializerHelper getInstance() {
      if (instance != null) {
         return instance;
      } else {
         synchronized(CrossSerializerHelper.class) {
            if (instance == null) {
               instance = new CrossSerializerHelper();
               instance.initialize();
            }
         }

         return instance;
      }
   }

   private void initialize() {
      this.decoder = new ReflectDecoder();
      this.encoder = new ReflectEncoder();
      this.codecFactory = new CrossMessageCodecFactory();
   }

   public CrossMessageCodecFactory getCodecFactory() {
      return this.codecFactory;
   }

   public IMessageDecoder getDecoder() {
      return this.decoder;
   }

   public IMessageEncoder getEncoder() {
      return this.encoder;
   }
}
