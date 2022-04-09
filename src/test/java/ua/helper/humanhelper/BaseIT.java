package ua.helper.humanhelper;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIT {

    public static final EasyRandom RANDOM = new EasyRandom();
    @Autowired
    public EntityManager entityManager;

  @Test
   void dummyTest() {
    System.out.println("Test start");
    assertTrue(true);
  }

  protected void flushAndClear() {
      entityManager.flush();
      entityManager.clear();
  }
}
