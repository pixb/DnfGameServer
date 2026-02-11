package com.dnfm.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MessageCodecFactory implements ProtocolCodecFactory {
   private final DNFDecoder decoder = new DNFDecoder();
   private final DNFEncoder encoder = new DNFEncoder();

   public ProtocolEncoder getEncoder(IoSession session) throws Exception {
      return this.encoder;
   }

   public ProtocolDecoder getDecoder(IoSession session) throws Exception {
      return this.decoder;
   }
}
