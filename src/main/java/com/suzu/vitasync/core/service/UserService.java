package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.UserDao;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Optional<User> getUserById(Integer id) {
        return userDao.findById(id);
    }

    public Optional<User> getUserByCorreoElectronico(String correoElectronico) {
        return userDao.findByCorreoElectronico(correoElectronico);
    }

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User updateUser(Integer id, User userDetails) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setNombreUsuario(userDetails.getNombreUsuario());
        user.setApellidoUsuario(userDetails.getApellidoUsuario());
        user.setCorreoElectronico(userDetails.getCorreoElectronico());
        user.setClaveAcceso(userDetails.getClaveAcceso());
        return userDao.save(user);
    }

    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }
}