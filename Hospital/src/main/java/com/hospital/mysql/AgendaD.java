package com.hospital.mysql;

import com.hospital.dao.AgendaDAO;
import com.hospital.entities.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaD implements AgendaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Agenda (estado,Medico_colegiado,Laboratoristas_registro) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE Agenda set estado = ?, set Medico_colegiado = ?, set Laboratoristas_registro = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Agenda WHERE codigo = ? ";
    private final String GETALL = "SELECT * FROM  Agenda  ";
    private final String GETONE = GETALL + "WHERE codigo = ?";
    private final String GET_AGENDA_MEDICO2 = GETALL + " a "
            + "Usuario u INNER JOIN Medico m ON m.Persona_dpi = u.Persona_dpi WHERE u.codigo = ? ";
    private final String GET_AGENDA_MEDICO = "SELECT * FROM"
            + " Usuario u "
            + "INNER JOIN Medico m ON m.Persona_dpi = u.Persona_dpi "
            + "INNER JOIN Agenda a on a.Medico_colegiado = m.colegiado  WHERE u.codigo = ?";

    public AgendaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Agenda object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getEstado());
            stat.setInt(2, object.getMedico_colegiado());
            stat.setString(3, object.getLaboratoristas_registro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Agenda");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Agenda object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getEstado());
            stat.setInt(2, object.getMedico_colegiado());
            stat.setString(3, object.getLaboratoristas_registro());
            stat.setInt(4, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Agenda");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Agenda> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Agenda> lst = new ArrayList<>();
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
    public Agenda obtener(Integer id) {
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
    public Agenda obtenerAgendaMedica(String colegiadoMedico) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_AGENDA_MEDICO);
            stat.setString(1, colegiadoMedico);
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
    public void delete(Agenda object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Agenda convertir(ResultSet rs) {

        try {
            Agenda agenda = new Agenda(rs.getInt("codigo"), rs.getString("estado"), rs.getInt("Medico_colegiado"), rs.getString("Laboratoristas_registro"));

            return agenda;
        } catch (SQLException ex) {
            Logger.getLogger(AgendaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AgendaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
