package com.dnfm.game.market;

import com.dnfm.game.ServerService;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.logs.LoggerFunction;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
   private static final boolean isClosed = true;
   @Autowired
   EquipService equipService;
   @Autowired
   BagService bagService;
   @Autowired
   RoleService roleService;
   @Autowired
   ServerService serverService;
   @Autowired
   Dao dao;
   private final Logger logger;
   private final int maxValue;

   public MarketService() {
      this.logger = LoggerFunction.MARKET.getLogger();
      this.maxValue = 99999999;
   }

   public void init() {
   }
}
