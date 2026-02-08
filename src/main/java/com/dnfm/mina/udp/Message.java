package com.dnfm.mina.udp;

import com.dnfm.mina.annotation.MessageMeta;
import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class Message implements Serializable {
   @JsonIgnore
   public int getModule() {
      MessageMeta annotation = (MessageMeta)this.getClass().getAnnotation(MessageMeta.class);
      return annotation != null ? annotation.module() : 0;
   }
}
