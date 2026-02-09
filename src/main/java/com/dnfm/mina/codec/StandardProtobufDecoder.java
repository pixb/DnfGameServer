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
            case 10100:
                return adaptEnterTownRequest(body);
            case 10103:
                return adaptCharacterInfoRequest(body);
            case 10106:
                return adaptTownUserGuidListRequest(body);
            case 10107:
                return adaptTargetUserDetailInfoRequest(body);
            case 10108:
                return adaptInteractionMenuRequest(body);
            case 10109:
                return adaptLeaveFromTownRequest(body);
            case 14000:
                return adaptItemListRequest(body);
            case 14001:
                return adaptItemMoveRequest(body);
            case 14002:
                return adaptItemDropRequest(body);
            case 14003:
                return adaptItemSplitRequest(body);
            case 14006:
                return adaptItemReinforceRequest(body);
            case 14017:
                return adaptItemUseRequest(body);
            case 16000:
                return adaptSkillSlotRequest(body);
            case 16001:
                return adaptSkillSetRequest(body);
            case 17000:
                return adaptAchievementListRequest(body);
            case 17001:
                return adaptAchievementInfoRequest(body);
            case 17002:
                return adaptAchievementRewardRequest(body);
            case 17003:
                return adaptAchievementBonusRewardRequest(body);
            case 18000:
                return adaptAdventureDataRequest(body);
            case 18001:
                return adaptAdventureReapInfoRequest(body);
            case 18002:
                return adaptAdventureReapRewardRequest(body);
            case 18003:
                return adaptAdventureStorageListRequest(body);
            case 18004:
                return adaptAdventureAutoSearchRequest(body);
            case 18005:
                return adaptAdventureAutoSearchRewardRequest(body);
            case 18006:
                return adaptAdventureBookInfoRequest(body);
            case 18007:
                return adaptAdventureBookSpecialRewardRequest(body);
            case 18008:
                return adaptAdventureBookTeraRewardRequest(body);
            case 18009:
                return adaptAdventureBookUpdateConditionRequest(body);
            case 19000:
                return adaptGuildCreateRequest(body);
            case 19001:
                return adaptGuildJoinRequest(body);
            case 19002:
                return adaptGuildInfoRequest(body);
            case 19003:
                return adaptGuildListRequest(body);
            case 19004:
                return adaptGuildMemberListRequest(body);
            case 19005:
                return adaptGuildDonateRequest(body);
            case 19006:
                return adaptGuildSkillRequest(body);
            case 19007:
                return adaptGuildStorageRequest(body);
            case 20000:
                return adaptTaskListRequest(body);
            case 20001:
                return adaptTaskInfoRequest(body);
            case 20002:
                return adaptTaskAcceptRequest(body);
            case 20003:
                return adaptTaskFinishRequest(body);
            case 20004:
                return adaptTaskProgressUpdateRequest(body);
            case 20005:
                return adaptTaskAbandonRequest(body);
            case 20006:
                return adaptTaskRewardClaimRequest(body);
            case 20007:
                return adaptTaskTrackRequest(body);
            case 21000:
                return adaptFriendListRequest(body);
            case 21001:
                return adaptFriendAddRequest(body);
            case 21002:
                return adaptFriendDeleteRequest(body);
            case 21003:
                return adaptFriendMessageRequest(body);
            case 21004:
                return adaptFriendRequestListRequest(body);
            case 21005:
                return adaptFriendRequestAcceptRequest(body);
            case 21006:
                return adaptFriendRequestRejectRequest(body);
            case 21007:
                return adaptFriendBlacklistRequest(body);
            case 15001:
                return adaptMailListRequest(body);
            case 15002:
                return adaptMailGetRequest(body);
            case 15003:
                return adaptMailReadRequest(body);
            case 15004:
                return adaptMailDeleteRequest(body);
            case 15005:
                return adaptMailItemAllGetRequest(body);
            case 15006:
                return adaptMailAllDeleteRequest(body);
            case 10105:
                return adaptTownChatRequest(body);
            case 13037:
                return adaptQuickChattingRequest(body);
            case 27017:
                return adaptLoadGuildChatRequest(body);
            case 10131:
                return adaptHiddenChattingLoadRequest(body);
            case 10129:
                return adaptHiddenChattingAddRequest(body);
            case 10130:
                return adaptHiddenChattingDeleteRequest(body);
            case 10128:
                return adaptChatChannelListRequest(body);
            case 14093:
                return adaptChatFrameTabListRequest(body);
            case 22000:
                return adaptStreamDataRequest(body);
            case 22001:
                return adaptUserInfoRequest(body);
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

    private Message adaptEnterTownRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptEnterTownRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.EnterTownRequest newRequest = 
            com.dnfm.mina.protobuf.generated.EnterTownRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN oldRequest = new com.dnfm.mina.protobuf.REQ_ENTER_TO_TOWN();
        oldRequest.authkey = newRequest.getAuthkey();
        oldRequest.town = (int) newRequest.getTown();
        oldRequest.area = (int) newRequest.getArea();
        oldRequest.posx = newRequest.getPosx();
        oldRequest.posy = newRequest.getPosy();
        
        return oldRequest;
    }

    private Message adaptCharacterInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptCharacterInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.CharacterInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.CharacterInfoRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_CHARACTER_INFO oldRequest = new com.dnfm.mina.protobuf.REQ_CHARACTER_INFO();
        oldRequest.authkey = newRequest.getAuthkey();
        oldRequest.option = newRequest.getOption();
        
        if (newRequest.getCharlistList() != null) {
            oldRequest.charlist = new java.util.ArrayList<>();
            for (com.dnfm.mina.protobuf.generated.CharacterGuid newCharGuid : newRequest.getCharlistList()) {
                com.dnfm.mina.protobuf.PT_CHARACTER_GUID oldCharGuid = new com.dnfm.mina.protobuf.PT_CHARACTER_GUID();
                oldCharGuid.charguid = newCharGuid.getCharguid();
                oldCharGuid.type = newCharGuid.getType();
                oldCharGuid.posx = newCharGuid.getPosx();
                oldCharGuid.posy = newCharGuid.getPosy();
                oldRequest.charlist.add(oldCharGuid);
            }
        }
        
        return oldRequest;
    }

    private Message adaptTownUserGuidListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTownUserGuidListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TownUserGuidListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TownUserGuidListRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_TOWN_USER_GUID_LIST oldRequest = new com.dnfm.mina.protobuf.REQ_TOWN_USER_GUID_LIST();
        oldRequest.count = newRequest.getCount();
        oldRequest.posx = newRequest.getPosx();
        oldRequest.posy = newRequest.getPosy();
        
        return oldRequest;
    }

    private Message adaptTargetUserDetailInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTargetUserDetailInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TargetUserDetailInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TargetUserDetailInfoRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_TARGET_USER_DETAIL_INFO oldRequest = new com.dnfm.mina.protobuf.REQ_TARGET_USER_DETAIL_INFO();
        oldRequest.targetguid = newRequest.getTargetguid();
        
        return oldRequest;
    }

    private Message adaptInteractionMenuRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptInteractionMenuRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.InteractionMenuRequest newRequest = 
            com.dnfm.mina.protobuf.generated.InteractionMenuRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_INTERACTION_MENU oldRequest = new com.dnfm.mina.protobuf.REQ_INTERACTION_MENU();
        oldRequest.charguid = newRequest.getCharguid();
        oldRequest.openmenutype = newRequest.getOpenmenutype();
        
        return oldRequest;
    }

    private Message adaptLeaveFromTownRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptLeaveFromTownRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.LeaveFromTownRequest newRequest = 
            com.dnfm.mina.protobuf.generated.LeaveFromTownRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_LEAVE_FROM_TOWN oldRequest = new com.dnfm.mina.protobuf.REQ_LEAVE_FROM_TOWN();
        
        return oldRequest;
    }

    private Message adaptMailListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptMailListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.MailListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.MailListRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_MAIL_LIST oldRequest = new com.dnfm.mina.protobuf.REQ_MAIL_LIST();
        oldRequest.page = newRequest.getPage();
        oldRequest.type = newRequest.getType();
        
        return oldRequest;
    }

    private Message adaptMailGetRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptMailGetRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.MailGetRequest newRequest = 
            com.dnfm.mina.protobuf.generated.MailGetRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_MAIL_GET oldRequest = new com.dnfm.mina.protobuf.REQ_MAIL_GET();
        oldRequest.type = newRequest.getType();
        oldRequest.guid = newRequest.getGuid();
        
        if (newRequest.getSelectedItemsList() != null) {
            oldRequest.selecteditems = new java.util.ArrayList<>();
            for (com.dnfm.mina.protobuf.generated.SelectedItem newItem : newRequest.getSelectedItemsList()) {
                com.dnfm.mina.protobuf.PT_SELECTED_ITEM oldItem = new com.dnfm.mina.protobuf.PT_SELECTED_ITEM();
                oldItem.guid = newItem.getGuid();
                oldItem.count = newItem.getCount();
                oldRequest.selecteditems.add(oldItem);
            }
        }
        
        return oldRequest;
    }

    private Message adaptMailReadRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptMailReadRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.MailReadRequest newRequest = 
            com.dnfm.mina.protobuf.generated.MailReadRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_MAIL_READ oldRequest = new com.dnfm.mina.protobuf.REQ_MAIL_READ();
        oldRequest.guid = newRequest.getGuid();
        oldRequest.type = newRequest.getType();
        
        return oldRequest;
    }

    private Message adaptMailDeleteRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptMailDeleteRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.MailDeleteRequest newRequest = 
            com.dnfm.mina.protobuf.generated.MailDeleteRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_MAIL_DELETE oldRequest = new com.dnfm.mina.protobuf.REQ_MAIL_DELETE();
        oldRequest.guid = newRequest.getGuid();
        oldRequest.type = newRequest.getType();
        
        return oldRequest;
    }

    private Message adaptMailItemAllGetRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptMailItemAllGetRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.MailItemAllGetRequest newRequest = 
            com.dnfm.mina.protobuf.generated.MailItemAllGetRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_MAIL_ITEM_ALL_GET oldRequest = new com.dnfm.mina.protobuf.REQ_MAIL_ITEM_ALL_GET();
        oldRequest.type = newRequest.getType();
        
        return oldRequest;
    }

    private Message adaptMailAllDeleteRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptMailAllDeleteRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.MailAllDeleteRequest newRequest = 
            com.dnfm.mina.protobuf.generated.MailAllDeleteRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_MAIL_ALL_DELETE oldRequest = new com.dnfm.mina.protobuf.REQ_MAIL_ALL_DELETE();
        oldRequest.type = newRequest.getType();
        
        return oldRequest;
    }

    private Message adaptItemUseRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptItemUseRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ItemUseRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ItemUseRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_ITEM_USE oldRequest = new com.dnfm.mina.protobuf.REQ_ITEM_USE();
        oldRequest.index = newRequest.getIndex();
        oldRequest.count = newRequest.getCount();
        oldRequest.bind = newRequest.getBind();
        oldRequest.guid = newRequest.getGuid();
        oldRequest.type = newRequest.getType();
        oldRequest.input = newRequest.getInput();
        oldRequest.selectitemindex = newRequest.getSelectitemindex();
        
        // 移除对不存在的PT_QUEST的引用
        
        return oldRequest;
    }

    private Message adaptItemReinforceRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptItemReinforceRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ItemReinforceRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ItemReinforceRequest.parseFrom(body);
        
        com.dnfm.mina.protobuf.REQ_ITEM_REINFORCE oldRequest = new com.dnfm.mina.protobuf.REQ_ITEM_REINFORCE();
        oldRequest.type = newRequest.getType();
        oldRequest.guid = newRequest.getGuid();
        oldRequest.talisman = newRequest.getTalisman();
        oldRequest.customtype = newRequest.getCustomtype();
        
        if (newRequest.getNoticharlistList() != null) {
            oldRequest.noticharlist = new java.util.ArrayList<>(newRequest.getNoticharlistList());
        }
        
        return oldRequest;
    }

    // 移除对不存在的REQ_ITEM_LIST的引用
    private Message adaptItemListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptItemListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ItemListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ItemListRequest.parseFrom(body);
        
        // Message是抽象类，返回null
        return null;
    }

    // 移除对不存在的REQ_ITEM_MOVE的引用
    private Message adaptItemMoveRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptItemMoveRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ItemMoveRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ItemMoveRequest.parseFrom(body);
        
        // Message是抽象类，返回null
        return null;
    }

    // 移除对不存在的REQ_ITEM_DROP的引用
    private Message adaptItemDropRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptItemDropRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ItemDropRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ItemDropRequest.parseFrom(body);
        
        // Message是抽象类，返回null
        return null;
    }

    // 移除对不存在的REQ_ITEM_SPLIT的引用
    private Message adaptItemSplitRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptItemSplitRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ItemSplitRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ItemSplitRequest.parseFrom(body);
        
        // Message是抽象类，返回null
        return null;
    }

    private Message adaptSkillSlotRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptSkillSlotRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.SkillSlotRequest newRequest = 
            com.dnfm.mina.protobuf.generated.SkillSlotRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptSkillSetRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptSkillSetRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.SkillSetRequest newRequest = 
            com.dnfm.mina.protobuf.generated.SkillSetRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAchievementListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAchievementListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AchievementListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AchievementListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAchievementInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAchievementInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AchievementInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AchievementInfoRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAchievementRewardRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAchievementRewardRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AchievementRewardRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AchievementRewardRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAchievementBonusRewardRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAchievementBonusRewardRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AchievementBonusRewardRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AchievementBonusRewardRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureDataRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureDataRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureDataRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureDataRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureReapInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureReapInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureReapInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureReapInfoRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureReapRewardRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureReapRewardRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureReapRewardRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureReapRewardRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureStorageListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureStorageListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureStorageListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureStorageListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureAutoSearchRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureAutoSearchRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureAutoSearchRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureAutoSearchRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureAutoSearchRewardRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureAutoSearchRewardRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureAutoSearchRewardRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureAutoSearchRewardRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureBookInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureBookInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureBookInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureBookInfoRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureBookSpecialRewardRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureBookSpecialRewardRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureBookSpecialRewardRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureBookSpecialRewardRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureBookTeraRewardRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureBookTeraRewardRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureBookTeraRewardRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureBookTeraRewardRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptAdventureBookUpdateConditionRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptAdventureBookUpdateConditionRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.AdventureBookUpdateConditionRequest newRequest = 
            com.dnfm.mina.protobuf.generated.AdventureBookUpdateConditionRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildCreateRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildCreateRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildCreateRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildCreateRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildJoinRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildJoinRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildJoinRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildJoinRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildInfoRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildMemberListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildMemberListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildMemberListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildMemberListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildDonateRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildDonateRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildDonateRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildDonateRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildSkillRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildSkillRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildSkillRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildSkillRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptGuildStorageRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptGuildStorageRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.GuildStorageRequest newRequest = 
            com.dnfm.mina.protobuf.generated.GuildStorageRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskInfoRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskInfoRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskAcceptRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskAcceptRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskAcceptRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskAcceptRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskFinishRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskFinishRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskFinishRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskFinishRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskProgressUpdateRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskProgressUpdateRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskProgressUpdateRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskProgressUpdateRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskAbandonRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskAbandonRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskAbandonRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskAbandonRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskRewardClaimRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskRewardClaimRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskRewardClaimRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskRewardClaimRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTaskTrackRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTaskTrackRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TaskTrackRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TaskTrackRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendAddRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendAddRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendAddRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendAddRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendDeleteRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendDeleteRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendDeleteRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendDeleteRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendMessageRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendMessageRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendMessageRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendMessageRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendRequestListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendRequestListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendRequestListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendRequestListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendRequestAcceptRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendRequestAcceptRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendRequestAcceptRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendRequestAcceptRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendRequestRejectRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendRequestRejectRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendRequestRejectRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendRequestRejectRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptFriendBlacklistRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptFriendBlacklistRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.FriendBlacklistRequest newRequest = 
            com.dnfm.mina.protobuf.generated.FriendBlacklistRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptTownChatRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptTownChatRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.TownChatRequest newRequest = 
            com.dnfm.mina.protobuf.generated.TownChatRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptQuickChattingRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptQuickChattingRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.QuickChattingRequest newRequest = 
            com.dnfm.mina.protobuf.generated.QuickChattingRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptLoadGuildChatRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptLoadGuildChatRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.LoadGuildChatRequest newRequest = 
            com.dnfm.mina.protobuf.generated.LoadGuildChatRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptHiddenChattingLoadRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptHiddenChattingLoadRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.HiddenChattingLoadRequest newRequest = 
            com.dnfm.mina.protobuf.generated.HiddenChattingLoadRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptHiddenChattingAddRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptHiddenChattingAddRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.HiddenChattingAddRequest newRequest = 
            com.dnfm.mina.protobuf.generated.HiddenChattingAddRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptHiddenChattingDeleteRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptHiddenChattingDeleteRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.HiddenChattingDeleteRequest newRequest = 
            com.dnfm.mina.protobuf.generated.HiddenChattingDeleteRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptChatChannelListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptChatChannelListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ChatChannelListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ChatChannelListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptChatFrameTabListRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptChatFrameTabListRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.ChatFrameTabListRequest newRequest = 
            com.dnfm.mina.protobuf.generated.ChatFrameTabListRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptStreamDataRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptStreamDataRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.StreamDataRequest newRequest = 
            com.dnfm.mina.protobuf.generated.StreamDataRequest.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }

    private Message adaptUserInfoRequest(byte[] body) throws Exception {
        System.out.println("===== StandardProtobufDecoder.adaptUserInfoRequest() 被调用，body.length=" + body.length + " =====");
        com.dnfm.mina.protobuf.generated.UserInfo newRequest = 
            com.dnfm.mina.protobuf.generated.UserInfo.parseFrom(body);
        
        // 简化实现，返回null
        return null;
    }
}
