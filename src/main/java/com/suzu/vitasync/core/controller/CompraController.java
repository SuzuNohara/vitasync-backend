package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.entity.Compra;
import com.suzu.vitasync.core.entity.User;
import com.suzu.vitasync.core.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> getAllCompras() {
        return compraService.getAllCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Integer id) {
        Optional<Compra> compra = compraService.getCompraById(id);
        return compra.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
        Compra created = compraService.createCompra(compra);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Integer id, @RequestBody Compra compra) {
        try {
            Compra updated = compraService.updateCompra(id, compra);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable Integer id) {
        compraService.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{userId}")
    public List<Compra> getComprasByUsuario(@PathVariable Integer userId) {
        User user = new User();
        user.setId(userId);
        return compraService.getComprasByUsuario(user);
    }
}