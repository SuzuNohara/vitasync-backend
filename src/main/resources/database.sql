-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema vitasync
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vitasync
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vitasync` DEFAULT CHARACTER SET utf8 ;
USE `vitasync` ;

-- -----------------------------------------------------
-- Table `vitasync`.`tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`tema` (
                                                 `id` INT NOT NULL,
                                                 `nombre` VARCHAR(45) NULL,
    `configuracion` VARCHAR(250) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`usuario` (
                                                    `id` INT NOT NULL,
                                                    `nombres` VARCHAR(45) NULL,
    `apellidos` VARCHAR(45) NULL,
    `email` VARCHAR(45) NULL,
    `contrasena` VARCHAR(45) NULL,
    `tema` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_tema_idx` (`tema` ASC) VISIBLE,
    CONSTRAINT `usuario_tema`
    FOREIGN KEY (`tema`)
    REFERENCES `vitasync`.`tema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categoria` (
                                                      `id` INT NOT NULL,
                                                      `nombre` VARCHAR(45) NULL,
    `color` VARCHAR(7) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`tarea` (
                                                  `id` INT NOT NULL,
                                                  `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(120) NULL,
    `creacion` DATETIME NULL,
    `entrega` DATETIME NULL,
    `inicio` DATETIME NULL,
    `fin` DATETIME NULL,
    `prioridad` ENUM('baja', 'media', 'alta', 'critica') NULL,
    `dificultado` ENUM('facil', 'media', 'dificil') NULL,
    `estado` ENUM('Pendiente', 'En progreso', 'Hecho', 'Cancelado') NULL,
    `usuario` INT NULL,
    `dependencia` INT NULL,
    `subtarea_de` INT NULL,
    `categoria` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_tarea_idx` (`usuario` ASC) VISIBLE,
    INDEX `dependencia_tarea_idx` (`dependencia` ASC) VISIBLE,
    INDEX `subtarea_tarea_idx` (`subtarea_de` ASC) VISIBLE,
    INDEX `tarea_categoria_idx` (`categoria` ASC) VISIBLE,
    CONSTRAINT `usuario_tarea`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `dependencia_tarea`
    FOREIGN KEY (`dependencia`)
    REFERENCES `vitasync`.`tarea` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `subtarea_tarea`
    FOREIGN KEY (`subtarea_de`)
    REFERENCES `vitasync`.`tarea` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `tarea_categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `vitasync`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`categoria_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categoria_evento` (
                                                             `id` INT NOT NULL,
                                                             `nombre` VARCHAR(45) NULL,
    `color` VARCHAR(7) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`repeticion_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`repeticion_evento` (
                                                              `id` INT NOT NULL,
                                                              `dia` INT NULL,
                                                              `semana` INT NULL,
                                                              `mes` INT NULL,
                                                              `anio` INT NULL,
                                                              `dias` VARCHAR(45) NULL,
    `fin` DATETIME NULL,
    `forever` TINYINT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`evento` (
                                                   `id` INT NOT NULL,
                                                   `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(45) NULL,
    `ubicacion` VARCHAR(200) NULL,
    `inicio` DATETIME NULL,
    `fin` DATETIME NULL,
    `allday` TINYINT NULL,
    `repetir` INT NULL,
    `categoria` INT NULL,
    `usuario` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `evento_categoria_idx` (`categoria` ASC) VISIBLE,
    INDEX `evento_repeticion_idx` (`repetir` ASC) VISIBLE,
    INDEX `usuario_evento_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `evento_categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `vitasync`.`categoria_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `evento_repeticion`
    FOREIGN KEY (`repetir`)
    REFERENCES `vitasync`.`repeticion_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `usuario_evento`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`rutina` (
                                                   `id` INT NOT NULL,
                                                   `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(200) NULL,
    `inicio` TIME NULL,
    `duracion` INT NULL,
    `repeticion` SET('D', 'L', 'MA', 'MI', 'J', 'V', 'S') NULL,
    `activa` TINYINT NULL,
    `usuario` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `rutina_usuario_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `rutina_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`pasos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`pasos` (
                                                  `id` INT NOT NULL,
                                                  `orden` INT NULL,
                                                  `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(200) NULL,
    `duracion` INT NULL,
    `rutina` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `pasos_rutina_idx` (`rutina` ASC) VISIBLE,
    CONSTRAINT `pasos_rutina`
    FOREIGN KEY (`rutina`)
    REFERENCES `vitasync`.`rutina` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`registro_rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`registro_rutina` (
                                                            `id` INT NOT NULL,
                                                            `rutina` INT NULL,
                                                            `fecha` DATETIME NULL,
                                                            `inicio` TIME NULL,
                                                            `pasos` INT NULL,
                                                            PRIMARY KEY (`id`),
    INDEX `registro_rutina_fk_idx` (`rutina` ASC) VISIBLE,
    CONSTRAINT `registro_rutina_fk`
    FOREIGN KEY (`rutina`)
    REFERENCES `vitasync`.`rutina` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`habito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`habito` (
                                                   `id` INT NOT NULL,
                                                   `nombre` VARCHAR(45) NULL,
    `descipcion` VARCHAR(200) NULL,
    `frecuencia` ENUM('DIARIA', 'SEMANAL', 'MENSUAL', 'ANUAL') NULL,
    `objetivo` INT NULL,
    `inicio` DATE NULL,
    `fin` DATE NULL,
    `activo` TINYINT NULL,
    `usuario` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `habito_usuario_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `habito_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`registro_habito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`registro_habito` (
                                                            `id` INT NOT NULL,
                                                            `habito` INT NULL,
                                                            `fecha` DATE NULL,
                                                            `realizado` TINYINT NULL,
                                                            PRIMARY KEY (`id`),
    INDEX `registro_habito_fk_idx` (`habito` ASC) VISIBLE,
    CONSTRAINT `registro_habito_fk`
    FOREIGN KEY (`habito`)
    REFERENCES `vitasync`.`habito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`categoria_transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categoria_transaccion` (
                                                                  `id` INT NOT NULL,
                                                                  `nombre` VARCHAR(45) NULL,
    `icono` VARCHAR(45) NULL,
    `color` VARCHAR(7) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`transaccion` (
                                                        `id` INT NOT NULL,
                                                        `tipo` ENUM('INGRESO', 'EGRESO') NULL,
    `categoria` INT NULL,
    `monto` DECIMAL(10,2) NULL,
    `fecha` DATE NULL,
    `descripcion` VARCHAR(200) NULL,
    `recurrencia` TINYINT NULL,
    `usuario` INT NULL,
    `frecuencia` ENUM('DIARIA', 'SEMANAL', 'MENSUAL', 'ANUAL') NULL,
    `fin` DATE NULL,
    PRIMARY KEY (`id`),
    INDEX `transaccion_usuario_idx` (`usuario` ASC) VISIBLE,
    INDEX `categoria_transaccion_idx` (`categoria` ASC) VISIBLE,
    CONSTRAINT `transaccion_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `categoria_transaccion`
    FOREIGN KEY (`categoria`)
    REFERENCES `vitasync`.`categoria_transaccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`objetivo_ahorro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`objetivo_ahorro` (
                                                            `id` INT NOT NULL,
                                                            `nombre` VARCHAR(45) NULL,
    `descipcion` VARCHAR(200) NULL,
    `objetivo` DECIMAL(10,2) NULL,
    `monto` DECIMAL(10,2) NULL,
    `inicio` DATE NULL,
    `limite` DATE NULL,
    `prioridad` ENUM('ALTA', 'MEDIA', 'BAJA') NULL,
    `estado` ENUM('ACTIVO', 'COMPLETADO', 'CANCELADO') NULL,
    `usuario` INT NULL,
    `transaccion` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_objetivo_idx` (`usuario` ASC) VISIBLE,
    INDEX `transaccion_objetivo_idx` (`transaccion` ASC) VISIBLE,
    CONSTRAINT `usuario_objetivo`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `transaccion_objetivo`
    FOREIGN KEY (`transaccion`)
    REFERENCES `vitasync`.`transaccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`quehacer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`quehacer` (
                                                     `id` INT NOT NULL,
                                                     `nombre` VARCHAR(45) NULL,
    `descripccion` VARCHAR(200) NULL,
    `frecuencia` ENUM('UNICA', 'DIARIA', 'SEMANAL', 'MENSUAL') NULL,
    `dias` SET('S', 'M', 'T', 'W', 'TH', 'F', 'SA') NULL,
    `activo` TINYINT NULL,
    `usuario` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_quehacer_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `usuario_quehacer`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`registro_quehacer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`registro_quehacer` (
                                                              `id` INT NOT NULL,
                                                              `quehacer` INT NULL,
                                                              `fecha` DATETIME NULL,
                                                              `estado` TINYINT NULL,
                                                              PRIMARY KEY (`id`),
    INDEX `registro_quehacer_fk_idx` (`quehacer` ASC) VISIBLE,
    CONSTRAINT `registro_quehacer_fk`
    FOREIGN KEY (`quehacer`)
    REFERENCES `vitasync`.`quehacer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`compra` (
                                                   `id` INT NOT NULL,
                                                   `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(200) NULL,
    `completado` TINYINT NULL,
    `usuario` INT NULL,
    `cantidad` INT NULL,
    `prioridad` ENUM('alta', 'media', 'baja') NULL,
    `notas` VARCHAR(200) NULL,
    PRIMARY KEY (`id`),
    INDEX `compra_usuario_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `compra_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitasync`.`emocion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`emocion` (
                                                    `id` INT NOT NULL,
                                                    `fecha` DATE NULL,
                                                    `hora` TIME NULL,
                                                    `emocion` VARCHAR(45) NULL,
    `intensidad` INT NULL,
    `tipo` ENUM('POSITIVA', 'NEGATIVA', 'NEUTRA') NULL,
    `color` VARCHAR(7) NULL,
    `usuario` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_emocion_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `usuario_emocion`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
