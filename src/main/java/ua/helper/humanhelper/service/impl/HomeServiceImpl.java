package ua.helper.humanhelper.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.helper.humanhelper.mapper.EntityMapper;
import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;
import ua.helper.humanhelper.repository.HomeRepository;
import ua.helper.humanhelper.service.HomeService;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {

    public HomeServiceImpl(HomeRepository homeRepository, EntityMapper entityMapper) {
        this.homeRepository = homeRepository;
        this.entityMapper = entityMapper;
    }

    private final HomeRepository homeRepository;
    private final EntityMapper entityMapper;

    @Override
    public List<Home> findAll() {
    return homeRepository.findAll(Sort.by("id"));
    }

    @Override
    public Home findById(Long id) {
        return homeRepository.getById(id);
    }

    @Override
    public void updateHome(HomeDto homeDto, Long id) {
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
    public Home saveHome(HomeDto homeDto) {
        return homeRepository.save(entityMapper.homeDtoToHome(homeDto));
    }


    @Override
    public void deleteHome(Long id) {
        homeRepository.delete(findById(id));
    }
}
