package com.example.library.controller;

import com.example.library.DTO.LoginRequest;
import com.example.library.DTO.UserDTO;
import com.example.library.model.User;
import com.example.library.service.JwtService;
import com.example.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

private final UserService us;
    private final JwtService js;

 public UserController(UserService us,JwtService js){
     this.us = us;
     this.js = js;
 }

 @PostMapping("/user")
 public User createUser(@RequestBody User user){
     return us.createUser(user);
 }


 @PostMapping("/login")
 public ResponseEntity<String> login(@RequestBody LoginRequest request){
    User user =  us.login(request);

//    String token =  js.generateToken(user.getUsername());
    String token = js.generateToken(user.getUsername(), user.getRole());
     return new ResponseEntity<>(token, HttpStatus.OK);

 }

 @GetMapping("/user")
  public ResponseEntity<List<User>> getUser(){
     return new ResponseEntity<>(us.getUser(),HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping ("/userDTO")
  public List<UserDTO> get_U(){
     return us.getUser_DTO()
             .stream()
             .map(user -> new UserDTO(user))
             .toList();
  }
}
