package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {
    List<Task> findByUsuarioId(Integer usuarioId);
    Optional<Task> findById(Integer id);
}