package com.example.mycrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    // Método para obtener un usuario por su ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Método para crear un nuevo usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Método para actualizar un usuario existente
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFullName(userDetails.getFullName());
            user.setIdNumber(userDetails.getIdNumber());
            userRepository.save(user);
        }
        return user;
    }

    // Método para eliminar un usuario
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
