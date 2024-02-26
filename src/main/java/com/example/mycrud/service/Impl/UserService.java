package com.example.mycrud.service.Impl;

import com.example.mycrud.model.User;
import com.example.mycrud.model.dto.UserCreateDTO;
import com.example.mycrud.model.dto.UserReadDTO;
import com.example.mycrud.model.dto.UserUpdateDTO;
import com.example.mycrud.repository.UserRepository;
import com.example.mycrud.service.IUserService;
import com.example.mycrud.utils.ApiResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<UserReadDTO>>> getUsers() {
        try {
            List<UserReadDTO> userDTOs = userRepository.findAll().stream()
                    .map(this::convertToReadDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponse<>("Usuarios obtenidos con éxito", HttpStatus.OK, userDTOs));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Error al obtener usuarios", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<UserReadDTO>> getUserById(Integer id) {
        try {
            UserReadDTO userDTO = userRepository.findById(id)
                    .map(this::convertToReadDTO)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
            return ResponseEntity.ok(new ApiResponse<>("Usuario encontrado con éxito", HttpStatus.OK, userDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Error al obtener usuario por ID", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<UserReadDTO>> createUser(UserCreateDTO userCreateDTO) {
        try {
            User user = objectMapper.convertValue(userCreateDTO, User.class);
            User savedUser = userRepository.save(user);
            UserReadDTO userReadDTO = convertToReadDTO(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Usuario creado con éxito", HttpStatus.CREATED, userReadDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<UserReadDTO>> updateUser(Integer id, UserUpdateDTO userUpdateDTO) {
        try {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

            objectMapper.readerForUpdating(user).readValue(objectMapper.writeValueAsString(userUpdateDTO));

            User updatedUser = userRepository.save(user);
            UserReadDTO userReadDTO = convertToReadDTO(updatedUser);
            return ResponseEntity.ok(new ApiResponse<>("Usuario actualizado con éxito", HttpStatus.OK, userReadDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Error al actualizar usuario", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> deleteUser(Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
            userRepository.delete(user);
            return ResponseEntity.ok(new ApiResponse<>("Usuario eliminado con éxito", HttpStatus.OK, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Error al eliminar usuario", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    private UserReadDTO convertToReadDTO(User user) {
        return objectMapper.convertValue(user, UserReadDTO.class);
    }
}
