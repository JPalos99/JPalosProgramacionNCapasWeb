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

public class InmuebleServicio {
    @ManyToOne  
    @JoinColumn(name = "idinmueble")
    private Inmueble inmueble;
    @ManyToOne  
    @JoinColumn(name = "idservicio")
    private Servicio servicio;

    public InmuebleServicio() {
    }

    public InmuebleServicio(Inmueble inmueble, Servicio servicio) {
        this.inmueble = inmueble;
        this.servicio = servicio;
    }

    public InmuebleServicio(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    
    
    
}
