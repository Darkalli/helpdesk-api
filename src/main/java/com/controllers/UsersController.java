package com.controllers;

import com.dtos.PasswordUpdateDto;
import com.dtos.UserRegisterDto;
import com.dtos.UserUpdateDto;
import com.entities.User;
import com.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegisterDto user){
        User newUser = usersService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(usersService.getById(id));
    }

    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto user){
        usersService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordUpdateDto password){
        usersService.updatePassword(password);
        return ResponseEntity.ok().build();
    }

}
