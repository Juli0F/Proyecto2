package com.hospital.mysql;

import com.hospital.dao.PacientesDAO;
import com.hospital.dto.PacienteHistorial;
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

                System.out.println("Paciente: "+pacHistorial);
                pacHistorial.setConsultas(
                        manager.getCitaDAO().getInformeConsulta(pacHistorial.getCodigo()));
                lst.add(pacHistorial);

            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
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
