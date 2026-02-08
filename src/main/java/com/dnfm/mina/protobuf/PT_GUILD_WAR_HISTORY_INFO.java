package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GUILD_WAR_HISTORY_INFO {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String time;
   @Protobuf(
      order = 2
   )
   public List<PT_GUILD_WAR_HISTORY_GUILD_INFO> win;
}
