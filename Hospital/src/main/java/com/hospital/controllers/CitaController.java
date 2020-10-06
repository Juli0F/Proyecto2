/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.entities.Agenda;
import com.hospital.entities.Cita;
import com.hospital.entities.Dia;
import com.hospital.entities.Paciente;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CitaController extends HttpServlet {

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
            out.println("<title>Servlet CitaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CitaController at " + request.getContextPath() + "</h1>");
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
        System.out.println("crear cita con " + request.getSession().getAttribute("colegiado") + request.getParameter("accion"));
        String[] action = request.getParameter("accion").split("=");

        accionesDoPost(action[0], request, response);

    }

    public void accionesDoPost(String action, HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Acciones Do Post");
            String pagJsp = "";
            switch (action) {
                case "crearCita":
                    request.setAttribute("almacenarCita", crearCita(request, response));
                    pagJsp = "feedback-cita.jsp";
                    break;
                case "lab":
                    break;

            }

            RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CitaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearCitaLab(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("crearCita");
        //OBTENER LA AGENDA MEDIANTE EL REGISTRO DE LABORATORISTA
        Agenda agenda = manager.getAgendaDAO().getAgendaByColegido((String) request.getSession().getAttribute("colegiado"));

        Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteSession");

        Cita cita = new Cita("cita-1", "Cita Medica", "1", paciente.getCodigo());

        Dia dia = new Dia(
                0,
                java.sql.Date.valueOf(request.getParameter("fecha")),
                "Cita Medica",
                agenda.getCodigo(),
                cita.getCodigo(),
                java.sql.Time.valueOf(request.getParameter("hora") + ":00")
        );

        //verificar si existe una cita a esa fecha y hora d
        Dia citaEnDia = manager.getDiaDAO().searchCoincidenceByDateHourAndAgenda(dia.getFecha(), dia.getHora(), agenda.getCodigo());

        if (citaEnDia != null) {

            return manager.getCitaDAO().insert(cita) && manager.getDiaDAO().insert(dia);

        } else {
            return false;
        }
        
    }

    public boolean crearCita(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("crearCita");
        Agenda agenda = manager.getAgendaDAO().getAgendaByColegido((String) request.getSession().getAttribute("colegiado"));

        Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteSession");

        Cita cita = new Cita("cita-1", "Cita Medica", "1", paciente.getCodigo());

        Dia dia = new Dia(
                0,
                java.sql.Date.valueOf(request.getParameter("fecha")),
                "Cita Medica",
                agenda.getCodigo(),
                cita.getCodigo(),
                java.sql.Time.valueOf(request.getParameter("hora") + ":00")
        );

        //verificar si existe una cita a esa fecha y hora d
        //  manager.getAgendaDAO().getAgendaByColegido(""+agenda.getMedico_colegiado());
        Dia citaEnDia = manager.getDiaDAO().searchCoincidenceByDateHourAndAgenda(dia.getFecha(), dia.getHora(), agenda.getCodigo());

        if (citaEnDia != null) {

            return manager.getCitaDAO().insert(cita) && manager.getDiaDAO().insert(dia);

        } else {
            return false;
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

    private Manager manager;
}
