/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

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
        String action = request.getParameter("accion");
        switch (action) {
            case "crearExamen":
                Examen examen = new Examen();
                crearExamen(examen, request, request);
                manager.getExamenDAO().insert(examen);
                request.setAttribute("insert", 1);
                
                System.out.println("Examen Creado");
                
                break;
            case "crearPaciente":

                insertPaciente(request, request);
                break;
            case "crearMedico":
                insertMedico(request, request);

                break;
            case "crearLab":
                insertLab(request, request);

                break;
            case "crearConsulta":

                
                break;
            
            
        }
    }
    public void crearExamen(Examen examen, HttpServletRequest request,HttpServletRequest response){
        
        examen.setCodigo(request.getParameter("codigo"));
        examen.setNombre(request.getParameter("nombre"));
        examen.setCosto(Double.parseDouble(request.getParameter("costo")));
        examen.setDescripcion(request.getParameter("descripcion"));
        examen.setOrden((request.getParameter("reqOrden").equals("1")));
        examen.setFormato(request.getParameter("formato"));
        
        
        
    }

    public void insertMedico( HttpServletRequest request,HttpServletRequest response){
        
        Persona persona = new Persona();
        Medico medico = new Medico();
        Usuario usuario = new Usuario();
    
    
        crearPersona(persona, request, response);
        crearUsuario(usuario, request, response);
        usuario.setPersonaDpi(persona.getDpi());
        
        System.out.println("=======================Persona: "+persona.getNombre());
        
        
        medico.setColegiado(Integer.parseInt(request.getParameter("colegiadoMedico")));
        System.out.println(" hora+++++++++++"+request.getParameter("horaEntrada")+":00");
        medico.setEntrada(java.sql.Time.valueOf(request.getParameter("horaEntrada")+":00"));
        medico.setSalida(java.sql.Time.valueOf(request.getParameter("horaSalida")+":00"));
        medico.setEstado(true);
        System.out.println("fECHA"+(request.getParameter("inicio")));
        medico.setInicio(java.sql.Date.valueOf(request.getParameter("inicio")));
        medico.setPersona_dpi(persona.getDpi());
        
        manager.getPersonaDAO().insert(persona);
        manager.getUsuarioDAO().insert(usuario);
        manager.getMedicoDAO().insert(medico);
        
        
        
        
    }
    public void crearPersona(Persona persona, HttpServletRequest request,HttpServletRequest response){
        
        persona.setNombre(request.getParameter("nombre"));
        persona.setCorreo(request.getParameter("email"));
        persona.setTelefono(request.getParameter("telefono"));
        persona.setDpi(request.getParameter("dpi"));
        persona.setEstado(true);
        
    }
    public void crearUsuario(Usuario usuario, HttpServletRequest request,HttpServletRequest response){
        
        usuario.setClave(request.getParameter("pwd"));
        usuario.setCodigo(request.getParameter("codigo"));
        usuario.setEstado(true);
        
    }
    public void insertPaciente(HttpServletRequest request,HttpServletRequest response){
 
        Persona persona = new Persona();
        Paciente paciente = new Paciente();
        Usuario usuario = new Usuario();
        
        crearPersona(persona, request, response);
        crearUsuario(usuario, request, response);
        crearPaciente(paciente, request, response);
        
        usuario.setPersonaDpi(persona.getDpi());
        paciente.setPersonaDpi(persona.getDpi());
        
        manager.getPersonaDAO().insert(persona);
        manager.getUsuarioDAO().insert(usuario);
        manager.getPacientesDAO().insert(paciente);
    }
    public void crearPaciente(Paciente paciente,HttpServletRequest request,HttpServletRequest response){

        
        paciente.setCodigo(request.getParameter("codigo"));
        paciente.setFecha(java.sql.Date.valueOf(request.getParameter("fecha")));
        paciente.setGenero(request.getParameter("genero"));
        paciente.setPeso(request.getParameter("peso"));
        paciente.setTipoDeSangre(request.getParameter("tipoSangre"));
        
        paciente.setEstado(true);
        
    }
    public void insertLab(HttpServletRequest request,HttpServletRequest response){
    
        Persona persona = new Persona();
        Usuario usuario = new Usuario();
        Laboratorista la= new Laboratorista();
        
        DiaTrabajo[] dt = new DiaTrabajo[request.getParameterValues("dia").length];
        
        
        crearPersona(persona, request, response);
        crearUsuario(usuario, request, response);
        crearLab(dt, la, request, response);
        
        usuario.setPersonaDpi(persona.getDpi());
        la.setPersonaDpi(persona.getDpi());
        
        manager.getPersonaDAO().insert(persona);
        manager.getUsuarioDAO().insert(usuario);
        manager.getLaboratoristasDAO().insert(la);
        
        for (DiaTrabajo diaTrabajo : dt) {
            
            manager.getDiaTrabajoDAO().insert(diaTrabajo);
        }
        
    }
    public void crearLab(DiaTrabajo[] dTrabajo, Laboratorista lab,HttpServletRequest request,HttpServletRequest response){
        
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
    public void crearConsulta(Consulta consulta,HttpServletRequest request,HttpServletRequest response){
     
        //verificar fecha
        consulta.setTipo(request.getParameter("tipo"));
        consulta.setCosto(Double.parseDouble(request.getParameter("costo")));
        
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
