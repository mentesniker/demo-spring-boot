package com.example.demo.api.mapper;

import java.util.List;

import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface DuenoMapper {

    @Results(id="DuenoMap", value = {
        @Result(property = "id",   column = "id"),
        @Result(property = "nombre",   column = "nombre"),    
    })
    @Select("SELECT * FROM dueno;")
    List<Dueno> getDuenos();

    @Results(id="PerritoMap", value = {
        @Result(property = "id",   column = "id"),
        @Result(property = "nombre",   column = "nombre"),
        @Result(property = "pertenece",   column = "pertenece"),        
    })
    @Select("SELECT * FROM perrito WHERE pertenece = #{id};")
    List<Perro> getPerritos(int id);
}
