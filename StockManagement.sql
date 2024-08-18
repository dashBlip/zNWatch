-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 18, 2024 at 10:01 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `StockManagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `Delete_Details`
--

CREATE TABLE `Delete_Details` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `EditDetails`
--

CREATE TABLE `EditDetails` (
  `Username` varchar(20) NOT NULL,
  `Old_Pass` varchar(20) NOT NULL,
  `New_Pass` varchar(20) DEFAULT NULL,
  `Old_E-mail` varchar(20) NOT NULL,
  `New_E-mail` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `StockInfo`
--

CREATE TABLE `StockInfo` (
  `Username` varchar(20) NOT NULL,
  `StockName` varchar(20) NOT NULL,
  `StockPrice` double NOT NULL,
  `Quantity` int(11) NOT NULL,
  `PurchaseDate` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `UserInfo`
--

CREATE TABLE `UserInfo` (
  `FullName` varchar(20) NOT NULL,
  `DateOfBirth` varchar(12) NOT NULL,
  `PhoneNum` bigint(20) NOT NULL,
  `eMail` varchar(20) NOT NULL,
  `PanNum` varchar(20) NOT NULL,
  `Username` varchar(15) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `UserInfo`
--
DELIMITER $$
CREATE TRIGGER `Delete_Store` BEFORE DELETE ON `UserInfo` FOR EACH ROW BEGIN
	INSERT INTO Delete_Details values(OLD.Username,OLD.Password);
    END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Edit_Store` AFTER UPDATE ON `UserInfo` FOR EACH ROW BEGIN
	INSERT INTO EditDetails VALUES (Username,OLD.Password,NEW.Password,OLD.eMail,NEW.eMail);
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `EditDetails`
--
ALTER TABLE `EditDetails`
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `StockInfo`
--
ALTER TABLE `StockInfo`
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `UserInfo`
--
ALTER TABLE `UserInfo`
  ADD PRIMARY KEY (`Username`),
  ADD UNIQUE KEY `PanNum` (`PanNum`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `StockInfo`
--
ALTER TABLE `StockInfo`
  ADD CONSTRAINT `StockInfo_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `UserInfo` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
