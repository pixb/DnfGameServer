package com.dnfm.game.dungeon.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_dungeon")
public class Dungeon {
   @Id
   private int index;
   @Column
   private String name;
   @Column
   private String mapSpec;
   @Column
   private String startMap;
   @Column
   private String bossMap;
   @Column
   private String dungeonType;
   @Column
   private Integer basisLevel;
   @Column
   private Integer clearExp;
   @Column
   private String hellmap;

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public String getMapSpec() {
      return this.mapSpec;
   }

   public String getStartMap() {
      return this.startMap;
   }

   public String getBossMap() {
      return this.bossMap;
   }

   public String getDungeonType() {
      return this.dungeonType;
   }

   public Integer getBasisLevel() {
      return this.basisLevel;
   }

   public Integer getClearExp() {
      return this.clearExp;
   }

   public String getHellmap() {
      return this.hellmap;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setMapSpec(String mapSpec) {
      this.mapSpec = mapSpec;
   }

   public void setStartMap(String startMap) {
      this.startMap = startMap;
   }

   public void setBossMap(String bossMap) {
      this.bossMap = bossMap;
   }

   public void setDungeonType(String dungeonType) {
      this.dungeonType = dungeonType;
   }

   public void setBasisLevel(Integer basisLevel) {
      this.basisLevel = basisLevel;
   }

   public void setClearExp(Integer clearExp) {
      this.clearExp = clearExp;
   }

   public void setHellmap(String hellmap) {
      this.hellmap = hellmap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Dungeon)) {
         return false;
      } else {
         Dungeon other = (Dungeon)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getIndex() != other.getIndex()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$mapSpec = this.getMapSpec();
            Object other$mapSpec = other.getMapSpec();
            if (this$mapSpec == null) {
               if (other$mapSpec != null) {
                  return false;
               }
            } else if (!this$mapSpec.equals(other$mapSpec)) {
               return false;
            }

            Object this$startMap = this.getStartMap();
            Object other$startMap = other.getStartMap();
            if (this$startMap == null) {
               if (other$startMap != null) {
                  return false;
               }
            } else if (!this$startMap.equals(other$startMap)) {
               return false;
            }

            Object this$bossMap = this.getBossMap();
            Object other$bossMap = other.getBossMap();
            if (this$bossMap == null) {
               if (other$bossMap != null) {
                  return false;
               }
            } else if (!this$bossMap.equals(other$bossMap)) {
               return false;
            }

            Object this$dungeonType = this.getDungeonType();
            Object other$dungeonType = other.getDungeonType();
            if (this$dungeonType == null) {
               if (other$dungeonType != null) {
                  return false;
               }
            } else if (!this$dungeonType.equals(other$dungeonType)) {
               return false;
            }

            Object this$basisLevel = this.getBasisLevel();
            Object other$basisLevel = other.getBasisLevel();
            if (this$basisLevel == null) {
               if (other$basisLevel != null) {
                  return false;
               }
            } else if (!this$basisLevel.equals(other$basisLevel)) {
               return false;
            }

            Object this$clearExp = this.getClearExp();
            Object other$clearExp = other.getClearExp();
            if (this$clearExp == null) {
               if (other$clearExp != null) {
                  return false;
               }
            } else if (!this$clearExp.equals(other$clearExp)) {
               return false;
            }

            Object this$hellmap = this.getHellmap();
            Object other$hellmap = other.getHellmap();
            if (this$hellmap == null) {
               if (other$hellmap != null) {
                  return false;
               }
            } else if (!this$hellmap.equals(other$hellmap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof Dungeon;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $mapSpec = this.getMapSpec();
      result = result * 59 + ($mapSpec == null ? 43 : $mapSpec.hashCode());
      Object $startMap = this.getStartMap();
      result = result * 59 + ($startMap == null ? 43 : $startMap.hashCode());
      Object $bossMap = this.getBossMap();
      result = result * 59 + ($bossMap == null ? 43 : $bossMap.hashCode());
      Object $dungeonType = this.getDungeonType();
      result = result * 59 + ($dungeonType == null ? 43 : $dungeonType.hashCode());
      Object $basisLevel = this.getBasisLevel();
      result = result * 59 + ($basisLevel == null ? 43 : $basisLevel.hashCode());
      Object $clearExp = this.getClearExp();
      result = result * 59 + ($clearExp == null ? 43 : $clearExp.hashCode());
      Object $hellmap = this.getHellmap();
      result = result * 59 + ($hellmap == null ? 43 : $hellmap.hashCode());
      return result;
   }

   public String toString() {
      return "Dungeon(index=" + this.getIndex() + ", name=" + this.getName() + ", mapSpec=" + this.getMapSpec() + ", startMap=" + this.getStartMap() + ", bossMap=" + this.getBossMap() + ", dungeonType=" + this.getDungeonType() + ", basisLevel=" + this.getBasisLevel() + ", clearExp=" + this.getClearExp() + ", hellmap=" + this.getHellmap() + ")";
   }
}
