package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_SYSTEM_BUFF_APPENDAGE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysBuffBox {
   private long time;
   private Map<Integer, PT_SYSTEM_BUFF_APPENDAGE> appendagesMap = new HashMap();

   public List<PT_SYSTEM_BUFF_APPENDAGE> getAppendages() {
      return new ArrayList(this.appendagesMap.values());
   }

   public void addAppendages(PT_SYSTEM_BUFF_APPENDAGE ptSystemBuffAppendage) {
      this.appendagesMap.put(ptSystemBuffAppendage.index, ptSystemBuffAppendage);
   }

   public PT_SYSTEM_BUFF_APPENDAGE createAppendage(int index, long time, List<Integer> values) {
      PT_SYSTEM_BUFF_APPENDAGE ptSystemBuffAppendage = new PT_SYSTEM_BUFF_APPENDAGE();
      ptSystemBuffAppendage.index = index;
      if (time != 0L) {
         ptSystemBuffAppendage.endtime = time;
      }

      ptSystemBuffAppendage.values = values;
      return ptSystemBuffAppendage;
   }

   public void removeAppendages(int index) {
      this.appendagesMap.remove(index);
   }

   public long getTime() {
      return this.time;
   }

   public Map<Integer, PT_SYSTEM_BUFF_APPENDAGE> getAppendagesMap() {
      return this.appendagesMap;
   }

   public void setTime(long time) {
      this.time = time;
   }

   public void setAppendagesMap(Map<Integer, PT_SYSTEM_BUFF_APPENDAGE> appendagesMap) {
      this.appendagesMap = appendagesMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SysBuffBox)) {
         return false;
      } else {
         SysBuffBox other = (SysBuffBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getTime() != other.getTime()) {
            return false;
         } else {
            Object this$appendagesMap = this.getAppendagesMap();
            Object other$appendagesMap = other.getAppendagesMap();
            if (this$appendagesMap == null) {
               if (other$appendagesMap != null) {
                  return false;
               }
            } else if (!this$appendagesMap.equals(other$appendagesMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SysBuffBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $time = this.getTime();
      result = result * 59 + (int)($time >>> 32 ^ $time);
      Object $appendagesMap = this.getAppendagesMap();
      result = result * 59 + ($appendagesMap == null ? 43 : $appendagesMap.hashCode());
      return result;
   }

   public String toString() {
      return "SysBuffBox(time=" + this.getTime() + ", appendagesMap=" + this.getAppendagesMap() + ")";
   }
}
