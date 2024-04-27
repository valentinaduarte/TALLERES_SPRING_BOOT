

package com.riwi.eventos.services.abstract_service;
import com.riwi.eventos.entities.Evento;
import org.springframework.data.domain.Page;

import java.util.List;

/*Utilizamos una interfaz para ser utilizada como inyección de dependencias en controlador,
* mantiene integridad, desacplamiento y principios SOLID */
public interface IEventoService {

    //Operaciones CRUD
    public Evento save(Evento objEvento);
    public List<Evento> getAll();

    public Page<Evento> findAllPaginate(int page, int size);

    public void delete(String id);
    public Evento update(Evento objEvento);

    public Evento getById(String id);


}
