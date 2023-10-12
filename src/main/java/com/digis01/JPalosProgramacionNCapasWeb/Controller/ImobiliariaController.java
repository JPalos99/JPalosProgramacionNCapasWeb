/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.Controller;

import Util.Util;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.AntiguedadImplemetationDAO;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.DireccionDAOImplemetation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.InmuebleDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.MonedaDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.PaisDAOImplemetation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.SercivioDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.TipoInmuebleDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.DAO.UnidadDAOImplementattion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Antiguedad;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Direccion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Inmueble;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Moneda;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Pais;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Servicio;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.TipoInmueble;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Unidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/imobiliaria")
public class ImobiliariaController {

    private TipoInmuebleDAOImplementation tipoInmuebleDAOImplementation;
    private InmuebleDAOImplementation inmuebleDAOImplementation;
    private AntiguedadImplemetationDAO antiguedadImplemetationDAO;
    private MonedaDAOImplementation MonedaDAOImplementation;
    private UnidadDAOImplementattion unidadDAOImplementattion;

    @Autowired
    public ImobiliariaController(TipoInmuebleDAOImplementation tipoInmuebleDAOImplementation, InmuebleDAOImplementation inmuebleDAOImplementation, AntiguedadImplemetationDAO antiguedadImplemetationDAO, MonedaDAOImplementation MonedaDAOImplementation, UnidadDAOImplementattion unidadDAOImplementattion) {
        this.tipoInmuebleDAOImplementation = tipoInmuebleDAOImplementation;
        this.inmuebleDAOImplementation = inmuebleDAOImplementation;
        this.antiguedadImplemetationDAO = antiguedadImplemetationDAO;
        this.MonedaDAOImplementation = MonedaDAOImplementation;
        this.unidadDAOImplementattion = unidadDAOImplementattion;
    }

    @GetMapping("/listado")
    private String Lista(Model model) {
        List<Inmueble> inmuebles = inmuebleDAOImplementation.GetAll();
        model.addAttribute("inmuebles", inmuebles);
        return "listadoImobiliaria";
    }

    

    @GetMapping("/form/{idinmueble}")
    public String Form(@PathVariable int idinmueble, Model model) {
        List<Antiguedad> antiguedades = antiguedadImplemetationDAO.GetAll();
        model.addAttribute("antiguedades", antiguedades);
        List<Moneda> monedas=MonedaDAOImplementation.GetAll();
        model.addAttribute("monedas", monedas);
        List<Unidad> unidades=unidadDAOImplementattion.GetAll();
        model.addAttribute("unidades", unidades);
        List<TipoInmueble> tipoinmueble=tipoInmuebleDAOImplementation.GetAll();
        model.addAttribute("tipoinmuebles", tipoinmueble);
        if (idinmueble == 0) {
            model.addAttribute("imuebles", new Inmueble());
            return "FormImobiliaria";
        } else {
            Inmueble inmueble = inmuebleDAOImplementation.GetById(idinmueble);
            model.addAttribute("imuebles", inmueble);
            return "FormImobiliaria";
        }
    }
    
    @PostMapping("form")
    public String Form(@ModelAttribute("inmueble") Inmueble inmueble, 
            @RequestParam("imagenFile") MultipartFile imagenFile,
            Model model) {   
        Util util=new Util();
        util.Codificar(imagenFile,inmueble);
         if(inmueble.getIdinmueble() == 0) {      
            inmuebleDAOImplementation.Add(inmueble);
            return "redirect:/imobiliaria/listado";
        } else {
            inmuebleDAOImplementation.Update(inmueble);
            return "redirect:/imobiliaria/listado";
        }
    }
    
    @GetMapping("/eliminaInmueble/{idinmueble}")
    public String Delete(@PathVariable int idinmueble, Model model) {
        inmuebleDAOImplementation.Delete(idinmueble);
            return "redirect:/imobiliaria/listado";
        }
    
    
    

}
