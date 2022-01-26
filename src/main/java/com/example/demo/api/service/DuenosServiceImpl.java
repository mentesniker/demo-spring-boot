package com.example.demo.api.service;

import java.util.List;

import com.example.demo.api.mapper.DuenoMapper;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

import org.springframework.stereotype.Service;

@Service("duenoService")
public class DuenosServiceImpl implements DuenosService{
    private final DuenoMapper duenoMapper;
    
    public DuenosServiceImpl(DuenoMapper duenoMapper) {
        this.duenoMapper = duenoMapper;
    }

    @Override
    public List<Dueno> getDuenos(){
        return duenoMapper.getDuenos();
    }

    @Override
    public List<Perro> getPerritos(Dueno dueno){
        return duenoMapper.getPerritos(dueno.getId());
    }
}
