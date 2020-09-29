package com.hospital.mysql;

import com.hospital.dao.DiaDAO;
import com.hospital.entities.Dia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiaD implements DiaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Dia (fecha,descripcion,Agenda_codigo,Cita_codigo,) VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE Dia set fecha = ?, set descripcion = ?, set Agenda_codigo = ?, set Cita_codigo = ? WHERE idDia = ? ";
    private final String DELETE = "DELETE Dia WHERE idDia = ? ";
    private final String GETALL = "SELECT * FROM  Dia  ";
    private final String GETONE = GETALL + "WHERE idDia = ?";

    public DiaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Dia object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setDate(1, object.getFecha());
            stat.setString(2, object.getDescripcion());
            stat.setInt(3, object.getAgenda_codigo());
            stat.setInt(4, object.getCita_codigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Dia");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Dia object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setDate(1, object.getFecha());
            stat.setString(2, object.getDescripcion());
            stat.setInt(3, object.getAgenda_codigo());
            stat.setInt(4, object.getCita_codigo());
            stat.setInt(5, object.getIdDia());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Dia");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Dia> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Dia> lst = new ArrayList<>();
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
    public Dia obtener(Integer id) {
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
    public void delete(Dia object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdDia());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DiaD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Dia convertir(ResultSet rs) {

        try {
            Dia dia = new Dia(rs.getInt("idDia"), rs.getDate("fecha"), rs.getString("descripcion"), rs.getInt("Agenda_codigo"), rs.getInt("Cita_codigo"));

            return dia;
        } catch (SQLException ex) {
            Logger.getLogger(DiaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DiaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}