package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpicPieceBox {
   Map<Integer, PT_STACKABLE> epicPiecesMap = new HashMap();
   private int maxcount = 200;

   public void addEpicPiece(PT_STACKABLE epicPiece) {
      this.epicPiecesMap.put(epicPiece.index, epicPiece);
   }

   public List<PT_STACKABLE> getCards() {
      return new ArrayList(this.epicPiecesMap.values());
   }

   public PT_STACKABLE getEpicPiece(int index) {
      return (PT_STACKABLE)this.epicPiecesMap.get(index);
   }

   public void putEpicPiece(PT_STACKABLE epicPiece) {
      this.epicPiecesMap.put(epicPiece.index, epicPiece);
   }

   public void updateEpicPieceSub(int index, int count) {
      PT_STACKABLE epicPiece = (PT_STACKABLE)this.epicPiecesMap.get(index);
      if (epicPiece == null) {
         epicPiece = new PT_STACKABLE();
         epicPiece.index = index;
         epicPiece.count = count;
         this.epicPiecesMap.put(index, epicPiece);
      } else {
         epicPiece.count = epicPiece.count - count;
      }

   }

   public void updateEpicPieceAdd(int index, int count) {
      PT_STACKABLE epicPiece = (PT_STACKABLE)this.epicPiecesMap.get(index);
      if (epicPiece == null) {
         epicPiece = new PT_STACKABLE();
         epicPiece.index = index;
         epicPiece.count = count;
         this.epicPiecesMap.put(index, epicPiece);
      } else {
         epicPiece.count = epicPiece.count + count;
      }

   }

   public void updateEpicPiece(PT_STACKABLE epicPiece) {
      if (this.epicPiecesMap.get(epicPiece.index) == null) {
         this.epicPiecesMap.put(epicPiece.index, epicPiece);
      } else {
         PT_STACKABLE pt_stackable = (PT_STACKABLE)this.epicPiecesMap.get(epicPiece.index);
         pt_stackable.count = pt_stackable.count + epicPiece.count;
         this.epicPiecesMap.put(epicPiece.index, pt_stackable);
      }

   }

   public List<PT_STACKABLE> getEpicPieceList() {
      return new ArrayList(this.epicPiecesMap.values());
   }

   public Map<Integer, PT_STACKABLE> getEpicPiecesMap() {
      return this.epicPiecesMap;
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public void setEpicPiecesMap(Map<Integer, PT_STACKABLE> epicPiecesMap) {
      this.epicPiecesMap = epicPiecesMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EpicPieceBox)) {
         return false;
      } else {
         EpicPieceBox other = (EpicPieceBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$epicPiecesMap = this.getEpicPiecesMap();
            Object other$epicPiecesMap = other.getEpicPiecesMap();
            if (this$epicPiecesMap == null) {
               if (other$epicPiecesMap != null) {
                  return false;
               }
            } else if (!this$epicPiecesMap.equals(other$epicPiecesMap)) {
               return false;
            }

            if (this.getMaxcount() != other.getMaxcount()) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EpicPieceBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $epicPiecesMap = this.getEpicPiecesMap();
      result = result * 59 + ($epicPiecesMap == null ? 43 : $epicPiecesMap.hashCode());
      result = result * 59 + this.getMaxcount();
      return result;
   }

   public String toString() {
      return "EpicPieceBox(epicPiecesMap=" + this.getEpicPiecesMap() + ", maxcount=" + this.getMaxcount() + ")";
   }
}
