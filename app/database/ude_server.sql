-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-02-2021 a las 12:40:01
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
(1, '1', 'Other users had problems at work after posting about their drug consumption', 0.688),
(2, '1', 'Other users have felt embarrassed after posting about their alcohol consumption', 0.435),
(3, '1', 'Other users have lost their job after posting about their drug consumption', 0.825),
(4, '1', 'Other users have last their job after posting about their alcohol consumption', 0.835),
(5, '1', 'Other users have felt embarrassed after posting about their drug consumption', 0.5),
(6, '1', 'Other users had problems at work after posting about their alcohol consumption', 0.535),
(7, '2', 'Other users have lost their job after posting a naked or semi-naked picture of themselves', 0.875),
(8, '2', 'Other users had problems at work after posting a naked or semi-naked picture of themselves', 0.758),
(9, '2', 'Other users have lost their job after posting about a personal sexual encounter or experience', 0.58),
(10, '2', 'Other users had problems at work after posting about a personal sexual encounter or experience', 0.609),
(11, '2', 'Other users have felt embarrassed after posting a naked or semi-naked picture of themselves', 0.756),
(12, '2', 'Other users have felt embarrassed after posting about a personal sexual encounter or experience', 0.579),
(13, '3', 'Other users have lost their job after posting about their religious beliefs', 0.775),
(14, '3', 'Other users had problems at work after posting about their political views', 0.448),
(15, '3', 'Other users have lost friendships after posting about their religious beliefs', 0.478),
(16, '3', 'Other users have lost friendships after posting about their political views', 0.445),
(17, '3', 'Other users had problems at work after posting about their religious beliefs', 0.434),
(18, '3', 'Other users have lost their job after posting about their political views', 0.822),
(19, '4', 'Other users have lost friendships after posting a negative comment about another person', 0.553),
(20, '4', 'Other users had problems at work after posting a negative comment about their employer', 0.657),
(21, '4', 'Other users have lost their job after posting a negative comment about their employer', 0.806),
(22, '5', 'Other users have been stalked after posting their current location', 0.725),
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

--
-- Volcado de datos para la tabla `interventions_de`
--

INSERT INTO `interventions_de` (`id`, `category`, `message`, `risk`) VALUES
(1, '1', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie etwas über ihren Drogenkonsum gepostet haben', 0.688),
(2, '1', 'Andere NutzerInnen haben sich geschämt, nachdem sie etwas über ihren Alkoholkonsum gepostet haben', 0.435),
(3, '1', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie etwas über ihren Drogenkonsum gepostet haben', 0.825),
(4, '1', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie etwas über ihren Alkoholkonsum gepostet haben', 0.835),
(5, '1', 'Andere NutzerInnen haben sich geschämt, nachdem sie etwas über ihren Drogenkonsum gepostet haben', 0.5),
(6, '1', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie etwas über ihren Alkoholkonsum gepostet haben', 0.535),
(7, '2', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie ein Bild von sich gepostet haben, auf dem \r\nsie nackt oder halbnackt waren', 0.875),
(8, '2', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie ein Bild von sich gepostet haben, auf dem \r\nsie nackt oder halbnackt waren', 0.758),
(9, '2', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie über eine persönliche sexuelle Begegnung oder Erfahrung gepostet haben', 0.58),
(10, '2', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie über eine persönliche sexuelle Begegnung oder Erfahrung gepostet haben', 0.609),
(11, '2', 'Andere NutzerInnen haben sich geschämt, nachdem sie ein Bild von sich gepostet haben, auf dem sie nackt oder halbnackt waren', 0.756),
(12, '2', 'Andere NutzerInnen haben sich geschämt, nachdem sie über eine persönliche sexuelle Begegnung oder Erfahrung gepostet haben', 0.579),
(13, '3', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie etwas über ihre religiösen Überzeugungen gepostet haben', 0.775),
(14, '3', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie etwas über ihre politischen Ansichten gepostet haben', 0.448),
(15, '3', 'Andere NutzerInnen haben Freundschaften verloren, nachdem sie etwas über ihre religiösen Überzeugungen gepostet haben', 0.478),
(16, '3', 'Andere NutzerInnen haben Freundschaften verloren, nachdem sie etwas über ihre politischen Ansichten gepostet haben', 0.445),
(17, '3', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie etwas über ihre religiösen Überzeugungen gepostet haben', 0.434),
(18, '3', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie etwas über ihre politischen Ansichten gepostet haben', 0.822),
(19, '4', 'Andere NutzerInnen haben Freundschaften verloren, nachdem sie einen negativen Kommentar über eine andere Person gepostet haben', 0.553),
(20, '4', 'Andere NutzerInnen hatten Probleme am Arbeitsplatz, nachdem sie einen negativen Kommentar über ihre(n) Arbeitgeber(in) gepostet haben', 0.657),
(21, '4', 'Andere NutzerInnen haben ihren Arbeitsplatz verloren, nachdem sie einen negativen Kommentar über ihre(n) Arbeitgeber(in) gepostet haben', 0.806),
(22, '5', 'Andere NutzerInnen sind gestalkt worden, nachdem sie ihren aktuellen Standort gepostet haben', 0.725),
(23, '5', 'Andere NutzerInnen sind Opfer von Einbrüchen geworden, nachdem sie ihre Wohnadresse gepostet haben', 0.857),
(24, '6', 'Andere NutzerInnen haben Nachrichten und Anrufe von Fremden erhalten, nachdem sie ihre Telefonnummer gepostet haben', 0.508),
(25, '6', 'Andere NutzerInnen haben finanziellen Betrug erlebt, nachdem sie ihre Kreditkartennummer gepostet haben', 0.818),
(26, '6', 'Andere NutzerInnen haben Spam-Nachrichten erhalten, nachdem sie ihre E-Mail-Adresse gepostet haben', 0.403);

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
(208, 0, 25, 0, 'd41d8cd98f00b204e9800998ecf8427e', '72b0a35715fa29ff0636c2bb90fcb3a6', 22, '2021-02-16 11:34:50');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

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
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=209;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
