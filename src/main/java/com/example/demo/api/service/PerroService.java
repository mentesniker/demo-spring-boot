package com.example.demo.api.service;

import java.util.List;

import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.model.Adoptado;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface para el servicio asociado a la entidad 'perro'.
 *
 * @author mentesniker
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 */
public interface PerroService {
    public List<Adoptado> getAdoptados() throws ServiceException;

    public List<Perro> getAll() throws ServiceException;

    public List<Adoptado> getAdoptado(Dueno dueno) throws ServiceException;

    public String uploadPerro(MultipartFile mpf, int id) throws ServiceException;

    public String insertPerro(Perro perro) throws ServiceException;

    public String deletePerro(int id) throws ServiceException;

}