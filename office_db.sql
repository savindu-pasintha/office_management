-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2022 at 09:06 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `office_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `contract_table`
--

CREATE TABLE `contract_table` (
  `cont_id` int(11) NOT NULL,
  `cont_name` varchar(100) NOT NULL,
  `cont_date_handeover` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contract_table`
--

INSERT INTO `contract_table` (`cont_id`, `cont_name`, `cont_date_handeover`) VALUES
(2, 'test contract2', 2343242),
(3, 'test contract2', 2343242),
(4, 'test contract2', 2343242),
(5, 'test contract2', 2343242),
(6, 'test contract2', 2343242),
(7, 'test contract2', 2343242),
(8, 'test contract2', 2343242),
(9, 'test contract2', 2343242),
(10, 'test contract2', 2343242);

-- --------------------------------------------------------

--
-- Table structure for table `department_table`
--

CREATE TABLE `department_table` (
  `dept_id` int(11) NOT NULL,
  `dept_name` varchar(45) NOT NULL,
  `dept_address` varchar(45) DEFAULT NULL,
  `dept_sector_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `employee_table`
--

CREATE TABLE `employee_table` (
  `emp_id` int(11) NOT NULL,
  `emp_first_name` varchar(45) NOT NULL,
  `emp_last_name` varchar(45) NOT NULL,
  `emp_email` varchar(100) NOT NULL,
  `emp_password` varchar(45) NOT NULL,
  `emp_address` varchar(100) DEFAULT NULL,
  `emp_birthday` int(11) DEFAULT NULL,
  `emp_designation` varchar(45) NOT NULL,
  `emp_dep_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `file_table`
--

CREATE TABLE `file_table` (
  `file_id` int(11) NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `file_title` varchar(100) DEFAULT NULL,
  `file_sender` varchar(100) DEFAULT NULL,
  `file_created_date` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `project_table`
--

CREATE TABLE `project_table` (
  `pro_id` int(11) NOT NULL,
  `pro_name` varchar(100) NOT NULL,
  `pro_supervisor_name` varchar(45) DEFAULT NULL,
  `pro_date_added` int(11) DEFAULT NULL,
  `pro_emp_id` int(11) DEFAULT NULL,
  `pro_cont_id` int(11) NOT NULL,
  `pro_suggestor_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project_table`
--

INSERT INTO `project_table` (`pro_id`, `pro_name`, `pro_supervisor_name`, `pro_date_added`, `pro_emp_id`, `pro_cont_id`, `pro_suggestor_name`) VALUES
(2, 'Test Project', 'Test Supervisor', 6454132, 1, 2, 'Suggestor NAme'),
(3, 'Test Project Updated', 'Test Supervisor', 6454132, 1, 2, 'Suggestor NAme'),
(4, 'Test Project Updated', 'Test Supervisor', 6454132, 1, 2, 'Suggestor NAme');

-- --------------------------------------------------------

--
-- Table structure for table `sector_table`
--

CREATE TABLE `sector_table` (
  `sector_id` int(11) NOT NULL,
  `sector_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sector_table`
--

INSERT INTO `sector_table` (`sector_id`, `sector_name`) VALUES
(2, 'sector two updated'),
(3, 'sector three');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contract_table`
--
ALTER TABLE `contract_table`
  ADD PRIMARY KEY (`cont_id`);

--
-- Indexes for table `department_table`
--
ALTER TABLE `department_table`
  ADD PRIMARY KEY (`dept_id`),
  ADD KEY `dept_sector_fk` (`dept_sector_id`);

--
-- Indexes for table `employee_table`
--
ALTER TABLE `employee_table`
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `emp_dept_fk` (`emp_dep_id`);

--
-- Indexes for table `file_table`
--
ALTER TABLE `file_table`
  ADD PRIMARY KEY (`file_id`);

--
-- Indexes for table `project_table`
--
ALTER TABLE `project_table`
  ADD PRIMARY KEY (`pro_id`),
  ADD KEY `pro_cont_fk` (`pro_cont_id`);

--
-- Indexes for table `sector_table`
--
ALTER TABLE `sector_table`
  ADD PRIMARY KEY (`sector_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `department_table`
--
ALTER TABLE `department_table`
  ADD CONSTRAINT `dept_sector_fk` FOREIGN KEY (`dept_sector_id`) REFERENCES `sector_table` (`sector_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee_table`
--
ALTER TABLE `employee_table`
  ADD CONSTRAINT `emp_dept_fk` FOREIGN KEY (`emp_dep_id`) REFERENCES `department_table` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `project_table`
--
ALTER TABLE `project_table`
  ADD CONSTRAINT `pro_cont_fk` FOREIGN KEY (`pro_cont_id`) REFERENCES `contract_table` (`cont_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
