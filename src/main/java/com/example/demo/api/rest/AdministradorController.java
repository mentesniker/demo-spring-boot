package com.example.demo.api.rest;

import com.example.demo.api.exceptions.ControllerException;
import com.example.demo.api.mapper.AdminMapper;
import com.example.demo.api.model.Administrador;
import com.example.demo.api.model.Login;
import com.example.demo.api.service.LoginService;
import com.example.demo.api.utils.HttpEstado;
import com.example.demo.api.utils.JWTUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/administrador")
public class AdministradorController {
    private final LoginService loginService;
    private AdminMapper adminMapper;

    public AdministradorController(LoginService administradorService, AdminMapper adminMapper){
        this.loginService = administradorService;
        this.adminMapper = adminMapper;
    }

    @ApiOperation(
            value = "AdministradorController::info",
            notes = "Recibe el administrador con el que queremos iniciar sesion.")
    @PostMapping(
        path = "/login",
        produces = "application/json; charset=utf-8")
    public Login login(@ApiParam(
        name = "administrador",
        value = "Representa a un administrador (mail, password, rol)")
        @RequestBody Administrador admin) throws ControllerException{
        return loginService.login(admin); 
    }

    @PostMapping(
        path = "/register",
        produces = "application/json; charset=utf-8")
    public String register(@ApiParam(
        name = "administrador",
        value = "Administrador")
        @RequestBody Administrador admin, @RequestHeader("jwt") String jwt) throws ControllerException{
        verifica(jwt, "ADMINISTRADOR");
        return loginService.insertAdmin(admin); 
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
