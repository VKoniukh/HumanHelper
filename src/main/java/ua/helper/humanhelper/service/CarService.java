package ua.helper.humanhelper.service;

import ua.helper.humanhelper.model.Car;
import ua.helper.humanhelper.model.dto.CarDto;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    void updateCar(CarDto carDto, Long id);

    Car saveCar(CarDto carDto);

    Car findById(Long id);

    void deleteCar(Long id);
}
