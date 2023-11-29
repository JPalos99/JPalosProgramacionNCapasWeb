/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.Controller;

import com.digis01.JPalosProgramacionNCapasWeb.DAO.UsuarioDAOImplementattion;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author digis
 */
@Controller
public class LoginControlller {

    private UsuarioDAOImplementattion UsuarioDAOImplementattion;

    public LoginControlller(UsuarioDAOImplementattion UsuarioDAOImplementattion) {
        this.UsuarioDAOImplementattion = UsuarioDAOImplementattion;
    }

    @GetMapping("/")
    public String Login() {

        return "Login";
    }

    @GetMapping("Login")
    public String entrada(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        return "Login";
    }

    @PostMapping("/Login")
    public String entrada(Model model, @ModelAttribute("usuario") Usuario usuario) {

        Usuario nuevousuario = UsuarioDAOImplementattion.GetByEmail(usuario.getEmail());
        if (usuario.getEmail().equals(nuevousuario.getEmail())) {
            if (usuario.getPassword().equals(nuevousuario.getPassword())) {
                return "redirect:/imobiliaria/listado";
            } else {
                model.addAttribute("Usuarioerror", true);
                return "Login";
            }
        } else {
            model.addAttribute("Usuarioerror", true);
            return "Login";
        }
    }

}
