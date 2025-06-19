package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Evento;
import com.suzu.vitasync.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoDao extends JpaRepository<Evento, Integer> {

    List<Evento> findByUsuario(User usuario);
}