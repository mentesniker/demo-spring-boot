package com.example.demo.api.service;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.mapper.PerroMapper;
import com.example.demo.api.model.Adoptado;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;
import com.example.demo.api.utils.HttpEstado;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("perroService")
public class PerroServiceImpl implements PerroService{
    private final PerroMapper perroMapper;

    public PerroServiceImpl(PerroMapper perroMapper) {
        this.perroMapper = perroMapper;
    }

    @Override
    public List<Adoptado> getAdoptados() throws ServiceException {
        return perroMapper.getAdoptados();
    }

    @Override
    public List<Perro> getAll() throws ServiceException {
        return perroMapper.getAll();
    }

    @Override
    public List<Adoptado> getAdoptado(Dueno dueno) throws ServiceException {
        return perroMapper.getAdoptado(dueno.getNombre());
    }

    @Override
    public String uploadPerro(MultipartFile mpf, int id) throws ServiceException {
        try {
            Perro perro = perroMapper.getById(id);
            String newName = getMd5(mpf) + "-" + mpf.getOriginalFilename();
            Path filepath = Paths.get("./uploads", newName);
            mpf.transferTo(filepath);
            return perroMapper.updateImagen(perro.getId(), "uploads/"+newName)== 1 ? "Exito" : "Fallo";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e, "Error al insertar el perro", e.getMessage() ,
             10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String deletePerro(int id) throws ServiceException {
        return perroMapper.deletePerro(id)== 1 ? "Exito" : "Fallo";
    }

    @Override
    public String insertPerro(Perro perro) throws ServiceException {
        return perroMapper.insertPerro(perro)== 1 ? "Exito" : "Fallo";
    }

    /**
     * Gets the md 5.
     *
     * @param mpf the mpf
     * @return the md 5
     * @throws ServiceException the upload exception
     */
    private static String getMd5(MultipartFile mpf) throws ServiceException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(mpf.getBytes());
            BigInteger container = new BigInteger(1, messageDigest);
            String hashtext = container.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0".concat(hashtext);
            }
            return hashtext;
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new ServiceException(e, "Error al recuperar los datos de la tabla perros", e.getMessage() ,
             10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
    }
    
}
