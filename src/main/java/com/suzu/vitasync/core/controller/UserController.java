package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.dto.LoginDto;
import com.suzu.vitasync.core.dto.UserDto;
import com.suzu.vitasync.core.entity.User;
import com.suzu.vitasync.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setNombreUsuario(userDto.getNombreUsuario());
        user.setApellidoUsuario(userDto.getApellidoUsuario());
        user.setCorreoElectronico(userDto.getCorreoElectronico());
        user.setClaveAcceso(userDto.getClaveAcceso());
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        Optional<User> user = userService.getUserByCorreoElectronico(loginDto.getCorreoElectronico());
        if (user.isPresent() && user.get().getClaveAcceso().equals(loginDto.getClaveAcceso())) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody UserDto userDto) {
        User userDetails = new User();
        userDetails.setNombreUsuario(userDto.getNombreUsuario());
        userDetails.setApellidoUsuario(userDto.getApellidoUsuario());
        userDetails.setCorreoElectronico(userDto.getCorreoElectronico());
        userDetails.setClaveAcceso(userDto.getClaveAcceso());
        User updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}