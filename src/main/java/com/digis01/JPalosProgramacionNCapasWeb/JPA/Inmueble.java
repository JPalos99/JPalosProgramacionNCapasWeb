/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Inmueble implements Serializable{
    
   @Id
   @GeneratedValue (strategy= GenerationType.IDENTITY) 
   private int idinmueble;
   private String nombre;
   private String descripcion;
   private float precio;
   private int numerorecamara;
   private int numerobanos;
   private int  numeroestacionamientos;
   private int superficie;
   private float laditud;
   private float longitud;
   
   @OneToOne
   @JoinColumn(name="idantiguedad")
   private Antiguedad  antiguedad;
   @OneToOne
   @JoinColumn(name="idmoneda")
   private Moneda moneda;
   @OneToOne
   @JoinColumn(name="idunidad")
   private Unidad unidad;
   @OneToOne
   @JoinColumn(name="idtipoinmueble")
   private TipoInmueble tipoinmueble;
   
    
   @Lob
   private String imagen;

    public Inmueble() {
    }

    public Inmueble(int idinmueble, String nombre, String descripcion, float precio, int numerorecamara, int numerobanos, int numeroestacionamientos, int superficie, float laditud, float longitud, Antiguedad antiguedad, Moneda moneda, Unidad unidad, TipoInmueble tipoinmueble, String imagen) {
        this.idinmueble = idinmueble;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.numerorecamara = numerorecamara;
        this.numerobanos = numerobanos;
        this.numeroestacionamientos = numeroestacionamientos;
        this.superficie = superficie;
        this.laditud = laditud;
        this.longitud = longitud;
        this.antiguedad = antiguedad;
        this.moneda = moneda;
        this.unidad = unidad;
        this.tipoinmueble = tipoinmueble;
        this.imagen = imagen;
    }

    public TipoInmueble getTipoinmueble() {
        return tipoinmueble;
    }

    public void setTipoinmueble(TipoInmueble tipoinmueble) {
        this.tipoinmueble = tipoinmueble;
    }

   
    
   
    public int getIdinmueble() {
        return idinmueble;
    }

    public void setIdinmueble(int idinmueble) {
        this.idinmueble = idinmueble;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getNumerorecamara() {
        return numerorecamara;
    }

    public void setNumerorecamara(int numerorecamara) {
        this.numerorecamara = numerorecamara;
    }

    public int getNumerobanos() {
        return numerobanos;
    }

    public void setNumerobanos(int numerobanos) {
        this.numerobanos = numerobanos;
    }

    

    public int getNumeroestacionamientos() {
        return numeroestacionamientos;
    }

    public void setNumeroestacionamientos(int numeroestacionamientos) {
        this.numeroestacionamientos = numeroestacionamientos;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public float getLaditud() {
        return laditud;
    }

    public void setLaditud(float laditud) {
        this.laditud = laditud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public Antiguedad getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Antiguedad antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
   
}
