package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GUILD_WAR_DETAIL {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long gguid;
   @Protobuf(
      order = 2
   )
   public List<PT_GUILD_WAR_DETAIL_INFO_CHARACTER> infos;
}
