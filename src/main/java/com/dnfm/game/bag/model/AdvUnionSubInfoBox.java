package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_CHARGUID_ENTRANCE_ITEM;
import com.dnfm.mina.protobuf.PT_CHARGUID_FATIGUE;
import com.dnfm.mina.protobuf.PT_CHARGUID_TICKET;
import com.dnfm.mina.protobuf.PT_SUBDUE_INFO;
import java.util.List;

public class AdvUnionSubInfoBox {
   public List<PT_SUBDUE_INFO> list = null;
   public List<PT_CHARGUID_FATIGUE> fatigues = null;
   public List<PT_CHARGUID_TICKET> tickets = null;
   public List<PT_CHARGUID_ENTRANCE_ITEM> entranceitems = null;
}
