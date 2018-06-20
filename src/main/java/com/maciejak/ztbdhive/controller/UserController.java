package com.maciejak.ztbdhive.controller;

import com.maciejak.ztbdhive.model.User;
import com.maciejak.ztbdhive.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createOrUpdateUser(user));
    }

    @PutMapping()
    @ResponseBody
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok(userService.createOrUpdateUser(user));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getAUserById(
            @PathVariable(value = "id") Integer userId
    ){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletetAUserById(
            @PathVariable(value = "id") Integer userId
    ){
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }


}
