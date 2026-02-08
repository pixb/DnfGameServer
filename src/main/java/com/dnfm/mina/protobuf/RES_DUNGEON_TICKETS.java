package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10150,
   cmd = 1
)
public class RES_DUNGEON_TICKETS extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_TICKET> ticket;
   @Protobuf(
      order = 3
   )
   public List<PT_ACCOUNT_TICKET> adventureticket;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer fatiguetime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer helpercount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer jarofgreed;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer lotteryfreecount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer lotterychargecount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer recvfriendcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer sendfriendcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer mentoringquestpublishcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12
   )
   public List<Integer> ftimestps;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer sddeathrewardcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer recvplatformfriendcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer sendplatformfriendcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer guildredpacketrecvcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer guildredpacketsendcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 18,
      required = false
   )
   public Integer battleleaguerewardlimit;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19
   )
   public List<Integer> todaycleardungeonlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20
   )
   public List<Integer> clearsweepdungeonlist;
   @Protobuf(
      order = 21
   )
   public List<PT_GAUGE> gauges;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer battleleagueguildrewardlimit;
}
