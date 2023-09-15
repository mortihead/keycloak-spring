package ru.mortihead.keycloakspring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class KeycloakSpringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void loggerTest1() {
      log.info("=>");
      String s = null;
      try {
          System.out.println(s.length());
      } catch (Exception e) {
          log.error("ERROR1");
          e.printStackTrace();
      }
    }



    @Test
    void loggerTest2() {
        log.info("=>");
        String s = null;
        try {
            System.out.println(s.length());
        } catch (Exception e) {
            log.error("ERROR2 {}", e);
        }
    }


}
