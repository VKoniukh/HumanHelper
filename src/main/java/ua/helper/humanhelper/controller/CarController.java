package ua.helper.humanhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.helper.humanhelper.mapper.EntityMapper;
import ua.helper.humanhelper.model.dto.CarDto;
import ua.helper.humanhelper.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CarController {

    private final EntityMapper carMapper;
    private final CarService carService;

    @GetMapping("/car")
    public List<CarDto> getAllCars() {
        return carService.findAll().stream().map(carMapper::carToCarDto).collect(Collectors.toList());
    }

    @GetMapping("/car/{id}")
    public CarDto getCarById(@PathVariable("id") Long id) {
        return carMapper.carToCarDto(carService.findById(id));
    }

    @PostMapping("/car")
    public void saveCar(@RequestBody CarDto carDto) {
        carService.saveCar(carDto);
    }

    @PutMapping("/car/{id}")
    public void updateCar(@RequestBody CarDto carDto, @PathVariable("id") Long id) {
        carService.updateCar(carDto, id);
    }

    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }
}