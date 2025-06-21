package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Compra;
import com.suzu.vitasync.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraDao extends JpaRepository<Compra, Integer> {
    List<Compra> findByUsuario(User usuario);
}