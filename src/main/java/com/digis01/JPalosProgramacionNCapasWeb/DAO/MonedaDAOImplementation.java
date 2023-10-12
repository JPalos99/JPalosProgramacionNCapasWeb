/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Antiguedad;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Moneda;
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
public class MonedaDAOImplementation implements IMonedaDAO {

    private EntityManager entityManager;

    @Autowired
    public MonedaDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Moneda> GetAll() {
        TypedQuery<Moneda> query = entityManager.createQuery("FROM Moneda", Moneda.class);
        List<Moneda> antiguedades = query.getResultList();
        return antiguedades;
    }
}
