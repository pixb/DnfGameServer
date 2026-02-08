package com.dnfm.game.role.model;

import com.dnfm.mina.protobuf.PT_TONIC_INFO;
import com.dnfm.mina.protobuf.PT_TONIC_MATERIAL_USAGE;
import java.util.ArrayList;
import java.util.List;

public class TonicBox {
   private List<PT_TONIC_INFO> crystaltonic = new ArrayList();
   private List<PT_TONIC_MATERIAL_USAGE> crystaltonicpoint = new ArrayList();

   public void addCrystaralTonic(PT_TONIC_INFO pt_tonic_info) {
      this.crystaltonic.add(pt_tonic_info);
   }

   public PT_TONIC_INFO getTonic(int index) {
      for(PT_TONIC_INFO pt_tonic_info : this.crystaltonic) {
         if (pt_tonic_info.index == index) {
            return pt_tonic_info;
         }
      }

      return null;
   }

   public PT_TONIC_MATERIAL_USAGE getTonicPoint(int index) {
      for(PT_TONIC_MATERIAL_USAGE pt_tonic_info : this.crystaltonicpoint) {
         if (pt_tonic_info.index == index) {
            return pt_tonic_info;
         }
      }

      return null;
   }

   public void updateCrystaralTonic(PT_TONIC_INFO pt_tonic_info) {
      for(PT_TONIC_INFO ptTonicInfo : this.crystaltonic) {
         if (ptTonicInfo.index == pt_tonic_info.index) {
            this.crystaltonic.remove(ptTonicInfo);
            this.crystaltonic.add(pt_tonic_info);
            return;
         }
      }

   }

   public void updateCrystalTonicPoint(PT_TONIC_MATERIAL_USAGE pt_tonic_info) {
      for(PT_TONIC_MATERIAL_USAGE pt_tonic_material_usage : this.crystaltonicpoint) {
         if (pt_tonic_material_usage.index == pt_tonic_info.index) {
            this.crystaltonicpoint.remove(pt_tonic_material_usage);
            this.crystaltonicpoint.add(pt_tonic_info);
            return;
         }
      }

   }

   public void addCrystaralTonicPoint(PT_TONIC_MATERIAL_USAGE pt_tonic_material_usage) {
      this.crystaltonicpoint.add(pt_tonic_material_usage);
   }

   public List<PT_TONIC_INFO> getCrystaltonic() {
      return this.crystaltonic;
   }

   public List<PT_TONIC_MATERIAL_USAGE> getCrystaltonicpoint() {
      return this.crystaltonicpoint;
   }

   public void setCrystaltonic(List<PT_TONIC_INFO> crystaltonic) {
      this.crystaltonic = crystaltonic;
   }

   public void setCrystaltonicpoint(List<PT_TONIC_MATERIAL_USAGE> crystaltonicpoint) {
      this.crystaltonicpoint = crystaltonicpoint;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TonicBox)) {
         return false;
      } else {
         TonicBox other = (TonicBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$crystaltonic = this.getCrystaltonic();
            Object other$crystaltonic = other.getCrystaltonic();
            if (this$crystaltonic == null) {
               if (other$crystaltonic != null) {
                  return false;
               }
            } else if (!this$crystaltonic.equals(other$crystaltonic)) {
               return false;
            }

            Object this$crystaltonicpoint = this.getCrystaltonicpoint();
            Object other$crystaltonicpoint = other.getCrystaltonicpoint();
            if (this$crystaltonicpoint == null) {
               if (other$crystaltonicpoint != null) {
                  return false;
               }
            } else if (!this$crystaltonicpoint.equals(other$crystaltonicpoint)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TonicBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $crystaltonic = this.getCrystaltonic();
      result = result * 59 + ($crystaltonic == null ? 43 : $crystaltonic.hashCode());
      Object $crystaltonicpoint = this.getCrystaltonicpoint();
      result = result * 59 + ($crystaltonicpoint == null ? 43 : $crystaltonicpoint.hashCode());
      return result;
   }

   public String toString() {
      return "TonicBox(crystaltonic=" + this.getCrystaltonic() + ", crystaltonicpoint=" + this.getCrystaltonicpoint() + ")";
   }
}
