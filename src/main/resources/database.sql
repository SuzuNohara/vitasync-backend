-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema vitasync
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vitasync
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vitasync` DEFAULT CHARACTER SET utf8mb3 ;
USE `vitasync` ;

-- -----------------------------------------------------
-- Table `vitasync`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categoria` (
                                                      `id` INT NOT NULL,
                                                      `nombre_categoria` VARCHAR(100) NULL DEFAULT NULL,
    `color_categoria` VARCHAR(7) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`categoria_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categoria_evento` (
                                                             `id` INT NOT NULL,
                                                             `nombre` VARCHAR(45) NULL DEFAULT NULL,
    `color` VARCHAR(7) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`categoria_transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categoria_transaccion` (
                                                                  `id` INT NOT NULL,
                                                                  `nombre` VARCHAR(45) NULL DEFAULT NULL,
    `icono` VARCHAR(45) NULL DEFAULT NULL,
    `color` VARCHAR(7) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`categorias` (
                                                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `descripcion_categoria` VARCHAR(255) NULL DEFAULT NULL,
    `nombre_categoria` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`tema` (
                                                 `id` INT NOT NULL,
                                                 `nombre` VARCHAR(100) NULL DEFAULT NULL,
    `configuracion` TEXT NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`usuario` (
                                                    `id` INT NOT NULL,
                                                    `nombre_usuario` VARCHAR(255) NOT NULL,
    `apellido_usuario` VARCHAR(255) NULL DEFAULT NULL,
    `correo_electronico` VARCHAR(255) NOT NULL,
    `clave_acceso` VARCHAR(255) NULL DEFAULT NULL,
    `tema` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_tema_idx` (`tema` ASC) VISIBLE,
    CONSTRAINT `usuario_tema`
    FOREIGN KEY (`tema`)
    REFERENCES `vitasync`.`tema` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`compra` (
                                                   `id` INT NOT NULL,
                                                   `nombre` VARCHAR(45) NULL DEFAULT NULL,
    `descripcion` VARCHAR(200) NULL DEFAULT NULL,
    `completado` TINYINT NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    `cantidad` INT NULL DEFAULT NULL,
    `prioridad` ENUM('alta', 'media', 'baja') NULL DEFAULT NULL,
    `notas` VARCHAR(200) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `compra_usuario_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `compra_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`emocion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`emocion` (
                                                    `id` INT NOT NULL,
                                                    `fecha` DATE NULL DEFAULT NULL,
                                                    `hora` TIME NULL DEFAULT NULL,
                                                    `emocion` VARCHAR(45) NULL DEFAULT NULL,
    `intensidad` INT NULL DEFAULT NULL,
    `tipo` ENUM('POSITIVA', 'NEGATIVA', 'NEUTRA') NULL DEFAULT NULL,
    `color` VARCHAR(7) NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_emocion_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `usuario_emocion`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`repeticion_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`repeticion_evento` (
                                                              `id` INT NOT NULL,
                                                              `dia_repeticion` INT NULL DEFAULT NULL,
                                                              `semana_repeticion` INT NULL DEFAULT NULL,
                                                              `mes_repeticion` INT NULL DEFAULT NULL,
                                                              `anio_repeticion` INT NULL DEFAULT NULL,
                                                              `dias_repeticion` VARCHAR(45) NULL DEFAULT NULL,
    `fin` DATETIME NULL DEFAULT NULL,
    `forever` TINYINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`evento` (
                                                   `id` INT NOT NULL,
                                                   `nombre_evento` VARCHAR(45) NULL DEFAULT NULL,
    `descripcion_evento` VARCHAR(200) NULL DEFAULT NULL,
    `ubicacion_evento` VARCHAR(200) NULL DEFAULT NULL,
    `fecha_inicio_evento` DATETIME NULL DEFAULT NULL,
    `fecha_fin_evento` DATETIME NULL DEFAULT NULL,
    `allday` TINYINT NULL DEFAULT NULL,
    `repetir` INT NULL DEFAULT NULL,
    `categoria` INT NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `evento_categoria_idx` (`categoria` ASC) VISIBLE,
    INDEX `evento_repeticion_idx` (`repetir` ASC) VISIBLE,
    INDEX `usuario_evento_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `evento_categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `vitasync`.`categoria_evento` (`id`),
    CONSTRAINT `evento_repeticion`
    FOREIGN KEY (`repetir`)
    REFERENCES `vitasync`.`repeticion_evento` (`id`),
    CONSTRAINT `usuario_evento`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`habito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`habito` (
                                                   `id` INT NOT NULL,
                                                   `nombre` VARCHAR(45) NULL DEFAULT NULL,
    `descipcion` VARCHAR(200) NULL DEFAULT NULL,
    `frecuencia` ENUM('DIARIA', 'SEMANAL', 'MENSUAL', 'ANUAL') NULL DEFAULT NULL,
    `objetivo` INT NULL DEFAULT NULL,
    `inicio` DATE NULL DEFAULT NULL,
    `fin` DATE NULL DEFAULT NULL,
    `activo` TINYINT NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `habito_usuario_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `habito_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`transaccion` (
                                                        `id` INT NOT NULL,
                                                        `tipo` ENUM('INGRESO', 'EGRESO') NULL DEFAULT NULL,
    `categoria` INT NULL DEFAULT NULL,
    `monto` DECIMAL(10,2) NULL DEFAULT NULL,
    `fecha` DATE NULL DEFAULT NULL,
    `descripcion` VARCHAR(200) NULL DEFAULT NULL,
    `recurrencia` TINYINT NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    `frecuencia` ENUM('DIARIA', 'SEMANAL', 'MENSUAL', 'ANUAL') NULL DEFAULT NULL,
    `fin` DATE NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `transaccion_usuario_idx` (`usuario` ASC) VISIBLE,
    INDEX `categoria_transaccion_idx` (`categoria` ASC) VISIBLE,
    CONSTRAINT `categoria_transaccion`
    FOREIGN KEY (`categoria`)
    REFERENCES `vitasync`.`categoria_transaccion` (`id`),
    CONSTRAINT `transaccion_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`objetivo_ahorro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`objetivo_ahorro` (
                                                            `id` INT NOT NULL,
                                                            `nombre` VARCHAR(45) NULL DEFAULT NULL,
    `descipcion` VARCHAR(200) NULL DEFAULT NULL,
    `objetivo` DECIMAL(10,2) NULL DEFAULT NULL,
    `monto` DECIMAL(10,2) NULL DEFAULT NULL,
    `inicio` DATE NULL DEFAULT NULL,
    `limite` DATE NULL DEFAULT NULL,
    `prioridad` ENUM('ALTA', 'MEDIA', 'BAJA') NULL DEFAULT NULL,
    `estado` ENUM('ACTIVO', 'COMPLETADO', 'CANCELADO') NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    `transaccion` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_objetivo_idx` (`usuario` ASC) VISIBLE,
    INDEX `transaccion_objetivo_idx` (`transaccion` ASC) VISIBLE,
    CONSTRAINT `transaccion_objetivo`
    FOREIGN KEY (`transaccion`)
    REFERENCES `vitasync`.`transaccion` (`id`),
    CONSTRAINT `usuario_objetivo`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`rutina` (
                                                   `id` INT NOT NULL,
                                                   `nombre_rutina` VARCHAR(255) NOT NULL,
    `descripcion_rutina` VARCHAR(255) NULL DEFAULT NULL,
    `hora_inicio_rutina` TIME NULL DEFAULT NULL,
    `duracion_rutina_minutos` INT NULL DEFAULT NULL,
    `repeticion` SET('D', 'L', 'MA', 'MI', 'J', 'V', 'S') NULL DEFAULT NULL,
    `activa` TINYINT NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    `usuario_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `rutina_usuario_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `rutina_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`pasos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`pasos` (
                                                  `id` INT NOT NULL,
                                                  `orden_paso` INT NULL DEFAULT NULL,
                                                  `nombre_paso` VARCHAR(45) NULL DEFAULT NULL,
    `descripcion_paso` VARCHAR(200) NULL DEFAULT NULL,
    `duracion_paso_minutos` INT NULL DEFAULT NULL,
    `rutina` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `pasos_rutina_idx` (`rutina` ASC) VISIBLE,
    CONSTRAINT `pasos_rutina`
    FOREIGN KEY (`rutina`)
    REFERENCES `vitasync`.`rutina` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`quehacer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`quehacer` (
                                                     `id` INT NOT NULL,
                                                     `nombre` VARCHAR(45) NULL DEFAULT NULL,
    `descripccion` VARCHAR(200) NULL DEFAULT NULL,
    `frecuencia` ENUM('UNICA', 'DIARIA', 'SEMANAL', 'MENSUAL') NULL DEFAULT NULL,
    `dias` SET('S', 'M', 'T', 'W', 'TH', 'F', 'SA') NULL DEFAULT NULL,
    `activo` TINYINT NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_quehacer_idx` (`usuario` ASC) VISIBLE,
    CONSTRAINT `usuario_quehacer`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`registro_habito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`registro_habito` (
                                                            `id` INT NOT NULL,
                                                            `habito` INT NULL DEFAULT NULL,
                                                            `fecha` DATE NULL DEFAULT NULL,
                                                            `realizado` TINYINT NULL DEFAULT NULL,
                                                            PRIMARY KEY (`id`),
    INDEX `registro_habito_fk_idx` (`habito` ASC) VISIBLE,
    CONSTRAINT `registro_habito_fk`
    FOREIGN KEY (`habito`)
    REFERENCES `vitasync`.`habito` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`registro_quehacer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`registro_quehacer` (
                                                              `id` INT NOT NULL,
                                                              `quehacer` INT NULL DEFAULT NULL,
                                                              `fecha` DATETIME NULL DEFAULT NULL,
                                                              `estado` TINYINT NULL DEFAULT NULL,
                                                              PRIMARY KEY (`id`),
    INDEX `registro_quehacer_fk_idx` (`quehacer` ASC) VISIBLE,
    CONSTRAINT `registro_quehacer_fk`
    FOREIGN KEY (`quehacer`)
    REFERENCES `vitasync`.`quehacer` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`registro_rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`registro_rutina` (
                                                            `id` INT NOT NULL,
                                                            `rutina` INT NULL DEFAULT NULL,
                                                            `fecha` DATETIME NULL DEFAULT NULL,
                                                            `hora_inicio` TIME NULL DEFAULT NULL,
                                                            `pasos_completados` INT NULL DEFAULT NULL,
                                                            PRIMARY KEY (`id`),
    INDEX `registro_rutina_fk_idx` (`rutina` ASC) VISIBLE,
    CONSTRAINT `registro_rutina_fk`
    FOREIGN KEY (`rutina`)
    REFERENCES `vitasync`.`rutina` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`rutinas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`rutinas` (
                                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `descripcion_rutina` VARCHAR(255) NULL DEFAULT NULL,
    `duracion_rutina_minutos` INT NULL DEFAULT NULL,
    `hora_inicio_rutina` TIME NULL DEFAULT NULL,
    `nombre_rutina` VARCHAR(255) NOT NULL,
    `usuario_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`tarea` (
                                                  `id` INT NOT NULL,
                                                  `nombre_tarea` VARCHAR(255) NOT NULL,
    `descripcion_tarea` VARCHAR(255) NULL DEFAULT NULL,
    `fecha_creacion_tarea` DATETIME NULL DEFAULT NULL,
    `fecha_entrega_tarea` DATE NULL DEFAULT NULL,
    `fecha_inicio_tarea` DATETIME NULL DEFAULT NULL,
    `fecha_fin_tarea` DATETIME NULL DEFAULT NULL,
    `fecha_actualizacion` DATETIME NULL DEFAULT NULL,
    `prioridad` ENUM('baja', 'media', 'alta', 'critica') NULL DEFAULT NULL,
    `dificultado` ENUM('facil', 'media', 'dificil') NULL DEFAULT NULL,
    `estado` ENUM('Pendiente', 'En progreso', 'Hecho', 'Cancelado') NULL DEFAULT NULL,
    `usuario` INT NULL DEFAULT NULL,
    `dependencia` INT NULL DEFAULT NULL,
    `subtarea_de` INT NULL DEFAULT NULL,
    `categoria` INT NULL DEFAULT NULL,
    `usuario_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `usuario_tarea_idx` (`usuario` ASC) VISIBLE,
    INDEX `dependencia_tarea_idx` (`dependencia` ASC) VISIBLE,
    INDEX `subtarea_tarea_idx` (`subtarea_de` ASC) VISIBLE,
    INDEX `tarea_categoria_idx` (`categoria` ASC) VISIBLE,
    CONSTRAINT `dependencia_tarea`
    FOREIGN KEY (`dependencia`)
    REFERENCES `vitasync`.`tarea` (`id`),
    CONSTRAINT `subtarea_tarea`
    FOREIGN KEY (`subtarea_de`)
    REFERENCES `vitasync`.`tarea` (`id`),
    CONSTRAINT `tarea_categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `vitasync`.`categoria` (`id`),
    CONSTRAINT `usuario_tarea`
    FOREIGN KEY (`usuario`)
    REFERENCES `vitasync`.`usuario` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`tareas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`tareas` (
                                                   `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `descripcion_tarea` VARCHAR(255) NULL DEFAULT NULL,
    `fecha_creacion_tarea` DATETIME(6) NULL DEFAULT NULL,
    `fecha_entrega_tarea` DATE NULL DEFAULT NULL,
    `fecha_fin_tarea` DATETIME(6) NULL DEFAULT NULL,
    `fecha_inicio_tarea` DATETIME(6) NULL DEFAULT NULL,
    `nombre_tarea` VARCHAR(255) NOT NULL,
    `usuario_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `vitasync`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitasync`.`usuarios` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `apellido_usuario` VARCHAR(255) NULL DEFAULT NULL,
    `clave_acceso` VARCHAR(255) NOT NULL,
    `correo_electronico` VARCHAR(255) NOT NULL,
    `nombre_usuario` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UKduxldumspflsqyka52vo72hse` (`correo_electronico` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
