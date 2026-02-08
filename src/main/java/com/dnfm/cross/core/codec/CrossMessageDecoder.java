package com.dnfm.cross.core.codec;

import com.dnfm.mina.codec.CodecContext;
import com.dnfm.mina.codec.IMessageDecoder;
import com.dnfm.mina.codec.SerializerHelper;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.session.SessionManager;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrossMessageDecoder extends CumulativeProtocolDecoder {
   Logger logger = LoggerFactory.getLogger(CrossMessageDecoder.class);
   AttributeKey CODEC_CONTEXT = new AttributeKey(CrossMessageDecoder.class, "CONTEXT_KEY");

   protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
      CodecContext context = (CodecContext)SessionManager.INSTANCE.getSessionAttr(session, this.CODEC_CONTEXT, CodecContext.class);
      if (context == null) {
         context = new CodecContext();
         session.setAttribute(this.CODEC_CONTEXT, context);
      }

      IoBuffer ioBuffer = context.getBuffer();
      ioBuffer.put(in);
      IMessageDecoder msgDecoder = SerializerHelper.getInstance().getDecoder();

      while(true) {
         ioBuffer.flip();
         int metaSize = 3;
         if (ioBuffer.remaining() < 3) {
            ioBuffer.compact();
            return false;
         }

         int length = ioBuffer.getInt();
         if (ioBuffer.remaining() < length) {
            ioBuffer.rewind();
            ioBuffer.compact();
            break;
         }

         int moduleId = ioBuffer.getInt();
         int msgbodyLen = length - 3;
         byte[] body = new byte[msgbodyLen];
         ioBuffer.get(body);
         Message msg = msgDecoder.readMessage(moduleId, 0, body);
         protocolDecoderOutput.write(msg);
         if (ioBuffer.remaining() == 0) {
            ioBuffer.clear();
            break;
         }

         ioBuffer.compact();
      }

      return true;
   }

   public void dispose(IoSession arg0) throws Exception {
   }

   public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
   }
}
