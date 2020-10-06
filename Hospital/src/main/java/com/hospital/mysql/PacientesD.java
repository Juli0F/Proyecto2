package com.hospital.mysql;

import com.hospital.dao.PacientesDAO;
import com.hospital.dto.ExamenIntervalo;
import com.hospital.dto.ExamenMedico;
import com.hospital.dto.PacienteHistorial;
import com.hospital.dto.Ultimos;
import com.hospital.entities.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacientesD implements PacientesDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Pacientes (masculino,fecha,peso,estado,tipo_de_sangre,Persona_dpi,codigo) VALUES (?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Pacientes set masculino = ?,fecha = ?,peso = ?,estado = ?,tipo_de_sangre = ?,Persona_dpi = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Pacientes WHERE codigo = ? ";
    private final String GET_ALL = "SELECT * FROM  Pacientes  ";
    private final String GETONE = GET_ALL + "WHERE codigo = ?";
    private final String GET_ADMIN_BY_CODE_AND_PWD = "select * from Usuario u inner join Pacientes a on u.Persona_dpi = a.Persona_dpi where u.codigo = ? AND u.clave = ?";
    private final String GET_ALL_WHIT_PERSONA = "select pac.codigo , per.nombre as nombre, pac.masculino, pac.fecha, pac.peso, pac.tipo_de_sangre as sangre from Pacientes pac inner join Persona per on pac.Persona_dpi = per.dpi";
    private final String GET_LAST_EXAMENES_BY_CODE_USER = "select e.Codigo, e.nombre, d.fecha , expac.idExamenPaciente from ExamenPaciente expac "
            + "inner join Examen e on expac.Examen_Codigo = e.Codigo "
            + "inner join Agenda a on expac.Laboratoristas_registro = a.Laboratoristas_registro "
            + "inner join Dia d    on d.Agenda_codigo = a.codigo "
            + "WHERE  expac.Pacientes_codigo = ?  and expac.realizado = '1' "
            + "group by expac.idExamenPaciente order by d.fecha desc limit 5";

    private final String GET_EXAMEN_BY_TIPO_AND_CODE_PACIENTE = "select e.Codigo as codigo, e.nombre as nombre ,lab.registro as registro,per.nombre as lab, d.fecha , expac.idExamenPaciente from ExamenPaciente expac  "
            + "inner join Examen e on expac.Examen_Codigo = e.Codigo  "
            + "inner join Agenda a on expac.Laboratoristas_registro = a.Laboratoristas_registro "
            + "inner join Dia d    on d.Agenda_codigo = a.codigo  "
            + "inner join Laboratoristas lab on lab.registro = a.Laboratoristas_registro "
            + "inner join Persona  per on per.dpi =lab.Persona_dpi "
            + "where e.nombre = ? and d.fecha between ifnull(?,'1970-01-01') and ifnull(?,'3000-01-01') and expac.Pacientes_codigo = ? "
            + "group by expac.idExamenPaciente order by d.fecha desc    ";

    private final String GET_ULTIAMS_CONSULTAS = "select co.tipo as Codigo, d.fecha , d.hora as nombre from Cita c  "
            + "inner join Dia d on d.Cita_codigo = c.codigo "
            + "inner join Consulta co on co.idConsulta = c.Consulta_idConsulta "
            + "where c.Pacientes_codigo = ? "
            + "group by c.codigo order by d.fecha desc limit 5";

    private final String GET_CONSULTAS_BY_MEDICO_INTERVALO_FECHAS = "select co.tipo as consulta, d.fecha,d.hora,per.nombre  from Cita c "
            + "inner join Dia d on d.Cita_codigo = c.codigo "
            + "inner join Consulta co on co.idConsulta = c.Consulta_idConsulta "
            + "inner join Agenda a on a.codigo = d.Agenda_codigo "
            + "inner join Medico m on a.Medico_colegiado = m.colegiado "
            + "inner join Persona per on per.dpi = m.Persona_dpi "
            + "where c.Pacientes_codigo = ? and per.nombre like ?"
            + "and d.fecha between ifnull(?,'1970-01-01') and ifnull(?,'3000-01-01') "
            + "group by c.codigo order by d.fecha desc ";

    public PacientesD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Paciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getGenero());
            stat.setDate(2, object.getFecha());
            stat.setString(3, object.getPeso());
            stat.setBoolean(4, object.isEstado());
            stat.setString(5, object.getTipo_de_sangre());
            stat.setString(6, object.getPersona_dpi());
            stat.setString(7, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Pacientes");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean modify(Paciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.isMasculino());
            stat.setDate(2, object.getFecha());
            stat.setString(3, object.getPeso());
            stat.setBoolean(4, object.isEstado());
            stat.setString(5, object.getTipo_de_sangre());
            stat.setString(6, object.getPersona_dpi());
            stat.setString(7, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Pacientes");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public List<Paciente> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Paciente> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertir(rs));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Paciente obtener(String id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GETONE);
            stat.setString(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                return (convertir(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Paciente object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public Paciente convertir(ResultSet rs) {

        try {
            Paciente pacientes = new Paciente(rs.getString("codigo"), rs.getString("masculino"), rs.getDate("fecha"), rs.getString("peso"), rs.getBoolean("estado"), rs.getString("tipo_de_sangre"), rs.getString("Persona_dpi"));

            return pacientes;
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String lastInsertId() {
        String ultimo = "SELECT last_insert_id()";
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(ultimo);
            rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public Paciente getPacienteByCodeAndPwd(String code, String pwd) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_ADMIN_BY_CODE_AND_PWD);
            stat.setString(1, code);
            stat.setString(2, pwd);
            rs = stat.executeQuery();
            while (rs.next()) {
                return (convertir(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PacienteHistorial> getPacienteHistoria() {
        Manager manager = new Manager();

        PreparedStatement stat = null;
        ResultSet rs = null;
        List<PacienteHistorial> lst = new ArrayList<>();

        try {

            stat = connection.prepareStatement(GET_ALL_WHIT_PERSONA);
            rs = stat.executeQuery();

            while (rs.next()) {
                PacienteHistorial pacHistorial = convertirPacienteHistorial(rs);

                System.out.println("Paciente: " + pacHistorial);

                pacHistorial.setConsultas(
                        manager.getCitaDAO().getInformeConsulta(pacHistorial.getCodigo()));

                pacHistorial.setResultados(manager.getExamenPacienteDAO().getExamenResultado(pacHistorial.getCodigo()));

                lst.add(pacHistorial);

            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Ultimos> getLastExamenesByCodePaciente(String codigoPaciente) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Ultimos> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_LAST_EXAMENES_BY_CODE_USER);
            stat.setString(1, codigoPaciente);
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertToLast(rs));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Ultimos> getLastConsultas(String codigoPaciente) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Ultimos> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_ULTIAMS_CONSULTAS);
            stat.setString(1, codigoPaciente);
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertToLast(rs));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //EXAMEN,INICIAL,FINAL CODIGO PACIENTE
    @Override
    public List<ExamenIntervalo> getExamenPorIntervalo(String tipoExamen, String fechaInicial, String fechaFinal, String codigoPaciente) {

        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ExamenIntervalo> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_EXAMEN_BY_TIPO_AND_CODE_PACIENTE);
            stat.setString(1, tipoExamen);
            stat.setString(2, fechaInicial);
            stat.setString(3, fechaFinal);
            stat.setString(4, codigoPaciente);
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertToExamenIntervalo(rs));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    ////codigo_paciente medico, fecha inicial y final
    public List<ExamenMedico> getConsultaByCodigoPacienteMedicoFechas(String codePaciente,
            String medico, String fechaInicial, String fechaFinal) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ExamenMedico> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_CONSULTAS_BY_MEDICO_INTERVALO_FECHAS);
            stat.setString(1, codePaciente);
            stat.setString(2, "%" + medico + "%");
            stat.setString(3, fechaInicial);
            stat.setString(4, fechaFinal);
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(new ExamenMedico(rs.getString("consulta"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("nombre")));

            }
            for (ExamenMedico examenMedico : lst) {
                System.out.println("examen: "+examenMedico);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ExamenIntervalo convertToExamenIntervalo(ResultSet rs) {

        try {
            return new ExamenIntervalo(rs.getString("codigo"), rs.getString("nombre"), rs.getString("registro"), rs.getString("lab"), rs.getString("fecha"));
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Ultimos convertToLast(ResultSet rs) {

        try {
            return new Ultimos(rs.getString("Codigo"), rs.getString("nombre"), rs.getString("fecha"));
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public PacienteHistorial convertirPacienteHistorial(ResultSet rs) {
        try {

            return new PacienteHistorial(rs.getString("codigo"), rs.getString("nombre"), rs.getString("masculino"), rs.getString("fecha"), rs.getString("peso"), rs.getString("sangre"));
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
