/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.controllers;

import com.hospital.controllers.fileupload.FIleUpload;
import com.hospital.dto.CitaPac;
import com.hospital.entities.Agenda;
import com.hospital.entities.Cita;
import com.hospital.entities.Examen;
import com.hospital.entities.Dia;
import com.hospital.entities.ExamenPaciente;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Medico;
import com.hospital.entities.Paciente;
import com.hospital.mysql.Manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String siguiente = "";
        String accion = request.getParameter("accion");
        System.out.println("Accion doGet Citas Controller" + accion);

        switch (accion) {

            case "citas":

                manager = new Manager();

                Medico medico = (Medico) request.getSession().getAttribute("medicoSession");
                System.out.println("Citas Medicas, colegiado: " + medico.getColegiado());
                
                List<CitaPac> listado;
                listado = manager.getCitaDAO().getCitaPac(String.valueOf(medico.getColegiado()));
                request.setAttribute("citasPaciente", listado);
                siguiente = "atender-citas.jsp";

                break;
            

        }
        RequestDispatcher vista = request.getRequestDispatcher(siguiente);
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
        System.out.println("crear cita con " + request.getSession().getAttribute("colegiado") + request.getParameter("accion"));
        String[] action = request.getParameter("accion").split("=");

        accionesDoPost(action[0], request, response);

    }

    public void accionesDoPost(String action, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("action: " + action);
        try {
            System.out.println("Acciones Do Post");
            String pagJsp = "";
            switch (action) {
                case "crearCita": {

                    boolean valor = crearCita(request, response);
                    //request.setAttribute("almacenarCita", crearCita(request, response));
                    request.setAttribute("almacenarCita", valor);

                    pagJsp = "feedback-cita.jsp";

                    break;
                }

                case "lab":
                    boolean valor = crearCitaLab(request, response);
                    request.setAttribute("almacenarCita", valor);

                    pagJsp = "feedback-cita.jsp";
                    break;

            }

            RequestDispatcher vista = request.getRequestDispatcher(pagJsp);
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CitaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearCitaLab(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //parametros del request
        String accion = request.getParameter("accion");
        String registro = accion.split("=")[1];
        String codeExamen = (String) request.getSession().getAttribute("codeExamen");
        String fechaRequest = (request.getParameter("fecha"));
        System.out.println("fechaRequest = " + fechaRequest);

        //objetos
        Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteSession");
        Examen examen = manager.getExamenDAO().obtener(codeExamen);
        Laboratorista lab = manager.getLaboratoristasDAO().obtener(registro);

        System.out.println("Registro: " + registro);

        System.out.println("crearCita");
        //OBTENER LA AGENDA MEDIANTE EL REGISTRO DE LABORATORISTA
        Agenda agenda = manager.getAgendaDAO().getAgendaByRegistro(registro);

        // Cita cita = new Cita("cita-1", "Cita Medica", "1", paciente.getCodigo());
        Cita cita = new Cita("0", "LAB", "PENDIENTE", paciente.getCodigo(), 1);

        Dia dia = new Dia(
                0,
                java.sql.Date.valueOf((fechaRequest)),
                "PENDIENTE",
                agenda.getCodigo(),
                cita.getCodigo(),
                java.sql.Time.valueOf("00:00:00")
        );

        System.out.println("fecha norma + "+ fechaRequest);
        System.out.println("java.sql.Date: "+java.sql.Date.valueOf(fechaRequest));
        //verificar si existe una cita a esa fecha y hora d
        //Dia citaEnDia = manager.getDiaDAO().searchCoincidenceByDateHourAndAgenda(dia.getFecha(), dia.getHora(), agenda.getCodigo());
        //if (citaEnDia == null) {
        boolean insert = manager.getCitaDAO().insert(cita);
        String idCita = manager.getCitaDAO().lastInsertId();
        dia.setCitaCodigo(idCita);
        ;

        insert = (insert && manager.getDiaDAO().insert(dia)) == true;

        ExamenPaciente exPac = new ExamenPaciente(0, "", codeExamen, registro, paciente.getCodigo(), false, false, true);

        if (examen.isOrden()) {

            FIleUpload file = new FIleUpload();
            file.upload(request, "ordenFile");
            Part archivo = request.getPart("ordenFile");

            String nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();

            System.out.println("ordenPath" + nombreArchivo);
            exPac.setOrdenPath(nombreArchivo);

        }

        insert = (insert && manager.getExamenPacienteDAO().insert(exPac)) == true;

        //ExamenPaciente exPac = new ExamenPaciente(0, ordenPath, Examen_Codigo, Laboratoristas_registro, Pacientes_codigo, Medico_colegiado, insert, insert, insert)
        return insert;

        //  } else {
        //    return false;
        //}
    }

    public boolean crearCita(HttpServletRequest request, HttpServletResponse response) {

        Agenda agenda = manager.getAgendaDAO().getAgendaByColegido((String) request.getSession().getAttribute("colegiado"));

        Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteSession");

        System.out.println("Paciente que reserva la cita: " + paciente.getCodigo());

        String idConsulta = request.getParameter("idConsulta");
        System.out.println(agenda);

        Cita cita = new Cita("cita-1", "Cita Medica", "PENDIENTE", paciente.getCodigo(), Integer.parseInt(idConsulta));
        Dia dia = new Dia(
                0,
                java.sql.Date.valueOf(request.getParameter("fecha")),
                "PENDIENTE",
                agenda.getCodigo(),
                cita.getCodigo(),
                java.sql.Time.valueOf(request.getParameter("hora") + ":00")
        );

        //verificar si existe una cita a esa fecha
        Dia citaEnDia = manager.getDiaDAO().searchCoincidenceByDateHourAndAgenda(dia.getFecha(), dia.getHora(), agenda.getCodigo());

        if ((citaEnDia == null || citaEnDia.getDescripcion().equalsIgnoreCase("CANCELADA"))) {

            boolean citaReturn = manager.getCitaDAO().insert(cita);
            ;
            dia.setCitaCodigo((manager.getCitaDAO().lastInsertId()));
            System.out.println("codigo Cita" + dia.getCita_codigo());
            boolean diaReturn = manager.getDiaDAO().insert(dia);

            return citaReturn == diaReturn;

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

    public String cargarCitasPendientes(HttpServletRequest request, HttpServletResponse response) {
        manager = new Manager();

        Medico medico = (Medico) request.getSession().getAttribute("medicoSession");
        System.out.println("Citas Medicas, colegiado: " + medico.getColegiado());
        //List<CitaPac> listado = manager.getCitaDAO().getCitaPac((String) request.getSession().getAttribute("medicoSession"));
        List<CitaPac> listado;
        listado = manager.getCitaDAO().getCitaPac(String.valueOf(medico.getColegiado()));
        request.setAttribute("citasPaciente", listado);
        return "atender-citas.jsp";
    }
    private Manager manager;
}
