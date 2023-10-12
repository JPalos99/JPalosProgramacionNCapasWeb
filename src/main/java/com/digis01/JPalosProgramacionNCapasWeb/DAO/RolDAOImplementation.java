/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Rol;
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
public class RolDAOImplementation implements IRolDAO{

    private EntityManager entityManager;

    @Autowired
    public RolDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Rol> GetAll() {
        TypedQuery<Rol> query=entityManager.createQuery("FROM Rol",Rol.class);
        List<Rol> roles=query.getResultList();
        return roles;
    }
    
    

}
