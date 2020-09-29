package com.hospital.mysql;

import com.hospital.dao.AgendaDAO;
import com.hospital.dao.DiaTrabajoDAO;
import com.hospital.dao.InformeDAO;
import com.hospital.dao.ExamenDAO;
import com.hospital.dao.EspecialidadDAO;
import com.hospital.dao.DiaDAO;
import com.hospital.dao.PersonaDAO;
import com.hospital.dao.ResultadoDAO;
import com.hospital.dao.AdministradorDAO;
import com.hospital.dao.LaboratoristasDAO;
import com.hospital.dao.DAOManager;
import com.hospital.dao.ConsultaDAO;
import com.hospital.dao.TurnoDAO;
import com.hospital.dao.CitaDAO;
import com.hospital.dao.MedicoDAO;
import com.hospital.dao.UsuarioDAO;
import com.hospital.dao.PacientesDAO;
import com.hospital.dao.ExamenPacienteDAO;
import com.hospital.dao.ExamenLaboratoristaDAO;
import com.hospital.connection.Conexion;
import java.sql.Connection;

public class Manager implements DAOManager {

    private AgendaDAO agendadao;
    private DiaTrabajoDAO diatrabajodao;
    private InformeDAO informedao;
    private ExamenDAO examendao;
    private EspecialidadDAO especialidaddao;
    private DiaDAO diadao;
    private PersonaDAO personadao;
    private ResultadoDAO resultadodao;
    private AdministradorDAO administradordao;
    private LaboratoristasDAO laboratoristasdao;
    private DAOManager daomanager;
    private ConsultaDAO consultadao;
    private TurnoDAO turnodao;
    private CitaDAO citadao;
    private MedicoDAO medicodao;
    private UsuarioDAO usuariodao;
    private PacientesDAO pacientesdao;
    private ExamenPacienteDAO examenpacientedao;
    private Connection connection;
    private ExamenLaboratoristaDAO examenlaboratoristadao;

    public Manager() {
        this.connection = Conexion.getInstancia();
    }

    @Override
    public AgendaDAO getAgendaDAO() {
        if (agendadao == null) {
            agendadao = new AgendaD(connection);
        }
        return agendadao;
    }

    @Override
    public DiaTrabajoDAO getDiaTrabajoDAO() {
        if (diatrabajodao == null) {
            diatrabajodao = new DiaTrabajoD(connection);
        }
        return diatrabajodao;
    }

    @Override
    public InformeDAO getInformeDAO() {
        if (informedao == null) {
            informedao = new InformeD(connection);
        }
        return informedao;
    }

    @Override
    public ExamenDAO getExamenDAO() {
        if (examendao == null) {
            examendao = new ExamenD(connection);
        }
        return examendao;
    }

    @Override
    public EspecialidadDAO getEspecialidadDAO() {
        if (especialidaddao == null) {
            especialidaddao = new EspecialidadD(connection);
        }
        return especialidaddao;
    }

    @Override
    public DiaDAO getDiaDAO() {
        if (diadao == null) {
            diadao = new DiaD(connection);
        }
        return diadao;
    }

    @Override
    public PersonaDAO getPersonaDAO() {
        if (personadao == null) {
            personadao = new PersonaD(connection);
        }
        return personadao;
    }

    @Override
    public ResultadoDAO getResultadoDAO() {
        if (resultadodao == null) {
            resultadodao = new ResultadoD(connection);
        }
        return resultadodao;
    }

    @Override
    public AdministradorDAO getAdministradorDAO() {
        if (administradordao == null) {
            administradordao = new AdministradorD(connection);
        }
        return administradordao;
    }

    @Override
    public LaboratoristasDAO getLaboratoristasDAO() {
        if (laboratoristasdao == null) {
            laboratoristasdao = new LaboratoristasD(connection);
        }
        return laboratoristasdao;
    }

    @Override
    public ConsultaDAO getConsultaDAO() {
        if (consultadao == null) {
            consultadao = new ConsultaD(connection);
        }
        return consultadao;
    }

    @Override
    public TurnoDAO getTurnoDAO() {
        if (turnodao == null) {
            turnodao = new TurnoD(connection);
        }
        return turnodao;
    }

    @Override
    public CitaDAO getCitaDAO() {
        if (citadao == null) {
            citadao = new CitaD(connection);
        }
        return citadao;
    }

    @Override
    public MedicoDAO getMedicoDAO() {
        if (medicodao == null) {
            medicodao = new MedicoD(connection);
        }
        return medicodao;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        if (usuariodao == null) {
            usuariodao = new UsuarioD(connection);
        }
        return usuariodao;
    }

    @Override
    public PacientesDAO getPacientesDAO() {
        if (pacientesdao == null) {
            pacientesdao = new PacientesD(connection);
        }
        return pacientesdao;
    }

    @Override
    public ExamenPacienteDAO getExamenPacienteDAO() {
        if (examenpacientedao == null) {
            examenpacientedao = new ExamenPacienteD(connection);
        }
        return examenpacientedao;
    }
 @Override
  public ExamenLaboratoristaDAO getExamenLaboratoristaDAO(){
      if( examenlaboratoristadao == null ){
          examenlaboratoristadao = new  ExamenLaboratoristaD(connection);
}
      return examenlaboratoristadao;
}
  
}
