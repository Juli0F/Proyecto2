package com.hospital.mysql;

import com.hospital.dao.DiaDAO;
import com.hospital.dto.FechaConMasTrabajo;
import com.hospital.entities.Dia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiaD implements DiaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Dia (fecha,descripcion,Agenda_codigo,Cita_codigo,hora) VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE Dia set fecha = ?, set descripcion = ?, set Agenda_codigo = ?, set Cita_codigo = ? WHERE idDia = ? ";
    private final String DELETE = "DELETE Dia WHERE idDia = ? ";
    private final String GET_ALL = "SELECT * FROM  Dia  ";
    private final String GETONE = GET_ALL + "WHERE idDia = ?";
    private final String GET_DAY_WHIT_MOST_WORK = "select d.fecha, count(d.fecha) cantidad from Dia d inner join Cita c on c.codigo =  d.Cita_codigo group by d.fecha order by cantidad desc limit 10";

    
    //consultas
    private final String SEARCH_COINCIDENCIA_OF_DIA_AND_HOUR ="select d.idDia ,d.Agenda_codigo, d.Cita_codigo  ,d.descripcion,d.fecha,d.hora from Dia d inner join Agenda a on a.codigo = d.Agenda_codigo where d.fecha = ? and d.hora  = ? and a.codigo = ? " ;
    private final String SEARCH_COINCIDENCIA_OF_DIA_AND_HOUR_WHIT_COLEGIADO ="select d.idDia ,d.Agenda_codigo, d.Cita_codigo  ,d.descripcion,d.fecha,d.hora from Dia d inner join Agenda a on a.codigo = d.Agenda_codigo where d.fecha = ? and d.hora  = ? and a.Medico_colegiado = ?";
    //
    
    public DiaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Dia object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setDate(1, object.getFecha());
            stat.setString(2, object.getDescripcion());
            stat.setInt(3, object.getAgenda_codigo());
            stat.setString(4, object.getCita_codigo());
            stat.setTime(5, object.getHora());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Dia");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Dia object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setDate(1, object.getFecha());
            stat.setString(2, object.getDescripcion());
            stat.setInt(3, object.getAgenda_codigo());
            stat.setString(4, object.getCita_codigo());
            stat.setInt(5, object.getIdDia());
            stat.setTime(5, object.getHora());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Dia");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Dia> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Dia> lst = new ArrayList<>();
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
    public Dia obtener(Integer id) {
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
    public boolean delete(Dia object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getIdDia());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DiaD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Dia convertir(ResultSet rs) {

        try {
            Dia dia = new Dia(rs.getInt("idDia"), rs.getDate("fecha"), rs.getString("descripcion"), rs.getInt("Agenda_codigo"), rs.getString("Cita_codigo"),rs.getTime("hora"));

            return dia;
        } catch (SQLException ex) {
            Logger.getLogger(DiaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DiaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Dia searchCoincidenceByDateHourAndAgenda(Date fecha, Time hora, int codigoAgenda) {
       PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(SEARCH_COINCIDENCIA_OF_DIA_AND_HOUR);
            stat.setDate(1, fecha);
            stat.setTime(2, hora);
            stat.setInt(3,codigoAgenda);
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
    public Dia searchCoincidenceByDateHourAndColegiado(Date fecha, Time hora, int colegiado) {
       PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(SEARCH_COINCIDENCIA_OF_DIA_AND_HOUR_WHIT_COLEGIADO);
            stat.setDate(1, fecha);
            stat.setTime(2, hora);
            stat.setInt(3,colegiado);
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
    public List<FechaConMasTrabajo> getDateWithMostWork() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<FechaConMasTrabajo> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_DAY_WHIT_MOST_WORK);
            rs = stat.executeQuery();
            
            while (rs.next()) {
                lst.add(new FechaConMasTrabajo(rs.getString("fecha"),rs.getString("cantidad")));
            }
            
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
