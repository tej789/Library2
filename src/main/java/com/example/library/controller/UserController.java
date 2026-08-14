package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

private final UserService us;

 public UserController(UserService us){
     this.us = us;
 }

 @PostMapping("/user")
 public User createUser(@RequestBody User user){
     return us.createUser(user);
 }

}
