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
                return adaptLoginResponse(body);
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
}
