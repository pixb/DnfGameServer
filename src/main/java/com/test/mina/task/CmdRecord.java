package com.test.mina.task;

public class CmdRecord implements Comparable<CmdRecord> {
   private int cmd;
   private int count;

   public CmdRecord(int cmd, int count) {
      this.cmd = cmd;
      this.count = count;
   }

   public int getCmd() {
      return this.cmd;
   }

   public void setCmd(int cmd) {
      this.cmd = cmd;
   }

   public int getCount() {
      return this.count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public String toString() {
      return "CmdRecord{cmd=" + this.cmd + ", count=" + this.count + '}';
   }

   public int compareTo(CmdRecord o) {
      return this.count != o.getCount() ? o.getCount() - this.count : o.getCmd() - this.cmd;
   }
}
