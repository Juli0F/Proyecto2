/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.loadxml;

import com.hospital.entities.Administrador;
import com.hospital.entities.Agenda;
import com.hospital.entities.Cita;
import com.hospital.entities.Consulta;
import com.hospital.entities.Dia;
import com.hospital.entities.DiaTrabajo;
import com.hospital.entities.Especialidad;
import com.hospital.entities.Examen;
import com.hospital.entities.Informe;
import com.hospital.entities.Medico;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Paciente;
import com.hospital.entities.Persona;
import com.hospital.entities.Resultado;
import com.hospital.entities.Usuario;
import com.hospital.mysql.Manager;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author julio
 */
public class Interprete {

    private Manager manager;
    private int idAgenda;
    private List<String> info ;
    
    public static void main(String[] args) {
        Interprete inter = new Interprete();
        inter.loadFile(new File(""));
    }
    public Interprete() {
        this.manager = new Manager();
        this.info = new ArrayList<String>();
        
    }

    public void loadFile(File file) {

        
        String path = "/home/julio/Documentos/IPC/Hospital/Proyecto2/data.xml";
        File file2 = new File(path);

        try {
            System.out.println("Empezara a cargar ");
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML

            Document documento = builder.parse(file2);
            
            NodeList listaDoctores = documento.getElementsByTagName("doctor");
            NodeList listaAdmins = documento.getElementsByTagName("admin");
            NodeList listaLaboratoristas = documento.getElementsByTagName("laboratorista");
            NodeList listaPaciente = documento.getElementsByTagName("paciente");
            NodeList listadoExamen = documento.getElementsByTagName("examen");

            NodeList listadoInforme = documento.getElementsByTagName("reporte");
            NodeList listadoResultado = documento.getElementsByTagName("resultado");
            NodeList listadoCita = documento.getElementsByTagName("cita");
            NodeList listadoConsulta = documento.getElementsByTagName("consulta");

            tagAdmin(listaAdmins);
            tagDoctor(listaDoctores);
            tagLaboratorista(listaLaboratoristas);
            tagPaciente(listaPaciente);
            tagExamen(listadoExamen);
            tagInforme(listadoInforme);
            tagResultado(listadoResultado);
            tagCita(listadoCita);
            tagConsulta(listadoConsulta);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void tagAdmin(NodeList listaAdmin) {

        Usuario usuario = new Usuario();
        Administrador admin = new Administrador();
        Persona persona = new Persona();

        // Recorro las etiquetas
        System.out.println(" <========>Especialidad");
        for (int i = 0; i < listaAdmin.getLength(); i++) {
            // Cojo el nodo actual
            Node nodo = listaAdmin.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido
                        if (!hijo.getNodeName().equalsIgnoreCase("horario")) {
                            System.out.println("Propiedad: " + hijo.getNodeName()
                                    + ", Valor: " + hijo.getTextContent());
                            switch (hijo.getNodeName().toUpperCase()) {
                                case "CODIGO":
                                    usuario.setCodigo(hijo.getTextContent());
                                    break;
                                case "DPI":

                                    persona.setDpi(hijo.getTextContent());

                                    break;
                                case "NOMBRE":

                                    persona.setNombre(hijo.getTextContent());
                                    break;
                                case "PASSWORD":
                                    usuario.setClave(hijo.getTextContent());
                                    break;
                                default:
                                    System.out.println("Mostrar error, etiqueta no conocida");
                                //throw new AssertionError();
                            }
                            usuario.setPersonaDpi(persona.getDpi());

                        }

                    }

                }
                persona.setTelefono("");
                persona.setCorreo("");
                manager.getPersonaDAO().insert(persona);
                info.add((manager.getPersonaDAO().insert(persona)?"Persona Creada Correctamente ":"Error al Insertar Persona ")+"DPI: "+persona.getDpi());
                
                admin.setCodigo(usuario.getCodigo());                
                admin.setPersona_dpi(persona.getDpi());
                
                //manager.getAdministradorDAO().insert(admin);
                ;
                info.add((manager.getAdministradorDAO().insert(admin)?"Administrador Creado Correctamente":"Error Al Insertar Admin")+admin.getCodigo());

                info.add((manager.getUsuarioDAO().insert(usuario)?"Usuario Creado Correctamente":"Error Al Insertar Usuario")+usuario.getCodigo());
                admin.setCodigo(usuario.getCodigo());
                System.out.println("");
            }

        }
    }

    public void tagDoctor(NodeList listaDoctor) {
        // Recorro las etiquetas
        Persona persona;
        Usuario usuario;
        Medico medico;
        String[] horario;
        List<String> especialidades;

        for (int i = 0; i < listaDoctor.getLength(); i++) {

            persona = new Persona();
            medico = new Medico();
            usuario = new Usuario();
            horario = new String[2];
            especialidades = new ArrayList<>();

            // Cojo el nodo actual
            Node nodo = listaDoctor.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido
                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());

                        if (hijo.getNodeName().equalsIgnoreCase("horario")) {

                            horario = tagHorario(hijo);

                        } else if (hijo.getNodeName().equalsIgnoreCase("especialidad")) {
                            especialidades = tagEspecialidad(hijo);

                        } else {

                            crearPersona(persona, hijo.getNodeName(), hijo.getTextContent());
                            crearMedico(medico, hijo.getNodeName(), hijo.getTextContent());
                            crearUsuario(usuario, hijo.getNodeName(), hijo.getTextContent());

                        }
                    }

                }
                //manager.getPersonaDAO().insert(persona);
                info.add((manager.getPersonaDAO().insert(persona)?"Persona Creada Correctamente ":"Error al Insertar Persona ")+"DPI: "+persona.getDpi());
                medico.setEntrada(java.sql.Time.valueOf(horario[0] + ":00"));
                medico.setSalida(java.sql.Time.valueOf(horario[1] + ":00"));
                medico.setPersona_dpi(persona.getDpi());
                manager.getMedicoDAO().insert(medico);
                manager.getUsuarioDAO().insert(usuario);

                for (String especialidad : especialidades) {

                    Especialidad es = new Especialidad(0, especialidad, true, medico.getColegiado());
                    manager.getEspecialidadDAO().insert(es);

                }
                System.out.println("");
            }

        }
    }

    public void tagLaboratorista(NodeList listadoLaboratorista) {
        // Recorro las etiquetas
        System.out.println(" <========>Laboratorista");
        Persona persona;
        Usuario usuario;
        Laboratorista laboratorista;
        String[] horario;
        List<String> turnos;

        for (int i = 0; i < listadoLaboratorista.getLength(); i++) {

            persona = new Persona();
            laboratorista = new Laboratorista();
            usuario = new Usuario();
            horario = new String[2];
            turnos = new ArrayList<>();

            // Cojo el nodo actual
            Node nodo = listadoLaboratorista.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        if (!hijo.getNodeName().equalsIgnoreCase("trabajo")) {

                            System.out.println("Propiedad: " + hijo.getNodeName()
                                    + ", Valor: " + hijo.getTextContent());
                            crearPersona(persona, hijo.getNodeName(), hijo.getTextContent());
                            crearUsuario(usuario, hijo.getNodeName(), hijo.getTextContent());
                            crearLaboratorista(laboratorista, hijo.getNodeName(), hijo.getTextContent());

                        } else {
                            //dias que trabaja
                            turnos = tagTrabajo(hijo);
                        }
                    }

                }
                info.add((manager.getPersonaDAO().insert(persona)?"Persona Creada Correctamente ":"Error al Insertar Persona ")+"DPI: "+persona.getDpi());
                manager.getPersonaDAO().insert(persona);
                laboratorista.setPersonaDpi(persona.getDpi());
                manager.getLaboratoristasDAO().insert(laboratorista);

                for (String turno : turnos) {
                    DiaTrabajo diaTrabajo = null;

                    switch (turno) {
                        case "LUNES":
                            diaTrabajo = new DiaTrabajo(0, 1, laboratorista.getRegistro(), true);

                            break;
                        case "MARTES":
                            diaTrabajo = new DiaTrabajo(0, 2, laboratorista.getRegistro(), true);
                            break;
                        case "MIERCOLES":
                            diaTrabajo = new DiaTrabajo(0, 3, laboratorista.getRegistro(), true);
                            break;
                        case "JUEVES":
                            diaTrabajo = new DiaTrabajo(0, 4, laboratorista.getRegistro(), true);
                            break;
                        case "VIERNES":
                            diaTrabajo = new DiaTrabajo(0, 5, laboratorista.getRegistro(), true);
                            break;
                        case "SABADO":
                            diaTrabajo = new DiaTrabajo(0, 6, laboratorista.getRegistro(), true);
                            break;
                        case "DOMINGO":
                            diaTrabajo = new DiaTrabajo(0, 7, laboratorista.getRegistro(), true);
                            break;

                    }
                    manager.getDiaTrabajoDAO().insert(diaTrabajo);
                }
                usuario.setPersonaDpi(persona.getDpi());
                System.out.println("================>" + usuario.getPersona_dpi() + "== " + persona.getDpi());
                manager.getUsuarioDAO().insert(usuario);
                System.out.println("");
            }

        }
    }

    public void tagPaciente(NodeList listadoPaciente) {
        // Recorro las etiquetas
        System.out.println(" <========>Paciente");

        Persona persona;
        Usuario usuario;
        Paciente paciente;

        for (int i = 0; i < listadoPaciente.getLength(); i++) {
            persona = new Persona();
            paciente = new Paciente();
            usuario = new Usuario();

            // Cojo el nodo actual
            Node nodo = listadoPaciente.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());

                        crearUsuario(usuario, hijo.getNodeName(), hijo.getTextContent());
                        crearPersona(persona, hijo.getNodeName(), hijo.getTextContent());
                        crearPaciente(paciente, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                
               // manager.getPersonaDAO().insert(persona);
               info.add((manager.getPersonaDAO().insert(persona)?"Persona Creada Correctamente ":"Error al Insertar Persona ")+"DPI: "+persona.getDpi());
                usuario.setPersonaDpi(persona.getDpi());

                manager.getUsuarioDAO().insert(usuario);

                paciente.setPersonaDpi(persona.getDpi());

                manager.getPacientesDAO().insert(paciente);

                System.out.println("");
            }

        }
    }

    public void tagExamen(NodeList listadoExamen) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        Examen examen;
        for (int i = 0; i < listadoExamen.getLength(); i++) {
            // Cojo el nodo actual
            Node nodo = listadoExamen.item(i);
            examen = new Examen();

            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearExamen(examen, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                System.out.println("");
            }

        }
    }

    public void tagInforme(NodeList listadoInforme) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        Informe informe;
        for (int i = 0; i < listadoInforme.getLength(); i++) {

            informe = new Informe();

            // Cojo el nodo actual
            Node nodo = listadoInforme.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearInforme(informe, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                System.out.println("");
            }

        }
    }

    public void tagResultado(NodeList listadoResultado) {
        // Recorro las etiquetas
        Resultado resultado;
        System.out.println(" <========>Examen");
        for (int i = 0; i < listadoResultado.getLength(); i++) {
            // Cojo el nodo actual
            resultado = new Resultado();
            Node nodo = listadoResultado.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearResultado(resultado, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                System.out.println("");
            }

        }
    }

    public void tagCita(NodeList listadoDeCitas) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        for (int i = 0; i < listadoDeCitas.getLength(); i++) {
            // Cojo el nodo actual
            Node nodo = listadoDeCitas.item(i);
            boolean existeAgenda = false;
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Integer agenda = 0;
                Agenda agendaMedica = null;
                Dia dia = null;
                java.sql.Time horaCita = null;
                java.sql.Date fechaCita = null;
                Integer colegiadoMedico = null;

                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                Cita cita = new Cita();
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        String value = hijo.getTextContent();

                        switch (hijo.getNodeName().toUpperCase()) {
                            case "CODIGO":
                                cita.setCodigo(value);

                                break;
                            case "PACIENTE":
                                //CONSULTA,VERIFICA QUE EL PACIENTE EXISTA DENTRO DE LA DB

                                //if (agendaMedica != null) {
                                if (manager.getPacientesDAO().obtener(value) == null) {

                                    System.out.println("Aun no Existe agenda, crear");

                                } else {

                                    cita.setPacientes_codigo(value);

                                }

                                //}
                                break;
                            case "MEDICO":
                                if (agendaMedica != null) {

                                } else {

                                    if (manager.getMedicoDAO().getMedicoByCodigoUsuario(value) == null) {

                                        System.out.println("Mostrar error el Medico no existe Con el Codigo " + value);
                                    } else {

                                        if (manager.getAgendaDAO().obtenerAgendaMedica((value)) == null) {

                                            Medico medico = manager.getMedicoDAO().getMedicoByCodigoUsuario(value);
                                            colegiadoMedico = medico.getColegiado();
                                            existeAgenda = false;

                                        } else {
                                            agendaMedica = manager.getAgendaDAO().obtenerAgendaMedica((value));
                                            existeAgenda = true;

                                        }
                                        //cita.set(Integer.parseInt(value));

                                    }

                                }

                                break;

                            case "FECHA":

                                fechaCita = java.sql.Date.valueOf(value);

                                break;
                            case "HORA":

                                horaCita = java.sql.Time.valueOf(value + ":00");
                                //dia.setHora(java.sql.Time.valueOf(value+":00"));

                                break;
                            default:

                        }

                    }

                }

                if (cita.getPacientes_codigo() != null) {
                    if (existeAgenda) {

                        //verifico que no exista una cita con esa fecha y hora, en la agenda del doctor
                        if (manager.getDiaDAO().searchCoincidenceByDateHourAndAgenda(fechaCita, horaCita, agendaMedica.getCodigo()) != null) {

                            manager.getCitaDAO().insert(cita);
                            //(int idDia, java.sql.Date fecha, String descripcion, int Agenda_codigo, int Cita_codigo, java.sql.Time hora) {
                            dia = new Dia(0, fechaCita, "", agendaMedica.getCodigo(), cita.getCodigo(), horaCita);
                            manager.getDiaDAO().insert(dia);

                        } else {
                            System.out.println(" Lanzar Error Cita existe en fecha y hora   ");
                        }
                    } else {
                        agendaMedica = new Agenda(0, "1", colegiadoMedico, null);
                        manager.getAgendaDAO().insert(agendaMedica);

                        agenda = manager.getAgendaDAO().lastInsertId();
                        agendaMedica = manager.getAgendaDAO().obtener(agenda);

                        manager.getCitaDAO().insert(cita);
                        //(int idDia, java.sql.Date fecha, String descripcion, int Agenda_codigo, int Cita_codigo, java.sql.Time hora) {
                        dia = new Dia(0, fechaCita, "", agendaMedica.getCodigo(), cita.getCodigo(), horaCita);
                        manager.getDiaDAO().insert(dia);
                    }
                } else {
                    System.out.println("no se puede crear cita, falta paciente");
                }
                System.out.println("");
            }

        }
    }

    public void tagConsulta(NodeList listadoDeConsultas) {
        // Recorro las etiquetas
        System.out.println(" <========>Consultas");
        Consulta consulta;
        for (int i = 0; i < listadoDeConsultas.getLength(); i++) {

            consulta = new Consulta();
            // Cojo el nodo actual
            Node nodo = listadoDeConsultas.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Propiedad: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearConsulta(consulta, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                System.out.println("");
            }

        }
    }

    public List<String> tagEspecialidad(Node especialidad) {
        // Recorro las etiquetas
        List<String> especilidadesMedicas = new ArrayList<>();
        System.out.println("<========>Especialidad<========>Especialidad<========>");
        // for (int i = 0; i < especialidad.getLength(); i++) {
        // Cojo el nodo actual
        Node nodo = especialidad;
        // Compruebo si el nodo es un elemento
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Lo transformo a Element
            Element e = (Element) nodo;
            // Obtengo sus hijos
            NodeList hijos = e.getChildNodes();
            // Recorro sus hijos
            for (int j = 0; j < hijos.getLength(); j++) {
                // Obtengo al hijo actual
                Node hijo = hijos.item(j);
                // Compruebo si es un nodo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Muestro el contenido

                    especilidadesMedicas.add(hijo.getTextContent());
                }

            }
            System.out.println("");
        }

        return especilidadesMedicas;
    }

    public String[] tagHorario(Node nodeHorario) {
        // Recorro las etiquetas
        String[] horario = new String[2];
        System.out.println("<========>Especialidad<========>Especialidad<========>");
        // for (int i = 0; i < especialidad.getLength(); i++) {
        // Cojo el nodo actual
        Node nodo = nodeHorario;
        // Compruebo si el nodo es un elemento
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Lo transformo a Element
            Element e = (Element) nodo;
            // Obtengo sus hijos
            NodeList hijos = e.getChildNodes();
            // Recorro sus hijos
            for (int j = 0; j < hijos.getLength(); j++) {
                // Obtengo al hijo actual
                Node hijo = hijos.item(j);
                // Compruebo si es un nodo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Muestro el contenido
                    //horario[0][0] = hijo.getNodeName();
                    //horario[0][j] = hijo.getTextContent();
                    System.out.println("Propiedad: " + hijo.getNodeName()
                            + ", Valor: " + hijo.getTextContent());

                    if (hijo.getNodeName().equalsIgnoreCase("inicio")) {
                        horario[0] = hijo.getTextContent();
                    } else {
                        horario[1] = hijo.getTextContent();
                    }
                }

            }
            System.out.println("");
        }

        return horario;
    }

    public List<String> tagTrabajo(Node especialidad) {

        System.out.println("<========>DIas de Trabajo Laboratorista<========>");

        List<String> diasDeTrabajo = new ArrayList<>();
        Node nodo = especialidad;
        // Compruebo si el nodo es un elemento
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Lo transformo a Element
            Element e = (Element) nodo;
            // Obtengo sus hijos
            NodeList hijos = e.getChildNodes();
            // Recorro sus hijos
            for (int j = 0; j < hijos.getLength(); j++) {
                // Obtengo al hijo actual
                Node hijo = hijos.item(j);
                // Compruebo si es un nodo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Muestro el contenido

                    System.out.println("Propiedad: " + hijo.getNodeName()
                            + ", Valor: " + hijo.getTextContent());

                    diasDeTrabajo.add(hijo.getTextContent());

                }

            }
            System.out.println("");
        }

        return diasDeTrabajo;
    }

    public void crearPersona(Persona persona, String tag, String valor) {
        switch (tag.toUpperCase()) {

            case "DPI":
                persona.setDpi(valor);
                break;
            case "NOMBRE":
                persona.setNombre(valor);
                break;
            case "TELEFONO":
                persona.setTelefono(valor);
                break;
            case "CORREO":
                persona.setCorreo(valor);
                break;

        }
    }

    public void crearUsuario(Usuario usuario, String tag, String valor) {
        switch (tag.toUpperCase()) {
            case "CODIGO":

                usuario.setCodigo(valor);

                break;
            case "PASSWORD":

                usuario.setClave(valor);
                break;
            case "DPI":
                usuario.setPersonaDpi(valor);
                break;

        }
    }

    public void crearMedico(Medico medico, String tag, String value) {
        switch (tag.toUpperCase()) {
            case "COLEGIADO":
                medico.setColegiado(Integer.parseInt(value));
                break;
            case "TRABAJO":
                medico.setInicio(java.sql.Date.valueOf(value));
                break;

        }
    }

    public void crearLaboratorista(Laboratorista lab, String tag, String value) {

        switch (tag.toUpperCase().trim()) {

            case "REGISTRO":
                lab.setRegistro(value);
                break;
            case "TRABAJO":

                lab.setInicio(java.sql.Date.valueOf(value));

                System.out.println("inicio ===========++++++++++++++++++++========================> " + lab.getInicio().toString());
                break;
            //aun se debe agregar el tipo de examen que realizara
            //============================================================================================================================leer linea 
            default:

        }

    }

    public void crearPaciente(Paciente paciente, String tag, String value) {

        switch (tag.toUpperCase()) {
            case "CODIGO":

                paciente.setCodigo(value);
                break;
            case "SEXO":

                paciente.setMasculino(value);
                break;
            case "BIRTH":

                paciente.setFecha(java.sql.Date.valueOf(value));
                break;
            case "PESO":

                paciente.setPeso(value);
                break;

            case "SANGRE":

                paciente.setTipoDeSangre(value);
                break;

            default:

        }

    }

    public void crearExamen(Examen examen, String tag, String value) {

        switch (tag.toUpperCase()) {
            case "CODIGO":

                examen.setCodigo(value);
                break;
            case "NOMBRE":

                examen.setNombre(value);
                break;
            case "ORDEN":

                //
                if (value.equalsIgnoreCase("true")) {
                    examen.setOrden(true);
                } else if (value.equalsIgnoreCase("FALSE")) {
                    examen.setOrden(false);
                } else {
                    System.out.println("Lanzar error, no se reconoce token");//==========================================no se reconcoce si es verdadero o falso
                }
                break;
            case "DESCRIPCION":
                examen.setDescripcion(value);
                break;

            case "COSTO":

                examen.setCosto(Double.parseDouble(value));
                break;
            case "INFORME":

                examen.setFormato(value);
                break;

            default:

        }
    }

    public void crearInforme(Informe informe, String tag, String value) {

        switch (tag.toUpperCase()) {
            case "CODIGO":

                informe.setIdInforme(value);
                break;
            case "PACIENTE":
                //CONSULTA,VERIFICA QUE EL PACIENTE EXISTA DENTRO DE LA DB

                if (manager.getPacientesDAO().obtener(value) == null) {
                    System.out.println("Mostrar error el paciente no existe");
                } else {
//                    informe.setPacientes_codigo(value);
                }

                break;
            case "MEDICO":

                if (manager.getMedicoDAO().getMedicoByCodigoUsuario(value) == null) {
                    System.out.println("Mostrar error el paciente no existe");
                } else {
                    int colegiado = manager.getMedicoDAO().getMedicoByCodigoUsuario(value).getColegiado();
                    informe.setMedico_colegiado(colegiado);
                }

                break;
            case "INFORME":

                informe.setDescripcion(value);
                break;

            case "FECHA":
                informe.setFecha(java.sql.Date.valueOf(value));

                break;
            case "HORA":

                informe.setHora(java.sql.Time.valueOf(value + ":00"));

                break;

            default:

        }

    }

    public void crearResultado(Resultado informe, String tag, String value) {

        switch (tag.toUpperCase()) {
            case "CODIGO":

                informe.setResultadoCodigo(value);
                break;
            case "PACIENTE":
                //CONSULTA,VERIFICA QUE EL PACIENTE EXISTA DENTRO DE LA DB

                if (manager.getPacientesDAO().obtener(value) == null) {
                    System.out.println("Mostrar error el paciente no existe");
                } else {
                    informe.setPacientes_codigo(value);
                }

                break;
            case "MEDICO":

                if (manager.getMedicoDAO().obtener(Integer.parseInt(value)) == null) {
                    System.out.println("Mostrar error el paciente no existe");
                } else {
                    informe.setMedicoColegiado(Integer.parseInt(value));
                }

                break;
            case "EXAMEN":

                if (manager.getExamenDAO().obtener((value)) == null) {
                    System.out.println("Mostrar error el Examen no existe");
                } else {

                    informe.setExamen_Codigo(tag);
                }
                break;

            case "LABORATORISTA":

                if (manager.getLaboratoristasDAO().obtener((value)) == null) {
                    System.out.println("Mostrar error el paciente no existe");
                } else {
                    informe.setLaboratoristasRegistro((value));
                }

                break;
            case "FECHA":
                informe.setFechaHora(java.sql.Date.valueOf(value));

                break;
            case "ORDEN":

                //CARGAR IMAEN O ARCHIVO
                //====================================================================================================================================REVISAR
                break;

            case "INFORME":

                //CARGAR IMAEN O ARCHIVO
                //====================================================================================================================================REVISAR
                break;

            case "HORA":

                informe.setHora(java.sql.Time.valueOf(value + ":00"));

                break;

            default:

        }

    }

    public void crearConsulta(Consulta consulta, String tag, String value) {

        switch (tag.toUpperCase()) {
            case "TIPO":
                consulta.setTipo(value);

                break;
            case "COSTO":
                consulta.setCosto(Double.parseDouble(value));
                break;
            default:
                System.out.println("Valor no permitido");
        }
    }
}
