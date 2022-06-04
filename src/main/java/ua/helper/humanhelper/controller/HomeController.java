package ua.helper.humanhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.helper.humanhelper.aspect.annotation.LogExecutionTime;
import ua.helper.humanhelper.mapper.EntityMapper;
import ua.helper.humanhelper.model.dto.HomeDto;
import ua.helper.humanhelper.service.HomeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final EntityMapper homeMapper;
    private final HomeService homeService;

  @GetMapping("/home")
  @LogExecutionTime
  public List<HomeDto> getAllHomes() {
        return homeService.findAll().stream().map(homeMapper::homeToHomeDto).collect(Collectors.toList());
    }

  @GetMapping("/home/{id}")
  @LogExecutionTime
  public HomeDto getHomeById(@PathVariable("id") Long id) {
        return homeMapper.homeToHomeDto(homeService.findById(id));
    }

  @PostMapping("/home")
  @LogExecutionTime
  public HomeDto saveHome(@RequestBody HomeDto homeDto) {
    return homeMapper.homeToHomeDto(homeService.saveHome(homeDto));
    }

  @PutMapping("/home/{id}")
  @LogExecutionTime
  public void updateHome(@RequestBody HomeDto homeDto, @PathVariable("id") Long id) {
        homeService.updateHome(homeDto, id);
    }

  @DeleteMapping("/home/{id}")
  @LogExecutionTime
  public void deleteHome(@PathVariable("id") Long id) {
        homeService.deleteHome(id);
    }
}