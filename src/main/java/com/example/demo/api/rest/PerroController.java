package com.example.demo.api.rest;

import java.util.List;

import com.example.demo.api.exceptions.ControllerException;
import com.example.demo.api.mapper.AdminMapper;
import com.example.demo.api.model.Adoptado;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;
import com.example.demo.api.service.PerroService;
import com.example.demo.api.utils.HttpEstado;
import com.example.demo.api.utils.JWTUtil;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/perro")
public class PerroController {
    
    private final PerroService perroService;
    private AdminMapper adminMapper;

    /**
     * Constructor que realiza el setting de los servicios que serán
     * utilizados en este controlador.
     *
     * @param duenoService Servicios de DuenosService
     */
    public PerroController(PerroService perroService, AdminMapper adminMapper){
        this.perroService = perroService;
        this.adminMapper = adminMapper;
    }

    @GetMapping(
        path = "/get-all-adoptados",
        produces = "application/json; charset=utf-8")
    public List<Adoptado> getAllAdoptados( @RequestHeader("jwt") String jwt) throws ControllerException{
        verifica(jwt, "ADMINISTRADOR");
        return perroService.getAdoptados();
    }

    @PostMapping(
        path = "/get-adoptado",
        produces = "application/json; charset=utf-8")
    public List<Adoptado> getOne(@ApiParam(
        name = "id",
        value = "ID del Dueno")
        @RequestBody Dueno dueno, @RequestHeader("jwt") String jwt) throws ControllerException{
            verifica(jwt, "ADMINISTRADOR");
        return perroService.getAdoptado(dueno);
    }

    @GetMapping(
        path = "/get-all",
        produces = "application/json; charset=utf-8")
    public List<Perro> getAll( @RequestHeader("jwt") String jwt) throws ControllerException{
        verifica(jwt, "EMPLEADO");
        return perroService.getAll();
    }

    @PutMapping(
        path = "/upload/{id}",
        produces = "application/json; charset=utf-8",
        consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE })
    public String uploadPerro(
        @RequestParam
        ("file") MultipartFile file,
        @PathVariable int id, @RequestHeader("jwt") String jwt) throws ControllerException{
            verifica(jwt, "EMPLEADO");
        return perroService.uploadPerro(file, 2);
    }

    @PutMapping(
        path = "/insert",
        produces = "application/json; charset=utf-8")
    public String insertPerro(
        @RequestBody Perro perro, @RequestHeader("jwt") String jwt) throws ControllerException{
            verifica(jwt, "EMPLEADO");
        return perroService.insertPerro(perro);
    }

    @DeleteMapping(
        path = "/delete/{id}",
        produces = "application/json; charset=utf-8")
    public String deletePerro(@PathVariable int id,  @RequestHeader("jwt") String jwt) throws ControllerException{
        verifica(jwt, "EMPLEADO");
        return perroService.deletePerro(id);
    }

    private void verifica(String token, String targetRol) throws ControllerException {
        String mail = JWTUtil.getInstance().getCorreoFromJwt(token, "secreto");
        String rolesForToken = adminMapper.getRolesDelCorreo(mail);
        if(!targetRol.equals(rolesForToken)){
            throw new ControllerException("Token invalido. No tienes permisos para ver ese recurso.",
            "contacta a tu administrador", 10000, "contacta a tu administrador", HttpEstado.FORBIDDEN);
        }
        
    }

}