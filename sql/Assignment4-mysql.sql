SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jburto2_201401_cpsc481` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `jburto2_201401_cpsc481` ;

-- -----------------------------------------------------
-- Table `jburto2_201401_cpsc481`.`Vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jburto2_201401_cpsc481`.`Vehicle` ;

CREATE TABLE IF NOT EXISTS `jburto2_201401_cpsc481`.`Vehicle` (
  `idVehicle` INT NOT NULL AUTO_INCREMENT,
  `VehicleDescription` VARCHAR(255) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`idVehicle`),
  UNIQUE INDEX `VehicleDescription_UNIQUE` (`VehicleDescription` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jburto2_201401_cpsc481`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jburto2_201401_cpsc481`.`Item` ;

CREATE TABLE IF NOT EXISTS `jburto2_201401_cpsc481`.`Item` (
  `idItem` INT NOT NULL AUTO_INCREMENT,
  `ItemDescription` VARCHAR(255) NOT NULL,
  `ItemMileageInterval` INT ZEROFILL NOT NULL,
  `ItemTimeInterval` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`idItem`),
  UNIQUE INDEX `ItemDescription_UNIQUE` (`ItemDescription` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jburto2_201401_cpsc481`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jburto2_201401_cpsc481`.`Location` ;

CREATE TABLE IF NOT EXISTS `jburto2_201401_cpsc481`.`Location` (
  `idLocation` INT NOT NULL AUTO_INCREMENT,
  `LocationDescription` VARCHAR(255) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`idLocation`),
  UNIQUE INDEX `LocationDescription_UNIQUE` (`LocationDescription` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jburto2_201401_cpsc481`.`Receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jburto2_201401_cpsc481`.`Receipt` ;

CREATE TABLE IF NOT EXISTS `jburto2_201401_cpsc481`.`Receipt` (
  `idReceipt` INT NOT NULL AUTO_INCREMENT,
  `ReceiptFile` VARCHAR(255) NOT NULL,
  `Location_idLocation` INT NOT NULL,
  `ReceiptDate` DATE NULL,
  `ReceiptAmount` INT NULL,
  `ReceiptNotes` VARCHAR(255) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`idReceipt`),
  UNIQUE INDEX `ReceiptFile_UNIQUE` (`ReceiptFile` ASC),
  INDEX `fk_Receipt_Location1_idx` (`Location_idLocation` ASC),
  INDEX `location_date` (`Location_idLocation` ASC, `ReceiptDate` ASC),
  CONSTRAINT `fk_Receipt_Location1`
    FOREIGN KEY (`Location_idLocation`)
    REFERENCES `jburto2_201401_cpsc481`.`Location` (`idLocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jburto2_201401_cpsc481`.`Work`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jburto2_201401_cpsc481`.`Work` ;

CREATE TABLE IF NOT EXISTS `jburto2_201401_cpsc481`.`Work` (
  `idWork` INT NOT NULL AUTO_INCREMENT,
  `Vehicle_idVehicle` INT NOT NULL,
  `Items_idItem` INT NOT NULL,
  `Receipt_idReceipt` INT NOT NULL,
  `WorkMileage` INT NOT NULL,
  `WorkNotes` VARCHAR(255) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL,
  INDEX `fk_Work_Vehicle1_idx` (`Vehicle_idVehicle` ASC),
  INDEX `fk_Work_Items1_idx` (`Items_idItem` ASC),
  INDEX `fk_Work_Receipt1_idx` (`Receipt_idReceipt` ASC),
  PRIMARY KEY (`idWork`),
  UNIQUE INDEX `Secondary` (`Vehicle_idVehicle` ASC, `Items_idItem` ASC, `Receipt_idReceipt` ASC),
  CONSTRAINT `fk_Work_Vehicle1`
    FOREIGN KEY (`Vehicle_idVehicle`)
    REFERENCES `jburto2_201401_cpsc481`.`Vehicle` (`idVehicle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Work_Items1`
    FOREIGN KEY (`Items_idItem`)
    REFERENCES `jburto2_201401_cpsc481`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Work_Receipt1`
    FOREIGN KEY (`Receipt_idReceipt`)
    REFERENCES `jburto2_201401_cpsc481`.`Receipt` (`idReceipt`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `jburto2_201401_cpsc481` ;

-- -----------------------------------------------------
-- Placeholder table for view `jburto2_201401_cpsc481`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jburto2_201401_cpsc481`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `jburto2_201401_cpsc481`.`view1`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `jburto2_201401_cpsc481`.`view1` ;
DROP TABLE IF EXISTS `jburto2_201401_cpsc481`.`view1`;
USE `jburto2_201401_cpsc481`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
