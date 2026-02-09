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
            case 10009:
                return adaptAuthkeyRefreshResponse(msg);
            case 10012:
                return adaptPlatformProfileUpdateResponse(msg);
            case 10014:
                return adaptConnectBattleServerResponse(msg);
            case 10017:
                return adaptIdipProhibitListResponse(msg);
            case 10031:
                return adaptLoadServerSimpleDataResponse(msg);
            case 10032:
                return adaptSaveServerSimpleDataResponse(msg);
            case 10100:
                return adaptEnterTownResponse(msg);
            case 10103:
                return adaptCharacterInfoResponse(msg);
            case 10106:
                return adaptTownUserGuidListResponse(msg);
            case 10107:
                return adaptTargetUserDetailInfoResponse(msg);
            case 10108:
                return adaptInteractionMenuResponse(msg);
            case 10109:
                return adaptLeaveFromTownResponse(msg);
            case 14000:
                return adaptItemListResponse(msg);
            case 14001:
                return adaptItemMoveResponse(msg);
            case 14002:
                return adaptItemDropResponse(msg);
            case 14003:
                return adaptItemSplitResponse(msg);
            case 14006:
                return adaptItemReinforceResponse(msg);
            case 14017:
                return adaptItemUseResponse(msg);
            case 15001:
                return adaptMailListResponse(msg);
            case 15002:
                return adaptMailGetResponse(msg);
            case 15003:
                return adaptMailReadResponse(msg);
            case 15004:
                return adaptMailDeleteResponse(msg);
            case 15005:
                return adaptMailItemAllGetResponse(msg);
            case 15006:
                return adaptMailAllDeleteResponse(msg);
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
    
    private byte[] adaptAuthkeyRefreshResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_AUTHKEY_REFRESH oldResponse = 
            (com.dnfm.mina.protobuf.RES_AUTHKEY_REFRESH) msg;
        
        com.dnfm.mina.protobuf.generated.AuthkeyRefreshResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.AuthkeyRefreshResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.authkey != null) {
            builder.setAuthkey(oldResponse.authkey);
        }
        if (oldResponse.version != null) {
            builder.setVersion(oldResponse.version);
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
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptConnectBattleServerResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_CONNECT_BATTLE_SERVER oldResponse = 
            (com.dnfm.mina.protobuf.RES_CONNECT_BATTLE_SERVER) msg;
        
        com.dnfm.mina.protobuf.generated.ConnectBattleServerResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ConnectBattleServerResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.authkey != null) {
            builder.setAuthkey(oldResponse.authkey);
        }
        if (oldResponse.bchannel != null) {
            builder.setBchannel(oldResponse.bchannel);
        }
        if (oldResponse.servertime != null) {
            builder.setServertime(oldResponse.servertime);
        }
        if (oldResponse.encrypt != null) {
            builder.setEncrypt(oldResponse.encrypt);
        }
        if (oldResponse.key != null) {
            builder.setKey(oldResponse.key);
        }
        if (oldResponse.seeds != null) {
            builder.addAllSeeds(oldResponse.seeds);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptIdipProhibitListResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_IDIP_PROHIBIT_LIST oldResponse = 
            (com.dnfm.mina.protobuf.RES_IDIP_PROHIBIT_LIST) msg;
        
        com.dnfm.mina.protobuf.generated.IdipProhibitListResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.IdipProhibitListResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.prohibit != null) {
            for (com.dnfm.mina.protobuf.PT_PROHIBIT oldProhibit : oldResponse.prohibit) {
                com.dnfm.mina.protobuf.generated.Prohibit.Builder prohibitBuilder = 
                    com.dnfm.mina.protobuf.generated.Prohibit.newBuilder();
                if (oldProhibit.target != null) {
                    prohibitBuilder.setTarget(oldProhibit.target);
                }
                if (oldProhibit.type != null) {
                    prohibitBuilder.setTypeValue(oldProhibit.type.value());
                }
                if (oldProhibit.subtype != null) {
                    prohibitBuilder.addAllSubtype(oldProhibit.subtype);
                }
                if (oldProhibit.endtime != null) {
                    prohibitBuilder.setEndtime(oldProhibit.endtime);
                }
                if (oldProhibit.reason != null) {
                    prohibitBuilder.setReason(oldProhibit.reason);
                }
                if (oldProhibit.times != null) {
                    prohibitBuilder.addAllTimes(oldProhibit.times);
                }
                builder.addProhibit(prohibitBuilder.build());
            }
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptLoadServerSimpleDataResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_LOAD_SERVER_SIMPLE_DATA oldResponse = 
            (com.dnfm.mina.protobuf.RES_LOAD_SERVER_SIMPLE_DATA) msg;
        
        com.dnfm.mina.protobuf.generated.LoadServerSimpleDataResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.LoadServerSimpleDataResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        if (oldResponse.enumvalue != null) {
            builder.setEnumvalue(oldResponse.enumvalue);
        }
        if (oldResponse.value != null) {
            builder.setValue(oldResponse.value);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptSaveServerSimpleDataResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_SAVE_SERVER_SIMPLE_DATA oldResponse = 
            (com.dnfm.mina.protobuf.RES_SAVE_SERVER_SIMPLE_DATA) msg;
        
        com.dnfm.mina.protobuf.generated.SaveServerSimpleDataResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.SaveServerSimpleDataResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        
        return builder.build().toByteArray();
    }
    
    private byte[] adaptPlatformProfileUpdateResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_PLATFORM_PROFILE_UPDATE oldResponse = 
            (com.dnfm.mina.protobuf.RES_PLATFORM_PROFILE_UPDATE) msg;
        
        com.dnfm.mina.protobuf.generated.PlatformProfileUpdateResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.PlatformProfileUpdateResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptEnterTownResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_ENTER_TO_TOWN oldResponse = 
            (com.dnfm.mina.protobuf.RES_ENTER_TO_TOWN) msg;
        
        com.dnfm.mina.protobuf.generated.EnterTownResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.EnterTownResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.town != null) {
            builder.setTown(oldResponse.town);
        }
        if (oldResponse.area != null) {
            builder.setArea(oldResponse.area);
        }
        if (oldResponse.posx != null) {
            builder.setPosx(oldResponse.posx);
        }
        if (oldResponse.posy != null) {
            builder.setPosy(oldResponse.posy);
        }
        if (oldResponse.partyguid != null) {
            builder.setPartyguid(oldResponse.partyguid);
        }
        if (oldResponse.servertime != null) {
            builder.setServertime(oldResponse.servertime);
        }
        if (oldResponse.expratio != null) {
            builder.setExpratio(oldResponse.expratio);
        }
        if (oldResponse.fatigueratio != null) {
            builder.setFatigueratio(oldResponse.fatigueratio);
        }
        if (oldResponse.version != null) {
            builder.setVersion(oldResponse.version);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptCharacterInfoResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_CHARACTER_INFO oldResponse = 
            (com.dnfm.mina.protobuf.RES_CHARACTER_INFO) msg;
        
        com.dnfm.mina.protobuf.generated.CharacterInfoResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.CharacterInfoResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        
        if (oldResponse.charlist != null) {
            for (com.dnfm.mina.protobuf.PT_CHARACTER_INFO oldCharInfo : oldResponse.charlist) {
                com.dnfm.mina.protobuf.generated.CharacterInfo.Builder charInfoBuilder = 
                    com.dnfm.mina.protobuf.generated.CharacterInfo.newBuilder();
                
                if (oldCharInfo.charguid != null) {
                    charInfoBuilder.setCharguid(oldCharInfo.charguid);
                }
                if (oldCharInfo.job != null) {
                    charInfoBuilder.setJob(oldCharInfo.job);
                }
                if (oldCharInfo.growtype != null) {
                    charInfoBuilder.setGrowtype(oldCharInfo.growtype);
                }
                if (oldCharInfo.secondgrowtype != null) {
                    charInfoBuilder.setSecondgrowtype(oldCharInfo.secondgrowtype);
                }
                if (oldCharInfo.level != null) {
                    charInfoBuilder.setLevel(oldCharInfo.level);
                }
                if (oldCharInfo.name != null) {
                    charInfoBuilder.setName(oldCharInfo.name);
                }
                if (oldCharInfo.gguid != null) {
                    charInfoBuilder.setGguid(oldCharInfo.gguid);
                }
                if (oldCharInfo.posx != null) {
                    charInfoBuilder.setPosx(oldCharInfo.posx);
                }
                if (oldCharInfo.posy != null) {
                    charInfoBuilder.setPosy(oldCharInfo.posy);
                }
                if (oldCharInfo.gname != null) {
                    charInfoBuilder.setGname(oldCharInfo.gname);
                }
                if (oldCharInfo.vip != null) {
                    charInfoBuilder.setVip(oldCharInfo.vip);
                }
                if (oldCharInfo.vcreature != null) {
                    charInfoBuilder.setVcreature(oldCharInfo.vcreature);
                }
                if (oldCharInfo.partydisturb != null) {
                    charInfoBuilder.setPartydisturb(oldCharInfo.partydisturb);
                }
                if (oldCharInfo.spoint != null) {
                    charInfoBuilder.setSpoint(oldCharInfo.spoint);
                }
                if (oldCharInfo.adventureunionlevel != null) {
                    charInfoBuilder.setAdventureunionlevel(oldCharInfo.adventureunionlevel);
                }
                if (oldCharInfo.adventureunionexp != null) {
                    charInfoBuilder.setAdventureunionexp(oldCharInfo.adventureunionexp);
                }
                if (oldCharInfo.partyguid != null) {
                    charInfoBuilder.setPartyguid(oldCharInfo.partyguid);
                }
                if (oldCharInfo.partyleaderguid != null) {
                    charInfoBuilder.setPartyleaderguid(oldCharInfo.partyleaderguid);
                }
                if (oldCharInfo.type != null) {
                    charInfoBuilder.setType(oldCharInfo.type);
                }
                if (oldCharInfo.pvpstargrade != null) {
                    charInfoBuilder.setPvpstargrade(oldCharInfo.pvpstargrade);
                }
                if (oldCharInfo.pvpstarcount != null) {
                    charInfoBuilder.setPvpstarcount(oldCharInfo.pvpstarcount);
                }
                if (oldCharInfo.blackdiamond != null) {
                    charInfoBuilder.setBlackdiamond(oldCharInfo.blackdiamond);
                }
                if (oldCharInfo.avatarVisibleFlags != null) {
                    charInfoBuilder.setAvatarVisibleFlags(oldCharInfo.avatarVisibleFlags);
                }
                if (oldCharInfo.fairpvpstargrade != null) {
                    charInfoBuilder.setFairpvpstargrade(oldCharInfo.fairpvpstargrade);
                }
                if (oldCharInfo.fairpvpstarcount != null) {
                    charInfoBuilder.setFairpvpstarcount(oldCharInfo.fairpvpstarcount);
                }
                
                builder.addCharlist(charInfoBuilder.build());
            }
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptTownUserGuidListResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_TOWN_USER_GUID_LIST oldResponse = 
            (com.dnfm.mina.protobuf.RES_TOWN_USER_GUID_LIST) msg;
        
        com.dnfm.mina.protobuf.generated.TownUserGuidListResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.TownUserGuidListResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        
        if (oldResponse.charlist != null) {
            for (com.dnfm.mina.protobuf.PT_CHARACTER_GUID oldCharGuid : oldResponse.charlist) {
                com.dnfm.mina.protobuf.generated.CharacterGuid.Builder charGuidBuilder = 
                    com.dnfm.mina.protobuf.generated.CharacterGuid.newBuilder();
                
                if (oldCharGuid.charguid != null) {
                    charGuidBuilder.setCharguid(oldCharGuid.charguid);
                }
                if (oldCharGuid.type != null) {
                    charGuidBuilder.setType(oldCharGuid.type);
                }
                if (oldCharGuid.posx != null) {
                    charGuidBuilder.setPosx(oldCharGuid.posx);
                }
                if (oldCharGuid.posy != null) {
                    charGuidBuilder.setPosy(oldCharGuid.posy);
                }
                
                builder.addCharlist(charGuidBuilder.build());
            }
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptTargetUserDetailInfoResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_TARGET_USER_DETAIL_INFO oldResponse = 
            (com.dnfm.mina.protobuf.RES_TARGET_USER_DETAIL_INFO) msg;
        
        com.dnfm.mina.protobuf.generated.TargetUserDetailInfoResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.TargetUserDetailInfoResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.guid != null) {
            builder.setGuid(oldResponse.guid);
        }
        if (oldResponse.world != null) {
            builder.setWorld(oldResponse.world);
        }
        if (oldResponse.gguid != null) {
            builder.setGguid(oldResponse.gguid);
        }
        if (oldResponse.gmastername != null) {
            builder.setGmastername(oldResponse.gmastername);
        }
        if (oldResponse.name != null) {
            builder.setName(oldResponse.name);
        }
        if (oldResponse.exp != null) {
            builder.setExp(oldResponse.exp);
        }
        if (oldResponse.level != null) {
            builder.setLevel(oldResponse.level);
        }
        if (oldResponse.job != null) {
            builder.setJob(oldResponse.job);
        }
        if (oldResponse.growtype != null) {
            builder.setGrowtype(oldResponse.growtype);
        }
        if (oldResponse.secgrowtype != null) {
            builder.setSecgrowtype(oldResponse.secgrowtype);
        }
        if (oldResponse.equipscore != null) {
            builder.setEquipscore(oldResponse.equipscore);
        }
        if (oldResponse.spoint != null) {
            builder.setSpoint(oldResponse.spoint);
        }
        if (oldResponse.adventureunionlevel != null) {
            builder.setAdventureunionlevel(oldResponse.adventureunionlevel);
        }
        if (oldResponse.adventureunionexp != null) {
            builder.setAdventureunionexp(oldResponse.adventureunionexp);
        }
        if (oldResponse.characlistcount != null) {
            builder.setCharaclistcount(oldResponse.characlistcount);
        }
        if (oldResponse.gname != null) {
            builder.setGname(oldResponse.gname);
        }
        if (oldResponse.gmembergrade != null) {
            builder.setGmembergrade(oldResponse.gmembergrade);
        }
        if (oldResponse.blackdiamond != null) {
            builder.setBlackdiamond(oldResponse.blackdiamond);
        }
        if (oldResponse.creditscore != null) {
            builder.setCreditscore(oldResponse.creditscore);
        }
        if (oldResponse.gamecenterinfo != null) {
            builder.setGamecenterinfo(oldResponse.gamecenterinfo);
        }
        if (oldResponse.qqVipinfo != null) {
            builder.setQqVipinfo(oldResponse.qqVipinfo);
        }
        if (oldResponse.avatarVisibleFlags != null) {
            builder.setAvatarVisibleFlags(oldResponse.avatarVisibleFlags);
        }
        if (oldResponse.totallike != null) {
            builder.setTotallike(oldResponse.totallike);
        }
        if (oldResponse.like != null) {
            builder.setLike(oldResponse.like);
        }
        if (oldResponse.rank != null) {
            builder.setRank(oldResponse.rank);
        }
        if (oldResponse.communionlevel != null) {
            builder.setCommunionlevel(oldResponse.communionlevel);
        }
        if (oldResponse.fame != null) {
            builder.setFame(oldResponse.fame);
        }
        if (oldResponse.charm != null) {
            builder.setCharm(oldResponse.charm);
        }
        if (oldResponse.grade != null) {
            builder.setGrade(oldResponse.grade);
        }
        if (oldResponse.gradeFair != null) {
            builder.setGradeFair(oldResponse.gradeFair);
        }
        if (oldResponse.isadventureCondition != null) {
            builder.setIsadventureCondition(oldResponse.isadventureCondition);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptInteractionMenuResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_INTERACTION_MENU oldResponse = 
            (com.dnfm.mina.protobuf.RES_INTERACTION_MENU) msg;
        
        com.dnfm.mina.protobuf.generated.InteractionMenuResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.InteractionMenuResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.charguid != null) {
            builder.setCharguid(oldResponse.charguid);
        }
        if (oldResponse.online != null) {
            builder.setOnline(oldResponse.online);
        }
        if (oldResponse.status != null) {
            builder.setStatus(oldResponse.status);
        }
        if (oldResponse.lastlogout != null) {
            builder.setLastlogout(oldResponse.lastlogout);
        }
        if (oldResponse.gguid != null) {
            builder.setGguid(oldResponse.gguid);
        }
        if (oldResponse.openmenutype != null) {
            builder.setOpenmenutype(oldResponse.openmenutype);
        }
        if (oldResponse.level != null) {
            builder.setLevel(oldResponse.level);
        }
        if (oldResponse.job != null) {
            builder.setJob(oldResponse.job);
        }
        if (oldResponse.growtype != null) {
            builder.setGrowtype(oldResponse.growtype);
        }
        if (oldResponse.secgrowtype != null) {
            builder.setSecgrowtype(oldResponse.secgrowtype);
        }
        if (oldResponse.name != null) {
            builder.setName(oldResponse.name);
        }
        if (oldResponse.gname != null) {
            builder.setGname(oldResponse.gname);
        }
        if (oldResponse.grade != null) {
            builder.setGrade(oldResponse.grade);
        }
        if (oldResponse.qindex != null) {
            builder.setQindex(oldResponse.qindex);
        }
        if (oldResponse.partyguid != null) {
            builder.setPartyguid(oldResponse.partyguid);
        }
        if (oldResponse.partyleader != null) {
            builder.setPartyleader(oldResponse.partyleader);
        }
        if (oldResponse.publictype != null) {
            builder.setPublictype(oldResponse.publictype);
        }
        if (oldResponse.creditscore != null) {
            builder.setCreditscore(oldResponse.creditscore);
        }
        if (oldResponse.world != null) {
            builder.setWorld(oldResponse.world);
        }
        if (oldResponse.openid != null) {
            builder.setOpenid(oldResponse.openid);
        }
        if (oldResponse.platform != null) {
            builder.setPlatform(oldResponse.platform);
        }
        if (oldResponse.platformserverid != null) {
            builder.setPlatformserverid(oldResponse.platformserverid);
        }
        if (oldResponse.adventureunionname != null) {
            builder.setAdventureunionname(oldResponse.adventureunionname);
        }
        if (oldResponse.gamecenterinfo != null) {
            builder.setGamecenterinfo(oldResponse.gamecenterinfo);
        }
        if (oldResponse.qqVipinfo != null) {
            builder.setQqVipinfo(oldResponse.qqVipinfo);
        }
        if (oldResponse.characterframe != null) {
            builder.setCharacterframe(oldResponse.characterframe);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptLeaveFromTownResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_LEAVE_FROM_TOWN oldResponse = 
            (com.dnfm.mina.protobuf.RES_LEAVE_FROM_TOWN) msg;
        
        com.dnfm.mina.protobuf.generated.LeaveFromTownResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.LeaveFromTownResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptMailListResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_MAIL_LIST oldResponse = 
            (com.dnfm.mina.protobuf.RES_MAIL_LIST) msg;
        
        com.dnfm.mina.protobuf.generated.MailListResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.MailListResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.count != null) {
            builder.setCount(oldResponse.count);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        if (oldResponse.postallist != null) {
            for (com.dnfm.mina.protobuf.PT_POST_ALL_LIST oldPost : oldResponse.postallist) {
                com.dnfm.mina.protobuf.generated.PostAllList.Builder postBuilder = 
                    com.dnfm.mina.protobuf.generated.PostAllList.newBuilder();
                if (oldPost.index != null) {
                    postBuilder.setIndex(oldPost.index);
                }
                if (oldPost.count != null) {
                    postBuilder.setCount(oldPost.count);
                }
                if (oldPost.guid != null) {
                    postBuilder.setGuid(oldPost.guid);
                }
                if (oldPost.title != null) {
                    postBuilder.setTitle(oldPost.title);
                }
                if (oldPost.msg != null) {
                    postBuilder.setMsg(oldPost.msg);
                }
                if (oldPost.expiretime != null) {
                    postBuilder.setExpiretime(oldPost.expiretime);
                }
                if (oldPost.read != null) {
                    postBuilder.setRead(oldPost.read);
                }
                if (oldPost.receive != null) {
                    postBuilder.setReceive(oldPost.receive);
                }
                if (oldPost.importance != null) {
                    postBuilder.setImportance(oldPost.importance);
                }
                builder.addPostallist(postBuilder.build());
            }
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptMailGetResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_MAIL_GET oldResponse = 
            (com.dnfm.mina.protobuf.RES_MAIL_GET) msg;
        
        com.dnfm.mina.protobuf.generated.MailGetResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.MailGetResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.guid != null) {
            builder.setGuid(oldResponse.guid);
        }
        if (oldResponse.limit != null) {
            builder.setLimit(oldResponse.limit);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        if (oldResponse.remaineditems != null) {
            for (com.dnfm.mina.protobuf.PT_SELECTED_ITEM oldItem : oldResponse.remaineditems) {
                com.dnfm.mina.protobuf.generated.SelectedItem.Builder itemBuilder = 
                    com.dnfm.mina.protobuf.generated.SelectedItem.newBuilder();
                if (oldItem.index != null) {
                    itemBuilder.setIndex(oldItem.index);
                }
                if (oldItem.count != null) {
                    itemBuilder.setCount(oldItem.count);
                }
                if (oldItem.guid != null) {
                    itemBuilder.setGuid(oldItem.guid);
                }
                if (oldItem.slotindex != null) {
                    itemBuilder.setSlotindex(oldItem.slotindex);
                }
                builder.addRemaineditems(itemBuilder.build());
            }
        }
        if (oldResponse.remainedpackages != null) {
            for (com.dnfm.mina.protobuf.PT_POST_PACKAGE oldPackage : oldResponse.remainedpackages) {
                com.dnfm.mina.protobuf.generated.PostPackage.Builder packageBuilder = 
                    com.dnfm.mina.protobuf.generated.PostPackage.newBuilder();
                if (oldPackage.value != null) {
                    packageBuilder.setValue(oldPackage.value);
                }
                if (oldPackage.index != null) {
                    packageBuilder.setIndex(oldPackage.index);
                }
                if (oldPackage.slotindex != null) {
                    packageBuilder.setSlotindex(oldPackage.slotindex);
                }
                builder.addRemainedpackages(packageBuilder.build());
            }
        }
        if (oldResponse.rewards != null) {
            com.dnfm.mina.protobuf.generated.ContentsRewardInfo.Builder rewardsBuilder = 
                com.dnfm.mina.protobuf.generated.ContentsRewardInfo.newBuilder();
            if (oldResponse.rewards.items != null && oldResponse.rewards.items.inventory != null) {
                com.dnfm.mina.protobuf.generated.ItemRewardInfo.Builder itemsBuilder = 
                    com.dnfm.mina.protobuf.generated.ItemRewardInfo.newBuilder();
                com.dnfm.mina.protobuf.generated.Items.Builder inventoryBuilder = 
                    com.dnfm.mina.protobuf.generated.Items.newBuilder();
                if (oldResponse.rewards.items.inventory.index != null) {
                    inventoryBuilder.setIndex(oldResponse.rewards.items.inventory.index);
                }
                if (oldResponse.rewards.items.inventory.count != null) {
                    inventoryBuilder.setCount(oldResponse.rewards.items.inventory.count);
                }
                itemsBuilder.setInventory(inventoryBuilder.build());
                rewardsBuilder.setItems(itemsBuilder.build());
            }
            builder.setRewards(rewardsBuilder.build());
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptMailReadResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_MAIL_READ oldResponse = 
            (com.dnfm.mina.protobuf.RES_MAIL_READ) msg;
        
        com.dnfm.mina.protobuf.generated.MailReadResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.MailReadResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.guid != null) {
            builder.setGuid(oldResponse.guid);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptMailDeleteResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_MAIL_DELETE oldResponse = 
            (com.dnfm.mina.protobuf.RES_MAIL_DELETE) msg;
        
        com.dnfm.mina.protobuf.generated.MailDeleteResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.MailDeleteResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.guid != null) {
            builder.setGuid(oldResponse.guid);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptMailItemAllGetResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_MAIL_ITEM_ALL_GET oldResponse = 
            (com.dnfm.mina.protobuf.RES_MAIL_ITEM_ALL_GET) msg;
        
        com.dnfm.mina.protobuf.generated.MailItemAllGetResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.MailItemAllGetResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.page != null) {
            builder.setPage(oldResponse.page);
        }
        if (oldResponse.maxpage != null) {
            builder.setMaxpage(oldResponse.maxpage);
        }
        if (oldResponse.limit != null) {
            builder.setLimit(oldResponse.limit);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        if (oldResponse.bind != null) {
            builder.setBind(oldResponse.bind);
        }
        if (oldResponse.rewards != null) {
            com.dnfm.mina.protobuf.generated.ContentsRewardInfo.Builder rewardsBuilder = 
                com.dnfm.mina.protobuf.generated.ContentsRewardInfo.newBuilder();
            if (oldResponse.rewards.items != null && oldResponse.rewards.items.inventory != null) {
                com.dnfm.mina.protobuf.generated.ItemRewardInfo.Builder itemsBuilder = 
                    com.dnfm.mina.protobuf.generated.ItemRewardInfo.newBuilder();
                com.dnfm.mina.protobuf.generated.Items.Builder inventoryBuilder = 
                    com.dnfm.mina.protobuf.generated.Items.newBuilder();
                if (oldResponse.rewards.items.inventory.index != null) {
                    inventoryBuilder.setIndex(oldResponse.rewards.items.inventory.index);
                }
                if (oldResponse.rewards.items.inventory.count != null) {
                    inventoryBuilder.setCount(oldResponse.rewards.items.inventory.count);
                }
                itemsBuilder.setInventory(inventoryBuilder.build());
                rewardsBuilder.setItems(itemsBuilder.build());
            }
            builder.setRewards(rewardsBuilder.build());
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptMailAllDeleteResponse(Message msg) throws Exception {
        com.dnfm.mina.protobuf.RES_MAIL_ALL_DELETE oldResponse = 
            (com.dnfm.mina.protobuf.RES_MAIL_ALL_DELETE) msg;
        
        com.dnfm.mina.protobuf.generated.MailAllDeleteResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.MailAllDeleteResponse.newBuilder();
        
        if (oldResponse.error != null) {
            builder.setError(oldResponse.error);
        }
        if (oldResponse.type != null) {
            builder.setType(oldResponse.type);
        }
        if (oldResponse.removecount != null) {
            builder.setRemovecount(oldResponse.removecount);
        }
        
        return builder.build().toByteArray();
    }

    private byte[] adaptItemUseResponse(Message msg) throws Exception {
        // 移除对不存在的RES_ITEM_USE的引用
        System.out.println("===== StandardProtobufEncoder.adaptItemUseResponse() 被调用 =====");
        
        com.dnfm.mina.protobuf.generated.ItemUseResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ItemUseResponse.newBuilder();
        
        // 简化实现，只设置默认值
        builder.setError(0);
        builder.setResult(0);
        
        return builder.build().toByteArray();
    }

    private byte[] adaptItemReinforceResponse(Message msg) throws Exception {
        // 移除对不存在的RES_ITEM_REINFORCE的引用
        System.out.println("===== StandardProtobufEncoder.adaptItemReinforceResponse() 被调用 =====");
        
        com.dnfm.mina.protobuf.generated.ItemReinforceResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ItemReinforceResponse.newBuilder();
        
        // 简化实现，只设置默认值
        builder.setError(0);
        
        return builder.build().toByteArray();
    }

    private byte[] adaptItemListResponse(Message msg) throws Exception {
        // 移除对不存在的RES_ITEM_LIST的引用
        System.out.println("===== StandardProtobufEncoder.adaptItemListResponse() 被调用 =====");
        
        com.dnfm.mina.protobuf.generated.ItemListResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ItemListResponse.newBuilder();
        
        // 简化实现，只设置默认值
        builder.setError(0);
        
        return builder.build().toByteArray();
    }

    private byte[] adaptItemMoveResponse(Message msg) throws Exception {
        // 移除对不存在的RES_ITEM_MOVE的引用
        System.out.println("===== StandardProtobufEncoder.adaptItemMoveResponse() 被调用 =====");
        
        com.dnfm.mina.protobuf.generated.ItemMoveResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ItemMoveResponse.newBuilder();
        
        // 简化实现，只设置默认值
        builder.setError(0);
        builder.setSuccess(true);
        
        return builder.build().toByteArray();
    }

    private byte[] adaptItemDropResponse(Message msg) throws Exception {
        // 移除对不存在的RES_ITEM_DROP的引用
        System.out.println("===== StandardProtobufEncoder.adaptItemDropResponse() 被调用 =====");
        
        com.dnfm.mina.protobuf.generated.ItemDropResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ItemDropResponse.newBuilder();
        
        // 简化实现，只设置默认值
        builder.setError(0);
        builder.setSuccess(true);
        
        return builder.build().toByteArray();
    }

    private byte[] adaptItemSplitResponse(Message msg) throws Exception {
        // 移除对不存在的RES_ITEM_SPLIT的引用
        System.out.println("===== StandardProtobufEncoder.adaptItemSplitResponse() 被调用 =====");
        
        com.dnfm.mina.protobuf.generated.ItemSplitResponse.Builder builder = 
            com.dnfm.mina.protobuf.generated.ItemSplitResponse.newBuilder();
        
        // 简化实现，只设置默认值
        builder.setError(0);
        builder.setSuccess(true);
        
        return builder.build().toByteArray();
    }
}
