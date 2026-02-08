package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_ARTIFACT;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CREATURE;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.List;

public class CharStorageBox {
   private int line = 1;
   private int storagegold;
   private int storagetera;
   private List<PT_EQUIP> equipitems = new ArrayList();
   private List<PT_EQUIP> titleitems = new ArrayList();
   private List<PT_EQUIP> flagitems = new ArrayList();
   private List<PT_STACKABLE> materialitems = new ArrayList();
   private List<PT_STACKABLE> consumeitems = new ArrayList();
   private List<PT_STACKABLE> carditems = new ArrayList();
   private List<PT_STACKABLE> emblemitems = new ArrayList();
   private List<PT_STACKABLE> epicpieceitems = new ArrayList();
   private List<PT_ARTIFACT> cartifactitems = new ArrayList();
   private List<PT_CREATURE> creatureitems = new ArrayList();
   private List<PT_AVATAR_ITEM> avataritems = new ArrayList();

   public PT_EQUIP getEquipItem(long guid) {
      for(PT_EQUIP item : this.equipitems) {
         if (item.getGuid() == guid) {
            return item;
         }
      }

      return null;
   }

   public PT_EQUIP getTitileItem(long guid) {
      for(PT_EQUIP item : this.titleitems) {
         if (item.getGuid() == guid) {
            return item;
         }
      }

      return null;
   }

   public PT_AVATAR_ITEM getAvatar(long guid) {
      for(PT_AVATAR_ITEM item : this.avataritems) {
         if (item.guid == guid) {
            return item;
         }
      }

      return null;
   }

   public PT_STACKABLE getConsume(int index) {
      for(PT_STACKABLE item : this.consumeitems) {
         if (item.index == index) {
            return item;
         }
      }

      return null;
   }

   public PT_STACKABLE getMaterial(int index) {
      for(PT_STACKABLE item : this.materialitems) {
         if (item.index == index) {
            return item;
         }
      }

      return null;
   }

   public PT_STACKABLE getCard(int index) {
      for(PT_STACKABLE item : this.carditems) {
         if (item.index == index) {
            return item;
         }
      }

      return null;
   }

   public PT_STACKABLE getEmblem(int index) {
      for(PT_STACKABLE item : this.emblemitems) {
         if (item.index == index) {
            return item;
         }
      }

      return null;
   }

   public PT_EQUIP getFlagItem(long guid) {
      for(PT_EQUIP item : this.flagitems) {
         if (item.getGuid() == guid) {
            return item;
         }
      }

      return null;
   }

   public void removeEquipItem(long guid) {
      PT_EQUIP ptEquip = new PT_EQUIP();

      for(PT_EQUIP item : this.equipitems) {
         if (item.getGuid() == guid) {
            ptEquip = item;
         }
      }

      this.equipitems.remove(ptEquip);
   }

   public void removeTitleItem(long guid) {
      PT_EQUIP ptEquip = new PT_EQUIP();

      for(PT_EQUIP item : this.titleitems) {
         if (item.getGuid() == guid) {
            ptEquip = item;
         }
      }

      this.titleitems.remove(ptEquip);
   }

   public void removeFlagItem(long guid) {
      PT_EQUIP ptEquip = new PT_EQUIP();

      for(PT_EQUIP item : this.flagitems) {
         if (item.getGuid() == guid) {
            ptEquip = item;
         }
      }

      this.flagitems.remove(ptEquip);
   }

   public void removeAvatar(long guid) {
      PT_AVATAR_ITEM ptAvatarItem = new PT_AVATAR_ITEM();

      for(PT_AVATAR_ITEM item : this.avataritems) {
         if (item.guid == guid) {
            ptAvatarItem = item;
         }
      }

      this.avataritems.remove(ptAvatarItem);
   }

   public void removeArtifact(long guid) {
      PT_ARTIFACT ptArtifact = new PT_ARTIFACT();

      for(PT_ARTIFACT item : this.cartifactitems) {
         if (item.guid == guid) {
            ptArtifact = item;
         }
      }

      this.avataritems.remove(ptArtifact);
   }

   public void subMaterial(int index, int count, boolean bind) {
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      int nowcount = 1;

      for(PT_STACKABLE item : this.materialitems) {
         if (item.getIndex() == index) {
            item.count = item.count - count;
            if (item.count == 0) {
               nowcount = 0;
               ptStackable = item;
            }
         }
      }

      if (nowcount == 0) {
         this.materialitems.remove(ptStackable);
      }

   }

   public void subConsumeitems(int index, int count, boolean bind) {
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      int nowcount = 1;

      for(PT_STACKABLE item : this.consumeitems) {
         if (item.getIndex() == index) {
            item.count = item.count - count;
            if (item.count == 0) {
               nowcount = 0;
               ptStackable = item;
            }
         }
      }

      if (nowcount == 0) {
         this.consumeitems.remove(ptStackable);
      }

   }

   public void subCarditems(int index, int count) {
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      int nowcount = 1;

      for(PT_STACKABLE item : this.carditems) {
         if (item.getIndex() == index) {
            item.count = item.count - count;
            if (item.count == 0) {
               nowcount = 0;
               ptStackable = item;
            }
         }
      }

      if (nowcount == 0) {
         this.carditems.remove(ptStackable);
      }

   }

   public void subEmblemitems(int index, int count) {
      for(PT_STACKABLE item : this.carditems) {
         if (item.getIndex() == index) {
            item.count = item.count - count;
         }
      }

   }

   public void subEpicpiece(int index, int count) {
      for(PT_STACKABLE item : this.epicpieceitems) {
         if (item.getIndex() == index) {
            item.count = item.count - count;
         }
      }

   }

   public int getLine() {
      return this.line;
   }

   public int getStoragegold() {
      return this.storagegold;
   }

   public int getStoragetera() {
      return this.storagetera;
   }

   public List<PT_EQUIP> getEquipitems() {
      return this.equipitems;
   }

   public List<PT_EQUIP> getTitleitems() {
      return this.titleitems;
   }

   public List<PT_EQUIP> getFlagitems() {
      return this.flagitems;
   }

   public List<PT_STACKABLE> getMaterialitems() {
      return this.materialitems;
   }

   public List<PT_STACKABLE> getConsumeitems() {
      return this.consumeitems;
   }

   public List<PT_STACKABLE> getCarditems() {
      return this.carditems;
   }

   public List<PT_STACKABLE> getEmblemitems() {
      return this.emblemitems;
   }

   public List<PT_STACKABLE> getEpicpieceitems() {
      return this.epicpieceitems;
   }

   public List<PT_ARTIFACT> getCartifactitems() {
      return this.cartifactitems;
   }

   public List<PT_CREATURE> getCreatureitems() {
      return this.creatureitems;
   }

   public List<PT_AVATAR_ITEM> getAvataritems() {
      return this.avataritems;
   }

   public void setLine(int line) {
      this.line = line;
   }

   public void setStoragegold(int storagegold) {
      this.storagegold = storagegold;
   }

   public void setStoragetera(int storagetera) {
      this.storagetera = storagetera;
   }

   public void setEquipitems(List<PT_EQUIP> equipitems) {
      this.equipitems = equipitems;
   }

   public void setTitleitems(List<PT_EQUIP> titleitems) {
      this.titleitems = titleitems;
   }

   public void setFlagitems(List<PT_EQUIP> flagitems) {
      this.flagitems = flagitems;
   }

   public void setMaterialitems(List<PT_STACKABLE> materialitems) {
      this.materialitems = materialitems;
   }

   public void setConsumeitems(List<PT_STACKABLE> consumeitems) {
      this.consumeitems = consumeitems;
   }

   public void setCarditems(List<PT_STACKABLE> carditems) {
      this.carditems = carditems;
   }

   public void setEmblemitems(List<PT_STACKABLE> emblemitems) {
      this.emblemitems = emblemitems;
   }

   public void setEpicpieceitems(List<PT_STACKABLE> epicpieceitems) {
      this.epicpieceitems = epicpieceitems;
   }

   public void setCartifactitems(List<PT_ARTIFACT> cartifactitems) {
      this.cartifactitems = cartifactitems;
   }

   public void setCreatureitems(List<PT_CREATURE> creatureitems) {
      this.creatureitems = creatureitems;
   }

   public void setAvataritems(List<PT_AVATAR_ITEM> avataritems) {
      this.avataritems = avataritems;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CharStorageBox)) {
         return false;
      } else {
         CharStorageBox other = (CharStorageBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLine() != other.getLine()) {
            return false;
         } else if (this.getStoragegold() != other.getStoragegold()) {
            return false;
         } else if (this.getStoragetera() != other.getStoragetera()) {
            return false;
         } else {
            Object this$equipitems = this.getEquipitems();
            Object other$equipitems = other.getEquipitems();
            if (this$equipitems == null) {
               if (other$equipitems != null) {
                  return false;
               }
            } else if (!this$equipitems.equals(other$equipitems)) {
               return false;
            }

            Object this$titleitems = this.getTitleitems();
            Object other$titleitems = other.getTitleitems();
            if (this$titleitems == null) {
               if (other$titleitems != null) {
                  return false;
               }
            } else if (!this$titleitems.equals(other$titleitems)) {
               return false;
            }

            Object this$flagitems = this.getFlagitems();
            Object other$flagitems = other.getFlagitems();
            if (this$flagitems == null) {
               if (other$flagitems != null) {
                  return false;
               }
            } else if (!this$flagitems.equals(other$flagitems)) {
               return false;
            }

            Object this$materialitems = this.getMaterialitems();
            Object other$materialitems = other.getMaterialitems();
            if (this$materialitems == null) {
               if (other$materialitems != null) {
                  return false;
               }
            } else if (!this$materialitems.equals(other$materialitems)) {
               return false;
            }

            Object this$consumeitems = this.getConsumeitems();
            Object other$consumeitems = other.getConsumeitems();
            if (this$consumeitems == null) {
               if (other$consumeitems != null) {
                  return false;
               }
            } else if (!this$consumeitems.equals(other$consumeitems)) {
               return false;
            }

            Object this$carditems = this.getCarditems();
            Object other$carditems = other.getCarditems();
            if (this$carditems == null) {
               if (other$carditems != null) {
                  return false;
               }
            } else if (!this$carditems.equals(other$carditems)) {
               return false;
            }

            Object this$emblemitems = this.getEmblemitems();
            Object other$emblemitems = other.getEmblemitems();
            if (this$emblemitems == null) {
               if (other$emblemitems != null) {
                  return false;
               }
            } else if (!this$emblemitems.equals(other$emblemitems)) {
               return false;
            }

            Object this$epicpieceitems = this.getEpicpieceitems();
            Object other$epicpieceitems = other.getEpicpieceitems();
            if (this$epicpieceitems == null) {
               if (other$epicpieceitems != null) {
                  return false;
               }
            } else if (!this$epicpieceitems.equals(other$epicpieceitems)) {
               return false;
            }

            Object this$cartifactitems = this.getCartifactitems();
            Object other$cartifactitems = other.getCartifactitems();
            if (this$cartifactitems == null) {
               if (other$cartifactitems != null) {
                  return false;
               }
            } else if (!this$cartifactitems.equals(other$cartifactitems)) {
               return false;
            }

            Object this$creatureitems = this.getCreatureitems();
            Object other$creatureitems = other.getCreatureitems();
            if (this$creatureitems == null) {
               if (other$creatureitems != null) {
                  return false;
               }
            } else if (!this$creatureitems.equals(other$creatureitems)) {
               return false;
            }

            Object this$avataritems = this.getAvataritems();
            Object other$avataritems = other.getAvataritems();
            if (this$avataritems == null) {
               if (other$avataritems != null) {
                  return false;
               }
            } else if (!this$avataritems.equals(other$avataritems)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CharStorageBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getLine();
      result = result * 59 + this.getStoragegold();
      result = result * 59 + this.getStoragetera();
      Object $equipitems = this.getEquipitems();
      result = result * 59 + ($equipitems == null ? 43 : $equipitems.hashCode());
      Object $titleitems = this.getTitleitems();
      result = result * 59 + ($titleitems == null ? 43 : $titleitems.hashCode());
      Object $flagitems = this.getFlagitems();
      result = result * 59 + ($flagitems == null ? 43 : $flagitems.hashCode());
      Object $materialitems = this.getMaterialitems();
      result = result * 59 + ($materialitems == null ? 43 : $materialitems.hashCode());
      Object $consumeitems = this.getConsumeitems();
      result = result * 59 + ($consumeitems == null ? 43 : $consumeitems.hashCode());
      Object $carditems = this.getCarditems();
      result = result * 59 + ($carditems == null ? 43 : $carditems.hashCode());
      Object $emblemitems = this.getEmblemitems();
      result = result * 59 + ($emblemitems == null ? 43 : $emblemitems.hashCode());
      Object $epicpieceitems = this.getEpicpieceitems();
      result = result * 59 + ($epicpieceitems == null ? 43 : $epicpieceitems.hashCode());
      Object $cartifactitems = this.getCartifactitems();
      result = result * 59 + ($cartifactitems == null ? 43 : $cartifactitems.hashCode());
      Object $creatureitems = this.getCreatureitems();
      result = result * 59 + ($creatureitems == null ? 43 : $creatureitems.hashCode());
      Object $avataritems = this.getAvataritems();
      result = result * 59 + ($avataritems == null ? 43 : $avataritems.hashCode());
      return result;
   }

   public String toString() {
      return "CharStorageBox(line=" + this.getLine() + ", storagegold=" + this.getStoragegold() + ", storagetera=" + this.getStoragetera() + ", equipitems=" + this.getEquipitems() + ", titleitems=" + this.getTitleitems() + ", flagitems=" + this.getFlagitems() + ", materialitems=" + this.getMaterialitems() + ", consumeitems=" + this.getConsumeitems() + ", carditems=" + this.getCarditems() + ", emblemitems=" + this.getEmblemitems() + ", epicpieceitems=" + this.getEpicpieceitems() + ", cartifactitems=" + this.getCartifactitems() + ", creatureitems=" + this.getCreatureitems() + ", avataritems=" + this.getAvataritems() + ")";
   }
}
