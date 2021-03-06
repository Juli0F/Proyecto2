package com.hospital.mysql;

import com.hospital.dao.DiaTrabajoDAO;
import com.hospital.entities.DiaTrabajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiaTrabajoD implements DiaTrabajoDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO DiaTrabajo (Turno_idTurno,Laboratoristas_registro,estado) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE DiaTrabajo set Turno_idTurno = ?, set Laboratoristas_registro = ?, set estado = ? WHERE idTrabajoLaboratoristaLaboratorista = ? ";
    private final String DELETE = "DELETE DiaTrabajo WHERE idTrabajoLaboratoristaLaboratorista = ? ";
    private final String GET_ALL = "SELECT * FROM  DiaTrabajo  ";
    private final String GETONE = GET_ALL + "WHERE idTrabajoLaboratoristaLaboratorista = ?";
    private final String GET_DIAS_DE_TRABAJO="select t.turno from DiaTrabajo dt inner join Laboratoristas l on l.registro = dt.Laboratoristas_registro inner join Turno t on t.idTurno = dt.Turno_idTurno where l.registro = ? ";

    public DiaTrabajoD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(DiaTrabajo object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, object.getTurno_idTurno());
            stat.setString(2, object.getLaboratoristas_registro());
            stat.setBoolean(3, object.isEstado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover DiaTrabajo");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(DiaTrabajo object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setInt(1, object.getTurno_idTurno());
            stat.setString(2, object.getLaboratoristas_registro());
            stat.setBoolean(3, object.isEstado());
            stat.setInt(4, object.getIdTrabajoLaboratoristaLaboratorista());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover DiaTrabajo");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getDiasDeTrabajo(String registro) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String diasDeTRabajo = "" ;
        try {
            stat = connection.prepareStatement(GET_DIAS_DE_TRABAJO);
            stat.setString(1,registro);
            rs = stat.executeQuery();
            while (rs.next()) {
                diasDeTRabajo = diasDeTRabajo+" " +rs.getString("turno");
                        
            }
            return diasDeTRabajo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return diasDeTRabajo;
    }
    @Override
    public List<DiaTrabajo> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<DiaTrabajo> lst = new ArrayList<>();
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
    public DiaTrabajo obtener(Integer id) {
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
    public boolean delete(DiaTrabajo object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdTrabajoLaboratoristaLaboratorista());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DiaTrabajoD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public DiaTrabajo convertir(ResultSet rs) {

        try {
            DiaTrabajo diaTrabajo = new DiaTrabajo(rs.getInt("idTrabajoLaboratoristaLaboratorista"), rs.getInt("Turno_idTurno"), rs.getString("Laboratoristas_registro"), rs.getBoolean("estado"));

            return diaTrabajo;
        } catch (SQLException ex) {
            Logger.getLogger(DiaTrabajoD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DiaTrabajoD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
