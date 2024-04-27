package com.riwi.eventos.controllers;

import com.riwi.eventos.entities.Evento;
import com.riwi.eventos.services.abstract_service.IEventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
@AllArgsConstructor
public class EventoController {
    //Al intectar con final hay que utilizar @AllArgsConstructor
    @Autowired
    private final IEventoService objIEventoService;

    @GetMapping
    //ResponseEntity lo tulizamos para responder con los status http
    public ResponseEntity<List<Evento>> getAll(){
        return ResponseEntity.ok(this.objIEventoService.getAll());
    }

    @GetMapping("/pagina")
    public Page<Evento> getAllPage(Model objModel,
                                                   @RequestParam(defaultValue = "1")int page ,
                                                   @RequestParam(defaultValue = "2") int size){

        Page<Evento> listEventos = this.objIEventoService.findAllPaginate(page -1, size);
        return listEventos;
    }

    //obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> get(@PathVariable String id){
        return ResponseEntity.ok(this.objIEventoService.getById(id));
    }

    //borrar por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.objIEventoService.delete(id);

        //Va a devolver un 204, finalmente lo construye
        return ResponseEntity.noContent().build();
    }

    //Crear evento
    @PostMapping
    public ResponseEntity<Evento> insert(@RequestBody Evento objEvento){

        //Se hace validación para la hora y la capacidad
        if(objEvento.getFecha().isAfter(LocalDate.now()) && (objEvento.getCapacidad() > 0)){
            return ResponseEntity.ok(this.objIEventoService.save(objEvento));
        }else{
            System.out.println("La fecha o la capacidad es invalida");
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(
            @RequestBody Evento objEvento,
            @PathVariable String id
    ){
        //Se hace validación para la hora y la capacidad
        if(objEvento.getFecha().isAfter(LocalDate.now()) && (objEvento.getCapacidad() > 0)){
            objEvento.setId(id);
            return ResponseEntity.ok(this.objIEventoService.save(objEvento));
        }else{
            System.out.println("La fecha o la capacidad es invalida");
            return null;
        }
    }
}
