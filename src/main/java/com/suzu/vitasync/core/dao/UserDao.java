package com.suzu.vitasync.core.dao;

import com.suzu.vitasync.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByCorreoElectronico(String correoElectronico);
    Optional<User> findById(Integer id);
}