package com.hospital.dao;





public interface DAOManager{
   AgendaDAO getAgendaDAO();
   DiaTrabajoDAO getDiaTrabajoDAO();
   InformeDAO getInformeDAO();
   ExamenDAO getExamenDAO();
   EspecialidadDAO getEspecialidadDAO();
   DiaDAO getDiaDAO();
   PersonaDAO getPersonaDAO();
   ResultadoDAO getResultadoDAO();
   AdministradorDAO getAdministradorDAO();
   LaboratoristasDAO getLaboratoristasDAO();
   
   ConsultaDAO getConsultaDAO();
   TurnoDAO getTurnoDAO();
   CitaDAO getCitaDAO();
   MedicoDAO getMedicoDAO();
   UsuarioDAO getUsuarioDAO();
   PacientesDAO getPacientesDAO();
   ExamenPacienteDAO getExamenPacienteDAO();
   ExamenLaboratoristaDAO getExamenLaboratoristaDAO();
}
