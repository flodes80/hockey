-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 19 avr. 2021 à 17:12
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `hockey`
--

-- --------------------------------------------------------

--
-- Structure de la table `gardien`
--

DROP TABLE IF EXISTS `gardien`;
CREATE TABLE IF NOT EXISTS `gardien` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `gardien`
--

INSERT INTO `gardien` (`id`, `nom`, `prenom`) VALUES
(1, 'Garcia', 'Florian'),
(2, 'Le Berre', 'Brendan');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Structure de la table `matchs`
--

DROP TABLE IF EXISTS `matchs`;
CREATE TABLE IF NOT EXISTS `matchs` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `lieu` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matchs`
--

INSERT INTO `matchs` (`id`, `date`, `lieu`) VALUES
(1, '2021-02-14 00:00:00', 'Amiens'),
(2, '2021-02-24 00:00:00', 'Lille');

-- --------------------------------------------------------

--
-- Structure de la table `tir`
--

DROP TABLE IF EXISTS `tir`;
CREATE TABLE IF NOT EXISTS `tir` (
  `nbBut` int(11) NOT NULL,
  `nbTir` int(11) NOT NULL,
  `zoneTir_id` int(11) NOT NULL,
  `zoneArret_id` int(11) NOT NULL,
  `match_id` int(11) NOT NULL,
  `gardien_id` int(11) NOT NULL,
  PRIMARY KEY (`zoneTir_id`,`zoneArret_id`,`match_id`,`gardien_id`),
  KEY `FKg2pfhutgw3vc5rrjfbbck9u7u` (`zoneArret_id`),
  KEY `FKijk3eb28s61wbj7jn8aemqksd` (`match_id`),
  KEY `FK86yomu8tmhsxrhdr1hw9mgc6n` (`gardien_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tir`
--

INSERT INTO `tir` (`nbBut`, `nbTir`, `zoneTir_id`, `zoneArret_id`, `match_id`, `gardien_id`) VALUES
(0, 10, 1, 1, 1, 1),
(1, 0, 1, 1, 1, 2),
(3, 1, 1, 1, 2, 1),
(0, 5, 1, 2, 1, 1),
(1, 3, 1, 3, 1, 1),
(0, 4, 1, 4, 1, 1),
(0, 4, 1, 5, 1, 1),
(0, 3, 1, 6, 1, 1),
(0, 3, 1, 7, 1, 1),
(0, 5, 1, 8, 1, 1),
(0, 5, 1, 9, 1, 1),
(0, 4, 2, 1, 1, 1),
(0, 1, 2, 1, 1, 2),
(1, 0, 2, 1, 2, 1),
(0, 3, 2, 2, 1, 1),
(0, 3, 2, 3, 1, 1),
(0, 2, 2, 4, 1, 1),
(0, 5, 2, 5, 1, 1),
(0, 3, 2, 6, 1, 1),
(0, 11, 2, 6, 2, 1),
(0, 3, 2, 7, 1, 1),
(0, 3, 2, 8, 1, 1),
(0, 2, 2, 9, 1, 1),
(0, 3, 3, 1, 1, 1),
(0, 3, 3, 2, 1, 1),
(0, 5, 3, 3, 1, 1),
(0, 3, 3, 4, 1, 1),
(0, 1, 3, 4, 1, 2),
(0, 3, 3, 5, 1, 1),
(0, 2, 3, 6, 1, 1),
(0, 2, 3, 7, 1, 1),
(1, 0, 3, 7, 1, 2),
(0, 3, 3, 8, 1, 1),
(0, 3, 3, 9, 1, 1),
(0, 3, 4, 1, 1, 1),
(0, 2, 4, 2, 1, 1),
(0, 2, 4, 3, 1, 1),
(0, 6, 4, 4, 1, 1),
(0, 4, 4, 5, 1, 1),
(0, 3, 4, 6, 1, 1),
(0, 2, 4, 7, 1, 1),
(0, 5, 4, 8, 1, 1),
(0, 3, 4, 9, 1, 1),
(0, 3, 5, 1, 1, 1),
(0, 2, 5, 2, 1, 1),
(0, 1, 5, 3, 1, 1),
(0, 3, 5, 4, 1, 1),
(0, 2, 5, 5, 1, 1),
(0, 4, 5, 6, 1, 1),
(0, 2, 5, 7, 1, 1),
(4, 2, 5, 7, 2, 1),
(0, 2, 5, 7, 2, 2),
(0, 3, 5, 8, 1, 1),
(0, 2, 5, 9, 1, 1),
(0, 3, 6, 1, 1, 1),
(1, 0, 6, 1, 1, 2),
(0, 2, 6, 2, 1, 1),
(0, 3, 6, 3, 1, 1),
(0, 2, 6, 4, 1, 1),
(0, 4, 6, 5, 1, 1),
(0, 2, 6, 6, 1, 1),
(0, 2, 6, 7, 1, 1),
(0, 3, 6, 8, 1, 1),
(0, 2, 6, 9, 1, 1),
(11, 0, 6, 9, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_syftr7gx86fwf7ox7bgvnnta7` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `name`, `password`) VALUES
(1, 'admin', 'mo90SfpIMG3cDEIzquMvNEHBus8fzfZzba/P4MqpHak=');

-- --------------------------------------------------------

--
-- Structure de la table `zonearret`
--

DROP TABLE IF EXISTS `zonearret`;
CREATE TABLE IF NOT EXISTS `zonearret` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `zonearret`
--

INSERT INTO `zonearret` (`id`, `libelle`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C'),
(4, 'D'),
(5, 'E'),
(6, 'F'),
(7, 'G'),
(8, 'H'),
(9, 'I');

-- --------------------------------------------------------

--
-- Structure de la table `zonetir`
--

DROP TABLE IF EXISTS `zonetir`;
CREATE TABLE IF NOT EXISTS `zonetir` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `zonetir`
--

INSERT INTO `zonetir` (`id`, `libelle`) VALUES
(1, '1'),
(2, '2'),
(3, '3'),
(4, '4'),
(5, '5'),
(6, '6');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tir`
--
ALTER TABLE `tir`
  ADD CONSTRAINT `FK86yomu8tmhsxrhdr1hw9mgc6n` FOREIGN KEY (`gardien_id`) REFERENCES `gardien` (`id`),
  ADD CONSTRAINT `FKcc4noqpum7shsxcwpnpbd7i48` FOREIGN KEY (`zoneTir_id`) REFERENCES `zonetir` (`id`),
  ADD CONSTRAINT `FKg2pfhutgw3vc5rrjfbbck9u7u` FOREIGN KEY (`zoneArret_id`) REFERENCES `zonearret` (`id`),
  ADD CONSTRAINT `FKijk3eb28s61wbj7jn8aemqksd` FOREIGN KEY (`match_id`) REFERENCES `matchs` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
