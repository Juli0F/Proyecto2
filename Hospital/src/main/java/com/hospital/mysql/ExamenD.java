package com.hospital.mysql;

import com.hospital.dao.ExamenDAO;
import com.hospital.entities.Examen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamenD implements ExamenDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Examen (nombre,orden,descripcion,costo,formato,estado,codigo) VALUES (?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Examen set nombre = ?, set orden = ?, set descripcion = ?, set costo = ?, set formato = ?, set estado = ? WHERE Codigo = ? ";
    private final String DELETE = "DELETE Examen WHERE Codigo = ? ";
    private final String GETALL = "SELECT * FROM  Examen  ";
    private final String GETONE = GETALL + "WHERE Codigo = ?";
    private final String GET_EXAMEN_BY_NAME = "SELECT * FROM Examen WHERE nombre = ?";

    public ExamenD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Examen object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getNombre());
            stat.setBoolean(2, object.isOrden());
            stat.setString(3, object.getDescripcion());
            stat.setDouble(4, object.getCosto());
            stat.setString(5, object.getFormato());
            stat.setString(6, "1");
            stat.setString(7, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Examen");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Examen object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getNombre());
            stat.setBoolean(2, object.isOrden());
            stat.setString(3, object.getDescripcion());
            stat.setDouble(4, object.getCosto());
            stat.setString(5, object.getFormato());
            stat.setString(6, object.getEstado());
            stat.setString(7, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Examen");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public List<Examen> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Examen> lst = new ArrayList<>();
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
    public Examen obtener(String id) {
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
    public Examen getExamenByName(String name) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_EXAMEN_BY_NAME);
            stat.setString(1, name);
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
    public boolean delete(Examen object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamenD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Examen convertir(ResultSet rs) {

        try {
            Examen examen = new Examen(rs.getString("Codigo"), rs.getString("nombre"), rs.getBoolean("orden"), rs.getString("descripcion"), rs.getDouble("costo"), rs.getString("formato"), rs.getString("estado"));

            return examen;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExamenD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
