package ua.helper.humanhelper.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String username;

    private String email;

    private String provider;
}
