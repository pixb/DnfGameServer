package com.dnfm.game.enter;

public class LineInfo {
   private short id;
   private String lineName;
   private String lineIp;
   private short status;

   public short getId() {
      return this.id;
   }

   public void setId(short id) {
      this.id = id;
   }

   public String getLineName() {
      return this.lineName;
   }

   public void setLineName(String lineName) {
      this.lineName = lineName;
   }

   public String getLineIp() {
      return this.lineIp;
   }

   public void setLineIp(String lineIp) {
      this.lineIp = lineIp;
   }

   public short getStatus() {
      return this.status;
   }

   public void setStatus(short status) {
      this.status = status;
   }
}
