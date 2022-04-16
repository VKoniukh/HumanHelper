package ua.helper.humanhelper.service.impl;

import ua.helper.humanhelper.mapper.EntityMapper;
import ua.helper.humanhelper.model.CustomOAuth2User;
import ua.helper.humanhelper.model.User;
import org.springframework.stereotype.Service;
import ua.helper.humanhelper.model.dto.UserDto;
import ua.helper.humanhelper.model.enums.Provider;
import ua.helper.humanhelper.repository.UserRepository;
import ua.helper.humanhelper.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserRepository userRepository, EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
        this.userRepository = userRepository;
    }

    private final EntityMapper entityMapper;
    private final UserRepository userRepository;

    @Override
    public void processOAuthPostLogin(CustomOAuth2User oathUser) {
        User existUser = userRepository.findUserByEmail(oathUser.getEmail());

        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(oathUser.getEmail());
            newUser.setUsername(oathUser.getName());
            newUser.setProvider(Provider.GOOGLE);

            userRepository.save(newUser);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UserDto userDto, Long id) {
        Optional<User> userOptional = userRepository.findById(String.valueOf(id));
        userOptional.ifPresent(user -> {
            user.setUsername(userDto.getUsername());
            user.setEmail(user.getEmail());
            user.setProvider(Provider.valueOf(userDto.getProvider()));
            userRepository.save(user);
        });
    }

    @Override
    public User saveUser(UserDto userDto) {
        return userRepository.save(entityMapper.userDtoToUser(userDto));
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(String.valueOf(id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(findById(Long.valueOf(id)));
    }
}

