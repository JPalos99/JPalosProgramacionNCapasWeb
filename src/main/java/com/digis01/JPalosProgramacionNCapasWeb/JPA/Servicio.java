/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author digis
 */
@Entity
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idservicio;
    private String nombre;

    public Servicio() {
    }

    public Servicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public Servicio(int idservicio, String nombre) {
        this.idservicio = idservicio;
        this.nombre = nombre;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
}
