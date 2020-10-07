/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.connection.Conexion;
import com.hospital.dto.MedicoCant;
import com.hospital.entities.Consulta;
import com.hospital.entities.DiaTrabajo;
import com.hospital.entities.Examen;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Medico;
import com.hospital.entities.Paciente;
import com.hospital.entities.Persona;
import com.hospital.entities.Usuario;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AdminController extends HttpServlet {

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
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
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
        switch (accion) {
            case "medicosMas":
                sigJsp = "admin-diez-medicos-con-mas-informes.jsp";
                break;

            case "medicoMenos":

                sigJsp = "admin-diez-medicos-con-menos-informes.jsp";
                break;
            case "salir100":
                request.getSession().setAttribute("admin", null);
                request.getSession().setAttribute("personaSession", null);
                sigJsp = "index.jsp";
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
        manager = new Manager();
        String action = request.getParameter("accion");
        String sigJsp = "";

        switch (action) {
            case "crearExamen":
                Examen examen = new Examen();
                crearExamen(examen, request, request);
                manager.getExamenDAO().insert(examen);

                //registro
                request.setAttribute("registro", manager.getExamenDAO().insert(examen));

                System.out.println("Examen Creado");
                sigJsp = "feed-admin.jsp";

                break;
            case "crearPaciente":

                request.setAttribute("registro", insertPaciente(request, request));
                sigJsp = "feed-admin.jsp";

                break;
            case "crearMedico":

                request.setAttribute("registro", insertMedico(request, request));
                sigJsp = "feed-admin.jsp";

                break;
            case "crearLab":

                request.setAttribute("registro", insertLab(request, request));
                sigJsp = "feed-admin.jsp";

                break;
            case "crearConsulta":

                crearConsulta(new Consulta(), request, request);
                sigJsp = "feed-admin.jsp";

                break;
            case "medicoMas":

                medicoInformes(request, "desc limit 10");
                sigJsp = "admin-diez-medicos-con-mas-informes.jsp";
                break;
            case "medicoMenos":
                medicoInformes(request, "asc limit 10");
                sigJsp = "admin-diez-medicos-con-menos-informes.jsp";
                break;

        }
        RequestDispatcher vista = request.getRequestDispatcher(sigJsp);
        vista.forward(request, response);
    }

    private void medicoInformes(HttpServletRequest request, String order) {

        String fechaInicial = request.getParameter("inicio");
        String fechaFinal = request.getParameter("final");

        System.out.println("Fecha" + fechaInicial.length() + ": " + fechaFinal.length());

        if (fechaInicial.equals("")) {
            fechaInicial = null;
        }
        if (fechaFinal.equals("")) {
            fechaFinal = null;
        }
        //desc limit 10 
        List<MedicoCant> cantidad = manager.getMedicoDAO().getDiezMedicos(fechaInicial, fechaFinal, order);

        request.setAttribute("medicoInforme", cantidad);

    }

    public void crearExamen(Examen examen, HttpServletRequest request, HttpServletRequest response) {

        examen.setCodigo(request.getParameter("codigo"));
        examen.setNombre(request.getParameter("nombre"));
        examen.setCosto(Double.parseDouble(request.getParameter("costo")));
        examen.setDescripcion(request.getParameter("descripcion"));
        examen.setFormato(request.getParameter("formato"));
        examen.setOrden(request.getParameter("reqOrden") != null);

    }

    public boolean insertMedico(HttpServletRequest request, HttpServletRequest response) {

        try {
            Persona persona = new Persona();
            Medico medico = new Medico();
            Usuario usuario = new Usuario();

            crearPersona(persona, request, response);
            crearUsuario(usuario, request, response);
            usuario.setPersonaDpi(persona.getDpi());

            System.out.println("=======================Persona: " + persona.getNombre());

            medico.setColegiado(Integer.parseInt(request.getParameter("colegiadoMedico")));
            System.out.println(" hora+++++++++++" + request.getParameter("horaEntrada") + ":00");
            medico.setEntrada(java.sql.Time.valueOf(request.getParameter("horaEntrada") + ":00"));
            medico.setSalida(java.sql.Time.valueOf(request.getParameter("horaSalida") + ":00"));
            medico.setEstado(true);
            System.out.println("fECHA" + (request.getParameter("inicio")));
            medico.setInicio(java.sql.Date.valueOf(request.getParameter("inicio")));
            medico.setPersona_dpi(persona.getDpi());

            Conexion.getInstancia().setAutoCommit(false);

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getMedicoDAO().insert(medico);

            Conexion.getInstancia().setAutoCommit(true);

            return true;

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                Conexion.getInstancia().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;

    }

    public void crearPersona(Persona persona, HttpServletRequest request, HttpServletRequest response) {

        persona.setNombre(request.getParameter("nombre"));
        persona.setCorreo(request.getParameter("email"));
        persona.setTelefono(request.getParameter("telefono"));
        persona.setDpi(request.getParameter("dpi"));
        persona.setEstado(true);

    }

    public void crearUsuario(Usuario usuario, HttpServletRequest request, HttpServletRequest response) {

        usuario.setClave(request.getParameter("pwd"));
        usuario.setCodigo(request.getParameter("codigo"));
        usuario.setEstado(true);

    }

    public boolean insertPaciente(HttpServletRequest request, HttpServletRequest response) {

        try {
            Persona persona = new Persona();
            Paciente paciente = new Paciente();
            Usuario usuario = new Usuario();

            crearPersona(persona, request, response);
            crearUsuario(usuario, request, response);
            crearPaciente(paciente, request, response);

            usuario.setPersonaDpi(persona.getDpi());
            paciente.setPersonaDpi(persona.getDpi());

            Conexion.getInstancia().setAutoCommit(false);
            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getPacientesDAO().insert(paciente);
            Conexion.getInstancia().setAutoCommit(true);
            return true;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                Conexion.getInstancia().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }

    public void crearPaciente(Paciente paciente, HttpServletRequest request, HttpServletRequest response) {

        paciente.setCodigo(request.getParameter("codigo"));
        paciente.setFecha(java.sql.Date.valueOf(request.getParameter("fecha")));
        paciente.setGenero(request.getParameter("genero"));
        paciente.setPeso(request.getParameter("peso"));
        paciente.setTipoDeSangre(request.getParameter("tipoSangre"));

        paciente.setEstado(true);

    }

    public boolean insertLab(HttpServletRequest request, HttpServletRequest response) {

        try {
            Persona persona = new Persona();
            Usuario usuario = new Usuario();
            Laboratorista la = new Laboratorista();

            DiaTrabajo[] dt = new DiaTrabajo[request.getParameterValues("dia").length];

            crearPersona(persona, request, response);
            crearUsuario(usuario, request, response);
            crearLab(dt, la, request, response);

            usuario.setPersonaDpi(persona.getDpi());
            la.setPersonaDpi(persona.getDpi());

            Conexion.getInstancia().setAutoCommit(false);

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getLaboratoristasDAO().insert(la);

            for (DiaTrabajo diaTrabajo : dt) {

                manager.getDiaTrabajoDAO().insert(diaTrabajo);
            }

            Conexion.getInstancia().setAutoCommit(true);

            return true;

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                Conexion.getInstancia().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        return false;
    }

    public void crearLab(DiaTrabajo[] dTrabajo, Laboratorista lab, HttpServletRequest request, HttpServletRequest response) {

        lab.setRegistro(request.getParameter("registro"));
        lab.setInicio(java.sql.Date.valueOf(request.getParameter("fecha")));
        lab.setOcupado("0");
        lab.setEstado("1");

        String[] dias = request.getParameterValues("dia");

        for (int i = 0; i < dias.length; i++) {

            dTrabajo[i].setEstado(true);
            dTrabajo[i].setTurno_idTurno(Integer.parseInt(dias[i]));
            dTrabajo[i].setLaboratoristas_registro(lab.getRegistro());

        }

    }

    public void crearConsulta(Consulta consulta, HttpServletRequest request, HttpServletRequest response) {

        //verificar fecha
        consulta.setTipo(request.getParameter("tipo"));
        consulta.setCosto(Double.parseDouble(request.getParameter("costo")));

        request.setAttribute("registro", manager.getConsultaDAO().insert(consulta));

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private Manager manager;
}
