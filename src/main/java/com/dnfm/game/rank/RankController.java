package com.dnfm.game.rank;

import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.REQ_INQUIRE_PERSONAL_RANKING;
import com.dnfm.mina.protobuf.REQ_MY_PARTY_RANKING;
import com.dnfm.mina.protobuf.REQ_MY_RANKING;
import com.dnfm.mina.protobuf.REQ_RANK_FRIEND;
import com.dnfm.mina.protobuf.RES_INQUIRE_PERSONAL_RANKING;
import com.dnfm.mina.protobuf.RES_MY_PARTY_RANKING;
import com.dnfm.mina.protobuf.RES_MY_RANKING;
import com.dnfm.mina.protobuf.RES_RANKING_FRIEND;
import java.util.ArrayList;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;

@Controller
public class RankController {
   @RequestMapping
   public void ReqInquirePersonalRanking(IoSession session, REQ_INQUIRE_PERSONAL_RANKING reqInquirePersonalRanking) {
      RES_INQUIRE_PERSONAL_RANKING resInquirePersonalRanking = new RES_INQUIRE_PERSONAL_RANKING();
      resInquirePersonalRanking.error = 3;
      resInquirePersonalRanking.transId = reqInquirePersonalRanking.transId;
      MessagePusher.pushMessage((IoSession)session, resInquirePersonalRanking);
   }

   @RequestMapping
   public void reqMyRanking(IoSession session, REQ_MY_RANKING reqMyRanking) {
      RES_MY_RANKING resMyRanking = new RES_MY_RANKING();
      resMyRanking.error = 3;
      resMyRanking.transId = reqMyRanking.transId;
      MessagePusher.pushMessage((IoSession)session, resMyRanking);
   }

   @RequestMapping
   public void REQ_RANK_FRIEND(IoSession session, REQ_RANK_FRIEND req_rank_friend) {
      RES_RANKING_FRIEND resRankingFriend = new RES_RANKING_FRIEND();
      resRankingFriend.ranking = new ArrayList();
      resRankingFriend.type = req_rank_friend.type;
      resRankingFriend.transId = req_rank_friend.transId;
      MessagePusher.pushMessage((IoSession)session, resRankingFriend);
   }

   @RequestMapping
   public void reqMyPartyRanking(IoSession session, REQ_MY_PARTY_RANKING reqMyPartyRanking) {
      RES_MY_PARTY_RANKING resMyPartyRanking = new RES_MY_PARTY_RANKING();
      resMyPartyRanking.error = 3;
      resMyPartyRanking.transId = reqMyPartyRanking.transId;
      MessagePusher.pushMessage((IoSession)session, resMyPartyRanking);
   }
}
