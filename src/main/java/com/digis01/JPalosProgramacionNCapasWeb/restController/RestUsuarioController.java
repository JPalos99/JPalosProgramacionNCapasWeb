/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.restController;

import com.digis01.JPalosProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.DireccionDAOImplemetation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.PaisDAOImplemetation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.RolDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.UsuarioDAOImplementattion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Pais;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Rol;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Usuario;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.UusarioDireccion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/usuarioApi")
public class RestUsuarioController {

    private UsuarioDAOImplementattion usuarioDAOImplementation;
    private DireccionDAOImplemetation direccionDAOImplmentation;
    private RolDAOImplementation rolDAOImplementation;
    private PaisDAOImplemetation paisDAOImplemenntation;
    private EstadoDAOImplementation estadoDAOImplementation;
    private MunicipioDAOImplementation municipioDAOImplementation;
    private ColoniaDAOImplementation coloniaDAOImplementation;

    public RestUsuarioController(UsuarioDAOImplementattion usuarioDAOImplementation, DireccionDAOImplemetation direccionDAOImplmentation, RolDAOImplementation rolDAOImplementation, PaisDAOImplemetation paisDAOImplemenntation, EstadoDAOImplementation estadoDAOImplementation, MunicipioDAOImplementation municipioDAOImplementation, ColoniaDAOImplementation coloniaDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
        this.direccionDAOImplmentation = direccionDAOImplmentation;
        this.rolDAOImplementation = rolDAOImplementation;
        this.paisDAOImplemenntation = paisDAOImplemenntation;
        this.estadoDAOImplementation = estadoDAOImplementation;
        this.municipioDAOImplementation = municipioDAOImplementation;
        this.coloniaDAOImplementation = coloniaDAOImplementation;
    }

    @GetMapping("/GetAll")
    private ResponseEntity<List<Usuario>> listadoUsuarios(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = usuarioDAOImplementation.GetAll(usuario);
        if (usuarios == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }

    @GetMapping("/Add&Update/{idusuario}")
    public ResponseEntity<UusarioDireccion> Form(@PathVariable int idusuario) {
        List<Rol> roles = rolDAOImplementation.GetAll();
        List<Pais> paises = paisDAOImplemenntation.GetAll();
        if (idusuario == 0) {
            return new ResponseEntity<>(new UusarioDireccion(), HttpStatus.OK);

        } else {
            Usuario usuario = usuarioDAOImplementation.GetById(idusuario);
            UusarioDireccion usuarioDireccion = new UusarioDireccion();
            usuarioDireccion.setUsuario(usuarioDAOImplementation.GetById(idusuario));
            usuarioDireccion.setDireccion(direccionDAOImplmentation.GetByIdUsuario(idusuario));
            if (usuarioDireccion == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(usuarioDireccion, HttpStatus.OK);
            }

        }
    }

    @PostMapping("form")
    public ResponseEntity<UusarioDireccion> Form(@RequestBody UusarioDireccion usuariodireccion) {
        if (usuariodireccion.getUsuario().getIdusuario() == 0) {
            int idUsuario = usuarioDAOImplementation.Add(usuariodireccion.getUsuario()); // recuperación de datos.
            usuariodireccion.getDireccion().setUsuario(new Usuario(idUsuario));
            direccionDAOImplmentation.Add(usuariodireccion.getDireccion());
            if (usuariodireccion == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(usuariodireccion, HttpStatus.OK);
            }
        } else {
            usuarioDAOImplementation.Update(usuariodireccion.getUsuario());

            if (usuariodireccion == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(usuariodireccion, HttpStatus.OK);
            }

        }
    }

    @GetMapping("/eliminarusuario/{idusuario}")
    public Map<String, Integer> Delete(@PathVariable int idusuario) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        usuarioDAOImplementation.Delete(idusuario);
        map.put("Se elimino el id", idusuario);
        return map;
    }

}
