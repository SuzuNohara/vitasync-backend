package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.Emocion;
import com.suzu.vitasync.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmocionDao extends JpaRepository<Emocion, Integer> {
    List<Emocion> findByUsuario(User usuario);
}