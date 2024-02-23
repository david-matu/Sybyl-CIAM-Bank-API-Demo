-- -----------------------------------------------------
-- Schema sybyl_demo_bank_api_db
-- -----------------------------------------------------
-- A demo schema to represent a Core Banking API that will provide basic functionality such as storing customer info, account info,recording transactions and payments, perform account top-ups (credits) and withdrawals.For Sybyl, by David (https://david-matu.github.io)

-- -----------------------------------------------------
-- Schema sybyl_demo_bank_api_db
--
-- A demo schema to represent a Core Banking API that will provide basic functionality such as storing customer info, account info,recording transactions and payments, perform account top-ups (credits) and withdrawals.For Sybyl, by David (https://david-matu.github.io)
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sybyl_demo_bank_api_db`;
USE `sybyl_demo_bank_api_db` ;

-- -----------------------------------------------------
-- Table `CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CUSTOMER` (
  `CUSTOMER_ID` INT NOT NULL,
  `FIRST_NAME` VARCHAR(45) NOT NULL,
  `LAST_NAME` VARCHAR(45) NOT NULL,
  `PHONE` VARCHAR(15) NOT NULL,
  `EMAIL` VARCHAR(45) NULL,
  `SECRET` VARCHAR(45) NULL COMMENT 'This can be password in case no authorization server is being used, or a PIN to enable customer withdraw at ATMs or counters',
  `REG_DATE` DATETIME NULL,
  PRIMARY KEY (`CUSTOMER_ID`));


-- -----------------------------------------------------
-- Table `ACCOUNT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACCOUNT` (
  `ACCOUNT_ID` INT NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` INT NOT NULL,
  `ACCOUNT_TYPE` VARCHAR(10) NOT NULL COMMENT 'CURRENT, SAVINGS, COMMERCIAL',
  `AVAILABLE_BALANCE` DOUBLE NULL,
  `CURRENT_BALANCE` DOUBLE NULL,
  `DATE_CREATED` DATETIME NULL,
  `STATUS` VARCHAR(10) NULL COMMENT 'ACTIVE, SUSPENDED',
  `ACCOUNT_LOG` TEXT NULL,
  PRIMARY KEY (`ACCOUNT_ID`));


-- -----------------------------------------------------
-- Table `TRANSACTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRANSACTION` (
  `TRANX_ID` VARCHAR(255) NOT NULL,
  `ACCOUNT_ID` INT NOT NULL,
  `TRANX_TYPE` VARCHAR(5) NULL COMMENT 'C(Credit), D(Debit)',
  `AMOUNT` DOUBLE NULL,
  `COMMENT` TEXT NULL,
  `TRANX_DATE` VARCHAR(45) NULL,
  PRIMARY KEY (`TRANX_ID`));


-- -----------------------------------------------------
-- Table `PAYMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PAYMENT` (
  `PAYMENT_ID` INT NOT NULL AUTO_INCREMENT,
  `SENDER_ACCOUNT_ID` INT NOT NULL,
  `RECIPIENT_ACCOUNT_ID` INT NOT NULL,
  `AMOUNT` DOUBLE NOT NULL,
  `PAYMENT_DATE` DATETIME NULL,
  `STATUS` VARCHAR(10) NULL COMMENT 'PENDING, COMPLETED',
  PRIMARY KEY (`PAYMENT_ID`));
  
INSERT INTO CUSTOMER (`CUSTOMER_ID`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `EMAIL`, `SECRET`, `REG_DATE`) VALUES (10012, 'Sybyl', 'Developer', '0780459455', 'david.ndirangu@sybyl.com', '1234', now());
COMMIT;

