package com.test.mina.udp.model;

import com.test.mina.annotation.ListField;
import java.util.List;

public class NeoInput {
   public int inputId;
   @ListField(3)
   public List<InputInfo> inputInfoList;
}
