package com.hospital.mysql;

import com.hospital.dao.ResultadoDAO;
import com.hospital.entities.Resultado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultadoD implements ResultadoDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Resultado (descripcion,fechaHora,estado,Laboratoristas_registro,Medico_colegiado,Pacientes_codigo,Examen_Codigo,resultadoCodigo,hora,orden) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Resultado set descripcion = ?, set fechaHora = ?, set estado = ?, set Laboratoristas_registro = ?, set Medico_colegiado = ?, set Pacientes_codigo = ?, set Examen_Codigo = ? WHERE resultadoCodigo = ? ";
    private final String DELETE = "DELETE Resultado WHERE resultadoCodigo = ? ";
    private final String GETALL = "SELECT * FROM  Resultado  ";
    private final String GETONE = GETALL + "WHERE resultadoCodigo = ?";

    public ResultadoD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Resultado object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getDescripcion());
            stat.setDate(2, object.getFechaHora());
            stat.setBoolean(3, object.isEstado());
            stat.setString(4, object.getLaboratoristas_registro());
            stat.setInt(5, object.getMedico_colegiado());
            stat.setString(6, object.getPacientesCodigo());
            stat.setString(7, object.getExamenCodigo());
            stat.setString(8, object.getResultadoCodigo());
            stat.setTime(9, object.getHora());
            stat.setBinaryStream(10, object.getInputStreamOrden());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Resultado");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean modify(Resultado object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getDescripcion());
            stat.setDate(2, object.getFechaHora());
            stat.setBoolean(3, object.isEstado());
            stat.setString(4, object.getLaboratoristas_registro());
            stat.setInt(5, object.getMedico_colegiado());
            stat.setString(6, object.getPacientesCodigo());
            stat.setString(7, object.getExamenCodigo());
            stat.setString(8, object.getResultadoCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Resultado");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public List<Resultado> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Resultado> lst = new ArrayList<>();
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
    public Resultado obtener(String id) {
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
    public boolean delete(Resultado object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getResultadoCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public Resultado convertir(ResultSet rs) {

        try {
            Resultado resultado = new Resultado(rs.getString("resultadoCodigo"),
                    rs.getString("descripcion"), rs.getDate("fechaHora"),
                    rs.getBoolean("estado"), rs.getString("Laboratoristas_registro"),
                    rs.getInt("Medico_colegiado"), rs.getString("Pacientes_codigo"),
                    rs.getString("Examen_Codigo"),
                    rs.getTime("hora")
            );

            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ResultadoD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
