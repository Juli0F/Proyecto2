package com.hospital.mysql;

import com.hospital.dao.MedicoDAO;
import com.hospital.entities.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoD implements MedicoDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Medico (inicio,estado,Persona_dpi,colegiado,horaEntrada,horaSalida) VALUES (?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Medico set inicio = ?, set estado = ?, set Persona_dpi = ? WHERE colegiado = ? ";
    private final String DELETE = "DELETE Medico WHERE colegiado = ? ";
    private final String GETALL = "SELECT * FROM  Medico  ";
    private final String GETONE = GETALL + "WHERE colegiado = ?";
    private final String GET_MEDICO_BY_CODIGO_USUARIO = "SELECT * FROM "
            + "Usuario u INNER JOIN Medico m ON m.Persona_dpi = u.Persona_dpi WHERE u.codigo = ?";

    public MedicoD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Medico object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getInicio());
            stat.setBoolean(2, object.isEstado());
            stat.setString(3, object.getPersona_dpi());
            stat.setInt(4, object.getColegiado());
            stat.setTime(5, object.getEntrada());
            stat.setTime(6, object.getSalida());
            
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Medico");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Medico object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getInicio());
            stat.setBoolean(2, object.isEstado());
            stat.setString(3, object.getPersona_dpi());
            stat.setInt(4, object.getColegiado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Medico");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medico> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Medico> lst = new ArrayList<>();
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
    public Medico obtener(Integer id) {
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
    public void delete(Medico object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getColegiado());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Medico convertir(ResultSet rs) {

        try {

            Medico medico = new Medico(rs.getInt("colegiado"),
                    rs.getString("inicio"), rs.getBoolean("estado"),
                    rs.getString("Persona_dpi"), rs.getTime("horaEntrada"),
                    rs.getTime("horaSalida"));

            return medico;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MedicoD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Medico getMedicoByCodigoUsuario(String codigoUsuario) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_MEDICO_BY_CODIGO_USUARIO);
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

}
