package com.example.demo.api.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.mapper.DuenoMapper;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;
import com.example.demo.api.utils.HttpEstado;

import org.springframework.core.io.ClassPathResource;
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
    private DuenoMapper duenoMapper;
    private final MailSenderService mailSenderService;
    private static final String EXITO = "exito";
    private static final String FALLO = "fallo";
    
    /**
     * Constructor que realiza el setting de todos los Mappers y todos los
     * servicios adicionales a ser empleados en esta clase.
     *
     * @param duenoMapper mapper utilizado para llamar a metodos de persistencia
     */
    public DuenosServiceImpl(DuenoMapper duenoMapper, MailSenderService mailSenderService) {
        this.duenoMapper = duenoMapper;
        this.mailSenderService = mailSenderService;
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
        sendMail("mzl78920@gmail.com", "yuibfdwaes", "foo");
        return duenoMapper.insertDueno(dueno) == 1 ? EXITO : FALLO;
    }

    @Override
    public String adopta(int idDueno, int idPerro) throws ServiceException {
        return duenoMapper.adopta(idDueno, idPerro)== 1 ? EXITO : FALLO;
    }

    @Override
    public String deleteDueno(int id) throws ServiceException {
        return duenoMapper.deleteDueno(id)== 1 ? EXITO : FALLO;
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
    
    private void sendMail(String correo, String randomString, String titulo)  throws ServiceException {
        String body= String.format("<h1>Hola %s. Tu clave de acceso es %s y tiene una validez de %d minutos. (body auxiliar) </h1>", correo, randomString, 10);
        //String body;
        try {
            //body = getTemplate(correo, randomString);
        } catch (Exception e) {
            throw new ServiceException(e, "Error al recuperar los datos de la tabla duenos", e.getMessage() ,
            10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
        this.mailSenderService.sendHtmlMail(correo, titulo, body);
    }

    private String getTemplate(String user, String randStr) throws ServiceException {
        String archivo = "public/mail/templateMail.html";
        try {
            // Accedemos al recurso
            InputStream resource = new ClassPathResource(archivo).getInputStream();
            // Leemos el recurso
            BufferedReader reader = new BufferedReader( new InputStreamReader(resource));
            String template = reader.lines().collect(Collectors.joining("\n"));
            // remplazamos el contenido:
            template = template.replace("%NAME%",user);
            template = template.replace("%TOKEN%",randStr);
            return template;
        } catch (Exception e) {
            throw new ServiceException(e, "Error al recuperar los datos de la tabla duenos", e.getMessage() ,
            10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
    }
}
