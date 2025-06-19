package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Routine;
import com.suzu.vitasync.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface RoutineDao extends JpaRepository<Routine, Integer> {
    List<Routine> findByUsuario(User usuario);
    Optional<Routine> findById(Integer id);
}