package com.dnfm.game.skill;

import com.dnfm.game.role.model.Role;
import com.dnfm.game.skill.bean.SkillObject;
import com.dnfm.game.skill.model.SkillBox;
import com.dnfm.game.skill.model.SkillslotBox;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_ALL_SKILL_SLOT;
import com.dnfm.mina.protobuf.PT_SKILL;
import com.dnfm.mina.protobuf.REQ_SKILL_LIST;
import com.dnfm.mina.protobuf.REQ_SKILL_SET;
import com.dnfm.mina.protobuf.REQ_SKILL_SLOT;
import com.dnfm.mina.protobuf.RES_SKILL_LIST;
import com.dnfm.mina.protobuf.RES_SKILL_SET;
import com.dnfm.mina.protobuf.RES_SKILL_SLOT;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class SkillController {
   Logger logger = LoggerFactory.getLogger(SkillController.class);

   @RequestMapping
   public void ReqSkillList(IoSession session, REQ_SKILL_LIST reqSkillList) {
      Role role = (Role)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.PLAYER, Role.class);
      SkillBox skillBox = role.getSkillBox();
      SkillslotBox skilllotBox = role.getSkillslotBox();
      RES_SKILL_LIST res_skill_list = new RES_SKILL_LIST();
      if (reqSkillList.type == null) {
         int sp = skillBox.getSp();
         if (sp < 0) {
            skillBox.setSp(0);
            sp = 0;
         }

         int tp = skillBox.getTp();
         if (tp < 0) {
            skillBox.setTp(0);
            tp = 0;
         }

         int addsp = skillBox.getAddsp();
         int addtp = skillBox.getAddtp();
         if (sp != 0) {
            res_skill_list.sp = sp;
         }

         if (tp != 0) {
            res_skill_list.tp = tp;
         }

         if (addsp != 0) {
            res_skill_list.addsp = addsp;
         }

         if (addtp != 0) {
            res_skill_list.addtp = addtp;
         }

         res_skill_list.skilllist = skillBox.getSkills();
         res_skill_list.skillslot = skilllotBox.getSkillslot();
      } else if (reqSkillList.type == 1) {
         int sp_pk = skillBox.getSp_pk();
         int tp_pk = skillBox.getTp_pk();
         int addsp_pk = skillBox.getAddsp_pk();
         int addtp_pk = skillBox.getAddtp_pk();
         if (sp_pk != 0) {
            res_skill_list.sp = sp_pk;
         }

         if (tp_pk != 0) {
            res_skill_list.tp = tp_pk;
         }

         if (addsp_pk != 0) {
            res_skill_list.addsp = addsp_pk;
         }

         if (addtp_pk != 0) {
            res_skill_list.addtp = addtp_pk;
         }

         res_skill_list.type = 1;
         res_skill_list.skilllist = skillBox.getSkilllist_pk();
         res_skill_list.skillslot = skilllotBox.getSkillslot_pk();
      }

      res_skill_list.transId = reqSkillList.transId;
      MessagePusher.pushMessage((IoSession)session, res_skill_list);
   }

   @RequestMapping
   public void REQ_SKILL_SET(IoSession session, REQ_SKILL_SET reqLearnSkill) {
      Role role = SessionUtils.getRoleBySession(session);
      int job = role.getJob();
      List<PT_SKILL> reqskill = reqLearnSkill.skilllist;
      SkillBox skillBox = role.getSkillBox();
      int sp = skillBox.getSp();
      int tp = skillBox.getTp();
      if (reqLearnSkill.type != null) {
         sp = skillBox.getSp_pk();
      }

      Map<Integer, PT_SKILL> myskills = new HashMap();
      if (reqLearnSkill.type == null) {
         for(PT_SKILL skill1 : skillBox.getSkills()) {
            myskills.put(skill1.index, skill1);
         }
      } else {
         for(PT_SKILL skill1 : skillBox.getSkilllist_pk()) {
            myskills.put(skill1.index, skill1);
         }
      }

      int cost = 0;

      for(int i = 0; i < reqskill.size(); ++i) {
         int maxlevel = 0;
         PT_SKILL entry = (PT_SKILL)reqskill.get(i);
         SkillObject skillObject = new SkillObject();
         if (job == 0) {
            skillObject = (SkillObject)DataCache.SKILLSWORDMAN.get(entry.index);
         } else if (job == 1) {
            skillObject = (SkillObject)DataCache.SKILLFIGHTER.get(entry.index);
         } else if (job == 2) {
            skillObject = (SkillObject)DataCache.SKILLGUNNER.get(entry.index);
         } else if (job == 3) {
            skillObject = (SkillObject)DataCache.SKILLMAGE.get(entry.index);
         } else if (job == 14) {
            skillObject = (SkillObject)DataCache.SKILLATPRIEST.get(entry.index);
         }

         cost = skillObject.getCost();
         maxlevel = skillObject.getMaxlevel();
         PT_SKILL roleskill = (PT_SKILL)myskills.get(entry.index);
         if (roleskill == null) {
            PT_SKILL roleskill1 = new PT_SKILL();
            roleskill1.index = entry.index;
            roleskill1.level = entry.level;
            if (maxlevel == 7) {
               tp -= entry.level * cost;
            } else {
               sp -= entry.level * cost;
            }
         } else if (entry.level != roleskill.level) {
            if (entry.level < roleskill.level) {
               if (maxlevel == 7) {
                  tp += (roleskill.level - entry.level) * cost;
               } else {
                  sp += (roleskill.level - entry.level) * cost;
               }
            } else if (maxlevel == 7) {
               tp -= (entry.level - roleskill.level) * cost;
            } else if (sp >= cost) {
               sp -= (entry.level - roleskill.level) * cost;
            }
         }
      }

      if (reqLearnSkill.type == null) {
         skillBox.setSkilllist(reqskill);
         skillBox.setSp(sp);
         skillBox.setTp(tp);
         role.save();
      } else {
         skillBox.setSkilllist_pk(reqskill);
         skillBox.setSp_pk(sp);
         skillBox.setTp_pk(tp);
         role.save();
      }

      RES_SKILL_SET resSkillSet = new RES_SKILL_SET();
      resSkillSet.type = reqLearnSkill.type;
      resSkillSet.transId = reqLearnSkill.transId;
      MessagePusher.pushMessage((IoSession)session, resSkillSet);
   }

   @RequestMapping
   public void REQ_SKILL_SLOT(IoSession session, REQ_SKILL_SLOT reqSkillSlot) {
      Role role = SessionUtils.getRoleBySession(session);
      if (reqSkillSlot.type == null) {
         PT_ALL_SKILL_SLOT pt_all_skill_slot = role.getSkillslotBox().getSkillslot();
         pt_all_skill_slot.active = reqSkillSlot.active;
         pt_all_skill_slot.buff = reqSkillSlot.buff;
         pt_all_skill_slot.combo = reqSkillSlot.combo;
         pt_all_skill_slot.keyboardmatching = reqSkillSlot.keyboardmatching;
         pt_all_skill_slot.padmatching = reqSkillSlot.padmatching;
      } else {
         PT_ALL_SKILL_SLOT pt_all_skill_slot_pk = role.getSkillslotBox().getSkillslot_pk();
         pt_all_skill_slot_pk.active = reqSkillSlot.active;
         pt_all_skill_slot_pk.buff = reqSkillSlot.buff;
         pt_all_skill_slot_pk.combo = reqSkillSlot.combo;
         pt_all_skill_slot_pk.keyboardmatching = reqSkillSlot.keyboardmatching;
         pt_all_skill_slot_pk.padmatching = reqSkillSlot.padmatching;
      }

      role.save();
      RES_SKILL_SLOT resSkillSlot = new RES_SKILL_SLOT();
      resSkillSlot.type = reqSkillSlot.type;
      resSkillSlot.transId = reqSkillSlot.transId;
      MessagePusher.pushMessage((IoSession)session, resSkillSlot);
   }
}
