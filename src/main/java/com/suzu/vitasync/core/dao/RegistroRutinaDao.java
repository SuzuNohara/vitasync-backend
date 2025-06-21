// src/main/java/com/suzu/vitasync/core/dao/RegistroRutinaDao.java
package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.RegistroRutina;
import com.suzu.vitasync.core.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRutinaDao extends JpaRepository<RegistroRutina, Integer> {
    List<RegistroRutina> findByRutina(Routine rutina);
}