/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.dto.PersonaLabDto;
import com.hospital.entities.Consulta;
import com.hospital.entities.Examen;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julio
 */
public class AgendaController extends HttpServlet {

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
        manager = new Manager();
        String mostrarJsp = "";
        String accion = request.getParameter("accion");
        
        if (accion.contains("crearCita")) {
            String[] agendar = accion.split("=");
            List<Consulta> consultas = manager.getConsultaDAO().obtenerTodo();
            request.setAttribute("consultas", consultas);
            request.getSession().setAttribute("colegiado", agendar[1]);
            mostrarJsp = "crear-cita.jsp";

        } else if (accion.contains("verLab")) {
            //PersonaLabDto
            String[] agendar = accion.split("=");
            List<PersonaLabDto> examenes = manager.getLaboratoristasDAO().getPersonaLabDtoByCodeExamen(agendar[1]);
            System.out.println("Codigo examen "+agendar[1]);
            Examen examen = manager.getExamenDAO().obtener(agendar[1]);
            request.setAttribute("examen", examen);
            request.setAttribute("laboratoristas", examenes);
         //   request.getSession().setAttribute("colegiado", agendar[1]);
            mostrarJsp = "ver-laboratorista.jsp";
        }
        RequestDispatcher vista = request.getRequestDispatcher(mostrarJsp);
        vista.forward(request, response);
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

        String accion = request.getParameter("accion");
        System.out.println("accion do Post Agenda Controller"+accion);
        accionPost(accion, request);
    }

    public void accionPost(String accion, HttpServletRequest request) {
        switch (accion) {
            case "verMedicos":

                break;
            default:

        }
    }

    public void crearCita(int colegiado) {

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
