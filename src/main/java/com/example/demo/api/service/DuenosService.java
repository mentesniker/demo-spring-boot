package com.example.demo.api.service;
import java.util.List;

import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

/**
 * Interface para el servicio asociado a la entidad 'dueno'.
 *
 * @author mentesniker
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 */
public interface DuenosService {
    public List<Dueno> getDuenos() throws ServiceException;

    public List<Perro> getPerritos(Dueno dueno) throws ServiceException;

    public String insertDueno(Dueno dueno) throws ServiceException;

    public String deleteDueno(int id) throws ServiceException;

    public String adopta(int idDueno, int idPerro) throws ServiceException;

    /**
     * Método utilizado para recuperar todos los elemento de la tabla 'dueno' por medio de su llave primaria.
     *
     * @param id Id del objeto buscado.
     * @return La información del elemento recuperado en una instacia de la clase Dueno
     * o nulo si no se encuentra ese elemento en la tabla.
     * @throws ServiceException cuando hay un error en la recuperación del dueno.
     */
    public Dueno getDueno(int id) throws ServiceException;


}