package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.ObjetivoAhorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.suzu.vitasync.core.entity.User;
import java.util.List;

@Repository
public interface ObjetivoAhorroDao extends JpaRepository<ObjetivoAhorro, Integer> {
    List<ObjetivoAhorro> findByUsuario(User usuario);
}
