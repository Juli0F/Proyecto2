/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.controllers.fileupload.FIleUpload;
import com.hospital.dto.CantidadInformes;
import com.hospital.dto.Intervalo;
import com.hospital.dto.PacienteHistorial;
import com.hospital.entities.Medico;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class MedicosInforme extends HttpServlet {

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
            out.println("<title>Servlet MedicosInforme</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MedicosInforme at " + request.getContextPath() + "</h1>");
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
        manager = new Manager();

        String sigJsp = "";

        String accion = request.getParameter("accion");

        switch (accion) {
            case "historial":
                List<PacienteHistorial> lst = manager.getPacientesDAO().getPacienteHistoria();

////                for (PacienteHistorial pacienteHistorial : lst) {
////                    System.out.println("Paciente Consultas: "+pacienteHistorial.getConsultas().size()
//                            + "Paciente Examenes" +  pacienteHistorial.getResultados().size());
////                }
                System.out.println("siguiente: " + sigJsp);
                request.setAttribute("ruta", FIleUpload.PATH_FILES);
                request.setAttribute("historial", lst);
                sigJsp = "historial-de-todos.jsp";

                break;
            case "intervalo":

                sigJsp = "Citas-intervalo-tiempo.jsp";
                break;
            case "hoy":
                Medico medico = (Medico) request.getSession().getAttribute("medicoSession");
                List<Intervalo> citasHoy = manager.getCitaDAO().getCitasParaHoyFechasByColegiado(String.valueOf(medico.getColegiado()));
                
                request.setAttribute("citasHoy", citasHoy);
                sigJsp= "citas-para-hoy.jsp";
                 
                break;
            case "cantidad":
                
                List<CantidadInformes> cantidad = manager.getCitaDAO().getCantidadDeInformesPorPaciente(null, null);                        
                        request.setAttribute("citasHoy", cantidad);
                        
                    sigJsp = "cantidad-informes.jsp";
                    
                    break;
                            
            default:

        }

        RequestDispatcher vista = request.getRequestDispatcher(sigJsp);
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
        String sigJsp = "";
        
        switch (accion) {
            case "intervalo":
                sigJsp = citasPorIntervalo(request, response);
                break;
                
            case "cantidad":
                sigJsp = cantidadDeInformesPorPaciente(request, response);
                break;
            default:
                
        }
        RequestDispatcher vista = request.getRequestDispatcher(sigJsp);
        vista.forward(request, response);
                

    }
    private String cantidadDeInformesPorPaciente(HttpServletRequest request, HttpServletResponse response){
        String fechaInicial = request.getParameter("inicio");
        String fechaFinal = request.getParameter("final");
        System.out.println("Fecha" + fechaInicial.length()+ ": "+fechaFinal.length());
        
        
        if (fechaInicial.equals("")) {
            fechaInicial  = null;
        }
        if (fechaFinal.equals("")) {
            fechaFinal = null;
        }
        
        List<CantidadInformes> cantidad = manager.getCitaDAO().getCantidadDeInformesPorPaciente(fechaInicial, fechaFinal);
        request.setAttribute("citasHoy", cantidad);
        
        
        
     return "cantidad-informes.jsp";
    }
    
    private String citasPorIntervalo(HttpServletRequest request , HttpServletResponse response){
        
        String fechaInicial = request.getParameter("inicio");
        String fechaFinal = request.getParameter("final");
        System.out.println("Fecha" + fechaInicial.length()+ ": "+fechaFinal.length());
        
        
        if (fechaInicial.equals("")) {
            fechaInicial  = null;
        }
        if (fechaFinal.equals("")) {
            fechaFinal = null;
        }
        
        Medico medico = (Medico) request.getSession().getAttribute("medicoSession");
        System.out.println("Colegiado Acutal: "+ medico.getColegiado());
        List<Intervalo> citas = manager.getCitaDAO().
                    getCitasIntervaloFechasByColegiado
                                                (fechaInicial,
                                                fechaFinal,
                                                String.valueOf(medico.getColegiado()));
        
        request.setAttribute("citas", citas);
        //       Citas-intervalo-tiempo
        return  "Citas-intervalo-tiempo.jsp";
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
