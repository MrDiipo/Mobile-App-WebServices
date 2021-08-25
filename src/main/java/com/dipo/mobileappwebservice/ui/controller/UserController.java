package com.dipo.mobileappwebservice.ui.controller;

import com.dipo.mobileappwebservice.ui.model.request.UserDetailRequestModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @GetMapping
    public String getUser(){
        return "get user was called";
    }

    @PostMapping
    public String createUser(@RequestBody UserDetailRequestModel userDetails){
        return "Create user was called";
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "Delete user was called";
    }
}
