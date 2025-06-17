package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.CategoriaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaEventoDao extends JpaRepository<CategoriaEvento, Integer> {
    List<CategoriaEvento> findAll();
}