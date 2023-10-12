/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *
 * @author digis
 */
@Entity
public class Pais implements Serializable {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int idpais;
 
    private String nombre;
    public Pais() {
    }

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    
    public Pais(int idpais, String nombre) {
        this.idpais = idpais;
        this.nombre = nombre;
    }

    public Pais(int idpais) {
        this.idpais = idpais;
    }

    public int getIdpais() {
        return idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
