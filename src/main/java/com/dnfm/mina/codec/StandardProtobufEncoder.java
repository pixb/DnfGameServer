package com.dnfm.mina.codec;

import com.dnfm.game.utils.ByteBuffUtil;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.protobuf.generated.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardProtobufEncoder implements ProtocolEncoder {
    Logger logger = LoggerFactory.getLogger(StandardProtobufEncoder.class);

    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        System.out.println("===== StandardProtobufEncoder.encode() 被调用，message=" + message + " =====");
        if (!(message instanceof Message)) {
            return;
        }

        Message msg = (Message) message;
        int moduleId = msg.getModule();
        
        System.out.println("===== StandardProtobufEncoder.encode() moduleId=" + moduleId + " =====");
        
        byte[] body = encodeMessage(moduleId, msg);
        
        int totalLen = body.length + 8;
        
        IoBuffer buffer = IoBuffer.allocate(totalLen);
        buffer.order(java.nio.ByteOrder.LITTLE_ENDIAN);
        buffer.putShort((short) totalLen);
        buffer.putShort((short) moduleId);
        buffer.put((byte) 1);
        buffer.put((byte) (msg.transId != null ? msg.transId : 0));
        buffer.putShort((short) 0);
        buffer.put(body);
        buffer.flip();
        
        this.logger.info("StandardProtobuf SENDMSG=={}=={}", moduleId, msg.getClass().getSimpleName());
        protocolEncoderOutput.write(buffer);
    }
    
    public void dispose(IoSession ioSession) throws Exception {
    }
    
    private byte[] encodeMessage(int moduleId, Message msg) throws Exception {
        switch (moduleId) {
            case 10001:
                return adaptLoginResponse(msg);
            default:
                throw new Exception("Unknown module ID: " + moduleId);
        }
    }
    
    private byte[] adaptLoginResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_LOGIN oldResponse = 
            (com.dnfm.mina.protobuf.RES_LOGIN) msg;
        
        com.dnfm.mina.protobuf.generated.LoginResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.LoginResponse.newBuilder();
        builder.setError(oldResponse.error);
        builder.setAuthkey(oldResponse.authkey);
        builder.setAccountkey(oldResponse.accountkey);
        builder.setEncrypt(oldResponse.encrypt);
        builder.setServertime(oldResponse.servertime);
        builder.setLocaltime(oldResponse.localtime);
        builder.setAuthority(oldResponse.authority);
        builder.setKey(oldResponse.key);
        builder.setWorldid(oldResponse.worldid);
        
        if (oldResponse.channel != null) {
            for (com.dnfm.mina.protobuf.PT_CHANNEL_INFO oldChannel : oldResponse.channel) {
                com.dnfm.mina.protobuf.generated.ChannelInfo.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.ChannelInfo.newBuilder();
                channelBuilder.setWorld(oldChannel.world);
                channelBuilder.setChannel(oldChannel.channel);
                channelBuilder.setIp(oldChannel.ip);
                channelBuilder.setPort(oldChannel.port);
                channelBuilder.setPriority(oldChannel.priority);
                builder.addChannel(channelBuilder.build());
            }
        }
        
        if (oldResponse.seeds != null) {
            builder.addAllSeeds(oldResponse.seeds);
        }
        
        if (oldResponse.intrudeinfo != null) {
            com.dnfm.mina.protobuf.PT_INTRUDE_INFO oldIntrude = oldResponse.intrudeinfo;
            com.dnfm.mina.protobuf.generated.IntrudeInfo.Builder intrudeBuilder = 
                com.dnfm.mina.protobuf.generated.IntrudeInfo.newBuilder();
            intrudeBuilder.setIntrude(oldIntrude.intrude);
            intrudeBuilder.setName(oldIntrude.name);
            intrudeBuilder.setGuid(oldIntrude.guid);
            
            if (oldIntrude.channel != null) {
                com.dnfm.mina.protobuf.PT_CHANNEL_INFO oldChannel = oldIntrude.channel;
                com.dnfm.mina.protobuf.generated.ChannelInfo.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.ChannelInfo.newBuilder();
                channelBuilder.setWorld(oldChannel.world);
                channelBuilder.setChannel(oldChannel.channel);
                channelBuilder.setIp(oldChannel.ip);
                channelBuilder.setPort(oldChannel.port);
                channelBuilder.setPriority(oldChannel.priority);
                intrudeBuilder.setChannel(channelBuilder.build());
            }
            
            if (oldIntrude.partymembers != null) {
                for (com.dnfm.mina.protobuf.PT_INTRUDE_MEMBER_INFO oldMember : oldIntrude.partymembers) {
                    com.dnfm.mina.protobuf.generated.IntrudeMemberInfo.Builder memberBuilder = 
                        com.dnfm.mina.protobuf.generated.IntrudeMemberInfo.newBuilder();
                    memberBuilder.setName(oldMember.name);
                    memberBuilder.setLevel(oldMember.level);
                    memberBuilder.setJob(oldMember.job);
                    memberBuilder.setGrowtype(oldMember.growtype);
                    memberBuilder.setSecgrowtype(oldMember.secgrowtype);
                    intrudeBuilder.addPartyMembers(memberBuilder.build());
                }
            }
            
            intrudeBuilder.setRpGuid(oldIntrude.rpguid);
            builder.setIntrudeinfo(intrudeBuilder.build());
        }
        
        return builder.build().toByteArray();
    }
}
