/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Direccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author digis
 */
@Repository
public class DireccionDAOImplemetation implements IDireccionDAO {
    
    private EntityManager entityManager;

    @Autowired
    public DireccionDAOImplemetation(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    
    @Override
    public List<Direccion> GetAll() {
        TypedQuery<Direccion> query=entityManager.createQuery("FROM Direccion",Direccion.class);
        List<Direccion> direcciones=query.getResultList();
        return direcciones;
    }
    
    @Override
    @Transactional
    public void Add(Direccion direccion) {
        entityManager.persist(direccion);
    }
    
    
    @Override
    public Direccion GetByIdUsuario(int idUsuario){
        TypedQuery<Direccion> query = entityManager.createQuery("FROM Direccion WHERE usuario.idusuario=:idUsuario", Direccion.class);
        query.setParameter("idUsuario", idUsuario);
        Direccion direccion = query.getSingleResult();
        return direccion;
    }
}
