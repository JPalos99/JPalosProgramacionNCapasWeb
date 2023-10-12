/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.service;

import com.digis01.JPalosProgramacionNCapasWeb.entity.HolaMundo;

/**
 *
 * @author digis
 */
public class DemoHolaMundo implements IHolaMundo {

    @Override
    public String Mensaje(HolaMundo holamundo) {
        HolaMundo holaMundo=new HolaMundo();
        holaMundo.setHolaMundo(holamundo.getHolaMundo());
        return holaMundo.getHolaMundo();
    }
   
}
