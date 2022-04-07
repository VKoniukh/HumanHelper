package ua.helper.humanhelper.service;

import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;

import java.util.List;

public interface HomeService {

    List<Home> findAll();

    void updateHome(HomeDto homeDto, Long id);

    Home saveHome(HomeDto homeDto);

    Home findById(Long id);

    void deleteHome(Long id);
}
