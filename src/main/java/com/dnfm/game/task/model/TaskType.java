package com.dnfm.game.task.model;

public enum TaskType {
   MAIN_LINE(1),
   SCHOOL(2);

   private final int id;

   private TaskType(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }
}
