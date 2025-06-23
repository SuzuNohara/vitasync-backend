package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.dto.RoutineDto;
import com.suzu.vitasync.core.dto.SubrutinaDto;
import com.suzu.vitasync.core.entity.*;
import com.suzu.vitasync.core.service.SubrutinaService;
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

    @Autowired
    private SubrutinaService subrutinaService;

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
        Routine routine = routineService.getRoutineById(id).get();
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

    @PostMapping("/{id}/subrutina")
    public ResponseEntity<Void> addSubrutina(@PathVariable Integer id, @RequestBody SubrutinaDto subrutina) {
        Routine routine = routineService.getRoutineById(id).get();
        subrutinaService.save(new Subrutina(subrutina.getId(), routine, subrutina.getName()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/subrutinas")
    public ResponseEntity<List<Subrutina>> getSubrutinasByRoutineId(@PathVariable Integer id) {
        List<Subrutina> subrutinas = subrutinaService.findRutina(id);
        return new ResponseEntity<>(subrutinas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/subrutina/{subrutinaId}")
    public ResponseEntity<Void> deleteSubrutina(@PathVariable Integer id, @RequestParam Integer subrutinaId) {
        Optional<Subrutina> subrutina = subrutinaService.findById(subrutinaId);
        if (subrutina.isPresent()) {
            subrutinaService.deleteById(subrutinaId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/registro-rutina")
    public ResponseEntity<List<RegistroRutina>> getAllRegistroRutina() {
        List<RegistroRutina> registros = routineService.getAllRegistroRutina();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // Get RegistroRutina by ID
    @GetMapping("/registro-rutina/{id}")
    public ResponseEntity<RegistroRutina> getRegistroRutinaById(@PathVariable Integer id) {
        return routineService.getRegistroRutinaById(id)
                .map(registro -> new ResponseEntity<>(registro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get RegistroRutina by Rutina ID
    @GetMapping("/registro-rutina/rutina/{rutinaId}")
    public ResponseEntity<List<RegistroRutina>> getRegistroRutinaByRutina(@PathVariable Integer rutinaId) {
        Routine rutina = new Routine();
        rutina.setId(rutinaId);
        List<RegistroRutina> registros = routineService.getRegistroRutinaByRutina(rutina);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // Create RegistroRutina
    @PostMapping("/registro-rutina")
    public ResponseEntity<RegistroRutina> createRegistroRutina(@RequestBody RegistroRutina registroRutina) {
        RegistroRutina created = routineService.createRegistroRutina(registroRutina);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update RegistroRutina
    @PutMapping("/registro-rutina/{id}")
    public ResponseEntity<RegistroRutina> updateRegistroRutina(@PathVariable Integer id, @RequestBody RegistroRutina registroRutina) {
        try {
            RegistroRutina updated = routineService.updateRegistroRutina(id, registroRutina);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete RegistroRutina
    @DeleteMapping("/registro-rutina/{id}")
    public ResponseEntity<Void> deleteRegistroRutina(@PathVariable Integer id) {
        routineService.deleteRegistroRutina(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pasos")
    public ResponseEntity<List<Pasos>> getAllPasos() {
        List<Pasos> pasos = routineService.getAllPasos();
        return new ResponseEntity<>(pasos, HttpStatus.OK);
    }

    // Get Pasos by ID
    @GetMapping("/pasos/{id}")
    public ResponseEntity<Pasos> getPasosById(@PathVariable Integer id) {
        return routineService.getPasosById(id)
                .map(pasos -> new ResponseEntity<>(pasos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create Pasos
    @PostMapping("/pasos")
    public ResponseEntity<Pasos> createPasos(@RequestBody Pasos pasos) {
        Pasos created = routineService.createPasos(pasos);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update Pasos
    @PutMapping("/pasos/{id}")
    public ResponseEntity<Pasos> updatePasos(@PathVariable Integer id, @RequestBody Pasos pasos) {
        try {
            Pasos updated = routineService.updatePasos(id, pasos);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Pasos
    @DeleteMapping("/pasos/{id}")
    public ResponseEntity<Void> deletePasos(@PathVariable Integer id) {
        routineService.deletePasos(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get Pasos by Routine (Rutina) ID
    @GetMapping("/pasos/rutina/{rutinaId}")
    public ResponseEntity<List<Pasos>> getPasosByRutina(@PathVariable Integer rutinaId) {
        List<Pasos> pasos = routineService.getPasosByRutina(rutinaId);
        return new ResponseEntity<>(pasos, HttpStatus.OK);
    }

    // Get RegistroRutina by Usuario ID
    @GetMapping("/registro-rutina/usuario/{usuarioId}")
    public ResponseEntity<List<RegistroRutina>> getRegistroRutinaByUsuario(@PathVariable Integer usuarioId) {
        List<RegistroRutina> registros = routineService.getRegistroRutinaByUsuario(usuarioId);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

}