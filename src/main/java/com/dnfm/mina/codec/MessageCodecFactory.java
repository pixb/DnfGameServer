package com.dnfm.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("messageCodecFactory")
public class MessageCodecFactory implements ProtocolCodecFactory {
    private final DNFDecoder jprotobufDecoder;
    private final DNFEncoder jprotobufEncoder;
    private final StandardProtobufDecoder standardProtobufDecoder;
    private final StandardProtobufEncoder standardProtobufEncoder;
    
    @Value("${protobuf.mode:jprotobuf}")
    private String protobufMode;
    
    public MessageCodecFactory() {
        this.jprotobufDecoder = new DNFDecoder();
        this.jprotobufEncoder = new DNFEncoder();
        this.standardProtobufDecoder = new StandardProtobufDecoder();
        this.standardProtobufEncoder = new StandardProtobufEncoder();
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
      System.out.println("===== MessageCodecFactory.getDecoder() 被调用，protobuf.mode=" + protobufMode + " =====");
      if ("standard".equals(protobufMode)) {
         System.out.println("===== 返回 StandardProtobufDecoder =====");
         return this.standardProtobufDecoder;
      } else {
         System.out.println("===== 返回 DNFDecoder =====");
         return this.jprotobufDecoder;
      }
   }

   public ProtocolEncoder getEncoder(IoSession session) throws Exception {
      System.out.println("===== MessageCodecFactory.getEncoder() 被调用，protobuf.mode=" + protobufMode + " =====");
      if ("standard".equals(protobufMode)) {
         System.out.println("===== 返回 StandardProtobufEncoder =====");
         return this.standardProtobufEncoder;
      } else {
         System.out.println("===== 返回 DNFEncoder =====");
         return this.jprotobufEncoder;
      }
   }
}
