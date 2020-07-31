-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 30, 2020 at 04:32 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `users`
--

-- --------------------------------------------------------

--
-- Table structure for table `popup_actions`
--

CREATE TABLE `popup_actions` (
  `action_id` int(11) NOT NULL,
  `description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `popup_actions`
--

INSERT INTO `popup_actions` (`action_id`, `description`) VALUES
(0, 'publish post'),
(1, 'edit post'),
(2, 'more information');

-- --------------------------------------------------------

--
-- Table structure for table `users_table`
--

CREATE TABLE `users_table` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_table`
--

INSERT INTO `users_table` (`id`, `name`, `password`) VALUES
(20, 'nicoediaz', '$2y$10$AsQOMl4PCbMKLkMGD2KmuubIZVPSfxIqgwSxjFb.o40KdUxDeczuC'),
(24, 'angela', '$2y$10$v3NeQnygudbpEICJF/ARDOM3KGdzohlV22zmbZTH3xWnrrcvJKnJe');

-- --------------------------------------------------------

--
-- Table structure for table `user_activity`
--

CREATE TABLE `user_activity` (
  `activity_id` int(11) NOT NULL,
  `popup_action` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_lenght` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_activity`
--

INSERT INTO `user_activity` (`activity_id`, `popup_action`, `user_id`, `post_lenght`) VALUES
(2, 1, 20, 10),
(3, 0, 20, 0),
(4, 1, 20, 0),
(5, 1, 20, 8),
(6, 1, 20, 18),
(7, 1, 20, 4),
(8, 1, 20, 3),
(9, 1, 20, 12),
(10, 0, 20, 11),
(11, 0, 20, 29),
(12, 1, 20, 0),
(13, 1, 20, 0),
(14, 1, 20, 12),
(15, 1, 20, 12),
(16, 0, 20, 12),
(17, 0, 24, 18),
(18, 0, 20, 26),
(19, 1, 20, 26),
(20, 0, 20, 26),
(21, 1, 24, 6),
(22, 0, 24, 9),
(23, 1, 24, 9),
(24, 0, 24, 4),
(25, 0, 24, 4),
(26, 1, 24, 0),
(27, 1, 24, 0),
(28, 1, 24, 9),
(29, 1, 24, 0),
(30, 1, 24, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `popup_actions`
--
ALTER TABLE `popup_actions`
  ADD PRIMARY KEY (`action_id`);

--
-- Indexes for table `users_table`
--
ALTER TABLE `users_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_activity`
--
ALTER TABLE `user_activity`
  ADD PRIMARY KEY (`activity_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users_table`
--
ALTER TABLE `users_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `user_activity`
--
ALTER TABLE `user_activity`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
