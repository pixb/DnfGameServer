package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ADVENTUREBOOK_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer bindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer bstate;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String charname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer growsecondtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 8
   )
   public List<Long> rewardedcharguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 9,
      required = false
   )
   public Boolean terareward;
}
