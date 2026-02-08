package com.dnfm.mina.protobuf;

import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta
public class ENUM_TEAM extends Message {
   public static enum T {
      PLAYER,
      RED,
      BLUE,
      YELLOW,
      GREEN,
      BLACK,
      PINK,
      GRAY,
      ORANGE,
      PURPLE,
      BROWN,
      ENEMY,
      ENEMY_END,
      NEUTRAL,
      NEUTRAL_END,
      OBSERVER,
      OBSERVER_END;
   }
}
