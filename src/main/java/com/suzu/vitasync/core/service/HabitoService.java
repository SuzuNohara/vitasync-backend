// src/main/java/com/suzu/vitasync/core/service/HabitoService.java
package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.HabitoDao;
import com.suzu.vitasync.core.dao.RegistroHabitoDao;
import com.suzu.vitasync.core.entity.Habito;
import com.suzu.vitasync.core.entity.RegistroHabito;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitoService {

    @Autowired
    private HabitoDao habitoDao;

    @Autowired
    private RegistroHabitoDao registroHabitoDao;

    // --- Habito CRUD ---

    public List<Habito> getAllHabitos() {
        return habitoDao.findAll();
    }

    public Optional<Habito> getHabitoById(Integer id) {
        return habitoDao.findById(id);
    }

    public Habito createHabito(Habito habito) {
        return habitoDao.save(habito);
    }

    public Habito updateHabito(Integer id, Habito habito) {
        if (!habitoDao.existsById(id)) {
            throw new RuntimeException("Habito not found");
        }
        habito.setId(id);
        return habitoDao.save(habito);
    }

    public void deleteHabito(Integer id) {
        habitoDao.deleteById(id);
    }

    public List<Habito> getHabitosByUsuario(User usuario) {
        return habitoDao.findByUsuario(usuario);
    }

    public List<RegistroHabito> getAllRegistroHabito() {
        return registroHabitoDao.findAll();
    }

    public Optional<RegistroHabito> getRegistroHabitoById(Integer id) {
        return registroHabitoDao.findById(id);
    }

    public RegistroHabito createRegistroHabito(RegistroHabito registroHabito) {
        return registroHabitoDao.save(registroHabito);
    }

    public RegistroHabito updateRegistroHabito(Integer id, RegistroHabito registroHabito) {
        if (!registroHabitoDao.existsById(id)) {
            throw new RuntimeException("RegistroHabito not found");
        }
        registroHabito.setId(id);
        return registroHabitoDao.save(registroHabito);
    }

    public void deleteRegistroHabito(Integer id) {
        registroHabitoDao.deleteById(id);
    }

    public List<RegistroHabito> getRegistroHabitoByHabito(Habito habito) {
        return registroHabitoDao.findByHabito(habito);
    }
}