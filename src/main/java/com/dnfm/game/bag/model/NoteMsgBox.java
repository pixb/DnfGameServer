package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_NOTE_MESSAGE;
import java.util.ArrayList;
import java.util.List;

public class NoteMsgBox {
   private List<PT_NOTE_MESSAGE> list = new ArrayList();

   public List<PT_NOTE_MESSAGE> getList() {
      return this.list;
   }

   public void setList(List<PT_NOTE_MESSAGE> list) {
      this.list = list;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NoteMsgBox)) {
         return false;
      } else {
         NoteMsgBox other = (NoteMsgBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$list = this.getList();
            Object other$list = other.getList();
            if (this$list == null) {
               if (other$list != null) {
                  return false;
               }
            } else if (!this$list.equals(other$list)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NoteMsgBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $list = this.getList();
      result = result * 59 + ($list == null ? 43 : $list.hashCode());
      return result;
   }

   public String toString() {
      return "NoteMsgBox(list=" + this.getList() + ")";
   }
}
