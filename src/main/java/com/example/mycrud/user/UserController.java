package com.example.mycrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Endpoint para actualizar un usuario
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
