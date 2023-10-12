/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import com.digis01.JPalosProgramacionNCapasWeb.JPA.Inmueble;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.codec.binary.Base64;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.UusarioDireccion;
/**
 *
 * @author digis
 */
public class Util {
    
    
   public static void Codificar(@RequestParam("imagenFile")MultipartFile imagenFile,UusarioDireccion usuariodireccion){
       try{
            if (!imagenFile.isEmpty()) {
                byte[] bytes = imagenFile.getBytes();
                String imagenBase64 = Base64.encodeBase64String(bytes);
                usuariodireccion.getUsuario().setImagen(imagenBase64);
            } else {
               usuariodireccion.getUsuario().setImagen(null);
            }
           
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public static void Codificar(@RequestParam("imagenFile")MultipartFile imagenFile,Inmueble inmueble){
       try{
            if (!imagenFile.isEmpty()) {
                byte[] bytes = imagenFile.getBytes();
                String imagenBase64 = Base64.encodeBase64String(bytes);
                inmueble.setImagen(imagenBase64);
            } else {
               inmueble.setImagen(null);
            }
           
       }catch(Exception e){
           e.printStackTrace();
       }
   }
    
}
