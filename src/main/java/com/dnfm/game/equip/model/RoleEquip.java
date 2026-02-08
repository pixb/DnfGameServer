package com.dnfm.game.equip.model;

import com.dnfm.mina.protobuf.PT_CRACK;
import com.dnfm.mina.protobuf.PT_EMBLEM;
import com.dnfm.mina.protobuf.PT_RANDOMOPTION_ITEM;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class RoleEquip {
   private static final Logger log = LoggerFactory.getLogger(RoleEquip.class);
   private Integer id;
   private Integer index;
   private Integer slot = -1;
   private Long guid;
   private Integer upgrade;
   private Integer quality;
   private Integer endurance;
   private Integer enchant;
   private Integer reforge;
   private Integer reforgeexp;
   private Integer amplify;
   private Integer aoption;
   private List<PT_EMBLEM> emblem;
   private List<PT_STACKABLE> card;
   private Integer scount;
   private Integer tcount;
   private Long expiretime;
   private Boolean rappearance;
   private List<PT_RANDOMOPTION_ITEM> roption;
   private List<PT_RANDOMOPTION_ITEM> rnoption;
   private Integer skin;
   private Long skinguid;
   private Boolean locked;
   private Boolean seal;
   private Integer enchantindex;
   private List<PT_CRACK> crack;
   private Integer sindex;
   private Integer sealing;
   private Integer upgradepoint;
   private Integer gaizaoJindu;
   private Integer score = 1;
   private Long roleId;
   private String type;
   private String name;

   public Integer getId() {
      return this.id;
   }

   public Integer getIndex() {
      return this.index;
   }

   public Integer getSlot() {
      return this.slot;
   }

   public Long getGuid() {
      return this.guid;
   }

   public Integer getUpgrade() {
      return this.upgrade;
   }

   public Integer getQuality() {
      return this.quality;
   }

   public Integer getEndurance() {
      return this.endurance;
   }

   public Integer getEnchant() {
      return this.enchant;
   }

   public Integer getReforge() {
      return this.reforge;
   }

   public Integer getReforgeexp() {
      return this.reforgeexp;
   }

   public Integer getAmplify() {
      return this.amplify;
   }

   public Integer getAoption() {
      return this.aoption;
   }

   public List<PT_EMBLEM> getEmblem() {
      return this.emblem;
   }

   public List<PT_STACKABLE> getCard() {
      return this.card;
   }

   public Integer getScount() {
      return this.scount;
   }

   public Integer getTcount() {
      return this.tcount;
   }

   public Long getExpiretime() {
      return this.expiretime;
   }

   public Boolean getRappearance() {
      return this.rappearance;
   }

   public List<PT_RANDOMOPTION_ITEM> getRoption() {
      return this.roption;
   }

   public List<PT_RANDOMOPTION_ITEM> getRnoption() {
      return this.rnoption;
   }

   public Integer getSkin() {
      return this.skin;
   }

   public Long getSkinguid() {
      return this.skinguid;
   }

   public Boolean getLocked() {
      return this.locked;
   }

   public Boolean getSeal() {
      return this.seal;
   }

   public Integer getEnchantindex() {
      return this.enchantindex;
   }

   public List<PT_CRACK> getCrack() {
      return this.crack;
   }

   public Integer getSindex() {
      return this.sindex;
   }

   public Integer getSealing() {
      return this.sealing;
   }

   public Integer getUpgradepoint() {
      return this.upgradepoint;
   }

   public Integer getGaizaoJindu() {
      return this.gaizaoJindu;
   }

   public Integer getScore() {
      return this.score;
   }

   public Long getRoleId() {
      return this.roleId;
   }

   public String getType() {
      return this.type;
   }

   public String getName() {
      return this.name;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public void setIndex(Integer index) {
      this.index = index;
   }

   public void setSlot(Integer slot) {
      this.slot = slot;
   }

   public void setGuid(Long guid) {
      this.guid = guid;
   }

   public void setUpgrade(Integer upgrade) {
      this.upgrade = upgrade;
   }

   public void setQuality(Integer quality) {
      this.quality = quality;
   }

   public void setEndurance(Integer endurance) {
      this.endurance = endurance;
   }

   public void setEnchant(Integer enchant) {
      this.enchant = enchant;
   }

   public void setReforge(Integer reforge) {
      this.reforge = reforge;
   }

   public void setReforgeexp(Integer reforgeexp) {
      this.reforgeexp = reforgeexp;
   }

   public void setAmplify(Integer amplify) {
      this.amplify = amplify;
   }

   public void setAoption(Integer aoption) {
      this.aoption = aoption;
   }

   public void setEmblem(List<PT_EMBLEM> emblem) {
      this.emblem = emblem;
   }

   public void setCard(List<PT_STACKABLE> card) {
      this.card = card;
   }

   public void setScount(Integer scount) {
      this.scount = scount;
   }

   public void setTcount(Integer tcount) {
      this.tcount = tcount;
   }

   public void setExpiretime(Long expiretime) {
      this.expiretime = expiretime;
   }

   public void setRappearance(Boolean rappearance) {
      this.rappearance = rappearance;
   }

   public void setRoption(List<PT_RANDOMOPTION_ITEM> roption) {
      this.roption = roption;
   }

   public void setRnoption(List<PT_RANDOMOPTION_ITEM> rnoption) {
      this.rnoption = rnoption;
   }

   public void setSkin(Integer skin) {
      this.skin = skin;
   }

   public void setSkinguid(Long skinguid) {
      this.skinguid = skinguid;
   }

   public void setLocked(Boolean locked) {
      this.locked = locked;
   }

   public void setSeal(Boolean seal) {
      this.seal = seal;
   }

   public void setEnchantindex(Integer enchantindex) {
      this.enchantindex = enchantindex;
   }

   public void setCrack(List<PT_CRACK> crack) {
      this.crack = crack;
   }

   public void setSindex(Integer sindex) {
      this.sindex = sindex;
   }

   public void setSealing(Integer sealing) {
      this.sealing = sealing;
   }

   public void setUpgradepoint(Integer upgradepoint) {
      this.upgradepoint = upgradepoint;
   }

   public void setGaizaoJindu(Integer gaizaoJindu) {
      this.gaizaoJindu = gaizaoJindu;
   }

   public void setScore(Integer score) {
      this.score = score;
   }

   public void setRoleId(Long roleId) {
      this.roleId = roleId;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RoleEquip)) {
         return false;
      } else {
         RoleEquip other = (RoleEquip)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$index = this.getIndex();
            Object other$index = other.getIndex();
            if (this$index == null) {
               if (other$index != null) {
                  return false;
               }
            } else if (!this$index.equals(other$index)) {
               return false;
            }

            Object this$slot = this.getSlot();
            Object other$slot = other.getSlot();
            if (this$slot == null) {
               if (other$slot != null) {
                  return false;
               }
            } else if (!this$slot.equals(other$slot)) {
               return false;
            }

            Object this$guid = this.getGuid();
            Object other$guid = other.getGuid();
            if (this$guid == null) {
               if (other$guid != null) {
                  return false;
               }
            } else if (!this$guid.equals(other$guid)) {
               return false;
            }

            Object this$upgrade = this.getUpgrade();
            Object other$upgrade = other.getUpgrade();
            if (this$upgrade == null) {
               if (other$upgrade != null) {
                  return false;
               }
            } else if (!this$upgrade.equals(other$upgrade)) {
               return false;
            }

            Object this$quality = this.getQuality();
            Object other$quality = other.getQuality();
            if (this$quality == null) {
               if (other$quality != null) {
                  return false;
               }
            } else if (!this$quality.equals(other$quality)) {
               return false;
            }

            Object this$endurance = this.getEndurance();
            Object other$endurance = other.getEndurance();
            if (this$endurance == null) {
               if (other$endurance != null) {
                  return false;
               }
            } else if (!this$endurance.equals(other$endurance)) {
               return false;
            }

            Object this$enchant = this.getEnchant();
            Object other$enchant = other.getEnchant();
            if (this$enchant == null) {
               if (other$enchant != null) {
                  return false;
               }
            } else if (!this$enchant.equals(other$enchant)) {
               return false;
            }

            Object this$reforge = this.getReforge();
            Object other$reforge = other.getReforge();
            if (this$reforge == null) {
               if (other$reforge != null) {
                  return false;
               }
            } else if (!this$reforge.equals(other$reforge)) {
               return false;
            }

            Object this$reforgeexp = this.getReforgeexp();
            Object other$reforgeexp = other.getReforgeexp();
            if (this$reforgeexp == null) {
               if (other$reforgeexp != null) {
                  return false;
               }
            } else if (!this$reforgeexp.equals(other$reforgeexp)) {
               return false;
            }

            Object this$amplify = this.getAmplify();
            Object other$amplify = other.getAmplify();
            if (this$amplify == null) {
               if (other$amplify != null) {
                  return false;
               }
            } else if (!this$amplify.equals(other$amplify)) {
               return false;
            }

            Object this$aoption = this.getAoption();
            Object other$aoption = other.getAoption();
            if (this$aoption == null) {
               if (other$aoption != null) {
                  return false;
               }
            } else if (!this$aoption.equals(other$aoption)) {
               return false;
            }

            Object this$emblem = this.getEmblem();
            Object other$emblem = other.getEmblem();
            if (this$emblem == null) {
               if (other$emblem != null) {
                  return false;
               }
            } else if (!this$emblem.equals(other$emblem)) {
               return false;
            }

            Object this$card = this.getCard();
            Object other$card = other.getCard();
            if (this$card == null) {
               if (other$card != null) {
                  return false;
               }
            } else if (!this$card.equals(other$card)) {
               return false;
            }

            Object this$scount = this.getScount();
            Object other$scount = other.getScount();
            if (this$scount == null) {
               if (other$scount != null) {
                  return false;
               }
            } else if (!this$scount.equals(other$scount)) {
               return false;
            }

            Object this$tcount = this.getTcount();
            Object other$tcount = other.getTcount();
            if (this$tcount == null) {
               if (other$tcount != null) {
                  return false;
               }
            } else if (!this$tcount.equals(other$tcount)) {
               return false;
            }

            Object this$expiretime = this.getExpiretime();
            Object other$expiretime = other.getExpiretime();
            if (this$expiretime == null) {
               if (other$expiretime != null) {
                  return false;
               }
            } else if (!this$expiretime.equals(other$expiretime)) {
               return false;
            }

            Object this$rappearance = this.getRappearance();
            Object other$rappearance = other.getRappearance();
            if (this$rappearance == null) {
               if (other$rappearance != null) {
                  return false;
               }
            } else if (!this$rappearance.equals(other$rappearance)) {
               return false;
            }

            Object this$roption = this.getRoption();
            Object other$roption = other.getRoption();
            if (this$roption == null) {
               if (other$roption != null) {
                  return false;
               }
            } else if (!this$roption.equals(other$roption)) {
               return false;
            }

            Object this$rnoption = this.getRnoption();
            Object other$rnoption = other.getRnoption();
            if (this$rnoption == null) {
               if (other$rnoption != null) {
                  return false;
               }
            } else if (!this$rnoption.equals(other$rnoption)) {
               return false;
            }

            Object this$skin = this.getSkin();
            Object other$skin = other.getSkin();
            if (this$skin == null) {
               if (other$skin != null) {
                  return false;
               }
            } else if (!this$skin.equals(other$skin)) {
               return false;
            }

            Object this$skinguid = this.getSkinguid();
            Object other$skinguid = other.getSkinguid();
            if (this$skinguid == null) {
               if (other$skinguid != null) {
                  return false;
               }
            } else if (!this$skinguid.equals(other$skinguid)) {
               return false;
            }

            Object this$locked = this.getLocked();
            Object other$locked = other.getLocked();
            if (this$locked == null) {
               if (other$locked != null) {
                  return false;
               }
            } else if (!this$locked.equals(other$locked)) {
               return false;
            }

            Object this$seal = this.getSeal();
            Object other$seal = other.getSeal();
            if (this$seal == null) {
               if (other$seal != null) {
                  return false;
               }
            } else if (!this$seal.equals(other$seal)) {
               return false;
            }

            Object this$enchantindex = this.getEnchantindex();
            Object other$enchantindex = other.getEnchantindex();
            if (this$enchantindex == null) {
               if (other$enchantindex != null) {
                  return false;
               }
            } else if (!this$enchantindex.equals(other$enchantindex)) {
               return false;
            }

            Object this$crack = this.getCrack();
            Object other$crack = other.getCrack();
            if (this$crack == null) {
               if (other$crack != null) {
                  return false;
               }
            } else if (!this$crack.equals(other$crack)) {
               return false;
            }

            Object this$sindex = this.getSindex();
            Object other$sindex = other.getSindex();
            if (this$sindex == null) {
               if (other$sindex != null) {
                  return false;
               }
            } else if (!this$sindex.equals(other$sindex)) {
               return false;
            }

            Object this$sealing = this.getSealing();
            Object other$sealing = other.getSealing();
            if (this$sealing == null) {
               if (other$sealing != null) {
                  return false;
               }
            } else if (!this$sealing.equals(other$sealing)) {
               return false;
            }

            Object this$upgradepoint = this.getUpgradepoint();
            Object other$upgradepoint = other.getUpgradepoint();
            if (this$upgradepoint == null) {
               if (other$upgradepoint != null) {
                  return false;
               }
            } else if (!this$upgradepoint.equals(other$upgradepoint)) {
               return false;
            }

            Object this$gaizaoJindu = this.getGaizaoJindu();
            Object other$gaizaoJindu = other.getGaizaoJindu();
            if (this$gaizaoJindu == null) {
               if (other$gaizaoJindu != null) {
                  return false;
               }
            } else if (!this$gaizaoJindu.equals(other$gaizaoJindu)) {
               return false;
            }

            Object this$score = this.getScore();
            Object other$score = other.getScore();
            if (this$score == null) {
               if (other$score != null) {
                  return false;
               }
            } else if (!this$score.equals(other$score)) {
               return false;
            }

            Object this$roleId = this.getRoleId();
            Object other$roleId = other.getRoleId();
            if (this$roleId == null) {
               if (other$roleId != null) {
                  return false;
               }
            } else if (!this$roleId.equals(other$roleId)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RoleEquip;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $index = this.getIndex();
      result = result * 59 + ($index == null ? 43 : $index.hashCode());
      Object $slot = this.getSlot();
      result = result * 59 + ($slot == null ? 43 : $slot.hashCode());
      Object $guid = this.getGuid();
      result = result * 59 + ($guid == null ? 43 : $guid.hashCode());
      Object $upgrade = this.getUpgrade();
      result = result * 59 + ($upgrade == null ? 43 : $upgrade.hashCode());
      Object $quality = this.getQuality();
      result = result * 59 + ($quality == null ? 43 : $quality.hashCode());
      Object $endurance = this.getEndurance();
      result = result * 59 + ($endurance == null ? 43 : $endurance.hashCode());
      Object $enchant = this.getEnchant();
      result = result * 59 + ($enchant == null ? 43 : $enchant.hashCode());
      Object $reforge = this.getReforge();
      result = result * 59 + ($reforge == null ? 43 : $reforge.hashCode());
      Object $reforgeexp = this.getReforgeexp();
      result = result * 59 + ($reforgeexp == null ? 43 : $reforgeexp.hashCode());
      Object $amplify = this.getAmplify();
      result = result * 59 + ($amplify == null ? 43 : $amplify.hashCode());
      Object $aoption = this.getAoption();
      result = result * 59 + ($aoption == null ? 43 : $aoption.hashCode());
      Object $emblem = this.getEmblem();
      result = result * 59 + ($emblem == null ? 43 : $emblem.hashCode());
      Object $card = this.getCard();
      result = result * 59 + ($card == null ? 43 : $card.hashCode());
      Object $scount = this.getScount();
      result = result * 59 + ($scount == null ? 43 : $scount.hashCode());
      Object $tcount = this.getTcount();
      result = result * 59 + ($tcount == null ? 43 : $tcount.hashCode());
      Object $expiretime = this.getExpiretime();
      result = result * 59 + ($expiretime == null ? 43 : $expiretime.hashCode());
      Object $rappearance = this.getRappearance();
      result = result * 59 + ($rappearance == null ? 43 : $rappearance.hashCode());
      Object $roption = this.getRoption();
      result = result * 59 + ($roption == null ? 43 : $roption.hashCode());
      Object $rnoption = this.getRnoption();
      result = result * 59 + ($rnoption == null ? 43 : $rnoption.hashCode());
      Object $skin = this.getSkin();
      result = result * 59 + ($skin == null ? 43 : $skin.hashCode());
      Object $skinguid = this.getSkinguid();
      result = result * 59 + ($skinguid == null ? 43 : $skinguid.hashCode());
      Object $locked = this.getLocked();
      result = result * 59 + ($locked == null ? 43 : $locked.hashCode());
      Object $seal = this.getSeal();
      result = result * 59 + ($seal == null ? 43 : $seal.hashCode());
      Object $enchantindex = this.getEnchantindex();
      result = result * 59 + ($enchantindex == null ? 43 : $enchantindex.hashCode());
      Object $crack = this.getCrack();
      result = result * 59 + ($crack == null ? 43 : $crack.hashCode());
      Object $sindex = this.getSindex();
      result = result * 59 + ($sindex == null ? 43 : $sindex.hashCode());
      Object $sealing = this.getSealing();
      result = result * 59 + ($sealing == null ? 43 : $sealing.hashCode());
      Object $upgradepoint = this.getUpgradepoint();
      result = result * 59 + ($upgradepoint == null ? 43 : $upgradepoint.hashCode());
      Object $gaizaoJindu = this.getGaizaoJindu();
      result = result * 59 + ($gaizaoJindu == null ? 43 : $gaizaoJindu.hashCode());
      Object $score = this.getScore();
      result = result * 59 + ($score == null ? 43 : $score.hashCode());
      Object $roleId = this.getRoleId();
      result = result * 59 + ($roleId == null ? 43 : $roleId.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      return result;
   }

   public String toString() {
      return "RoleEquip(id=" + this.getId() + ", index=" + this.getIndex() + ", slot=" + this.getSlot() + ", guid=" + this.getGuid() + ", upgrade=" + this.getUpgrade() + ", quality=" + this.getQuality() + ", endurance=" + this.getEndurance() + ", enchant=" + this.getEnchant() + ", reforge=" + this.getReforge() + ", reforgeexp=" + this.getReforgeexp() + ", amplify=" + this.getAmplify() + ", aoption=" + this.getAoption() + ", emblem=" + this.getEmblem() + ", card=" + this.getCard() + ", scount=" + this.getScount() + ", tcount=" + this.getTcount() + ", expiretime=" + this.getExpiretime() + ", rappearance=" + this.getRappearance() + ", roption=" + this.getRoption() + ", rnoption=" + this.getRnoption() + ", skin=" + this.getSkin() + ", skinguid=" + this.getSkinguid() + ", locked=" + this.getLocked() + ", seal=" + this.getSeal() + ", enchantindex=" + this.getEnchantindex() + ", crack=" + this.getCrack() + ", sindex=" + this.getSindex() + ", sealing=" + this.getSealing() + ", upgradepoint=" + this.getUpgradepoint() + ", gaizaoJindu=" + this.getGaizaoJindu() + ", score=" + this.getScore() + ", roleId=" + this.getRoleId() + ", type=" + this.getType() + ", name=" + this.getName() + ")";
   }
}
