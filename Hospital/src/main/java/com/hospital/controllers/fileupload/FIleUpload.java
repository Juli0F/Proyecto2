/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers.fileupload;

import com.hospital.loadxml.LoadSaxBuilder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author julio
 */
public class FIleUpload {

    public static final String PATH_FILES = "/home/julio/Documentos/IPC/Hospital/Proyecto2/Hospital/src/main/webapp/archivos/";

    public void upload(HttpServletRequest request,String nameTag) throws IOException, ServletException {
        
        Part part = request.getPart(nameTag);
        
        String nombreArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        
        System.out.println("Path del Archivo: " + nombreArchivo);
        guardarArchivos(part, nombreArchivo);
        
 
    }

    private void guardarArchivos(Part filePart, String fileName) {
        File rutaDestino = new File(PATH_FILES);
        File file = new File(rutaDestino, fileName);

        try (InputStream inputS = filePart.getInputStream()) {
            Files.copy(inputS, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
