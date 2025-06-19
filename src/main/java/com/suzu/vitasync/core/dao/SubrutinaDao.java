package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Subrutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.suzu.vitasync.core.entity.Routine;

import java.util.Optional;
import java.util.List;

@Repository
public interface SubrutinaDao extends JpaRepository<Subrutina, Integer> {

    @Override
    Optional<Subrutina> findById(Integer integer);

    List<Subrutina> findByRoutine(Routine rutinaId);
}
