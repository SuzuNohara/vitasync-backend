package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.entity.Quehacer;
import com.suzu.vitasync.core.service.QuehacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quehaceres")
public class QuehacerController {

    @Autowired
    private QuehacerService quehacerService;

    @GetMapping("/{id}")
    public ResponseEntity<Quehacer> getQuehacerById(@PathVariable Integer id) {
        return quehacerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Quehacer> getAllQuehaceres() {
        return quehacerService.findAll();
    }

    @PostMapping
    public Quehacer createQuehacer(@RequestBody Quehacer quehacer) {
        return quehacerService.save(quehacer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quehacer> updateQuehacer(@PathVariable Integer id, @RequestBody Quehacer quehacer) {
        if (!quehacerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quehacer.setId(id);
        return ResponseEntity.ok(quehacerService.save(quehacer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuehacer(@PathVariable Integer id) {
        if (!quehacerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quehacerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}