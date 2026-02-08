package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_CERA_SHOP_INFO;

public class CeraShopBuyInfo {
   private PT_CERA_SHOP_INFO account = new PT_CERA_SHOP_INFO();
   private PT_CERA_SHOP_INFO character = new PT_CERA_SHOP_INFO();

   public PT_CERA_SHOP_INFO getAccount() {
      return this.account;
   }

   public PT_CERA_SHOP_INFO getCharacter() {
      return this.character;
   }

   public void setAccount(PT_CERA_SHOP_INFO account) {
      this.account = account;
   }

   public void setCharacter(PT_CERA_SHOP_INFO character) {
      this.character = character;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CeraShopBuyInfo)) {
         return false;
      } else {
         CeraShopBuyInfo other = (CeraShopBuyInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$account = this.getAccount();
            Object other$account = other.getAccount();
            if (this$account == null) {
               if (other$account != null) {
                  return false;
               }
            } else if (!this$account.equals(other$account)) {
               return false;
            }

            Object this$character = this.getCharacter();
            Object other$character = other.getCharacter();
            if (this$character == null) {
               if (other$character != null) {
                  return false;
               }
            } else if (!this$character.equals(other$character)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CeraShopBuyInfo;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $account = this.getAccount();
      result = result * 59 + ($account == null ? 43 : $account.hashCode());
      Object $character = this.getCharacter();
      result = result * 59 + ($character == null ? 43 : $character.hashCode());
      return result;
   }

   public String toString() {
      return "CeraShopBuyInfo(account=" + this.getAccount() + ", character=" + this.getCharacter() + ")";
   }
}
