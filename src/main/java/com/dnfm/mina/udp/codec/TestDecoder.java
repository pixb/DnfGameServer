package com.dnfm.mina.udp.codec;

import com.alibaba.fastjson.JSON;
import com.dnfm.game.utils.ByteBuffUtil;
import com.dnfm.mina.udp.Global;
import com.dnfm.mina.udp.Message;
import com.dnfm.mina.udp.UdpMessageFactory;
import com.dnfm.mina.udp.session.SessionManager;
import com.dnfm.mina.udp.session.SessionProperties;
import java.nio.ByteOrder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDecoder extends CumulativeProtocolDecoder {
   Logger logger = LoggerFactory.getLogger(TestDecoder.class);
   static final int MIN_PACKET_LEN = 23;

   protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
      if (ioBuffer.remaining() >= 23) {
         ioBuffer.mark();
         ioBuffer = ioBuffer.order(ByteOrder.LITTLE_ENDIAN);
         String hexStr = ioBuffer.getHexDump();
         int packetType = ByteBuffUtil.readUnsignedShort(ioBuffer);
         int sessionId = ByteBuffUtil.readUnsignedShort(ioBuffer);
         int bodyLength = ByteBuffUtil.readUnsignedShort(ioBuffer);
         int originLength = ByteBuffUtil.readUnsignedShort(ioBuffer);
         long userId = ByteBuffUtil.readLong(ioBuffer);
         int roomId = ByteBuffUtil.readUnsignedShort(ioBuffer);
         int roomServerId = ByteBuffUtil.readUnsignedShort(ioBuffer);
         byte communicationType = ByteBuffUtil.readByte(ioBuffer);
         int tempSessionId = ByteBuffUtil.readUnsignedShort(ioBuffer);
         int globalSessionId = Global.session_id;
         Integer session_id = (Integer)SessionManager.INSTANCE.getSessionAttr(ioSession, SessionProperties.SESSION_ID, Integer.class);
         if (session_id == null) {
            this.logger.error("TestDecoder==sessionId==NULL=={}", ioSession.getRemoteAddress());
            ioSession.setAttribute(SessionProperties.SESSION_ID, globalSessionId);
            ++Global.session_id;
         }

         Long user_id = (Long)SessionManager.INSTANCE.getSessionAttr(ioSession, SessionProperties.USER_ID, Long.class);
         if (user_id == null) {
            this.logger.error("{}==set-userId=={}", ioSession, userId);
            ioSession.setAttribute(SessionProperties.USER_ID, userId);
         }

         Integer room_id = (Integer)SessionManager.INSTANCE.getSessionAttr(ioSession, SessionProperties.ROOM_ID, Integer.class);
         if (room_id == null) {
            this.logger.error("{}==set-roomId=={}", ioSession, roomId);
            ioSession.setAttribute(SessionProperties.ROOM_ID, roomId);
         }

         Integer room_server_id = (Integer)SessionManager.INSTANCE.getSessionAttr(ioSession, SessionProperties.ROOM_SERVER_ID, Integer.class);
         if (room_server_id == null) {
            this.logger.error("{}==set-roomServerId=={}", ioSession, roomServerId);
            ioSession.setAttribute(SessionProperties.ROOM_SERVER_ID, roomServerId);
         }

         Byte comm_type = (Byte)SessionManager.INSTANCE.getSessionAttr(ioSession, SessionProperties.COMMUNICATION_TYPE, Byte.class);
         if (comm_type == null) {
            this.logger.error("{}==set-communicationType=={}", ioSession, communicationType);
            ioSession.setAttribute(SessionProperties.COMMUNICATION_TYPE, communicationType);
         }

         Integer tmp_session_id = (Integer)SessionManager.INSTANCE.getSessionAttr(ioSession, SessionProperties.TMP_SESSION_ID, Integer.class);
         if (tmp_session_id == null) {
            this.logger.error("{}==set-tempSessionId=={}", ioSession, tempSessionId);
            ioSession.setAttribute(SessionProperties.TMP_SESSION_ID, tempSessionId);
         }

         Class<?> msgClazz = UdpMessageFactory.INSTANCE.getMessage(packetType, 0);
         this.logger.error("{}-UDP-RECV=={}=={}", new Object[]{ioSession.getId(), packetType, hexStr});
         if (msgClazz == null) {
            this.logger.warn("UNHANDLE==packetType={}", packetType);
            byte[] tmpBs = new byte[bodyLength];
            ioBuffer.get(tmpBs);
            return true;
         } else if (bodyLength == 0) {
            Message msg = (Message)msgClazz.newInstance();
            this.logger.error("{}-UDP-RECVMSG=={}=={}=={}", new Object[]{ioSession.getId(), packetType, msg.getClass().getSimpleName(), JSON.toJSONString(msg)});
            protocolDecoderOutput.write(msg);
            return true;
         } else {
            byte[] body = new byte[bodyLength];
            ioBuffer.get(body);
            byte[] orgBs = Enc.lz4Decompress(body, originLength);
            IMessageDecoder msgDecoder = SerializerHelper.getInstance().getDecoder();

            try {
               Message msg = msgDecoder.readMessage(packetType, 0, orgBs);
               this.logger.error("{}-UDP-RECVMSG=={}=={}=={}", new Object[]{ioSession.getId(), packetType, msg.getClass().getSimpleName(), JSON.toJSONString(msg)});
               protocolDecoderOutput.write(msg);
            } catch (Exception var27) {
               this.logger.error("解包出错=={}=={}", packetType, ioBuffer.getHexDump());
            }

            return true;
         }
      } else {
         this.logger.error("出现断包=={}", ioBuffer.getHexDump());
         return false;
      }
   }

   public static void main(String[] args) {
   }
}
