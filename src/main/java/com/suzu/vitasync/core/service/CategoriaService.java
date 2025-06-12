package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.CategoriaDao;
import com.suzu.vitasync.core.entity.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaDao categoriaDao;

    public CategoriaService(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public Categoria save(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    public Optional<Categoria> findById(Integer id) {
        return categoriaDao.findById(id);
    }

    public List<Categoria> findAll() {
        return categoriaDao.findAll();
    }

    public void deleteById(Integer id) {
        categoriaDao.deleteById(id);
    }
}