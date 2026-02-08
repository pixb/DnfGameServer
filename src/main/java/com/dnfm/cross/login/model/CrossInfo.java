package com.dnfm.cross.login.model;

public class CrossInfo {
   private int fromServer;
   private String fromIp;
   private boolean isCross;
   private int crossType;

   public int getFromServer() {
      return this.fromServer;
   }

   public String getFromIp() {
      return this.fromIp;
   }

   public boolean isCross() {
      return this.isCross;
   }

   public int getCrossType() {
      return this.crossType;
   }

   public void setFromServer(int fromServer) {
      this.fromServer = fromServer;
   }

   public void setFromIp(String fromIp) {
      this.fromIp = fromIp;
   }

   public void setCross(boolean isCross) {
      this.isCross = isCross;
   }

   public void setCrossType(int crossType) {
      this.crossType = crossType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CrossInfo)) {
         return false;
      } else {
         CrossInfo other = (CrossInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getFromServer() != other.getFromServer()) {
            return false;
         } else {
            Object this$fromIp = this.getFromIp();
            Object other$fromIp = other.getFromIp();
            if (this$fromIp == null) {
               if (other$fromIp != null) {
                  return false;
               }
            } else if (!this$fromIp.equals(other$fromIp)) {
               return false;
            }

            if (this.isCross() != other.isCross()) {
               return false;
            } else if (this.getCrossType() != other.getCrossType()) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CrossInfo;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getFromServer();
      Object $fromIp = this.getFromIp();
      result = result * 59 + ($fromIp == null ? 43 : $fromIp.hashCode());
      result = result * 59 + (this.isCross() ? 79 : 97);
      result = result * 59 + this.getCrossType();
      return result;
   }

   public String toString() {
      return "CrossInfo(fromServer=" + this.getFromServer() + ", fromIp=" + this.getFromIp() + ", isCross=" + this.isCross() + ", crossType=" + this.getCrossType() + ")";
   }
}
