package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.ListField;
import java.util.List;

public class FrameInfo {
   public int frameId;
   public int recvTickMs;
   @ListField(3)
   public List<RelayData> relayDataList;
}
