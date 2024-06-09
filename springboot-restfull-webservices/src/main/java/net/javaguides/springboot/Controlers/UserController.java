package net.javaguides.springboot.Controlers;


import lombok.AllArgsConstructor;
import net.javaguides.springboot.DTO.UserDto;
import net.javaguides.springboot.Entity.User;
import net.javaguides.springboot.Services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {


    private UserServiceImpl userService;

    @PostMapping("create")                         //http://localhost:8080/api/users/create
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUserDto=userService.createUser(user);
      return  new ResponseEntity<>( savedUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/FindById/{id}")                    //http://localhost:8080/api/users/FindById/{id}
    public ResponseEntity<UserDto>  getUserById( @PathVariable Long id) {
        UserDto userdto= userService.getUserById(id);
       if(userdto!=null) {
           return new ResponseEntity<>(userdto, HttpStatus.OK);
       }else{
           return new ResponseEntity<>(userdto, HttpStatus.NOT_FOUND);
       }
    }


    @GetMapping("/GetAll")                    //http://localhost:8080/api/users/GetAll
    public ResponseEntity<List<UserDto>>  getAllUsers() {
        List<UserDto> listDto= userService.getAllUser();
       return new ResponseEntity<>(listDto,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")               //http://localhost:8080/api/users/update/{id}
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto,@PathVariable("id") Long id){
     UserDto UpdatedUserDto=   userService.updateUseril(userDto,id);
     if(UpdatedUserDto==null){
         System.out.println("Nullreturned from Sevice:-");
         System.out.println("Nullreturned from Sevice:-");
         System.out.println("Nullreturned from Sevice:-");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     return    new ResponseEntity<>(UpdatedUserDto,HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{id}")   //http://localhost:8080/api/users/Delete/{id}
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long idd){
       boolean bb= userService.deleteUser(idd);
       if(bb==true){
           return new ResponseEntity<>("User Record Deleted",HttpStatus.OK);
       }
        return new ResponseEntity<>("User Record not found ",HttpStatus.NOT_FOUND);
    }

    //Adding A list:-
    @PostMapping("/AddList")       //http://localhost:8080/api/users/AddList
    public ResponseEntity<?> AddListOfUserDTO(@RequestBody List<UserDto> userDtoList){
     boolean bb=  userService.AddList(userDtoList);
     if(bb==true){
       return   new ResponseEntity<>("List is added",HttpStatus.CREATED);
     }
     else{
        return new ResponseEntity<>("List is not added",HttpStatus.BAD_REQUEST);
     }
    }

}


