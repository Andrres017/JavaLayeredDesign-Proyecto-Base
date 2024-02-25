package com.example.mycrud.service;

import com.example.mycrud.model.User;
import com.example.mycrud.model.dto.UserCreateDTO;
import com.example.mycrud.model.dto.UserReadDTO;
import com.example.mycrud.model.dto.UserUpdateDTO;
import com.example.mycrud.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IUserService {
    ResponseEntity<ApiResponse<List<UserReadDTO>>> getUsers();
    ResponseEntity<ApiResponse<UserReadDTO>> getUserById(Integer id);
    ResponseEntity<ApiResponse<UserReadDTO>> createUser(UserCreateDTO userCreateDTO);
    ResponseEntity<ApiResponse<UserReadDTO>> updateUser(Integer id, UserUpdateDTO userUpdateDTO);
    ResponseEntity<ApiResponse<Void>> deleteUser(Integer id);
}
