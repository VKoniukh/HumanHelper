package ua.helper.humanhelper.mapper;

import org.mapstruct.Mapper;
import ua.helper.humanhelper.model.Car;
import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.CarDto;
import ua.helper.humanhelper.model.dto.HomeDto;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    HomeDto homeToHomeDto(Home home);

    Home homeDtoToHome(HomeDto homeDto);

    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto carDto);
}
