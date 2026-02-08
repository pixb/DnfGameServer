package com.dnfm.adminclient;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AdminClientService {
   @Scheduled(
      cron = "0 0/1 * * * ?"
   )
   public void postToAdmin() {
   }
}
