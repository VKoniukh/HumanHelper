package ua.helper.humanhelper.service.impl;

import org.springframework.stereotype.Service;
import ua.helper.humanhelper.mapper.EntityMapper;
import ua.helper.humanhelper.model.Car;
import ua.helper.humanhelper.model.dto.CarDto;
import ua.helper.humanhelper.repository.CarRepository;
import ua.helper.humanhelper.service.CarService;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    public CarServiceImpl(CarRepository carRepository, EntityMapper entityMapper) {
        this.carRepository = carRepository;
        this.entityMapper = entityMapper;
    }

    private final CarRepository carRepository;
    private final EntityMapper entityMapper;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void updateCar(CarDto carDto, Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(car -> {
            car.setRouteFrom(carDto.getRouteFrom());
            car.setRouteTo(carDto.getRouteTo());
            car.setDescription(carDto.getDescription());
            car.setPrice(carDto.getPrice());
            car.setDate(carDto.getDate());
            carRepository.save(car);
        });
    }

    @Override
    public Car saveCar(CarDto carDto) {
        return carRepository.save(entityMapper.carDtoToCar(carDto));
    }

    @Override
    public Car findById(Long id) {
        return carRepository.getById(id);
    }

    @Override
    public void deleteCar(Long id) {
     carRepository.delete(findById(id));
    }
}
