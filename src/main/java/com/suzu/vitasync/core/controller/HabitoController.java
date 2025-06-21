package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.entity.Habito;
import com.suzu.vitasync.core.entity.RegistroHabito;
import com.suzu.vitasync.core.entity.User;
import com.suzu.vitasync.core.service.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    @Autowired
    private HabitoService habitoService;

    // --- Habito endpoints ---

    @GetMapping
    public List<Habito> getAllHabitos() {
        return habitoService.getAllHabitos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habito> getHabitoById(@PathVariable Integer id) {
        Optional<Habito> habito = habitoService.getHabitoById(id);
        return habito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Habito> createHabito(@RequestBody Habito habito) {
        Habito created = habitoService.createHabito(habito);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habito> updateHabito(@PathVariable Integer id, @RequestBody Habito habito) {
        try {
            Habito updated = habitoService.updateHabito(id, habito);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabito(@PathVariable Integer id) {
        habitoService.deleteHabito(id);
        return ResponseEntity.noContent().build();
    }

    // Example: get habits by user (adjust User handling as needed)
    @GetMapping("/usuario/{userId}")
    public List<Habito> getHabitosByUsuario(@PathVariable Integer userId) {
        User user = new User();
        user.setId(userId);
        return habitoService.getHabitosByUsuario(user);
    }

    // --- RegistroHabito endpoints ---

    @GetMapping("/registro")
    public List<RegistroHabito> getAllRegistroHabito() {
        return habitoService.getAllRegistroHabito();
    }

    @GetMapping("/registro/{id}")
    public ResponseEntity<RegistroHabito> getRegistroHabitoById(@PathVariable Integer id) {
        Optional<RegistroHabito> registro = habitoService.getRegistroHabitoById(id);
        return registro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public ResponseEntity<RegistroHabito> createRegistroHabito(@RequestBody RegistroHabito registroHabito) {
        RegistroHabito created = habitoService.createRegistroHabito(registroHabito);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/registro/{id}")
    public ResponseEntity<RegistroHabito> updateRegistroHabito(@PathVariable Integer id, @RequestBody RegistroHabito registroHabito) {
        try {
            RegistroHabito updated = habitoService.updateRegistroHabito(id, registroHabito);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/registro/{id}")
    public ResponseEntity<Void> deleteRegistroHabito(@PathVariable Integer id) {
        habitoService.deleteRegistroHabito(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/registro/habito/{habitoId}")
    public List<RegistroHabito> getRegistroHabitoByHabito(@PathVariable Integer habitoId) {
        Habito habito = new Habito();
        habito.setId(habitoId);
        return habitoService.getRegistroHabitoByHabito(habito);
    }
}