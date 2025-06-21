package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.RegistroHabito;
import com.suzu.vitasync.core.entity.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroHabitoDao extends JpaRepository<RegistroHabito, Integer> {
    List<RegistroHabito> findByHabito(Habito habito);
}