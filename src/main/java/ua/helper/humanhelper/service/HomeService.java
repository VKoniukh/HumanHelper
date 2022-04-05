package ua.helper.humanhelper.service;

import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;

import java.util.List;

public interface HomeService {

    List<Home> findAll();

    void updateHome(HomeDto homeDto, long id);

    Home saveHome(HomeDto homeDto, long id);

    Home findById(long id);

    void deleteHome(long id);
}
