package com.example.demo.api.mapper;

import java.util.List;

import javax.persistence.PersistenceException;

import com.example.demo.api.model.Adoptado;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

/**
 * Interfaz 'Mapper' MyBatis asociado a la entidad Dueno.
 *
 * @author mentesniker
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Dueno
 */
@Mapper
public interface PerroMapper {

    @Results(id="AdoptadoMap", value = {
        @Result(property = "id",   column = "id"),
        @Result(property = "nombre",   column = "nombre"),    
        @Result(property = "pertenece",   column = "pertenece"),
        @Result(property = "url",   column = "imagen"),
    })
    @Select("SELECT * FROM ADOPTADOS;")
    List<Adoptado> getAdoptados() throws PersistenceException;

    /**
     * Obtiene un objeto de perro 'Adoptado' dado su id.
     *
     * @return Perro que tiene asignado el id pasado como parámetro.
     * @throws java.sql.PersistenceException Se dispara en caso de que ocurra un error en esta operación desde la base de datos.
     * @param id un int.
     */
    @ResultMap("AdoptadoMap")
    @Select("SELECT * FROM ADOPTADOS WHERE pertenece=#{nombre};")
    List<Adoptado> getAdoptado(String nombre) throws PersistenceException;

    @Results(id="PerritoMap", value = {
        @Result(property = "id",   column = "id"),
        @Result(property = "nombre",   column = "nombre"),
        @Result(property = "pertenece",   column = "pertenece"),        
        @Result(property = "url",   column = "imagen"),
    })
    @Select("SELECT * FROM perrito WHERE id=#{id};")
    Perro getById(int id) throws PersistenceException;

    @ResultMap("PerritoMap")
    @Select("SELECT * FROM perrito;")
    List<Perro> getAll() throws PersistenceException;

    @Insert("INSERT INTO perrito(id, nombre) VALUES(#{id}, #{nombre});")
    int insertPerro(Perro perro) throws PersistenceException;

    @Update("UPDATE perrito SET imagen=#{url} WHERE id = #{idPerro};")
    int updateImagen(int idPerro, String url) throws PersistenceException;

    @Insert("DELETE FROM perrito WHERE id=#{id};")
    int deletePerro(int id) throws PersistenceException;

}