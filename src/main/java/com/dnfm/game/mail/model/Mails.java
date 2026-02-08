package com.dnfm.game.mail.model;

import com.dnfm.mina.protobuf.PT_POST_ALL_LIST;
import java.util.ArrayList;
import java.util.List;
import org.nutz.lang.util.NutMap;

public class Mails {
   private int count;
   private List<PT_POST_ALL_LIST> postallist;
   private ArrayList<NutMap> datas;

   public int getCount() {
      return this.count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public List<PT_POST_ALL_LIST> getPostallist() {
      return this.postallist;
   }

   public void setPostallist(List<PT_POST_ALL_LIST> postallist) {
      this.postallist = postallist;
   }

   public ArrayList<NutMap> getDatas() {
      return this.datas;
   }

   public void setDatas(ArrayList<NutMap> datas) {
      this.datas = datas;
   }
}
