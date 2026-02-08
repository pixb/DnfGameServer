package com.dnfm.doctor;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.market.MarketService;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.SessionUtils;
import org.apache.mina.core.session.IoSession;

public class CommonScript implements IScript {
   public CommonScript() {
      Role role = SpringUtils.getPlayerService().getPlayerBy(3198663572914176L);
      role.setLevel(62);
      IoSession session = SessionUtils.getSessionBy(role.getId());
      MarketService marketService = (MarketService)SpringUtils.getBean(MarketService.class);
   }
}
