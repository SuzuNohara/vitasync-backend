package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.CompraDao;
import com.suzu.vitasync.core.entity.Compra;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraDao compraDao;

    public List<Compra> getAllCompras() {
        return compraDao.findAll();
    }

    public Optional<Compra> getCompraById(Integer id) {
        return compraDao.findById(id);
    }

    public Compra createCompra(Compra compra) {
        return compraDao.save(compra);
    }

    public Compra updateCompra(Integer id, Compra compra) {
        if (!compraDao.existsById(id)) {
            throw new RuntimeException("Compra not found");
        }
        compra.setId(id);
        return compraDao.save(compra);
    }

    public void deleteCompra(Integer id) {
        compraDao.deleteById(id);
    }

    public List<Compra> getComprasByUsuario(User usuario) {
        return compraDao.findByUsuario(usuario);
    }
}