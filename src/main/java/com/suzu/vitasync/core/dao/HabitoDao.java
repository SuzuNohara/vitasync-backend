package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.suzu.vitasync.core.entity.User;

@Repository
public interface HabitoDao extends JpaRepository<Habito, Integer> {
    List<Habito> findByUsuario(User usuario);
}