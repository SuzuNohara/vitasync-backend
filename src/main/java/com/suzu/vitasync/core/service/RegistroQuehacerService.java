package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.RegistroQuehacerDao;
import com.suzu.vitasync.core.entity.Quehacer;
import com.suzu.vitasync.core.entity.RegistroQuehacer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroQuehacerService {

    @Autowired
    private RegistroQuehacerDao registroQuehacerDao;

    public List<RegistroQuehacer> findAll() {
        return registroQuehacerDao.findAll();
    }

    public Optional<RegistroQuehacer> findById(Integer id) {
        return registroQuehacerDao.findById(id);
    }

    public List<RegistroQuehacer> findByQuehacer(Quehacer quehacer) {
        return registroQuehacerDao.findByQuehacer(quehacer);
    }

    public RegistroQuehacer save(RegistroQuehacer registroQuehacer) {
        return registroQuehacerDao.save(registroQuehacer);
    }

    public void deleteById(Integer id) {
        registroQuehacerDao.deleteById(id);
    }
}