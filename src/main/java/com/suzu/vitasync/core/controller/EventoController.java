package com.suzu.vitasync.core.controller;

import com.suzu.vitasync.core.dto.EventoDto;
import com.suzu.vitasync.core.entity.CategoriaEvento;
import com.suzu.vitasync.core.entity.Evento;
import com.suzu.vitasync.core.entity.RepeticionEvento;
import com.suzu.vitasync.core.service.EventoService;
import com.suzu.vitasync.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UserService userService;

    @PostMapping
    public EventoDto createEvento(@RequestBody EventoDto eventoDto) {
        Evento evento = mapToEntity(eventoDto);
        Evento saved = eventoService.save(evento);
        return mapToDto(saved);
    }

    @GetMapping("/{id}")
    public Optional<EventoDto> getEvento(@PathVariable Integer id) {
        return eventoService.findById(id).map(this::mapToDto);
    }

    @GetMapping
    public List<EventoDto> getAllEventos() {
        return eventoService.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Integer id) {
        eventoService.deleteById(id);
    }

    private EventoDto mapToDto(Evento evento) {
        EventoDto dto = new EventoDto();
        dto.setId(evento.getId());
        dto.setNombreEvento(evento.getNombreEvento());
        dto.setDescripcionEvento(evento.getDescripcionEvento());
        dto.setUbicacionEvento(evento.getUbicacionEvento());
        dto.setFechaInicioEvento(evento.getFechaInicioEvento());
        dto.setFechaFinEvento(evento.getFechaFinEvento());
        dto.setAllday(evento.getAllday());
        dto.setRepetirId(evento.getRepetir() != null ? evento.getRepetir().getId() : null);
        dto.setCategoriaId(evento.getCategoria() != null ? evento.getCategoria().getId() : null);
        dto.setUsuarioId(evento.getUsuario() != null ? evento.getUsuario().getId() : null);
        return dto;
    }

    private Evento mapToEntity(EventoDto dto) {
        Evento evento = new Evento();
        evento.setId(dto.getId());
        evento.setNombreEvento(dto.getNombreEvento());
        evento.setDescripcionEvento(dto.getDescripcionEvento());
        evento.setUbicacionEvento(dto.getUbicacionEvento());
        evento.setFechaInicioEvento(dto.getFechaInicioEvento());
        evento.setFechaFinEvento(dto.getFechaFinEvento());
        evento.setAllday(dto.getAllday());
        evento.setRepetir(eventoService.getRepeticion(dto.getRepetirId()));
        evento.setCategoria(eventoService.getCategoria(dto.getCategoriaId()));
        evento.setUsuario(userService.getUserById(dto.getUsuarioId()).orElse(null));
        return evento;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Evento> getEventosByUsuario(@PathVariable Integer usuarioId) {
        return eventoService.findByUsuario(usuarioId);
    }


    // CategoriaEvento endpoints
    @PostMapping("/categoria")
    public CategoriaEvento createCategoria(@RequestBody CategoriaEvento categoriaEvento) {
        return eventoService.saveCategoria(categoriaEvento);
    }

    @GetMapping("/categoria")
    public List<CategoriaEvento> getAllCategorias() {
        return eventoService.findAllCategoria();
    }

    @DeleteMapping("/categoria/{id}")
    public void deleteCategoria(@PathVariable Integer id) {
        eventoService.deleteCategoriaEvento(id);
    }

    // RepeticionEvento endpoints
    @PostMapping("/repeticion")
    public RepeticionEvento createRepeticion(@RequestBody RepeticionEvento repeticionEvento) {
        return eventoService.saveRepeticion(repeticionEvento);
    }

    @DeleteMapping("/repeticion/{id}")
    public void deleteRepeticion(@PathVariable Integer id) {
        eventoService.deleteRepeticion(id);
    }
}