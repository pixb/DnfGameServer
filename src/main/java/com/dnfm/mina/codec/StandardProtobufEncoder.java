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
            case 10000:
                return adaptLoginResponse(msg);
            case 10001:
                return adaptStandbyResponse(msg);
            case 10006:
                return adaptPingResponse(msg);
            case 10002:
                return adaptCharacterListResponse(msg);
            case 10003:
                return adaptCreateCharacterResponse(msg);
            case 10004:
                return adaptRemoveCharacterResponse(msg);
            case 10008:
                return adaptChannelListResponse(msg);
            default:
                throw new Exception("Unknown module ID: " + moduleId);
        }
    }
    
    private byte[] adaptLoginResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_LOGIN oldResponse = 
            (com.dnfm.mina.protobuf.RES_LOGIN) msg;
        
        com.dnfm.mina.protobuf.generated.LoginResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.LoginResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.authkey != null) {
            builder.setAuthkey(oldResponse.authkey);
        }
        if (oldResponse.accountkey != null) {
            builder.setAccountkey(oldResponse.accountkey);
        }
        if (oldResponse.encrypt != null) {
            builder.setEncrypt(oldResponse.encrypt);
        }
        if (oldResponse.servertime != null) {
            builder.setServertime(oldResponse.servertime);
        }
        if (oldResponse.localtime != null) {
            builder.setLocaltime(oldResponse.localtime);
        }
        if (oldResponse.authority != null) {
            builder.setAuthority(oldResponse.authority);
        }
        if (oldResponse.key != null) {
            builder.setKey(oldResponse.key);
        }
        if (oldResponse.worldid != null) {
            builder.setWorldid(oldResponse.worldid);
        }
        
        if (oldResponse.channel != null) {
            for (com.dnfm.mina.protobuf.PT_CHANNEL_INFO oldChannel : oldResponse.channel) {
                com.dnfm.mina.protobuf.generated.ChannelInfo.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.ChannelInfo.newBuilder();
                if (oldChannel.world != null) {
                    channelBuilder.setWorld(oldChannel.world);
                }
                if (oldChannel.channel != null) {
                    channelBuilder.setChannel(oldChannel.channel);
                }
                if (oldChannel.ip != null) {
                    channelBuilder.setIp(oldChannel.ip);
                }
                if (oldChannel.port != null) {
                    channelBuilder.setPort(oldChannel.port);
                }
                if (oldChannel.priority != null) {
                    channelBuilder.setPriority(oldChannel.priority);
                }
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
            if (oldIntrude.intrude != null) {
                intrudeBuilder.setIntrude(oldIntrude.intrude);
            }
            if (oldIntrude.name != null) {
                intrudeBuilder.setName(oldIntrude.name);
            }
            if (oldIntrude.guid != null) {
                intrudeBuilder.setGuid(oldIntrude.guid);
            }
            
            if (oldIntrude.channel != null) {
                com.dnfm.mina.protobuf.PT_CHANNEL_INFO oldChannel = oldIntrude.channel;
                com.dnfm.mina.protobuf.generated.ChannelInfo.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.ChannelInfo.newBuilder();
                if (oldChannel.world != null) {
                    channelBuilder.setWorld(oldChannel.world);
                }
                if (oldChannel.channel != null) {
                    channelBuilder.setChannel(oldChannel.channel);
                }
                if (oldChannel.ip != null) {
                    channelBuilder.setIp(oldChannel.ip);
                }
                if (oldChannel.port != null) {
                    channelBuilder.setPort(oldChannel.port);
                }
                if (oldChannel.priority != null) {
                    channelBuilder.setPriority(oldChannel.priority);
                }
                intrudeBuilder.setChannel(channelBuilder.build());
            }
            
            if (oldIntrude.partymembers != null) {
                for (com.dnfm.mina.protobuf.PT_INTRUDE_MEMBER_INFO oldMember : oldIntrude.partymembers) {
                    com.dnfm.mina.protobuf.generated.IntrudeMemberInfo.Builder memberBuilder = 
                        com.dnfm.mina.protobuf.generated.IntrudeMemberInfo.newBuilder();
                    if (oldMember.name != null) {
                        memberBuilder.setName(oldMember.name);
                    }
                    if (oldMember.level != null) {
                        memberBuilder.setLevel(oldMember.level);
                    }
                    if (oldMember.job != null) {
                        memberBuilder.setJob(oldMember.job);
                    }
                    if (oldMember.growtype != null) {
                        memberBuilder.setGrowtype(oldMember.growtype);
                    }
                    if (oldMember.secgrowtype != null) {
                        memberBuilder.setSecgrowtype(oldMember.secgrowtype);
                    }
                    intrudeBuilder.addPartyMembers(memberBuilder.build());
                }
            }
            
            if (oldIntrude.rpguid != null) {
                intrudeBuilder.setRpGuid(oldIntrude.rpguid);
            }
            builder.setIntrudeinfo(intrudeBuilder.build());
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptPingResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_PING oldResponse = 
            (com.dnfm.mina.protobuf.RES_PING) msg;
        
        com.dnfm.mina.protobuf.generated.PingResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.PingResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.responsetime != null) {
            builder.setResponsetime(oldResponse.responsetime);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptCharacterListResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_CHARAC_LIST oldResponse = 
            (com.dnfm.mina.protobuf.RES_CHARAC_LIST) msg;
        
        com.dnfm.mina.protobuf.generated.CharacterListResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.CharacterListResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.version != null) {
            builder.setVersion(oldResponse.version);
        }
        
        if (oldResponse.limits != null) {
            for (com.dnfm.mina.protobuf.PT_JOB_LIMIT_INFO oldLimit : oldResponse.limits) {
                com.dnfm.mina.protobuf.generated.JobLimitInfo.Builder limitBuilder = 
                    com.dnfm.mina.protobuf.generated.JobLimitInfo.newBuilder();
                if (oldLimit.job != null) {
                    limitBuilder.setJob(oldLimit.job);
                }
                if (oldLimit.growtype != null) {
                    limitBuilder.setGrowType(oldLimit.growtype);
                }
                if (oldLimit.level != null) {
                    limitBuilder.setLevel(oldLimit.level);
                }
                builder.addLimits(limitBuilder.build());
            }
        }
        
        if (oldResponse.characlist != null) {
            for (com.dnfm.mina.protobuf.PT_CHARACTER oldChar : oldResponse.characlist) {
                com.dnfm.mina.protobuf.generated.CharacterWithEquipList.Builder charBuilder = 
                    com.dnfm.mina.protobuf.generated.CharacterWithEquipList.newBuilder();
                if (oldChar.charguid != null) {
                    charBuilder.setCharGuid(oldChar.charguid);
                }
                if (oldChar.lastlogout != null) {
                    charBuilder.setLastLogout(oldChar.lastlogout);
                }
                if (oldChar.growtype != null) {
                    charBuilder.setGrowType(oldChar.growtype);
                }
                if (oldChar.secgrowtype != null) {
                    charBuilder.setSecGrowType(oldChar.secgrowtype);
                }
                if (oldChar.job != null) {
                    charBuilder.setJob(oldChar.job);
                }
                if (oldChar.level != null) {
                    charBuilder.setLevel(oldChar.level);
                }
                if (oldChar.name != null) {
                    charBuilder.setName(oldChar.name);
                }
                if (oldChar.fatigue != null) {
                    charBuilder.setFatigue(oldChar.fatigue);
                }
                if (oldChar.equipscore != null) {
                    charBuilder.setEquipScore(oldChar.equipscore);
                }
                if (oldChar.characterframe != null) {
                    charBuilder.setCharacterFrame(oldChar.characterframe);
                }
                if (oldChar.avatarVisibleFlags != null) {
                    charBuilder.setAvatarVisibleFlags(oldChar.avatarVisibleFlags);
                }
                if (oldChar.deletionstatus != null) {
                    charBuilder.setDeletionStatus(oldChar.deletionstatus);
                }
                if (oldChar.deletiontime != null) {
                    charBuilder.setDeletionTime(oldChar.deletiontime);
                }
                if (oldChar.createtime != null) {
                    charBuilder.setCreateTime(oldChar.createtime);
                }
                if (oldChar.changename != null) {
                    charBuilder.setChangeName(oldChar.changename);
                }
                
                if (oldChar.equips != null) {
                    com.dnfm.mina.protobuf.generated.CharacterEquipIndexList.Builder equipsBuilder = 
                        com.dnfm.mina.protobuf.generated.CharacterEquipIndexList.newBuilder();
                    
                    if (oldChar.equips.equiplist != null) {
                        for (com.dnfm.mina.protobuf.PT_EQUIP_INDEX_SLOT oldSlot : oldChar.equips.equiplist) {
                            com.dnfm.mina.protobuf.generated.EquipIndexSlot.Builder slotBuilder = 
                                com.dnfm.mina.protobuf.generated.EquipIndexSlot.newBuilder();
                            if (oldSlot.index != null) {
                                slotBuilder.setIndex(oldSlot.index);
                            }
                            if (oldSlot.slot != null) {
                                slotBuilder.setSlot(oldSlot.slot);
                            }
                            if (oldSlot.reforge != null) {
                                slotBuilder.setReforge(oldSlot.reforge);
                            }
                            if (oldSlot.reforgeexp != null) {
                                slotBuilder.setReforgeexp(oldSlot.reforgeexp);
                            }
                            if (oldSlot.upgrade != null) {
                                slotBuilder.setUpgrade(oldSlot.upgrade);
                            }
                            equipsBuilder.addEquiplist(slotBuilder.build());
                        }
                    }
                    
                    if (oldChar.equips.avatarlist != null) {
                        for (com.dnfm.mina.protobuf.PT_AVATAR_INDEX_SLOT oldSlot : oldChar.equips.avatarlist) {
                            com.dnfm.mina.protobuf.generated.AvatarIndexSlot.Builder slotBuilder = 
                                com.dnfm.mina.protobuf.generated.AvatarIndexSlot.newBuilder();
                            if (oldSlot.index != null) {
                                slotBuilder.setIndex(oldSlot.index);
                            }
                            if (oldSlot.slot != null) {
                                slotBuilder.setSlot(oldSlot.slot);
                            }
                            equipsBuilder.addAvatarlist(slotBuilder.build());
                        }
                    }
                    
                    charBuilder.setEquips(equipsBuilder.build());
                }
                
                builder.addCharacters(charBuilder.build());
            }
        }
        
        if (oldResponse.count != null) {
            builder.setCount(oldResponse.count);
        }
        if (oldResponse.adventureunionlevel != null) {
            builder.setAdventureUnionLevel(oldResponse.adventureunionlevel);
        }
        if (oldResponse.adventureunionexp != null) {
            builder.setAdventureUnionExp(oldResponse.adventureunionexp);
        }
        if (oldResponse.dailycreatecharcount != null) {
            builder.setDailyCreateCharCount(oldResponse.dailycreatecharcount);
        }
        if (oldResponse.dailycreatecharmaxcount != null) {
            builder.setDailyCreateCharMaxCount(oldResponse.dailycreatecharmaxcount);
        }
        if (oldResponse.adventureunionname != null) {
            builder.setAdventureUnionName(oldResponse.adventureunionname);
        }
        if (oldResponse.maxcount != null) {
            builder.setMaxCount(oldResponse.maxcount);
        }
        if (oldResponse.ignorecontentsblacklist != null) {
            builder.setIgnoreContentsBlacklist(oldResponse.ignorecontentsblacklist);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptCreateCharacterResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_CREATE_CHARACTER oldResponse = 
            (com.dnfm.mina.protobuf.RES_CREATE_CHARACTER) msg;
        
        com.dnfm.mina.protobuf.generated.CreateCharacterResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.CreateCharacterResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.charguid != null) {
            builder.setCharguid(oldResponse.charguid);
        }
        if (oldResponse.job != null) {
            builder.setJob(oldResponse.job);
        }
        if (oldResponse.name != null) {
            builder.setName(oldResponse.name);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptChannelListResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_CHANNEL_LIST oldResponse = 
            (com.dnfm.mina.protobuf.RES_CHANNEL_LIST) msg;
        
        com.dnfm.mina.protobuf.generated.ChannelListResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ChannelListResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        
        if (oldResponse.list != null) {
            for (com.dnfm.mina.protobuf.PT_CHANNEL oldChannel : oldResponse.list) {
                com.dnfm.mina.protobuf.generated.Channel.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.Channel.newBuilder();
                if (oldChannel.world != null) {
                    channelBuilder.setWorld(oldChannel.world);
                }
                if (oldChannel.channel != null) {
                    channelBuilder.setChannel(oldChannel.channel);
                }
                if (oldChannel.iP != null) {
                    channelBuilder.setIp(oldChannel.iP);
                }
                if (oldChannel.port != null) {
                    channelBuilder.setPort(oldChannel.port);
                }
                if (oldChannel.saturation != null) {
                    channelBuilder.setSaturation(oldChannel.saturation);
                }
                if (oldChannel.integration != null) {
                    channelBuilder.setIntegration(oldChannel.integration);
                }
                builder.addList(channelBuilder.build());
            }
        }
        
        if (oldResponse.count != null) {
            builder.setCount(oldResponse.count);
        }
        
        if (oldResponse.integrations != null) {
            for (com.dnfm.mina.protobuf.PT_INTEGRATION_WORLD oldWorld : oldResponse.integrations) {
                com.dnfm.mina.protobuf.generated.IntegrationWorld.Builder worldBuilder = 
                    com.dnfm.mina.protobuf.generated.IntegrationWorld.newBuilder();
                if (oldWorld.world != null) {
                    worldBuilder.setWorld(oldWorld.world);
                }
                builder.addIntegrations(worldBuilder.build());
            }
        }
        
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        
        if (oldResponse.integrationrecommands != null) {
            for (com.dnfm.mina.protobuf.PT_CHANNEL oldChannel : oldResponse.integrationrecommands) {
                com.dnfm.mina.protobuf.generated.Channel.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.Channel.newBuilder();
                if (oldChannel.world != null) {
                    channelBuilder.setWorld(oldChannel.world);
                }
                if (oldChannel.channel != null) {
                    channelBuilder.setChannel(oldChannel.channel);
                }
                if (oldChannel.iP != null) {
                    channelBuilder.setIp(oldChannel.iP);
                }
                if (oldChannel.port != null) {
                    channelBuilder.setPort(oldChannel.port);
                }
                if (oldChannel.saturation != null) {
                    channelBuilder.setSaturation(oldChannel.saturation);
                }
                if (oldChannel.integration != null) {
                    channelBuilder.setIntegration(oldChannel.integration);
                }
                builder.addIntegrationrecommands(channelBuilder.build());
            }
        }
        
        if (oldResponse.worldrecommands != null) {
            for (com.dnfm.mina.protobuf.PT_CHANNEL oldChannel : oldResponse.worldrecommands) {
                com.dnfm.mina.protobuf.generated.Channel.Builder channelBuilder = 
                    com.dnfm.mina.protobuf.generated.Channel.newBuilder();
                if (oldChannel.world != null) {
                    channelBuilder.setWorld(oldChannel.world);
                }
                if (oldChannel.channel != null) {
                    channelBuilder.setChannel(oldChannel.channel);
                }
                if (oldChannel.iP != null) {
                    channelBuilder.setIp(oldChannel.iP);
                }
                if (oldChannel.port != null) {
                    channelBuilder.setPort(oldChannel.port);
                }
                if (oldChannel.saturation != null) {
                    channelBuilder.setSaturation(oldChannel.saturation);
                }
                if (oldChannel.integration != null) {
                    channelBuilder.setIntegration(oldChannel.integration);
                }
                builder.addWorldrecommands(channelBuilder.build());
            }
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptStandbyResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_STANDBY oldResponse = 
            (com.dnfm.mina.protobuf.RES_STANDBY) msg;
        
        com.dnfm.mina.protobuf.generated.StandbyResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.StandbyResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.standby != null) {
            builder.setStandby(oldResponse.standby);
        }
        if (oldResponse.vip != null) {
            builder.setVip(oldResponse.vip);
        }
        if (oldResponse.reconnect != null) {
            builder.setReconnect(oldResponse.reconnect);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptRemoveCharacterResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_REMOVE_CHARACTER oldResponse = 
            (com.dnfm.mina.protobuf.RES_REMOVE_CHARACTER) msg;
        
        com.dnfm.mina.protobuf.generated.DeleteCharacterResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.DeleteCharacterResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.charguid != null) {
            builder.setCharguid(oldResponse.charguid);
        }
        if (oldResponse.pendingtime != null) {
            builder.setPendingtime(oldResponse.pendingtime);
        }
        
        return builder.build().toByteArray();
    }
}
