package com.maciejak.ztbdhive.controller;

import com.maciejak.ztbdhive.model.User;
import com.maciejak.ztbdhive.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    @PutMapping
    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createOrUpdateUser(user));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getAUserById(
            @PathVariable("id") Long userId
    ){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletetAUserById(
            @PathVariable("id") Long userId
    ){
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }


}
