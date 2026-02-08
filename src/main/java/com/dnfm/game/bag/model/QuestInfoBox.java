package com.dnfm.game.bag.model;

import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.protobuf.PT_QUEST_INFO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestInfoBox {
   private int queststate = 0;
   private List<PT_QUEST_INFO> quest = new ArrayList();
   private Map<Integer, PT_QUEST_INFO> id2QuestMap = new HashMap();
   private Map<Integer, Integer> id2IndexMap = new HashMap();
   private Map<Integer, Integer> index2IdMap = new HashMap();

   public void addQuest(PT_QUEST_INFO quest) {
      if (this.quest == null) {
         this.quest = new ArrayList();
      }

      this.quest.add(quest);
      Integer id = (Integer)DataCache.QUEST_INDEX2ID_MAP.get(quest.qindex);
      if (id != null) {
         this.index2IdMap.put(quest.qindex, id);
      }

      this.id2QuestMap.put(id, quest);
      this.id2IndexMap.put(id, quest.qindex);
   }

   public void addQuest(int index, int state, boolean ismine) {
      PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
      pt_quest_info.qindex = index;
      if (state != 0) {
         pt_quest_info.state = state;
      }

      if (ismine) {
         pt_quest_info.isminequest = true;
      }

      this.quest.add(pt_quest_info);
      Integer id = (Integer)DataCache.QUEST_INDEX2ID_MAP.get(index);
      if (id != null) {
         this.index2IdMap.put(index, id);
      }

      this.id2QuestMap.put(id, pt_quest_info);
      this.id2IndexMap.put(id, index);
   }

   public void addQuest(int index, int state) {
      PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
      pt_quest_info.qindex = index;
      if (state != 0) {
         pt_quest_info.state = state;
      }

      pt_quest_info.isminequest = true;
      this.quest.add(pt_quest_info);
      Integer id = (Integer)DataCache.QUEST_INDEX2ID_MAP.get(index);
      if (id != null) {
         this.index2IdMap.put(index, id);
      }

      this.id2QuestMap.put(id, pt_quest_info);
      this.id2IndexMap.put(id, index);
   }

   public void addQuest(int index, int state, int cnt) {
      PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
      pt_quest_info.qindex = index;
      if (state != 0) {
         pt_quest_info.state = state;
      }

      pt_quest_info.count = cnt;
      pt_quest_info.isminequest = true;
      this.quest.add(pt_quest_info);
      Integer id = (Integer)DataCache.QUEST_INDEX2ID_MAP.get(index);
      if (id != null) {
         this.index2IdMap.put(index, id);
      }

      this.id2QuestMap.put(id, pt_quest_info);
      this.id2IndexMap.put(id, index);
   }

   public void addQuest(int index) {
      PT_QUEST_INFO pt_quest_info = new PT_QUEST_INFO();
      pt_quest_info.qindex = index;
      pt_quest_info.isminequest = true;
      this.quest.add(pt_quest_info);
      Integer id = (Integer)DataCache.QUEST_INDEX2ID_MAP.get(index);
      if (id != null) {
         this.index2IdMap.put(index, id);
      }

      this.id2QuestMap.put(id, pt_quest_info);
      this.id2IndexMap.put(id, index);
   }

   public void removeQuestById(int id) {
      PT_QUEST_INFO pt_quest_info = (PT_QUEST_INFO)this.id2QuestMap.get(id);
      this.removeQuest(pt_quest_info, id);
   }

   private void removeQuest(PT_QUEST_INFO pt_quest_info, int id) {
      this.quest.remove(pt_quest_info);
      this.id2QuestMap.remove(id);
      this.id2IndexMap.remove(id);
      this.index2IdMap.remove(pt_quest_info.qindex);
   }

   public void updateQuestState(int qindex, int state) {
      for(int i = 0; i < this.quest.size(); ++i) {
         if (((PT_QUEST_INFO)this.quest.get(i)).qindex == qindex) {
            ((PT_QUEST_INFO)this.quest.get(i)).state = state;
            break;
         }
      }

   }

   public int getQueststate() {
      return this.queststate;
   }

   public List<PT_QUEST_INFO> getQuest() {
      return this.quest;
   }

   public Map<Integer, PT_QUEST_INFO> getId2QuestMap() {
      return this.id2QuestMap;
   }

   public Map<Integer, Integer> getId2IndexMap() {
      return this.id2IndexMap;
   }

   public Map<Integer, Integer> getIndex2IdMap() {
      return this.index2IdMap;
   }

   public void setQueststate(int queststate) {
      this.queststate = queststate;
   }

   public void setQuest(List<PT_QUEST_INFO> quest) {
      this.quest = quest;
   }

   public void setId2QuestMap(Map<Integer, PT_QUEST_INFO> id2QuestMap) {
      this.id2QuestMap = id2QuestMap;
   }

   public void setId2IndexMap(Map<Integer, Integer> id2IndexMap) {
      this.id2IndexMap = id2IndexMap;
   }

   public void setIndex2IdMap(Map<Integer, Integer> index2IdMap) {
      this.index2IdMap = index2IdMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof QuestInfoBox)) {
         return false;
      } else {
         QuestInfoBox other = (QuestInfoBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getQueststate() != other.getQueststate()) {
            return false;
         } else {
            Object this$quest = this.getQuest();
            Object other$quest = other.getQuest();
            if (this$quest == null) {
               if (other$quest != null) {
                  return false;
               }
            } else if (!this$quest.equals(other$quest)) {
               return false;
            }

            Object this$id2QuestMap = this.getId2QuestMap();
            Object other$id2QuestMap = other.getId2QuestMap();
            if (this$id2QuestMap == null) {
               if (other$id2QuestMap != null) {
                  return false;
               }
            } else if (!this$id2QuestMap.equals(other$id2QuestMap)) {
               return false;
            }

            Object this$id2IndexMap = this.getId2IndexMap();
            Object other$id2IndexMap = other.getId2IndexMap();
            if (this$id2IndexMap == null) {
               if (other$id2IndexMap != null) {
                  return false;
               }
            } else if (!this$id2IndexMap.equals(other$id2IndexMap)) {
               return false;
            }

            Object this$index2IdMap = this.getIndex2IdMap();
            Object other$index2IdMap = other.getIndex2IdMap();
            if (this$index2IdMap == null) {
               if (other$index2IdMap != null) {
                  return false;
               }
            } else if (!this$index2IdMap.equals(other$index2IdMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof QuestInfoBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getQueststate();
      Object $quest = this.getQuest();
      result = result * 59 + ($quest == null ? 43 : $quest.hashCode());
      Object $id2QuestMap = this.getId2QuestMap();
      result = result * 59 + ($id2QuestMap == null ? 43 : $id2QuestMap.hashCode());
      Object $id2IndexMap = this.getId2IndexMap();
      result = result * 59 + ($id2IndexMap == null ? 43 : $id2IndexMap.hashCode());
      Object $index2IdMap = this.getIndex2IdMap();
      result = result * 59 + ($index2IdMap == null ? 43 : $index2IdMap.hashCode());
      return result;
   }

   public String toString() {
      return "QuestInfoBox(queststate=" + this.getQueststate() + ", quest=" + this.getQuest() + ", id2QuestMap=" + this.getId2QuestMap() + ", id2IndexMap=" + this.getId2IndexMap() + ", index2IdMap=" + this.getIndex2IdMap() + ")";
   }
}
