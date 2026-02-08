package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_COLLECTION_INFO;
import java.util.ArrayList;
import java.util.List;

public class CollectionBox {
   private int level;
   private List<PT_COLLECTION_INFO> collectionInfos = new ArrayList();

   public int getLevel() {
      return this.level;
   }

   public List<PT_COLLECTION_INFO> getCollectionInfos() {
      return this.collectionInfos;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setCollectionInfos(List<PT_COLLECTION_INFO> collectionInfos) {
      this.collectionInfos = collectionInfos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CollectionBox)) {
         return false;
      } else {
         CollectionBox other = (CollectionBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else {
            Object this$collectionInfos = this.getCollectionInfos();
            Object other$collectionInfos = other.getCollectionInfos();
            if (this$collectionInfos == null) {
               if (other$collectionInfos != null) {
                  return false;
               }
            } else if (!this$collectionInfos.equals(other$collectionInfos)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CollectionBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getLevel();
      Object $collectionInfos = this.getCollectionInfos();
      result = result * 59 + ($collectionInfos == null ? 43 : $collectionInfos.hashCode());
      return result;
   }

   public String toString() {
      return "CollectionBox(level=" + this.getLevel() + ", collectionInfos=" + this.getCollectionInfos() + ")";
   }
}
