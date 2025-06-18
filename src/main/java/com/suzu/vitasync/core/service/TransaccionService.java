package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.CategoriaTransaccionDao;
import com.suzu.vitasync.core.dao.TransaccionDao;
import com.suzu.vitasync.core.entity.CategoriaTransaccion;
import com.suzu.vitasync.core.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionDao transaccionRepository;

    @Autowired
    private CategoriaTransaccionDao categoriaTransaccionRepository;

    public Optional<Transaccion> findTransaccionById(Integer id) {
        return transaccionRepository.findById(id);
    }

    public List<Transaccion> findAllTransacciones() {
        return transaccionRepository.findAll();
    }

    public Transaccion saveTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public void deleteTransaccionById(Integer id) {
        transaccionRepository.deleteById(id);
    }

    // CategoriaTransaccion methods
    public Optional<CategoriaTransaccion> findCategoriaById(Integer id) {
        return categoriaTransaccionRepository.findById(id);
    }

    public List<CategoriaTransaccion> findAllCategorias() {
        return categoriaTransaccionRepository.findAll();
    }

    public CategoriaTransaccion saveCategoria(CategoriaTransaccion categoria) {
        return categoriaTransaccionRepository.save(categoria);
    }

    public void deleteCategoriaById(Integer id) {
        categoriaTransaccionRepository.deleteById(id);
    }
}