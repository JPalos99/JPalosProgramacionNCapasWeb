/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JPalosProgramacionNCapasWeb.Controller;

import com.digis01.JPalosProgramacionNCapasWeb.DAO.InmuebleDAOImplementation;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Antiguedad;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Inmueble;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Moneda;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.TipoInmueble;
import com.digis01.JPalosProgramacionNCapasWeb.JPA.Unidad;
import com.digis01.JPalosProgramacionNCapasWeb.entity.ResultExcel;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/cargaMasiva")
public class CargaMasivaController {

    InmuebleDAOImplementation inmuebleDAOImplementation;

    public CargaMasivaController(InmuebleDAOImplementation inmuebleDAOImplementation) {
        this.inmuebleDAOImplementation = inmuebleDAOImplementation;
    }

    @GetMapping("/Carga")
    private String Datos() {
        return "CargaMasiva";
    }

    @PostMapping("/GuardarTXT")
    private String AddDBTxt(Model mode, HttpSession session, HttpServletRequest request) {

        session = request.getSession(false);

        if (session != null) {
            try {
                String path = session.getAttribute("path").toString();
                File file = new File(path);
                
                    FileInputStream input = new FileInputStream(file);
                    MultipartFile archivo = new MockMultipartFile(file.getName(), input);;
                    if (archivo !=null) {
                        System.out.println("Archivo: "+archivo);
                    List<Inmueble> inmuebles = LeerArchivoTXT(archivo);
                    
                    for (Inmueble inmueble : inmuebles) {
                        
                        inmuebleDAOImplementation.Add(inmueble);
                    }
                    mode.addAttribute("guardadoExitosoTXT", true);

                    input.close();
                } else {
                    mode.addAttribute("guardadoFracasoTXT", true);
                }
            } catch (Exception e) {

            }
        }
        return "CargaMasiva";
    }

    @PostMapping("/GuardarExcel")
    private String AddDBExcel(Model model, HttpSession session, HttpServletRequest request) throws FileNotFoundException, IOException {
        session = request.getSession(false);
        if (session != null) {
            String path = session.getAttribute("path").toString();
            File file = new File(path);
            FileInputStream input = new FileInputStream(file);
            MultipartFile archivo = new MockMultipartFile(file.getName(), input);;
            List<Inmueble> inmuebles = LeerArchivo(archivo);
            for (int i = 0; i < inmuebles.size(); i++) {
                inmuebleDAOImplementation.Add(inmuebles.get(i));
            }
            model.addAttribute("guardadoExitoso", true);
            input.close();
        }
        return "CargaMasiva";
    }
    @PostMapping("/Carga")
    private String CargaMasiva(Model model, @RequestParam("fileExcel") MultipartFile archivo, @RequestParam("fileTXT") MultipartFile archivoTXT, HttpSession session) throws IOException {

        if (archivo != null && !archivo.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
            if (extension.equals("xlsx") || extension.equals("xls")) {
                List<Inmueble> inmuebles = LeerArchivo(archivo);
                String absolutePath = System.getProperty("user.dir")
                        + "/src/main/resources/static/archivos/"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                        + archivo.getOriginalFilename();
                archivo.transferTo(new File(absolutePath));
                if (!inmuebles.isEmpty()) {
                    ResultExcel result = validar(inmuebles);
                    if (result.getErrores().size() > 0) {
                        model.addAttribute("errores", result.getErrores());
                        return "CargaMasiva";
                    } else {
                        session.setAttribute("path", absolutePath);
                        model.addAttribute("Validado", 1);
                        model.addAttribute("verificadoExitoso", true);
                        return "CargaMasiva";
                    }
                }
            }
        } else if (archivoTXT != null && !archivoTXT.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivoTXT.getOriginalFilename());
            if (extension.equals("txt")) {
                List<Inmueble> inmuebles = LeerArchivoTXT(archivoTXT);
                String absolutePath = System.getProperty("user.dir")
                        + "/src/main/resources/static/archivos/"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                        + archivoTXT.getOriginalFilename();
                archivo.transferTo(new File(absolutePath));
                if (!inmuebles.isEmpty()) {
                    ResultExcel result = validar(inmuebles);
                    if (result.getErrores().size() > 0) {
                        model.addAttribute("errores", result.getErrores());
                        return "CargaMasiva";
                    } else {
                        session.setAttribute("path", absolutePath);
                        model.addAttribute("ValidadoTXT", 1);
                        model.addAttribute("verificadoExitosoTXT", true);
                        return "CargaMasiva";
                    }
                }
            }
        }
        return "CargaMasiva";
    }

    public ResultExcel validar(List<Inmueble> inmuebles) {
        ResultExcel resultProncipal = new ResultExcel();
        resultProncipal.setErrores(new ArrayList());
        int fila = 2;
        String errorMessage = "";
        for (Inmueble inmueble : inmuebles) {
            errorMessage += (inmueble.getNombre().equals("") || inmueble.getNombre() == null) ? "Falto el Nombre" : "";
            errorMessage += (inmueble.getDescripcion().equals("")) ? "Falta la Descripcion " : "";
            errorMessage += (inmueble.getPrecio() == 0) ? "Falta el Precio " : "";
            errorMessage += (inmueble.getNumerorecamara() == 0) ? "Falta el Numero de Recamaras " : "";
            errorMessage += (inmueble.getNumerobanos() == 0) ? "Falta el Numero de Banos " : "";
            errorMessage += (inmueble.getNumeroestacionamientos() == 0) ? "Falta el Numero de Estacionamientos " : "";
            errorMessage += (inmueble.getSuperficie() == 0) ? "Falta la Superficie " : "";
            errorMessage += (inmueble.getLaditud() == 0) ? "Falta la Laditud " : "";
            errorMessage += (inmueble.getLongitud() == 0) ? "Falta la longitud " : "";
            errorMessage += (inmueble.getAntiguedad().getIdantiguedad() == 0) ? "Falta la Antiguedad " : "";
            errorMessage += (inmueble.getMoneda().getIdmoneda() == 0) ? "Falta la Moneda " : "";
            errorMessage += (inmueble.getUnidad().getIdunidad() == 0) ? "Falta la Unidad " : "";
            errorMessage += (inmueble.getTipoinmueble().getIdtipoinmueble() == 0) ? "Falta el Tipo de Inmueble " : "";

            if (!errorMessage.equals("")) { //Hubo un error
                ResultExcel resultExcel = new ResultExcel();
                resultExcel.setRow(fila);
                resultExcel.setErrorMessage(errorMessage);
                resultProncipal.getErrores().add(resultExcel);
                errorMessage = "";
            }
            fila++;
        }
        return resultProncipal;
    }

    public List<Inmueble> LeerArchivo(MultipartFile archivo) throws IOException {
        List<Inmueble> inmuebles = new ArrayList<Inmueble>();
        XSSFWorkbook workbook = new XSSFWorkbook(archivo.getInputStream());
        Sheet workSheet = workbook.getSheetAt(0);
        boolean primeraFila = true;
        for (Row row : workSheet) {
            if (primeraFila) {
                primeraFila = false;
                continue;
            }
            Inmueble inmueble = new Inmueble();
            inmueble.setNombre((row.getCell(0) == null || row.getCell(0).toString().equals(" ")) ? "" : row.getCell(0).toString());
            inmueble.setDescripcion(row.getCell(1).toString());
            inmueble.setPrecio((int) row.getCell(2).getNumericCellValue());
            inmueble.setNumerorecamara((int) row.getCell(3).getNumericCellValue());
            inmueble.setNumerobanos((int) row.getCell(4).getNumericCellValue());
            inmueble.setNumeroestacionamientos((int) row.getCell(5).getNumericCellValue());
            inmueble.setSuperficie((int) row.getCell(6).getNumericCellValue());
            inmueble.setLaditud((int) row.getCell(7).getNumericCellValue());
            inmueble.setLongitud((int) row.getCell(8).getNumericCellValue());
            Antiguedad antiguedad = new Antiguedad();
            antiguedad.setIdantiguedad((int) row.getCell(9).getNumericCellValue());
            inmueble.setAntiguedad(antiguedad);
            Moneda moneda = new Moneda();
            moneda.setIdmoneda((int) row.getCell(10).getNumericCellValue());
            inmueble.setMoneda(moneda);
            Unidad unidad = new Unidad();
            unidad.setIdunidad((int) row.getCell(11).getNumericCellValue());
            inmueble.setUnidad(unidad);
            TipoInmueble tipoinmueble = new TipoInmueble();
            tipoinmueble.setIdtipoinmueble((int) row.getCell(12).getNumericCellValue());
            inmueble.setTipoinmueble(tipoinmueble);
            inmuebles.add(inmueble);
        }
        workbook.close();
        return inmuebles;
    }

    public List<Inmueble> LeerArchivoTXT(MultipartFile archivoTXT) throws IOException {
        List<Inmueble> inmuebles = new ArrayList<Inmueble>();
        InputStream archivot = archivoTXT.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(archivot));
        String fila = bufferedReader.readLine();
        int rowInserted = 0;
        while ((fila = bufferedReader.readLine()) != null) {
            String[] datos = fila.split("\\|");
            Inmueble inmueble = new Inmueble();
            inmueble.setNombre(datos[0]);
            inmueble.setDescripcion(datos[1]);
            inmueble.setPrecio((float) (((datos[2]).equals("") || Integer.parseInt(datos[2]) == 0) ? 0.0 : Integer.parseInt(datos[2])));
            inmueble.setNumerorecamara(Integer.parseInt(datos[3]));
            inmueble.setNumerobanos(Integer.parseInt(datos[4]));
            inmueble.setNumeroestacionamientos(Integer.parseInt(datos[5]));
            inmueble.setSuperficie(Integer.parseInt(datos[6]));
            inmueble.setLaditud(Integer.parseInt(datos[7]));
            inmueble.setLongitud(Integer.parseInt(datos[8]));
            Antiguedad antiguedad = new Antiguedad();
            antiguedad.setIdantiguedad(Integer.parseInt(datos[9]));
            inmueble.setAntiguedad(antiguedad);
            Moneda moneda = new Moneda();
            moneda.setIdmoneda(Integer.parseInt(datos[10]));
            inmueble.setMoneda(moneda);
            Unidad unidad = new Unidad();
            unidad.setIdunidad(Integer.parseInt(datos[11]));
            inmueble.setUnidad(unidad);
            TipoInmueble tipoInmueble = new TipoInmueble();
            tipoInmueble.setIdtipoinmueble(Integer.parseInt(datos[12]));
            inmueble.setTipoinmueble(tipoInmueble);
            inmuebles.add(inmueble);
        }
        return inmuebles;
    }
}
