// src/main/java/com/suzu/vitasync/core/dao/RegistroRutinaDao.java
package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.RegistroRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import com.suzu.vitasync.core.entity.Routine;

import java.util.List;

public interface RegistroRutinaDao extends JpaRepository<RegistroRutina, Integer> {
    List<RegistroRutina> findByRutina(Routine rutina);
    List<RegistroRutina> findByRutinaUsuarioId(Integer usuarioId);

}