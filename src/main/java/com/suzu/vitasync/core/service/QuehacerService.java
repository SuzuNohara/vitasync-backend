package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.QuehacerDao;
import com.suzu.vitasync.core.entity.Quehacer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuehacerService {

    @Autowired
    private QuehacerDao quehacerRepository;

    public Optional<Quehacer> findById(Integer id) {
        return quehacerRepository.findById(id);
    }

    public List<Quehacer> findAll() {
        return quehacerRepository.findAll();
    }

    public Quehacer save(Quehacer quehacer) {
        return quehacerRepository.save(quehacer);
    }

    public void deleteById(Integer id) {
        quehacerRepository.deleteById(id);
    }
}