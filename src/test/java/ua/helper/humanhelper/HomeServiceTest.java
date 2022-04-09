package ua.helper.humanhelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;
import ua.helper.humanhelper.service.HomeService;

import java.math.RoundingMode;

class HomeServiceTest extends BaseIT {

    @Autowired
    private HomeService homeService;

    @Test
    @Transactional
    void saveHomeAndGetByIdTest() {
        final HomeDto homeDto = RANDOM.nextObject(HomeDto.class);
        final Long savedHomeId = homeService.saveHome(homeDto).getId();
        flushAndClear();

        final Home savedHome = homeService.findById(savedHomeId);
        Assertions.assertEquals(homeDto.getDescription(), savedHome.getDescription());
        Assertions.assertEquals(homeDto.getLocation(), savedHome.getLocation());
        Assertions.assertEquals(homeDto.getPrice().setScale(2, RoundingMode.HALF_UP), savedHome.getPrice());
        Assertions.assertEquals(homeDto.getSleepPlace(), savedHome.getSleepPlace());
    }
}
