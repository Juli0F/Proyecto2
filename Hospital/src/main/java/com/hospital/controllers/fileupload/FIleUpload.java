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

    public final String PATH_FILES = "/home/julio/Documentos/IPC/Hospital/Proyecto2/Hospital/src/main/webapp/archivos/";

    public void upload(HttpServletRequest request) throws IOException, ServletException {
        
        Part archivo = request.getPart("dataFile");
        
        String nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
        
        System.out.println("Path del Archivo: " + nombreArchivo);
        guardarArchivos(archivo, nombreArchivo);
        
        

        LoadSaxBuilder copyDataToDb = new LoadSaxBuilder();
        copyDataToDb.readFile(PATH_FILES+nombreArchivo);
        //Guardar todos los Archivos que estan incluidos dentro de la carga del Archivo en la carpeta ArchivosDB
        ArrayList<Part> filePartArchivosDB = (ArrayList<Part>) request.getParts();
        for (Part part : filePartArchivosDB) {
            String rutaArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            guardarArchivos(part, rutaArchivo);

        }
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
