package com.digis01.JPalosProgramacionNCapasWeb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JPalosProgramacionNCapasWebApplicationTests {

    @RequestMapping("/")
    String hellow() 
    {
        return "Hello World!";
    }
    
	

}


