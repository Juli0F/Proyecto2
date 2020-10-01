-- MySQL Workbench Synchronization
-- Generated: 2020-09-28 09:16
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Julio

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Hospital` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `Hospital`.`Persona` (
  `dpi` VARCHAR(13) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `correo` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`dpi`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Pacientes` (
  `codigo` INT(11) NOT NULL,
  `masculino` TINYINT(1) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `peso` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `tipo_de_sangre` VARCHAR(45) NULL DEFAULT NULL,
  `Persona_dpi` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Pacientes_Persona1_idx` (`Persona_dpi` ASC),
  CONSTRAINT `fk_Pacientes_Persona1`
    FOREIGN KEY (`Persona_dpi`)
    REFERENCES `Hospital`.`Persona` (`dpi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Medico` (
  `colegiado` INT(11) NOT NULL,
  `inicio` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `horaEntrada` TIME NULL DEFAULT NULL,
  `horaSalida` TIME NULL DEFAULT NULL,
  `Persona_dpi` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`colegiado`),
  INDEX `fk_Medicos_Persona1_idx` (`Persona_dpi` ASC),
  CONSTRAINT `fk_Medicos_Persona1`
    FOREIGN KEY (`Persona_dpi`)
    REFERENCES `Hospital`.`Persona` (`dpi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Laboratoristas` (
  `registro` INT(11) NOT NULL,
  `inicio` DATE NULL DEFAULT NULL,
  `ocupado` VARCHAR(45) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  `Persona_dpi` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`registro`),
  INDEX `fk_Laboratoristas_Persona1_idx` (`Persona_dpi` ASC),
  CONSTRAINT `fk_Laboratoristas_Persona1`
    FOREIGN KEY (`Persona_dpi`)
    REFERENCES `Hospital`.`Persona` (`dpi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Usuario` (
  `codigo` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `Persona_dpi` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Usuario_Persona_idx` (`Persona_dpi` ASC),
  CONSTRAINT `fk_Usuario_Persona`
    FOREIGN KEY (`Persona_dpi`)
    REFERENCES `Hospital`.`Persona` (`dpi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Turno` (
  `idTurno` INT(11) NOT NULL AUTO_INCREMENT,
  `turno` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idTurno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Examen` (
  `Codigo` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `orden` TINYINT(1) NULL DEFAULT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `costo` DOUBLE NULL DEFAULT NULL,
  `formato` VARCHAR(45) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Informe` (
  `idInforme` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(2000) NULL DEFAULT NULL,
  `fechaHora` DATETIME NULL DEFAULT NULL,
  `Medico_colegiado` INT(11) NOT NULL,
  `estado` TINYINT(1) NULL DEFAULT '1',
  `Consulta_idConsulta` INT(11) NOT NULL,
  `Pacientes_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`idInforme`),
  INDEX `fk_Informe_Medico1_idx` (`Medico_colegiado` ASC),
  INDEX `fk_Informe_Consulta1_idx` (`Consulta_idConsulta` ASC),
  INDEX `fk_Informe_Pacientes1_idx` (`Pacientes_codigo` ASC),
  CONSTRAINT `fk_Informe_Medico1`
    FOREIGN KEY (`Medico_colegiado`)
    REFERENCES `Hospital`.`Medico` (`colegiado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Informe_Consulta1`
    FOREIGN KEY (`Consulta_idConsulta`)
    REFERENCES `Hospital`.`Consulta` (`idConsulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Informe_Pacientes1`
    FOREIGN KEY (`Pacientes_codigo`)
    REFERENCES `Hospital`.`Pacientes` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Especialidad` (
  `idEspecialidad` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT 1,
  `Medico_colegiado` INT(11) NOT NULL,
  PRIMARY KEY (`idEspecialidad`),
  INDEX `fk_Especialidad_Medico1_idx` (`Medico_colegiado` ASC),
  CONSTRAINT `fk_Especialidad_Medico1`
    FOREIGN KEY (`Medico_colegiado`)
    REFERENCES `Hospital`.`Medico` (`colegiado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Cita` (
  `codigo` INT(11) NOT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  `Pacientes_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Cita_Pacientes1_idx` (`Pacientes_codigo` ASC),
  CONSTRAINT `fk_Cita_Pacientes1`
    FOREIGN KEY (`Pacientes_codigo`)
    REFERENCES `Hospital`.`Pacientes` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Dia` (
  `idDia` INT(11) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `hora` TIME NULL DEFAULT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `Agenda_codigo` INT(11) NOT NULL,
  `Cita_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`idDia`),
  INDEX `fk_Dia_Agenda1_idx` (`Agenda_codigo` ASC),
  INDEX `fk_Dia_Cita1_idx` (`Cita_codigo` ASC),
  CONSTRAINT `fk_Dia_Agenda1`
    FOREIGN KEY (`Agenda_codigo`)
    REFERENCES `Hospital`.`Agenda` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dia_Cita1`
    FOREIGN KEY (`Cita_codigo`)
    REFERENCES `Hospital`.`Cita` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Agenda` (
  `codigo` INT(11) NOT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  `Medico_colegiado` INT(11) NULL DEFAULT NULL,
  `Laboratoristas_registro` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Agenda_Medico1_idx` (`Medico_colegiado` ASC),
  INDEX `fk_Agenda_Laboratoristas1_idx` (`Laboratoristas_registro` ASC),
  CONSTRAINT `fk_Agenda_Medico1`
    FOREIGN KEY (`Medico_colegiado`)
    REFERENCES `Hospital`.`Medico` (`colegiado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agenda_Laboratoristas1`
    FOREIGN KEY (`Laboratoristas_registro`)
    REFERENCES `Hospital`.`Laboratoristas` (`registro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Administrador` (
  `codigo` INT(11) NOT NULL,
  `Persona_dpi` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Administrador_Persona1_idx` (`Persona_dpi` ASC),
  CONSTRAINT `fk_Administrador_Persona1`
    FOREIGN KEY (`Persona_dpi`)
    REFERENCES `Hospital`.`Persona` (`dpi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Consulta` (
  `idConsulta` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL DEFAULT NULL,
  `costo` VARCHAR(45) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idConsulta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`Resultado` (
  `resultadoCodigo` INT(11) NOT NULL,
  `orden` MEDIUMBLOB NULL DEFAULT NULL,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `fechaHora` DATETIME NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `informeArchivo` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`resultadoCodigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Hospital`.`ExamenPaciente` (
  `idExamenPaciente` INT(11) NOT NULL AUTO_INCREMENT,
  `Resultado_resultadoCodigo` INT(11) NOT NULL,
  `Examen_Codigo` VARCHAR(45) NOT NULL,
  `Laboratoristas_registro` INT(11) NOT NULL,
  `Pacientes_codigo` INT(11) NOT NULL,
  `Medico_colegiado` INT(11) NOT NULL,
  `realizado` TINYINT(1) NULL DEFAULT NULL,
  `cancelar` TINYINT(1) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idExamenPaciente`),
  INDEX `fk_Resultado_has_Examen_Examen1_idx` (`Examen_Codigo` ASC),
  INDEX `fk_Resultado_has_Examen_Resultado1_idx` (`Resultado_resultadoCodigo` ASC),
  INDEX `fk_Resultado_has_Examen_Laboratoristas1_idx` (`Laboratoristas_registro` ASC),
  INDEX `fk_Resultado_has_Examen_Pacientes1_idx` (`Pacientes_codigo` ASC),
  INDEX `fk_Resultado_has_Examen_Medico1_idx` (`Medico_colegiado` ASC),
  CONSTRAINT `fk_Resultado_has_Examen_Resultado1`
    FOREIGN KEY (`Resultado_resultadoCodigo`)
    REFERENCES `Hospital`.`Resultado` (`resultadoCodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resultado_has_Examen_Examen1`
    FOREIGN KEY (`Examen_Codigo`)
    REFERENCES `Hospital`.`Examen` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resultado_has_Examen_Laboratoristas1`
    FOREIGN KEY (`Laboratoristas_registro`)
    REFERENCES `Hospital`.`Laboratoristas` (`registro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resultado_has_Examen_Pacientes1`
    FOREIGN KEY (`Pacientes_codigo`)
    REFERENCES `Hospital`.`Pacientes` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resultado_has_Examen_Medico1`
    FOREIGN KEY (`Medico_colegiado`)
    REFERENCES `Hospital`.`Medico` (`colegiado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Examenes pendientes a un paciente, \ncuando el atributo \'realizar\' se encuentre como verdadero, el examen se habra realizado';

CREATE TABLE IF NOT EXISTS `Hospital`.`DiaTrabajo` (
  `idTrabajoLaboratoristaLaboratorista` INT(11) NOT NULL AUTO_INCREMENT,
  `Turno_idTurno` INT(11) NOT NULL,
  `Laboratoristas_registro` INT(11) NOT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idTrabajoLaboratoristaLaboratorista`, `Turno_idTurno`, `Laboratoristas_registro`),
  INDEX `fk_Laboratoristas_has_Turno_Turno1_idx` (`Turno_idTurno` ASC),
  INDEX `fk_Laboratoristas_has_Turno_Laboratoristas1_idx` (`Laboratoristas_registro` ASC),
  CONSTRAINT `fk_Laboratoristas_has_Turno_Laboratoristas1`
    FOREIGN KEY (`Laboratoristas_registro`)
    REFERENCES `Hospital`.`Laboratoristas` (`registro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Laboratoristas_has_Turno_Turno1`
    FOREIGN KEY (`Turno_idTurno`)
    REFERENCES `Hospital`.`Turno` (`idTurno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
