package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_EQUIP {
   public int score;
   public int equiptype;
   public int minlevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer upgrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer quality;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer endurance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer enchant;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer reforge;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer reforgeexp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer amplify;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer aoption;
   @Protobuf(
      order = 12
   )
   public List<PT_EMBLEM> emblem;
   @Protobuf(
      order = 13
   )
   public List<PT_STACKABLE> card;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer scount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer tcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 16,
      required = false
   )
   public Long expiretime;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 17,
      required = false
   )
   public Boolean rappearance;
   @Protobuf(
      order = 18
   )
   public List<PT_RANDOMOPTION_ITEM> roption;
   @Protobuf(
      order = 19
   )
   public List<PT_RANDOMOPTION_ITEM> rnoption;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20,
      required = false
   )
   public Integer skin;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 21,
      required = false
   )
   public Long skinguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 22,
      required = false
   )
   public Boolean locked;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 23,
      required = false
   )
   public Boolean seal;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer enchantindex;
   @Protobuf(
      order = 25
   )
   public List<PT_CRACK> crack;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer sindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer sealing;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer upgradepoint;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 29,
      required = false
   )
   public Integer season;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer slot;

   public Integer getIndex() {
      return this.index;
   }

   public void setIndex(Integer index) {
      this.index = index;
   }

   public Integer getCount() {
      return this.count;
   }

   public void setCount(Integer count) {
      this.count = count;
   }

   public Long getGuid() {
      return this.guid;
   }

   public void setGuid(Long guid) {
      this.guid = guid;
   }

   public Integer getUpgrade() {
      return this.upgrade;
   }

   public void setUpgrade(Integer upgrade) {
      this.upgrade = upgrade;
   }

   public Integer getQuality() {
      return this.quality;
   }

   public void setQuality(Integer quality) {
      this.quality = quality;
   }

   public Integer getEndurance() {
      return this.endurance;
   }

   public void setEndurance(Integer endurance) {
      this.endurance = endurance;
   }

   public Integer getEnchant() {
      return this.enchant;
   }

   public void setEnchant(Integer enchant) {
      this.enchant = enchant;
   }

   public Integer getReforge() {
      return this.reforge;
   }

   public void setReforge(Integer reforge) {
      this.reforge = reforge;
   }

   public Integer getReforgeexp() {
      return this.reforgeexp;
   }

   public void setReforgeexp(Integer reforgeexp) {
      this.reforgeexp = reforgeexp;
   }

   public Integer getAmplify() {
      return this.amplify;
   }

   public void setAmplify(Integer amplify) {
      this.amplify = amplify;
   }

   public Integer getAoption() {
      return this.aoption;
   }

   public void setAoption(Integer aoption) {
      this.aoption = aoption;
   }

   public List<PT_EMBLEM> getEmblem() {
      return this.emblem;
   }

   public void setEmblem(List<PT_EMBLEM> emblem) {
      this.emblem = emblem;
   }

   public List<PT_STACKABLE> getCard() {
      return this.card;
   }

   public void setCard(List<PT_STACKABLE> card) {
      this.card = card;
   }

   public Integer getScount() {
      return this.scount;
   }

   public void setScount(Integer scount) {
      this.scount = scount;
   }

   public Integer getTcount() {
      return this.tcount;
   }

   public void setTcount(Integer tcount) {
      this.tcount = tcount;
   }

   public Long getExpiretime() {
      return this.expiretime;
   }

   public void setExpiretime(Long expiretime) {
      this.expiretime = expiretime;
   }

   public Boolean getRappearance() {
      return this.rappearance;
   }

   public void setRappearance(Boolean rappearance) {
      this.rappearance = rappearance;
   }

   public List<PT_RANDOMOPTION_ITEM> getRnoption() {
      return this.rnoption;
   }

   public void setRnoption(List<PT_RANDOMOPTION_ITEM> rnoption) {
      this.rnoption = rnoption;
   }

   public Integer getSkin() {
      return this.skin;
   }

   public void setSkin(Integer skin) {
      this.skin = skin;
   }

   public Long getSkinguid() {
      return this.skinguid;
   }

   public void setSkinguid(Long skinguid) {
      this.skinguid = skinguid;
   }

   public Boolean getLocked() {
      return this.locked;
   }

   public void setLocked(Boolean locked) {
      this.locked = locked;
   }

   public Boolean getSeal() {
      return this.seal;
   }

   public void setSeal(Boolean seal) {
      this.seal = seal;
   }

   public Integer getEnchantindex() {
      return this.enchantindex;
   }

   public void setEnchantindex(Integer enchantindex) {
      this.enchantindex = enchantindex;
   }

   public List<PT_CRACK> getCrack() {
      return this.crack;
   }

   public void setCrack(List<PT_CRACK> crack) {
      this.crack = crack;
   }

   public Integer getSindex() {
      return this.sindex;
   }

   public void setSindex(Integer sindex) {
      this.sindex = sindex;
   }

   public Integer getSealing() {
      return this.sealing;
   }

   public void setSealing(Integer sealing) {
      this.sealing = sealing;
   }

   public Integer getUpgradepoint() {
      return this.upgradepoint;
   }

   public void setUpgradepoint(Integer upgradepoint) {
      this.upgradepoint = upgradepoint;
   }

   public Integer getSeason() {
      return this.season;
   }

   public void setSeason(Integer season) {
      this.season = season;
   }

   public Integer getSlot() {
      return this.slot;
   }

   public void setSlot(Integer slot) {
      this.slot = slot;
   }

   public List<PT_RANDOMOPTION_ITEM> getRoption() {
      return this.roption;
   }

   public void setRoption(List<PT_RANDOMOPTION_ITEM> roption) {
      this.roption = roption;
   }
}
