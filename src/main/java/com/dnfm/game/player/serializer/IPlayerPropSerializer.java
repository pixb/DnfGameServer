package com.dnfm.game.player.serializer;

import com.dnfm.game.role.model.Role;

public interface IPlayerPropSerializer {
   void serialize(Role var1);

   void deserialize(Role var1);
}
