package com.dnfm.game.pet.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class PetBox implements Serializable {
   private int currentPetId;
   private PetStore petStore = new PetStore();
   private Map<Integer, Pet> allPets = new HashMap();

   public void addNewPet(Pet pet) {
      this.allPets.put(pet.getId(), pet);
   }

   public void removePet(Pet pet) {
      this.allPets.remove(pet.getId());
   }

   public Set<Integer> allPets() {
      Set<Integer> set = new HashSet();
      return set;
   }

   public Pet getPetByPetId(int petId) {
      if (!this.allPets().contains(petId)) {
         return null;
      } else {
         for(Map.Entry<Integer, Pet> entry : this.allPets.entrySet()) {
            Pet pet = (Pet)entry.getValue();
            if (pet.getId() == petId) {
               return pet;
            }
         }

         return null;
      }
   }

   public Pet getEquippedPetByPosition(byte pos) {
      Set<Integer> equipped = this.allPets();

      for(Map.Entry<Integer, Pet> entry : this.allPets.entrySet()) {
         Pet pet = (Pet)entry.getValue();
         if (equipped.contains(pet.getId()) && pet.getPosition() == pos) {
            return pet;
         }
      }

      return null;
   }

   public void setCurrentPetId(int currentPetId) {
      this.currentPetId = currentPetId;
   }

   public void setPetStore(PetStore petStore) {
      this.petStore = petStore;
   }

   public void setAllPets(Map<Integer, Pet> allPets) {
      this.allPets = allPets;
   }

   public int getCurrentPetId() {
      return this.currentPetId;
   }

   public PetStore getPetStore() {
      return this.petStore;
   }

   public Map<Integer, Pet> getAllPets() {
      return this.allPets;
   }
}
