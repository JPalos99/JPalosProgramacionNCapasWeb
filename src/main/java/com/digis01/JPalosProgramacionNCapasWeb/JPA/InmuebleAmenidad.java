/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author digis
 */

public class InmuebleAmenidad {
    @ManyToOne  
    @JoinColumn(name = "idinmueble")
    private Inmueble inmueble;
    @ManyToOne
    @JoinColumn(name="idamenidad")
    private Amenidad amenidad;

    public InmuebleAmenidad() {
    }

    public InmuebleAmenidad(Inmueble inmueble, Amenidad amenidad) {
        this.inmueble = inmueble;
        this.amenidad = amenidad;
    }

    public InmuebleAmenidad(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Amenidad getAmenidad() {
        return amenidad;
    }

    public void setAmenidad(Amenidad amenidad) {
        this.amenidad = amenidad;
    }
    
    
    
}
