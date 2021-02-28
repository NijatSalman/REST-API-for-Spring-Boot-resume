package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceInter userService;

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name="name",required = false) String name,
            @RequestParam(name="surname",required = false) String surname,
            @RequestParam(name = "age",required = false) Integer age
    ){
        List<User> userList=userService.getAll(name,surname,age);

        List<UserDTO> userDTOS=new ArrayList<>();

        for (int i=0; i<userList.size();i++){
            User user=userList.get(i);
            userDTOS.add(new UserDTO(user));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOS));
    }


    //this class working as getUser.we just want to indicate how access to different method
    @CrossOrigin
    @GetMapping("/foo")
    public ResponseEntity<ResponseDTO> foo(
            @RequestParam(name="name",required = false) String name,
            @RequestParam(name="surname",required = false) String surname,
            @RequestParam(name = "age",required = false) Integer age
    ){
        List<User> userList=userService.getAll(name,surname,age);

        List<UserDTO> userDTOS=new ArrayList<>();

        for (int i=0; i<userList.size();i++){
            User user=userList.get(i);
            userDTOS.add(new UserDTO(user));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(userDTOS));
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id){
        User user=userService.getById(id);

        //return ResponseEntity.status(HttpStatus.OK).body(userDTOS); //both of them can be used
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
    }


    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id){
        User user=userService.getById(id);
        userService.removeUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of("Successfully deleted",new UserDTO(user)));
    }

    @CrossOrigin
    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO){
        User user=new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userService.addUser(user);

        UserDTO objDTO=new UserDTO();
        objDTO.setId(user.getId());
        objDTO.setName(user.getName());
        objDTO.setSurname(user.getSurname());
        objDTO.setPassword(user.getPassword());
        objDTO.setEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of("Successfully added",objDTO));
    }


}
