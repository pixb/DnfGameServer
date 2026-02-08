package com.dnfm.game.utils.dirtywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordService {
   private final Map<Character, List<String>> dirtyWords = new HashMap();
   Logger logger = LoggerFactory.getLogger(WordService.class);
   Set<String> words = new HashSet();
   @Autowired
   Dao dao;

   @PostConstruct
   private void init() {
   }

   public List<String> checkDirtyWords(String sentence) {
      char[] stringArr = sentence.toCharArray();
      int wordCount = stringArr.length;
      List<String> result = new ArrayList();

      for(int i = 0; i < wordCount - 1; ++i) {
         char key = stringArr[i];
         if (this.dirtyWords.containsKey(key)) {
            int endIndex = Math.min(10, wordCount - i);
            String checkWord = new String(stringArr, i, endIndex);
            String matchedKey = this.checkWordsMatch(checkWord, key);
            if (matchedKey != null && matchedKey.trim().length() > 0) {
               result.add(matchedKey);
            }
         }
      }

      return result;
   }

   private String checkWordsMatch(String source, Character key) {
      for(String unit : (List<String>)this.dirtyWords.get(key)) {
         DirtyWordUnit wordUnit = new DirtyWordUnit(source, unit);
         wordUnit.checkWordIndex();
         if (wordUnit.foundDitryWord()) {
            return wordUnit.getKeyWord();
         }
      }

      return "";
   }

   private void initWordsStore(Set<String> words) {
      for(String word : words) {
         char first = word.charAt(0);
         List<String> sameFirst = (List)this.dirtyWords.computeIfAbsent(first, (k) -> new ArrayList());
         sameFirst.add(word);
      }

   }
}
