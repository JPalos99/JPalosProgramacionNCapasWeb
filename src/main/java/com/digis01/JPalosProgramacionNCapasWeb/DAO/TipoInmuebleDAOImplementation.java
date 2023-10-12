/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.TipoInmueble;
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
public class TipoInmuebleDAOImplementation implements ITipoInmuebleDAO {

    private EntityManager entityManager;

    @Autowired
    public TipoInmuebleDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TipoInmueble> GetAll() {
        TypedQuery<TipoInmueble> query = entityManager.createQuery("FROM TipoInmueble", TipoInmueble.class);
        List<TipoInmueble> tipoInmuebles = query.getResultList();
        return tipoInmuebles;
    }

}
