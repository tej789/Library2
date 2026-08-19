package com.example.library.DTO;

import com.example.library.model.User;
import lombok.Getter;

@Getter
public class UserDTO {
    private String username;
    private String role;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.role = user.getRole();
    }




}
