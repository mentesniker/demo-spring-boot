package com.example.demo.api.mapper;

import javax.persistence.PersistenceException;

import com.example.demo.api.model.Administrador;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface AdminMapper {

    @Results(id="AdminMap", value = {
        @Result(property = "mail",   column = "mail"),
        @Result(property = "password",   column = "password"),    
        @Result(property = "rol",   column = "rol"),
    })
    @Select("SELECT * FROM administrador WHERE mail = #{mail};")
    Administrador getbyMail(String mail) throws PersistenceException;

    @Select("SELECT rol FROM administrador WHERE mail = #{mail};")
    String getRolesDelCorreo(String mail) throws PersistenceException;

    @Insert("INSERT INTO administrador VALUES(#{mail},#{password}, #{rol});")
    int insertAdmin(Administrador admin);
}
