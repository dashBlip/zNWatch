-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 27, 2024 at 05:52 PM
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
-- Stand-in structure for view `AllDataView`
-- (See below for the actual view)
--
CREATE TABLE `AllDataView` (
`User_Username` varchar(15)
,`User_FullName` varchar(20)
,`User_DateOfBirth` varchar(12)
,`User_PhoneNum` bigint(20)
,`User_Email` varchar(20)
,`User_PanNum` varchar(20)
,`User_Balance` double
,`Stock_StockName` varchar(20)
,`Stock_StockPrice` double
,`Stock_Quantity` int(11)
,`Stock_PurchaseDate` varchar(20)
,`Edit_OldPassword` varchar(20)
,`Edit_NewPassword` varchar(20)
,`Edit_OldEmail` varchar(20)
,`Edit_NewEmail` varchar(20)
,`Delete_Password` varchar(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `Delete_Details`
--

CREATE TABLE `Delete_Details` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL DEFAULT '-'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `EditDetails`
--

CREATE TABLE `EditDetails` (
  `Username` varchar(20) NOT NULL,
  `Old_Pass` varchar(20) NOT NULL DEFAULT '-',
  `New_Pass` varchar(20) DEFAULT '-',
  `Old_E-mail` varchar(20) NOT NULL DEFAULT '-',
  `New_E-mail` varchar(20) DEFAULT '-'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `EditDetails`
--

INSERT INTO `EditDetails` (`Username`, `Old_Pass`, `New_Pass`, `Old_E-mail`, `New_E-mail`) VALUES
('', '0', '0', 'jj@gmail.com', 'jj@gmail.com'),
('', '0', '0', 'jj@gmail.com', 'jj@gmail.com'),
('', '0', '0', 'jj@gmail.com', 'jj@gmail.com'),
('', '0', '0', 'jj@gmail.com', 'jj@gmail.com'),
('', '0', '0', 'jj@gmail.com', 'jj@gmail.com'),
('', '0', '0', 'jj@gmail.com', 'jj@gmail.com');

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

--
-- Dumping data for table `StockInfo`
--

INSERT INTO `StockInfo` (`Username`, `StockName`, `StockPrice`, `Quantity`, `PurchaseDate`) VALUES
('Kapil17', 'JioFinance', 716.7899412341144, 2, '2024-08-24'),
('Kapil17', 'Suzlon', 273.42199425885974, 3, '2024-08-24'),
('Kapil17', 'Wipro', 246.5837519791662, 1, '2024-08-24'),
('kapil17', 'JioFinance', 357.51322013495775, 1, '2024-08-24'),
('kapil17', 'MTNL', 60.59745185011691, 1, '2024-08-24');

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
-- Dumping data for table `UserInfo`
--

INSERT INTO `UserInfo` (`FullName`, `DateOfBirth`, `PhoneNum`, `eMail`, `PanNum`, `Username`, `Password`, `Balance`) VALUES
('Kapil Ramesh Surti', '17/04/2006', 9825771916, 'jj@gmail.com', 'PAN-kapil', 'Kapil17', '0', 18345.093640542782);

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

-- --------------------------------------------------------

--
-- Structure for view `AllDataView`
--
DROP TABLE IF EXISTS `AllDataView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `AllDataView`  AS SELECT `u`.`Username` AS `User_Username`, `u`.`FullName` AS `User_FullName`, `u`.`DateOfBirth` AS `User_DateOfBirth`, `u`.`PhoneNum` AS `User_PhoneNum`, `u`.`eMail` AS `User_Email`, `u`.`PanNum` AS `User_PanNum`, `u`.`Balance` AS `User_Balance`, `s`.`StockName` AS `Stock_StockName`, `s`.`StockPrice` AS `Stock_StockPrice`, `s`.`Quantity` AS `Stock_Quantity`, `s`.`PurchaseDate` AS `Stock_PurchaseDate`, `e`.`Old_Pass` AS `Edit_OldPassword`, `e`.`New_Pass` AS `Edit_NewPassword`, `e`.`Old_E-mail` AS `Edit_OldEmail`, `e`.`New_E-mail` AS `Edit_NewEmail`, `d`.`Password` AS `Delete_Password` FROM (((`UserInfo` `u` left join `StockInfo` `s` on(`u`.`Username` = `s`.`Username`)) left join `EditDetails` `e` on(`u`.`Username` = `e`.`Username`)) left join `Delete_Details` `d` on(`u`.`Username` = `d`.`Username`)) ;

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
