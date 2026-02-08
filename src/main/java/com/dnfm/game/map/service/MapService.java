package com.dnfm.game.map.service;

import com.dnfm.game.ServerService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.scene.SceneManager;
import java.util.List;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapService {
   @Autowired
   Dao dao;
   @Autowired
   ServerService serverService;
   @Autowired
   RoleService roleService;
   private final Logger logger = LoggerFactory.getLogger(MapService.class);

   public void enterTown(Role role) {
      int mapId = role.getPos().getTown() * 100 + role.getPos().getArea();
      SceneManager.INSTANCE.enterMap(mapId, role);
   }

   public void leaveTown(Role role) {
      int mapId = role.getPos().getTown() * 100 + role.getPos().getArea();
      SceneManager.INSTANCE.leaveMap(mapId, role);
   }

   public void move(Role role) {
      int mapId = role.getPos().getTown() * 100 + role.getPos().getArea();
      SceneManager.INSTANCE.movePosition(mapId, role);
   }

   public List<Role> getNearbyRoles(Role role) {
      int mapId = role.getPos().getTown() * 100 + role.getPos().getArea();
      return SceneManager.INSTANCE.getNearbyRoles(mapId, role);
   }
}
