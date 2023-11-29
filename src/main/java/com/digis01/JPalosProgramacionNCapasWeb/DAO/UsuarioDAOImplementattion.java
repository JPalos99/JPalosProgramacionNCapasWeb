/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Direccion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
public class UsuarioDAOImplementattion implements IUsuarioDAO {

    private EntityManager entityManager;

    @Autowired //Inyección de dependencias.
    public UsuarioDAOImplementattion(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public int Add(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario.getIdusuario();
    }

    @Override
    public List<Usuario> GetAll(Usuario usuario) {

        String nombre = usuario.getNombre().trim().toLowerCase();
        String apellidoPaterno = usuario.getApellidopaterno().trim().toLowerCase();
        String apellidoMaterno = usuario.getApellidomaterno().trim().toLowerCase();

        TypedQuery<Usuario> query = entityManager.createQuery(
                "FROM Usuario u WHERE TRIM(LOWER(u.nombre)) LIKE :nombreusuario "
                + "AND TRIM(LOWER(u.apellidopaterno)) LIKE :apellidopaterno "
                + "AND TRIM(LOWER(u.apellidomaterno)) LIKE :apellidomaterno", Usuario.class);

        query.setParameter("nombreusuario", '%' + nombre + '%');
        query.setParameter("apellidopaterno", '%' + apellidoPaterno + '%');
        query.setParameter("apellidomaterno", '%' + apellidoMaterno + '%');

        List<Usuario> usuarios = query.getResultList();
        return usuarios;

    }

    @Override
    public List<Usuario> GetAll() {
        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario ", Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

    @Override
    @Transactional
    public void Update(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario GetById(int idusuarioeditable) {
        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE idusuario=: idusuarioeditable", Usuario.class);
        query.setParameter("idusuarioeditable", idusuarioeditable);
        return query.getSingleResult();
    }

    @Override
    public Direccion GetByIdDireccion(int idusuarioeditable) {
        TypedQuery<Direccion> query = entityManager.createQuery("FROM Direccion WHERE usuario.idusuario=: idusuarioeditable", Direccion.class);
        query.setParameter("idusuarioeditable", idusuarioeditable);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void Delete(int idusuarioeliminado) {
        Direccion direccion = GetByIdDireccion(idusuarioeliminado);
        Usuario usuarioEliminar = entityManager.find(Usuario.class, idusuarioeliminado);
        entityManager.remove(direccion);
        entityManager.remove(usuarioEliminar);

    }

    @Override
    @Transactional
    public void ChangeStatus(int idUsuario, boolean status) {
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
        usuario.setStatus((status) ? "Y" : "N");
        entityManager.merge(usuario);
    }

    @Override
    public Usuario GetByEmail(String emailusuario) {
        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE email=: emailusuario", Usuario.class);
        query.setParameter("emailusuario", emailusuario);
        Usuario usuario= new Usuario();
        try{
        usuario = query.getSingleResult();
        }catch(NoResultException e){
        }
        
        return usuario;

    }

}
