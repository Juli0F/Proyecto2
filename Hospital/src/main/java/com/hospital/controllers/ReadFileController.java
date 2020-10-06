/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.controllers.fileupload.FIleUpload;
import com.hospital.loadxml.LoadSaxBuilder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author julio
 */
@MultipartConfig(maxFileSize = 16177215)
public class ReadFileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReadFileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReadFileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Part archivo = request.getPart("dataFile");

        String nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();

        System.out.println("Path del Archivo: " + nombreArchivo);
        guardarArchivos(archivo, nombreArchivo);

        LoadSaxBuilder copyDataToDb = new LoadSaxBuilder();
        copyDataToDb.readFile(PATH_FILES + nombreArchivo);
        
        //Guardar todos los Archivos que estan incluidos dentro de la carga del Archivo en la carpeta ArchivosDB
        ArrayList<Part> filePartArchivosDB = (ArrayList<Part>) request.getParts();
        for (Part part : filePartArchivosDB) {
           
            String rutaArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            guardarArchivos(part, rutaArchivo);

        }

        System.out.println("Carga Finalizada");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public final String PATH_FILES = "/home/julio/Documentos/IPC/Hospital/Proyecto2/Hospital/src/main/webapp/archivos/";
}
