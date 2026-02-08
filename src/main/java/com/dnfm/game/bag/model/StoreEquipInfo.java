package com.dnfm.game.bag.model;

import com.dnfm.game.equip.model.EquipField;
import com.dnfm.mina.annotation.ListField;
import java.util.List;

public class StoreEquipInfo {
   private byte index = 1;
   private short pos = 201;
   private short size;
   @ListField(2)
   private List<EquipField> equipFields;

   public byte getIndex() {
      return this.index;
   }

   public void setIndex(byte index) {
      this.index = index;
   }

   public short getPos() {
      return this.pos;
   }

   public void setPos(short pos) {
      this.pos = pos;
   }

   public short getSize() {
      return -1;
   }

   public void setSize(short size) {
      this.size = size;
   }

   public List<EquipField> getEquipFields() {
      return this.equipFields;
   }

   public void setEquipFields(List<EquipField> equipFields) {
      this.equipFields = equipFields;
      this.setSize(this.getSize());
   }
}
