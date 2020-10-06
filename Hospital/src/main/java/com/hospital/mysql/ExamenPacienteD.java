package com.hospital.mysql;

import com.hospital.dao.ExamenPacienteDAO;
import com.hospital.dto.ResultadoPaciente;
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
    private final String INSERT = "INSERT INTO ExamenPaciente (ordenPath,Examen_Codigo,Laboratoristas_registro,Pacientes_codigo,Medico_colegiado,realizado,cancelar,estado) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE ExamenPaciente set ordenPath = ?,  Examen_Codigo = ?,  Laboratoristas_registro = ?,  Pacientes_codigo = ?,  Medico_colegiado = ?,  realizado = ?,  cancelar = ?,  estado = ? WHERE idExamenPaciente = ? ";
    private final String DELETE = "DELETE ExamenPaciente WHERE idExamenPaciente = ? ";
    private final String GET_ALL = "SELECT * FROM  ExamenPaciente  ";
    private final String GET_ONE = GET_ALL + "WHERE idExamenPaciente = ?";
    private final String GET_RESULTADO_PACIENTE = "select ep.idExamenPaciente,e.nombre as examen,p.codigo as codigo_paciente, per.nombre ,p.masculino as genero,d.fecha,e.formato  from "
            + " ExamenPaciente ep inner join Pacientes p on ep.Pacientes_codigo = p.codigo "
            + "inner join Persona per on per.dpi = p.Persona_dpi "
            + "inner join Examen e on e.codigo = ep.Examen_codigo "
            + "inner join Agenda a on a.Laboratoristas_registro = ep.Laboratoristas_registro "
            + "inner join Dia d on d.Agenda_codigo = d.Agenda_codigo "
            + "where ep.Laboratoristas_registro = ? and ep.realizado = '0' group by ep.idExamenPaciente ";

    public ExamenPacienteD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(ExamenPaciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getOrdenPath());
            stat.setString(2, object.getExamen_Codigo());
            stat.setString(3, object.getLaboratoristas_registro());
            stat.setString(4, object.getPacientes_codigo());
            stat.setString(5, object.getMedico_colegiado());
            stat.setBoolean(6, object.isRealizado());
            stat.setBoolean(7, object.isCancelar());
            stat.setBoolean(8, object.isEstado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover ExamenPaciente");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(ExamenPaciente object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getOrdenPath());
            stat.setString(2, object.getExamen_Codigo());
            stat.setString(3, object.getLaboratoristas_registro());
            stat.setString(4, object.getPacientes_codigo());
            stat.setString(5, object.getMedico_colegiado());
            stat.setBoolean(6, object.isRealizado());
            stat.setBoolean(7, object.isCancelar());
            stat.setBoolean(8, object.isEstado());
            stat.setInt(9, object.getIdExamenPaciente());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover ExamenPaciente");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<ExamenPaciente> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ExamenPaciente> lst = new ArrayList<>();
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
    public ExamenPaciente obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_ONE);
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
    public boolean delete(ExamenPaciente object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdExamenPaciente());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamenPacienteD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public ExamenPaciente convertir(ResultSet rs) {

        try {
            ExamenPaciente examenPaciente = new ExamenPaciente(
                    rs.getInt("idExamenPaciente"), 
                    rs.getString("ordenPath"),
                    rs.getString("Examen_Codigo"),
                    rs.getString("Laboratoristas_registro"),
                    rs.getString("Pacientes_codigo"), 
                    rs.getString("Medico_colegiado"),
                    rs.getBoolean("realizado"), 
                    rs.getBoolean("cancelar"),
                    rs.getBoolean("estado"));

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
    
      @Override
    public List<ResultadoPaciente> getResultadoPaciente(String registroLaboratorista) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ResultadoPaciente> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_RESULTADO_PACIENTE);
            System.out.println("Q " +stat.toString());
            stat.setString(1, registroLaboratorista);
            System.out.println("QUery: "+stat.toString());
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertirToResultadoPaciente(rs));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public ResultadoPaciente convertirToResultadoPaciente(ResultSet rs){
        
        try {
            return new ResultadoPaciente(rs.getString("idExamenPaciente"),rs.getString("examen"),rs.getString("codigo_paciente"),rs.getString("nombre"),rs.getString("genero"),rs.getString("fecha"),rs.getString("formato"));
        } catch (SQLException ex) {
            Logger.getLogger(ExamenPacienteD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
