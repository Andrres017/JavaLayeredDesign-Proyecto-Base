package com.example.mycrud.service.Impl;

import com.example.mycrud.model.User;
import com.example.mycrud.model.dto.UserCreateDTO;
import com.example.mycrud.model.dto.UserReadDTO;
import com.example.mycrud.model.dto.UserUpdateDTO;
import com.example.mycrud.repository.UserRepository;
import com.example.mycrud.service.IUserService;
import com.example.mycrud.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<List<UserReadDTO>>> getUsers(){
        List<UserReadDTO> userDTOs = userRepository.findAll().stream()
                .map(this::convertToReadDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>("Usuarios obtenidos con éxito", HttpStatus.OK, userDTOs));
    }

    @Override
    public ResponseEntity<ApiResponse<UserReadDTO>> getUserById(Integer id) {
        UserReadDTO userDTO = userRepository.findById(id)
                .map(this::convertToReadDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return ResponseEntity.ok(new ApiResponse<>("Usuario encontrado con éxito", HttpStatus.OK, userDTO));
    }

    @Override
    public ResponseEntity<ApiResponse<UserReadDTO>> createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setFullName(userCreateDTO.getFullName());
        user.setIdNumber(userCreateDTO.getIdNumber());
        User savedUser = userRepository.save(user);
        UserReadDTO userReadDTO = convertToReadDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Usuario creado con éxito", HttpStatus.CREATED, userReadDTO));
    }

    @Override
    public ResponseEntity<ApiResponse<UserReadDTO>> updateUser(Integer id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        user.setFullName(userUpdateDTO.getFullName());
        user.setIdNumber(userUpdateDTO.getIdNumber());
        User updatedUser = userRepository.save(user);
        UserReadDTO userReadDTO = convertToReadDTO(updatedUser);
        return ResponseEntity.ok(new ApiResponse<>("Usuario actualizado con éxito", HttpStatus.OK, userReadDTO));
    }


    @Override
    public ResponseEntity<ApiResponse<Void>> deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        userRepository.delete(user);
        return ResponseEntity.ok(new ApiResponse<>("Usuario eliminado con éxito", HttpStatus.OK, null));
    }


    // Métodos auxiliares para convertir entre entidades y DTOs
    private UserReadDTO convertToReadDTO(User user) {
        UserReadDTO dto = new UserReadDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setIdNumber(user.getIdNumber());
        // Otros campos que quieras incluir en el DTO
        return dto;
    }

}
