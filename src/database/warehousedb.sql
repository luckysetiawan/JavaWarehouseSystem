-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2020 at 02:17 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehousedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` int(6) NOT NULL,
  `uid` int(6) NOT NULL,
  `item_name` varchar(15) NOT NULL,
  `item_quantity` int(5) NOT NULL,
  `item_size` int(5) NOT NULL,
  `item_weight` int(4) NOT NULL,
  `item_price` int(10) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `req_id` int(6) NOT NULL,
  `dist_id` int(6) NOT NULL,
  `supp_id` int(6) NOT NULL,
  `item_id` int(6) NOT NULL,
  `item_quantity` int(5) NOT NULL,
  `is_accepted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `takenitem`
--

CREATE TABLE `takenitem` (
  `taken_id` int(6) NOT NULL,
  `item_id` int(6) NOT NULL,
  `uid` int(6) NOT NULL,
  `item_quantity` int(5) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uid` int(6) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(25) NOT NULL,
  `email` varchar(20) NOT NULL,
  `address` varchar(30) NOT NULL,
  `user_type` int(1) NOT NULL,
  `membership_status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `uid` (`uid`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`req_id`),
  ADD KEY `dist_id` (`dist_id`),
  ADD KEY `supp_id` (`supp_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `takenitem`
--
ALTER TABLE `takenitem`
  ADD PRIMARY KEY (`taken_id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `uid` (`uid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `req_id` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `takenitem`
--
ALTER TABLE `takenitem`
  MODIFY `taken_id` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(6) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`dist_id`) REFERENCES `user` (`uid`),
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`supp_id`) REFERENCES `user` (`uid`),
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `takenitem`
--
ALTER TABLE `takenitem`
  ADD CONSTRAINT `takenitem_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `takenitem_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
