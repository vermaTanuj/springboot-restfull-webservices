package net.javaguides.springboot.Mapper;

import net.javaguides.springboot.DTO.UserDto;
import net.javaguides.springboot.Entity.User;

public class UserMapper {

    // Dto to JPAEntity:-
    public static User DtoToJpa(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        return user;
    }

    //  JPAEntity to Dto:-
    public static UserDto JpatoDto(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return userDto;
    }
}
