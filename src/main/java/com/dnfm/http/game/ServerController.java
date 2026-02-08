package com.dnfm.http.game;

import com.dnfm.common.start.GameServer;
import com.dnfm.common.start.MyApplicationRunner;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.PayService;
import com.dnfm.game.role.service.RoleService;
import javax.servlet.http.HttpServletResponse;
import org.nutz.dao.Dao;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/scl/my"})
public class ServerController {
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   MyApplicationRunner myApplicationRunner;
   @Autowired(
      required = false
   )
   HttpServletResponse response;
   @Autowired
   AccountService accountService;
   @Autowired
   GameServer gameServer;
   @Autowired
   PlayerService playerService;
   @Autowired
   PayService payService;
   @Autowired
   RoleService roleService;
   @Autowired
   Dao dao;

   @RequestMapping(
      method = {RequestMethod.GET},
      path = {"id"}
   )
   @ResponseBody
   public Object id() {
      NutMap nutMap = new NutMap();
      nutMap.setv("roleId", this.roleService.getRoleId());
      nutMap.setv("uid", IdGenerator.getNextId());
      return nutMap;
   }
}
