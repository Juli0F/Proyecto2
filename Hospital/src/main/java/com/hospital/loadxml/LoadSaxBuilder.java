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
        // System.out.println("Hora "+ java.sql.Time.valueOf("10:00:00"));
        LoadSaxBuilder l = new LoadSaxBuilder();
        l.readFile("/home/julio/Documentos/IPC/Hospital/Proyecto2/data.xml");
    }

    public LoadSaxBuilder() {
        this.insert = new ArrayList<String>();
        this.manager = new Manager();
    }

    public void readFile(String path) {

        try {

            SAXBuilder builder = new SAXBuilder();

            //File xml = new File("concesionario.xml");
            Document document = builder.build(new File(path));
            Element root = document.getRootElement();

            List<Element> list = root.getChildren("examen");
            readExamen(list);

            list = root.getChildren("admin");
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

        } catch (JDOMException | IOException ex) {
            ex.printStackTrace();
        }

    }

    public void readAdmin(List<Element> listAdmin) {

        for (Element element : listAdmin) {

            String codigo = element.getChildTextTrim("CODIGO");
            String dpi = element.getChildTextTrim("DPI");
            String nombre = element.getChildTextTrim("NOMBRE");
            String clave = element.getChildTextTrim("PASSWORD");

            System.out.println("Codigo: " + codigo);
            System.out.print(" Dpi: " + dpi);
            System.out.print(" Nombre: " + nombre);
            System.out.print(" Password: " + clave);

            crearAdmin(codigo, dpi, nombre, clave);

        }

    }

    public void readDoctor(List<Element> lstDoctor) {

        List<String> especialidades;
        List<String> horario;
        for (Element element : lstDoctor) {

            especialidades = new ArrayList<>();
            horario = new ArrayList<>();

            String codigo = element.getChildTextTrim("CODIGO");
            String nombre = element.getChildTextTrim("NOMBRE");
            String colegiado = element.getChildTextTrim("COLEGIADO");
            String dpi = element.getChildTextTrim("DPI");
            String telefono = element.getChildTextTrim("TELEFONO");
            String correo = element.getChildTextTrim("CORREO");
            String trabajo = element.getChildTextTrim("TRABAJO");
            String clave = element.getChildTextTrim("PASSWORD");

            List<Element> list = element.getChildren("ESPECIALIDAD");

            for (Element element1 : list) {
                String[] especialidad = element1.getValue().trim().split("\n");
                for (String dia : especialidad) {
                    especialidades.add(dia.trim());
                }
                //diaTrabajo.add(element1.getChildTextTrim("DIA"));
            }

            list = element.getChildren("HORARIO");
            for (Element element1 : list) {
                horario.add(element1.getChildTextTrim("INICIO") + ":00");
                horario.add(element1.getChildTextTrim("FIN") + ":00");

            }

            System.out.println("Horario : " + horario.size());
            System.out.println("Especialidades :" + especialidades.size());
            crearDoctor(codigo, nombre, colegiado, dpi, telefono, especialidades, correo, horario, trabajo, clave);

        }

    }

    public void readLaboratorista(List<Element> laboratoristas) {

        List<String> horario;
        List<String> diaTrabajo;

        for (Element element : laboratoristas) {
            horario = new ArrayList<>();
            diaTrabajo = new ArrayList<>();

            String codigo = element.getChildTextTrim("CODIGO");
            String nombre = element.getChildTextTrim("NOMBRE");
            String registro = element.getChildTextTrim("REGISTRO");
            String dpi = element.getChildTextTrim("DPI");
            String telefono = element.getChildTextTrim("TELEFONO");
            String examen = element.getChildTextTrim("EXAMEN");
            String correo = element.getChildTextTrim("CORREO");
            String trabajoF = element.getChildTextTrim("TRABAJOF");
            String clave = element.getChildTextTrim("PASSWORD");

            List<Element> lst = element.getChildren("HORARIO");

            for (Element element1 : lst) {
                horario.add(element1.getChildTextTrim("INICIO") + ":00");
                horario.add(element1.getChildTextTrim("FIN") + ":00");
            }

            lst = element.getChildren("TRABAJO");

            for (Element element1 : lst) {
                String[] dias = element1.getValue().trim().split("\n");
                for (String dia : dias) {
                    diaTrabajo.add(dia.trim());
                }

            }

            crearLaboratorista(codigo, nombre, registro, dpi, telefono, examen, correo, horario, diaTrabajo, trabajoF, clave);
        }
    }

    public void readPaciente(List<Element> pacientes) {

        List<String> horario;
        List<String> diaTrabajo;

        for (Element element : pacientes) {

            horario = new ArrayList<>();
            diaTrabajo = new ArrayList<>();

            String codigo = element.getChildTextTrim("CODIGO");
            String nombre = element.getChildTextTrim("NOMBRE");
            String sexo = element.getChildTextTrim("SEXO");
            String nacimiento = element.getChildTextTrim("BIRTH");
            String dpi = element.getChildTextTrim("DPI");
            String telefono = element.getChildTextTrim("TELEFONO");
            String peso = element.getChildTextTrim("PESO");
            String sangre = element.getChildTextTrim("SANGRE");
            String correo = element.getChildTextTrim("CORREO");
            String clave = element.getChildTextTrim("PASSWORD");

            if (element.getName().equalsIgnoreCase("Horario")) {
                String[] values = element.getValue().trim().split("\n");
                for (String value : values) {
                    System.out.println("values: " + value.trim() + ":00");
                    horario.add(value.trim() + ":00");
                }
            }
            if (element.getName().equalsIgnoreCase("TRABAJO")) {
                String[] values = element.getValue().trim().split("\n");
                for (String value : values) {
                    System.out.println("values: " + value.trim());
                    diaTrabajo.add(value.trim());
                }
            }

            crearPaciente(codigo, nombre, sexo, nacimiento, dpi, telefono, peso, sangre, correo, clave);
        }

    }

    public void readExamen(List<Element> examenes) {
        for (Element element : examenes) {

            String codigo = element.getChildTextTrim("CODIGO");
            String nombre = element.getChildTextTrim("NOMBRE");
            String orden = element.getChildTextTrim("ORDEN");
            String descripcion = element.getChildTextTrim("DESCRIPCION");
            String costo = element.getChildTextTrim("COSTO");
            String informe = element.getChildTextTrim("INFORME");

            if (descripcion.length() > 1999) {
                descripcion = descripcion.substring(0, 1999);

            }
            System.out.println("codigo " + codigo + " nombre: " + nombre + " Ordend:" + orden + " descripcion" + descripcion + " Costo: " + costo + " informe" + informe);
            crearExamen(codigo, nombre, orden, descripcion, costo, informe);

        }

    }

    public void readInformeReporte(List<Element> informeReporte) {

        for (Element element : informeReporte) {

            String codigo = element.getChildTextTrim("CODIGO");
            String paciente = element.getChildTextTrim("PACIENTE");
            String medico = element.getChildTextTrim("MEDICO");
            String informe = element.getChildTextTrim("INFORME");
            String fecha = element.getChildTextTrim("FECHA");
            String hora = element.getChildTextTrim("HORA");

            crearInforme(codigo, paciente, medico, informe, fecha, hora);

        }
    }

    public void readResultado(List<Element> resultado) {

        for (Element element : resultado) {

            String codigo = element.getChildTextTrim("CODIGO");
            String paciente = element.getChildTextTrim("PACIENTE");
            String medico = element.getChildTextTrim("MEDICO");
            String examen = element.getChildTextTrim("EXAMEN");
            String laborat = element.getChildTextTrim("LABORATORISTA");
            String orden = element.getChildTextTrim("ORDEN");
            String informe = element.getChildTextTrim("INFORME");
            String fecha = element.getChildTextTrim("FECHA");
            String hora = element.getChildTextTrim("HORA") + ":00";

            System.out.println("codigo: " + codigo + " paciente: " + paciente + "Medico: " + medico + "examen : " + examen + "laboratorista" + laborat + "informe" + informe + "fecha" + fecha + "hora" + hora + " orden" + orden);
            crearResultado(codigo, paciente, medico, examen, laborat, informe, fecha, hora, orden);

        }
    }

    public void readCita(List<Element> citas) {

        for (Element element : citas) {

            String codigo = element.getChildTextTrim("CODIGO");
            String paciente = element.getChildTextTrim("PACIENTE");
            String medico = element.getChildTextTrim("MEDICO");
            String especial = element.getChildTextTrim("ESPECIALIDAD");
            String fecha = element.getChildTextTrim("FECHA");
            String hora = element.getChildTextTrim("HORA") + ":00";

            //  System.out.println("Cita:  codigo: "+codigo+" paciente: "+paciente+" medico: "+medico+" especial: "+especial+"fecha: "+fecha+" hora: "+hora);
            crearCita(codigo, paciente, medico, especial, fecha, hora);
        }

    }

    public void readConsulta(List<Element> consultas) {

        for (Element element : consultas) {

            String tipo = element.getChildTextTrim("TIPO");
            String costo = element.getChildTextTrim("COSTO");
            crearConsulta(tipo, Double.parseDouble(costo));

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
            manager.getAgendaDAO().insert(new Agenda(0, "1", "" + medico.getColegiado()));

            for (String especialidade : especialidades) {
                Especialidad es = new Especialidad(0, especialidade, true, medico.getColegiado());

                manager.getEspecialidadDAO().insert(es);
            }

        }

    }

    public void crearLaboratorista(String codigo, String nombre, String registro, String dpi, String telefono, String examen, String correo, List<String> horario, List<String> trabajo, String fecha, String password) {

        Persona persona = new Persona(dpi, nombre, telefono, correo, true);
        Usuario usuario = new Usuario(codigo, password, true, dpi);
        System.out.println("Fecha" + fecha);
        Laboratorista lab = new Laboratorista(registro, java.sql.Date.valueOf(fecha), "0", "1", dpi);
        Examen asignarExamen = manager.getExamenDAO().getExamenByName(examen);

        boolean pers = (manager.getPersonaDAO().obtener(dpi) == null);
        boolean usu = (manager.getUsuarioDAO().obtener(codigo) == null);
        boolean medBool = manager.getLaboratoristasDAO().obtener(registro) == null;

        if (pers && usu && medBool) {

            manager.getPersonaDAO().insert(persona);
            manager.getUsuarioDAO().insert(usuario);
            manager.getLaboratoristasDAO().insert(lab);

            Agenda agenda = new Agenda(0, "1", registro, 0);

            manager.getAgendaDAO().insertLab(agenda);

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

        if ((manager.getExamenDAO().obtener(codigo) == null)) {

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

        if (medic != null) {

            Agenda agenda = manager.getAgendaDAO().getAgendaByColegido(medic.getColegiado() + "");

            Cita yaVencida = new Cita(codigo, "Cita Creada, para un Resultado, que se encontro en la carga de archivo", "1", paciente);
            Dia paraLaCita = new Dia(0, java.sql.Date.valueOf(fecha), "dia carga de archivo, --", agenda.getCodigo(), yaVencida.getCodigo(), java.sql.Time.valueOf(hora));

            manager.getCitaDAO().insert(yaVencida);
            String codeCita = manager.getCitaDAO().lastInsertId();

            manager.getDiaDAO().insert(paraLaCita);

            Informe inf = new Informe(codigo, informeRedactado, java.sql.Date.valueOf(fecha), medic.getColegiado(), true, java.sql.Time.valueOf("hora"), codeCita);

            manager.getInformeDAO().insert(inf);
        }

    }

    public void crearResultado(String codigo, String idPaciente, String codeMedico, String idExamen, String codeLab, String informePath, String fecha, String hora, String ordenPath) {

        System.out.println("codigo: " + codigo + " paciente: " + idPaciente + "Medico: " + codeMedico + "examen : " + idExamen + "laboratorista" + codeLab + "informe" + informePath + "fecha" + fecha + "hora" + hora + " orden" + ordenPath);

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

                    rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), ordenPath);
                    exPac = new ExamenPaciente(0, rsultad.getResultadoCodigo(), examen.getCodigo(), lab.getRegistro(), paciente.getCodigo(), medico.getColegiado() + "", true, true, false);

                } else {
                    rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), ordenPath);
                    exPac = new ExamenPaciente(0, codigo, examen.getCodigo(), lab.getRegistro(), paciente.getCodigo(), null, true, true, false);
                }

            }else{
                rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), ordenPath);
                if (codeMedico != null && !codeMedico.equals("")) {

                    medico = manager.getMedicoDAO().getMedicoByCodigoUsuario(codeMedico);

                    rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), "");
                    exPac = new ExamenPaciente(0, rsultad.getResultadoCodigo(), examen.getCodigo(), lab.getRegistro(), paciente.getCodigo(), medico.getColegiado() + "", true, true, false);

                } else {
                    rsultad = new Resultado(codigo, informePath, java.sql.Date.valueOf(fecha), true, java.sql.Time.valueOf(hora), "");
                    exPac = new ExamenPaciente(0, codigo, examen.getCodigo(), lab.getRegistro(), paciente.getCodigo(), null, true, true, false);
                }
            }

            manager.getCitaDAO().insertSinConsulta(cita);
            
            String idCita = manager.getCitaDAO().lastInsertId();
            dia.setCitaCodigo(idCita);
            
            manager.getDiaDAO().insert(dia);
            manager.getResultadoDAO().insert(rsultad);
            manager.getExamenPacienteDAO().insert(exPac);

        }

    }

    public void crearCita(String codigo, String paciente, String medico, String especialidad, String fecha, String hora) {

        Consulta consulta;
        if (especialidad != null) {

            consulta = manager.getConsultaDAO().getCOnsultaByTipo(especialidad);
            //consulta = new Consulta(1, "Auxiliar", 0.0, hora);
        } else {
            consulta = new Consulta(1, "Auxiliar", 0.0, "1");
        }

        if (manager.getPacientesDAO().obtener(paciente) != null) {

            Cita cita = new Cita(codigo, "Cita Desde Archivo", "PENDIENTE", paciente, consulta.getIdConsulta());
            Agenda agenda = manager.getAgendaDAO().obtenerAgendaMedica(medico);
            //public Dia (int idDia, String fecha, String descripcion,  int Agenda_codigo,  String Cita_codigo, String hora) {
            System.out.println("Dia: " + 0 + "fecha" + fecha + "Agenda: " + agenda.getCodigo() + "Cita " + cita.getCodigo() + " Hora " + hora);

            Dia dia = new Dia(0, fecha, "PENDIENTE", agenda.getCodigo(), cita.getCodigo(), hora);

            Dia citaEnDia = manager.getDiaDAO().searchCoincidenceByDateHourAndAgenda(dia.getFecha(), dia.getHora(), agenda.getCodigo());

            if ((citaEnDia == null || citaEnDia.getDescripcion().equalsIgnoreCase("CANCELADA"))) {

                manager.getCitaDAO().insert(cita);

                dia.setCitaCodigo((manager.getCitaDAO().lastInsertId()));
                System.out.println("codigo Cita" + dia.getCita_codigo());
                manager.getDiaDAO().insert(dia);

            } else {
                insert.add("No se Guardo cita: " + codigo + " Paciente " + paciente);
            }

        } else {
            insert.add("No se puede insertar citar porque, " + paciente + "No existe, ");
        }

    }

    public void crearConsulta(String tipo, double costo) {
        Consulta consulta = new Consulta(0, tipo, costo, "1");
        manager.getConsultaDAO().insert(consulta);
    }

    private Manager manager;

}
