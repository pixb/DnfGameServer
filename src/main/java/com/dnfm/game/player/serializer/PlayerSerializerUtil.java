package com.dnfm.game.player.serializer;

import com.dnfm.game.role.model.Role;
import com.dnfm.game.utils.ClassScanner;
import java.util.ArrayList;
import java.util.List;

public class PlayerSerializerUtil {
   private static final String SCAN_PATH = "com.dnfm.game";
   private static final List<IPlayerPropSerializer> propSerializers = new ArrayList();

   public static void serialize(Role player) {
      for(IPlayerPropSerializer handler : propSerializers) {
         handler.serialize(player);
      }

   }

   public static void deserialize(Role player) {
      for(IPlayerPropSerializer handler : propSerializers) {
         handler.deserialize(player);
      }

   }

   static {
      for(Class<?> clazz : ClassScanner.listAllSubclasses("com.dnfm.game", IPlayerPropSerializer.class)) {
         try {
            propSerializers.add((IPlayerPropSerializer)clazz.newInstance());
         } catch (Exception var4) {
            System.exit(1);
         }
      }

   }
}
