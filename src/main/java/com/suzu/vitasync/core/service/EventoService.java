package com.suzu.vitasync.core.service;

import com.suzu.vitasync.core.dao.EventoDao;
import com.suzu.vitasync.core.dao.RepeticionEventoDao;
import com.suzu.vitasync.core.entity.CategoriaEvento;
import com.suzu.vitasync.core.dao.CategoriaEventoDao;
import com.suzu.vitasync.core.entity.Evento;
import com.suzu.vitasync.core.entity.RepeticionEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suzu.vitasync.core.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoDao eventoDao;

    @Autowired
    private CategoriaEventoDao categoriaDao;

    @Autowired
    private RepeticionEventoDao repeticionDao;

    public Evento save(Evento evento) {
        return eventoDao.save(evento);
    }

    public Optional<Evento> findById(Integer id) {
        return eventoDao.findById(id);
    }

    public List<Evento> findAll() {
        return eventoDao.findAll();
    }

    public List<Evento> findByUsuario(Integer usuario){
        User user = new User();
        user.setId(usuario);
        return eventoDao.findByUsuario(user);
    }

    public void deleteById(Integer id) {
        eventoDao.deleteById(id);
    }

    public CategoriaEvento saveCategoria(CategoriaEvento categoriaEvento) {
        return categoriaDao.save(categoriaEvento);
    }

    public CategoriaEvento getCategoria(Integer id) {
        if (id == null) {
            return null;
        }
        return categoriaDao.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<CategoriaEvento> findAllCategoria() {
        return categoriaDao.findAll();
    }

    public void deleteCategoriaEvento(Integer categoria){
        categoriaDao.deleteById(categoria);
    }

    public RepeticionEvento saveRepeticion(RepeticionEvento repeticion){
        return repeticionDao.save(repeticion);
    }

    public RepeticionEvento getRepeticion(Integer id){
        if (id == null) {
            return null;
        }
        return repeticionDao.findById(id).orElseThrow(() -> new RuntimeException("Repetition not found"));
    }

    public void deleteRepeticion(Integer repeticion){
        repeticionDao.deleteById(repeticion);
    }

}