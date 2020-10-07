/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.connection.Conexion;
import com.hospital.entities.Consulta;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ConsultaController extends HttpServlet {

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
            out.println("<title>Servlet ConsultaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultaController at " + request.getContextPath() + "</h1>");
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
        String accion = request.getParameter("accion");
        String sigJsp = "";

        manager = new Manager();

        switch (accion) {
            case "modificar":

                sigJsp = "consulta-modificar.jsp";

                break;
            case "buscar":

            default:

        }

        System.out.println("pagJsp = " + sigJsp);
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

        manager = new Manager();

        switch (accion) {
            case "modificar":

                request.setAttribute("registro", modificarConsulta(request));
                sigJsp = "feed-admin.jsp";

                break;
            case "buscar":
                searchByParameter(request);
                sigJsp = "consulta-modificar.jsp";

            default:

        }

        System.out.println("pagJsp = " + sigJsp);
        RequestDispatcher vista = request.getRequestDispatcher(sigJsp);
        vista.forward(request, response);

    }

    private void searchByParameter(HttpServletRequest request) {

        String parametro = request.getParameter("buscar");
        System.out.println("Parametro: "+parametro);
        List<Consulta> consultas = new ArrayList<>();

        if (parametro.equals("")) {
            consultas = manager.getConsultaDAO().obtenerTodo();

        } else {
            consultas = manager.getConsultaDAO().getConsultaSearchLike(parametro);
        }

        request.setAttribute("consulta", consultas);

    }

    private boolean modificarConsulta(HttpServletRequest request) {

        try {
            String tipo = request.getParameter("tipo");
            String costo = request.getParameter("costo");
            String id = request.getParameter("id");

            Consulta consulta = manager.getConsultaDAO().obtener(Integer.valueOf(id));

            consulta.setTipo(tipo);
            consulta.setCosto(Double.parseDouble(costo));

            Conexion.getInstancia().setAutoCommit(false);

            manager.getConsultaDAO().modify(consulta);

            Conexion.getInstancia().setAutoCommit(true);

            return true;

        } catch (SQLException ex) {
            try {
                Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
                Conexion.getInstancia().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return false;

    }

    private Manager manager;

}
