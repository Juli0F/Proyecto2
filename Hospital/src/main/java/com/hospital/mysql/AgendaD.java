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
    private final String INSERT = "INSERT INTO Agenda (estado,Medico_colegiado,Laboratoristas_registro) VALUES ('1',?,?)";
    private final String INSERT_LAB = "INSERT INTO Agenda (estado,Laboratoristas_registro) VALUES ('1',?)";
    private final String UPDATE = "UPDATE Agenda set estado = ?, set Medico_colegiado = ?, set Laboratoristas_registro = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Agenda WHERE codigo = ? ";
    private final String GETALL = "SELECT * FROM  Agenda  ";
    private final String GETONE = GETALL + "WHERE codigo = ?";
    private final String GET_AGENDA_MEDICO2 = GETALL + " a "
            + "Usuario u INNER JOIN Medico m ON m.Persona_dpi = u.Persona_dpi WHERE u.codigo = ? ";
    private final String GET_AGENDA_MEDICO_BY_CODIGO_USUARIO = "SELECT a.codigo,a.estado,a.Medico_colegiado,a.Laboratoristas_registro FROM"
            + " Usuario u "
            + "INNER JOIN Medico m ON m.Persona_dpi = u.Persona_dpi "
            + "INNER JOIN Agenda a on a.Medico_colegiado = m.colegiado  WHERE u.codigo = ?";
    private final String GET_AGENDA_BY_COLEGIADO = "select * from Agenda a where a.Medico_colegiado = ? ";
    
    private final String GET_AGENDA_BY_REGISTRO = "select * from Agenda a where a.Laboratoristas_registro = ?";

    public AgendaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Agenda object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
           // stat.setString(1, object.getEstado());
            stat.setString(1, object.getMedico_colegiado());
            stat.setString(2, object.getLaboratoristas_registro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Agenda");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
     @Override
    public boolean insertLab(Agenda object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT_LAB);
           // stat.setString(1, object.getEstado());
            //stat.setInt(1, object.getMedico_colegiado());
            stat.setString(1, object.getLaboratoristas_registro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Agenda");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Agenda object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getEstado());
            stat.setString(2, object.getMedico_colegiado());
            stat.setString(3, object.getLaboratoristas_registro());
            stat.setInt(4, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Agenda");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
    public Agenda obtenerAgendaMedica(String codigoUsuario) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_AGENDA_MEDICO_BY_CODIGO_USUARIO);
            stat.setString(1, codigoUsuario);
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
    public Agenda getAgendaByColegido(String colegiadoMedico) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_AGENDA_BY_COLEGIADO);
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
    public Agenda getAgendaByRegistro(String registroLaboratorista) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_AGENDA_BY_REGISTRO);
            stat.setString(1, registroLaboratorista);
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
    public boolean delete(Agenda object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Agenda convertir(ResultSet rs) {

        try {
                            //public Agenda(int codigo,           String estado,      String Medico_colegiado,     String Laboratoristas_registro) {
            Agenda agenda = new Agenda(rs.getInt("codigo"), rs.getString("estado"), rs.getString("Medico_colegiado"), rs.getString("Laboratoristas_registro"));

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
