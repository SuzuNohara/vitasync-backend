package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Quehacer;
import com.suzu.vitasync.core.entity.RegistroQuehacer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroQuehacerDao extends JpaRepository<RegistroQuehacer, Integer> {
    List<RegistroQuehacer> findByQuehacer(Quehacer quehacer);
}