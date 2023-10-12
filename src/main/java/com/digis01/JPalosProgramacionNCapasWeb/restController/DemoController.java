/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.restController;

import com.digis01.JPalosProgramacionNCapasWeb.entity.HolaMundo;
import com.digis01.JPalosProgramacionNCapasWeb.entity.NumerosOperacion;
import com.digis01.JPalosProgramacionNCapasWeb.entity.Resultado;
import com.digis01.JPalosProgramacionNCapasWeb.service.DemoServiceImplementation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digis01.JPalosProgramacionNCapasWeb.service.DemoHolaMundo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/api")
public class DemoController {
    
    
    
    @PostMapping("/suma")
    public Resultado suma(@RequestBody NumerosOperacion numeros) {
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Suma(numeros);
    }
    
    @PostMapping("/resta")
    public Resultado resta(@RequestBody NumerosOperacion numeros) {
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Resta(numeros);
    }
    @PostMapping("/multiplicacion")
    public Resultado multiplicacion(@RequestBody NumerosOperacion numeros) {
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Multipliacion(numeros);
    }
    @PostMapping("/division")
    public Resultado division(@RequestBody NumerosOperacion numeros) {
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Division(numeros);
    }
    
    @GetMapping("/Mensaje")
    public Map<String,Object> mensaje(@RequestBody HolaMundo  holaMundo){
        Map<String,Object> map=new HashMap<String,Object>();
        DemoHolaMundo demoHolaMundo=new DemoHolaMundo();
        map.put("Holamundo", "hola "+demoHolaMundo.Mensaje(holaMundo));
        return map;
    }
}
