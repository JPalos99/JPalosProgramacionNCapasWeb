/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author digis
 */
@Entity
public class Amenidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idamenidad;
    private String nombre;

    public Amenidad() {
    }

    public Amenidad(int idamenidad, String nombre) {
        this.idamenidad = idamenidad;
        this.nombre = nombre;
    }

    public Amenidad(int idamenidad) {
        this.idamenidad = idamenidad;
    }

    public int getIdamenidad() {
        return idamenidad;
    }

    public void setIdamenidad(int idamenidad) {
        this.idamenidad = idamenidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
}
