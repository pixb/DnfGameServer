package com.dnfm.mina.codec;

import com.alibaba.fastjson.JSON;
import com.dnfm.ParseUtil;
import com.dnfm.game.utils.ProtocalSet;
import com.dnfm.mina.message.MessageFactory;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.RES_ENTER_CHANNEL;
import com.dnfm.mina.session.SessionProperties;
import java.nio.ByteOrder;
import java.util.HashSet;
import java.util.Set;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DNFEncoder implements ProtocolEncoder {
   private static final Logger log = LoggerFactory.getLogger(DNFEncoder.class);
   static Logger logger = LoggerFactory.getLogger(DNFEncoder.class);
   private static final Set<Integer> FILTER_MAP = new HashSet();

   @Test
   public static void main(String[] args) {
      MessageFactory.INSTANCE.initMessagePool("com.dnfm.mina.protobuf");
      RES_ENTER_CHANNEL message = new RES_ENTER_CHANNEL();
      message.error = 1;
      message.authkey = "RES_ENTER_CHANNEL";
      byte[] body = SerializerHelper.protobufEncode(message);
      Byte seq = 0;
      int module = message.getModule();
      byte[] encBody = Enc.encrypt(seq, body);
      logger.error("decBody==msgId={}={}", module, ParseUtil.bytesToHexStr(body));
      byte[] decBody = Enc.decrypt2(seq, encBody);
      logger.error("SEND1==msgId={}={}", module, ParseUtil.bytesToHexStr(decBody));

      try {
         Message newMsg = SerializerHelper.protobufDecode((short)module, (short)0, decBody);
         logger.error("decBody-Message={}={}={}", new Object[]{module, newMsg.getClass().getSimpleName(), JSON.toJSONString(newMsg)});
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public static String bytesToHexString(byte[] src) {
      StringBuilder stringBuilder = new StringBuilder();
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

   public void dispose(IoSession arg0) throws Exception {
   }

   public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
      CodecContext context = (CodecContext)session.getAttribute(SessionProperties.CODEC_CONTEXT);
      if (context == null) {
         context = new CodecContext();
         session.setAttribute(SessionProperties.CODEC_CONTEXT, context);
      }

      Byte seq = (Byte)session.getAttribute(SessionProperties.SEQ);
      if (seq == null) {
         seq = 0;
      }

      Byte nextSeq = (byte)(seq + 1);
      session.setAttribute(SessionProperties.SEQ, nextSeq);
      IoBuffer buffer = this.writeMessage((Message)message, seq);
      out.write(buffer);
   }

   private IoBuffer writeMessage(Message message, byte seq) {
      int module = message.getModule();
      if (!ProtocalSet.ignoreSet.contains(module)) {
         logger.error("SENDMSG=={}=={}=={}", new Object[]{module, message.getClass().getSimpleName(), JSON.toJSONString(message)});
      }

      IoBuffer out = IoBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN).setAutoExpand(true).setAutoShrink(true);
      int metaSize = 8;
      byte[] body = SerializerHelper.protobufEncode(message);
      byte[] encBody = Enc.encrypt(seq, body);
      if (encBody != null) {
         short totalLen = (short)(encBody.length + 8);
         out.putShort(totalLen);
         out.putShort((short)module);
         out.put(seq);
         Integer transactionId = message.transId;
         if (transactionId != null) {
            out.put(transactionId.byteValue());
         } else {
            out.put((byte)0);
         }

         out.putUnsignedShort(body.length);
         out.put(encBody);
         out.flip();
         out.rewind();
         return out;
      } else {
         short totalLen = 8;
         out.putShort(totalLen);
         out.putShort((short)module);
         out.put(seq);
         Integer transactionId = message.transId;
         if (transactionId != null) {
            out.put(transactionId.byteValue());
         } else {
            out.put((byte)0);
         }

         out.putUnsignedShort(body.length);
         out.flip();
         out.rewind();
         return out;
      }
   }

   static {
      FILTER_MAP.add(45555);
      FILTER_MAP.add(13143);
   }
}
