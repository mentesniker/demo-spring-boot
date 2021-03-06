package com.example.demo.api.mapper;

import java.util.List;

import javax.persistence.PersistenceException;

import com.example.demo.api.model.Dueno;
import com.example.demo.api.model.Perro;

import org.apache.ibatis.annotations.Delete;
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
public interface DuenoMapper {

    @Results(id="DuenoMap", value = {
        @Result(property = "id",   column = "id"),
        @Result(property = "nombre",   column = "nombre"),    
    })
    @Select("SELECT * FROM dueno;")
    List<Dueno> getDuenos() throws PersistenceException;

    /**
     * Obtiene un objeto de tipo 'Dueno' dado su id.
     *
     * @return Dueno que tiene asignado el id pasado como parámetro.
     * @throws java.sql.PersistenceException Se dispara en caso de que ocurra un error en esta operación desde la base de datos.
     * @param id un int.
     */
    @ResultMap("DuenoMap")
    @Select("SELECT * FROM dueno WHERE id=#{id};")
    Dueno getDueno(int id) throws PersistenceException;

    @Results(id="PerritoMap", value = {
        @Result(property = "id",   column = "id"),
        @Result(property = "nombre",   column = "nombre"),
        @Result(property = "pertenece",   column = "pertenece"),        
        @Result(property = "url",   column = "imagen"),
    })
    @Select("SELECT * FROM perrito WHERE pertenece = #{id};")
    List<Perro> getPerritos(int id) throws PersistenceException;

    @Insert("INSERT INTO dueno VALUES(#{id}, #{nombre});")
    int insertDueno(Dueno dueno) throws PersistenceException;

    @Update("UPDATE perrito SET pertenece=#{idDueno} WHERE id = #{idPerro};")
    int adopta( int idDueno, int idPerro) throws PersistenceException;

    @Delete("DELETE FROM dueno WHERE id=#{id};")
    int deleteDueno(int id) throws PersistenceException;

}
