// src/main/java/com/suzu/vitasync/core/service/RoutineService.java
package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.PasosDao;
import com.suzu.vitasync.core.dao.RegistroRutinaDao;
import com.suzu.vitasync.core.dao.RoutineDao;
import com.suzu.vitasync.core.entity.Pasos;
import com.suzu.vitasync.core.entity.RegistroRutina;
import com.suzu.vitasync.core.entity.Routine;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineService {

    @Autowired
    private RoutineDao routineDao;

    @Autowired
    private RegistroRutinaDao registroRutinaDao;

    @Autowired
    private PasosDao pasosDao;

    public List<Routine> getAllRoutines() {
        return routineDao.findAll();
    }

    public Optional<Routine> getRoutineById(Integer id) {
        return routineDao.findById(id);
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

    public List<Routine> getRoutinesByUsuarioId(Integer usuarioId) {
        User user = new User();
        user.setId(usuarioId);
        return routineDao.findByUsuario(user);
    }

    public List<RegistroRutina> getAllRegistroRutina() {
        return registroRutinaDao.findAll();
    }

    public Optional<RegistroRutina> getRegistroRutinaById(Integer id) {
        return registroRutinaDao.findById(id);
    }

    public RegistroRutina createRegistroRutina(RegistroRutina registroRutina) {
        return registroRutinaDao.save(registroRutina);
    }

    public RegistroRutina updateRegistroRutina(Integer id, RegistroRutina registroRutinaDetails) {
        RegistroRutina registroRutina = registroRutinaDao.findById(id)
                .orElseThrow(() -> new RuntimeException("RegistroRutina not found"));
        registroRutina.setRutina(registroRutinaDetails.getRutina());
        registroRutina.setFecha(registroRutinaDetails.getFecha());
        registroRutina.setHoraInicio(registroRutinaDetails.getHoraInicio());
        registroRutina.setPasosCompletados(registroRutinaDetails.getPasosCompletados());
        return registroRutinaDao.save(registroRutina);
    }

    public void deleteRegistroRutina(Integer id) {
        registroRutinaDao.deleteById(id);
    }

    // Custom method: Get RegistroRutina by Routine
    public List<RegistroRutina> getRegistroRutinaByRutina(Routine rutina) {
        return registroRutinaDao.findByRutina(rutina);
    }

    public List<Pasos> getAllPasos() {
        return pasosDao.findAll();
    }

    public Optional<Pasos> getPasosById(Integer id) {
        return pasosDao.findById(id);
    }

    public Pasos createPasos(Pasos pasos) {
        return pasosDao.save(pasos);
    }

    public Pasos updatePasos(Integer id, Pasos pasosDetails) {
        Pasos pasos = pasosDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Pasos not found"));
        pasos.setOrdenPaso(pasosDetails.getOrdenPaso());
        pasos.setNombrePaso(pasosDetails.getNombrePaso());
        pasos.setDescripcionPaso(pasosDetails.getDescripcionPaso());
        pasos.setDuracionPasoMinutos(pasosDetails.getDuracionPasoMinutos());
        pasos.setRutina(pasosDetails.getRutina());
        return pasosDao.save(pasos);
    }

    public void deletePasos(Integer id) {
        pasosDao.deleteById(id);
    }

    // Custom method: Get Pasos by Rutina
    public List<Pasos> getPasosByRutina(Routine rutina) {
        return pasosDao.findByRutina(rutina);
    }

    public List<Pasos> getPasosByRutina(Integer rutinaId) {
        return pasosDao.findByRutinaId(rutinaId);
    }

    public List<RegistroRutina> getRegistroRutinaByUsuario(Integer usuarioId) {
        return registroRutinaDao.findByRutinaUsuarioId(usuarioId);
    }
    
}