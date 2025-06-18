package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Quehacer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuehacerDao extends JpaRepository<Quehacer, Integer> {
}