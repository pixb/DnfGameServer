package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_ARTIFACT;
import java.util.ArrayList;
import java.util.List;

public class ArtifactBox {
   private int maxcount = 80;
   private List<PT_ARTIFACT> artifacts = new ArrayList();

   public void addArtifact(PT_ARTIFACT artifact) {
      this.artifacts.add(artifact);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public List<PT_ARTIFACT> getArtifacts() {
      return this.artifacts;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setArtifacts(List<PT_ARTIFACT> artifacts) {
      this.artifacts = artifacts;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ArtifactBox)) {
         return false;
      } else {
         ArtifactBox other = (ArtifactBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$artifacts = this.getArtifacts();
            Object other$artifacts = other.getArtifacts();
            if (this$artifacts == null) {
               if (other$artifacts != null) {
                  return false;
               }
            } else if (!this$artifacts.equals(other$artifacts)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ArtifactBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $artifacts = this.getArtifacts();
      result = result * 59 + ($artifacts == null ? 43 : $artifacts.hashCode());
      return result;
   }

   public String toString() {
      return "ArtifactBox(maxcount=" + this.getMaxcount() + ", artifacts=" + this.getArtifacts() + ")";
   }
}
