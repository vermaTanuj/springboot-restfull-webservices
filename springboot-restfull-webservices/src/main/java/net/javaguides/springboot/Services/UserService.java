package net.javaguides.springboot.Services;

import net.javaguides.springboot.DTO.UserDto;
import net.javaguides.springboot.Entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUser();
    UserDto updateUseril(UserDto user,Long id);
    boolean deleteUser(Long id);

    boolean AddList(List<UserDto> userDtoList);

}
