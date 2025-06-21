package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.EmocionDao;
import com.suzu.vitasync.core.entity.Emocion;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmocionService {

    @Autowired
    private EmocionDao emocionDao;

    public List<Emocion> findAll() {
        return emocionDao.findAll();
    }

    public Optional<Emocion> findById(Integer id) {
        return emocionDao.findById(id);
    }

    public List<Emocion> findByUsuario(Integer userId) {
        User usuario = new User();
        usuario.setId(userId);
        return emocionDao.findByUsuario(usuario);
    }

    public Emocion save(Emocion emocion) {
        return emocionDao.save(emocion);
    }

    public void deleteById(Integer id) {
        emocionDao.deleteById(id);
    }
}