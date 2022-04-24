package ua.helper.humanhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.helper.humanhelper.model.nikolay.User;
import ua.helper.humanhelper.service.NikolayGameService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NikolayGameController {

  private final NikolayGameService nikolayGameService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return nikolayGameService.getUsers();
  }
}
