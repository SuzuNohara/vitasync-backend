package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDao extends JpaRepository<Categoria, Integer> {
}