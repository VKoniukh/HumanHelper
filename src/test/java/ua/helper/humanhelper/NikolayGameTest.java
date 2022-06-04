package ua.helper.humanhelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.helper.humanhelper.model.nikolay.User;
import ua.helper.humanhelper.service.NikolayGameService;

import java.util.List;

class NikolayGameTest extends BaseIT {

  @Autowired private NikolayGameService nikolayGameService;

  @Test
  @Transactional
  void getAllUsers() {
    final List<User> users = nikolayGameService.getUsers();
    for (User u : users) {
      System.out.println(u);
    }
    Assertions.assertEquals(5, users.size());
  }
}
