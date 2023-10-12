/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author digis
 */
@Entity
@Table (name="TIPOINMUEBLE")
public class TipoInmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtipoinmueble;
    private String nnombre;

    public TipoInmueble() {
    }

    public TipoInmueble(int idtipoinmueble) {
        this.idtipoinmueble = idtipoinmueble;
    }

    public TipoInmueble(int idtipoinmueble, String nnombre) {
        this.idtipoinmueble = idtipoinmueble;
        this.nnombre = nnombre;
    }

    public int getIdtipoinmueble() {
        return idtipoinmueble;
    }

    public void setIdtipoinmueble(int idtipoinmueble) {
        this.idtipoinmueble = idtipoinmueble;
    }

    public String getNnombre() {
        return nnombre;
    }

    public void setNnombre(String nnombre) {
        this.nnombre = nnombre;
    }

    

}
