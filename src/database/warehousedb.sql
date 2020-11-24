-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2020 at 04:25 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `uid`, `item_name`, `item_quantity`, `item_size`, `item_weight`, `item_price`, `is_deleted`) VALUES
(1, 2, 'sabun', 3, 100, 50, 25000, 0),
(2, 2, 'azareel', 3, 1500, 65000, 10, 0),
(4, 1, 'colek', 10, 50, 100, 9000, 0),
(5, 1, 'colek', 10, 50, 100, 9000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `req_id` int(6) NOT NULL,
  `dist_id` int(6) NOT NULL,
  `supp_id` int(6) NOT NULL,
  `item_quantity` int(5) NOT NULL,
  `is_accepted` int(1) NOT NULL DEFAULT 0,
  `req_type` int(1) NOT NULL,
  `item_take_id` int(6) DEFAULT NULL,
  `taken_id` int(6) DEFAULT NULL,
  `item_return_id` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`req_id`, `dist_id`, `supp_id`, `item_quantity`, `is_accepted`, `req_type`, `item_take_id`, `taken_id`, `item_return_id`) VALUES
(1, 3, 2, 3, 1, 0, 1, NULL, NULL),
(2, 3, 2, 2, 1, 1, NULL, 1, 2),
(4, 3, 2, 3, 0, 0, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `takenitem`
--

CREATE TABLE `takenitem` (
  `taken_id` int(6) NOT NULL,
  `uid` int(6) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `takenitem`
--

INSERT INTO `takenitem` (`taken_id`, `uid`, `date`) VALUES
(1, 3, '2020-11-09'),
(2, 3, '3920-09-01'),
(3, 3, '3920-09-01');

-- --------------------------------------------------------

--
-- Table structure for table `takenitemdetail`
--

CREATE TABLE `takenitemdetail` (
  `taken_id` int(6) NOT NULL,
  `item_id` int(6) NOT NULL,
  `item_quantity` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `takenitemdetail`
--

INSERT INTO `takenitemdetail` (`taken_id`, `item_id`, `item_quantity`) VALUES
(1, 2, 1),
(2, 1, 3),
(2, 2, 3),
(3, 1, 3),
(3, 2, 3);

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
  `membership_status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `username`, `password`, `email`, `address`, `user_type`, `membership_status`) VALUES
(1, 'julian', 'julian', 'julian@gmail.com', 'jalan julian', 0, 1),
(2, 'azareel', 'ojan', 'ojan@gmail.com', 'jalan ojan', 1, 0),
(3, 'lucky', 'lucky', 'lucky@gmail.com', 'jalan lucky', 2, 1),
(5, 'ozan', 'lol', 'lol@gmail.com', 'lol', 2, 0);

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
  ADD PRIMARY KEY (`req_id`) USING BTREE,
  ADD KEY `dist_id` (`dist_id`),
  ADD KEY `supp_id` (`supp_id`),
  ADD KEY `item_take_id` (`item_take_id`),
  ADD KEY `taken_id` (`taken_id`,`item_return_id`);

--
-- Indexes for table `takenitem`
--
ALTER TABLE `takenitem`
  ADD PRIMARY KEY (`taken_id`) USING BTREE,
  ADD KEY `uid` (`uid`) USING BTREE;

--
-- Indexes for table `takenitemdetail`
--
ALTER TABLE `takenitemdetail`
  ADD PRIMARY KEY (`taken_id`,`item_id`),
  ADD KEY `taken_id` (`taken_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `req_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `takenitem`
--
ALTER TABLE `takenitem`
  MODIFY `taken_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`item_take_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `request_ibfk_4` FOREIGN KEY (`taken_id`,`item_return_id`) REFERENCES `takenitemdetail` (`taken_id`, `item_id`);

--
-- Constraints for table `takenitem`
--
ALTER TABLE `takenitem`
  ADD CONSTRAINT `takenitem_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

--
-- Constraints for table `takenitemdetail`
--
ALTER TABLE `takenitemdetail`
  ADD CONSTRAINT `takenitemdetail_ibfk_1` FOREIGN KEY (`taken_id`) REFERENCES `takenitem` (`taken_id`),
  ADD CONSTRAINT `takenitemdetail_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
