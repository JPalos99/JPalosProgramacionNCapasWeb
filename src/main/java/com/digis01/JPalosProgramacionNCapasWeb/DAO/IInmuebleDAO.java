/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Inmueble;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IInmuebleDAO {

    List<Inmueble> GetAll();

    void Add(Inmueble inmueble);

    void Update(Inmueble inmueble);

    Inmueble GetById(int idinmuebleeditable);

    void Delete(int idinmuebleeliminado);
}
