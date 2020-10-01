package com.hospital.mysql;

import com.hospital.dao.InformeDAO;
import com.hospital.entities.Informe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;

public class InformeD implements InformeDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Informe (descripcion,fechaHora,Medico_colegiado,estado,Consulta_idConsulta,Pacientes_codigo,hora) VALUES (?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Informe set descripcion = ?, set fechaHora = ?, set Medico_colegiado = ?, set estado = ?, set Consulta_idConsulta = ?, set hora = ? set Pacientes_codigo = ? WHERE idInforme = ? ";
    private final String DELETE = "DELETE Informe WHERE idInforme = ? ";
    private final String GETALL = "SELECT * FROM  Informe  ";
    private final String GETONE = GETALL + "WHERE idInforme = ?";

    public InformeD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Informe object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getDescripcion());
            stat.setDate(2, object.getFechaHora());
            stat.setInt(3, object.getMedico_colegiado());
            stat.setBoolean(4, object.isEstado());
            stat.setInt(5, object.getConsulta_idConsulta());
            stat.setString(6, object.getPacientes_codigo());
            stat.setTime(7, (object.getHora()));
            stat.setString(8, object.getCodigo());
            
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Informe");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Informe object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getDescripcion());
            stat.setDate(2, object.getFechaHora());
            stat.setInt(3, object.getMedico_colegiado());
            stat.setBoolean(4, object.isEstado());
            stat.setInt(5, object.getConsulta_idConsulta());
            stat.setString(6, object.getPacientes_codigo());
            stat.setTime(7, (object.getHora()));
            stat.setString(8, object.getIdInforme());
            
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Informe");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Informe> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Informe> lst = new ArrayList<>();
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
    public Informe obtener(String id) {
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
    public void delete(Informe object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getIdInforme());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public LocalTime convertToLocalTime(java.sql.Time time){
        return time.toLocalTime();
    }

    public Informe convertir(ResultSet rs) {

        try {
            Informe informe = new Informe(rs.getString("idInforme"), 
                    rs.getString("descripcion"), rs.getDate("fechaHora"), 
                    rs.getInt("Medico_colegiado"), rs.getBoolean("estado"),
                    rs.getInt("Consulta_idConsulta"), rs.getString("Pacientes_codigo"),
                    (rs.getTime("hora"))
                    
                    );
            

            return informe;
        } catch (SQLException ex) {
            Logger.getLogger(InformeD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InformeD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
