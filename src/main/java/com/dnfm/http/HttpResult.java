package com.dnfm.http;

public class HttpResult {
   private String status;
   private String msg;

   public static HttpResult valueOfOk(String msg) {
      HttpResult reply = new HttpResult();
      reply.status = "success";
      reply.msg = msg;
      return reply;
   }

   public static HttpResult valueOfFail(String msg) {
      HttpResult reply = new HttpResult();
      reply.status = "error";
      reply.msg = msg;
      return reply;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }
}
