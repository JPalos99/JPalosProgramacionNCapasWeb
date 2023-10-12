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
public class Antiguedad {
@Id
@GeneratedValue (strategy= GenerationType.IDENTITY)
private int idantiguedad ;
private String nombre;

    public Antiguedad() {
    }

    public Antiguedad(int idantiguedad, String nombre) {
        this.idantiguedad = idantiguedad;
        this.nombre = nombre;
    }

    public int getIdantiguedad() {
        return idantiguedad;
    }

    public void setIdantiguedad(int idantiguedad) {
        this.idantiguedad = idantiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }






}
