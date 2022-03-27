package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import com.example.demo.api.mapper.DuenoMapper;
import com.example.demo.api.model.Dueno;
import com.example.demo.api.service.DuenosService;
import com.example.demo.api.service.DuenosServiceImpl;
import com.example.demo.api.service.MailSenderService;

import org.mockito.junit.MockitoJUnitRunner;

/** Clase de prueba para el servicio DuenoServiceTest **/
@RunWith(MockitoJUnitRunner.class)
public class DuenoServiceTest {

    private DuenosService duenoService;

    @Before
    public void setUp() {
        System.out.println("\nRunning setUp...");
    }

    @After
    public void tearDown() {
        System.out.println("Running tearDown...\n");
    }

    @Mock
    private DuenoMapper duenoMapper;

    @Mock
    private MailSenderService mailSenderService;
    
    /** Metodo para probar el micrservicio de 
    * obtener a todos los duenos.
    * 
    **/
    @Test
    public void getDuenos() {
        Dueno usuario = new Dueno();
        usuario.setId(1);
        usuario.setNombre("juan");

        this.duenoService = new DuenosServiceImpl(duenoMapper, mailSenderService);
        LinkedList<Dueno> lista = new LinkedList<Dueno>();
        lista.add(usuario);
        
        when(duenoMapper.getDuenos()).thenReturn(lista);

        assertEquals(duenoMapper.getDuenos().get(0),usuario);
    }


    
}
