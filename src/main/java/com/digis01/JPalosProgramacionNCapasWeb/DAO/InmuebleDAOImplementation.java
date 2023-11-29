/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Inmueble;
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
public class InmuebleDAOImplementation implements IInmuebleDAO {

    private EntityManager entityManager;

    @Autowired //Inyección de dependencias.
    public InmuebleDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Inmueble> GetAll() {

        TypedQuery<Inmueble> query = entityManager.createQuery("FROM Inmueble ", Inmueble.class);
        List<Inmueble> inmuebles = query.getResultList();

        return inmuebles;

    }

    @Override
    public List<Inmueble> GetAll(List<String> lista) {
        int antiguedadID = Integer.parseInt(lista.get(0));
        TypedQuery<Inmueble> query = entityManager.createQuery(
                "SELECT Inmueble.nombre, Inmueble.descripcion " +
                      "FROM Inmueble " +
                      "JOIN Inmueble.idantiguedad a " +
                      "WHERE (:antiguedadID > 0 AND Inmueble.idantiguedad = :antiguedadID) OR (:antiguedadID <= 0 AND Inmueble.idantiguedad != 0)", Inmueble.class);
        query.setParameter("antiguedadID", antiguedadID);
        List<Inmueble> inmuebles = query.getResultList();
        return inmuebles;
    }

    @Override
    public Inmueble GetById(int idinmuebleeditable) {
        TypedQuery<Inmueble> query = entityManager.createQuery("FROM Inmueble WHERE idinmueble=: idinmuebleeditable", Inmueble.class);
        query.setParameter("idinmuebleeditable", idinmuebleeditable);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void Add(Inmueble inmueble) {
        entityManager.persist(inmueble);
    
    }

    @Override
    @Transactional
    public void Update(Inmueble inmueble) {
        entityManager.merge(inmueble);
    }

    @Override
    @Transactional
    public void Delete(int idinmuebleeliminado) {
        Inmueble inmueble = entityManager.find(Inmueble.class, idinmuebleeliminado);
        entityManager.remove(inmueble);
    }
}
