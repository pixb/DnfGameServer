package com.dnfm.game.skill.service;

import com.dnfm.game.config.Skill;
import com.dnfm.game.skill.SkillDataPool;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
   Logger logger = LoggerFactory.getLogger(SkillService.class);
   private final List<Integer> targetNumSkillIds = Arrays.asList(701, 702, 703, 704, 925, 926, 927, 928, 922, 923, 924);

   public void initSkill(List<Skill> list) {
      Map<Integer, Skill> id2Skill = new HashMap(list.size());
      Map<String, Skill> name2Skill = new HashMap(list.size());

      for(Skill skill : list) {
         id2Skill.put(skill.getSkillId(), skill);
         name2Skill.put(skill.getSkillName(), skill);
      }

      SkillDataPool.id2Skill = id2Skill;
      SkillDataPool.name2Skill = name2Skill;
   }

   public Skill getSkillInfo(int skillId) {
      return (Skill)SkillDataPool.id2Skill.get(skillId);
   }
}
