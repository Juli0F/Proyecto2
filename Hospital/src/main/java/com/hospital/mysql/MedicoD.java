package com.hospital.mysql;

import com.hospital.dao.MedicoDAO;
import com.hospital.dto.MedicoDto;
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

    private final String GET_ADMIN_BY_CODE_AND_PWD = "select * from Usuario u inner join Medico a on u.Persona_dpi = a.Persona_dpi where u.codigo = ? AND u.clave = ?";
    private final String GET_MEDICO_CON_ESPECIALIDAD = "select u.codigo,a.nombre,m.colegiado,a.correo,m.horaEntrada,m.horaSalida,e.nombre as especialidad from Medico m inner join Persona a on m.Persona_dpi = a.dpi inner join Usuario u on u.Persona_dpi=a.dpi inner join Especialidad e on e.Medico_colegiado = m.colegiado ";

    public MedicoD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Medico object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setDate(1, object.getInicio());
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
            return false;
        }
        return true;

    }

    @Override
    public boolean modify(Medico object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setDate(1, object.getInicio());
            stat.setBoolean(2, object.isEstado());
            stat.setString(3, object.getPersona_dpi());
            stat.setInt(4, object.getColegiado());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Medico");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

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
    public boolean delete(Medico object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setInt(1, object.getColegiado());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public Medico convertir(ResultSet rs) {

        try {

            Medico medico = new Medico(rs.getInt("colegiado"),
                    rs.getDate("inicio"), rs.getBoolean("estado"),
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

    @Override
    public Medico getMedicoByCodeANdPwd(String codigo, String pwd) {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = connection.prepareStatement(GET_ADMIN_BY_CODE_AND_PWD);
            stat.setString(1, codigo);
            stat.setString(2, pwd);
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
    public List<MedicoDto> getMedicoConEspecialida() {

        PreparedStatement stat = null;
        ResultSet rs = null;
        List<MedicoDto> lst = new ArrayList<>();
        try {
            stat = connection.prepareStatement(GET_MEDICO_CON_ESPECIALIDAD);
            rs = stat.executeQuery();
            int id = 0;
            while (rs.next()) {
                id++;
                //lst.add(convertirMedicoDto(rs));
                MedicoDto mDto = convertirMedicoDto(id, rs);
                if (lst.contains(mDto)) {
                    for (MedicoDto medicoDto : lst) {
                        if (medicoDto.equals(mDto)) {
                            //medicoDto.setEspecialidades(mDto.getEspecialidades() + ", " + medicoDto.getEspecialidades());
                            mDto.setEspecialidades(mDto.getEspecialidades() + ", " + medicoDto.getEspecialidades());
                        }
                        System.out.println("Medico: "+medicoDto.getId()+" "+medicoDto.getEspecialidades());
                    }

                } else {
                    lst.add(mDto);
                }

            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public MedicoDto convertirMedicoDto(int id, ResultSet rs) {

        try {

//            Medico medico = new Medico(rs.getInt("colegiado"),
//                    rs.getDate("inicio"), rs.getBoolean("estado"),
//                    rs.getString("Persona_dpi"), rs.getTime("horaEntrada"),
//                    rs.getTime("horaSalida"));
//public MedicoDto(String codigo,                          String medico,               String correo,            String horaEntrada,     String horaSalida,   String especialidades,String colegiado
            return new MedicoDto(id, rs.getString("codigo"), rs.getString("nombre"), rs.getString("correo"), rs.getString("horaEntrada"), rs.getString("horaSalida"), rs.getString("especialidad"), rs.getString("colegiado"));
        } catch (SQLException ex) {
            Logger.getLogger(MedicoD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
