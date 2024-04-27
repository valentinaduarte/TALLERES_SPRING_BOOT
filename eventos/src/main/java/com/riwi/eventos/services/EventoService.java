package com.riwi.eventos.services;

import com.riwi.eventos.entities.Evento;
import com.riwi.eventos.repositories.EventoRepository;
import com.riwi.eventos.services.abstract_service.IEventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor //constancia de que hay que llenar el constructur de la variable final EventoRepository
public class EventoService implements IEventoService {

    //Inyección de dependencias
    @Autowired
    private final EventoRepository objEventoRepository;
    @Override
    public Evento save(Evento objEvento) {
        return this.objEventoRepository.save(objEvento);
    }

    @Override
    public List<Evento> getAll() {
        return this.objEventoRepository.findAll();
    }

    @Override
    public void delete(String id) {
        //var es una variable genérica en java
        var evento = this.objEventoRepository.findById(id).orElseThrow();
        this.objEventoRepository.delete(evento);
    }

    @Override
    public Evento update(Evento objEvento) {
        return this.objEventoRepository.save(objEvento);

    }

    @Override
    public Evento getById(String id){
        //Si no encuentra nada tira el Throw
        return this.objEventoRepository.findById(id).orElseThrow();
    }
    @Override
    public Page<Evento> findAllPaginate(int page, int size){
        /*Validar que la página no sea menor a 0*/
        if(page < 0){
            page = 0;
        }
        /*Crear la paginación*/
        Pageable objPage = PageRequest.of(page,size);

        return this.objEventoRepository.findAll(objPage);
    }
}
