package com.example.mycrud.service.Impl;

import com.example.mycrud.repository.UserRepository;
import com.example.mycrud.model.User;
import com.example.mycrud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    // Método para obtener un usuario por su ID
    @Override
    public User getUserById(Integer id) {
        var user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("error");
        }
        return user.get();
    }

    // Método para crear un nuevo usuario
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Método para actualizar un usuario existente
    @Override
    public User updateUser(Integer id, User userDetails) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
           // execpcion
        }

        user.get().setFullName(userDetails.getFullName());
        user.get().setIdNumber(userDetails.getIdNumber());
        return userRepository.save(user.get());
    }

    // Método para eliminar un usuario
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
