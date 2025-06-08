package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.TaskDao;
import com.suzu.vitasync.core.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> getTasksByUsuarioId(Long usuarioId) {
        return taskDao.findByUsuarioId(usuarioId);
    }

    public Task createTask(Task task) {
        return taskDao.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskDao.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setNombreTarea(taskDetails.getNombreTarea());
        task.setDescripcionTarea(taskDetails.getDescripcionTarea());
        task.setFechaCreacionTarea(taskDetails.getFechaCreacionTarea());
        task.setFechaEntregaTarea(taskDetails.getFechaEntregaTarea());
        task.setFechaInicioTarea(taskDetails.getFechaInicioTarea());
        task.setFechaFinTarea(taskDetails.getFechaFinTarea());
        return taskDao.save(task);
    }

    public void deleteTask(Long id) {
        taskDao.deleteById(id);
    }
}