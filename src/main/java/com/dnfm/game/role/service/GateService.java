package com.dnfm.game.role.service;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.ServerService;
import com.dnfm.game.player.model.PlayerProfile;
import java.util.HashMap;
import java.util.Map;
import org.nutz.http.Request;
import org.nutz.http.Sender;
import org.nutz.http.Request.METHOD;
import org.springframework.stereotype.Service;

@Service
public class GateService {
   public void addRoleToDist(String sid, PlayerProfile playerProfile) {
      Map<String, Object> params = new HashMap();
      params.put("sid", sid);
      params.put("content", JSON.toJSONString(playerProfile));

      try {
         Request request = Request.create(((ServerService)SpringUtils.getBean(ServerService.class)).getLoginServerUrl() + "/game/addRole", METHOD.POST).setParams(params);
         Sender.create(request).setTimeout(1000).send();
      } catch (Exception var5) {
      }

   }
}
