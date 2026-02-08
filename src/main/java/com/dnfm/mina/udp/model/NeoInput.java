package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.ListField;
import java.util.List;

public class NeoInput {
   public int inputId;
   @ListField(3)
   public List<InputInfo> inputInfoList;
}
