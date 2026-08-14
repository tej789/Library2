package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository ur;
    private final PasswordEncoder pe;

    public UserService(UserRepository ur,PasswordEncoder pe){
        this.ur = ur;
        this.pe =pe;
    }

    public User createUser(User user) {

        user.setPassword(
                pe.encode(user.getPassword())
        );

        return ur.save(user);
    }
}
