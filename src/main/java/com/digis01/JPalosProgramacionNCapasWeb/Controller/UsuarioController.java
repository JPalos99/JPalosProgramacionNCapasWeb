/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.Controller;

import ML.Result;
import Util.Util;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.DireccionDAOImplemetation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.PaisDAOImplemetation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.RolDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.UsuarioDAOImplementattion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Colonia;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Direccion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Estado;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Municipio;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Pais;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Rol;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Usuario;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.UusarioDireccion;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    int prueba;
    private UsuarioDAOImplementattion usuarioDAOImplementation;
    private DireccionDAOImplemetation direccionDAOImplmentation;
    private RolDAOImplementation rolDAOImplementation;
    private PaisDAOImplemetation paisDAOImplemenntation;
    private EstadoDAOImplementation estadoDAOImplementation;
    private MunicipioDAOImplementation municipioDAOImplementation;
    private ColoniaDAOImplementation coloniaDAOImplementation;
    @Autowired
    public UsuarioController(UsuarioDAOImplementattion usuarioDAOImplementation,
        DireccionDAOImplemetation direccionDAOImplmentation,RolDAOImplementation rolDAOImplementation,
        PaisDAOImplemetation paisDAOImplemenntation, EstadoDAOImplementation estadoDAOImplementation,
        MunicipioDAOImplementation municipioDAOImplementation,ColoniaDAOImplementation coloniaDAOImplementation) {
            this.usuarioDAOImplementation = usuarioDAOImplementation;
            this.direccionDAOImplmentation = direccionDAOImplmentation;
            this.rolDAOImplementation = rolDAOImplementation;
            this.paisDAOImplemenntation = paisDAOImplemenntation;
            this.estadoDAOImplementation=estadoDAOImplementation;
            this.municipioDAOImplementation= municipioDAOImplementation;
            this.coloniaDAOImplementation=coloniaDAOImplementation;
    }

    @GetMapping("/listado")
    private String listadoUsuarios(Model model ) {
        Usuario usuariobusqueda =new Usuario();
        List<Usuario> usuarios = usuarioDAOImplementation.GetAll( ); // recuperación de datos
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariobusqueda", usuariobusqueda);
        List<Direccion> direcciones=direccionDAOImplmentation.GetAll();
        model.addAttribute("direcciones",direcciones);

    return "listadoUsuario";
    }
    
    @PostMapping("/listado")
    private String listadoUsuarios(Model model, @ModelAttribute("usuariobusqueda") Usuario usuariobusqueda ) {
        List<Usuario> usuarios = usuarioDAOImplementation.GetAll( usuariobusqueda); // recuperación de datos
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariobusqueda", usuariobusqueda);
        List<Direccion> direcciones=direccionDAOImplmentation.GetAll();
        model.addAttribute("direcciones",direcciones);
    return "listadoUsuario";
    }

    @GetMapping("/form/{idusuario}")
    public String Form(@PathVariable int idusuario, Model model) {
         List<Rol> roles=rolDAOImplementation.GetAll();
         model.addAttribute("roles", roles);
         List<Pais> paises=paisDAOImplemenntation.GetAll();
         
         model.addAttribute("paises", paises);
        if (idusuario == 0) {
            model.addAttribute("usuariodireccion", new UusarioDireccion());
            return "Form";
        } else {
            Usuario usuario = usuarioDAOImplementation.GetById(idusuario);
            UusarioDireccion usuarioDireccion= new UusarioDireccion();
            usuarioDireccion.setUsuario(usuarioDAOImplementation.GetById(idusuario));
            usuarioDireccion.setDireccion(direccionDAOImplmentation.GetByIdUsuario(idusuario));
            if(usuarioDireccion.getDireccion()!=null){
                
                 model.addAttribute("Colonias", coloniaDAOImplementation.GetByIdMunicipio(usuarioDireccion.getDireccion().getColonia().getMunicipio().getIdmunicipio()));
                 model.addAttribute("Municipios", municipioDAOImplementation.GetByIdEstado(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getIdestado()));   
                 model.addAttribute("Estados", estadoDAOImplementation.GetByIdPais(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getPais().getIdpais()));
                 model.addAttribute("usuariodireccion", usuarioDireccion);
            }
            return "Form";
        }
    }
    
    @PostMapping("form")
    public String Form(@Valid @ModelAttribute("usuariodireccion") UusarioDireccion usuariodireccion, 
            @RequestParam("imagenFile") MultipartFile imagenFile,
            BindingResult bindingResult,Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("usuariodireccion",usuariodireccion);
            return "Form";
        }
        Util util=new Util();
        util.Codificar(imagenFile,usuariodireccion);
        if (usuariodireccion.getUsuario().getIdusuario() == 0) {      
            int idUsuario=usuarioDAOImplementation.Add(usuariodireccion.getUsuario()); // recuperación de datos.
            usuariodireccion.getDireccion().setUsuario(new Usuario(idUsuario));
            direccionDAOImplmentation.Add(usuariodireccion.getDireccion());
            return "redirect:/usuario/listado";
        } else {
            usuarioDAOImplementation.Update(usuariodireccion.getUsuario());
            return "redirect:/usuario/listado";
        }
    }
    
    @GetMapping("/GetEstadoByIdPais")
    @ResponseBody
    public List<Estado> GetEstadoByIdPais(@RequestParam("IdPais")int IdPais) {
        List<Estado> estados = estadoDAOImplementation.GetByIdPais(IdPais); // Obtiene los datos del servicio
        return estados;
    }
    @GetMapping("/GetMunicipioByIdEstado")
    @ResponseBody
    public List<Municipio> GetMunicipioByIdEstado(@RequestParam("IdEstado")int IdEstado) {
        List<Municipio> municipios = municipioDAOImplementation.GetByIdEstado(IdEstado);
        return municipios;
    }
    
    @GetMapping("/GetColoniaByIdMunicipio")
    @ResponseBody
    public List<Colonia> GetColoniaByIdMunicipio(@RequestParam("IdMunicipio")int IdMunicipio) {
        List<Colonia> colonias =coloniaDAOImplementation.GetByIdMunicipio(IdMunicipio);
        return colonias;
    }
    
    @GetMapping("/eliminarusuario/{idusuario}")
    public String Delete(@PathVariable int idusuario, Model model) {
        usuarioDAOImplementation.Delete(idusuario);
            return "redirect:/usuario/listado";
        }
    
    @GetMapping("/ChangeStatus")
    public void ChangeStatus(@RequestParam("idUsuario")int idUsuario,@RequestParam("status")boolean status) {     
        usuarioDAOImplementation.ChangeStatus(idUsuario, status);
    }
}
