package net.javaguides.springboot.Services.Implementation;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.DTO.UserDto;
import net.javaguides.springboot.Entity.User;
import net.javaguides.springboot.Mapper.UserMapper;
import net.javaguides.springboot.Reposatory.UserReposatory;
import net.javaguides.springboot.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserReposatory userReposatory;

    //ModelMapper Dependency injection :- for JPA to DTO   and   DTO to JPA conversion:-
    private ModelMapper modelMapper;    //:-   used in createUser method;

    @Override
    public UserDto createUser(UserDto userDto) {
        //Converting UserDto into JPAEntity :-
       // User user= UserMapper.DtoToJpa(userDto);
        User user=modelMapper.map(userDto,User.class);
       //Saving JPA Entity:-
    User savedUserEntity=   userReposatory.save(user);
    //Converting JPA Entity into UserDto:-
        UserDto savedUserDto=modelMapper.map(user,UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        if(userReposatory.existsById(id)==true) {
            Optional<User> userEntity = userReposatory.findById(id);
            //Converting JPA Entity into UserDto:-
            UserDto userDto = UserMapper.JpatoDto(userEntity.get());
            return userDto;
        }
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User>userList= userReposatory.findAll();
        List<UserDto> userDtoList=new ArrayList<>();
        for(int i=0;i<userList.size();i++){
            User userEntity=userList.get(i);
            //Converting JPA Entity into DTO:-
            UserDto userDto= UserMapper.JpatoDto(userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUseril(UserDto userDto, Long id) {

        // Check if the provided id is null or doesn't exist in the database
        if (id == null) {
            System.out.println("\n\nn\n\n +Id :-  " + id + "   id is null" + " \n\n\n\n");
            System.out.println("\n\nn\n\n +Id :-  " + id + "   id is null" + " \n\n\n\n");
            return null;
        }
        if (!userReposatory.existsById(id)) {
            System.out.println("\n\nn\n\n +Id :-  " + id + "   id dosnot exists" + " \n\n\n\n");
            System.out.println("\n\nn\n\n +Id :-  " + id + "   id dosnot exists" + " \n\n\n\n");
            return null;
        }
        // Retrieve the existing user using the provided id
        User Existing_user = userReposatory.findById(id).get();
        if (Existing_user == null) {
            return null; // Or throw an exception if preferred
        }

        // Dto to JPA Entity:-
        User userEntity=UserMapper.DtoToJpa(userDto);

        //Updating:-
        Existing_user.setEmail(userEntity.getEmail());
        Existing_user.setFirstName(userEntity.getFirstName());
        Existing_user.setLastName(userEntity.getLastName());
        userReposatory.save(Existing_user);

        //Get user by id returns userDTO:-
       return  getUserById(id);
    }

    @Override
    public boolean deleteUser(Long id) {
        if(userReposatory.existsById(id)==true){
            userReposatory.deleteById(id);
            return true;
        }
      return  false;
    }

    @Override
    public boolean AddList(List<UserDto> userDtoList) {

        for(int i=0;i<userDtoList.size();i++){
            UserDto userDto =userDtoList.get(i);
           //Create user function takes DTO as input so no need of conversion:-
            createUser(userDto);
        }
        return true;
    }


}
