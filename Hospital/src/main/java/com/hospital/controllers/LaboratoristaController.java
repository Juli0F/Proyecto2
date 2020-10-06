/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.dto.ExamenHoy;
import com.hospital.dto.FechaConMasTrabajo;
import com.hospital.dto.ResultadoPaciente;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Resultado;
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
public class LaboratoristaController extends HttpServlet {

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
            out.println("<title>Servlet LaboratoristaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LaboratoristaController at " + request.getContextPath() + "</h1>");
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

        Laboratorista lab = (Laboratorista) request.getSession().getAttribute("labSession");
        String accion = request.getParameter("accion");
        String pagJsp = "";

        switch (accion) {
            case "subirresultados":
                //llamar a la lista de examnes sin resultado y enviarla como parametro
                if (lab.getRegistro() == null) {
                    System.out.println("lab es null");
                } else {

                    List<ResultadoPaciente> listado = manager.getExamenPacienteDAO().getResultadoPaciente(lab.getRegistro());

                    for (ResultadoPaciente resultadoPaciente : listado) {
                        System.out.println(resultadoPaciente.getNombre()+ ", "+resultadoPaciente.getFecha() );
                    }
                    request.setAttribute("listado", listado);

                    pagJsp = "subir-resultado.jsp";
                }
                break;
            case "hoy":
                getCitasLabHoy(request);
                pagJsp = "lab-examenes-hoy.jsp";
                break;
            case "realizados":
                
                realizadosHoyTodos(request);
                pagJsp = "realizados.jsp";
                break;
                
            case "fechas":
                
                getListDatesWithWork(request);
                pagJsp = "fechas-mas-trabajo.jsp";
                break;
                        
        }

        System.out.println("pagJsp = " + pagJsp);
        RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
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
        String pagJsp = "";
        
        switch (accion) {
            case "realizados":
                realizadosHoyActual(request);
                pagJsp = "realizados.jsp";
                
                break;
            default:
                throw new AssertionError();
        }
        
        System.out.println("pagJsp = " + pagJsp);
        RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
        vista.forward(request, response);
        
    }

    
    public void realizadosHoyActual(HttpServletRequest request ){
        Laboratorista actual = (Laboratorista) request.getSession().getAttribute("labSession");
        List<ResultadoPaciente> misResultados = manager.getResultadoDAO().resultadoDeHoy(actual.getRegistro());
        
        request.getSession().setAttribute("realizados", misResultados);       
        
    }
    public void realizadosHoyTodos(HttpServletRequest request ){
        
        List<ResultadoPaciente> misResultados = manager.getResultadoDAO().resultadoDeHoy(null);
        
        request.getSession().setAttribute("realizados", misResultados);       
        
    }
    
    public void getCitasLabHoy(HttpServletRequest request){
        
        Laboratorista actual = (Laboratorista) request.getSession().getAttribute("labSession");
        List<ExamenHoy>  listado = manager.getExamenPacienteDAO().getExamenesParaHoy(actual.getRegistro());
        
        request.setAttribute("citasHoy", listado);
        
    }
    
    public void getListDatesWithWork(HttpServletRequest request){
        List<FechaConMasTrabajo> lista = manager.getDiaDAO().getDateWithMostWork();
        request.setAttribute("fechas", lista);
    }
            
    

    private Manager manager;
}
