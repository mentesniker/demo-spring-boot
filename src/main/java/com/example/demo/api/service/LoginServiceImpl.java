package com.example.demo.api.service;

import com.example.demo.api.exceptions.ControllerException;
import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.model.Administrador;
import com.example.demo.api.model.Login;
import com.example.demo.api.utils.DigestEncoder;
import com.example.demo.api.utils.HttpEstado;
import com.example.demo.api.utils.JWTUtil;

import org.springframework.stereotype.Service;

import com.example.demo.api.mapper.AdminMapper;

@Service
public class LoginServiceImpl implements LoginService{

    private AdminMapper adminMapper;

    public LoginServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Login login(Administrador admin) throws ControllerException {
        Administrador validAdmin = adminMapper.getbyMail(admin.getMail());
        String passwordHasheado = DigestEncoder.digest(admin.getPassword(), admin.getMail());
        if(!passwordHasheado.equals(validAdmin.getPassword())){
            throw new ControllerException( "Error al ingresar con las credenciales proporcionadas", "contacta a tu administrador",
            10000, "contacta a tu administrador", HttpEstado.INTERNAL_SERVER_ERROR);
        }
        Login loginResponse = new Login();
        String encryptKey = "secreto";
        loginResponse.setMail(validAdmin.getMail());
        String jwt = JWTUtil.getInstance().createToken(admin.getMail(), 27, encryptKey);
        loginResponse.setJwt(jwt);
        return loginResponse;
    }

    @Override
    public String insertAdmin(Administrador admin) throws ServiceException {
        String claveHash = DigestEncoder.digest(admin.getPassword(), admin.getMail());
        admin.setPassword(claveHash);
        return adminMapper.insertAdmin(admin)== 1 ? "Exito" : "Fallo";
    }
    
}