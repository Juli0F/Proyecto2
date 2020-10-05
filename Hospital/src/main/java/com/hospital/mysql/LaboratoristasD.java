package com.hospital.mysql;

import com.hospital.dao.LaboratoristasDAO;
import com.hospital.dto.PersonaLabDto;
import com.hospital.entities.Laboratorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratoristasD implements LaboratoristasDAO {

    private Connection connection;
    private final String INSERT = "INSERT INTO Laboratoristas (inicio,ocupado,estado,Persona_dpi,registro) VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE Laboratoristas set inicio = ?, set ocupado = ?, set estado = ?, set Persona_dpi = ? WHERE registro = ? ";
    private final String DELETE = "DELETE Laboratoristas WHERE registro = ? ";
    private final String GETALL = "SELECT * FROM  Laboratoristas  ";
    private final String GETONE = GETALL + "WHERE registro = ?";
    private final String GET_ADMIN_BY_CODE_AND_PWD = "select * from Usuario u inner join Laboratoristas a on u.Persona_dpi = a.Persona_dpi where u.codigo = ? AND u.clave = ?";
    private final String GET_PERSONALABDTO_BY_CODE_EXAMEN = "select p.dpi,p.nombre,l.registro,l.ocupado,e.codigo,e.nombre as examen from ExamenLaboratorista  el inner join Examen e on el.Examen_Codigo = e.Codigo inner join Laboratoristas l on el.Laboratoristas_registro = l.registro inner join Persona p on p.dpi = l.Persona_dpi where e.codigo = ? AND l.estado = 1 and e.estado = 1" ;

    public LaboratoristasD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Laboratorista object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(INSERT);

            stat.setDate(1, object.getInicio());
            stat.setString(2, "0");
            stat.setString(3, "1");
            stat.setString(4, object.getPersona_dpi());
            stat.setString(5, object.getRegistro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Laboratoristas");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(Laboratorista object) {
        PreparedStatement stat = null;;
        try {
            stat = connection.prepareStatement(UPDATE);
            stat.setDate(1, object.getInicio());
            stat.setString(2, object.getOcupado());
            stat.setString(3, object.getEstado());
            stat.setString(4, object.getPersona_dpi());
            stat.setString(5, object.getRegistro());
            if (stat.executeUpdate() == 0) {
                System.out.println("crear popover Laboratoristas");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Laboratorista> obtenerTodo() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Laboratorista> lst = new ArrayList<>();
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
    public Laboratorista obtener(String id) {
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
    public boolean delete(Laboratorista object) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(DELETE);
            stat.setString(1, object.getRegistro());
            if (stat.executeUpdate() == 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Laboratorista convertir(ResultSet rs) {

        try {
            Laboratorista laboratoristas = new Laboratorista(rs.getString("registro"), rs.getDate("inicio"), rs.getString("ocupado"), rs.getString("estado"), rs.getString("Persona_dpi"));

            return laboratoristas;
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public Laboratorista getLaboratoristaByCodeANdPwd(String codigo, String pwd) {
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
    public List<PersonaLabDto> getPersonaLabDtoByCodeExamen(String codeExamen) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<PersonaLabDto> lst = new ArrayList<>();

        try {
            stat = connection.prepareStatement(GET_PERSONALABDTO_BY_CODE_EXAMEN);
            stat.setString(1, codeExamen);

            rs = stat.executeQuery();
            while (rs.next()) {
                lst.add(convertirPersonaLabDto(rs));
            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PersonaLabDto convertirPersonaLabDto(ResultSet rs) {
        try {

            return new PersonaLabDto(!rs.getBoolean("ocupado"),
                                        rs.getString("nombre"),
                                        rs.getString("dpi"),
                                        rs.getString("registro"),
                                        rs.getString("examen"),
                                        rs.getString("codeExamen")
                    );
            
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoristasD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
