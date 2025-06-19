package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.RoutineDao;
import com.suzu.vitasync.core.entity.Routine;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineService {

    @Autowired
    private RoutineDao routineDao;

    public List<Routine> getRoutinesByUsuarioId(Integer usuarioId) {
        User user = new User();
        user.setId(usuarioId);
        return routineDao.findByUsuario(user);
    }

    public Routine createRoutine(Routine routine) {
        return routineDao.save(routine);
    }

    public Routine updateRoutine(Integer id, Routine routineDetails) {
        Routine routine = routineDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine not found"));
        routine.setNombreRutina(routineDetails.getNombreRutina());
        routine.setDescripcionRutina(routineDetails.getDescripcionRutina());
        routine.setHoraInicioRutina(routineDetails.getHoraInicioRutina());
        routine.setDuracionRutinaMinutos(routineDetails.getDuracionRutinaMinutos());
        routine.setRepeticion(routineDetails.getRepeticion());
        routine.setActiva(routineDetails.getActiva());
        routine.setUsuario(routineDetails.getUsuario());
        return routineDao.save(routine);
    }

    public void deleteRoutine(Integer id) {
        routineDao.deleteById(id);
    }

    public Routine getRoutineById(Integer id) {
        return routineDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine not found"));
    }
}