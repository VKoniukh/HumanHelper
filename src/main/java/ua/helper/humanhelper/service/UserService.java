package ua.helper.humanhelper.service;

import ua.helper.humanhelper.model.User;
import ua.helper.humanhelper.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void updateUser(UserDto userDto, Long id);

    User saveUser(UserDto userDto);

    User findById(Long id);

    void deleteUser(Long id);
}
