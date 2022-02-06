package com.example.demo.api.service;

import com.example.demo.api.exceptions.ControllerException;
import com.example.demo.api.exceptions.ServiceException;
import com.example.demo.api.model.Administrador;
import com.example.demo.api.model.Login;

public interface LoginService {
    public Login login(Administrador admin) throws ControllerException;

    public String insertAdmin(Administrador admin) throws ServiceException;
}
