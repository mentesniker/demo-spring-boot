package com.example.demo.api.rest;

import java.util.List;

import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;
import com.example.demo.api.service.DuenosService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DuenoController {
    
    private final DuenosService duenoService;

    public DuenoController(DuenosService duenoService){
        this.duenoService = duenoService;
    }

    @GetMapping(
        path = "/get-all",
        produces = "application/json; charset=utf-8")
    public List<Dueno> getAll(){
        return duenoService.getDuenos();
    }

    @PostMapping(
        path = "/info",
        produces = "application/json; charset=utf-8")
    public List<Perro> getPerritos(@RequestBody Dueno dueno){
        return duenoService.getPerritos(dueno);
    }

}
