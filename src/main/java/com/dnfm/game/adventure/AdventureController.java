package com.dnfm.game.adventure;

import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_REAP_REWARD;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_COLLECTION_REWARD;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_LEVEL_REWARD;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_NAME_CHANGE;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_SEARCH_START;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_UNION_SET_SHAREBOARD;
import com.dnfm.mina.protobuf.RES_ADVENTURE_REAP_REWARD;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_COLLECTION_REWARD;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_LEVEL_REWARD;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_NAME_CHANGE;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_SEARCH_START;
import com.dnfm.mina.protobuf.RES_ADVENTURE_UNION_SET_SHAREBOARD;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;

@Controller
public class AdventureController {
   @RequestMapping
   public void reqAdventureUnionNameChange(IoSession session, REQ_ADVENTURE_UNION_NAME_CHANGE reqAdventureUnionNameChange) {
      RES_ADVENTURE_UNION_NAME_CHANGE resAdventureUnionNameChange = new RES_ADVENTURE_UNION_NAME_CHANGE();
      resAdventureUnionNameChange.error = 3;
      resAdventureUnionNameChange.transId = reqAdventureUnionNameChange.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureUnionNameChange);
   }

   @RequestMapping
   public void reqAdventureUnionLevelReward(IoSession session, REQ_ADVENTURE_UNION_LEVEL_REWARD req_adventure_union_level_reward) {
      RES_ADVENTURE_UNION_LEVEL_REWARD res_adventure_union_level_reward = new RES_ADVENTURE_UNION_LEVEL_REWARD();
      res_adventure_union_level_reward.error = 3;
      res_adventure_union_level_reward.transId = req_adventure_union_level_reward.transId;
      MessagePusher.pushMessage((IoSession)session, res_adventure_union_level_reward);
   }

   @RequestMapping
   public void reqAdventureReapReward(IoSession session, REQ_ADVENTURE_REAP_REWARD req_adventure_reap_reward) {
      RES_ADVENTURE_REAP_REWARD res_adventure_reap_reward = new RES_ADVENTURE_REAP_REWARD();
      res_adventure_reap_reward.error = 3;
      res_adventure_reap_reward.transId = req_adventure_reap_reward.transId;
      MessagePusher.pushMessage((IoSession)session, res_adventure_reap_reward);
   }

   @RequestMapping
   public void reqAdventureUnionSearchStart(IoSession session, REQ_ADVENTURE_UNION_SEARCH_START reqAdventureUnionSearchStart) {
      RES_ADVENTURE_UNION_SEARCH_START resAdventureUnionSearchStart = new RES_ADVENTURE_UNION_SEARCH_START();
      resAdventureUnionSearchStart.error = 3;
      resAdventureUnionSearchStart.transId = reqAdventureUnionSearchStart.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureUnionSearchStart);
   }

   @RequestMapping
   public void reqAdventureUnionSetShareboard(IoSession session, REQ_ADVENTURE_UNION_SET_SHAREBOARD reqAdventureUnionSetShareboard) {
      RES_ADVENTURE_UNION_SET_SHAREBOARD resAdventureUnionSetShareboard = new RES_ADVENTURE_UNION_SET_SHAREBOARD();
      resAdventureUnionSetShareboard.error = 3;
      resAdventureUnionSetShareboard.transId = reqAdventureUnionSetShareboard.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureUnionSetShareboard);
   }

   @RequestMapping
   public void reqAdventureUnionOpenShareboardSlot(IoSession session, REQ_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT reqAdventureUnionOpenShareboardSlot) {
      RES_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT resAdventureUnionOpenShareboardSlot = new RES_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT();
      resAdventureUnionOpenShareboardSlot.error = 3;
      resAdventureUnionOpenShareboardSlot.transId = reqAdventureUnionOpenShareboardSlot.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureUnionOpenShareboardSlot);
   }

   @RequestMapping
   public void reqAdventureUnionCollectionReward(IoSession session, REQ_ADVENTURE_UNION_COLLECTION_REWARD reqAdventureUnionCollectionReward) {
      RES_ADVENTURE_UNION_COLLECTION_REWARD resAdventureUnionCollectionReward = new RES_ADVENTURE_UNION_COLLECTION_REWARD();
      resAdventureUnionCollectionReward.error = 3;
      resAdventureUnionCollectionReward.transId = reqAdventureUnionCollectionReward.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureUnionCollectionReward);
   }
}
