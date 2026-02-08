package com.dnfm.game.pet.model;

import java.util.HashMap;
import org.nutz.dao.entity.annotation.Comment;

public class PetStore {
   private int id;
   @Comment("主人ID")
   private int hostId;
   @Comment("宠物位置ID")
   private HashMap<Short, Integer> petInfo = new HashMap();

   public HashMap<Short, Integer> getPetInfo() {
      return this.petInfo;
   }

   public void setPetInfo(HashMap<Short, Integer> petInfo) {
      this.petInfo = petInfo;
   }

   public int getHostId() {
      return this.hostId;
   }

   public void setHostId(int hostId) {
      this.hostId = hostId;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
