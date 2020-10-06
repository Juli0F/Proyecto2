/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.entities.Administrador;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Medico;
import com.hospital.entities.Paciente;
import com.hospital.entities.Persona;
import com.hospital.entities.Usuario;
import com.hospital.mysql.Manager;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.Part;
import com.hospital.controllers.fileupload.FIleUpload;
import com.hospital.loadxml.LoadSaxBuilder;

/**
 *
 * @author julio
 */
@MultipartConfig(maxFileSize = 16177215) //Maximo = 16mb
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        manager = new Manager();
{

            String pagJsp = "index.jsp";
            System.out.println("Request: " + request.getParameter("uname"));

            String name = request.getParameter("uname");
            String clave = request.getParameter("psw");

            //verificar si la persona que entro es: Medico, Laboratorista, paciente o adminsitrador
            Usuario usuario = manager.getUsuarioDAO().getUsrByCodigoAndClave(name, clave);
            Paciente paciente = manager.getPacientesDAO().getPacienteByCodeAndPwd(name, clave);
            Medico medico = manager.getMedicoDAO().getMedicoByCodeANdPwd(name, clave);
            Laboratorista laboratorista = manager.getLaboratoristasDAO().getLaboratoristaByCodeANdPwd(name, clave);
            Administrador admin = manager.getAdministradorDAO().getAdminByCodeAndPsw(name, clave);
            
            Persona persona = manager.getPersonaDAO().getPersonaByCodeANdPwd(name, clave);

            if (usuario != null) {

                
                System.out.println("Persona p"+ persona.getNombre());

                request.getSession().setAttribute("personaSession", persona);
                
                
                if (paciente != null) {

                    request.setAttribute("paciente", paciente);
                    request.getSession().setAttribute("pacienteSession", paciente);
                    System.out.println("Login Paciente");
                    pagJsp = "paciente.jsp";
                    
                } else if (medico != null) {
                    
                    request.getSession().setAttribute("medicoSession", medico);
                    request.setAttribute("medico", medico);
                    pagJsp = "Doctor.jsp";
                    
                } else if (laboratorista != null) {
                    
                    request.setAttribute("laboratorista", laboratorista);
                    request.getSession().setAttribute("labSession", laboratorista);
                    pagJsp = "laboratorista-home.jsp";

                } else if (admin != null) {
                    
                    request.setAttribute("admin", admin);
                    System.out.println("Admin" + admin.getCodigo());
                    request.getSession().setAttribute("admin", admin);
                    
                }

               

            } else {

                System.out.println("Mostrar PopOver");
            }

            System.out.println("pagJsp = " + pagJsp);
            RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
            vista.forward(request, response);
        }
    
    }
    // InputStream -> File
    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        System.out.println("Listo Para copiar");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            // commons-io
            //IOUtils.copy(inputStream, outputStream);
        }

    }

    public void fileWrite(File file,String str) {
        try {

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrar(String path, FileOutputStream fos) {
//        String filepath = "C:\\test.txt";
//        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            byte[] buffer = "This will be written in test.txt".getBytes();
            fos.write(buffer, 0, buffer.length);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    
    String subirArchivo(int codigo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    Part filePart = request.getPart("contenido-archivo"); // Obtiene el archivo
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
    
        System.out.println(" ===== fileName"+fileName);

    //InputStream fileContent = filePart.getInputStream(); //Lo transforma en InputStream

    String path="/archivos/";
    File uploads = new File(path); //Carpeta donde se guardan los archivos
    uploads.mkdirs(); //Crea los directorios necesarios
    File file = File.createTempFile("cod"+codigo+"-", "-"+fileName, uploads); //Evita que hayan dos archivos con el mismo nombre

    try (InputStream input = filePart.getInputStream()){
        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    return file.getPath();
}
    
    private Manager manager;
}
