-- MySQL Workbench Synchronization
-- Generated: 2020-09-29 08:11
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Julio

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `Hospital`.`Administrador` 
CHANGE COLUMN `codigo` `codigo` VARCHAR(13) NOT NULL ;

CREATE TABLE IF NOT EXISTS `Hospital`.`ExamenLaboratorista` (
  `idExamenLaboratorista` INT(11) NOT NULL AUTO_INCREMENT,
  `Examen_Codigo` VARCHAR(45) NOT NULL,
  `Laboratoristas_registro` INT(11) NOT NULL,
  `estado` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`idExamenLaboratorista`),
  INDEX `fk_Examen_has_Laboratoristas_Laboratoristas1_idx` (`Laboratoristas_registro` ASC),
  INDEX `fk_Examen_has_Laboratoristas_Examen1_idx` (`Examen_Codigo` ASC),
  CONSTRAINT `fk_Examen_has_Laboratoristas_Examen1`
    FOREIGN KEY (`Examen_Codigo`)
    REFERENCES `Hospital`.`Examen` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Examen_has_Laboratoristas_Laboratoristas1`
    FOREIGN KEY (`Laboratoristas_registro`)
    REFERENCES `Hospital`.`Laboratoristas` (`registro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
