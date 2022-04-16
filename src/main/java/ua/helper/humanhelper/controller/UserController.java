package ua.helper.humanhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.helper.humanhelper.mapper.EntityMapper;
import ua.helper.humanhelper.model.dto.UserDto;
import ua.helper.humanhelper.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final EntityMapper homeMapper;
    private final UserService userService;

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.findAll().stream().map(homeMapper::userToUserDto).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return homeMapper.userToUserDto(userService.findById(id));
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long id) {
        userService.updateUser(userDto, id);
    }

    @DeleteMapping("/home/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
