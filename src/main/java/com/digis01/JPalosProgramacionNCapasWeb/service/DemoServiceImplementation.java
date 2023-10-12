/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.service;

import com.digis01.JPalosProgramacionNCapasWeb.entity.NumerosOperacion;
import com.digis01.JPalosProgramacionNCapasWeb.entity.Resultado;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class DemoServiceImplementation implements IDemoServicio {

    @Override
    public Resultado Suma(NumerosOperacion numeros) {
        Resultado resultado = new Resultado();
        resultado.setResultado((numeros.getNumero1() + numeros.getNumero2()));
        return resultado;
    }

    @Override
    public Resultado Resta(NumerosOperacion numeros) {
        Resultado resultado = new Resultado();
        resultado.setResultado((numeros.getNumero1() - numeros.getNumero2()));
        return resultado;
    }

    @Override
    public Resultado Division(NumerosOperacion numeros) {
        Resultado resultado = new Resultado();
        resultado.setResultado((numeros.getNumero1() / numeros.getNumero2()));
        return resultado;
    }

    @Override
    public Resultado Multipliacion(NumerosOperacion numeros) {
        Resultado resultado = new Resultado();
        resultado.setResultado((numeros.getNumero1() * numeros.getNumero2()));
        return resultado;
    }

    

}
