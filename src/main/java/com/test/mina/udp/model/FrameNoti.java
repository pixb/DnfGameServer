package com.test.mina.udp.model;

import com.test.mina.annotation.ListField;
import com.test.mina.annotation.MessageMeta;
import com.test.mina.udp.Message;
import java.util.List;

@MessageMeta(
   module = 1011
)
public class FrameNoti extends Message {
   @ListField(3)
   public List<FrameInfo> frameInfoList;
   public int currentFrameId;
}
