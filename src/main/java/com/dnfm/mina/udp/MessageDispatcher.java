package com.dnfm.mina.udp;

import com.alibaba.fastjson.JSON;
import com.dnfm.ParseUtil;
import com.dnfm.game.groupdungeon.model.GroupDungeonCache;
import com.dnfm.game.utils.ByteBuffUtil;
import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.codec.SerializerHelper;
import com.dnfm.mina.protobuf.RES_PARTY_MEMBER_BATTLE_SERVER_CONNECT;
import com.dnfm.mina.udp.message.CmdExecutor;
import com.dnfm.mina.udp.message.IMessageDispatcher;
import com.dnfm.mina.udp.message.MessagePusher;
import com.dnfm.mina.udp.model.FrameInfo;
import com.dnfm.mina.udp.model.FrameNoti;
import com.dnfm.mina.udp.model.InputInfo;
import com.dnfm.mina.udp.model.InputRequest;
import com.dnfm.mina.udp.model.LoginRequest;
import com.dnfm.mina.udp.model.LoginResponse;
import com.dnfm.mina.udp.model.LogoutResponse;
import com.dnfm.mina.udp.model.NeoInput;
import com.dnfm.mina.udp.model.Pong;
import com.dnfm.mina.udp.model.ReadyRequest;
import com.dnfm.mina.udp.model.ReadyResponse;
import com.dnfm.mina.udp.model.RelayData;
import com.dnfm.mina.udp.model.Start;
import com.dnfm.mina.udp.session.SessionManager;
import com.dnfm.mina.udp.session.SessionProperties;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDispatcher implements IMessageDispatcher {
   private Logger logger = LoggerFactory.getLogger(this.getClass());
   private static MessageDispatcher instance = new MessageDispatcher();
   private static final Map<String, CmdExecutor> MODULE_CMD_HANDLERS = new HashMap();

   public static MessageDispatcher getInstance() {
      return instance;
   }

   public void registerMessageHandler(Object controller) {
      try {
         Method[] methods = controller.getClass().getDeclaredMethods();

         for(Method method : methods) {
            RequestMapping mapperAnnotation = (RequestMapping)method.getAnnotation(RequestMapping.class);
            if (mapperAnnotation != null) {
               String meta = this.getMessageMeta(method);
               if (meta == null) {
                  this.logger.error("methodName = " + method.getName());
                  throw new RuntimeException(String.format("controller[%s] method[%s] lack of RequestMapping annotation", controller.getClass().getSimpleName(), method.getName()));
               }

               CmdExecutor cmdExecutor = (CmdExecutor)MODULE_CMD_HANDLERS.get(meta);
               if (cmdExecutor != null) {
               }

               cmdExecutor = CmdExecutor.valueOf(method, method.getParameterTypes(), controller);
               MODULE_CMD_HANDLERS.put(meta, cmdExecutor);
            }
         }
      } catch (Exception e) {
         this.logger.error("", e);
      }

   }

   private String getMessageMeta(Method method) {
      for(Class<?> paramClazz : method.getParameterTypes()) {
         if (Message.class.isAssignableFrom(paramClazz)) {
            MessageMeta protocol = (MessageMeta)paramClazz.getAnnotation(MessageMeta.class);
            if (protocol != null) {
               return String.valueOf(protocol.module());
            }
         }
      }

      return null;
   }

   public void dispatch(IoSession session, Message message) {
      int module = message.getModule();
      this.logger.error("dispatch=={}=={}", module, message.getClass().getSimpleName());
      switch (module) {
         case 1004:
            this.loginRequest(session, (LoginRequest)message);
            break;
         case 1005:
         case 1007:
         case 1009:
         case 1011:
         case 1012:
         default:
            this.logger.error("UNHANDLED MESSAGE, module={}", module);
            break;
         case 1006:
            LogoutResponse logoutResponse = new LogoutResponse();
            MessagePusher.pushMessage(session, logoutResponse);
            break;
         case 1008:
            this.readyRequest(session, (ReadyRequest)message);
            break;
         case 1010:
            this.inputRequest(session, (InputRequest)message);
            break;
         case 1013:
            Pong pong = new Pong();
            pong.checkData1 = 0;
            pong.checkData2 = 0L;
            MessagePusher.pushMessage(session, pong);
      }

   }

   private void inputRequest(IoSession session, InputRequest inputRequest) {
      long charguid = 0L;
      long matchingguid = 0L;
      Integer playerId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PK_PLAYER_ID, Integer.class);
      Integer roomId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ROOM_ID, Integer.class);

      for(NeoInput neoInput : inputRequest.inputList) {
         for(InputInfo neoInputInfo : neoInput.inputInfoList) {
            if (neoInputInfo == null) {
               return;
            }

            RelayData relayData = new RelayData();
            relayData.playerId = playerId;
            relayData.recvDelayMs = 0;
            relayData.data = neoInputInfo.inputData;
            relayData.sendTime = neoInputInfo.sendTime;
            Global.putRelayData(roomId, relayData);
         }
      }

      this.logger.error("inputRequest==ROOM_FRAME_MAP=={}", JSON.toJSONString(Global.ROOM_FRAME_MAP));
      if (!Global.INITED) {
         Global.INITED = true;
      }

      FrameNoti frameNoti = new FrameNoti();
      frameNoti.currentFrameId = roomId;
      frameNoti.frameInfoList = new ArrayList();
      frameNoti.frameInfoList.addAll(Global.getFrames(roomId));
      MessagePusher.pushMessage(session, frameNoti);
   }

   private void loginRequest(IoSession session, LoginRequest loginRequest) {
      session.setAttribute(SessionProperties.ACCESS_TOKEN, loginRequest.accessToken);
      Long user_id = (Long)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.USER_ID, Long.class);
      long pk_player_id = (long)GroupDungeonCache.getPlayerId(user_id);
      int playerId = (int)pk_player_id;
      this.logger.error("loginRequest==playerId=={}", pk_player_id);
      session.setAttribute(SessionProperties.PK_PLAYER_ID, playerId);
      Integer session_id = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.SESSION_ID, Integer.class);
      LoginResponse loginResponse = new LoginResponse();
      loginResponse.errorCode = 0;
      loginResponse.playerId = playerId;
      loginResponse.sessionId = session_id.shortValue();
      MessagePusher.pushMessage(session, loginResponse);
   }

   private void readyRequest(IoSession session, ReadyRequest loginRequest) {
      ReadyResponse readyResponse = new ReadyResponse();
      readyResponse.errorCode = 0;
      MessagePusher.pushMessage(session, readyResponse);
      Start start = new Start();
      start.intVal = 0;
      MessagePusher.pushMessage(session, start);
      FrameNoti frameNoti = new FrameNoti();
      frameNoti.currentFrameId = 0;
      frameNoti.frameInfoList = new ArrayList();
      FrameInfo frameInfo1 = new FrameInfo();
      frameInfo1.frameId = 0;
      frameInfo1.recvTickMs = 0;
      frameInfo1.relayDataList = new ArrayList();
      Integer roomId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ROOM_ID, Integer.class);
      frameNoti.currentFrameId = roomId;
      frameInfo1.frameId = roomId;

      for(Long charguid : GroupDungeonCache.getCharguidList((long)roomId)) {
         RelayData relayData1 = new RelayData();
         relayData1.playerId = roomId;
         relayData1.recvDelayMs = 0;
         IoBuffer out = IoBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN).setAutoExpand(true).setAutoShrink(true);
         ByteBuffUtil.writeInt(out, 2162945);
         ByteBuffUtil.writeInt(out, 15950);
         ByteBuffUtil.writeShort(out, (short)0);
         int playerId = GroupDungeonCache.getPlayerId(charguid);
         if (charguid == 9348293791842395L) {
            ByteBuffUtil.writeInt(out, 105);
         } else {
            ByteBuffUtil.writeInt(out, 116);
         }

         ByteBuffUtil.writeByte(out, (byte)0);
         this.logger.error("readyRequest==roomId=={}", roomId);
         long matchingguid = (Long)GroupDungeonCache.matchingGuidMap.get((long)roomId);
         this.logger.error("readyRequest==matchingguid=={}", matchingguid);
         RES_PARTY_MEMBER_BATTLE_SERVER_CONNECT res_party_member_battle_server_connect = new RES_PARTY_MEMBER_BATTLE_SERVER_CONNECT();
         res_party_member_battle_server_connect.charguid = charguid;
         res_party_member_battle_server_connect.matchingguid = matchingguid;
         byte[] body = SerializerHelper.protobufEncode(res_party_member_battle_server_connect);
         ByteBuffUtil.writeBytes(out, body);
         byte[] relayDataBytes1 = out.array();
         if (relayDataBytes1.length > 33) {
            byte[] res = new byte[33];
            System.arraycopy(relayDataBytes1, 0, res, 0, 33);
            relayData1.data = res;
            relayData1.sendTime = 0L;
            frameInfo1.relayDataList.add(relayData1);
            this.logger.error("readyRequest==relayDataBytes1=={}=={}", ParseUtil.bytesToHexStr(res), charguid);
         } else {
            relayData1.data = relayDataBytes1;
            relayData1.sendTime = 0L;
            frameInfo1.relayDataList.add(relayData1);
            this.logger.error("readyRequest==relayDataBytes1=={}=={}", ParseUtil.bytesToHexStr(relayDataBytes1), charguid);
         }
      }

      frameNoti.frameInfoList.add(frameInfo1);
      Global.putFrameAndInc(roomId, frameInfo1);
      this.logger.error("readyRequest==ROOM_FRAME_MAP=={}", JSON.toJSONString(Global.ROOM_FRAME_MAP));
      MessagePusher.pushMessage(session, frameNoti);
   }
}
