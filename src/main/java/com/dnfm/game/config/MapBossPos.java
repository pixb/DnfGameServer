package com.dnfm.game.config;

import com.dnfm.game.enter.Position;
import java.util.List;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_mapbosspos")
public class MapBossPos {
   @Id
   private int id;
   @Column
   private int mapId;
   @Column
   @Default("[]")
   @ColDefine(
      width = 4096
   )
   private List<Position> list;

   public int getMapId() {
      return this.mapId;
   }

   public void setMapId(int mapId) {
      this.mapId = mapId;
   }

   public List<Position> getList() {
      return this.list;
   }

   public void setList(List<Position> list) {
      this.list = list;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
