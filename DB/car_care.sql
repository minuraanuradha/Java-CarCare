-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3377
-- Generation Time: Oct 06, 2024 at 08:22 AM
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
-- Database: `car_care`
--

-- --------------------------------------------------------

--
-- Table structure for table `empandord`
--

CREATE TABLE `empandord` (
  `Id` int(20) NOT NULL,
  `E_code` varchar(50) NOT NULL,
  `E_name` varchar(225) NOT NULL,
  `O_code` varchar(50) NOT NULL,
  `O_name` varchar(225) NOT NULL,
  `E_department` varchar(40) NOT NULL,
  `O_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `empandord`
--

INSERT INTO `empandord` (`Id`, `E_code`, `E_name`, `O_code`, `O_name`, `E_department`, `O_status`) VALUES
(8, 'E_01', 'naveen', 'R_01', 'Supun', 'Body', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `Id` int(11) NOT NULL,
  `E_code` varchar(50) NOT NULL,
  `E_name` varchar(225) NOT NULL,
  `E_num` int(15) NOT NULL,
  `E_email` varchar(100) NOT NULL,
  `E_department` varchar(40) NOT NULL,
  `E_address` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`Id`, `E_code`, `E_name`, `E_num`, `E_email`, `E_department`, `E_address`) VALUES
(4, 'E_01', 'Naveen', 1264579784, 'naveen@gmail.com', 'Car Body', 'colombo'),
(5, 'E_02', 'Minura', 775978654, 'Minura@gmail.com', 'body parts', 'Homagama');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `Id` int(11) NOT NULL,
  `O_code` varchar(8) NOT NULL,
  `O_name` varchar(225) NOT NULL,
  `O_num` int(11) NOT NULL,
  `O_email` varchar(225) NOT NULL,
  `O_cost` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`Id`, `O_code`, `O_name`, `O_num`, `O_email`, `O_cost`) VALUES
(1, 'R_01', 'Supun', 789568741, 'Supun@gmail.com', 5080),
(2, 'R_02', 'Jagath', 745698742, 'Jagath@gmail.com', 5000),
(3, 'V_01', 'Cris', 956793241, 'Cris@gmail.com', 7855500),
(4, 'V_02', 'Andro', 714569321, 'Andro@gmail.com', 48460),
(15, 'S_01', 'Zee', 945769843, 'Zee@gmail.com', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `Id` int(11) NOT NULL,
  `S_code` varchar(50) NOT NULL,
  `S_name` varchar(225) NOT NULL,
  `S_email` varchar(225) NOT NULL,
  `S_product` varchar(100) NOT NULL,
  `S_status` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`Id`, `S_code`, `S_name`, `S_email`, `S_product`, `S_status`) VALUES
(10, 'S_01', 'Dilshan', 'dilshan@gmail.com', 'Weel', 'Aprroved');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `empandord`
--
ALTER TABLE `empandord`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `empandord`
--
ALTER TABLE `empandord`
  MODIFY `Id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
