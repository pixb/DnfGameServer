package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14103,
   cmd = 0
)
public class REQ_SEND_INVEN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer storage;
   @Protobuf(
      order = 2
   )
   public List<SendItem_GuidInfo> equipitems;
   @Protobuf(
      order = 3
   )
   public List<SendItem_Info> materialitems;
   @Protobuf(
      order = 4
   )
   public List<SendItem_Info> consumeitems;
   @Protobuf(
      order = 5
   )
   public List<SendItem_GuidInfo> avataritems;
   @Protobuf(
      order = 6
   )
   public List<SendItem_Info> emblemitems;
   @Protobuf(
      order = 7
   )
   public List<SendItem_Info> carditems;
   @Protobuf(
      order = 8
   )
   public List<SendItem_GuidInfo> titleitems;
   @Protobuf(
      order = 9
   )
   public List<SendItem_GuidInfo> creatureitems;
   @Protobuf(
      order = 10
   )
   public List<SendItem_GuidInfo> artifactitems;
   @Protobuf(
      order = 11
   )
   public List<SendItem_Info> crackitems;
   @Protobuf(
      order = 12
   )
   public List<SendItem_GuidInfo> crackequipitems;
   @Protobuf(
      order = 13
   )
   public List<SendItem_GuidInfo> sdavataritems;
   @Protobuf(
      order = 14
   )
   public List<SendItem_Info> bookmarkitems;
   @Protobuf(
      order = 15
   )
   public List<SendItem_GuidInfo> scrollitems;
}
