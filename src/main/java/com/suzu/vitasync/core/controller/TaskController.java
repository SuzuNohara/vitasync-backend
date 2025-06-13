package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.suzu.vitasync.core.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
// TaskController.java
@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        TaskDto  created = taskService.createTask(taskDto);
        ResponseEntity<?> response;
        response = new ResponseEntity<>(taskDto, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        ResponseEntity<?> response;
        response = new ResponseEntity<>(id, HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    public ResponseEntity<?> listTasksByUser(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(taskService.getTasksByUsuarioId(usuarioId.intValue()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        ResponseEntity<?> response;
        response = new ResponseEntity<>(taskDto, HttpStatus.CREATED);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        ResponseEntity<?> response;
        response = new ResponseEntity<>(id, HttpStatus.CREATED);
        return response;
    }
}