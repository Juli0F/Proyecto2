

CREATE DATABASE IF NOT EXISTS Hospital;
USE Hospital;

CREATE TABLE IF NOT EXISTS Examen (
  Codigo VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NULL DEFAULT NULL,
  orden TINYINT(1) NULL DEFAULT NULL,
  descripcion VARCHAR(2000) NULL DEFAULT NULL,
  costo DOUBLE NULL DEFAULT NULL,
  formato VARCHAR(45) NULL DEFAULT NULL,
  estado VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (Codigo));

CREATE TABLE IF NOT EXISTS Consulta (
  idConsulta INT(11) NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(45) NULL DEFAULT NULL,
  costo VARCHAR(45) NULL DEFAULT NULL,
  estado VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (idConsulta));


CREATE TABLE IF NOT EXISTS Persona (
  dpi VARCHAR(13) NOT NULL,
  nombre VARCHAR(45) NULL DEFAULT NULL,
  telefono VARCHAR(45) NULL DEFAULT NULL,
  correo VARCHAR(45) NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (dpi));


CREATE TABLE IF NOT EXISTS Pacientes (
  codigo VARCHAR(45) NOT NULL,
  masculino VARCHAR(13) NULL DEFAULT NULL,
  fecha DATE NULL DEFAULT NULL,
  peso VARCHAR(45) NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  tipo_de_sangre VARCHAR(45) NULL DEFAULT NULL,
  Persona_dpi VARCHAR(13) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Pacientes_Persona1
    FOREIGN KEY (Persona_dpi)
    REFERENCES Persona (dpi)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS Medico (
  colegiado INT(11) NOT NULL,
  inicio DATE NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  horaEntrada TIME NULL DEFAULT NULL,
  horaSalida TIME NULL DEFAULT NULL,
  Persona_dpi VARCHAR(13) NOT NULL,
  PRIMARY KEY (colegiado),
  CONSTRAINT fk_Medicos_Persona1
    FOREIGN KEY (Persona_dpi)
    REFERENCES Persona (dpi)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Turno (
  idTurno INT(11) NOT NULL AUTO_INCREMENT,
  turno VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (idTurno));


CREATE TABLE IF NOT EXISTS Laboratoristas (
  registro VARCHAR(45) NOT NULL,
  inicio DATE NULL DEFAULT NULL,
  ocupado VARCHAR(45) NULL DEFAULT NULL,
  estado VARCHAR(45) NULL DEFAULT NULL,
  Persona_dpi VARCHAR(13) NOT NULL,
  PRIMARY KEY (registro),
  CONSTRAINT fk_Laboratoristas_Persona1
    FOREIGN KEY (Persona_dpi)
    REFERENCES Persona (dpi)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS Usuario (
  codigo VARCHAR(45) NOT NULL,
  clave VARCHAR(45) NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  Persona_dpi VARCHAR(13) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Usuario_Persona
    FOREIGN KEY (Persona_dpi)
    REFERENCES Persona (dpi)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS Cita (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(45) NULL DEFAULT NULL,
  estado VARCHAR(45) NULL DEFAULT NULL,
  Pacientes_codigo VARCHAR(13) NOT NULL,
  Consulta_idConsulta INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Cita_Consulta1
    FOREIGN KEY (Consulta_idConsulta)
    REFERENCES Consulta (idConsulta)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Cita_Pacientes1
    FOREIGN KEY (Pacientes_codigo)
    REFERENCES Pacientes (codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Agenda (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  estado VARCHAR(45) NULL DEFAULT NULL,
  Medico_colegiado INT(11) NULL DEFAULT NULL,
  Laboratoristas_registro VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Agenda_Laboratoristas1
    FOREIGN KEY (Laboratoristas_registro)
    REFERENCES Laboratoristas (registro)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Agenda_Medico1
    FOREIGN KEY (Medico_colegiado)
    REFERENCES Medico (colegiado)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS Dia (
  idDia INT(11) NOT NULL AUTO_INCREMENT,
  fecha DATE NULL DEFAULT NULL,
  hora TIME NULL DEFAULT NULL,
  descripcion VARCHAR(45) NULL DEFAULT NULL,
  Agenda_codigo INT(11) NOT NULL,
  Cita_codigo INT(11) NOT NULL,
  PRIMARY KEY (idDia),
  CONSTRAINT fk_Dia_Agenda1
    FOREIGN KEY (Agenda_codigo)
    REFERENCES Agenda (codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Dia_Cita1
    FOREIGN KEY (Cita_codigo)
    REFERENCES Cita (codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Informe (
  idInforme VARCHAR(13) NOT NULL,
  descripcion VARCHAR(2000) NULL DEFAULT NULL,
  fechaHora DATETIME NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT '1',
  hora VARCHAR(45) NULL DEFAULT NULL,
  Medico_colegiado INT(11) NOT NULL,
  Cita_codigo INT(11) NOT NULL,
  PRIMARY KEY (idInforme),
  CONSTRAINT fk_Informe_Cita1
    FOREIGN KEY (Cita_codigo)
    REFERENCES Cita (codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Informe_Medico1
    FOREIGN KEY (Medico_colegiado)
    REFERENCES Medico (colegiado)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS Especialidad (
  idEspecialidad INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT '1',
  Medico_colegiado INT(11) NOT NULL,
  PRIMARY KEY (idEspecialidad),
  CONSTRAINT fk_Especialidad_Medico1
    FOREIGN KEY (Medico_colegiado)
    REFERENCES Medico (colegiado)
    ON DELETE CASCADE
    ON UPDATE CASCADE);



CREATE TABLE IF NOT EXISTS Administrador (
  codigo VARCHAR(13) NOT NULL,
  Persona_dpi VARCHAR(13) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Administrador_Persona1
    FOREIGN KEY (Persona_dpi)
    REFERENCES Persona (dpi)
    ON DELETE CASCADE
    ON UPDATE CASCADE);



CREATE TABLE IF NOT EXISTS ExamenPaciente (
  idExamenPaciente INT(11) NOT NULL AUTO_INCREMENT,
  Examen_Codigo VARCHAR(45) NOT NULL,
  Laboratoristas_registro VARCHAR(45) NOT NULL,
  Pacientes_codigo VARCHAR(13) NOT NULL,
  Medico_colegiado INT(11) NULL DEFAULT NULL,
  realizado TINYINT(1) NULL DEFAULT NULL,
  cancelar TINYINT(1) NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  ordenPath VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (idExamenPaciente),
  CONSTRAINT fk_Resultado_has_Examen_Examen1
    FOREIGN KEY (Examen_Codigo)
    REFERENCES Examen (Codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Resultado_has_Examen_Laboratoristas1
    FOREIGN KEY (Laboratoristas_registro)
    REFERENCES Laboratoristas (registro)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Resultado_has_Examen_Medico1
    FOREIGN KEY (Medico_colegiado)
    REFERENCES Medico (colegiado)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Resultado_has_Examen_Pacientes1
    FOREIGN KEY (Pacientes_codigo)
    REFERENCES Pacientes (codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS Resultado (
  resultadoCodigo VARCHAR(13) NOT NULL,
  orden MEDIUMBLOB NULL DEFAULT NULL,
  descripcion VARCHAR(100) NULL DEFAULT NULL,
  fechaHora DATETIME NULL DEFAULT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  informeArchivo MEDIUMBLOB NULL DEFAULT NULL,
  ExamenPaciente_idExamenPaciente INT(11) NOT NULL,
  PRIMARY KEY (resultadoCodigo),
  CONSTRAINT fk_Resultado_ExamenPaciente1
    FOREIGN KEY (ExamenPaciente_idExamenPaciente)
    REFERENCES ExamenPaciente (idExamenPaciente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS DiaTrabajo (
  idTrabajoLaboratoristaLaboratorista INT(11) NOT NULL AUTO_INCREMENT,
  Turno_idTurno INT(11) NOT NULL,
  Laboratoristas_registro VARCHAR(45) NOT NULL,
  estado TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (idTrabajoLaboratoristaLaboratorista, Turno_idTurno, Laboratoristas_registro),
  CONSTRAINT fk_Laboratoristas_has_Turno_Laboratoristas1
    FOREIGN KEY (Laboratoristas_registro)
    REFERENCES Laboratoristas (registro)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Laboratoristas_has_Turno_Turno1
    FOREIGN KEY (Turno_idTurno)
    REFERENCES Turno (idTurno)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS ExamenLaboratorista (
  idExamenLaboratorista INT(11) NOT NULL AUTO_INCREMENT,
  Examen_Codigo VARCHAR(45) NOT NULL,
  Laboratoristas_registro VARCHAR(45) NOT NULL,
  estado TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (idExamenLaboratorista),
  CONSTRAINT fk_Examen_has_Laboratoristas_Examen1
    FOREIGN KEY (Examen_Codigo)
    REFERENCES Examen (Codigo)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Examen_has_Laboratoristas_Laboratoristas1
    FOREIGN KEY (Laboratoristas_registro)
    REFERENCES Laboratoristas (registro)
    ON DELETE CASCADE
    ON UPDATE CASCADE);



 INSERT INTO Turno (turno) values("Lunes"),("Martes"),("Miercoles"),("Jueves"),("Viernes"),("Sabado"),("Domingo");
INSERT INTO Consulta (idConsulta, tipo, costo, estado) VALUES ('1', 'Auxiliar', '0.0', '1');


