package com.digis01.JPalosProgramacionNCapasWeb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JPalosProgramacionNCapasWebApplication implements CommandLineRunner {

    private static Logger LOG=LoggerFactory.getLogger(JPalosProgramacionNCapasWebApplication.class);
    
    
    public static void main(String[] args) {
        SpringApplication.run(JPalosProgramacionNCapasWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.warn("peligro"); 
                }

}
