package com.example.demo.api.service;
import java.util.List;

import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

public interface DuenosService {
    public List<Dueno> getDuenos();

    public List<Perro> getPerritos(Dueno dueno);
}