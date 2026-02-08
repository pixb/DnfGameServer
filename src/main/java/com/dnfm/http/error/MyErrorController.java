package com.dnfm.http.error;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {
   @Autowired(
      required = false
   )
   protected HttpServletResponse response;

   @RequestMapping({"/error"})
   public void handleError() throws IOException {
      PrintWriter writer = this.response.getWriter();
      writer.println("\n<html>\n<head><title>403 Forbidden</title></head>\n<body bgcolor=\"white\">\n<center><h1>403 Forbidden</h1></center>\n<hr><center>BSD</center>\n</body>\n</html>\n");
   }

   public String getErrorPath() {
      return null;
   }
}
