package com.hospital.mysql;

import com.hospital.dao.PersonaDAO;
import com.hospital.entities.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaD implements PersonaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Persona (nombre,telefono,correo,estado,dpi) VALUES (?,?,?,?,?)";
    //                  UPDATE `Hospital`.`Persona` SET `nombre`='Galarga', `correo`='galarga@othermail.com' WHERE `dpi`='69749736369';
    private final String UPDATE = "UPDATE Persona SET nombre = ?, telefono = ?, correo = ? , estado = ? WHERE  dpi = ? ";
    private final String DELETE = "DELETE Persona WHERE dpi = ? ";
    private final String GETALL = "SELECT * FROM  Persona  ";
    private final String GETONE = GETALL + "WHERE dpi = ?";
    
    private final String GET_ADMIN_BY_CODE_AND_PWD = "select * from Usuario u inner join Persona a on u.Persona_dpi = a.dpi where u.codigo = ? AND u.clave = ?";
    

    public PersonaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Persona object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getNombre());
            stat.setString(2, object.getTelefono());
            stat.setString(3, object.getCorreo());
            stat.setBoolean(4, object.isEstado());
            stat.setString(5, object.getDpi());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Persona");

            }
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
    }

    @Override
    public boolean modify(Persona object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getNombre());
            stat.setString(2, object.getTelefono());
            stat.setString(3, object.getCorreo());
            stat.setBoolean(4, object.isEstado());
            stat.setString(5, object.getDpi());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Persona");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }

    @Override
    public List<Persona> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Persona> lst = new ArrayList<>();
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
    public Persona obtener(String id) {
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
    public boolean delete(Persona object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getDpi());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
        
    }

    public Persona convertir(ResultSet rs) {

        try {
            Persona persona = new Persona(rs.getString("dpi"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("correo"), rs.getBoolean("estado"));

            return persona;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PersonaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    @Override
    public Persona getPersonaByCodeANdPwd(String codigo, String pwd) {
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
