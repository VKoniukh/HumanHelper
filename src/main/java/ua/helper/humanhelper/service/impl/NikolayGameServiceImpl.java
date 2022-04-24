package ua.helper.humanhelper.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.helper.humanhelper.model.nikolay.User;
import ua.helper.humanhelper.repository.NikolayGameRepository;
import ua.helper.humanhelper.service.NikolayGameService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NikolayGameServiceImpl implements NikolayGameService {

  private final NikolayGameRepository nikolayGameRepository;

  @PostConstruct
  private void innitDbInfo() {
    if (CollectionUtils.isEmpty(nikolayGameRepository.findAll())) {
      final User user1 = new User("Slava Ukraine", 1488L);
      final User user2 = new User("Nikolay Nagibator", 999999L);
      final User user3 = new User("mr. Gay", 0L);
      final User user4 = new User("Шустрый членикс", 3000L);
      final User user5 = new User("Killer228", 229L);
      nikolayGameRepository.saveAll(List.of(user1, user2, user3, user4, user5));
    }
  }

  @Override
  public List<User> getUsers() {
    final List<User> score = nikolayGameRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
    for (int i = 0; i < score.size(); i++) {
      score.get(i).setPlace(i + 1L);
    }
    return score;
  }
}
