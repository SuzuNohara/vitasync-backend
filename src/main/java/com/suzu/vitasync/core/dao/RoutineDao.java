package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineDao extends JpaRepository<Routine, Long> {
    List<Routine> findByUsuarioId(Long usuarioId);
}