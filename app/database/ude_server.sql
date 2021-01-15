-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 15-01-2021 a las 18:06:19
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ude_server`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `interventions`
--

CREATE TABLE `interventions` (
  `id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `message` varchar(250) NOT NULL,
  `risk` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `interventions`
--

INSERT INTO `interventions` (`id`, `category`, `message`, `risk`) VALUES
(1, '1', 'Other users had problems at work after posting about drug consumption', 0.688),
(2, '1', 'Other users have felt embarrassed after posting about alcohol consumption', 0.435),
(3, '1', 'Other users have lost their job after posting about drug consumption', 0.825),
(4, '1', 'Other users have last their job after posting about alcohol consumption', 0.835),
(5, '1', 'Other users have felt embarrassed after posting about drug consumption', 0.5),
(6, '1', 'Other users had problems at work after posting about alcohol consumption', 0.535),
(7, '2', 'Other users have lost their jobs after posting naked or semi-naked pictures of themselves', 0.875),
(8, '2', 'Other users had problems at work after posting naked or semi-naked pictures of themselves', 0.758),
(9, '2', 'Other users have lost their jobs after posting about a personal sexual encounter or experience', 0.58),
(10, '2', 'Other users had problems at work after posting about a personal sexual encounter or experience', 0.609),
(11, '2', 'Other users have felt embarrassed after posting naked or semi-naked pictures of themselves', 0.756),
(12, '2', 'Other users have felt embarrassed after posting about a personal sexual encounter or experience', 0.579),
(13, '3', 'Other users have lost their jobs after posting about their religious beliefs', 0.775),
(14, '3', 'Other users had problems at work after posting about their political views', 0.448),
(15, '3', 'Other users have lost friendships after posting about their religious beliefs', 0.478),
(16, '3', 'Other users have lost friendships after posting about their political views', 0.445),
(17, '3', 'Other users had problems at work after posting about their religious beliefs', 0.434),
(18, '3', 'Other users have lost their jobs after posting about their political views', 0.822),
(19, '4', 'Other users have lost friendships after posting a negative comment about someone else', 0.553),
(20, '4', 'Other users had problems at work after posting a negative comment about their employer', 0.657),
(21, '4', 'Other users have lost their jobs after posting a negative comment about their employer', 0.806),
(22, '5', 'Other users have being stalked after posting their current location', 0.725),
(23, '5', 'Other users have become victims of burglary after sharing their home address', 0.857),
(24, '6', 'Other users have received calls and messages from strangers after posting their phone number', 0.508),
(25, '6', 'Other users have experienced financial fraud after posting their credit card number', 0.818),
(26, '6', 'Other users have received spam messages after posting their e-mail address', 0.403);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `interventions_de`
--

CREATE TABLE `interventions_de` (
  `id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `message` varchar(250) NOT NULL,
  `risk` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intervention_categories`
--

CREATE TABLE `intervention_categories` (
  `id` int(11) NOT NULL,
  `category` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `intervention_categories`
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
-- Estructura de tabla para la tabla `popup_actions`
--

CREATE TABLE `popup_actions` (
  `action_id` int(11) NOT NULL,
  `description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `popup_actions`
--

INSERT INTO `popup_actions` (`action_id`, `description`) VALUES
(0, 'edit post'),
(1, 'publish post'),
(2, 'more information');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users_table`
--

CREATE TABLE `users_table` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` text NOT NULL,
  `app_language` varchar(10) NOT NULL,
  `app_version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users_table`
--

INSERT INTO `users_table` (`id`, `name`, `password`, `app_language`, `app_version`) VALUES
(25, 'nicoediaz', '$2y$10$TxNR3iyxCksgXzzSBeDAee3decl8pJhMcO41v1rpL8NiEvvjOh5pm', '', 0),
(26, 'nico', '$2y$10$J/ZSR7CUoYaWfCa0oShHrOUd2P182PxuOe715iUG4oqhYKruudjfS', 'EN', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_activity`
--

CREATE TABLE `user_activity` (
  `activity_id` int(11) NOT NULL,
  `popup_action` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_lenght` int(11) NOT NULL,
  `post_hash` varchar(40) NOT NULL,
  `image_hash` varchar(40) NOT NULL,
  `msg_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_activity`
--

INSERT INTO `user_activity` (`activity_id`, `popup_action`, `user_id`, `post_lenght`, `post_hash`, `image_hash`, `msg_id`, `timestamp`) VALUES
(161, 0, 20, 3, 'asdas34234234dsdfsdfsd', '', 0, '2020-08-19 10:57:45'),
(162, 1, 25, 6, '9aa6e5f2256c17d2d430b100032b997c', '', 0, '2020-08-19 11:00:07'),
(163, 1, 25, 1, '2db95e8e1a9267b7a1188556b2013b33', '', 0, '2020-08-19 11:18:41'),
(164, 1, 25, 3, 'bf083d4ab960620b645557217dd59a49', '', 0, '2020-08-19 11:20:30'),
(165, 1, 25, 4, '562b530cff1f5bca3b1a4c1ad4ad9962', '', 0, '2020-08-19 11:21:11'),
(166, 1, 25, 4, '590f53e8699817c6fa498cc11a4cbe63', '', 0, '2020-08-21 14:42:36'),
(167, 0, 25, 8, '7d60b15bc13904c2220db021100d56bc', '', 0, '2020-08-21 14:56:05'),
(168, 0, 25, 1, '8fa14cdd754f91cc6554c9e71929cce7', '', 0, '2020-08-24 13:03:33'),
(169, 1, 25, 8, '61aab332b71c50264c34858d32bc58d0', '', 0, '2020-11-18 12:19:19'),
(170, 1, 25, 6, '9aa6e5f2256c17d2d430b100032b997c', '', 0, '2020-11-18 12:25:01'),
(171, 1, 25, 8, '274883dcadb83028c76c3ccfadc7d9f4', '', 0, '2020-11-18 12:26:00'),
(172, 1, 25, 15, '398be4852472fde06de48f17ca409838', '', 0, '2020-11-18 16:17:38'),
(173, 1, 25, 6, '9aa6e5f2256c17d2d430b100032b997c', '', 0, '2021-01-14 12:44:48'),
(174, 1, 25, 6, '9aa6e5f2256c17d2d430b100032b997c', '', 0, '2021-01-14 12:47:05'),
(175, 0, 25, 10, '4b95df3e6460351e90cf3a725e5621be', '', 0, '2021-01-14 15:16:04'),
(176, 0, 25, 3, '202cb962ac59075b964b07152d234b70', '', 0, '2021-01-14 15:22:11'),
(177, 0, 25, 1, '0cc175b9c0f1b6a831c399e269772661', '', 0, '2021-01-14 15:30:29'),
(178, 0, 25, 3, '698d51a19d8a121ce581499d7b701668', '', 0, '2021-01-15 08:33:37'),
(179, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 09:36:09'),
(180, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 09:36:30'),
(181, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 10:19:18'),
(182, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 10:21:48'),
(183, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 10:25:46'),
(184, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 10:52:58'),
(185, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 10:54:33'),
(186, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 11:00:34'),
(187, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 11:59:39'),
(188, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 12:01:14'),
(189, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 0, '2021-01-15 12:25:00'),
(190, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 20, '2021-01-15 13:20:09'),
(191, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 2, '2021-01-15 13:24:27'),
(192, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 3, '2021-01-15 13:34:29'),
(193, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 23, '2021-01-15 13:37:36'),
(194, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 21, '2021-01-15 13:38:27'),
(195, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 13, '2021-01-15 13:39:24'),
(196, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 18, '2021-01-15 13:40:49'),
(197, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 1, '2021-01-15 13:42:59'),
(198, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 12, '2021-01-15 13:43:48'),
(199, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '', 18, '2021-01-15 13:52:32'),
(200, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '72b0a35715fa29ff0636c2bb90fcb3a6', 16, '2021-01-15 14:11:04'),
(201, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '72b0a35715fa29ff0636c2bb90fcb3a6', 21, '2021-01-15 14:12:30'),
(202, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '922e1b63bf74db3361a78c418b85ad03', 25, '2021-01-15 14:13:18'),
(203, 1, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '922e1b63bf74db3361a78c418b85ad03', 11, '2021-01-15 14:13:58'),
(204, 1, 26, 0, 'd41d8cd98f00b204e9800998ecf8427e', '922e1b63bf74db3361a78c418b85ad03', 16, '2021-01-15 17:01:23');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `interventions`
--
ALTER TABLE `interventions`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `interventions_de`
--
ALTER TABLE `interventions_de`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `intervention_categories`
--
ALTER TABLE `intervention_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `popup_actions`
--
ALTER TABLE `popup_actions`
  ADD PRIMARY KEY (`action_id`);

--
-- Indices de la tabla `users_table`
--
ALTER TABLE `users_table`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user_activity`
--
ALTER TABLE `user_activity`
  ADD PRIMARY KEY (`activity_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `interventions`
--
ALTER TABLE `interventions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `interventions_de`
--
ALTER TABLE `interventions_de`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `intervention_categories`
--
ALTER TABLE `intervention_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `popup_actions`
--
ALTER TABLE `popup_actions`
  MODIFY `action_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `users_table`
--
ALTER TABLE `users_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `user_activity`
--
ALTER TABLE `user_activity`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=205;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
