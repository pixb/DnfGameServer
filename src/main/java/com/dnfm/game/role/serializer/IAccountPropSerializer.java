package com.dnfm.game.role.serializer;

import com.dnfm.game.role.model.Account;

public interface IAccountPropSerializer {
   void serialize(Account var1);

   void deserialize(Account var1);
}
