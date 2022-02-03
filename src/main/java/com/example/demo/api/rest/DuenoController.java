package com.example.demo.api.rest;

import java.util.List;

import com.example.demo.api.exceptions.ControllerException;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;
import com.example.demo.api.service.DuenosService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
/**
 * Implementacion  del controlador REST asociado a los endpoints
 * de gestión del DuenoController.
 *
 * <p>Todos los métodos de esta clase disparan {@link ControllerException}</p>
 *
 * <p>NOTA IMPORTANTE: Los  distintos métodos de este controlador no
 * llevan  javadoc  debido a que la  documentación  Swagger  API
 * cumple con ese objetivo.</p>
 *
 * @author  mentesniker
 * @see     com.example.demo.api.service
 * @see     com.example.demo.api.model
 * @version 1.0-SNAPSHOT
 * @since   1.0-SNAPSHOT
 */
@RestController
@RequestMapping(value = "/dueno")
public class DuenoController {
    
    private final DuenosService duenoService;

    /**
     * Constructor que realiza el setting de los servicios que serán
     * utilizados en este controlador.
     *
     * @param duenoService Servicios de DuenosService
     */
    public DuenoController(DuenosService duenoService){
        this.duenoService = duenoService;
    }

    @GetMapping(
        path = "/get-all",
        produces = "application/json; charset=utf-8")
    public List<Dueno> getAll() throws ControllerException{
        return duenoService.getDuenos();
    }

    @GetMapping(
        path = "/get/{id}",
        produces = "application/json; charset=utf-8")
    public Dueno getOne(@ApiParam(
        name = "id",
        value = "ID del Dueno",
        defaultValue = "1")
        @PathVariable int id) throws ControllerException{
        return duenoService.getDueno(id);
    }

    @ApiOperation(
            value = "DuenoController::info",
            notes = "Recibe el dueno del que queremos saber los datos de sus perros")
    @PostMapping(
        path = "/info",
        produces = "application/json; charset=utf-8")
    public List<Perro> getPerritos(
        @ApiParam(name="cred", value="Representa a un dueno (id y nombre) ")
        @RequestBody Dueno dueno) throws ControllerException{
        return duenoService.getPerritos(dueno);
    }

    @PutMapping(
        path = "/adopta/{idDueno}/{idPerro}",
        produces = "application/json; charset=utf-8")
    public String adopta(@PathVariable int idDueno, @PathVariable int idPerro) throws ControllerException{
        return duenoService.adopta(idDueno, idPerro);
    }

    @PutMapping(
        path = "/insert",
        produces = "application/json; charset=utf-8")
    public String insertDueno(@RequestBody Dueno dueno) throws ControllerException{
        return duenoService.insertDueno(dueno);
    }

    @DeleteMapping(
        path = "/delete",
        produces = "application/json; charset=utf-8")
    public String deleteDueno(@RequestBody int id) throws ControllerException{
        return duenoService.deleteDueno(id);
    }

}
