package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.entity.Emocion;
import com.suzu.vitasync.core.service.EmocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emociones")
public class EmocionController {

    @Autowired
    private EmocionService emocionService;

    @GetMapping
    public List<Emocion> getAllEmociones() {
        return emocionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emocion> getEmocionById(@PathVariable Integer id) {
        return emocionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{userId}")
    public List<Emocion> getEmocionesByUsuario(@PathVariable Integer userId) {
        return emocionService.findByUsuario(userId);
    }

    @PostMapping
    public Emocion createEmocion(@RequestBody Emocion emocion) {
        return emocionService.save(emocion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmocion(@PathVariable Integer id) {
        if (emocionService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        emocionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}