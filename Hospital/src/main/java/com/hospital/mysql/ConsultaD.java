package com.hospital.mysql;

import com.hospital.dao.ConsultaDAO;
import com.hospital.entities.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaD implements ConsultaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Consulta (tipo,costo,estado) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE Consulta set tipo = ?,  costo = ?,  estado = ? WHERE idConsulta = ? ";
    private final String DELETE = "DELETE Consulta WHERE idConsulta = ? ";
    private final String GET_ALL = "SELECT * FROM  Consulta  ";
    private final String GETONE = GET_ALL + "WHERE idConsulta = ?";
    private final String GET_BY_TIPO = "SELECT * FROM Consulta WHERE tipo = ? ";
    private final String GET_CONSULTA_BY_PARAMETER_LIKE = "SELECT * FROM Consulta c where c.costo like ? or tipo like ? and estado = '1'";
    

    public ConsultaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Consulta object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getTipo());
            stat.setDouble(2, object.getCosto());
            stat.setString(3, object.getEstado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Consulta");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Consulta object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getTipo());
            stat.setDouble(2, object.getCosto());
            stat.setString(3, object.getEstado());
            stat.setInt(4, object.getIdConsulta());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Consulta");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Consulta> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Consulta> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_ALL + " WHERE estado = '1'");
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
    public List<Consulta> getConsultaSearchLike(String parameter) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Consulta> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_CONSULTA_BY_PARAMETER_LIKE);
            stat.setString(1,"%"+ parameter+"%");
            stat.setString(2,"%"+ parameter+"%");
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
    public Consulta obtener(Integer id) {
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
    public Consulta getCOnsultaByTipo(String  tipo) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_BY_TIPO);
            stat.setString(1, tipo);
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
    public boolean delete(Consulta object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdConsulta());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Consulta convertir(ResultSet rs) {

        try {
            Consulta consulta = new Consulta(rs.getInt("idConsulta"), rs.getString("tipo"), rs.getDouble("costo"), rs.getString("estado"));

            return consulta;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
