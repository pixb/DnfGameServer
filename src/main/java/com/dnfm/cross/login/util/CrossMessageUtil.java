package com.dnfm.cross.login.util;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.cross.CrossServerConfig;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.protobuf.Message;

public class CrossMessageUtil {
   public static void send(Role role, Message message) {
      if (((CrossServerConfig)SpringUtils.getBean(CrossServerConfig.class)).isCenterServer()) {
         if (message.getModule() > 0) {
            throw new IllegalStateException("跨服状态不允许发送协议" + message.getClass().getSimpleName());
         }
      }
   }
}
