package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.dto.RoutineDto;
import com.suzu.vitasync.core.entity.Routine;
import com.suzu.vitasync.core.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @PostMapping
    public ResponseEntity<?> createRoutine(@RequestBody RoutineDto routineDto) {
        Routine routine = new Routine();
        routine.setNombreRutina(routineDto.getNombreRutina());
        routine.setDescripcionRutina(routineDto.getDescripcionRutina());
        routine.setHoraInicioRutina(routineDto.getHoraInicioRutina());
        routine.setDuracionRutinaMinutos(routineDto.getDuracionRutinaMinutos());
        routine.setUsuarioId(routineDto.getUsuarioId());
        Routine createdRoutine = routineService.createRoutine(routine);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listRoutinesByUser(@RequestParam Long usuarioId) {
        List<Routine> routines = routineService.getRoutinesByUsuarioId(usuarioId);
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoutineById(@PathVariable Long id) {
        List<Routine> routines = routineService.getRoutinesByUsuarioId(id);
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoutine(@PathVariable Long id, @RequestBody RoutineDto routineDto) {
        Routine routineDetails = new Routine();
        routineDetails.setNombreRutina(routineDto.getNombreRutina());
        routineDetails.setDescripcionRutina(routineDto.getDescripcionRutina());
        routineDetails.setHoraInicioRutina(routineDto.getHoraInicioRutina());
        routineDetails.setDuracionRutinaMinutos(routineDto.getDuracionRutinaMinutos());
        Routine updatedRoutine = routineService.updateRoutine(id, routineDetails);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}