package com.hospital.loadxml;

import com.hospital.entities.Administrador;
import com.hospital.entities.Agenda;
import com.hospital.entities.Cita;
import com.hospital.entities.Consulta;
import com.hospital.entities.Dia;
import com.hospital.entities.DiaTrabajo;
import com.hospital.entities.Especialidad;
import com.hospital.entities.Examen;
import com.hospital.entities.ExamenLaboratorista;
import com.hospital.entities.ExamenPaciente;
import com.hospital.entities.Informe;
import com.hospital.entities.Laboratorista;
import com.hospital.entities.Medico;
import com.hospital.entities.Paciente;
import com.hospital.entities.Persona;
import com.hospital.entities.Resultado;
import com.hospital.entities.Usuario;
import com.hospital.mysql.Manager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class LoadSaxBuilder {

    List<String> insert;

    public static void main(String[] args) {
        LoadSaxBuilder l = new LoadSaxBuilder();
        l.readFile(new File("/home/julio/Documentos/IPC/Hospital/Proyecto2/data.xml"));
    }

    public LoadSaxBuilder() {
        this.insert = new ArrayList<String>();
        this.manager = new Manager();
    }

    public void readFile(File file) {

        try {
            SAXBuilder builder = new SAXBuilder();

            //File xml = new File("concesionario.xml");
            Document document = builder.build(file);
            Element root = document.getRootElement();
            
            List<Element> listAdmin = root.getChildren("admin");
            readAdmin(listAdmin);
             /*
            List<Element> list = root.getChildren("examen");
            readExamen(list);
            
           
            list =  root.getChildren("admin");
            readAdmin(list);
            

            list = root.getChildren("doctor");
            readDoctor(list);
            
            list = root.getChildren("laboratorista");
            readLaboratorista(list);
            
            list = root.getChildren("paciente");
            readPaciente(list);
            
            list = root.getChildren("cita");
            readCita(list);
            
            list = root.getChildren("consulta");
            readConsulta(list);
            
            
            
            list = root.getChildren("reporte");
            readInformeReporte(list);
            
            list = root.getChildren("resultado");
            readResultado(list);
            
             */
            

        } catch (JDOMException | IOException ex) {
            ex.printStackTrace();
        }

    }

    public void readAdmin(List<Element> listAdmin) {

        for (Element element : listAdmin) {
            
            
            List<Element> valores_element = element.getChildren();

            for (Element campo : valores_element) {


                
                String codigo = campo.getAttributeValue("CODIGO");
                String dpi = campo.getChildTextTrim("DPI");
                String nombre = campo.getChildTextTrim("NOMBRE");
                String clave = campo.getChildTextTrim("PASSWORD");
                switch (campo.getName()) {
                    case "CODIGO":
                        codigo = campo.getTextTrim();
                        break;
                    case "DPI":
                        dpi = campo.getTextTrim();
                        break;
                    case "NOMBRE":
                        nombre = campo.getTextTrim();
                        break;
                    case "PASSWORD":
                        clave = campo.getTextTrim();
                        break;
                    
                        
                }

                crearAdmin(codigo, dpi, nombre, clave);
            }
        }

    }

    public void readDoctor(List<Element> lstDoctor) {

        List<String> especialidades;
        List<String> horario;
        for (int i = 0; i < lstDoctor.size(); i++) {

            especialidades = new ArrayList<>();
            horario = new ArrayList<>();

            Element elements = lstDoctor.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo = campo.getAttributeValue("CODIGO");
                String nombre = campo.getChildTextTrim("NOMBRE");
                String colegiado = campo.getChildTextTrim("COLEGIADO");
                String dpi = campo.getChildTextTrim("DPI");
                String telefono = campo.getChildTextTrim("TELEFONO");
                String correo = campo.getChildTextTrim("CORREO");
                String trabajo = campo.getChildTextTrim("TRABAJO");
                String clave = campo.getChildTextTrim("PASSWORD");

                if (campo.getName().equalsIgnoreCase("Especialidad")) {
                    String[] values = campo.getValue().trim().split("\n");
                    for (String value : values) {
                        System.out.println("values: " + value.trim());
                        especialidades.add(value.trim());
                    }

                }
                if (campo.getName().equalsIgnoreCase("Horario")) {
                    String[] values = campo.getValue().trim().split("\n");
                    for (String value : values) {
                        System.out.println("values: " + value.trim() + ":00");
                        horario.add(value.trim() + ":00");
                    }
                }
                crearDoctor(codigo, nombre, colegiado, dpi, telefono, especialidades, correo, horario, trabajo, clave);

            }

        }

    }

    public void readLaboratorista(List<Element> laboratoristas) {

        List<String> horario;
        List<String> diaTrabajo;
        for (int i = 0; i < laboratoristas.size(); i++) {

            horario = new ArrayList<>();
            diaTrabajo = new ArrayList<>();

            Element elements = laboratoristas.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo = campo.getAttributeValue("CODIGO");
                String nombre = campo.getChildTextTrim("NOMBRE");
                String registro = campo.getChildTextTrim("REGISTRO");
                String dpi = campo.getChildTextTrim("DPI");
                String telefono = campo.getChildTextTrim("TELEFONO");
                String examen = campo.getChildTextTrim("EXAMEN");
                String correo = campo.getChildTextTrim("CORREO");
                String trabajoF = campo.getChildTextTrim("TRABAJOF");
                String clave = campo.getChildTextTrim("PASSWORD");

                if (campo.getName().equalsIgnoreCase("Horario")) {
                    String[] values = campo.getValue().trim().split("\n");
                    for (String value : values) {
                        System.out.println("values: " + value.trim() + ":00");
                        horario.add(value.trim() + ":00");
                    }
                }
                if (campo.getName().equalsIgnoreCase("TRABAJO")) {
                    String[] values = campo.getValue().trim().split("\n");
                    for (String value : values) {
                        System.out.println("values: " + value.trim() + ":00");
                        horario.add(value.trim() + ":00");
                    }
                }

                crearLaboratorista(codigo, nombre, registro, dpi, telefono, examen, correo, horario, diaTrabajo, trabajoF, clave);
            }

        }

    }

    public void readPaciente(List<Element> pacientes) {

        List<String> horario;
        List<String> diaTrabajo;
        for (int i = 0; i < pacientes.size(); i++) {

            horario = new ArrayList<>();
            diaTrabajo = new ArrayList<>();

            Element elements = pacientes.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo = campo.getAttributeValue("CODIGO");
                String nombre = campo.getChildTextTrim("NOMBRE");
                String sexo = campo.getChildTextTrim("SEXO");
                String nacimiento = campo.getChildTextTrim("BIRTH");
                String dpi = campo.getChildTextTrim("DPI");
                String telefono = campo.getChildTextTrim("TELEFONO");
                String peso = campo.getChildTextTrim("PESO");
                String sangre = campo.getChildTextTrim("SANGRE");
                String correo = campo.getChildTextTrim("CORREO");
                String clave = campo.getChildTextTrim("PASSWORD");

                if (campo.getName().equalsIgnoreCase("Horario")) {
                    String[] values = campo.getValue().trim().split("\n");
                    for (String value : values) {
                        System.out.println("values: " + value.trim() + ":00");
                        horario.add(value.trim() + ":00");
                    }
                }
                if (campo.getName().equalsIgnoreCase("TRABAJO")) {
                    String[] values = campo.getValue().trim().split("\n");
                    for (String value : values) {
                        System.out.println("values: " + value.trim());
                        diaTrabajo.add(value.trim());
                    }
                }

                crearPaciente(codigo, nombre, sexo, nacimiento, dpi, telefono, peso, sangre, correo, clave);
            }

        }

    }

    public void readExamen(List<Element> examenes) {

        for (int i = 0; i < examenes.size(); i++) {

            Element elements = examenes.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo = campo.getAttributeValue("CODIGO");
                String nombre = campo.getChildTextTrim("NOMBRE");
                String orden = campo.getChildTextTrim("ORDEN");
                String descripcion = campo.getChildTextTrim("DESCRIPCION");
                String costo = campo.getChildTextTrim("COSTO");
                String informe = campo.getChildTextTrim("INFORME");
                
                crearExamen(codigo, nombre, orden, descripcion, costo, informe);

            }

        }
    }

    public void readInformeReporte(List<Element> informeReporte) {

        for (int i = 0; i < informeReporte.size(); i++) {

            Element elements = informeReporte.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo = campo.getAttributeValue("CODIGO");
                String paciente = campo.getChildTextTrim("PACIENTE");
                String medico = campo.getChildTextTrim("MEDICO");
                String informe = campo.getChildTextTrim("INFORME");
                String fecha = campo.getChildTextTrim("FECHA");
                String hora = campo.getChildTextTrim("HORA");
                
                crearInforme(codigo, paciente, medico, informe, fecha, hora);

            }

        }
    }

    public void readResultado(List<Element> resultado) {

        for (int i = 0; i < resultado.size(); i++) {

            Element elements = resultado.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo = campo.getAttributeValue("CODIGO");
                String paciente = campo.getChildTextTrim("PACIENTE");
                String medico = campo.getChildTextTrim("MEDICO");
                String examen = campo.getChildTextTrim("EXAMEN");
                String laborat = campo.getChildTextTrim("LABORATORISTA");
                String orden = campo.getChildTextTrim("ORDEN");
                String informe = campo.getChildTextTrim("INFORME");
                String fecha = campo.getChildTextTrim("FECHA");
                String hora = campo.getChildTextTrim("HORA");
                
                crearResultado(codigo, paciente, medico, examen, laborat, informe, fecha, hora, orden);

            }

        }
    }

    public void readCita(List<Element> citas) {

        for (int i = 0; i < citas.size(); i++) {

            Element elements = citas.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String codigo   = campo.getAttributeValue("CODIGO");
                String paciente = campo.getChildTextTrim("PACIENTE");
                String medico   = campo.getChildTextTrim("MEDICO");
                String especial = campo.getChildTextTrim("ESPECIALIDAD");
                String fecha    = campo.getChildTextTrim("FECHA");
                String hora     = campo.getChildTextTrim("HORA");

                crearCita(codigo, paciente, medico, especial, fecha, hora);
            }

        }
    }

    public void readConsulta(List<Element> consultas) {

        for (int i = 0; i < consultas.size(); i++) {

            Element elements = consultas.get(i);

            List<Element> valores_elements = elements.getChildren();

            for (int j = 0; j < valores_elements.size(); j++) {

                Element campo = valores_elements.get(j);

                String tipo = campo.getAttributeValue("TIPO");
                String costo = campo.getChildTextTrim("COSTO");
                crearConsulta(tipo, Double.parseDouble(costo));

            }

        }
    }

    public void crearAdmin(String codigo, String dpi, String nombre, String clave) {
//String dpi, String nombre, String telefono, String correo, boolean estado) {
        Persona persona = new Persona(dpi, nombre, "", "", true);
        Usuario usuario = new Usuario(codigo, clave, true, dpi);
        Administrador admin = new Administrador(codigo, dpi);

        boolean pers = (manager.getPersonaDAO().obtener(dpi) == null);
        boolean usu = (manager.getUsuarioDAO().obtener(codigo) == null);
        boolean adminBool = (manager.getAdministradorDAO().obtener(codigo) == null);

        if (pers && usu && adminBool) {

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getAdministradorDAO().insert(admin);

        }

    }

    public void crearDoctor(String codigo, String nombre, String colegiado, String dpi, String telefono, List<String> especialidades, String correo, List<String> horario, String fecha, String password) {
        Persona persona = new Persona(dpi, nombre, telefono, correo, true);
        Usuario usuario = new Usuario(codigo, password, true, dpi);
        Medico medico = new Medico(Integer.parseInt(colegiado), java.sql.Date.valueOf(fecha), true, dpi, java.sql.Time.valueOf(horario.get(0)), java.sql.Time.valueOf(horario.get(1)));

        boolean pers = (manager.getPersonaDAO().obtener(dpi) == null);
        boolean usu = (manager.getUsuarioDAO().obtener(codigo) == null);
        boolean medBool = manager.getMedicoDAO().obtener(medico.getColegiado()) == null;

        if (pers && usu && medBool) {

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getMedicoDAO().insert(medico);
            manager.getAgendaDAO().insert(new Agenda(0, "1", medico.getColegiado()));

            for (String especialidade : especialidades) {
                Especialidad es = new Especialidad(0, especialidade, true, medico.getColegiado());

                manager.getEspecialidadDAO().insert(es);
            }

        }

    }

    public void crearLaboratorista(String codigo, String nombre, String registro, String dpi, String telefono, String examen, String correo, List<String> horario, List<String> trabajo, String fecha, String password) {

        Persona persona = new Persona(dpi, nombre, telefono, correo, true);
        Usuario usuario = new Usuario(codigo, password, true, dpi);
        Laboratorista lab = new Laboratorista(registro, java.sql.Date.valueOf(fecha), "0", "1", dpi);
        Examen asignarExamen = manager.getExamenDAO().getExamenByName(examen);

        boolean pers = (manager.getPersonaDAO().obtener(dpi) == null);
        boolean usu = (manager.getUsuarioDAO().obtener(codigo) == null);
        boolean medBool = manager.getLaboratoristasDAO().obtener(registro) == null;

        if (pers && usu && medBool) {

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getLaboratoristasDAO().insert(lab);
            manager.getAgendaDAO().insert(new Agenda(0, "1", registro));

            if (asignarExamen != null) {
                    ExamenLaboratorista examenLaboratorista = new ExamenLaboratorista(0, asignarExamen.getCodigo(), lab.getRegistro(), true);
                manager.getExamenLaboratoristaDAO().insert(examenLaboratorista);
            }

            for (String string : trabajo) {
                DiaTrabajo diaTrabajo;
                switch (string.toUpperCase()) {
                    case "LUNES":
                        diaTrabajo = new DiaTrabajo(0, 1, lab.getRegistro(), true);
                        break;
                    case "MARTES":
                        diaTrabajo = new DiaTrabajo(0, 2, lab.getRegistro(), true);
                        break;
                    case "MIERCOLES":
                        diaTrabajo = new DiaTrabajo(0, 3, lab.getRegistro(), true);
                        break;
                    case "JUEVES":
                        diaTrabajo = new DiaTrabajo(0, 4, lab.getRegistro(), true);

                        break;
                    case "VIERNES":
                        diaTrabajo = new DiaTrabajo(0, 5, lab.getRegistro(), true);
                        break;
                    case "SABADO":
                        diaTrabajo = new DiaTrabajo(0, 6, lab.getRegistro(), true);
                        break;
                    case "DOMINGO":
                        diaTrabajo = new DiaTrabajo(0, 7, lab.getRegistro(), true);
                        break;
                    default:
                        diaTrabajo = new DiaTrabajo(0, 7, lab.getRegistro(), true);
                }
                manager.getDiaTrabajoDAO().insert(diaTrabajo);
            }

        }

    }

    public void crearPaciente(String codigo, String nombre, String sexo, String birth, String dpi, String telefono, String peso, String sangre, String correo, String password) {

        Persona persona = new Persona(dpi, nombre, telefono, correo, true);
        Usuario usuario = new Usuario(codigo, password, true, dpi);
        Paciente pacien = new Paciente(codigo, sexo, java.sql.Date.valueOf(birth), peso, true, sangre, dpi);

        boolean pers = (manager.getPersonaDAO().obtener(dpi) == null);
        boolean usu = (manager.getUsuarioDAO().obtener(codigo) == null);
        boolean medBool = manager.getPacientesDAO().obtener(codigo) == null;

        if (pers && usu && medBool) {

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getPacientesDAO().insert(pacien);

        }
    }

    public void crearExamen(String codigo, String nombre, String orden, String descripcion, String costo, String informe) {

        Examen examen = new Examen(codigo, nombre, orden.equalsIgnoreCase("TRUE"), descripcion, Double.parseDouble(costo), informe, "1");
        if (!(manager.getExamenDAO().obtener(codigo) == null)) {

            manager.getExamenDAO().insert(examen);

        }
    }

    /**
     *
     * @param codigo identificador del informe
     * @param paciente codigo identificador del paciente
     * @param medico codigo del medico, no es colegiado
     * @param informeRedactado redactado por el medico
     * @param fecha fecha en que se realizo la consulta
     * @param hora hora en la que se realizo la consulta
     */
    public void crearInforme(String codigo, String paciente, String medico, String informeRedactado, String fecha, String hora) {

        Medico medic = manager.getMedicoDAO().getMedicoByCodigoUsuario(codigo);
        Agenda agenda = manager.getAgendaDAO().getAgendaByColegido(medic.getColegiado() + "");

        Cita yaVencida = new Cita(codigo, "Cita Creada, para un Resultado, que se encontro en la carga de archivo", "1", paciente);
        Dia paraLaCita = new Dia(0, java.sql.Date.valueOf(fecha), "dia carga de archivo, --", agenda.getCodigo(), yaVencida.getCodigo(), java.sql.Time.valueOf(hora));

        Informe inf = new Informe(codigo, informeRedactado, java.sql.Date.valueOf(fecha), medic.getColegiado(), true, java.sql.Time.valueOf("hora"), paraLaCita.getCita_codigo());

        manager.getCitaDAO().insert(yaVencida);
        manager.getDiaDAO().insert(paraLaCita);
        manager.getInformeDAO().insert(inf);

    }

    public void crearResultado(String codigo, String idPaciente, String codeMedico, String idExamen, String codeLab, String informePath, String fecha, String hora, String ordenPath) {

        Laboratorista lab = manager.getLaboratoristasDAO().getLabByCodeUsr(codeLab);
        Examen examen = manager.getExamenDAO().obtener(idExamen);
        Paciente paciente = manager.getPacientesDAO().obtener(idPaciente);
        Medico medico = null;
        Resultado rsultad = null;
        ExamenPaciente exPac = null;
        

        if (lab != null && examen != null && paciente != null) {
            
        
            Agenda agenda = manager.getAgendaDAO().getAgendaByRegistro(lab.getRegistro());
            Cita cita = new Cita(codigo, "Desde la Carga de archivos", "1", paciente.getCodigo());
            Dia dia = new Dia(0, fecha, "Desde la Carga De archivos", agenda.getCodigo(), cita.getCodigo(), hora);

            if (examen.isOrden()) {

                rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), ordenPath);
                if (codeMedico != null && !codeMedico.equals("")) {

                    medico = manager.getMedicoDAO().getMedicoByCodigoUsuario(codeMedico);

                }

            }
            
            rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), ordenPath);
            exPac = new ExamenPaciente(0, rsultad.getResultadoCodigo(), examen.getCodigo()  , lab.getRegistro(), paciente.getCodigo(), medico.getColegiado(), true, true, false);
            
            manager.getCitaDAO().insert(cita);
            manager.getDiaDAO().insert(dia);
            manager.getResultadoDAO().insert(rsultad);
            manager.getExamenPacienteDAO().insert(exPac);
            
        }
        
        
        
        
    }
    public void crearCita(String codigo, String paciente,String medico,String especialidad,String fecha, String hora){
        
        Consulta consulta = manager.getConsultaDAO().getCOnsultaByTipo(especialidad);
        //insertar primero la cita
        Cita cita = new Cita(codigo, paciente, hora, paciente, consulta.getIdConsulta());
        Agenda agenda = manager.getAgendaDAO().obtenerAgendaMedica(medico);
        Dia dia = new Dia(0, fecha, "Cita Pendiente, desde: Archivo.xml", agenda.getCodigo(), cita.getCodigo(), hora);
        
        manager.getCitaDAO().insert(cita);
        manager.getDiaDAO().insert(dia);
    }
    public void crearConsulta(String tipo, double costo){
        Consulta consulta = new Consulta(0, tipo, costo, "1");
        manager.getConsultaDAO().insert(consulta);
    }

    private Manager manager;

}
