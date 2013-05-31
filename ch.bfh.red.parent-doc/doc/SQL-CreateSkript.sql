SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Person` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `Firstname` VARCHAR(45) NOT NULL ,
  `Country` VARCHAR(45) NOT NULL ,
  `Zip` VARCHAR(45) NOT NULL ,
  `City` VARCHAR(45) NOT NULL ,
  `Street` VARCHAR(45) NOT NULL ,
  `Streetnbr` VARCHAR(45) NULL ,
  PRIMARY KEY (`idPerson`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Patient` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Patient` (
  `idPatient` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `Firstname` VARCHAR(45) NOT NULL ,
  `Country` VARCHAR(45) NOT NULL ,
  `Zip` VARCHAR(45) NOT NULL ,
  `City` VARCHAR(45) NOT NULL ,
  `Street` VARCHAR(45) NOT NULL ,
  `Streetnbr` VARCHAR(45) NULL ,
  `Person_idPerson` INT NOT NULL ,
  PRIMARY KEY (`idPatient`) ,
  INDEX `fk_Patient_Person1_idx` (`Person_idPerson` ASC) ,
  CONSTRAINT `fk_Patient_Person1`
    FOREIGN KEY (`Person_idPerson` )
    REFERENCES `mydb`.`Person` (`idPerson` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Profile` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Profile` (
  `idProfile` INT NOT NULL AUTO_INCREMENT ,
  `Profilename` VARCHAR(45) NOT NULL ,
  `CreationDate` TIMESTAMP NOT NULL ,
  `Patient_idPatient` INT NOT NULL ,
  PRIMARY KEY (`idProfile`) ,
  INDEX `fk_Profile_Patient1_idx` (`Patient_idPatient` ASC) ,
  CONSTRAINT `fk_Profile_Patient1`
    FOREIGN KEY (`Patient_idPatient` )
    REFERENCES `mydb`.`Patient` (`idPatient` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Therapist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Therapist` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Therapist` (
  `idTherapist` INT NOT NULL AUTO_INCREMENT ,
    `idPerson` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `Firstname` VARCHAR(45) NOT NULL ,
  `Country` VARCHAR(45) NOT NULL ,
  `Zip` VARCHAR(45) NOT NULL ,
  `City` VARCHAR(45) NOT NULL ,
  `Street` VARCHAR(45) NOT NULL ,
  `Streetnbr` VARCHAR(45) NULL ,
  `competenceLevel` INT NULL ,
  `Person_idPerson` INT NOT NULL ,
  PRIMARY KEY (`idTherapist`) ,
  INDEX `fk_Therapist_Person1_idx` (`Person_idPerson` ASC) ,
  CONSTRAINT `fk_Therapist_Person1`
    FOREIGN KEY (`Person_idPerson` )
    REFERENCES `mydb`.`Person` (`idPerson` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Diagnosis`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Diagnosis` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Diagnosis` (
  `idDiagnosis` INT NOT NULL AUTO_INCREMENT ,
  `Title` VARCHAR(45) NOT NULL ,
  `Date` TIMESTAMP NOT NULL ,
  `Diagnosis` VARCHAR(500) NOT NULL ,
  `Profile_idProfile` INT NOT NULL ,
  `Therapist_idTherapist` INT NOT NULL ,
  `Therapist_Person_idPerson` INT NOT NULL ,
  PRIMARY KEY (`idDiagnosis`) ,
  INDEX `fk_Diagnosis_Profile_idx` (`Profile_idProfile` ASC) ,
  INDEX `fk_Diagnosis_Therapist1_idx` (`Therapist_idTherapist` ASC, `Therapist_Person_idPerson` ASC) ,
  CONSTRAINT `fk_Diagnosis_Profile`
    FOREIGN KEY (`Profile_idProfile` )
    REFERENCES `mydb`.`Profile` (`idProfile` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Diagnosis_Therapist1`
    FOREIGN KEY (`Therapist_idTherapist` , `Therapist_Person_idPerson` )
    REFERENCES `mydb`.`Therapist` (`idTherapist` , `Person_idPerson` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Diary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Diary` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Diary` (
  `idDiary` INT NOT NULL ,
  `CreationDate` TIMESTAMP NOT NULL ,
  `ModificationDate` TIMESTAMP NULL ,
  PRIMARY KEY (`idDiary`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DiaryEntry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DiaryEntry` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`DiaryEntry` (
  `idDiaryEntry` INT NOT NULL AUTO_INCREMENT ,
  `Text` VARCHAR(500) NOT NULL ,
  `Feeling` INT NOT NULL ,
  `CreationDate` TIMESTAMP NOT NULL ,
  `Diary_idDiary` INT NOT NULL ,
  PRIMARY KEY (`idDiaryEntry`, `Diary_idDiary`) ,
  INDEX `fk_DiaryEntry_Diary1_idx` (`Diary_idDiary` ASC) ,
  CONSTRAINT `fk_DiaryEntry_Diary1`
    FOREIGN KEY (`Diary_idDiary` )
    REFERENCES `mydb`.`Diary` (`idDiary` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
