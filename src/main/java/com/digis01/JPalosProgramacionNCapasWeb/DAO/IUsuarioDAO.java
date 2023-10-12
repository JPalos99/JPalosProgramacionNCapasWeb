/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.DAO;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Direccion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Usuario;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IUsuarioDAO {
  
   List<Usuario>  GetAll(Usuario usuario);
   List<Usuario>  GetAll();
   int Add(Usuario usuario);
   void Update(Usuario usuario);
   Usuario GetById(int idusuarioeditable); 
   void Delete(int idusuarioeliminado);
   void ChangeStatus(int idUsuario,boolean status);
   Direccion GetByIdDireccion(int idusuarioeliminado);
}
