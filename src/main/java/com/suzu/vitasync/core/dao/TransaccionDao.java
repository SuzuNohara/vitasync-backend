package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionDao extends JpaRepository<Transaccion, Integer> {
}
