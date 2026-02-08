package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_CLEAR_DUNGEON_INFO;
import com.dnfm.mina.protobuf.PT_CLEAR_DUNGEON_INFO_LIST;
import java.util.ArrayList;
import java.util.List;

public class ClearDungeonBox {
   private List<PT_CLEAR_DUNGEON_INFO_LIST> dungeoninfos = new ArrayList();

   public void addDungeoninfo(PT_CLEAR_DUNGEON_INFO_LIST dungeoninfo) {
      this.dungeoninfos.add(dungeoninfo);
   }

   public PT_CLEAR_DUNGEON_INFO_LIST getDungeoninfo(int index) {
      for(PT_CLEAR_DUNGEON_INFO_LIST pt_clear_dungeon_info_list : this.dungeoninfos) {
         if (pt_clear_dungeon_info_list.index == index) {
            return pt_clear_dungeon_info_list;
         }
      }

      PT_CLEAR_DUNGEON_INFO_LIST pt_clear_dungeon_info_list = new PT_CLEAR_DUNGEON_INFO_LIST();
      pt_clear_dungeon_info_list.index = index;
      pt_clear_dungeon_info_list.list = new ArrayList();
      return pt_clear_dungeon_info_list;
   }

   public void addDungeonInfo(int index) {
      PT_CLEAR_DUNGEON_INFO_LIST pt_clear_dungeon_info_list = new PT_CLEAR_DUNGEON_INFO_LIST();
      pt_clear_dungeon_info_list.index = index;
      pt_clear_dungeon_info_list.list = new ArrayList();
      PT_CLEAR_DUNGEON_INFO pt_clear_dungeon_info = new PT_CLEAR_DUNGEON_INFO();
      pt_clear_dungeon_info.score = 7;
      pt_clear_dungeon_info_list.list.add(pt_clear_dungeon_info);
      this.dungeoninfos.add(pt_clear_dungeon_info_list);
   }

   public void addDungeonInfo2(int index) {
      PT_CLEAR_DUNGEON_INFO_LIST pt_clear_dungeon_info_list = new PT_CLEAR_DUNGEON_INFO_LIST();
      pt_clear_dungeon_info_list.index = index;
      pt_clear_dungeon_info_list.list = new ArrayList();
      PT_CLEAR_DUNGEON_INFO pt_clear_dungeon_info = new PT_CLEAR_DUNGEON_INFO();
      pt_clear_dungeon_info.shortestcleartime = 100L;
      pt_clear_dungeon_info_list.list.add(pt_clear_dungeon_info);
      this.dungeoninfos.add(pt_clear_dungeon_info_list);
   }

   public void clear() {
      this.dungeoninfos.clear();
   }

   public List<PT_CLEAR_DUNGEON_INFO_LIST> getDungeoninfos() {
      return this.dungeoninfos;
   }

   public void setDungeoninfos(List<PT_CLEAR_DUNGEON_INFO_LIST> dungeoninfos) {
      this.dungeoninfos = dungeoninfos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClearDungeonBox)) {
         return false;
      } else {
         ClearDungeonBox other = (ClearDungeonBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$dungeoninfos = this.getDungeoninfos();
            Object other$dungeoninfos = other.getDungeoninfos();
            if (this$dungeoninfos == null) {
               if (other$dungeoninfos != null) {
                  return false;
               }
            } else if (!this$dungeoninfos.equals(other$dungeoninfos)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClearDungeonBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $dungeoninfos = this.getDungeoninfos();
      result = result * 59 + ($dungeoninfos == null ? 43 : $dungeoninfos.hashCode());
      return result;
   }

   public String toString() {
      return "ClearDungeonBox(dungeoninfos=" + this.getDungeoninfos() + ")";
   }
}
