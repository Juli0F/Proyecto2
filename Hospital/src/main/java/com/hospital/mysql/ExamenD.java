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
    private final String INSERT = "INSERT INTO Examen (nombre,orden,descripcion,costo,formato,estado,) VALUES (?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Examen set nombre = ?, set orden = ?, set descripcion = ?, set costo = ?, set formato = ?, set estado = ? WHERE Codigo = ? ";
    private final String DELETE = "DELETE Examen WHERE Codigo = ? ";
    private final String GETALL = "SELECT * FROM  Examen  ";
    private final String GETONE = GETALL + "WHERE Codigo = ?";

    public ExamenD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Examen object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getNombre());
            stat.setBoolean(2, object.isOrden());
            stat.setString(3, object.getDescripcion());
            stat.setDouble(4, object.getCosto());
            stat.setString(5, object.getFormato());
            stat.setString(6, object.getEstado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Examen");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Examen object) {
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
        }
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
    public Examen obtener(Integer id) {
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
    public void delete(Examen object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamenD.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(ExamenD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
