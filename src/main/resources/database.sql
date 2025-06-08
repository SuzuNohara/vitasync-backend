-- -----------------------------------------------------
-- Esquema vitasync
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vitasync` DEFAULT CHARACTER SET utf8;
USE `vitasync`;

-- -----------------------------------------------------
-- Tabla `tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tema` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `nombre` VARCHAR(45) NULL,
    `configuracion` VARCHAR(250) NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
                                         `id` INT NOT NULL AUTO_INCREMENT,
                                         `nombres` VARCHAR(45) NULL,
    `apellidos` VARCHAR(45) NULL,
    `correo` VARCHAR(45) NULL,
    `contrasena` VARCHAR(250) NULL,
    `tema_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_usuario_tema_idx` (`tema_id` ASC),
    CONSTRAINT `fk_usuario_tema`
    FOREIGN KEY (`tema_id`)
    REFERENCES `tema` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categoria` (
                                           `id` INT NOT NULL AUTO_INCREMENT,
                                           `nombre` VARCHAR(45) NULL,
    `color` VARCHAR(7) NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tarea` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(120) NULL,
    `fecha_creacion` DATETIME NULL,
    `fecha_entrega` DATETIME NULL,
    `fecha_inicio` DATETIME NULL,
    `fecha_fin` DATETIME NULL,
    `prioridad` ENUM('baja', 'media', 'alta', 'critica') NULL,
    `dificultad` ENUM('facil', 'media', 'dificil') NULL,
    `estado` ENUM('pendiente', 'en_progreso', 'hecho', 'cancelado') NULL,
    `usuario_id` INT NULL,
    `dependencia_id` INT NULL,
    `subtarea_de_id` INT NULL,
    `categoria_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_tarea_usuario_idx` (`usuario_id` ASC),
    INDEX `fk_tarea_dependencia_idx` (`dependencia_id` ASC),
    INDEX `fk_tarea_subtarea_idx` (`subtarea_de_id` ASC),
    INDEX `fk_tarea_categoria_idx` (`categoria_id` ASC),
    CONSTRAINT `fk_tarea_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_tarea_dependencia`
    FOREIGN KEY (`dependencia_id`)
    REFERENCES `tarea` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_tarea_subtarea`
    FOREIGN KEY (`subtarea_de_id`)
    REFERENCES `tarea` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_tarea_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(200) NULL,
    `ubicacion` VARCHAR(200) NULL,
    `fecha_inicio` DATETIME NULL,
    `fecha_fin` DATETIME NULL,
    `todo_el_dia` TINYINT NULL,
    `repeticion_id` INT NULL,
    `categoria_id` INT NULL,
    `usuario_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_evento_categoria_idx` (`categoria_id` ASC),
    INDEX `fk_evento_repeticion_idx` (`repeticion_id` ASC),
    INDEX `fk_evento_usuario_idx` (`usuario_id` ASC),
    CONSTRAINT `fk_evento_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_evento_repeticion`
    FOREIGN KEY (`repeticion_id`)
    REFERENCES `repeticion_evento` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_evento_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `repeticion_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repeticion_evento` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `dia` INT NULL,
                                                   `semana` INT NULL,
                                                   `mes` INT NULL,
                                                   `anio` INT NULL,
                                                   `dias` VARCHAR(45) NULL,
    `fecha_fin` DATETIME NULL,
    `indefinido` TINYINT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rutina` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(200) NULL,
    `hora_inicio` TIME NULL,
    `duracion_minutos` INT NULL,
    `dias_repeticion` SET('D', 'L', 'MA', 'MI', 'J', 'V', 'S') NULL,
    `activa` TINYINT NULL,
    `usuario_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_rutina_usuario_idx` (`usuario_id` ASC),
    CONSTRAINT `fk_rutina_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `paso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paso` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `orden` INT NULL,
                                      `nombre` VARCHAR(45) NULL,
    `descripcion` VARCHAR(200) NULL,
    `duracion_minutos` INT NULL,
    `rutina_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_paso_rutina_idx` (`rutina_id` ASC),
    CONSTRAINT `fk_paso_rutina`
    FOREIGN KEY (`rutina_id`)
    REFERENCES `rutina` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla `registro_rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `registro_rutina` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `rutina_id` INT NULL,
                                                 `fecha` DATETIME NULL,
                                                 `hora_inicio` TIME NULL,
                                                 `pasos_completados` INT NULL,
                                                 PRIMARY KEY (`id`),
    INDEX `fk_registro_rutina_idx` (`rutina_id` ASC),
    CONSTRAINT `fk_registro_rutina`
    FOREIGN KEY (`rutina_id`)
    REFERENCES `rutina` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- Additional tables follow similar patterns for normalization and Spanish standardization.