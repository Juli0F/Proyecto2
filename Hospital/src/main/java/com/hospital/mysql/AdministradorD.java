package com.hospital.mysql;

import com.hospital.dao.AdministradorDAO;
import com.hospital.entities.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorD implements AdministradorDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Administrador (codigo, Persona_dpi) VALUES (?,?)";
    private final String UPDATE = "UPDATE Administrador set Persona_dpi = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Administrador WHERE codigo = ? ";
    private final String GETALL = "SELECT * FROM  Administrador  ";
    private final String GETONE = GETALL + "WHERE codigo = ?";

    public AdministradorD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Administrador object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getPersona_dpi());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Administrador");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Administrador object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getPersona_dpi());
            stat.setString(2, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Administrador");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Administrador> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Administrador> lst = new ArrayList<>();
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
    public Administrador obtener(Integer id) {
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
    public void delete(Administrador object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Administrador convertir(ResultSet rs) {

        try {
            Administrador administrador = new Administrador(rs.getString("codigo"), rs.getString("Persona_dpi"));

            return administrador;
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdministradorD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
