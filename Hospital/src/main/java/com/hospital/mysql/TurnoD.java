package com.hospital.mysql;

import com.hospital.dao.TurnoDAO;
import com.hospital.entities.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TurnoD implements TurnoDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Turno (turno,) VALUES (?)";
    private final String UPDATE = "UPDATE Turno set turno = ? WHERE idTurno = ? ";
    private final String DELETE = "DELETE Turno WHERE idTurno = ? ";
    private final String GETALL = "SELECT * FROM  Turno  ";
    private final String GETONE = GETALL + "WHERE idTurno = ?";

    public TurnoD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Turno object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getTurno());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Turno");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }

    @Override
    public boolean modify(Turno object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getTurno());
            stat.setInt(2, object.getIdTurno());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Turno");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }

    @Override
    public List<Turno> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Turno> lst = new ArrayList<>();
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
    public Turno obtener(Integer id) {
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
    public boolean delete(Turno object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdTurno());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(TurnoD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
        
    }

    public Turno convertir(ResultSet rs) {

        try {
            Turno turno = new Turno(rs.getInt("idTurno"), rs.getString("turno"));

            return turno;
        } catch (SQLException ex) {
            Logger.getLogger(TurnoD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TurnoD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
