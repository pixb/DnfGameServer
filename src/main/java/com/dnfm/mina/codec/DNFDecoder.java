package com.dnfm.mina.codec;

import com.alibaba.fastjson.JSON;
import com.dnfm.game.utils.ByteBuffUtil;
import com.dnfm.game.utils.ProtocalSet;
import com.dnfm.mina.message.MessageFactory;
import com.dnfm.mina.protobuf.Message;
import java.nio.ByteOrder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DNFDecoder extends CumulativeProtocolDecoder {
   static final int MIN_PACKET_LEN = 8;
   Logger logger = LoggerFactory.getLogger(DNFDecoder.class);

   public static void main(String[] args) {
   }

   protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
      if (ioBuffer.remaining() < 8) {
         return false;
      } else {
         ioBuffer.mark();
         ioBuffer = ioBuffer.order(ByteOrder.LITTLE_ENDIAN);
         if (ioBuffer.remaining() < 8) {
            this.logger.error("小于MIN_PACKET_LEN");
            ioBuffer.reset();
            return false;
         } else {
            String hexStr = ioBuffer.getHexDump();
            int totalLen = ByteBuffUtil.readUnsignedShort(ioBuffer);
            int remaining = ioBuffer.remaining();
            if (remaining < totalLen - 2) {
               ioBuffer.reset();
               return false;
            } else {
               int moduleId = ByteBuffUtil.readUnsignedShort(ioBuffer);
               Class<?> msgClazz = MessageFactory.INSTANCE.getMessage(moduleId, 0);
               if (msgClazz != null) {
                  byte seq = ByteBuffUtil.readByte(ioBuffer);
                  Integer transactionId = Integer.valueOf(ByteBuffUtil.readByte(ioBuffer));
                  ByteBuffUtil.readUnsignedShort(ioBuffer);
                  if (totalLen > 8) {
                     byte[] body = new byte[totalLen - 8];
                     ioBuffer.get(body);
                     byte[] decBody = Enc.decrypt2(seq, body);

                     try {
                        Message msg = SerializerHelper.protobufDecode((short)moduleId, (short)0, decBody);
                        if (transactionId != 0) {
                           msg.transId = transactionId;
                        }

                        if (!ProtocalSet.ignoreSet.contains(moduleId)) {
                           this.logger.error("RECVMSG=={}=={}=={}", new Object[]{moduleId, msg.getClass().getSimpleName(), JSON.toJSONString(msg)});
                        }

                        protocolDecoderOutput.write(msg);
                     } catch (Exception var15) {
                        this.logger.error("DECODE_ERR=={}=={}", moduleId, ioBuffer.getHexDump());
                     }
                  } else {
                     byte[] decBody = null;

                     try {
                        Message msg = SerializerHelper.protobufDecode((short)moduleId, (short)0, decBody);
                        if (transactionId != 0) {
                           msg.transId = transactionId;
                        }

                        if (!ProtocalSet.ignoreSet.contains(moduleId)) {
                           this.logger.error("RECVMSG=={}=={}=={}", new Object[]{moduleId, msg.getClass().getSimpleName(), JSON.toJSONString(msg)});
                        }

                        protocolDecoderOutput.write(msg);
                     } catch (Exception var14) {
                        this.logger.error("DECODE_ERR=={}=={}", moduleId, ioBuffer.getHexDump());
                     }
                  }

                  return true;
               } else {
                  this.logger.warn("UNHANDLE==msgId={}", moduleId);
                  int len = totalLen - 4;

                  for(int i = 0; i < len; ++i) {
                     ByteBuffUtil.readByte(ioBuffer);
                  }

                  return true;
               }
            }
         }
      }
   }
}
