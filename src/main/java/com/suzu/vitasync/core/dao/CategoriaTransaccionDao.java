package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.CategoriaTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaTransaccionDao extends JpaRepository<CategoriaTransaccion, Integer> {
}
