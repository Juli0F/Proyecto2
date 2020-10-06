package com.hospital.mysql;

import com.hospital.connection.Conexion;
import com.hospital.dao.ExamenLaboratoristaDAO;
import com.hospital.entities.ExamenLaboratorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamenLaboratoristaD implements ExamenLaboratoristaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO ExamenLaboratorista (Examen_Codigo,Laboratoristas_registro,estado) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE ExamenLaboratorista set Examen_Codigo = ?, set Laboratoristas_registro = ?, set estado = ? WHERE idExamenLaboratorista = ? ";
    private final String DELETE = "DELETE ExamenLaboratorista WHERE idExamenLaboratorista = ? ";
    private final String GETALL = "SELECT * FROM  ExamenLaboratorista  ";
    private final String GETONE = GETALL + "WHERE idExamenLaboratorista = ?";

    public ExamenLaboratoristaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(ExamenLaboratorista object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getExamen_Codigo());
            stat.setString(2, object.getLaboratoristas_registro());
            stat.setBoolean(3, object.isEstado());
            
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover ExamenLaboratorista");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(ExamenLaboratorista object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getExamen_Codigo());
            stat.setString(2, object.getLaboratoristas_registro());
            stat.setBoolean(3, object.isEstado());
            stat.setInt(4, object.getIdExamenLaboratorista());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover ExamenLaboratorista");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<ExamenLaboratorista> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ExamenLaboratorista> lst = new ArrayList<>();
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
    public ExamenLaboratorista obtener(Integer id) {
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
    public boolean delete(ExamenLaboratorista object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdExamenLaboratorista());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamenLaboratoristaD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
        
    }

    public ExamenLaboratorista convertir(ResultSet rs) {

        try {
            ExamenLaboratorista examenLaboratorista = new ExamenLaboratorista(rs.getInt("idExamenLaboratorista"), rs.getString("Examen_Codigo"), rs.getString("Laboratoristas_registro"), rs.getBoolean("estado"));

            return examenLaboratorista;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenLaboratoristaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExamenLaboratoristaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
