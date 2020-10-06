
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.controllers.fileupload.FIleUpload;
import com.hospital.entities.ExamenPaciente;
import com.hospital.entities.Resultado;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;
import javax.servlet.RequestDispatcher;
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
public class ResultadoController extends HttpServlet {

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
            out.println("<title>Servlet ResultadoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResultadoController at " + request.getContextPath() + "</h1>");
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
        Random r = new Random();

        Part part = request.getPart("resultFile");
        String idExPac = request.getParameter("idExPac");
        String fechaHora = request.getParameter("fecha");
        ExamenPaciente exPacObj = manager.getExamenPacienteDAO().obtener(Integer.parseInt(idExPac));
        String id = "";
        String pagJsp = "feed-laboratorista.jsp";

        FIleUpload subir = new FIleUpload();
        subir.upload(request, "resultFile");

        for (int i = 0; i < 9; i++) {

            id += r.nextInt(9);

        }

        String nameFile = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        //(String resultadoCodigo, String informePath, Date fechaHora, boolean estado , Time hora, String ordenPath) {
        Resultado result = new Resultado(id, nameFile, java.sql.Date.valueOf(LocalDate.now()), true, java.sql.Time.valueOf("00:00:00"), idExPac);
        exPacObj.setRealizado(true);

        boolean insert = manager.getResultadoDAO().insert(result);
        System.out.println("Insert : " + insert);
        insert = insert && manager.getExamenPacienteDAO().modify(exPacObj);
        System.out.println("Insert  ::"+ insert);

        request.setAttribute("registro", insert);

        System.out.println("pagJsp = " + pagJsp);
        RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
        vista.forward(request, response);
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

    private Manager manager;
}
