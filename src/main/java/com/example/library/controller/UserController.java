package com.example.library.controller;

import com.example.library.DTO.LoginRequest;
import com.example.library.model.User;
import com.example.library.service.JwtService;
import com.example.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    String token =  js.generateToken(user.getUsername());
     return new ResponseEntity<>(token, HttpStatus.OK);

 }
}
