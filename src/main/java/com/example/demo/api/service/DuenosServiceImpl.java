package com.example.demo.api.service;

import java.util.List;

import javax.persistence.PersistenceException;

import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.mapper.DuenoMapper;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;
import com.example.demo.api.utils.HttpEstado;

import org.springframework.stereotype.Service;

/**
 * Servicio asociado a la entidad 'dueno'.
 *
 * <p>Implementación de la interfaz {@link com.example.demo.api.service.DuenoService}.
 *
 * <p>Todos los métodos de esta clase disparan {@link io.kebblar.petstore.api.model.exceptions.ServiceException}
 *
 * @author mentesniker
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see  Dueno
 * @see  DuenosService
 */
@Service("duenoService")
public class DuenosServiceImpl implements DuenosService{
    private final DuenoMapper duenoMapper;
    
    /**
     * Constructor que realiza el setting de todos los Mappers y todos los
     * servicios adicionales a ser empleados en esta clase.
     *
     * @param duenoMapper mapper utilizado para llamar a metodos de persistencia
     */
    public DuenosServiceImpl(DuenoMapper duenoMapper) {
        this.duenoMapper = duenoMapper;
    }

    @Override
    public List<Dueno> getDuenos() throws ServiceException{
        try{
            return duenoMapper.getDuenos();
        }catch(PersistenceException p){
            throw new ServiceException(p, "Error al recuperar los datos de la tabla duenos", p.getMessage() ,
             10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
        
    }

    @Override
    public List<Perro> getPerritos(Dueno dueno) throws ServiceException{
        return duenoMapper.getPerritos(dueno.getId());
    }

    @Override
    public String insertDueno(Dueno dueno) throws ServiceException {
        List<Dueno> duenos = duenoMapper.getDuenos();
        for(Dueno d: duenos){
            if(dueno.getId() == d.getId()){
                throw new ServiceException("Error al insertar el usuario. ID existente", "contacta a tu administrador",
                10001,"10001", HttpEstado.BAD_REQUEST);
            }
        }
        return duenoMapper.insertDueno(dueno) == 1 ? "Exito" : "Fallo";
    }

    @Override
    public String adopta(int idDueno, int idPerro) throws ServiceException {
        return duenoMapper.adopta(idDueno, idPerro)== 1 ? "Exito" : "Fallo";
    }

    @Override
    public String deleteDueno(int id) throws ServiceException {
        return duenoMapper.deleteDueno(id)== 1 ? "Exito" : "Fallo";
    }

    /** {@inheritDoc} */
    @Override
    public Dueno getDueno(int id) throws ServiceException {
        try{
            return duenoMapper.getDueno(id);
        }catch(PersistenceException p){
            throw new ServiceException(p, "Error al recuperar los datos de la tabla duenos", p.getMessage() ,
             10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
    }
}
