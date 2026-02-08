package com.dnfm.game.role.model;

import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class RoleMoney {
   private static final Logger log = LoggerFactory.getLogger(RoleMoney.class);
   private List<PT_MONEY_ITEM> moneyList;

   public List<PT_MONEY_ITEM> getMoneyList() {
      return this.moneyList;
   }

   public void setMoneyList(List<PT_MONEY_ITEM> moneyList) {
      this.moneyList = moneyList;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RoleMoney)) {
         return false;
      } else {
         RoleMoney other = (RoleMoney)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$moneyList = this.getMoneyList();
            Object other$moneyList = other.getMoneyList();
            if (this$moneyList == null) {
               if (other$moneyList != null) {
                  return false;
               }
            } else if (!this$moneyList.equals(other$moneyList)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RoleMoney;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $moneyList = this.getMoneyList();
      result = result * 59 + ($moneyList == null ? 43 : $moneyList.hashCode());
      return result;
   }

   public String toString() {
      return "RoleMoney(moneyList=" + this.getMoneyList() + ")";
   }
}
