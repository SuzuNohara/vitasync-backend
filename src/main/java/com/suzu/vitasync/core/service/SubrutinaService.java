package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.SubrutinaDao;
import com.suzu.vitasync.core.entity.Subrutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubrutinaService {

    @Autowired
    private SubrutinaDao subrutinaDao;

    public Optional<Subrutina> findById(Integer id) {
        return subrutinaDao.findById(id);
    }

    public List<Subrutina> findAll() {
        return subrutinaDao.findAll();
    }

    public Subrutina save(Subrutina subrutina) {
        return subrutinaDao.save(subrutina);
    }

    public void deleteById(Integer id) {
        subrutinaDao.deleteById(id);
    }

    public List<Subrutina> findRutina(Integer rutinaId) {
        return subrutinaDao.findByRutina(rutinaId);
    }
}