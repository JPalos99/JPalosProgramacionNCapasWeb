/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.JPA;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author digis
 */
@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int idusuario; 
    
    @NotEmpty(message = "Ingresa el nombre")
    private String nombre;
    @NotEmpty(message = "Ingresa el apellido paterno")
    private String apellidopaterno;
    @NotEmpty(message = "Ingresa del apellido ,aterno")
    private String apellidomaterno;
    @NotEmpty(message = "Ingresa el username")
    private String username;
    @NotEmpty(message = "Ingresa  el email") 
    private String email;
    @NotEmpty(message = "Ingresa  el password")
    private String password;
    @NotEmpty(message = "Ingresa el sexo")
    private String sexo;
    @NotEmpty(message = "Ingresa el telefono")
    private String telefono;
    @NotEmpty(message = "Ingresa el celular")
    private String celular;
    @NotEmpty(message = "Ingresa el curp")
    private String curp;
    @NotNull(message = "Ingresa la fecha") 
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechanacimiento;
    @Valid
    @ManyToOne
    @JoinColumn(name="idrol")
    private Rol rol;
    @Lob
    private String imagen;
    public Usuario() {
    }
   
    public Usuario(int idUsuario,String userName,String nombre, String apellidoPaterno, String apellidoMaterno,  String email, String password, Date fechaNacimiento,String sexo, String telefono, String celular, String CURP,  Rol rol) {
        this.idusuario = idUsuario;
        this.username = userName;
        this.nombre = nombre;
        this.apellidopaterno = apellidoPaterno;
        this.apellidomaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.fechanacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = CURP;
        this.rol = rol;
    }

    public Usuario(String nombre, String apellidopaterno, String apellidomaterno, String username, String email, String password, String sexo, String telefono, String celular, String curp, Date fechanacimiento, Rol rol) {
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.username = username;
        this.email = email;
        this.password = password;
        this.sexo = sexo;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = curp;
        this.fechanacimiento = fechanacimiento;
        this.rol = rol;
    }

    private String status;
    
    
    public Usuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}

