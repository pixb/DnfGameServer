package com.dnfm.game.adventure.model;

import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_COLLECTION;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_COLLECTION_SLOT;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_EXPEDITION;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_LEVEL_REWARD;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_SHAREBOARD_FRAME;
import com.dnfm.mina.protobuf.PT_ADVENTURE_UNION_SHAREBOARD_SLOT;
import java.util.List;

public class AdventureUnionInfo {
   public Integer error;
   public Long exp;
   public Integer level;
   public Integer day;
   public Long typicalcharacterguid;
   public String name;
   public Long updatetime;
   public Long lastchangenametime;
   public PT_ADVENTURE_UNION_EXPEDITION expedition;
   public List<PT_ADVENTURE_UNION_EXPEDITION> expeditions;
   public List<PT_ADVENTURE_UNION_COLLECTION> collections;
   public List<PT_ADVENTURE_UNION_COLLECTION_SLOT> collectionslots;
   public Integer shareboardbackground;
   public Integer shareboardframe;
   public List<PT_ADVENTURE_UNION_SHAREBOARD_SLOT> shareboardslotlist;
   public List<PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND> shareboardbackgroundlist;
   public List<PT_ADVENTURE_UNION_SHAREBOARD_FRAME> shareboardframelist;
   public Boolean shareboardshowantievilscore;
   public Integer autosearchcount;
   public List<Integer> receivedcollectionrewards;
   public List<PT_ADVENTURE_UNION_LEVEL_REWARD> levelrewards;
   public Integer shareboardtotalantievilscore;
   public Boolean shareboardantievilscorerefresh;
   public Boolean isadventureCondition;
}
