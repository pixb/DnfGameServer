package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_GAUGE;
import com.dnfm.mina.protobuf.PT_TICKET;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonTicketsBox {
   public int hellGauge = 0;
   private Map<String, PT_TICKET> ticketMap = new HashMap();
   private List<PT_TICKET> ticket;
   private int jarofgreed;
   private int lotteryfreecount;
   private int recvfriendcount;
   private int sendfriendcount;
   private int recvplatformfriendcount;
   private int sendplatformfriendcount;
   private int guildredpacketrecvcount;
   private int guildredpacketsendcount;
   private int battleleaguerewardlimit;
   private List<Integer> clearsweepdungeonlist;
   private List<PT_GAUGE> gauges;
   private int battleleagueguildrewardlimit;
   private int bingoticketcount;

   public void subDailyTicket(String dungeonType) {
      PT_TICKET pt_ticket = (PT_TICKET)this.ticketMap.get(dungeonType);
      if (pt_ticket.dailyticket != null && pt_ticket.dailyticket > 0) {
         pt_ticket.dailyticket = pt_ticket.dailyticket - 1;
      }

   }

   public List<PT_TICKET> getTicketList() {
      return new ArrayList(this.ticketMap.values());
   }

   public int getHellGauge() {
      return this.hellGauge;
   }

   public Map<String, PT_TICKET> getTicketMap() {
      return this.ticketMap;
   }

   public List<PT_TICKET> getTicket() {
      return this.ticket;
   }

   public int getJarofgreed() {
      return this.jarofgreed;
   }

   public int getLotteryfreecount() {
      return this.lotteryfreecount;
   }

   public int getRecvfriendcount() {
      return this.recvfriendcount;
   }

   public int getSendfriendcount() {
      return this.sendfriendcount;
   }

   public int getRecvplatformfriendcount() {
      return this.recvplatformfriendcount;
   }

   public int getSendplatformfriendcount() {
      return this.sendplatformfriendcount;
   }

   public int getGuildredpacketrecvcount() {
      return this.guildredpacketrecvcount;
   }

   public int getGuildredpacketsendcount() {
      return this.guildredpacketsendcount;
   }

   public int getBattleleaguerewardlimit() {
      return this.battleleaguerewardlimit;
   }

   public List<Integer> getClearsweepdungeonlist() {
      return this.clearsweepdungeonlist;
   }

   public List<PT_GAUGE> getGauges() {
      return this.gauges;
   }

   public int getBattleleagueguildrewardlimit() {
      return this.battleleagueguildrewardlimit;
   }

   public int getBingoticketcount() {
      return this.bingoticketcount;
   }

   public void setHellGauge(int hellGauge) {
      this.hellGauge = hellGauge;
   }

   public void setTicketMap(Map<String, PT_TICKET> ticketMap) {
      this.ticketMap = ticketMap;
   }

   public void setTicket(List<PT_TICKET> ticket) {
      this.ticket = ticket;
   }

   public void setJarofgreed(int jarofgreed) {
      this.jarofgreed = jarofgreed;
   }

   public void setLotteryfreecount(int lotteryfreecount) {
      this.lotteryfreecount = lotteryfreecount;
   }

   public void setRecvfriendcount(int recvfriendcount) {
      this.recvfriendcount = recvfriendcount;
   }

   public void setSendfriendcount(int sendfriendcount) {
      this.sendfriendcount = sendfriendcount;
   }

   public void setRecvplatformfriendcount(int recvplatformfriendcount) {
      this.recvplatformfriendcount = recvplatformfriendcount;
   }

   public void setSendplatformfriendcount(int sendplatformfriendcount) {
      this.sendplatformfriendcount = sendplatformfriendcount;
   }

   public void setGuildredpacketrecvcount(int guildredpacketrecvcount) {
      this.guildredpacketrecvcount = guildredpacketrecvcount;
   }

   public void setGuildredpacketsendcount(int guildredpacketsendcount) {
      this.guildredpacketsendcount = guildredpacketsendcount;
   }

   public void setBattleleaguerewardlimit(int battleleaguerewardlimit) {
      this.battleleaguerewardlimit = battleleaguerewardlimit;
   }

   public void setClearsweepdungeonlist(List<Integer> clearsweepdungeonlist) {
      this.clearsweepdungeonlist = clearsweepdungeonlist;
   }

   public void setGauges(List<PT_GAUGE> gauges) {
      this.gauges = gauges;
   }

   public void setBattleleagueguildrewardlimit(int battleleagueguildrewardlimit) {
      this.battleleagueguildrewardlimit = battleleagueguildrewardlimit;
   }

   public void setBingoticketcount(int bingoticketcount) {
      this.bingoticketcount = bingoticketcount;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DungeonTicketsBox)) {
         return false;
      } else {
         DungeonTicketsBox other = (DungeonTicketsBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getHellGauge() != other.getHellGauge()) {
            return false;
         } else {
            Object this$ticketMap = this.getTicketMap();
            Object other$ticketMap = other.getTicketMap();
            if (this$ticketMap == null) {
               if (other$ticketMap != null) {
                  return false;
               }
            } else if (!this$ticketMap.equals(other$ticketMap)) {
               return false;
            }

            Object this$ticket = this.getTicket();
            Object other$ticket = other.getTicket();
            if (this$ticket == null) {
               if (other$ticket != null) {
                  return false;
               }
            } else if (!this$ticket.equals(other$ticket)) {
               return false;
            }

            if (this.getJarofgreed() != other.getJarofgreed()) {
               return false;
            } else if (this.getLotteryfreecount() != other.getLotteryfreecount()) {
               return false;
            } else if (this.getRecvfriendcount() != other.getRecvfriendcount()) {
               return false;
            } else if (this.getSendfriendcount() != other.getSendfriendcount()) {
               return false;
            } else if (this.getRecvplatformfriendcount() != other.getRecvplatformfriendcount()) {
               return false;
            } else if (this.getSendplatformfriendcount() != other.getSendplatformfriendcount()) {
               return false;
            } else if (this.getGuildredpacketrecvcount() != other.getGuildredpacketrecvcount()) {
               return false;
            } else if (this.getGuildredpacketsendcount() != other.getGuildredpacketsendcount()) {
               return false;
            } else if (this.getBattleleaguerewardlimit() != other.getBattleleaguerewardlimit()) {
               return false;
            } else {
               Object this$clearsweepdungeonlist = this.getClearsweepdungeonlist();
               Object other$clearsweepdungeonlist = other.getClearsweepdungeonlist();
               if (this$clearsweepdungeonlist == null) {
                  if (other$clearsweepdungeonlist != null) {
                     return false;
                  }
               } else if (!this$clearsweepdungeonlist.equals(other$clearsweepdungeonlist)) {
                  return false;
               }

               Object this$gauges = this.getGauges();
               Object other$gauges = other.getGauges();
               if (this$gauges == null) {
                  if (other$gauges != null) {
                     return false;
                  }
               } else if (!this$gauges.equals(other$gauges)) {
                  return false;
               }

               if (this.getBattleleagueguildrewardlimit() != other.getBattleleagueguildrewardlimit()) {
                  return false;
               } else if (this.getBingoticketcount() != other.getBingoticketcount()) {
                  return false;
               } else {
                  return true;
               }
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DungeonTicketsBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getHellGauge();
      Object $ticketMap = this.getTicketMap();
      result = result * 59 + ($ticketMap == null ? 43 : $ticketMap.hashCode());
      Object $ticket = this.getTicket();
      result = result * 59 + ($ticket == null ? 43 : $ticket.hashCode());
      result = result * 59 + this.getJarofgreed();
      result = result * 59 + this.getLotteryfreecount();
      result = result * 59 + this.getRecvfriendcount();
      result = result * 59 + this.getSendfriendcount();
      result = result * 59 + this.getRecvplatformfriendcount();
      result = result * 59 + this.getSendplatformfriendcount();
      result = result * 59 + this.getGuildredpacketrecvcount();
      result = result * 59 + this.getGuildredpacketsendcount();
      result = result * 59 + this.getBattleleaguerewardlimit();
      Object $clearsweepdungeonlist = this.getClearsweepdungeonlist();
      result = result * 59 + ($clearsweepdungeonlist == null ? 43 : $clearsweepdungeonlist.hashCode());
      Object $gauges = this.getGauges();
      result = result * 59 + ($gauges == null ? 43 : $gauges.hashCode());
      result = result * 59 + this.getBattleleagueguildrewardlimit();
      result = result * 59 + this.getBingoticketcount();
      return result;
   }

   public String toString() {
      return "DungeonTicketsBox(hellGauge=" + this.getHellGauge() + ", ticketMap=" + this.getTicketMap() + ", ticket=" + this.getTicket() + ", jarofgreed=" + this.getJarofgreed() + ", lotteryfreecount=" + this.getLotteryfreecount() + ", recvfriendcount=" + this.getRecvfriendcount() + ", sendfriendcount=" + this.getSendfriendcount() + ", recvplatformfriendcount=" + this.getRecvplatformfriendcount() + ", sendplatformfriendcount=" + this.getSendplatformfriendcount() + ", guildredpacketrecvcount=" + this.getGuildredpacketrecvcount() + ", guildredpacketsendcount=" + this.getGuildredpacketsendcount() + ", battleleaguerewardlimit=" + this.getBattleleaguerewardlimit() + ", clearsweepdungeonlist=" + this.getClearsweepdungeonlist() + ", gauges=" + this.getGauges() + ", battleleagueguildrewardlimit=" + this.getBattleleagueguildrewardlimit() + ", bingoticketcount=" + this.getBingoticketcount() + ")";
   }
}
