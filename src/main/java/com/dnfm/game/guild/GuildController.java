package com.dnfm.game.guild;

import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.REQ_CREATE_GUILD;
import com.dnfm.mina.protobuf.RES_CREATE_GUILD;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;

@Controller
public class GuildController {
   @RequestMapping
   public void reqCreateGuild(IoSession session, REQ_CREATE_GUILD req_create_guild) {
      RES_CREATE_GUILD res_create_guild = new RES_CREATE_GUILD();
      res_create_guild.error = 3;
      res_create_guild.transId = req_create_guild.transId;
      MessagePusher.pushMessage((IoSession)session, res_create_guild);
   }
}
