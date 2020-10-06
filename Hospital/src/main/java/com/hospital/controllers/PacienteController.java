/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.connection.Conexion;
import com.hospital.dto.MedicoDto;
import com.hospital.entities.Examen;
import com.hospital.entities.Paciente;
import com.hospital.entities.Persona;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julio
 */
public class PacienteController extends HttpServlet {

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
        String pagJsp = "";

        String accion = request.getParameter("accion");
        switch (accion) {
            case "cita":
                pagJsp = citaMedica(request);
                break;
            case "citaLAB":
                pagJsp = citaLab(request);
                break;
            default:
        }

        RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
        vista.forward(request, response);
    }

    public String citaLab(HttpServletRequest request) {
        //medicos
        List<Examen> examenes = manager.getExamenDAO().obtenerTodo();

        request.setAttribute("examenes", examenes);
        return "cita-laboratorista.jsp";

    }

    public String citaMedica(HttpServletRequest request) {
        //medicos
        List<MedicoDto> medicos = manager.getMedicoDAO().getMedicoConEspecialida();
        for (MedicoDto medico : medicos) {
            System.out.println("medicos" + medico.getNombre());
        }
        request.setAttribute("medicos", medicos);
        return "AgendarCita.jsp";

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
        //guardar-cambios
        String accion = request.getParameter("accion");
        switch (accion) {
            case "guardar-cambios":

                guardarCambiosDatos(request, response);
                break;
            default:

        }

    }

    private void guardarCambiosDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            manager = new Manager();
            String nombre = request.getParameter("nombre");
            String dpi = request.getParameter("dpi");
            String tipo = request.getParameter("tipo");
            String peso = request.getParameter("peso");
            String fecha = request.getParameter("fecha");

            Persona persona = (Persona) request.getSession().getAttribute("personaSession");
            Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteSession");

            persona.setNombre(nombre);
            persona.setDpi(dpi);

            paciente.setTipoDeSangre(tipo);
            paciente.setPeso(peso);
            paciente.setFecha(java.sql.Date.valueOf(fecha));
            paciente.setPersonaDpi(dpi);
            ;

            Conexion.getInstancia().setAutoCommit(false);
            
            request.setAttribute("registro", manager.getPersonaDAO().modify(persona) && manager.getPacientesDAO().modify(paciente));
            
            Conexion.getInstancia().setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                Conexion.getInstancia().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        RequestDispatcher vista = request.getRequestDispatcher("paciente-feed.jsp");
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
