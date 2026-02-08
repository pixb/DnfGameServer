package com.dnfm.game.bag.model;

import com.dnfm.game.config.CharacterScoreConfig;
import com.dnfm.game.config.DnfSystemConfig;
import com.dnfm.game.config.Equip;
import com.dnfm.game.config.UpgradeScoreConfig;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.protobuf.PT_ARTIFACT;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CREATURE;
import com.dnfm.mina.protobuf.PT_EMBLEM;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquippedBox {
   private Map<Long, PT_EQUIPPED> equiplistMap = new HashMap();
   private Map<Long, PT_CREATURE> creaturelistMap = new HashMap();
   private Map<Long, PT_AVATAR_ITEM> avatarlistMap = new HashMap();
   private Map<Long, PT_ARTIFACT> cartifactlistMap = new HashMap();
   private Map<Long, PT_EQUIPPED> equipskinlistMap = new HashMap();
   private Map<Long, PT_AVATAR_ITEM> avatarskinlistMap = new HashMap();
   private Map<Long, PT_SKIN_ITEM> skinlistMap = new HashMap();
   private Map<Long, PT_AVATAR_ITEM> sdavatarlistMap = new HashMap();

   public PT_EQUIPPED getEquippedBySlot(int slot) {
      for(PT_EQUIPPED equip : this.equiplistMap.values()) {
         if (equip.slot == slot) {
            return equip;
         }
      }

      return null;
   }

   public PT_AVATAR_ITEM getAvatarBySlot(int slot) {
      for(PT_AVATAR_ITEM equip : this.avatarlistMap.values()) {
         if (equip.slot == slot) {
            return equip;
         }
      }

      return null;
   }

   public PT_AVATAR_ITEM getAvatarSkinBySlot(int slot) {
      for(PT_AVATAR_ITEM equip : this.avatarskinlistMap.values()) {
         if (equip.slot == slot) {
            return equip;
         }
      }

      return null;
   }

   public void addEquip(PT_EQUIPPED equip) {
      this.equiplistMap.put(equip.guid, equip);
   }

   public void addEquipped(PT_EQUIP equip) {
      PT_EQUIPPED equipped = new PT_EQUIPPED();
      equipped.score = equip.score;
      equipped.equiptype = equip.equiptype;
      equipped.minlevel = equip.minlevel;
      equipped.guid = equip.guid;
      equipped.index = equip.index;
      equipped.quality = equip.quality;
      equipped.endurance = equip.endurance;
      equipped.slot = equip.slot;
      if (equip.upgrade != null) {
         equipped.upgrade = equip.upgrade;
      }

      if (equip.upgradepoint != null) {
         equipped.upgradepoint = equip.upgradepoint;
      }

      if (equip.rappearance != null) {
         equipped.rappearance = equip.rappearance;
      }

      if (equip.roption != null) {
         equipped.roption = equip.roption;
      }

      if (equip.rnoption != null) {
         equipped.roption = equip.roption;
      }

      if (equip.card != null) {
         equipped.card = equip.card;
      }

      if (equip.emblem != null) {
         equipped.emblem = equip.emblem;
      }

      this.equiplistMap.put(equipped.guid, equipped);
   }

   public void updateEquip(PT_EQUIPPED equip) {
      this.equiplistMap.put(equip.guid, equip);
   }

   public void removeEquip(PT_EQUIPPED equip) {
      this.equiplistMap.remove(equip.guid);
   }

   public void removeAllEquip() {
      List<PT_EQUIPPED> deleteEquip = new ArrayList();

      for(PT_EQUIPPED ptEquipped : this.equiplistMap.values()) {
         if (ptEquipped.slot == 13 || ptEquipped.slot == 14 || ptEquipped.slot == 15 || ptEquipped.slot == 16 || ptEquipped.slot == 17 || ptEquipped.slot == 18 || ptEquipped.slot == 19 || ptEquipped.slot == 20) {
            deleteEquip.add(ptEquipped);
         }
      }

      for(PT_EQUIPPED ptEquipped : deleteEquip) {
         this.equiplistMap.remove(ptEquipped.guid);
      }

   }

   public void removeAvatar(PT_AVATAR_ITEM equip) {
      this.avatarlistMap.remove(equip.guid);
   }

   public void removeAvatar(long guid) {
      this.avatarlistMap.remove(guid);
   }

   public void removeSdavatar(PT_AVATAR_ITEM equip) {
      this.avatarskinlistMap.remove(equip.guid);
   }

   public void removeAvatarskin(long guid) {
      this.avatarskinlistMap.remove(guid);
   }

   public void addCreature(PT_CREATURE creature) {
      this.creaturelistMap.put(creature.guid, creature);
   }

   public void addAvatar(PT_AVATAR_ITEM avatar) {
      this.avatarlistMap.put(avatar.guid, avatar);
   }

   public void addCartifact(PT_ARTIFACT cartifact) {
      this.cartifactlistMap.put(cartifact.guid, cartifact);
   }

   public void addEquipskin(PT_EQUIPPED equipskin) {
      this.equipskinlistMap.put(equipskin.guid, equipskin);
   }

   public void addAvatarskin(PT_AVATAR_ITEM avatarskin) {
      this.avatarskinlistMap.put(avatarskin.guid, avatarskin);
   }

   public void addSkin(PT_SKIN_ITEM skin) {
      this.skinlistMap.put(skin.guid, skin);
   }

   public void addSdavatar(PT_AVATAR_ITEM sdavatar) {
      this.avatarskinlistMap.put(sdavatar.guid, sdavatar);
   }

   public List<PT_EQUIPPED> getEquipped(Role role) {
      EquippedBox equipBox = role.getEquippedBox();
      List<PT_EQUIPPED> equips = new ArrayList();

      for(PT_EQUIPPED pe : equipBox.getEquiplist()) {
         PT_EQUIPPED pt_equipped = new PT_EQUIPPED();
         pt_equipped.index = pe.index;
         pt_equipped.slot = pe.slot;
         equips.add(pt_equipped);
      }

      return equips;
   }

   public List<PT_EQUIP> getEquipList(Role role) {
      EquippedBox equipBox = role.getEquippedBox();
      List<PT_EQUIP> equips = new ArrayList();

      for(PT_EQUIPPED pe : equipBox.getEquiplist()) {
         PT_EQUIP pt_equipped = new PT_EQUIP();
         pt_equipped.score = pe.score;
         pt_equipped.equiptype = pe.equiptype;
         pt_equipped.minlevel = pe.minlevel;
         pt_equipped.index = pe.index;
         pt_equipped.guid = pe.guid;
         pt_equipped.quality = pe.quality;
         pt_equipped.endurance = pe.endurance;
         pt_equipped.slot = pe.slot;
         if (pe.upgrade != null) {
            pt_equipped.upgrade = pe.upgrade;
         }

         if (pe.upgradepoint != null) {
            pt_equipped.upgradepoint = pe.upgradepoint;
         }

         if (pe.rappearance != null) {
            pt_equipped.rappearance = pe.rappearance;
         }

         if (pe.roption != null) {
            pt_equipped.roption = pe.roption;
         }

         if (pe.rnoption != null) {
            pt_equipped.roption = pe.roption;
         }

         if (pe.card != null) {
            pt_equipped.card = pe.card;
         }

         if (pe.emblem != null) {
            pt_equipped.emblem = pe.emblem;
         }

         equips.add(pt_equipped);
      }

      return equips;
   }

   public PT_EQUIPPED getEquipped(long guid) {
      return (PT_EQUIPPED)this.equiplistMap.get(guid);
   }

   public PT_EQUIPPED getEquipped2(int index) {
      for(PT_EQUIPPED pe : this.equiplistMap.values()) {
         if (pe.index == index) {
            return pe;
         }
      }

      return null;
   }

   public PT_EQUIPPED addEmblem(PT_EQUIPPED equ, PT_EMBLEM emb) {
      for(int i = 0; i < equ.emblem.size(); ++i) {
         if (((PT_EMBLEM)equ.emblem.get(i)).index == emb.index) {
            PT_EMBLEM var4 = (PT_EMBLEM)equ.emblem.get(i);
            Integer var5 = var4.count;
            Integer var6 = var4.count = var4.count + 1;
            return equ;
         }
      }

      equ.emblem.add(emb);
      return equ;
   }

   public int removeEmblem(PT_EQUIPPED equ, int slot) {
      int removeIndex = -1;

      for(int i = 0; i < equ.emblem.size(); ++i) {
         PT_EMBLEM emblem = (PT_EMBLEM)equ.emblem.get(i);
         if (slot == emblem.slot) {
            removeIndex = i;
            break;
         }
      }

      return removeIndex;
   }

   public int removeEmblem2(PT_EQUIP equ, int slot) {
      int removeIndex = -1;

      for(int i = 0; i < equ.emblem.size(); ++i) {
         PT_EMBLEM emblem = (PT_EMBLEM)equ.emblem.get(i);
         if (slot == emblem.slot) {
            removeIndex = i;
            break;
         }
      }

      return removeIndex;
   }

   public List<PT_EQUIPPED> getEquiplist() {
      return new ArrayList(this.equiplistMap.values());
   }

   public List<PT_EQUIP> getEquippedlist() {
      List<PT_EQUIP> equips = new ArrayList();

      for(PT_EQUIPPED ptEquipped : this.equiplistMap.values()) {
         equips.add(EquipDataPool.changeEquip(ptEquipped));
      }

      return equips;
   }

   public List<PT_CREATURE> getCreaturelist() {
      return new ArrayList(this.creaturelistMap.values());
   }

   public List<PT_AVATAR_ITEM> getAvatarlist() {
      return new ArrayList(this.avatarlistMap.values());
   }

   public List<PT_ARTIFACT> getCartifactlist() {
      return new ArrayList(this.cartifactlistMap.values());
   }

   public List<PT_EQUIPPED> getEquipskinlist() {
      return new ArrayList(this.equipskinlistMap.values());
   }

   public List<PT_AVATAR_ITEM> getAvatarskinlist() {
      return new ArrayList(this.avatarskinlistMap.values());
   }

   public List<PT_SKIN_ITEM> getSkinlist() {
      return new ArrayList(this.skinlistMap.values());
   }

   public List<PT_AVATAR_ITEM> getSdavatarlist() {
      return new ArrayList(this.sdavatarlistMap.values());
   }

   public int getRoleEquipScore(Role role) {
      Integer allScore = 0;
      allScore = allScore + 229;
      allScore = allScore + role.getLevel() * 73;

      for(PT_EQUIPPED pt_equipped : role.getEquippedBox().getEquiplist()) {
         allScore = allScore + this.getEquipScore(role, pt_equipped);
      }

      return allScore;
   }

   public int getEquipScore(Role role, PT_EQUIPPED roleEquip) {
      Integer defScore = 0;
      Integer supScore = 0;
      Equip equip = EquipDataPool.getEquip(roleEquip.getIndex());
      defScore = equip.getScore();
      defScore = defScore + (int)Math.floor((double)(roleEquip.getQuality() - 50) * 0.15 * (double)equip.getScore() / (double)100.0F);
      if (roleEquip.getUpgrade() > 0) {
         UpgradeScoreConfig upgradeScoreConfig = DnfSystemConfig.getUpgradeScoreConfig(roleEquip.getUpgrade());
         CharacterScoreConfig characterScoreConfig = DnfSystemConfig.getCharacterScoreConfig(role.getJob(), equip.getGroupname());
         double pct = (double)0.0F;
         switch (role.getGrowtype()) {
            case 1:
               pct = characterScoreConfig.getVal1();
               break;
            case 2:
               pct = characterScoreConfig.getVal2();
               break;
            case 3:
               pct = characterScoreConfig.getVal3();
               break;
            case 4:
               pct = characterScoreConfig.getVal4();
               break;
            default:
               pct = (double)1.0F;
         }

         supScore = supScore + (int)Math.floor((double)equip.getScore() * pct * upgradeScoreConfig.getVal2() * 0.01 + upgradeScoreConfig.getVal1());
      }

      if (roleEquip.getRoption() != null && roleEquip.getRoption().size() > 0) {
         for(int i = 0; i < roleEquip.getRoption().size(); ++i) {
            Integer m1 = (int)Math.floor(DnfSystemConfig.getMagicSpell(equip.getRarity()) * (double)(2 + equip.getMinlevel() * 10 / 20));
            supScore = supScore + m1;
         }
      }

      if (roleEquip.getCard() != null && roleEquip.getCard().size() > 0) {
         Integer scoreCard = 0;

         for(PT_STACKABLE stackable : roleEquip.getCard()) {
            Equip equipCard = EquipDataPool.getEquip(stackable.index);
            if (null != equipCard) {
               scoreCard = scoreCard + equipCard.getScore();
            } else {
               scoreCard = scoreCard + 133;
            }
         }

         supScore = supScore + scoreCard;
      }

      if (roleEquip.getEmblem() != null && roleEquip.getEmblem().size() > 0) {
         Integer scoreEmblem = 0;

         for(PT_EMBLEM emblem : roleEquip.getEmblem()) {
            Equip equipCard = EquipDataPool.getEquip(emblem.index);
            scoreEmblem = scoreEmblem + equipCard.getScore();
         }

         supScore = supScore + scoreEmblem;
      }

      return defScore + supScore;
   }

   public Map<Long, PT_EQUIPPED> getEquiplistMap() {
      return this.equiplistMap;
   }

   public Map<Long, PT_CREATURE> getCreaturelistMap() {
      return this.creaturelistMap;
   }

   public Map<Long, PT_AVATAR_ITEM> getAvatarlistMap() {
      return this.avatarlistMap;
   }

   public Map<Long, PT_ARTIFACT> getCartifactlistMap() {
      return this.cartifactlistMap;
   }

   public Map<Long, PT_EQUIPPED> getEquipskinlistMap() {
      return this.equipskinlistMap;
   }

   public Map<Long, PT_AVATAR_ITEM> getAvatarskinlistMap() {
      return this.avatarskinlistMap;
   }

   public Map<Long, PT_SKIN_ITEM> getSkinlistMap() {
      return this.skinlistMap;
   }

   public Map<Long, PT_AVATAR_ITEM> getSdavatarlistMap() {
      return this.sdavatarlistMap;
   }

   public void setEquiplistMap(Map<Long, PT_EQUIPPED> equiplistMap) {
      this.equiplistMap = equiplistMap;
   }

   public void setCreaturelistMap(Map<Long, PT_CREATURE> creaturelistMap) {
      this.creaturelistMap = creaturelistMap;
   }

   public void setAvatarlistMap(Map<Long, PT_AVATAR_ITEM> avatarlistMap) {
      this.avatarlistMap = avatarlistMap;
   }

   public void setCartifactlistMap(Map<Long, PT_ARTIFACT> cartifactlistMap) {
      this.cartifactlistMap = cartifactlistMap;
   }

   public void setEquipskinlistMap(Map<Long, PT_EQUIPPED> equipskinlistMap) {
      this.equipskinlistMap = equipskinlistMap;
   }

   public void setAvatarskinlistMap(Map<Long, PT_AVATAR_ITEM> avatarskinlistMap) {
      this.avatarskinlistMap = avatarskinlistMap;
   }

   public void setSkinlistMap(Map<Long, PT_SKIN_ITEM> skinlistMap) {
      this.skinlistMap = skinlistMap;
   }

   public void setSdavatarlistMap(Map<Long, PT_AVATAR_ITEM> sdavatarlistMap) {
      this.sdavatarlistMap = sdavatarlistMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EquippedBox)) {
         return false;
      } else {
         EquippedBox other = (EquippedBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$equiplistMap = this.getEquiplistMap();
            Object other$equiplistMap = other.getEquiplistMap();
            if (this$equiplistMap == null) {
               if (other$equiplistMap != null) {
                  return false;
               }
            } else if (!this$equiplistMap.equals(other$equiplistMap)) {
               return false;
            }

            Object this$creaturelistMap = this.getCreaturelistMap();
            Object other$creaturelistMap = other.getCreaturelistMap();
            if (this$creaturelistMap == null) {
               if (other$creaturelistMap != null) {
                  return false;
               }
            } else if (!this$creaturelistMap.equals(other$creaturelistMap)) {
               return false;
            }

            Object this$avatarlistMap = this.getAvatarlistMap();
            Object other$avatarlistMap = other.getAvatarlistMap();
            if (this$avatarlistMap == null) {
               if (other$avatarlistMap != null) {
                  return false;
               }
            } else if (!this$avatarlistMap.equals(other$avatarlistMap)) {
               return false;
            }

            Object this$cartifactlistMap = this.getCartifactlistMap();
            Object other$cartifactlistMap = other.getCartifactlistMap();
            if (this$cartifactlistMap == null) {
               if (other$cartifactlistMap != null) {
                  return false;
               }
            } else if (!this$cartifactlistMap.equals(other$cartifactlistMap)) {
               return false;
            }

            Object this$equipskinlistMap = this.getEquipskinlistMap();
            Object other$equipskinlistMap = other.getEquipskinlistMap();
            if (this$equipskinlistMap == null) {
               if (other$equipskinlistMap != null) {
                  return false;
               }
            } else if (!this$equipskinlistMap.equals(other$equipskinlistMap)) {
               return false;
            }

            Object this$avatarskinlistMap = this.getAvatarskinlistMap();
            Object other$avatarskinlistMap = other.getAvatarskinlistMap();
            if (this$avatarskinlistMap == null) {
               if (other$avatarskinlistMap != null) {
                  return false;
               }
            } else if (!this$avatarskinlistMap.equals(other$avatarskinlistMap)) {
               return false;
            }

            Object this$skinlistMap = this.getSkinlistMap();
            Object other$skinlistMap = other.getSkinlistMap();
            if (this$skinlistMap == null) {
               if (other$skinlistMap != null) {
                  return false;
               }
            } else if (!this$skinlistMap.equals(other$skinlistMap)) {
               return false;
            }

            Object this$sdavatarlistMap = this.getSdavatarlistMap();
            Object other$sdavatarlistMap = other.getSdavatarlistMap();
            if (this$sdavatarlistMap == null) {
               if (other$sdavatarlistMap != null) {
                  return false;
               }
            } else if (!this$sdavatarlistMap.equals(other$sdavatarlistMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EquippedBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $equiplistMap = this.getEquiplistMap();
      result = result * 59 + ($equiplistMap == null ? 43 : $equiplistMap.hashCode());
      Object $creaturelistMap = this.getCreaturelistMap();
      result = result * 59 + ($creaturelistMap == null ? 43 : $creaturelistMap.hashCode());
      Object $avatarlistMap = this.getAvatarlistMap();
      result = result * 59 + ($avatarlistMap == null ? 43 : $avatarlistMap.hashCode());
      Object $cartifactlistMap = this.getCartifactlistMap();
      result = result * 59 + ($cartifactlistMap == null ? 43 : $cartifactlistMap.hashCode());
      Object $equipskinlistMap = this.getEquipskinlistMap();
      result = result * 59 + ($equipskinlistMap == null ? 43 : $equipskinlistMap.hashCode());
      Object $avatarskinlistMap = this.getAvatarskinlistMap();
      result = result * 59 + ($avatarskinlistMap == null ? 43 : $avatarskinlistMap.hashCode());
      Object $skinlistMap = this.getSkinlistMap();
      result = result * 59 + ($skinlistMap == null ? 43 : $skinlistMap.hashCode());
      Object $sdavatarlistMap = this.getSdavatarlistMap();
      result = result * 59 + ($sdavatarlistMap == null ? 43 : $sdavatarlistMap.hashCode());
      return result;
   }

   public String toString() {
      return "EquippedBox(equiplistMap=" + this.getEquiplistMap() + ", creaturelistMap=" + this.getCreaturelistMap() + ", avatarlistMap=" + this.getAvatarlistMap() + ", cartifactlistMap=" + this.getCartifactlistMap() + ", equipskinlistMap=" + this.getEquipskinlistMap() + ", avatarskinlistMap=" + this.getAvatarskinlistMap() + ", skinlistMap=" + this.getSkinlistMap() + ", sdavatarlistMap=" + this.getSdavatarlistMap() + ")";
   }
}
