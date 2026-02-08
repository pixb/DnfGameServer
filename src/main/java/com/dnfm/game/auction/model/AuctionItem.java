package com.dnfm.game.auction.model;

import com.dnfm.common.db.BaseEntity;
import com.dnfm.mina.protobuf.PT_AUCTION_EQUIP;
import com.dnfm.mina.protobuf.PT_AUCTION_STACKABLE;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_auction")
public class AuctionItem extends BaseEntity<Long> {
   @Id(
      auto = false
   )
   private long auid;
   @Column
   private int price;
   @Column
   private int index1;
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private PT_AUCTION_STACKABLE stackable;
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private PT_AUCTION_EQUIP equip;
   @Column
   private int type;

   public Long getId() {
      return this.auid;
   }

   public long getAuid() {
      return this.auid;
   }

   public int getPrice() {
      return this.price;
   }

   public int getIndex1() {
      return this.index1;
   }

   public PT_AUCTION_STACKABLE getStackable() {
      return this.stackable;
   }

   public PT_AUCTION_EQUIP getEquip() {
      return this.equip;
   }

   public int getType() {
      return this.type;
   }

   public void setAuid(long auid) {
      this.auid = auid;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public void setIndex1(int index1) {
      this.index1 = index1;
   }

   public void setStackable(PT_AUCTION_STACKABLE stackable) {
      this.stackable = stackable;
   }

   public void setEquip(PT_AUCTION_EQUIP equip) {
      this.equip = equip;
   }

   public void setType(int type) {
      this.type = type;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AuctionItem)) {
         return false;
      } else {
         AuctionItem other = (AuctionItem)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getAuid() != other.getAuid()) {
            return false;
         } else if (this.getPrice() != other.getPrice()) {
            return false;
         } else if (this.getIndex1() != other.getIndex1()) {
            return false;
         } else {
            Object this$stackable = this.getStackable();
            Object other$stackable = other.getStackable();
            if (this$stackable == null) {
               if (other$stackable != null) {
                  return false;
               }
            } else if (!this$stackable.equals(other$stackable)) {
               return false;
            }

            Object this$equip = this.getEquip();
            Object other$equip = other.getEquip();
            if (this$equip == null) {
               if (other$equip != null) {
                  return false;
               }
            } else if (!this$equip.equals(other$equip)) {
               return false;
            }

            if (this.getType() != other.getType()) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AuctionItem;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $auid = this.getAuid();
      result = result * 59 + (int)($auid >>> 32 ^ $auid);
      result = result * 59 + this.getPrice();
      result = result * 59 + this.getIndex1();
      Object $stackable = this.getStackable();
      result = result * 59 + ($stackable == null ? 43 : $stackable.hashCode());
      Object $equip = this.getEquip();
      result = result * 59 + ($equip == null ? 43 : $equip.hashCode());
      result = result * 59 + this.getType();
      return result;
   }

   public String toString() {
      return "AuctionItem(auid=" + this.getAuid() + ", price=" + this.getPrice() + ", index1=" + this.getIndex1() + ", stackable=" + this.getStackable() + ", equip=" + this.getEquip() + ", type=" + this.getType() + ")";
   }
}
