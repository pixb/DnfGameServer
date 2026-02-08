package com.dnfm.cross.core.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CrossMessageCodecFactory implements ProtocolCodecFactory {
   private final CrossMessageDecoder decoder = new CrossMessageDecoder();
   private final CrossMessageEncoder encoder = new CrossMessageEncoder();

   public ProtocolEncoder getEncoder(IoSession session) throws Exception {
      return this.encoder;
   }

   public ProtocolDecoder getDecoder(IoSession session) throws Exception {
      return this.decoder;
   }
}
