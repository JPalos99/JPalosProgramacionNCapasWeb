/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Antiguedad;
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
public class AntiguedadImplemetationDAO implements IAntiguedadDAO {

    private EntityManager entityManager;

    @Autowired
    public AntiguedadImplemetationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Antiguedad> GetAll() {
        TypedQuery<Antiguedad> query=entityManager.createQuery("FROM Antiguedad",Antiguedad.class);
        List<Antiguedad> antiguedades=query.getResultList();
        return antiguedades;
         }

}
