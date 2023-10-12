/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Servicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class SercivioDAOImplementation implements IServicioDAO {

    private EntityManager entityManager;

    @Autowired
    public SercivioDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Servicio> GetAll() {
        TypedQuery<Servicio> query=entityManager.createQuery("From Servicio", Servicio.class);
        List<Servicio> servicios=query.getResultList();
        return servicios;
    }

}
