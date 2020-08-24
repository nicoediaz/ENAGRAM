-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 24, 2020 at 04:10 PM
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
-- Database: `ude_server`
--

-- --------------------------------------------------------

--
-- Table structure for table `interventions`
--

CREATE TABLE `interventions` (
  `id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `message` varchar(250) NOT NULL,
  `risk` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interventions`
--

INSERT INTO `interventions` (`id`, `category`, `message`, `risk`) VALUES
(1, '1', 'You share a post describing your experience with drugs. You get a wake-up call from your superior after a colleague forwards this post to him.', 0.688),
(2, '1', 'You post a picture in which you are drunk at a party. You feel embarrassed after you realize this picture was seen by all your contacts including close friends, family and acquaintances.', 0.435),
(3, '1', 'You share a post describing your experience with drugs. You lose your job after your work colleagues forward this post to your boss.', 0.825),
(4, '1', 'You post a picture in which you are drunk at a party. You lose your job after your work colleagues forward this picture to your boss.', 0.835),
(5, '1', 'You share a post describing your experience with drugs. You feel embarrassed after you realize this post was seen by all your contacts including close friends, family and acquaintances.', 0.5),
(6, '1', 'You post a picture in which you are drunk at a party. You get a wake-up call from your superior after a colleague forwards this picture to him.', 0.535),
(7, '2', 'You post a naked or semi-naked picture of you. You lose your job after your work colleagues forward this picture to your boss.', 0.875),
(8, '2', 'You post a naked or semi-naked picture of you. You get a wake-up call from your superior after a colleague forwards this picture to him.', 0.758),
(9, '2', 'You share a post describing a personal sexual encounter or experience. You feel embarrassed after you realize this post was seen by all your contacts including close friends, family and acquaintances.', 0.58),
(10, '2', 'You share a post describing a personal sexual encounter or experience. You get a wake-up call from your superior after a colleague forwards this post to him.', 0.609),
(11, '2', 'You post a naked or semi-naked picture of you. You feel embarrassed after you realize this picture was seen by all your contacts including close friends, family and acquaintances.', 0.756),
(12, '2', 'You share a post describing a personal sexual encounter or experience. You feel embarrassed after you realize this post was seen by all your contacts including close friends, family and acquaintances.', 0.579),
(13, '3', 'You share a post giving your opinion about a religious issue or statement. You lose your job after your work colleagues forward this post to your boss.', 0.775),
(14, '3', 'You share a post giving your opinion about a political issue or statement. You get a wake-up call from your superior after a colleague forwards this post to him.', 0.448),
(15, '3', 'You share a post giving your opinion about a religious issue or statement. Some of your friends decide to end up their relationship with you because they found your post offensive.', 0.478),
(16, '3', 'You share a post giving your opinion about a political issue or statement. Some of your friends decide to end up their relationship with you because they disagree with what you wrote.', 0.445),
(17, '3', 'You share a post giving your opinion about a religious issue or statement. You get a wake-up call\r\nfrom your superior after a colleague forwards this post to him.', 0.434),
(18, '3', 'You share a post giving your opinion about a political issue or statement. You lose your job after\r\nyour work colleagues forward this post to your boss.', 0.822),
(19, '4', 'You share a post with a negative comment about someone else. Friends in common decide to end up their relationship with you after seeing what you wrote.', 0.553),
(20, '4', 'You share a post with a negative comment about your employer. You get a wake-up call from your superior after a colleague forwards this post to him.', 0.657),
(21, '4', 'You share a post with a negative comment about your employer. You lose your job after your work colleagues forward this post to your boss.', 0.806),
(22, '5', 'You share a post and include the location where you are at the moment. You get stalked by a person who saw your post and is at the same place as you are.', 0.725),
(23, '5', 'You share a post including your new home address. Someone who saw your post breaks into your house to rob your belongings.', 0.857),
(24, '6', 'You share a post including your new phone number. You get messages and calls from a person who was not supposed to see your post.', 0.508),
(25, '6', 'You share a picture of your brand-new credit card. Some days later you realize that someone has been buying stuff on your behalf.', 0.818),
(26, '6', 'You share a post including your new email address. Thereafter, you start getting spam messages from someone you don’t know.', 0.403);

-- --------------------------------------------------------

--
-- Table structure for table `intervention_categories`
--

CREATE TABLE `intervention_categories` (
  `id` int(11) NOT NULL,
  `category` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `intervention_categories`
--

INSERT INTO `intervention_categories` (`id`, `category`) VALUES
(1, 'drugs and alcohol use'),
(2, 'sex'),
(3, 'religion and politics'),
(4, 'strong sentiment'),
(5, 'location'),
(6, 'personal identifiers');

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
(0, 'edit post'),
(1, 'publish post'),
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
(25, 'nicoediaz', '$2y$10$TxNR3iyxCksgXzzSBeDAee3decl8pJhMcO41v1rpL8NiEvvjOh5pm');

-- --------------------------------------------------------

--
-- Table structure for table `user_activity`
--

CREATE TABLE `user_activity` (
  `activity_id` int(11) NOT NULL,
  `popup_action` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_lenght` int(11) NOT NULL,
  `post_hash` varchar(40) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_activity`
--

INSERT INTO `user_activity` (`activity_id`, `popup_action`, `user_id`, `post_lenght`, `post_hash`, `timestamp`) VALUES
(161, 0, 20, 3, 'asdas34234234dsdfsdfsd', '2020-08-19 10:57:45'),
(162, 1, 25, 6, '9aa6e5f2256c17d2d430b100032b997c', '2020-08-19 11:00:07'),
(163, 1, 25, 1, '2db95e8e1a9267b7a1188556b2013b33', '2020-08-19 11:18:41'),
(164, 1, 25, 3, 'bf083d4ab960620b645557217dd59a49', '2020-08-19 11:20:30'),
(165, 1, 25, 4, '562b530cff1f5bca3b1a4c1ad4ad9962', '2020-08-19 11:21:11'),
(166, 1, 25, 4, '590f53e8699817c6fa498cc11a4cbe63', '2020-08-21 14:42:36'),
(167, 0, 25, 8, '7d60b15bc13904c2220db021100d56bc', '2020-08-21 14:56:05'),
(168, 0, 25, 1, '8fa14cdd754f91cc6554c9e71929cce7', '2020-08-24 13:03:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `interventions`
--
ALTER TABLE `interventions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `intervention_categories`
--
ALTER TABLE `intervention_categories`
  ADD PRIMARY KEY (`id`);

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
-- AUTO_INCREMENT for table `interventions`
--
ALTER TABLE `interventions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `intervention_categories`
--
ALTER TABLE `intervention_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `popup_actions`
--
ALTER TABLE `popup_actions`
  MODIFY `action_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users_table`
--
ALTER TABLE `users_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `user_activity`
--
ALTER TABLE `user_activity`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=169;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
