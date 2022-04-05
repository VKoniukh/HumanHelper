package ua.helper.humanhelper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.helper.humanhelper.mapper.HomeMapper;
import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;
import ua.helper.humanhelper.repository.HomeRepository;
import ua.helper.humanhelper.service.HomeService;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    private final HomeRepository homeRepository;

    @Override
    public List<Home> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Home findById(long id) {
        return homeRepository.getById(id);
    }

    @Override
    public void updateHome(HomeDto homeDto, long id) {
        Optional<Home> homeOptional = homeRepository.findById(id);
        homeOptional.ifPresent(home -> {
            home.setLocation(homeDto.getLocation());
            home.setDescription(homeDto.getDescription());
            home.setSleepPlace(homeDto.getSleepPlace());
            home.setPrice(homeDto.getPrice());
            homeRepository.save(home);
        });
    }

    @Override
    public Home saveHome(HomeDto homeDto, long id) {
        return homeRepository.save(HomeMapper.INSTANCE.HomeDtoToHome(homeDto));
    }


    @Override
    public void deleteHome(long id) {
        homeRepository.delete(findById(id));
    }
}
