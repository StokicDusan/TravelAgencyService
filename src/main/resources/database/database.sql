-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Aug 25, 2020 at 08:27 PM
-- Server version: 5.7.28
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travelagencydb1`
--
CREATE DATABASE IF NOT EXISTS `travelagencydb1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `travelagencydb1`;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `country` varchar(64) NOT NULL,
  `city` varchar(64) NOT NULL,
  `street` varchar(64) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `arrangement`
--

DROP TABLE IF EXISTS `arrangement`;
CREATE TABLE IF NOT EXISTS `arrangement` (
  `arrangement_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_id` int(10) UNSIGNED NOT NULL,
  `offer_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`arrangement_id`),
  KEY `fk_arrangement_customer_id_idx` (`customer_id`),
  KEY `fk_arrangement_offer_id_idx` (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `contact_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `telephone` varchar(64) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address_id` int(10) UNSIGNED NOT NULL,
  `contact_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(64) NOT NULL,
  `surname` varchar(64) NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `fk_customer_address_id_idx` (`address_id`),
  KEY `fk_customer_contact_id_idx` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
CREATE TABLE IF NOT EXISTS `offer` (
  `offer_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `offer_type_id` int(10) UNSIGNED NOT NULL,
  `country` varchar(45) NOT NULL,
  `location` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` mediumtext,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`offer_id`),
  KEY `fk_offer_offer_type_id_idx` (`offer_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `offer`
--

INSERT INTO `offer` (`offer_id`, `created_at`, `offer_type_id`, `country`, `location`, `name`, `description`, `active`) VALUES
(1, '2020-08-25 13:55:24', 1, 'Srbija', 'Bukovačka banja', 'Hotel Izvor', 'Hotel Izvor, luksuzno SPA & Wellness resort hotelsko zdanje, se nalazi u srcu Šumadije i jedne od najlepših kraljevskih banja Srbije – Bukovičke banje.\r\nHotel je proglašen najboljim hotelom na Mediteranu i u jugoistočnoj Evropi u 2015. \r\nOvaj luksuzni hotel je kongresni, spa&wellness centar koji se prostire na 32000 m2, udaljen je od Beocitya oko 75 km. Hotel se nalazi na samo 12 km udaljenosti od Topole i Oplenca, mesta gde se nalazi mauzolej dinastije Karađorđević, čiji obilazak je uključen u veliki number tura kroz Srbiju.\r\nU sklopu kompleksa \"Izvor\" nalazi se aqua park, čije je korišćenje besplatno za goste hotela. 3 bazena sa mineralnom vodom, 12 slajdova za odrasle, 3 slajda za decu i mnoštvo atrakcija će zadovoljiti i najzahtevnije posetioce koji su imali priliku da posete slična mesta u svetu.', b'1'),
(2, '2020-08-25 14:02:00', 3, 'Crna Gora', 'Budva', 'Turističko naselje SLOVENSKA PLAŽA', 'Turističko naselje SLOVENSKA PLAŽA, nalazi se na samoj obali. \r\nSmeštaj: Potpuno adaptirane smeštajne jedinice koje opremljenošću i sadržajem odgovaraju kategoriji hotela sa 3*+ i lux 4*+. \r\nNaselje čini 10 zasebnih vila, funkcionalnih celina i to: 6 vila u kategoriji 3*+ sa 422 sobe i 100 apartmana sa terasom i 4 vile u kategoriji lux 4*+ sa 441 sobom i 53 apartmana sa terasom. \r\nSvaka soba, apartman poseduju kupatilo/ wc,TV, klima uređaj, telefon, frižider, sef i balkon. \r\nSadržaj hotela: Sobe i apartmani povezani su uličicama sa korzoom, na kojem su smešteni svi prateći objekti: radnje sa suvenirima, samoposluge, kafe-barovi, nacionalni i specijalizovani restorani, picerije, poslastičarnice, pivnica, gril, aperitiv barovi, radnje sa raznom robom. \r\nTu su i frizerski salon, ambulanta, dva otvorena bazena, 14 teniskih terena. Na plaži su uređena igrališta za goste. \r\nUz plažu gosti mogu uz povoljne cene i dodatne popuste koristiti plažni mobilijar.\r\n', b'1'),
(3, '2020-08-25 14:02:08', 3, 'Crna Gora', 'Budva', 'Hotel Aleksandar', 'Hotel Aleksandar je sezonski odmorišni hotel, smešten pored turističkog naselja “Slovenska plaža”. \r\nUsluga: Polupansion (doručak, večera) - švedski sto. Mogućnost doplate punog pansiona ili All Inclusive usluge. \r\nSmeštaj: Raspolaže komfornim sobama i apartmanima sa 2, 3, 4 i 5 ležaja, ukupnog kapaciteta 450 ležaja. Sve sobe i apartmani su klimatizovani, sa TV aparatom i kablovskim priključkom, standardno opremljeni. \r\nSadržaj hotela: Restoran, aperitiv i snek bar, poslastičarnica, dve banket sale, frizerski i kozmetički salon, parking i ostali sadržaji za ugodan odmor i zabavu. \r\nHotel, unutar objekta, poseduje otvoreni bazen sa morskom vodom, a ljubitelji otvorenog mora mogu da uživaju i na prelepoj plaži, smeštenoj uz sam hotel, koja pruža i sve ostale užitke rekreacije na pesku i moru. ', b'0'),
(4, '2020-08-25 14:36:39', 1, 'Srbija', 'Vrnjačka banja', 'Hotel Merkur', 'Prednost banjskog turizma je u tome što se banje mogu posećivati i leti i zimi. \r\nBogate kulturno – istorijskim spomenicima, različitim manifestacijama i prirodnim motivima, one su podjedanako privlačne turistima u svako doba godine. \r\nBanje su pogodne, kako za odmor i rekreaciju, tako i za prevenciju i rehabilitaciju posetilaca u kombinaciji sa wellnes i spa centrima, u kojima gosti mogu uživati u masažama, hidromasažama, saunama, kupanju u bazenima sa termalnom vodom, đakuziju…\r\n\r\nNa celokupnoj teritoriji Srbije registrovano je oko 300 termomineralnih izvora. \r\nZavisno od geografskog položaja, klime, neke banje su se komercijalno izdvojile, uz razvijeniju infrastrukturu, pa boravak u njima pruža osećaj kao da ste u evropskim metropolama. \r\nNasuprot njima, neka banjska lečilišta su oaze mira, daleko od gradske gužve, u prirodnom netaknutom okruženju.', b'1'),
(5, '2020-08-25 15:51:55', 1, 'Srbija', 'Zlatibor', 'Klub Satelit', 'Planina i vazdušna banja Zlatibor, sa najdužom turističkom tradicijom, nalazi se u Zapadnoj Srbiji, 230 km jugo-zapadno od Beograda. \r\nProsečna nadmorska visina je 1000 m. Za ljubitelje skijanja, na 7 km od centra Zlatibora se nalazi ski centar Tornik, najviši vrh ove prelepe planine.\r\nKlub Satelit jedan je od najtraženijih objekata za smeštaj na Zlatiboru kako kod domaćih tako i kod inostranih gostiju. \r\nDobitnik je brojnih priznanja za kvalitet rada.\r\nTri najvažnije karakteristike objekta jesu luksuzna opremljenost, nesvakidašnje prijatan ambijent i ljubazno osoblje. \r\nSvi apartmani i sobe su unikatno uređeni tako da ispunjavaju očekivanja i zahtevnijih hedonista.', b'1'),
(6, '2020-08-25 15:53:00', 1, 'Srbija', 'Zlatibor', 'Hotel Mona', 'Planina i vazdušna banja Zlatibor, sa najdužom turističkom tradicijom, nalazi se u Zapadnoj Srbiji, 230 km jugo-zapadno od Beograda. \r\nProsečna nadmorska visina je 1000 m. Za ljubitelje skijanja, na 7 km od centra Zlatibora se nalazi ski centar Tornik, najviši vrh ove prelepe planine.\r\nHotel Zlatibor Mona, nalazi se u samom centru Zlatibora, u neposrednoj blizini svih sadržaja ovog turističkog centra. Udaljen je 235 km jugozapadno od Beograda.', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `offer_picture`
--

DROP TABLE IF EXISTS `offer_picture`;
CREATE TABLE IF NOT EXISTS `offer_picture` (
  `offer_picture_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `offer_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(128) NOT NULL,
  `short_description` varchar(64) NOT NULL DEFAULT 'no description',
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`offer_picture_id`),
  KEY `fk_offer_picture_offer_id_idx` (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `offer_picture`
--

INSERT INTO `offer_picture` (`offer_picture_id`, `created_at`, `offer_id`, `name`, `short_description`, `active`) VALUES
(1, '2020-08-25 16:56:26', 1, 'bukovacka_banja_11.png', 'hotel izvor', b'1'),
(2, '2020-08-25 17:37:49', 5, 'zlatibor_111.png', 'hotel', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `offer_type`
--

DROP TABLE IF EXISTS `offer_type`;
CREATE TABLE IF NOT EXISTS `offer_type` (
  `offer_type_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`offer_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `offer_type`
--

INSERT INTO `offer_type` (`offer_type_id`, `created_at`, `name`) VALUES
(1, '2020-08-05 12:02:42', 'Spa & Wellness'),
(2, '2020-08-05 12:02:42', 'Winter vacation'),
(3, '2020-08-05 12:02:42', 'Summer vacation'),
(4, '2020-08-05 12:58:03', 'Cruising'),
(5, '2020-08-25 13:51:00', 'Hike');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `arrangement`
--
ALTER TABLE `arrangement`
  ADD CONSTRAINT `fk_arrangement_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_arrangement_offer_id` FOREIGN KEY (`offer_id`) REFERENCES `offer` (`offer_id`) ON UPDATE CASCADE;

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `fk_customer_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_customer_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`) ON UPDATE CASCADE;

--
-- Constraints for table `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `fk_offer_offer_type_id` FOREIGN KEY (`offer_type_id`) REFERENCES `offer_type` (`offer_type_id`) ON UPDATE CASCADE;

--
-- Constraints for table `offer_picture`
--
ALTER TABLE `offer_picture`
  ADD CONSTRAINT `fk_offer_picture_offer_id` FOREIGN KEY (`offer_id`) REFERENCES `offer` (`offer_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
