package com.glampro.registerclient.controller;

import com.glampro.registerclient.dto.UpdatePasswordUserDTO;
import com.glampro.registerclient.dto.UserRequestDTO;
import com.glampro.registerclient.dto.UserUpdateDTO;
import com.glampro.registerclient.exception.ExceptionHandler;
import com.glampro.registerclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    protected UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO){
        try {
            userService.createUser(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
        } catch (ExceptionHandler e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        try {
            userService.updateServiceSalon(userUpdateDTO);
            return ResponseEntity.ok("Usuário alterado com sucesso!");
        } catch (ExceptionHandler e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePasswordUser(@RequestBody UpdatePasswordUserDTO updatePasswordUserDTO){
        try {
            userService.updatePassword(updatePasswordUserDTO);
            return ResponseEntity.ok("Password alterado com sucesso!");
        } catch (ExceptionHandler e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listUser(){
        try {
            return ResponseEntity.ok(userService.listUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        try {
            return ResponseEntity.ok(userService.getUserId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/byEmail")
    public ResponseEntity<?> getUserEmail(
            @RequestParam(name = "email",  required = true) String email){
        try {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> getUserDelete(@PathVariable String id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuário de deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
