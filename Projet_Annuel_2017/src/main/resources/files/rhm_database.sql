-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 01 Mars 2017 à 15:38
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `rhm_database`
--

-- --------------------------------------------------------

--
-- Structure de la table `room`
--

CREATE TABLE `room` (
  `Id_room` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Building` varchar(25) NOT NULL,
  `Number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `Id_client` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Firstname` varchar(25) NOT NULL,
  `Birthday` date NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Phone` varchar(25) DEFAULT NULL,
  `Country` varchar(25) DEFAULT NULL,
  `City` varchar(25) DEFAULT NULL,
  `Address` varchar(25) DEFAULT NULL,
  `Postal_code` varchar(25) DEFAULT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `book_room`
--

CREATE TABLE `book_room` (
  `Id_room` int(11) NOT NULL,
  `Id_book` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `book_table`
--

CREATE TABLE `book_table` (
  `Id_book` int(11) NOT NULL,
  `Id_table` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `Id_book` int(11) NOT NULL,
  `Date_book` datetime NOT NULL,
  `Date_start` date NOT NULL,
  `Date_end` date NOT NULL,
  `People_number` int(11) DEFAULT NULL,
  `Price` float NOT NULL,
  `Payment_mode` varchar(25) DEFAULT NULL,
  `Hour_start` time DEFAULT NULL,
  `Id_client` int(11) NOT NULL,
  `Id_partyRoom` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `salledefetes`
--

CREATE TABLE `salledefetes` (
  `Id_partyRoom` int(11) NOT NULL,
  `Event` varchar(35) NOT NULL,
  `Number_chairs` int(11) DEFAULT NULL,
  `Number_tables` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `table_restaurant`
--

CREATE TABLE `table_restaurant` (
  `Id_table` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Place_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`Id_room`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`Id_client`);

--
-- Index pour la table `book_room`
--
ALTER TABLE `book_room`
  ADD PRIMARY KEY (`Id_room`,`Id_book`),
  ADD KEY `FK_book_room_Id_book` (`Id_book`);

--
-- Index pour la table `book_table`
--
ALTER TABLE `book_table`
  ADD PRIMARY KEY (`Id_book`,`Id_table`),
  ADD KEY `FK_book_table_Id_table` (`Id_table`);

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`Id_book`),
  ADD KEY `FK_book_Id_client` (`Id_client`),
  ADD KEY `FK_book_Id_partyRoom` (`Id_partyRoom`);

--
-- Index pour la table `salledefetes`
--
ALTER TABLE `salledefetes`
  ADD PRIMARY KEY (`Id_partyRoom`);

--
-- Index pour la table `table_restaurant`
--
ALTER TABLE `table_restaurant`
  ADD PRIMARY KEY (`Id_table`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `Id_room` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `Id_client` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `book`
--
ALTER TABLE `book`
  MODIFY `Id_book` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `salledefetes`
--
ALTER TABLE `salledefetes`
  MODIFY `Id_partyRoom` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `table_restaurant`
--
ALTER TABLE `table_restaurant`
  MODIFY `Id_table` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `book_room`
--
ALTER TABLE `book_room`
  ADD CONSTRAINT `FK_book_room_Id_room` FOREIGN KEY (`Id_room`) REFERENCES `room` (`Id_room`),
  ADD CONSTRAINT `FK_book_room_Id_book` FOREIGN KEY (`Id_book`) REFERENCES `book` (`Id_book`);

--
-- Contraintes pour la table `book_table`
--
ALTER TABLE `book_table`
  ADD CONSTRAINT `FK_book_table_Id_book` FOREIGN KEY (`Id_book`) REFERENCES `book` (`Id_book`),
  ADD CONSTRAINT `FK_book_table_Id_table` FOREIGN KEY (`Id_table`) REFERENCES `table_restaurant` (`Id_table`);

--
-- Contraintes pour la table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FK_book_Id_client` FOREIGN KEY (`Id_client`) REFERENCES `client` (`Id_client`),
  ADD CONSTRAINT `FK_book_Id_partyRoom` FOREIGN KEY (`Id_partyRoom`) REFERENCES `salledefetes` (`Id_partyRoom`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
