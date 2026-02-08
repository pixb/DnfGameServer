package com.dnfm.game.enter;

public class MapTransmission {
   private String name;
   private short X;
   private short Y;
   private short dir;
   private short a = 0;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public short getX() {
      return this.X;
   }

   public void setX(short x) {
      this.X = x;
   }

   public short getY() {
      return this.Y;
   }

   public void setY(short y) {
      this.Y = y;
   }

   public short getDir() {
      return this.dir;
   }

   public void setDir(short dir) {
      this.dir = dir;
   }

   public short getA() {
      return this.a;
   }

   public void setA(short a) {
      this.a = a;
   }
}
