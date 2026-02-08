package com.dnfm.cross.core.codec;

import com.dnfm.mina.codec.CodecContext;
import com.dnfm.mina.codec.IMessageEncoder;
import com.dnfm.mina.codec.SerializerHelper;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.session.SessionProperties;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CrossMessageEncoder implements ProtocolEncoder {
   public void dispose(IoSession arg0) throws Exception {
   }

   public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
      CodecContext context = (CodecContext)session.getAttribute(SessionProperties.CODEC_CONTEXT);
      if (context == null) {
         context = new CodecContext();
         session.setAttribute(SessionProperties.CODEC_CONTEXT, context);
      }

      IoBuffer buffer = this.writeMessage((Message)message);
      out.write(buffer);
   }

   private IoBuffer writeMessage(Message message) {
      IoBuffer buffer = IoBuffer.allocate(1024);
      buffer.setAutoExpand(true);
      IMessageEncoder msgEncoder = SerializerHelper.getInstance().getEncoder();
      byte[] body = msgEncoder.writeMessageBody(message);
      int metaSize = 3;
      buffer.putInt(body.length + 3);
      int moduleId = message.getModule();
      buffer.putInt(moduleId);
      buffer.put(body);
      buffer.flip();
      return buffer;
   }
}
