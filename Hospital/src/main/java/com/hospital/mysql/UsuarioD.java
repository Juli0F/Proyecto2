package com.hospital.mysql;

import com.hospital.dao.UsuarioDAO;
import com.hospital.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioD implements UsuarioDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Usuario (clave,estado,Persona_dpi,codigo) VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE Usuario set clave = ?, set estado = ?, set Persona_dpi = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Usuario WHERE codigo = ? ";
    private final String GET_ALL = "SELECT * FROM  Usuario  ";
    private final String GETONE = GET_ALL + "WHERE codigo = ?";
    private final String GET_USR_BY_CODIGO_AND_CLAVE = GET_ALL + "WHERE codigo = ? and clave = ?";

    public UsuarioD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Usuario object) {
        PreparedStatement stat = null;;
        try {
            System.out.println("clave : "+ object.getClave());
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getClave());
            stat.setBoolean(2, object.isEstado());
            stat.setString(3, object.getPersona_dpi());
            stat.setString(4, object.getCodigo());

            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Usuario");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }

    @Override
    public boolean modify(Usuario object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getClave());
            stat.setBoolean(2, object.isEstado());
            stat.setString(3, object.getPersona_dpi());
            stat.setString(4, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Usuario");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }

    @Override
    public List<Usuario> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Usuario> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_ALL);
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
    public Usuario obtener(String id) {
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
    public boolean delete(Usuario object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
        
    }

    public Usuario convertir(ResultSet rs) {

        try {
            Usuario usuario = new Usuario(rs.getString("codigo"), rs.getString("clave"), rs.getBoolean("estado"), rs.getString("Persona_dpi"));

            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UsuarioD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public Usuario getUsrByCodigoAndClave(String codigo, String clave) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_USR_BY_CODIGO_AND_CLAVE);
            stat.setString(1, codigo);
            stat.setString(2,clave);
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
