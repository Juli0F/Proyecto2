/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.loadxml;

import com.hospital.entities.Administrador;
import com.hospital.entities.Medico;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Paciente;
import com.hospital.entities.Persona;
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

    public static void main(String[] args) {
        Interprete interprete = new Interprete();
        interprete.loadFile();
    }

    public Interprete() {
        this.manager = new Manager();
    }

    public void loadFile() {

        String path = "/home/julio/Documentos/IPC/Hospital/Proyecto2/data.xml";

        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File(path));

            // Cojo todas las etiquetas admin del documento
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
            System.out.println(ex.getMessage());
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
                manager.getPersonaDAO().insert(persona);
                manager.getUsuarioDAO().insert(usuario);
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

        for (int i = 0; i < listaDoctor.getLength(); i++) {

            persona = new Persona();
            medico = new Medico();
            usuario = new Usuario();

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

                        if (hijo.getNodeName().equalsIgnoreCase("horario")) {

                            horario = tagHorario(hijo);

                        } else if (hijo.getNodeName().equalsIgnoreCase("especialidad")) {
                            tagEspecialidad(hijo);

                        } else {

                            crearPersona(persona, hijo.getNodeName(), hijo.getTextContent());
                            crearMedico(medico, hijo.getNodeName(), hijo.getTextContent());

                        }
                    }

                }
                System.out.println("");
            }

        }
    }

    public void tagLaboratorista(NodeList listadoLaboratorista) {
        // Recorro las etiquetas
        System.out.println(" <========>Laboratorista");
        for (int i = 0; i < listadoLaboratorista.getLength(); i++) {
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

                        } else {
                            //dias que trabaja
                            tagTrabajo(hijo);
                        }
                    }

                }
                System.out.println("");
            }

        }
    }

    public static void tagPaciente(NodeList listadoPaciente) {
        // Recorro las etiquetas
        System.out.println(" <========>Paciente");
        for (int i = 0; i < listadoPaciente.getLength(); i++) {
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

                    }

                }
                System.out.println("");
            }

        }
    }

    public static void tagExamen(NodeList listadoExamen) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        for (int i = 0; i < listadoExamen.getLength(); i++) {
            // Cojo el nodo actual
            Node nodo = listadoExamen.item(i);
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

                    }

                }
                System.out.println("");
            }

        }
    }

    public static void tagInforme(NodeList listadoInforme) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        for (int i = 0; i < listadoInforme.getLength(); i++) {
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

                    }

                }
                System.out.println("");
            }

        }
    }

    public static void tagResultado(NodeList listadoResultado) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        for (int i = 0; i < listadoResultado.getLength(); i++) {
            // Cojo el nodo actual
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

                    }

                }
                System.out.println("");
            }

        }
    }

    public static void tagCita(NodeList listadoDeCitas) {
        // Recorro las etiquetas
        System.out.println(" <========>Examen");
        for (int i = 0; i < listadoDeCitas.getLength(); i++) {
            // Cojo el nodo actual
            Node nodo = listadoDeCitas.item(i);
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

                    }

                }
                System.out.println("");
            }

        }
    }

    public static void tagConsulta(NodeList listadoDeConsultas) {
        // Recorro las etiquetas
        System.out.println(" <========>Consultas");
        for (int i = 0; i < listadoDeConsultas.getLength(); i++) {
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
            case "CLAVE":

                usuario.setClave(valor);
                break;
            case "DPI":
                usuario.setPersonaDpi(valor);
                break;
            default:
                throw new AssertionError();
        }
    }

    public void crearMedico(Medico medico, String tag, String value) {
        switch (tag.toUpperCase()) {
            case "COLEGIADO":
                medico.setColegiado(Integer.parseInt(value));
                break;
            case "TRABAJO":
                medico.setInicio(value);
                break;

        }
    }
    public void crearLaboratorista(Laboratorista lab, String tag, String value){
        
        switch (tag.toUpperCase()) {
            case "REGISTRO":
                lab.setRegistro(Integer.parseInt(value));
                break;
            case "TRABAJO":
                
                lab.setInicio(Date.valueOf(value));
                
                break;
            default:
                
        }
        
        
    }

        public void crearPaciente(Paciente paciente, String tag, String value){
        
        switch (tag.toUpperCase()) {
            case "CODIGO":
                
                paciente.setCodigo(Integer.parseInt(value));
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
        
        

}
