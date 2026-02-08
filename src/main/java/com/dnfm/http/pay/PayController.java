package com.dnfm.http.pay;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pay"})
public class PayController {
   @Autowired(
      required = false
   )
   HttpServletResponse response;

   @RequestMapping(
      method = {RequestMethod.POST},
      path = {"order"}
   )
   @ResponseBody
   public String order(String data) throws IOException {
      return "";
   }
}
