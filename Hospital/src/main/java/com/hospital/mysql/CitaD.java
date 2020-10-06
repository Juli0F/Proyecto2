package com.hospital.mysql;

import com.hospital.dao.CitaDAO;
import com.hospital.dto.CitaPac;
import com.hospital.dto.InformeConsulta;
import com.hospital.entities.Cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitaD implements CitaDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Cita (descripcion,estado,Pacientes_codigo,Consulta_idConsulta) VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE Cita set descripcion = ?, estado = ?, Pacientes_codigo = ? WHERE codigo = ? ";
    private final String DELETE = "DELETE Cita WHERE codigo = ? ";
    private final String GETALL = "SELECT * FROM  Cita  ";
    private final String GET_ONE = GETALL + "WHERE codigo = ?";
    private final String GET_CITA_PACIENTE_AND_CONSULTA = "select c.codigo as codigoCita ,cons.tipo as consulta,p.codigo as codigoPaciente , per.nombre as paciente, d.fecha, d.hora, cons.costo  from Cita c inner join Pacientes p on c.Pacientes_codigo = p.codigo  inner join Consulta cons on c.Consulta_idConsulta = cons.idConsulta inner join Persona per on per.dpi = p.Persona_dpi inner join Dia d on d.Cita_codigo = c.codigo INNER JOIN Agenda a  where c.estado = 'PENDIENTE' and a.Medico_colegiado = ?";

    private final String GET_CONSULTA_INFORME_BY_CODIGO_USUARIO ="select (c.codigo) as codigoCita, con.idConsulta as codigoConsulta, con.tipo, con.costo, i.descripcion as informe , d.fecha as fecha, m.colegiado as colegiado, per.nombre    from Consulta con " +
                                                                "inner join Cita c on con.idConsulta = c.Consulta_idConsulta " +
                                                                "inner join Informe i on i.Cita_codigo = c.codigo " +
                                                                "inner join Medico m on m.colegiado = i.Medico_colegiado  " +
                                                                "inner join Dia d on d.Cita_codigo = c.codigo  " +
                                                                "inner join Persona per on per.dpi = m.Persona_dpi where c.Pacientes_codigo = ?";
    
    public CitaD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Cita object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, object.getDescripcion());
            stat.setString(2, object.getEstado());
            stat.setString(3, object.getPacientes_codigo());
            stat.setInt(4, object.getIdConsulta());

            System.out.println("idConsulta " + object.getIdConsulta());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Cita");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Cita object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setString(1, object.getDescripcion());
            stat.setString(2, object.getEstado());
            stat.setString(3, object.getPacientes_codigo());
            stat.setString(4, object.getCodigo());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Cita");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Cita> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cita> lst = new ArrayList<>();
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
    public Cita obtener(String id) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_ONE);
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
    public boolean delete(Cita object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getCodigo());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Cita convertir(ResultSet rs) {

        try {
            Cita cita = new Cita(rs.getString("codigo"), rs.getString("descripcion"), rs.getString("estado"), rs.getString("Pacientes_codigo"), rs.getInt("Consulta_idConsulta"));

            return cita;
        } catch (SQLException ex) {
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * busca las citas pendientes de un medico, se requiere del colegiado para
     * saber que medico
     *
     * @param colegiado
     * @return
     */

    @Override
    public List<CitaPac> getCitaPac(String colegiado) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<CitaPac> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_CITA_PACIENTE_AND_CONSULTA);
            stat.setString(1, colegiado);
            stat.toString();
            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertirCitaPac(rs));
            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public CitaPac convertirCitaPac(ResultSet rs) {

        try {
            System.out.println("Convertir");
            return new CitaPac(rs.getString("codigoCita"),
                    rs.getString("consulta"),
                    rs.getString("codigoPaciente"),
                    rs.getString("paciente"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("costo")
            );

            //  return cita;
        } catch (SQLException ex) {
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
     @Override
    public List<InformeConsulta> getInformeConsulta(String codigoPaciente) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<InformeConsulta> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_CONSULTA_INFORME_BY_CODIGO_USUARIO);
            stat.setString(1, codigoPaciente);
            stat.toString();
            rs = stat.executeQuery();
            while (rs.next()) {
                InformeConsulta info = convertirInformeConsulta(rs);
                if (!lst.contains(info)) {
                    lst.add(info);
                }
            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    public InformeConsulta convertirInformeConsulta(ResultSet rs) {

        try {
            System.out.println("Convertir");
            return new InformeConsulta(
                    rs.getString("codigoCita"),
                    rs.getString("codigoConsulta"),
                    rs.getString("tipo"),
                    rs.getString("costo"),
                    rs.getString("informe"),
                    rs.getString("fecha"),
                    rs.getString("colegiado"),
                    rs.getString("nombre")
                    
            );

            //  return cita;
        } catch (SQLException ex) {
            Logger.getLogger(CitaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
