package com.dnfm.game.config;

import java.util.HashMap;
import java.util.Map;

public class ServerSimpleDataBox {
   public int nextTransId = 0;
   private Map<String, ServerSimpleData> allDatas = new HashMap();

   public Map<String, ServerSimpleData> getAllDatas() {
      return this.allDatas;
   }

   public void setAllDatas(Map<String, ServerSimpleData> allDatas) {
      this.allDatas = allDatas;
   }

   public void addServerSimpleData(int type, int enumvalue, ServerSimpleData data) {
      String key = type + "_" + enumvalue;
      this.allDatas.put(key, data);
   }

   public ServerSimpleData getData(int type, int enumvalue) {
      String key = type + "_" + enumvalue;
      return (ServerSimpleData)this.allDatas.get(key);
   }

   public int getNextTransId() {
      return this.nextTransId;
   }

   public void setNextTransId(int nextTransId) {
      this.nextTransId = nextTransId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServerSimpleDataBox)) {
         return false;
      } else {
         ServerSimpleDataBox other = (ServerSimpleDataBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getNextTransId() != other.getNextTransId()) {
            return false;
         } else {
            Object this$allDatas = this.getAllDatas();
            Object other$allDatas = other.getAllDatas();
            if (this$allDatas == null) {
               if (other$allDatas != null) {
                  return false;
               }
            } else if (!this$allDatas.equals(other$allDatas)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ServerSimpleDataBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getNextTransId();
      Object $allDatas = this.getAllDatas();
      result = result * 59 + ($allDatas == null ? 43 : $allDatas.hashCode());
      return result;
   }

   public String toString() {
      return "ServerSimpleDataBox(nextTransId=" + this.getNextTransId() + ", allDatas=" + this.getAllDatas() + ")";
   }
}
