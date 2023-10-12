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
public class Unidad {
@Id
@GeneratedValue (strategy= GenerationType.IDENTITY)
private int idunidad ;
private String nombre;

    public Unidad() {
    }

    public Unidad(int idunidad, String nombre) {
        this.idunidad = idunidad;
        this.nombre = nombre;
    }

    public int getIdunidad() {
        return idunidad;
    }

    public void setIdunidad(int idunidad) {
        this.idunidad = idunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    


}
