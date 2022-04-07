package ua.helper.humanhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public List<HomeDto> getAllHomes() {
        return homeService.findAll().stream().map(homeMapper::HomeToHomeDto).collect(Collectors.toList());
    }

    @GetMapping("/home/{id}")
    public HomeDto getHomeById(@PathVariable("id") Long id) {
        return homeMapper.HomeToHomeDto(homeService.findById(id));
    }

    @PostMapping("/home")
    public void saveHome(@RequestBody HomeDto homeDto) {
        homeService.saveHome(homeDto);
    }

    @PutMapping("/home/{id}")
    public void updateHome(@RequestBody HomeDto homeDto, @PathVariable("id") Long id) {
        homeService.updateHome(homeDto, id);
    }

    @DeleteMapping("/home")
    public void deleteHome(@PathVariable("id") Long id) {
        homeService.deleteHome(id);
    }
}