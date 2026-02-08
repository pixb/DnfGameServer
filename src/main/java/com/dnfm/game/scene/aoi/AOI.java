package com.dnfm.game.scene.aoi;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.protobuf.Message;
import java.util.List;

public abstract class AOI {
   private int id;

   public abstract void enter(Role var1);

   public abstract void leave(Role var1);

   public abstract void move(Role var1);

   public abstract void sendMessages(Role var1, Message... var2);

   public abstract List<Role> getNearbyRoles(Role var1);

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
