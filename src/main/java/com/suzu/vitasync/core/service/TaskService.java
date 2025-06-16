package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.CategoriaDao;
import com.suzu.vitasync.core.dao.TaskDao;
import com.suzu.vitasync.core.dao.UserDao;
import com.suzu.vitasync.core.entity.Categoria;
import com.suzu.vitasync.core.entity.Task;
import com.suzu.vitasync.core.dto.TaskDto;
import com.suzu.vitasync.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoriaDao categoriaDao;

    public List<TaskDto> getTasksByUsuarioId(Integer usuarioId) {
        return taskDao.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = toEntity(taskDto);
        Task saved = taskDao.save(task);
        return toDto(saved);
    }

    public TaskDto updateTask(Integer id, TaskDto taskDto) {
        Task task = taskDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        updateEntityFromDto(task, taskDto);
        Task updated = taskDao.save(task);
        return toDto(updated);
    }

    public void deleteTask(Integer id) {
        taskDao.deleteById(id);
    }

    private TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setNombreTarea(task.getNombreTarea());
        dto.setDescripcionTarea(task.getDescripcionTarea());
        dto.setFechaCreacionTarea(task.getFechaCreacionTarea());
        dto.setFechaEntregaTarea(task.getFechaEntregaTarea());
        dto.setFechaInicioTarea(task.getFechaInicioTarea());
        dto.setFechaFinTarea(task.getFechaFinTarea());
        dto.setFechaActualizacion(task.getFechaActualizacion());
        dto.setPrioridad(task.getPrioridad());
        dto.setDificultado(task.getDificultado());
        dto.setEstado(task.getEstado());
        dto.setUsuarioId(task.getUsuario() != null ? task.getUsuario().getId() : null);
        dto.setDependencia(task.getDependencia() != null ? task.getDependencia().getId() : null);
        dto.setSubtareaDe(task.getSubtareaDe() != null ? task.getSubtareaDe().getId() : null);
        dto.setCategoria(task.getCategoria() != null ? task.getCategoria().getId() : null);
        return dto;
    }

    private Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setNombreTarea(dto.getNombreTarea());
        task.setDescripcionTarea(dto.getDescripcionTarea());
        task.setFechaCreacionTarea(dto.getFechaCreacionTarea());
        task.setFechaEntregaTarea(dto.getFechaEntregaTarea());
        task.setFechaInicioTarea(dto.getFechaInicioTarea());
        task.setFechaFinTarea(dto.getFechaFinTarea());
        task.setFechaActualizacion(dto.getFechaActualizacion());
        task.setPrioridad(dto.getPrioridad());
        task.setDificultado(dto.getDificultado());
        task.setEstado(dto.getEstado());
        User usuario = userDao.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUsuario(usuario);
        Task dependencia = dto.getDependencia() != null ? taskDao.findById(dto.getDependencia())
                .orElseThrow(() -> new RuntimeException("Dependency task not found")) : null;
        task.setDependencia(dependencia);
        Task subtareaDe = dto.getSubtareaDe() != null ? taskDao.findById(dto.getSubtareaDe())
                .orElseThrow(() -> new RuntimeException("Subtask not found")) : null;
        task.setSubtareaDe(subtareaDe);
        Categoria categoria = categoriaDao.findById(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        task.setCategoria(categoria);
        return task;
    }

    private void updateEntityFromDto(Task task, TaskDto dto) {
        task.setNombreTarea(dto.getNombreTarea());
        task.setDescripcionTarea(dto.getDescripcionTarea());
        task.setFechaCreacionTarea(dto.getFechaCreacionTarea());
        task.setFechaEntregaTarea(dto.getFechaEntregaTarea());
        task.setFechaInicioTarea(dto.getFechaInicioTarea());
        task.setFechaFinTarea(dto.getFechaFinTarea());
        task.setFechaActualizacion(dto.getFechaActualizacion());
        task.setPrioridad(dto.getPrioridad());
        task.setDificultado(dto.getDificultado());
        task.setEstado(dto.getEstado());
        User usuario = userDao.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUsuario(usuario);
        Task dependencia = dto.getDependencia() != null ? taskDao.findById(dto.getDependencia())
                .orElseThrow(() -> new RuntimeException("Dependency task not found")) : null;
        task.setDependencia(dependencia);
        Task subtareaDe = dto.getSubtareaDe() != null ? taskDao.findById(dto.getSubtareaDe())
                .orElseThrow(() -> new RuntimeException("Subtask not found")) : null;
        task.setSubtareaDe(subtareaDe);
        Categoria categoria = categoriaDao.findById(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        task.setCategoria(categoria);
    }

}