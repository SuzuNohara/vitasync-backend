package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.entity.CategoriaTransaccion;
import com.suzu.vitasync.core.entity.Transaccion;
import com.suzu.vitasync.core.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactionss")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    // Transaccion endpoints

    @GetMapping("/transacciones/{id}")
    public ResponseEntity<Transaccion> getTransaccionById(@PathVariable Integer id) {
        return transaccionService.findTransaccionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/transacciones")
    public List<Transaccion> getAllTransacciones() {
        return transaccionService.findAllTransacciones();
    }

    @PostMapping("/transacciones")
    public Transaccion createTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.saveTransaccion(transaccion);
    }

    @PutMapping("/transacciones/{id}")
    public ResponseEntity<Transaccion> updateTransaccion(@PathVariable Integer id, @RequestBody Transaccion transaccion) {
        if (!transaccionService.findTransaccionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transaccion.setId(id);
        return ResponseEntity.ok(transaccionService.saveTransaccion(transaccion));
    }

    @DeleteMapping("/transacciones/{id}")
    public ResponseEntity<Void> deleteTransaccion(@PathVariable Integer id) {
        if (!transaccionService.findTransaccionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transaccionService.deleteTransaccionById(id);
        return ResponseEntity.noContent().build();
    }

    // CategoriaTransaccion endpoints

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaTransaccion> getCategoriaById(@PathVariable Integer id) {
        return transaccionService.findCategoriaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categorias")
    public List<CategoriaTransaccion> getAllCategorias() {
        return transaccionService.findAllCategorias();
    }

    @PostMapping("/categorias")
    public CategoriaTransaccion createCategoria(@RequestBody CategoriaTransaccion categoria) {
        return transaccionService.saveCategoria(categoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaTransaccion> updateCategoria(@PathVariable Integer id, @RequestBody CategoriaTransaccion categoria) {
        if (!transaccionService.findCategoriaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        return ResponseEntity.ok(transaccionService.saveCategoria(categoria));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        if (!transaccionService.findCategoriaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transaccionService.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }
}