package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.dto.RoutineDto;
import com.suzu.vitasync.core.entity.Routine;
import com.suzu.vitasync.core.entity.User;
import com.suzu.vitasync.core.service.UserService;
import com.suzu.vitasync.core.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutinas")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody RoutineDto routineDto) {
        Routine routine = new Routine();
        routine.setNombreRutina(routineDto.getNombreRutina());
        routine.setDescripcionRutina(routineDto.getDescripcionRutina());
        routine.setHoraInicioRutina(routineDto.getHoraInicioRutina());
        routine.setDuracionRutinaMinutos(routineDto.getDuracionRutinaMinutos());
        routine.setRepeticion(routineDto.getRepeticion());
        routine.setActiva(routineDto.getActiva());
        Optional<User> usuario = userService.getUserById(routineDto.getUsuario());
        routine.setUsuario(usuario.orElse(null));
        Routine createdRoutine = routineService.createRoutine(routine);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Routine>> listRoutinesByUser(@RequestParam Integer usuarioId) {
        List<Routine> routines = routineService.getRoutinesByUsuarioId(usuarioId);
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable Integer id) {
        Routine routine = routineService.getRoutineById(id);
        return new ResponseEntity<>(routine, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Routine> updateRoutine(@PathVariable Integer id, @RequestBody RoutineDto routineDto) {
        Routine routineDetails = new Routine();
        routineDetails.setNombreRutina(routineDto.getNombreRutina());
        routineDetails.setDescripcionRutina(routineDto.getDescripcionRutina());
        routineDetails.setHoraInicioRutina(routineDto.getHoraInicioRutina());
        routineDetails.setDuracionRutinaMinutos(routineDto.getDuracionRutinaMinutos());
        routineDetails.setRepeticion(routineDto.getRepeticion());
        routineDetails.setActiva(routineDto.getActiva());
        Optional<User> usuario = userService.getUserById(routineDto.getUsuario());
        routineDetails.setUsuario(usuario.orElse(null));
        Routine updatedRoutine = routineService.updateRoutine(id, routineDetails);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Integer id) {
        routineService.deleteRoutine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}