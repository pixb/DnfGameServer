package com.dnfm.cross.login.service;

import com.dnfm.cross.CrossServerConfig;
import com.dnfm.game.role.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerNativeTransferService {
   @Autowired
   private CrossServerConfig cSConfig;

   public void beginTransfer(Role role) {
   }
}
