package ua.helper.humanhelper.spockTests.service

import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import ua.helper.humanhelper.model.Home
import ua.helper.humanhelper.model.dto.HomeDto
import ua.helper.humanhelper.service.HomeService

class HomeServiceTest extends Specification {

    @Autowired
    HomeService homeService;

    def "should save and find home by id"() {
        given:
        Home savedHome = getSavedHome()

        when:
        Home homeById = homeService.findById(savedHome.getId())

        than:
        homeById.getId() == savedHome.getId()
        homeById.getLocation() == savedHome.getLocation()
        homeById.getDescription() == savedHome.getDescription()
        homeById.getPrice() == savedHome.getPrice()
        homeById.getSleepPlace() == savedHome.getSleepPlace()
    }

    private Home getSavedHome() {
        HomeDto homeDto = new HomeDto();

        homeDto.setPrice(999)
        homeDto.sleepPlace(5)
        homeDto.setDescription("Hi! I'm Bogdan I'm here!")
        homeDto.setLocation("Lviv")

        homeService.saveHome(homeDto);
    }
}
