package com.hospital.mysql;

import com.hospital.dao.EspecialidadDAO;
import com.hospital.entities.Especialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EspecialidadD implements EspecialidadDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Especialidad (nombre,estado,Medico_colegiado,) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE Especialidad set nombre = ?, set estado = ?, set Medico_colegiado = ? WHERE idEspecialidad = ? ";
    private final String DELETE = "DELETE Especialidad WHERE idEspecialidad = ? ";
    private final String GETALL = "SELECT * FROM  Especialidad  ";
    private final String GETONE = GETALL + "WHERE idEspecialidad = ?";

    public EspecialidadD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Especialidad object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getNombre());
            stat.setBoolean(2, object.isEstado());
            stat.setInt(3, object.getMedico_colegiado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Especialidad");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Especialidad object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getNombre());
            stat.setBoolean(2, object.isEstado());
            stat.setInt(3, object.getMedico_colegiado());
            stat.setInt(4, object.getIdEspecialidad());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Especialidad");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Especialidad> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Especialidad> lst = new ArrayList<>();
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
    public Especialidad obtener(Integer id) {
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
    public void delete(Especialidad object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdEspecialidad());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Especialidad convertir(ResultSet rs) {

        try {
            Especialidad especialidad = new Especialidad(rs.getInt("idEspecialidad"), rs.getString("nombre"), rs.getBoolean("estado"), rs.getInt("Medico_colegiado"));

            return especialidad;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EspecialidadD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
