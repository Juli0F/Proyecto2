package com.hospital.mysql;

import com.hospital.dao.LaboratoristasDAO;
import com.hospital.entities.Laboratorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratoristasD implements LaboratoristasDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Laboratoristas (inicio,ocupado,estado,Persona_dpi,registro) VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE Laboratoristas set inicio = ?, set ocupado = ?, set estado = ?, set Persona_dpi = ? WHERE registro = ? ";
    private final String DELETE = "DELETE Laboratoristas WHERE registro = ? ";
    private final String GETALL = "SELECT * FROM  Laboratoristas  ";
    private final String GETONE = GETALL + "WHERE registro = ?";

    public LaboratoristasD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Laboratorista object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            
            stat.setDate(1, object.getInicio());
            stat.setString(2, "0");
            stat.setString(3, "1");
            stat.setString(4, object.getPersona_dpi());
            stat.setString(5, object.getRegistro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Laboratoristas");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Laboratorista object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setDate(1, object.getInicio());
            stat.setString(2, object.getOcupado());
            stat.setString(3, object.getEstado());
            stat.setString(4, object.getPersona_dpi());
            stat.setString(5, object.getRegistro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Laboratoristas");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Laboratorista> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Laboratorista> lst = new ArrayList<>();
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
    public Laboratorista obtener(String id) {
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
    public void delete(Laboratorista object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getRegistro());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Laboratorista convertir(ResultSet rs) {

        try {
            Laboratorista laboratoristas = new Laboratorista(rs.getString("registro"), rs.getDate("inicio"), rs.getString("ocupado"), rs.getString("estado"), rs.getString("Persona_dpi"));

            return laboratoristas;
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
