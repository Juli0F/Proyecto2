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
    private final String GET_ADMIN_BY_CODE_AND_PWD = "select * from Usuario u inner join Administrador a on u.Persona_dpi = a.Persona_dpi where u.codigo = ? AND u.clave = ?";

    public AdministradorD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Administrador object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getCodigo());
            stat.setString(2, object.getPersona_dpi());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Administrador");

                
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Administrador object) {
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
            return false;
        }
        return true;
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
    public Administrador obtener(String id) {
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
    public boolean delete(Administrador object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
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
            Logger.getLogger(AdministradorD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public Administrador getAdminByCodeAndPsw(String codigo, String pwd) {
         PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_ADMIN_BY_CODE_AND_PWD);
            stat.setString(1, codigo);
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
}
