package com.hospital.mysql;

import com.hospital.dao.CitaDAO;
import com.hospital.entities.Cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitaD implements CitaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Cita (descripcion,estado,Pacientes_codigo,codigo) VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE Cita set descripcion = ?, set estado = ?, set Pacientes_codigo = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Cita WHERE codigo = ? ";
    private final String GETALL = "SELECT * FROM  Cita  ";
    private final String GETONE = GETALL + "WHERE codigo = ?";

    public CitaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Cita object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getDescripcion());
            stat.setString(2, object.getEstado());
            stat.setString(3, object.getPacientes_codigo());
            stat.setString(4, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Cita");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Cita object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getDescripcion());
            stat.setString(2, object.getEstado());
            stat.setString(3, object.getPacientes_codigo());
            stat.setString(4, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Cita");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cita> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cita> lst = new ArrayList<>();
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
    public Cita obtener(String id) {
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
    public void delete(Cita object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cita convertir(ResultSet rs) {

        try {
            Cita cita = new Cita(rs.getString("codigo"), rs.getString("descripcion"), rs.getString("estado"), rs.getString("Pacientes_codigo"));

            return cita;
        } catch (SQLException ex) {
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
