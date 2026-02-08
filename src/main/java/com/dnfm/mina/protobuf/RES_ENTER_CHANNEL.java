package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10011,
   cmd = 1
)
public class RES_ENTER_CHANNEL extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer standby;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean encrypt;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long servertime;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer launchinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer vip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer checkup;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer checkuptype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 10,
      required = false
   )
   public String key;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer roxytutorial;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 12,
      required = false
   )
   public Long gamecenterregdate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer areatype;
   @Protobuf(
      order = 14,
      required = false
   )
   public PT_RETURN_USER_INFO returnUserInfo;
   @Protobuf(
      order = 15,
      required = false
   )
   public PT_CHANNEL channelinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16
   )
   public List<Integer> seeds;
   @Protobuf(
      order = 17,
      required = false
   )
   public PT_EVENT_SELECT_INFO eventSelectInfo;
}
