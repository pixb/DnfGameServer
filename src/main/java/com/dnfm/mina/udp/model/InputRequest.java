package com.dnfm.mina.udp.model;

import com.dnfm.mina.annotation.ListField;
import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.udp.Message;
import java.util.List;

@MessageMeta(
   module = 1010
)
public class InputRequest extends Message {
   @ListField(3)
   public List<NeoInput> inputList;
}
