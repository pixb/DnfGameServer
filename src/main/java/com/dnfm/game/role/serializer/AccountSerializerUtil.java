package com.dnfm.game.role.serializer;

import com.dnfm.game.role.model.Account;
import com.dnfm.game.utils.ClassScanner;
import java.util.ArrayList;
import java.util.List;

public class AccountSerializerUtil {
   private static final String SCAN_PATH = "com.dnfm.game";
   private static final List<IAccountPropSerializer> propSerializers = new ArrayList();

   public static void serialize(Account account) {
      for(IAccountPropSerializer handler : propSerializers) {
         handler.serialize(account);
      }

   }

   public static void deserialize(Account account) {
      for(IAccountPropSerializer handler : propSerializers) {
         handler.deserialize(account);
      }

   }

   static {
      for(Class<?> clazz : ClassScanner.listAllSubclasses("com.dnfm.game", IAccountPropSerializer.class)) {
         try {
            propSerializers.add((IAccountPropSerializer)clazz.newInstance());
         } catch (Exception var4) {
            System.exit(1);
         }
      }

   }
}
