package com.hospital.mysql;

import com.hospital.dao.ExamenPacienteDAO;
import com.hospital.entities.ExamenPaciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamenPacienteD implements ExamenPacienteDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO ExamenPaciente (Resultado_resultadoCodigo,Examen_Codigo,Laboratoristas_registro,Pacientes_codigo,Medico_colegiado,realizado,cancelar,estado,) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE ExamenPaciente set Resultado_resultadoCodigo = ?, set Examen_Codigo = ?, set Laboratoristas_registro = ?, set Pacientes_codigo = ?, set Medico_colegiado = ?, set realizado = ?, set cancelar = ?, set estado = ? WHERE idExamenPaciente = ? ";
    private final String DELETE = "DELETE ExamenPaciente WHERE idExamenPaciente = ? ";
    private final String GETALL = "SELECT * FROM  ExamenPaciente  ";
    private final String GETONE = GETALL + "WHERE idExamenPaciente = ?";

    public ExamenPacienteD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(ExamenPaciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, object.getResultado_resultadoCodigo());
            stat.setString(2, object.getExamen_Codigo());
            stat.setInt(3, object.getLaboratoristas_registro());
            stat.setInt(4, object.getPacientes_codigo());
            stat.setInt(5, object.getMedico_colegiado());
            stat.setBoolean(6, object.isRealizado());
            stat.setBoolean(7, object.isCancelar());
            stat.setBoolean(8, object.isEstado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover ExamenPaciente");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(ExamenPaciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setInt(1, object.getResultado_resultadoCodigo());
            stat.setString(2, object.getExamen_Codigo());
            stat.setInt(3, object.getLaboratoristas_registro());
            stat.setInt(4, object.getPacientes_codigo());
            stat.setInt(5, object.getMedico_colegiado());
            stat.setBoolean(6, object.isRealizado());
            stat.setBoolean(7, object.isCancelar());
            stat.setBoolean(8, object.isEstado());
            stat.setInt(9, object.getIdExamenPaciente());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover ExamenPaciente");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExamenPaciente> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ExamenPaciente> lst = new ArrayList<>();
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
    public ExamenPaciente obtener(Integer id) {
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
    public void delete(ExamenPaciente object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdExamenPaciente());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamenPacienteD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ExamenPaciente convertir(ResultSet rs) {

        try {
            ExamenPaciente examenPaciente = new ExamenPaciente(rs.getInt("idExamenPaciente"), rs.getInt("Resultado_resultadoCodigo"), rs.getString("Examen_Codigo"), rs.getInt("Laboratoristas_registro"), rs.getInt("Pacientes_codigo"), rs.getInt("Medico_colegiado"), rs.getBoolean("realizado"), rs.getBoolean("cancelar"), rs.getBoolean("estado"));

            return examenPaciente;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenPacienteD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExamenPacienteD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
