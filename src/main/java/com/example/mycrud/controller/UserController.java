package com.example.mycrud.controller;

import com.example.mycrud.model.dto.UserCreateDTO;
import com.example.mycrud.model.dto.UserReadDTO;
import com.example.mycrud.model.dto.UserUpdateDTO;
import com.example.mycrud.service.IUserService;
import com.example.mycrud.utils.ApiResponse;
import com.example.mycrud.utils.ValidationExceptionHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserReadDTO>>> getUsers(){
        return userService.getUsers();
    }

    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserReadDTO>> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<ApiResponse<UserReadDTO>> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    // Endpoint para actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserReadDTO>> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(id, userUpdateDTO);
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ValidationExceptionHandler.handleValidationExceptions(ex);
    }
}
