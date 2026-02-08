package com.dnfm.game.utils.dirtywords;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_word")
public class Word {
   @Id
   private long id;
   @Column
   private String word;

   public Long getId() {
      return null;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getWord() {
      return this.word;
   }

   public void setWord(String word) {
      this.word = word;
   }
}
