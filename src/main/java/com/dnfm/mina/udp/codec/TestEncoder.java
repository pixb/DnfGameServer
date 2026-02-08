package com.dnfm.mina.udp.codec;

import com.alibaba.fastjson.JSON;
import com.dnfm.mina.codec.CodecContext;
import com.dnfm.mina.udp.Message;
import com.dnfm.mina.udp.session.SessionManager;
import com.dnfm.mina.udp.session.SessionProperties;
import java.nio.ByteOrder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEncoder implements ProtocolEncoder {
   private static final Logger log = LoggerFactory.getLogger(TestEncoder.class);
   static Logger logger = LoggerFactory.getLogger(TestEncoder.class);

   public void dispose(IoSession arg0) throws Exception {
   }

   public void encode(IoSession session, Object message, ProtocolEncoderOutput output) throws Exception {
      CodecContext context = (CodecContext)session.getAttribute(SessionProperties.CODEC_CONTEXT);
      if (context == null) {
         context = new CodecContext();
         session.setAttribute(SessionProperties.CODEC_CONTEXT, context);
      }

      Message msg = (Message)message;
      logger.error("{}==UDP-SENDMSG=={}=={}=={}", new Object[]{session.getId(), ((Message)message).getModule(), msg.getClass().getSimpleName(), JSON.toJSONString(msg)});
      IoBuffer out = IoBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN).setAutoExpand(true).setAutoShrink(true);
      int packetType = msg.getModule();
      int metaSize = 23;
      Integer sessionId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.SESSION_ID, Integer.class);
      Long userId = (Long)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.USER_ID, Long.class);
      Integer roomId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ROOM_ID, Integer.class);
      Integer roomServerId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ROOM_SERVER_ID, Integer.class);
      Byte communicationType = (Byte)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.COMMUNICATION_TYPE, Byte.TYPE);
      Integer tempSessionId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.TMP_SESSION_ID, Integer.class);
      IMessageEncoder msgEncoder = SerializerHelper.getInstance().getEncoder();
      byte[] body = msgEncoder.writeMessageBody(msg);
      byte[] finalBody = Enc.lz4Compress(body, body.length);
      out.putUnsignedShort(packetType);
      out.putUnsignedShort(sessionId);
      out.putUnsignedShort(finalBody.length);
      out.putUnsignedShort(body.length);
      out.putLong(userId);
      out.putUnsignedShort(roomId);
      if (packetType != 1011) {
         out.putUnsignedShort(roomServerId);
      } else {
         out.putUnsignedShort((short)0);
      }

      if (packetType != 1005) {
         out.put(communicationType);
      } else {
         out.put((byte)2);
      }

      out.putUnsignedShort(tempSessionId);
      out.put(finalBody);
      out.flip();
      out.rewind();
      output.write(out);
      logger.error("{}==UDP-SEND=={}=={}=={}", new Object[]{session.getId(), ((Message)message).getModule(), msg.getClass().getSimpleName(), out.getHexDump()});
   }

   public static void main(String[] args) {
   }

   public static String bytesToHexString(byte[] src) {
      StringBuilder stringBuilder = new StringBuilder("");
      if (src != null && src.length > 0) {
         for(int i = 0; i < src.length; ++i) {
            int v = src[i] & 255;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
               stringBuilder.append(0);
            }

            stringBuilder.append(hv);
         }

         return stringBuilder.toString();
      } else {
         return null;
      }
   }
}
