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
-- Database: `zadatak2tema8`
--
CREATE DATABASE IF NOT EXISTS `zadatak2tema8` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `zadatak2tema8`;

-- --------------------------------------------------------

--
-- Table structure for table `adresa`
--

DROP TABLE IF EXISTS `adresa`;
CREATE TABLE IF NOT EXISTS `adresa` (
  `adresa_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `drzava` varchar(64) NOT NULL,
  `grad` varchar(64) NOT NULL,
  `ulica` varchar(64) NOT NULL,
  `broj` int(11) NOT NULL,
  PRIMARY KEY (`adresa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `aranzman`
--

DROP TABLE IF EXISTS `aranzman`;
CREATE TABLE IF NOT EXISTS `aranzman` (
  `aranzman_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `korisnik_id` int(10) UNSIGNED NOT NULL,
  `ponuda_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`aranzman_id`),
  KEY `fk_aranzman_korisnik_id_idx` (`korisnik_id`),
  KEY `fk_aranzman_ponuda_id_idx` (`ponuda_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `kontakt`
--

DROP TABLE IF EXISTS `kontakt`;
CREATE TABLE IF NOT EXISTS `kontakt` (
  `kontakt_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `telefon` varchar(64) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`kontakt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `korisnik_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adresa_id` int(10) UNSIGNED NOT NULL,
  `kontakt_id` int(10) UNSIGNED NOT NULL,
  `ime` varchar(64) NOT NULL,
  `prezime` varchar(64) NOT NULL,
  PRIMARY KEY (`korisnik_id`),
  KEY `fk_korisnik_adresa_id_idx` (`adresa_id`),
  KEY `fk_korisnik_kontakt_id_idx` (`kontakt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ponuda`
--

DROP TABLE IF EXISTS `ponuda`;
CREATE TABLE IF NOT EXISTS `ponuda` (
  `ponuda_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ponuda_vrsta_id` int(10) UNSIGNED NOT NULL,
  `drzava` varchar(45) NOT NULL,
  `mesto` varchar(64) NOT NULL,
  `naziv` varchar(64) NOT NULL,
  `opis` mediumtext,
  `aktivna` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ponuda_id`),
  KEY `fk_ponuda_ponuda_vrsta_id_idx` (`ponuda_vrsta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ponuda`
--

INSERT INTO `ponuda` (`ponuda_id`, `created_at`, `ponuda_vrsta_id`, `drzava`, `mesto`, `naziv`, `opis`, `aktivna`) VALUES
(1, '2020-08-25 13:55:24', 1, 'Srbija', 'Bukovačka banja', 'Hotel Izvor', 'Hotel Izvor, luksuzno SPA & Wellness resort hotelsko zdanje, se nalazi u srcu Šumadije i jedne od najlepših kraljevskih banja Srbije – Bukovičke banje.\r\nHotel je proglašen najboljim hotelom na Mediteranu i u jugoistočnoj Evropi u 2015. \r\nOvaj luksuzni hotel je kongresni, spa&wellness centar koji se prostire na 32000 m2, udaljen je od Beograda oko 75 km. Hotel se nalazi na samo 12 km udaljenosti od Topole i Oplenca, mesta gde se nalazi mauzolej dinastije Karađorđević, čiji obilazak je uključen u veliki broj tura kroz Srbiju.\r\nU sklopu kompleksa \"Izvor\" nalazi se aqua park, čije je korišćenje besplatno za goste hotela. 3 bazena sa mineralnom vodom, 12 slajdova za odrasle, 3 slajda za decu i mnoštvo atrakcija će zadovoljiti i najzahtevnije posetioce koji su imali priliku da posete slična mesta u svetu.', b'1'),
(2, '2020-08-25 13:59:36', 3, 'Crna Gora', 'Ada Bojana', 'Naturističko naselje Ada Bojana', 'Naturističko naselje Ada Bojana, nalazi se na ostrvu na ušću reke Bojane u Jadransko more. Ako želite letovanje u kostimima Adama i Eve, prava adresa je Ada Bojana i pravo mesto za ponovni i pravi dodir tela s Majkom Prirodom. Usluga: Polupansion (doručak, večera) - švedski sto. Smeštaj: Ovaj raj za nudiste raspolaže sa 500 ležaja u vilama i bungalovima u sobama sa kupatilom i terasom. Sadržaj naselja: Ovde možete upražnjavati sportove na vodi ili sportove na kopnu, po želji. \"A\" deo naselja raspolaže i svim pratećim ugostiteljskim i drugim sadržajima. ', b'1'),
(3, '2020-08-25 14:02:00', 3, 'Crna Gora', 'Budva', 'Turističko naselje SLOVENSKA PLAŽA', 'Turističko naselje SLOVENSKA PLAŽA, nalazi se na samoj obali. \r\nSmeštaj: Potpuno adaptirane smeštajne jedinice koje opremljenošću i sadržajem odgovaraju kategoriji hotela sa 3*+ i lux 4*+. \r\nNaselje čini 10 zasebnih vila, funkcionalnih celina i to: 6 vila u kategoriji 3*+ sa 422 sobe i 100 apartmana sa terasom i 4 vile u kategoriji lux 4*+ sa 441 sobom i 53 apartmana sa terasom. \r\nSvaka soba, apartman poseduju kupatilo/ wc,TV, klima uređaj, telefon, frižider, sef i balkon. \r\nSadržaj hotela: Sobe i apartmani povezani su uličicama sa korzoom, na kojem su smešteni svi prateći objekti: radnje sa suvenirima, samoposluge, kafe-barovi, nacionalni i specijalizovani restorani, picerije, poslastičarnice, pivnica, gril, aperitiv barovi, radnje sa raznom robom. \r\nTu su i frizerski salon, ambulanta, dva otvorena bazena, 14 teniskih terena. Na plaži su uređena igrališta za goste. \r\nUz plažu gosti mogu uz povoljne cene i dodatne popuste koristiti plažni mobilijar.\r\n', b'1'),
(4, '2020-08-25 14:02:08', 3, 'Crna Gora', 'Budva', 'Hotel Aleksandar', 'Hotel Aleksandar je sezonski odmorišni hotel, smešten pored turističkog naselja “Slovenska plaža”. \r\nUsluga: Polupansion (doručak, večera) - švedski sto. Mogućnost doplate punog pansiona ili All Inclusive usluge. \r\nSmeštaj: Raspolaže komfornim sobama i apartmanima sa 2, 3, 4 i 5 ležaja, ukupnog kapaciteta 450 ležaja. Sve sobe i apartmani su klimatizovani, sa TV aparatom i kablovskim priključkom, standardno opremljeni. \r\nSadržaj hotela: Restoran, aperitiv i snek bar, poslastičarnica, dve banket sale, frizerski i kozmetički salon, parking i ostali sadržaji za ugodan odmor i zabavu. \r\nHotel, unutar objekta, poseduje otvoreni bazen sa morskom vodom, a ljubitelji otvorenog mora mogu da uživaju i na prelepoj plaži, smeštenoj uz sam hotel, koja pruža i sve ostale užitke rekreacije na pesku i moru. ', b'0'),
(5, '2020-08-25 14:04:26', 5, 'Srbija', 'Golubac', 'Tvrđava Golubački grad', 'Prostorna kulturno-istorijska i prirodna celina Golubački grad je jedinstvena i nedeljiva funkcionalna celina prirodnih (rezervat prirode Golubački grad) i stvorenih resursa (srednjevekovna tvrđava i arheološko nalazište pored same tvrđave) i vrednosti od značaja za turizam, prostor koji zbog svojih karakteristika, vrednosti i prioritetne turističke namene je dobio poseban režim organizacije, uređenja, korišćenja i zaštite. Na osnovu Zakona o turizmu Vlada Republike Srbije je 3. juna 2011. godine proglasila ovu celinu Turističkim prostorom «Tvrđava golubački grad».\r\nOsnovnu turističku atrakciju Turističkog prostora čini nepokretno kulturno dobro – srednjovekovna tvrđava «Golubački grad».\r\n\r\nUkupna površina Turističkog prostora iznosi 21,54.32 m2 i on se u celini nalazi na teritoriji opštine Golubac.\r\n\r\nTurističkim prostorom «Tvrđava Golubački grad» upravlja Društvo sa ograničenom odgovornošću za razvoj turizma «Tvrđava Golubački grad».\r\n\r\nDruštvo ima obaveze da u istoimenom Turističkom prostoru razvija:\r\n\r\nturističke atrakcije;\r\nturistički proizvod;\r\nturističku infrastrukturu;\r\nturističku suprastrukturu;\r\nmarketinške i promotivne aktivnosti.\r\nDruštvo «Tvrđava Golubački grad» d.o.o osnovali su Vlada Republike Srbije i opština Golubac a na osnovu člana 14. stav 3. Zakona o turizmu (“Službeni glasnik RS”, broj 36/09 i 88/2010), člana 104. stav 1. i 106. Zakona o privrednim društvima (“Službeni glasnik RS”, broj 125/04). Sedište Društva je u Golupcu.\r\n\r\nMisija Društva jeste upravljanje razvojem turizma na Turističkim prostorom Tvrđava „Golubački grad“ a posebno zaštita kulturno – istorijskog nasleđa, prirodnog bogatstva i životne sredine;\r\n\r\nrezervisanje, zaštita i regulacija prostora, obuhvatajući prilagođavanje i redefinisanje urbanističkog okvira – prostorno planske dokumentacije, kao nephodne pretpostavke za sprovođenje predloženog koncepta razvoja turizma;\r\n\r\ndefinisanje prioriteta i realizaciji projekata izgradnje neophodnih objekata javne i komunalne infrastrukture, kao i projekata infrastrukturnog opremanja lokacija rezervisanih za objekte turističke infrastrukture;\r\n\r\nidentifikacija ključnih investicionih projekata na području prostora;\r\n\r\npromovisanje investicionih projekata i pregovorima sa potencijalnim investitorima;\r\n\r\nizgradnja odnosa sa potencijalnim partnerima– investitorima i pružanje tehničke pomoći i podrške u postupku realizacije investicionih projekata;\r\n\r\nrazrada arhitektonskih i urbanističkih idejnih rešenja, kao i izradi tehničke dokumentacije;\r\n\r\nizgradnja i jačanje konkurentske pozicije područja u saradnji sa lokalnim samoupravama i drugim zainteresovanim stranama kroz jačanje preduzetničke kulture, stvaranje kapaciteta za inovacije, jačanje kapaciteta lokalnih samouprava i organizacija koje se bave destinacijskim menadžmentom i marketingom, kao i kroz uspostavanje zajedničkih\r\n\r\ninicijativa sa susednim opštinama sa teritorije Rumunije – korišćenje mogućnosti formulisanja zajedničkih projekata kojima bi se konkurisalo za dobijanje finansijskih sredstava iz Programa prekogranične saradnje Srbija – Rumunija;\r\n\r\nsaradnja sa ključnim zainteresovanim stranama u turizmu na nivou regiona, ali i na nacionalnom nivou na razvojnim projektima i marketinškim aktivnostima – Regionalna turistička organizacija „Donje Podunavlje“, regionalne razvojne agencije, Dunavski centar za kompetencije, programi tehničke pomoći opštinama Donjeg Podunavlja, Turistička organizacija Srbije, ministarstvo zaduženo za poslove turizma, itd;\r\n\r\npodizanje svesti javnosti na lokalnom i regionalnom nivoy o turističkom prostoru kao prostoru koji ima mogućnost ostvarivanja održivog ekonomskog rasta kroz diversifikaciju delatnosti putem razvoja turizma.', b'1'),
(6, '2020-08-25 14:36:39', 1, 'Srbija', 'Vrnjačka banja', 'Hotel Merkur', 'Prednost banjskog turizma je u tome što se banje mogu posećivati i leti i zimi. \r\nBogate kulturno – istorijskim spomenicima, različitim manifestacijama i prirodnim motivima, one su podjedanako privlačne turistima u svako doba godine. \r\nBanje su pogodne, kako za odmor i rekreaciju, tako i za prevenciju i rehabilitaciju posetilaca u kombinaciji sa wellnes i spa centrima, u kojima gosti mogu uživati u masažama, hidromasažama, saunama, kupanju u bazenima sa termalnom vodom, đakuziju…\r\n\r\nNa celokupnoj teritoriji Srbije registrovano je oko 300 termomineralnih izvora. \r\nZavisno od geografskog položaja, klime, neke banje su se komercijalno izdvojile, uz razvijeniju infrastrukturu, pa boravak u njima pruža osećaj kao da ste u evropskim metropolama. \r\nNasuprot njima, neka banjska lečilišta su oaze mira, daleko od gradske gužve, u prirodnom netaknutom okruženju.', b'1'),
(7, '2020-08-25 15:17:41', 4, 'Karibi', 'Karibsko more', 'Karibsko more', 'Majami, Ocho Rios, Džordž Taun, Cozumel, San Huan\r\n\r\nKaribi - Popust 10%! Avio prevoz, aerodromske i lučke takse, transferi, 2 noćenje sa doručkom u hotelu sa 3* u Majamiju i 15-dnevno krstarenje na bazi punog pansiona na brodu MSC Seaside, od 1979 € (po osobi).', b'1'),
(8, '2020-08-25 15:22:05', 4, 'Rusija', 'Volgograd', 'Krstarenje Volgom', 'Rusija Jedna od najpopularnijih destinacija na svetu. Privlači svake godine milione turista iz celog sveta svojom lepotom, prelepim građevinama i fenomenalnim mestima za zabavu i nezaboravan provod.\r\nMoskva je prestonica Rusije, moderna metropola, prepuna carske istorije i arhitektonske raskoši. \r\nOsnovana 1147. godine od strane princa Jurija Dolgorukog, danas Moskva predstavlja jedan od najvažnijih svetsih gradova sa populacijom od preko 10 miliona stanovnika. \r\nNaselje zanatlija i trgovaca, u 15. veku postaje glavni grad Rusije. Premeštanjem ruske prestonice u St.Petersburg 1712. godine, postaje druga prestonica. Broji preko 13 miliona stanovnika, zauzima površinu od 1.000 km2 . \r\nSmeštena je na 7 brežuljaka.\r\nUglič Osnovan 1148. godine, Ugljič je jedan od najvoljenijih gradova stare Rusije. Prelep je pogled na grad sa reke Volge, sa koje se na horizontu vide Crkva Vaskrsenja i Crkva Svetog Jovana. \r\nKrajem 16. Veka carica Marija Naga, sedma žena Ivana Groznog, živela je u egzilu u Ugljičkom Kremlju. U dvorištu Kremlja nastradao je carević Dmitrij i na tom mestu je podignuta Crkva Svetog Mučenika Dimitrija, gde se i danas nalazi. \r\nU okviru Kremlja ce nalazi i najstarije zdanje u Ugljiču - Palata carevića Dmitrija.', b'1'),
(9, '2020-08-25 15:23:41', 5, 'Srbija', 'Golubac', 'Manastir Tumani', 'Ambijentalna lepota sa jedne i duhovni mir sa druge strane, čine  manastir Tuman vrednim kako u duhovnom tako i u kulturno-istorijskom smislu. Kao retko koja svetinja, ovaj braničevski manastir ostavlja divan utisak svakome ko se u njemu nađe, pleneći svojom lepotom i okruženjem. U njemu se na čudesan način prožimaju paradoksalne činjenice vere i budućnosti, satkana istorijom izvire sadašnjost, u centru svega imajući čoveka i njegov odnos sa Tvorcem. Manastir Tumane je svetinja u kojoj vera čini čuda. Ovde vernici pronalaze lek i spas. Pođite sa nama i doživite!\r\n', b'1'),
(10, '2020-08-25 15:39:20', 5, 'Bosan i Hercegovina', 'Sarajevo', 'Sarajevo sa posetom mostara', 'Ove sezone vam predstavljamo još jedan balkanski turistički dragulj - Sarajevo sa posetom Mostaru! Prošetajte kroz istoriju, kulturu, muziku i vreme sa nama, upoznajte najlepše kutke ovih gradova koji su ostali kao živi spomenici jednog vremena.\r\n\r\nSarajevo, glavni i najveći grad – ujedno i ekonomsko, univerzitetsko i kulturno središte Bosne i Hercegovine. Neposredno izvan grada, uzdiže se velelepni Trebević, a grad je dodatno išaran tokom reke Miljacke, koja je inspirisala mnoge tekstopisce i muzičare. \r\nOvo putovanje je idealno za hedoniste koji vole nešto laganiji tempo, mirne i šarene gradove, bez prevelike gužve. \r\nU ovom gradu će vas dočekati dobri i gostoprimivi domaćini, koji će vam ponuditi neke od preukusnih specijaliteta ovog kraja, uz to šetnja Baščaršijom jeste pravi osvrt na prošlost i atmosferu otomanske vladavine koja je još živa na ovim vanvremenskim ulicama.\r\n\r\nMostar je zaista čudesan grad, jedan od onih vanvremenskih kutaka koji mame turiste da ih posete. Želimo da vas upoznamo sa mestom u kome živi oko sto hiljada stanovnika. Grad je uglavnom turistički nastrojen, s tim što se ne mogu zaobići raznolikosti leve i desne obale. \r\nU Mostaru je jedna gradska kuća, jedan gradonačelnik i nadaleko čuveni simboli poput Starog mosta koji je sagrađen daven 1566.godine prema zahtevu tadašnjeg sultana Sulejmana Veličanstvenog. \r\nOsim toga, grad dodatno krase Neretva, stari duh čaršija i dućana…\r\nHajde da zajedno počnemo ovo putovanje, na kome ćemo hodati kroz istoriju, umetnost, mitove i legende dva večna balkanska grada – Sarajevo i Mostar.', b'1'),
(11, '2020-08-25 15:51:55', 1, 'Srbija', 'Zlatibor', 'Klub Satelit', 'Planina i vazdušna banja Zlatibor, sa najdužom turističkom tradicijom, nalazi se u Zapadnoj Srbiji, 230 km jugo-zapadno od Beograda. \r\nProsečna nadmorska visina je 1000 m. Za ljubitelje skijanja, na 7 km od centra Zlatibora se nalazi ski centar Tornik, najviši vrh ove prelepe planine.\r\nKlub Satelit jedan je od najtraženijih objekata za smeštaj na Zlatiboru kako kod domaćih tako i kod inostranih gostiju. \r\nDobitnik je brojnih priznanja za kvalitet rada.\r\nTri najvažnije karakteristike objekta jesu luksuzna opremljenost, nesvakidašnje prijatan ambijent i ljubazno osoblje. \r\nSvi apartmani i sobe su unikatno uređeni tako da ispunjavaju očekivanja i zahtevnijih hedonista.', b'1'),
(12, '2020-08-25 15:53:00', 1, 'Srbija', 'Zlatibor', 'Hotel Mona', 'Planina i vazdušna banja Zlatibor, sa najdužom turističkom tradicijom, nalazi se u Zapadnoj Srbiji, 230 km jugo-zapadno od Beograda. \r\nProsečna nadmorska visina je 1000 m. Za ljubitelje skijanja, na 7 km od centra Zlatibora se nalazi ski centar Tornik, najviši vrh ove prelepe planine.\r\nHotel Zlatibor Mona, nalazi se u samom centru Zlatibora, u neposrednoj blizini svih sadržaja ovog turističkog centra. Udaljen je 235 km jugozapadno od Beograda.', b'1'),
(13, '2020-08-25 15:54:47', 1, 'Srbija', 'Vrnjačka banja', 'Solaris Resort', 'Solaris Resort je nov i moderno opremljen objekat u turističkoj ponudi Vrnjačke Banje. \r\nSmešten je u mirnom delu Vrnjačke Banje, u neposrednoj blizini banjske promenade. \r\nIdealno je mesto za odmor, relaksaciju i opuštanje. \r\nAmbijent odiše komforom, prijatnošću i umetničkim šarmom koji goste neće ostaviti  ravnodušnim. \r\nObjekat nudi luksuzne apartmane i porodične sobe sa elegantnim enterijerom, restoran, aperitiv bar, salu za seminare, sopstveni parking prostor. U ponudi su takođe i sportsko-rekreativni sadržaji sa otvorenim i zatvorenim bazenom, fitnes centar, ekskluzivni Wellness centar  i Relax zona. ', b'1'),
(14, '2020-08-25 15:57:23', 2, 'Austrija', 'Nassfeld', 'Skiarena Nassfeld-Hermagor', 'Nassfeld jedno od najpoznatijih i najboljih austrijskih skijališta u ovoj regiji. Posebnost skijališta je da se jedan deo nalazi u Italiji, a drugi u Austriji. Mnogo sunca, blizina naše zemlje i odlične nove žičare.\r\n \r\nSkijalište u Nassfeldu, koje se punim imenom zove Skiarena Nassfeld-Hermagor, nalazi se u blizini gradića Hermagor u regiji Koruška. Ovo je jedno od najpoznatijih i najboljih austrijskih skijališta u ovoj regiji.\r\n \r\nSkijalište se otvara početkom decembra i sezona traje do sredine aprila. Nassfeld-Hermagor okružuju planine Gartnerkofel (2195 m), Rosskofel (2239 m) i Trogkofel (2280 m). Staze svih težina se rasprostiru od 610-2020 m, staze su dovoljno duge i pružaju vrlo ugodno skijanje za one koji vole brzinu i za one koji vole kratke zavoje. Staze su uglavnom crvene kategorije i vrlo su raznolike. Postoje dva snoubord parka, i jedan halfpajp za one koji žele da pokažu svoje umeće.\r\n\r\nSki centar Nassfeld je poznat i po gondoli \"Millennium Express\" dugoj preko 6 km, koja skijaše za nekoliko minuta penje sa 600 m na 2000 m visine. Početna stanica ove gondole se nalazi u podnožju skijališta u mestu Tröpollach, 7 km udaljenom od mesta Hermagor. Sonnleitn 1380 i Sonnenalpe 1500 se nalaze na samom skijalištu.\r\nUkratko, pruža mnoge pogodnosti za različite zimske aktivnosti i rekreaciju. Šetnja po snežnim stazama odvući će vas od vreve svakodnevnog života.\r\n \r\nNadmorska visina: 610-2020 m\r\nSkijalište Nassfeld/Hermagor: 110 km staza\r\nPutni pravac: Beograd - Zagreb - Ljubljana - tunel Karavanke - Arnoldstein - Hermagor - Tröpollach/Nassfeld\r\nUdaljenost: cca 690 km\r\n', b'1'),
(15, '2020-08-25 15:58:56', 2, 'Austrija', 'Kaprun', 'Kaprun Zimovanje & Skijanje', 'Kaprun je slikovito tradicionalno austrijsko mesto koje se odlikuje bogatom ponudom kvalitetnog smeštaja.\r\nSkijalište se nalazi ispod glečera Kicštajnhorn (Kitzsteinhorn) što omogućava dugu sezonu, čak i do 10 meseci. Kaprun ima 50 km staza i udaljen je 9 km od mesta Zell am See. Prednost oba mesta je blizina grada Salcburga i to što su međusobno dobro povezana. Ski bus i sistem žičara su uključeni u jedan ski pas koji pokriva oba mesta  i čine skijalište od ukupno 134 km ski staza (skijališta nisu povezana sistemom žičara, već ski busom)\r\nKaprun 2020\r\n\r\nNovom gondolom 3K-Konnection vozite se od centra Kapruna preko Maiskogela do Kitzsteinhorna. Još jedan od noviteta je ujedinjena mreža ski karata u SKI ALPIN: premium ski staze od ukupno 408 kilometara sa jednim ski pass-om: skijalište sa glečerom Kitzsteinhorn - Maiskogel u Kaprunu, skijalište Schmittenhohe u Zell am See-u, skijalište Skicircus Saalbach Hinterglemm Leogang Fieberbrunn. \r\n\r\nNadmorska visina: 911-3029 m\r\nSkijalište Kaprun/Zell am See: 134 km staza (obuhvata Kaprun, Zell am See i glečer Kitzsteinhorn)\r\nPutni pravac: Beograd - Zagreb - Ljubljana - tunel Karavanke - Villach - Spittal - Malnitz (autovoz) - Bad Gastein - Bruck an der Glocknerstrasse - Kaprun\r\nUdaljenost: cca 770 km ', b'1'),
(16, '2020-08-25 16:00:16', 2, 'Austrija', 'Cel am Ze', 'Skijalište Zell am See', 'Provedite i ovu zimu u Cel am Ze (Zell am See) jednom od najlepših turističkih centara Austrije. Jedno od austrijskih omiljenih skijališta, sa zadivljujućim prizorima planina u okruženju i jezerom u dolini.\r\nSrednjovekovno gradsko jezgro odlikuje pešačka zona sa velikim brojem restorana, vinskih podruma, barova, ekskluzivnih prodavnica. Grad pored toga ima i klizalište, bazene, teniske terene, a možete da iskoristite večeri i za ugodne šetnje uz jezero.\r\nSkijalište Zell am See ima 80 km staza koje su idealne za rekreativce i koje odlikuje odlična međusobna povezanost sistemom žičara. Sa susednim ski centrom Kaprun je povezan ski busom i uz kupovinu određenog ski pasa zajedno predstavlja centar ski regiona \"Europa Sportregion\" sa 134 km uređenih staza.\r\nSmeštaj u ski centru Zell am See.\r\n\r\nZell am See se preporučuje za porodični odmor i skijaše rekreativce. Planirajte vaš odmor u ski centru Zell am See i obezbedite sebi akcijske popuste na smeštaj.\r\n\r\n\r\nNadmorska visina: 750-2000 m\r\nSkijalište Zell am See/Kaprun: 134 km staza (obuhvata Zell am See, Kaprun i glečer Kitzsteinhorn)\r\nPutni pravac: Beograd - Zagreb - Ljubljana - tunel Karavanke - Villach - Spittal - Malnitz (autovoz) - Bad Gastein - Bruck an der Glocknerstrasse - Zell am See\r\nUdaljenost: cca 770 km', b'1'),
(17, '2020-08-25 16:01:49', 2, 'Austrija', 'Bad Klajnkirhajm', 'Skijalište Bad Klajnkirhajm', 'Bad Klajnkirhajm je među 15 najposećenijih skijališta u Austriji. Svi hoteli i apartmani su smešteni u neposrednoj blizini ski liftova i centra. Uživajte u kupanju na otvorenom bazenu u dve termalne banje dok pada sneg.\r\nSkijalište Bad Klajnkirhajm / St. Osvald\r\n\r\nProstire se na obroncima planina Nockberge, u okrugu Spittal u regiji Koruška. Skijašima su na raspolaganju preko 103 km uređenih staza među kojima je i najduža staza u Koruškoj, Klammer (4.2 km). Bad Klajnkirhajm je popularan i atraktivan turistički centar, poznat po takmičenjima za FIS svetski kup i posebno pogodan za miran porodični odmor.\r\nBad Klajnkirhajm Austrija - Wellness & Spa\r\n\r\nTakođe, ovde možete uživati i u wellness tretmanima i to u termalnim banjama \"St. Kathrein\" i \"Romerbad\" (u sklopu kupališta Romerbad usred samog skijališta nalazi se otvoreni bazen sa termalnom vodom).\r\nUkoliko od vašeg zimskog odmora tražite nešto više od samog skijanja, Bad Klajnkirhajm je idealno odredište i za vas.       \r\n\r\nNadmorska visina: 1025-2055 m\r\nSkijalište: Bad Kleinkirchheim / St. Oswald: 103 km staza\r\nPutni pravac: Beograd - Zagreb - Ljubljana - tunel Karavanke - Villach - Treffen - Radenthein - Bad Kleinkirchheim\r\nUdaljenost: cca 640 km', b'1'),
(18, '2020-08-25 16:03:37', 2, 'Srbija', 'Stara Planina', 'Stara Planina', 'Raskošna lepota Stare Planine, osvaja i pleni, ne samo u toku zime, već u toku cele godine. Idealno je mesto za ljubitelje netaknute prirode, koji žele da otkriju neizmerna bogatstva ove planinske lepotice na istoku Srbije.\r\n\r\nStara Planina, spada u grupu venačnih planina. Glavni venac dugačak je oko 550 kilometara, i proteže se od Zaječara do Crnog mora. Deo ovog venca označava i prirodnu granicu izmedju Srbije i Bugarske. Stara Planina nosi i drugo ime - Balkan, tako da je Balkansko poluostrvo dobilo naziv po ovoj planini.\r\n\r\nJedan od najlepših krajolika Stare Planine je prirodni rezervat Babin zub, koji se nalazi na nadmorskoj visini od 1.758 metara. Strme padine, oštri usponi, čudesni vodopadi, polja šumskog voća, bogate šume, pružaju mogućnost za razvoj različitih oblika turizma. Biljni i životinjiski svet na Staroj Planini je karakterističan, pa se u podnožju Babinog zuba nalazi stanište Subalpske bukve, a u potocima i rekama ima potočne pastrmke.\r\n\r\nStara Planina obiluje lekovitim biljem i pečurkama, a stanište je i mnogim vrstama retkih biljaka. U selima podno Stare Planine dočekaće vas ljubazni domaćini, uvek spremni da putnike dočekaju izvornom trpezom, na kojoj će se obavezno naći \"belmuž\", specijalitet ovog kraja, napravljen od sira i kukuruznog brašna. \r\n', b'0'),
(19, '2020-08-25 16:04:19', 2, 'Srbija', 'Zlatibor', 'Ski centar Tornik', 'Planina Zlatibor ili vazdušna banja, sa stogodišnjom turističkom tradicijom, jedna od od najposećenijih planina Srbije. Prosečna nadmorska visina Zlatibora je 1000 m, a najviši vrhovi su: Tornik sa 1496m nadmorske visine i Čigota 1422m nadmorske visine.\r\nZlatibor • Skijanje 2020\r\n\r\nProsečna nadmorska visina planine Zlatibor je 1000 m, a najviši vrhovi su: Tornik sa 1496 m nadmorske visine i Čigota 1422 m nadmorske visine.\r\n\r\nSki centar Tornik opremljen je sa šestosednom žičarom, kapaciteta 3.000 skijaša na sat, dva ski lifta, ukupnog kapaciteta 2.400 skijaša na sat. Četri staze, Čigota, Tornik, Ribnica i Zmajevac pokrivene su sistemom za veštačko osnežavanje, tako da ovaj ski centar više ne zavisi od vremenskih uslova.\r\n\r\nU ski centru Tornik nalazi se i posebna vrsta instalacije tzv. \"tjubing\", koja, kao i sama žičara, može da se koristi i u letnjem i u zimskom periodu godine i pruža neizmerno zadovoljstvo i uzbuđenje svima koji odluče da je isprobaju. Ski centar je opremljen i dečijim igralištem i karuselom.\r\nZlatibor ima prosečno 95 snežnih dana godišnje, a snežni pokrivač dostiže debljinu do 60 cm.\r\n\r\nSusretanje mediteranske i kontinentalne klime su stvorili uslove da se \"ruža vetrova\" rascvetava, upravo, nad Zlatiborom. Te struje omogućavaju uspešno lečenje i oporavak od mnogih plućnih, srčanih bolesti i posebno bolesti štitaste žlezde i malokrvnosti.\r\n\r\nZlatibor je omiljena planina vrhunskh sportista i mesto za njihove najkvalitetnije pripreme. Pored prirodnih terena, za ove namene su izgrađeni najsavremeniji sportski objekti: velika sportska hala, fudbalski tereni, igrališta za tenis, zatvoren i otvoren bazen.\r\n\r\nUrbani centar Zlatibora, predstavlja pravo mesto za uzbudljiv noćni život, a prijatne trenutke u toku dana možete provesti u nekoj od bašti mnogobrojnih kafea, restorana, poslastičarnica. \r\n', b'1'),
(20, '2020-08-25 16:05:33', 2, 'Srbija', 'Kopaonik', 'Hotel Jat Apartmani', 'Kopaonik je najveći ski centar u Srbiji, bogat je sa odlično pripremljenim stazama koja skijašima pruža neizmerno uživanje! Ski centar Kopaonik poseduje ski staze za sve kategorije skijaša - od početnika do profesionalaca.\r\nSkijanje na Kopaoniku\r\n\r\nSki centar Kopaonik prostire se na oko 62km staza i ski puteva uređenih za alpsko i nordijsko skijanje. Za ljubitelje noćnog skijanja u samom centru skijališta osvetljena je staza \"Malo Jezero\".\r\nZa najmlađe skijaše i sve druge početnike, koji prave svoje prve skijaške korake, postoji obezbeđen prostor ski vrtića sa pokretnom trakom i karusel koja olakšava da se ovlada osnovnim skijaškim veštinama.\r\n\r\nSki centar Kopaonik poseduje i sistem za veštačko osnežavanje pokriveno je 97% skijališta. Ovim sistemom pokrivene su staze: Karaman A i B, Pančićev vrh, Duboka 1 i 2, Sunčana dolina, Malo jezero, Krst, Krčmar, Kneževske bare, Mali Karaman, Marine vode, Karaman i pripadajući ski putevi, kao i skijalište na Gobelji.\r\n\r\nZahvaljujući sistemima za veštačko osnežavanje sezona skijanja na Kopaoniku traje duže, a skijaši, kako početnici tako i oni malo zahtevniji mogu biti sigurni da će tokom zimske sezone moći da uživaju u odličnno pripremljenim stazama.\r\n\r\nSki staze na Kopaoniku su veoma dobro povezane sistemom žičara i ski liftova, kapaciteta od preko 34.000 skijaša na sat.\r\n\r\nŽičara četvorosed \"Pančićev vrh\" tokom čitave zimske sezone ali i u toku leta, osim skijaša i snoubordera, prevozi i posetioce centra koji se ne bave zimskim sportovima, već panoramski razgledaju centar i bave se brojnim drugim aktivnostima koje ski centar Kopaonik omogućava.\r\nSmeštaj na Kopaoniku\r\n\r\nU našoj ponudi za smeštaj na Kopaoniku tokom zimske sezone, imamo apartmane i hotele. Hotelski smeštaj koji preporučujemo: Hotel Nebeska stolica 1 kao i nezaobilazni Hotel Nebeska stolica 2.\r\n', b'1'),
(21, '2020-08-25 16:29:51', 3, 'Hrvatska', 'Dubrovnik', 'Hotel Adriatic', 'DUBROVNIK, na samom jugu Hrvatske i Dalmacije, grad je jedinstvene, svetski poznate spomeničke baštine i lepote, te jedan od najatraktivnijih i najpoznatijih gradova Sredozemlja. „Bijeli grad“ je u kamenu opasan gotovo 2 km dugačkim dubrovačkim zidinama, kulama, tornjevima, podeljen Stradunom, a štiti ga Sv.Vlaho, glavni zaštitnik Dubrovnika. Kao turistički centar, Dubrovnik je grad prelepih šljunkovitih plaža, ali i divljih južno-dalmatinskih grebena i grad koji Vas sigurno neće ostaviti ravnodušnim.\r\nHotel Adriatic nalazi se na poluostrvu Lapad oko 4 km od starog grada. Od plaže je udaljen 50m.\r\n\r\nHotelska ponuda:\r\nrecepcija, pored restorana sa klimom, gostima su na raspolaganju dva aperitiv bara, dva TV salona, menjačnica, sef na recepciji, pa zatim teniski tereni, stoni tenis i niz rekvizita za sportove na vodi, internet, parking. Plaža je delom betonska, ima šljunkovitog dela plaže.\r\n', b'1'),
(22, '2020-08-25 16:30:55', 3, 'Hrvatska', 'Dubrovnik', 'Hotel komodor', 'DUBROVNIK, na samom jugu Hrvatske i Dalmacije, grad je jedinstvene, svetski poznate spomeničke baštine i lepote, te jedan od najatraktivnijih i najpoznatijih gradova Sredozemlja. „Bijeli grad“ je u kamenu opasan gotovo 2 km dugačkim dubrovačkim zidinama, kulama, tornjevima, podeljen Stradunom, a štiti ga Sv.Vlaho, glavni zaštitnik Dubrovnika. Kao turistički centar, Dubrovnik je grad prelepih šljunkovitih plaža, ali i divljih južno-dalmatinskih grebena i grad koji Vas sigurno neće ostaviti ravnodušnim.\r\n\r\nHotel se nalazi 50 m od betonske i šljunkovite plaže,u zalivu Lapad, na oko 4 km od centra mesta Dubrovnik i na oko 25km aerodrom Dubrovnik. Hotel poseduje 63 sobe.\r\n\r\nHotelska ponuda:\r\nrecepcija, restoran, bar, bazen, sef na recepciji hotela, Wi-Fi internet, room servis (uz doplatu), usluge pranja veša (uz doplatu), parking.', b'1'),
(23, '2020-08-25 16:31:50', 3, 'Hrvatska', 'Istra', 'Hotel Sol Aurora Plava Laguna', 'Istarsko poluostrvo, smešteno na krajnjem zapadu Hrvatske, oduek je blio poseban kraj.\r\nTakva je i turisticka regija Istre koju cini samo jedna, Istarska, županija, ali po posecenosti uverljivo prva turisticka županija Hrvatske.\r\nPosebnost Istre proizlazi iz njenog  položaja. Istarske pešcane plaže idealna su mesta za malu decu i neplivace zbog blagog ulaza u more, koje je kristalno cisto i pruža ugodan osecaj. Istra obiluje pešcanim plažama i slikovitim uvalama od kojih zastaje dah!\r\n\r\nHotel se nalazi na oko 200m od plaže, na oko 2.5 km od centra mesta Umag, oko70 km od aerodroma Pula i oko 90km od aerodroma Rijeka.\r\n \r\n\r\nHotelska ponuda:\r\nRecepcija, bar, restoran, a la carte restoran na plaži (od juna - sredine septembra), beach bar (od juna - sredine septembra), bazen, bazen za decu, Wi-Fi internet, parking (uz doplatu).', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `ponuda_slika`
--

DROP TABLE IF EXISTS `ponuda_slika`;
CREATE TABLE IF NOT EXISTS `ponuda_slika` (
  `ponuda_slika_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ponuda_id` int(10) UNSIGNED NOT NULL,
  `naziv` varchar(128) NOT NULL,
  `kratak_opis` varchar(64) NOT NULL DEFAULT 'bez opisa',
  `aktivna` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ponuda_slika_id`),
  KEY `fk_ponuda_slika_ponuda_id_idx` (`ponuda_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ponuda_slika`
--

INSERT INTO `ponuda_slika` (`ponuda_slika_id`, `created_at`, `ponuda_id`, `naziv`, `kratak_opis`, `aktivna`) VALUES
(1, '2020-08-25 14:19:01', 5, 'golubac_51.png', 'Golubačka tvrđava danju', b'1'),
(2, '2020-08-25 14:19:01', 5, 'golubac_52.png', 'Golubačka tvrđava noću', b'1'),
(3, '2020-08-25 14:19:01', 5, 'golubac_53.png', 'Hol Hotela Dunav', b'1'),
(4, '2020-08-25 14:19:01', 5, 'golubac_54.png', 'Hotel Dunav u Golupcu', b'1'),
(5, '2020-08-25 14:19:01', 5, 'golubac_55.png', 'Golubačka tvrđava', b'1'),
(6, '2020-08-25 15:28:46', 9, 'tumani_91.png', 'Manastir Tumani', b'1'),
(7, '2020-08-25 15:28:46', 9, 'tumani_92.png', 'Manastir Tumani2', b'1'),
(8, '2020-08-25 15:28:46', 9, 'tumani_93.png', 'Manastir Tumani3', b'1'),
(9, '2020-08-25 15:28:46', 9, 'tumani_94.png', 'Manastir Tumani4', b'1'),
(10, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_101.png', 'bascarsija sarajevo izlet vikend', b'1'),
(11, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_102.png', 'kazandziluk ulica sarajevo izlet', b'1'),
(12, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_103.png', 'sarajevo dzamija izlet bosna', b'1'),
(13, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_104.png', 'sarajevo mostar izleti bosna', b'1'),
(14, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_105.png', 'sarajevo mostar tura leto2020', b'1'),
(15, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_106.png', 'sarajevo mostar vikend izleti bosna', b'1'),
(16, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_107.png', 'sarajevo mostar vikend izleti bosna hercegovina', b'1'),
(17, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_108.png', 'sarajevo narodno pozoriste izlet', b'1'),
(18, '2020-08-25 15:47:33', 10, 'sarajevo_mostar_109.png', 'vrelo bosne sarajevo izlet', b'1'),
(19, '2020-08-25 16:35:48', 23, 'istra_231.png', 'Istra Hotel', b'1'),
(20, '2020-08-25 16:35:48', 23, 'istra_232.png', 'bez opisa', b'1'),
(21, '2020-08-25 16:35:48', 23, 'istra_233.png', 'Bazen', b'1'),
(22, '2020-08-25 16:44:58', 21, 'hote_adriatic_211.png', 'bez opisa', b'1'),
(23, '2020-08-25 16:44:58', 21, 'hote_adriatic_212.png', 'bez opisa', b'1'),
(24, '2020-08-25 16:50:43', 22, 'hotel_komodor_221.png', 'bez opisa', b'1'),
(25, '2020-08-25 16:50:43', 22, 'hotel_komodor_222.png', 'bez opisa', b'1'),
(26, '2020-08-25 16:50:43', 22, 'hotel_komodor_223.png', 'bez opisa', b'1'),
(27, '2020-08-25 16:56:26', 1, 'bukovacka_banja_11.png', 'hotel izvor', b'1'),
(28, '2020-08-25 17:37:48', 2, 'ada_bojana_21.png', 'panorama', b'1'),
(29, '2020-08-25 17:37:48', 2, 'ada_bojana_22.png', 'panorama 2', b'1'),
(30, '2020-08-25 17:37:49', 2, 'ada_bojana_23.png', 'plaza', b'1'),
(31, '2020-08-25 17:37:49', 2, 'ada_bojana_24.png', 'plaza 2', b'1'),
(32, '2020-08-25 17:37:49', 7, 'karibi_71.png', 'bez opisa', b'1'),
(33, '2020-08-25 17:37:49', 7, 'karibi_72.png', 'bez opisa', b'1'),
(34, '2020-08-25 17:37:49', 8, 'volga_81.png', 'mapa puta', b'1'),
(35, '2020-08-25 17:37:49', 8, 'volga_82.png', 'grad', b'1'),
(36, '2020-08-25 17:37:49', 8, 'volga_83.png', 'grad', b'1'),
(37, '2020-08-25 17:37:49', 11, 'zlatibor_111.png', 'hotel', b'1'),
(38, '2020-08-25 17:37:49', 12, 'zlatibor_121.png', 'hotel', b'1'),
(39, '2020-08-25 17:37:49', 13, 'vrnjacka_banja_131.png', 'solaris resort', b'1'),
(40, '2020-08-25 17:37:49', 13, 'vrnjacka_banja_132.png', 'solaris resort', b'1'),
(41, '2020-08-25 17:37:49', 13, 'vrnjacka_banja_133.png', 'solaris resort', b'1'),
(42, '2020-08-25 17:37:49', 13, 'vrnjacka_banja_134.png', 'solaris resort', b'1'),
(43, '2020-08-25 17:37:49', 14, 'nassfeld_141.png', 'ski centar', b'1'),
(44, '2020-08-25 17:37:49', 14, 'nassfeld_142.png', 'ski centar', b'1'),
(45, '2020-08-25 17:37:49', 14, 'nassfeld_143.png', 'ski centar', b'1'),
(46, '2020-08-25 17:37:49', 14, 'nassfeld_144.png', 'ski centar', b'1'),
(47, '2020-08-25 17:37:49', 15, 'kaprun_151.png', 'bez opisa', b'1'),
(48, '2020-08-25 17:37:50', 15, 'kaprun_152.png', 'bez opisa', b'1'),
(49, '2020-08-25 17:37:50', 16, 'cel_am_ze_161.png', 'cel_am_ze', b'1'),
(50, '2020-08-25 17:37:50', 16, 'cel_am_ze_162.png', 'cel_am_ze', b'1'),
(51, '2020-08-25 17:37:50', 16, 'cel_am_ze_163.png', 'cel_am_ze', b'1'),
(52, '2020-08-25 17:37:50', 17, 'bad_klajnkirhajm_171.png', 'Ski Centar Bad Klajnkirhajm', b'1'),
(53, '2020-08-25 17:37:50', 20, 'kopaonik_201.png', 'hotel', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `ponuda_vrsta`
--

DROP TABLE IF EXISTS `ponuda_vrsta`;
CREATE TABLE IF NOT EXISTS `ponuda_vrsta` (
  `ponuda_vrsta_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`ponuda_vrsta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ponuda_vrsta`
--

INSERT INTO `ponuda_vrsta` (`ponuda_vrsta_id`, `created_at`, `naziv`) VALUES
(1, '2020-08-05 12:02:42', 'Banja & Wellness'),
(2, '2020-08-05 12:02:42', 'Zimovanje'),
(3, '2020-08-05 12:02:42', 'Letovanje'),
(4, '2020-08-05 12:58:03', 'Krstarenje'),
(5, '2020-08-25 13:51:00', 'Izlet');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `aranzman`
--
ALTER TABLE `aranzman`
  ADD CONSTRAINT `fk_aranzman_korisnik_id` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_aranzman_ponuda_id` FOREIGN KEY (`ponuda_id`) REFERENCES `ponuda` (`ponuda_id`) ON UPDATE CASCADE;

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `fk_korisnik_adresa_id` FOREIGN KEY (`adresa_id`) REFERENCES `adresa` (`adresa_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_korisnik_kontakt_id` FOREIGN KEY (`kontakt_id`) REFERENCES `kontakt` (`kontakt_id`) ON UPDATE CASCADE;

--
-- Constraints for table `ponuda`
--
ALTER TABLE `ponuda`
  ADD CONSTRAINT `fk_ponuda_ponuda_vrsta_id` FOREIGN KEY (`ponuda_vrsta_id`) REFERENCES `ponuda_vrsta` (`ponuda_vrsta_id`) ON UPDATE CASCADE;

--
-- Constraints for table `ponuda_slika`
--
ALTER TABLE `ponuda_slika`
  ADD CONSTRAINT `fk_ponuda_slika_ponuda_id` FOREIGN KEY (`ponuda_id`) REFERENCES `ponuda` (`ponuda_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
