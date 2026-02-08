package com.dnfm.mina.codec;

import com.dnfm.game.utils.ByteBuffUtil;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.generated.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardProtobufDecoder extends CumulativeProtocolDecoder {
    static final int MIN_PACKET_LEN = 8;
    Logger logger = LoggerFactory.getLogger(StandardProtobufDecoder.class);

    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        System.out.println("===== StandardProtobufDecoder.doDecode() 被调用 =====");
        if (ioBuffer.remaining() < 8) {
            return false;
        } else {
            ioBuffer.mark();
            ioBuffer = ioBuffer.order(java.nio.ByteOrder.LITTLE_ENDIAN);
            
            if (ioBuffer.remaining() < 8) {
                this.logger.error("小于MIN_PACKET_LEN");
                ioBuffer.reset();
                return false;
            } else {
                int totalLen = ByteBuffUtil.readUnsignedShort(ioBuffer);
                int remaining = ioBuffer.remaining();
                
                System.out.println("===== StandardProtobufDecoder.doDecode() totalLen=" + totalLen + ", remaining=" + remaining + " =====");
                
                if (remaining < totalLen - 2) {
                    ioBuffer.reset();
                    return false;
                } else {
                    int moduleId = ByteBuffUtil.readUnsignedShort(ioBuffer);
                    byte seq = ByteBuffUtil.readByte(ioBuffer);
                    Integer transactionId = Integer.valueOf(ByteBuffUtil.readByte(ioBuffer));
                    ByteBuffUtil.readUnsignedShort(ioBuffer);
                    
                    System.out.println("===== StandardProtobufDecoder.doDecode() moduleId=" + moduleId + " =====");
                    
                    if (totalLen > 8) {
                        byte[] body = new byte[totalLen - 8];
                        ioBuffer.get(body);
                        
                        try {
                            Message msg = decodeMessage(moduleId, body);
                            if (transactionId != 0) {
                                msg.transId = transactionId;
                            }
                            
                            this.logger.info("StandardProtobuf RECVMSG=={}=={}", moduleId, msg.getClass().getSimpleName());
                            protocolDecoderOutput.write(msg);
                        } catch (Exception e) {
                            this.logger.error("StandardProtobuf DECODE_ERR=={}=={}", moduleId, e.getMessage());
                        }
                    } else {
                        Message msg = decodeMessage(moduleId, new byte[0]);
                        if (transactionId != 0) {
                            msg.transId = transactionId;
                        }
                        protocolDecoderOutput.write(msg);
                    }
                    
                    return true;
                }
            }
        }
    }
    
    private Message decodeMessage(int moduleId, byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.decodeMessage() 被调用，moduleId=" + moduleId + " =====");
        switch (moduleId) {
            case 10000:
                return adaptLoginRequest(body);
            case 10001:
                return adaptStandbyRequest(body);
            case 10006:
                return adaptPingRequest(body);
            case 10002:
                return adaptCharacterListRequest(body);
            case 10003:
                return adaptCreateCharacterRequest(body);
            case 10004:
                return adaptRemoveCharacterRequest(body);
            case 10005:
                return adaptStartGameRequest(body);
            case 10007:
                return adaptExitCharacterRequest(body);
            case 10008:
                return adaptChannelListRequest(body);
            case 10009:
                return adaptAuthkeyRefreshRequest(body);
            case 10011:
                return adaptEnterChannelRequest(body);
            case 10012:
                return adaptPlatformProfileUpdateRequest(body);
            case 10014:
                return adaptConnectBattleServerRequest(body);
            case 10017:
                return adaptIdipProhibitListRequest(body);
            case 10031:
                return adaptLoadServerSimpleDataRequest(body);
            case 10032:
                return adaptSaveServerSimpleDataRequest(body);
            default:
                throw new Exception("Unknown module ID: " + moduleId);
        }
    }
    
    private Message adaptLoginRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptLoginRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.LoginRequest newRequest = 
            com.dnfm.mina.protobuf.generated.LoginRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptLoginRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_LOGIN oldRequest = new com.dnfm.mina.protobuf.REQ_LOGIN();
        oldRequest.openid = newRequest.getOpenid();
        oldRequest.type = (int) newRequest.getType();
        oldRequest.token = newRequest.getToken();
        oldRequest.platID = (int) newRequest.getPlatID();
        oldRequest.clientIP = newRequest.getClientIP();
        oldRequest.version = newRequest.getVersion();
        oldRequest.friendopenid = newRequest.getFriendopenid();
        oldRequest.cancelunregist = (int) newRequest.getCancelunregist();
        oldRequest.countrycode = newRequest.getCountrycode();
        oldRequest.toyplatid = newRequest.getToyplatid();
        oldRequest.agetype = newRequest.getAgetype();
        
        System.out.println("===== StandardProtobufDecoder.adaptLoginRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptLoginResponse(byte[] body) throws Exception {
        com.dnfm.mina.protobuf.generated.LoginResponse newResponse = 
            com.dnfm.mina.protobuf.generated.LoginResponse.parseFrom(body);
        
        com.dnfm.mina.protobuf.RES_LOGIN oldResponse = new com.dnfm.mina.protobuf.RES_LOGIN();
        oldResponse.error = newResponse.getError();
        oldResponse.authkey = newResponse.getAuthkey();
        oldResponse.accountkey = newResponse.getAccountkey();
        oldResponse.encrypt = newResponse.getEncrypt();
        oldResponse.servertime = newResponse.getServertime();
        oldResponse.localtime = newResponse.getLocaltime();
        oldResponse.authority = (int) newResponse.getAuthority();
        oldResponse.key = newResponse.getKey();
        oldResponse.worldid = (int) newResponse.getWorldid();
        
        if (newResponse.getChannelList() != null) {
            oldResponse.channel = new java.util.ArrayList<>();
            for (com.dnfm.mina.protobuf.generated.ChannelInfo newChannel : newResponse.getChannelList()) {
                com.dnfm.mina.protobuf.PT_CHANNEL_INFO oldChannel = new com.dnfm.mina.protobuf.PT_CHANNEL_INFO();
                oldChannel.world = newChannel.getWorld();
                oldChannel.channel = newChannel.getChannel();
                oldChannel.ip = newChannel.getIp();
                oldChannel.port = newChannel.getPort();
                oldChannel.priority = newChannel.getPriority();
                oldResponse.channel.add(oldChannel);
            }
        }
        
        if (newResponse.getSeedsList() != null) {
            oldResponse.seeds = new java.util.ArrayList<>(newResponse.getSeedsList());
        }
        
        if (newResponse.hasIntrudeinfo()) {
            com.dnfm.mina.protobuf.generated.IntrudeInfo newIntrude = newResponse.getIntrudeinfo();
            com.dnfm.mina.protobuf.PT_INTRUDE_INFO oldIntrude = new com.dnfm.mina.protobuf.PT_INTRUDE_INFO();
            oldIntrude.intrude = newIntrude.getIntrude();
            oldIntrude.name = newIntrude.getName();
            oldIntrude.guid = newIntrude.getGuid();
            
            if (newIntrude.hasChannel()) {
                com.dnfm.mina.protobuf.generated.ChannelInfo newChannel = newIntrude.getChannel();
                com.dnfm.mina.protobuf.PT_CHANNEL_INFO oldChannel = new com.dnfm.mina.protobuf.PT_CHANNEL_INFO();
                oldChannel.world = newChannel.getWorld();
                oldChannel.channel = newChannel.getChannel();
                oldChannel.ip = newChannel.getIp();
                oldChannel.port = newChannel.getPort();
                oldChannel.priority = newChannel.getPriority();
                oldIntrude.channel = oldChannel;
            }
            
            if (newIntrude.getPartyMembersList() != null) {
                oldIntrude.partymembers = new java.util.ArrayList<>();
                for (com.dnfm.mina.protobuf.generated.IntrudeMemberInfo newMember : newIntrude.getPartyMembersList()) {
                    com.dnfm.mina.protobuf.PT_INTRUDE_MEMBER_INFO oldMember = new com.dnfm.mina.protobuf.PT_INTRUDE_MEMBER_INFO();
                    oldMember.name = newMember.getName();
                    oldMember.level = newMember.getLevel();
                    oldMember.job = newMember.getJob();
                    oldMember.growtype = newMember.getGrowtype();
                    oldMember.secgrowtype = newMember.getSecgrowtype();
                    oldIntrude.partymembers.add(oldMember);
                }
            }
            
            oldIntrude.rpguid = newIntrude.getRpGuid();
            oldResponse.intrudeinfo = oldIntrude;
        }
        
        return oldResponse;
    }
    
    private Message adaptPingRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptPingRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.PingRequest newRequest = 
            com.dnfm.mina.protobuf.generated.PingRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptPingRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_PING oldRequest = new com.dnfm.mina.protobuf.REQ_PING();
        
        System.out.println("===== StandardProtobufDecoder.adaptPingRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptCharacterListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptCharacterListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.CharacterListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.CharacterListRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptCharacterListRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_CHARAC_LIST oldRequest = new com.dnfm.mina.protobuf.REQ_CHARAC_LIST();
        
        System.out.println("===== StandardProtobufDecoder.adaptCharacterListRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptCreateCharacterRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptCreateCharacterRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.CreateCharacterRequest newRequest = 
            com.dnfm.mina.protobuf.generated.CreateCharacterRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptCreateCharacterRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_CREATE_CHARACTER oldRequest = new com.dnfm.mina.protobuf.REQ_CREATE_CHARACTER();
        oldRequest.job = newRequest.getJob();
        oldRequest.name = newRequest.getName();
        
        System.out.println("===== StandardProtobufDecoder.adaptCreateCharacterRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptChannelListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptChannelListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ChannelListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ChannelListRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptChannelListRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_CHANNEL_LIST oldRequest = new com.dnfm.mina.protobuf.REQ_CHANNEL_LIST();
        oldRequest.type = newRequest.getType();
        oldRequest.worldid = newRequest.getWorldid();
        
        System.out.println("===== StandardProtobufDecoder.adaptChannelListRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptEnterChannelRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptEnterChannelRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.EnterChannelRequest newRequest = 
            com.dnfm.mina.protobuf.generated.EnterChannelRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptEnterChannelRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_ENTER_CHANNEL oldRequest = new com.dnfm.mina.protobuf.REQ_ENTER_CHANNEL();
        oldRequest.openid = newRequest.getOpenid();
        oldRequest.charguid = newRequest.getCharguid();
        oldRequest.authkey = newRequest.getAuthkey();
        oldRequest.version = newRequest.getVersion();
        oldRequest.accesstoken = newRequest.getAccesstoken();
        oldRequest.launchinfo = newRequest.getLaunchinfo();
        oldRequest.dungeonguid = newRequest.getDungeonguid();
        oldRequest.registeredchannelid = newRequest.getRegisteredchannelid();
        oldRequest.installchannelid = newRequest.getInstallchannelid();
        oldRequest.isexternpackage = newRequest.getIsexternpackage();
        oldRequest.validusercheckcode = newRequest.getValidusercheckcode();
        oldRequest.toyPlatID = newRequest.getToyPlatID();
        oldRequest.countrycode = newRequest.getCountrycode();
        oldRequest.language = newRequest.getLanguage();
        oldRequest.adid = newRequest.getAdid();
        oldRequest.idfv = newRequest.getIdfv();
        oldRequest.isadult = newRequest.getIsadult();
        
        if (newRequest.hasDeviceinfo()) {
            com.dnfm.mina.protobuf.generated.ClientInfo newClientInfo = newRequest.getDeviceinfo();
            com.dnfm.mina.protobuf.PT_CLIENTINFO oldClientInfo = new com.dnfm.mina.protobuf.PT_CLIENTINFO();
            oldClientInfo.platID = (int) newClientInfo.getPlatID();
            oldClientInfo.deviceSoft = newClientInfo.getDeviceSoft();
            oldClientInfo.deviceHard = newClientInfo.getDeviceHard();
            oldClientInfo.teleOper = newClientInfo.getTeleOper();
            oldClientInfo.network = newClientInfo.getNetwork();
            oldClientInfo.scrWidth = newClientInfo.getScrWidth();
            oldClientInfo.scrHeight = newClientInfo.getScrHeight();
            oldClientInfo.density = newClientInfo.getDensity();
            oldClientInfo.cpu = newClientInfo.getCpu();
            oldClientInfo.memory = newClientInfo.getMemory();
            oldClientInfo.glRender = newClientInfo.getGlRender();
            oldClientInfo.glVersion = newClientInfo.getGlVersion();
            oldClientInfo.deviceID = newClientInfo.getDeviceID();
            oldClientInfo.clientIP = newClientInfo.getClientIP();
            oldClientInfo.type = (int) newClientInfo.getType();
            oldClientInfo.oAID = newClientInfo.getOAID();
            oldClientInfo.deviceLanguage = newClientInfo.getDeviceLanguage();
            oldClientInfo.deviceUTC = newClientInfo.getDeviceUTC();
            
            if (newClientInfo.getBuildType() != com.dnfm.mina.protobuf.generated.ClientBuildType.NONE) {
                switch (newClientInfo.getBuildType()) {
                    case NONE:
                        oldClientInfo.buildType = com.dnfm.mina.protobuf.ENUM_CLIENT_BUILD_TYPE.T.NONE;
                        break;
                    case TEEN:
                        oldClientInfo.buildType = com.dnfm.mina.protobuf.ENUM_CLIENT_BUILD_TYPE.T.TEEN;
                        break;
                    case ADULT:
                        oldClientInfo.buildType = com.dnfm.mina.protobuf.ENUM_CLIENT_BUILD_TYPE.T.ADULT;
                        break;
                }
            }
            
            oldRequest.deviceinfo = oldClientInfo;
        }
        
        System.out.println("===== StandardProtobufDecoder.adaptEnterChannelRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptStandbyRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptStandbyRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.StandbyRequest newRequest = 
            com.dnfm.mina.protobuf.generated.StandbyRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptStandbyRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_STANDBY oldRequest = new com.dnfm.mina.protobuf.REQ_STANDBY();
        
        System.out.println("===== StandardProtobufDecoder.adaptStandbyRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptRemoveCharacterRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptRemoveCharacterRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.DeleteCharacterRequest newRequest = 
            com.dnfm.mina.protobuf.generated.DeleteCharacterRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptRemoveCharacterRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_REMOVE_CHARACTER oldRequest = new com.dnfm.mina.protobuf.REQ_REMOVE_CHARACTER();
        oldRequest.charguid = newRequest.getCharGuid();
        
        System.out.println("===== StandardProtobufDecoder.adaptRemoveCharacterRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptStartGameRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptStartGameRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.StartGameRequest newRequest = 
            com.dnfm.mina.protobuf.generated.StartGameRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptStartGameRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_START_GAME oldRequest = new com.dnfm.mina.protobuf.REQ_START_GAME();
        oldRequest.charguid = newRequest.getCharguid();
        oldRequest.dungeonguid = newRequest.getDungeonguid();
        oldRequest.authkey = newRequest.getAuthkey();
        oldRequest.accesstoken = newRequest.getAccesstoken();
        oldRequest.paytoken = newRequest.getPaytoken();
        oldRequest.town = (int) newRequest.getTown();
        oldRequest.area = (int) newRequest.getArea();
        oldRequest.posx = newRequest.getPosx();
        oldRequest.posy = newRequest.getPosy();
        oldRequest.partyguid = newRequest.getPartyguid();
        
        if (newRequest.getRequestList() != null) {
            oldRequest.request = new java.util.ArrayList<>();
            for (com.dnfm.mina.protobuf.generated.ProtocolTransaction newTransaction : newRequest.getRequestList()) {
                com.dnfm.mina.protobuf.PT_PROTOCOL_TRANSACTION oldTransaction = new com.dnfm.mina.protobuf.PT_PROTOCOL_TRANSACTION();
                oldTransaction.protocol = newTransaction.getProtocol();
                oldTransaction.transactionid = newTransaction.getTransactionid();
                oldRequest.request.add(oldTransaction);
            }
        }
        
        System.out.println("===== StandardProtobufDecoder.adaptStartGameRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptExitCharacterRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptExitCharacterRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ExitCharacterRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ExitCharacterRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptExitCharacterRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_EXIT_CHARACTER oldRequest = new com.dnfm.mina.protobuf.REQ_EXIT_CHARACTER();
        oldRequest.world = newRequest.getWorld();
        oldRequest.channel = newRequest.getChannel();
        oldRequest.reservationtype = newRequest.getReservationtype();
        oldRequest.exittype = newRequest.getExittype();
        
        System.out.println("===== StandardProtobufDecoder.adaptExitCharacterRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptAuthkeyRefreshRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAuthkeyRefreshRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AuthkeyRefreshRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AuthkeyRefreshRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptAuthkeyRefreshRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_AUTHKEY_REFRESH oldRequest = new com.dnfm.mina.protobuf.REQ_AUTHKEY_REFRESH();
        oldRequest.authkey = newRequest.getAuthkey();
        
        System.out.println("===== StandardProtobufDecoder.adaptAuthkeyRefreshRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptPlatformProfileUpdateRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptPlatformProfileUpdateRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.PlatformProfileUpdateRequest newRequest = 
            com.dnfm.mina.protobuf.generated.PlatformProfileUpdateRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptPlatformProfileUpdateRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_PLATFORM_PROFILE_UPDATE oldRequest = new com.dnfm.mina.protobuf.REQ_PLATFORM_PROFILE_UPDATE();
        oldRequest.profileurl = newRequest.getProfileurl();
        oldRequest.profilename = newRequest.getProfilename();
        oldRequest.firstlocation = newRequest.getFirstlocation();
        oldRequest.secondlocation = newRequest.getSecondlocation();
        
        System.out.println("===== StandardProtobufDecoder.adaptPlatformProfileUpdateRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptConnectBattleServerRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptConnectBattleServerRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ConnectBattleServerRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ConnectBattleServerRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptConnectBattleServerRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_CONNECT_BATTLE_SERVER oldRequest = new com.dnfm.mina.protobuf.REQ_CONNECT_BATTLE_SERVER();
        oldRequest.authkey = newRequest.getAuthkey();
        oldRequest.openid = newRequest.getOpenid();
        oldRequest.world = newRequest.getWorld();
        oldRequest.channel = newRequest.getChannel();
        oldRequest.charguid = newRequest.getCharguid();
        oldRequest.type = newRequest.getType();
        oldRequest.dungeonguid = newRequest.getDungeonguid();
        
        System.out.println("===== StandardProtobufDecoder.adaptConnectBattleServerRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptIdipProhibitListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptIdipProhibitListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.IdipProhibitListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.IdipProhibitListRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptIdipProhibitListRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_IDIP_PROHIBIT_LIST oldRequest = new com.dnfm.mina.protobuf.REQ_IDIP_PROHIBIT_LIST();
        
        System.out.println("===== StandardProtobufDecoder.adaptIdipProhibitListRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptLoadServerSimpleDataRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptLoadServerSimpleDataRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.LoadServerSimpleDataRequest newRequest = 
            com.dnfm.mina.protobuf.generated.LoadServerSimpleDataRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptLoadServerSimpleDataRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_LOAD_SERVER_SIMPLE_DATA oldRequest = new com.dnfm.mina.protobuf.REQ_LOAD_SERVER_SIMPLE_DATA();
        
        if (newRequest.getListList() != null) {
            oldRequest.list = new java.util.ArrayList<>();
            for (com.dnfm.mina.protobuf.generated.LoadServerSimpleData newData : newRequest.getListList()) {
                com.dnfm.mina.protobuf.PT_LOAD_SERVER_SIMPLE_DATA oldData = new com.dnfm.mina.protobuf.PT_LOAD_SERVER_SIMPLE_DATA();
                oldData.type = newData.getType();
                oldData.enumvalue = newData.getEnumvalue();
                oldRequest.list.add(oldData);
            }
        }
        
        System.out.println("===== StandardProtobufDecoder.adaptLoadServerSimpleDataRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
    
    private Message adaptSaveServerSimpleDataRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptSaveServerSimpleDataRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.SaveServerSimpleDataRequest newRequest = 
            com.dnfm.mina.protobuf.generated.SaveServerSimpleDataRequest.parseFrom(body);
        
        System.out.println("===== StandardProtobufDecoder.adaptSaveServerSimpleDataRequest() newRequest=" + newRequest + " =====");
        
        com.dnfm.mina.protobuf.REQ_SAVE_SERVER_SIMPLE_DATA oldRequest = new com.dnfm.mina.protobuf.REQ_SAVE_SERVER_SIMPLE_DATA();
        oldRequest.type = newRequest.getType();
        oldRequest.enumvalue = newRequest.getEnumvalue();
        oldRequest.value = newRequest.getValue();
        
        System.out.println("===== StandardProtobufDecoder.adaptSaveServerSimpleDataRequest() oldRequest=" + oldRequest + " =====");
        
        return oldRequest;
    }
}
