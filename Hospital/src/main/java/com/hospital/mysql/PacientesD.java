package com.hospital.mysql;

import com.hospital.dao.PacientesDAO;
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
    private final String INSERT = "INSERT INTO Pacientes (masculino,fecha,peso,estado,tipo_de_sangre,Persona_dpi,) VALUES (?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Pacientes set masculino = ?, set fecha = ?, set peso = ?, set estado = ?, set tipo_de_sangre = ?, set Persona_dpi = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Pacientes WHERE codigo = ? ";
    private final String GETALL = "SELECT * FROM  Pacientes  ";
    private final String GETONE = GETALL + "WHERE codigo = ?";

    public PacientesD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Paciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.isMasculino());
            stat.setDate(2, object.getFecha());
            stat.setString(3, object.getPeso());
            stat.setBoolean(4, object.isEstado());
            stat.setString(5, object.getTipo_de_sangre());
            stat.setString(6, object.getPersona_dpi());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Pacientes");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Paciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.isMasculino());
            stat.setDate(2, object.getFecha());
            stat.setString(3, object.getPeso());
            stat.setBoolean(4, object.isEstado());
            stat.setString(5, object.getTipo_de_sangre());
            stat.setString(6, object.getPersona_dpi());
            stat.setInt(7, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Pacientes");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Paciente> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GETALL);
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
    public Paciente obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GETONE);
            stat.setInt(1, id);
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
    public void delete(Paciente object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Paciente convertir(ResultSet rs) {

        try {
            Paciente pacientes = new Paciente(rs.getInt("codigo"), rs.getString("masculino"), rs.getDate("fecha"), rs.getString("peso"), rs.getBoolean("estado"), rs.getString("tipo_de_sangre"), rs.getString("Persona_dpi"));

            return pacientes;
        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer lastInsertId() {
        String ultimo = "SELECT last_insert_id()";
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(ultimo);
            rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacientesD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
