package com.dnfm.common.db;

import java.io.Serializable;

public abstract class BaseEntity<PK extends Comparable<PK> & Serializable> implements Serializable {
   protected boolean delete = false;

   public abstract PK getId();

   public void doAfterInit() {
   }

   public void doBeforeSave() {
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.getId().hashCode();
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         BaseEntity other = (BaseEntity)obj;
         return this.getId() == other.getId();
      }
   }

   public boolean isDelete() {
      return this.delete;
   }

   public void setDelete(boolean delete) {
      this.delete = delete;
   }
}
