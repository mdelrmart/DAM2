-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 13, 2025 at 10:34 AM
-- Server version: 8.0.40-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinica`
--

-- --------------------------------------------------------

--
-- Table structure for table `cans`
--

CREATE TABLE `cans` (
  `chip` varchar(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `codRaza` int NOT NULL,
  `dniPropietario` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cans`
--

INSERT INTO `cans` (`chip`, `nome`, `codRaza`, `dniPropietario`) VALUES
('0000', 'EraMellorMorrer', 7, '76814588T'),
('1001', 'Rita', 4, '11111111A'),
('1111', 'Becker', 4, '111144'),
('11111', 'Este', 5, '1243'),
('111222', 'Bigotes', 4, '48576298D'),
('112233', 'Carcajadas', 4, '19283746Z'),
('123321', 'Dentón', 3, '58294736L'),
('12333', 'Marin', 5, '123'),
('1245', 'Coco', 1, '76401369S'),
('1314', 'Bella', 5, '33333333C'),
('1345', 'Gina', 4, '111144'),
('1945', 'Merkel', 1, '22222222B'),
('2233', 'Dau', 4, '11111111A'),
('223344', 'Bellota', 1, '24713685V'),
('233', 'Balena', 5, '22222222B'),
('2345', 'Leia', 5, '123'),
('2356', 'Sasha', 3, '32769757R'),
('2789', 'Zeus', 1, '22222222B'),
('333444', 'Salsera', 5, '67283918F'),
('3456', 'Thor', 5, '1243'),
('3678', 'Rock', 5, '33333333C'),
('369963', 'Peludiña', 5, '38572169C'),
('3705', 'Tuna', 5, '33333333C'),
('4356', 'Lucky', 5, '1243'),
('445566', 'Melodía', 5, '62378912M'),
('4510', 'Simba', 5, '1243'),
('456654', 'Visa', 5, '75983421E'),
('4577', 'Caballero', 3, '32769757R'),
('5123', 'Bruno', 4, '111144'),
('555666', 'Viajero', 1, '98123456K'),
('556677', 'Monteiro', 4, '75849301H'),
('5612', 'Max', 3, '32769757R'),
('6421', 'Nala', 1, '76401369S'),
('6789', 'Toby', 1, '22222222B'),
('6932', 'Bobby', 1, '22222222B'),
('741147', 'Calcetín', 4, '57289341D'),
('7412', 'Spike', 5, '33333333C'),
('741852', 'Licor', 5, '63829741R'),
('7458', 'Rex', 4, '11111111A'),
('7632', 'Jax', 5, '1243'),
('7641', 'Lago', 1, '76401369S'),
('777888', 'Tijeritas', 3, '54782134G'),
('778899', 'Pimienta', 4, '82937465P'),
('789987', 'Riso', 1, '84937261Y'),
('8452', 'Finn', 5, '33333333C'),
('852258', 'Cafeteira', 5, '62318947S'),
('8524', 'Milo', 4, '11111111A'),
('8764', 'Kira', 1, '76401369S'),
('8765', 'Luna', 4, '111144'),
('8897', 'Tara', 5, '1243'),
('889900', 'Chispi', 5, '63849271N'),
('9023', 'Blue', 3, '32769757R'),
('9072', 'Rocky', 5, '1243'),
('963369', 'Tornillo', 3, '74928153T'),
('9786', 'Duke', 5, '33333333C'),
('9823', 'Chloe', 4, '111144'),
('9876', 'Osa', 5, '33333333C'),
('998877', 'Miga', 5, '93847162J'),
('999000', 'Fabada', 5, '38912457L');

-- --------------------------------------------------------

--
-- Table structure for table `citasperrucaria`
--

CREATE TABLE `citasperrucaria` (
  `codCita` int NOT NULL,
  `chip` varchar(20) NOT NULL,
  `data` date NOT NULL,
  `hora` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `citasperrucaria`
--

INSERT INTO `citasperrucaria` (`codCita`, `chip`, `data`, `hora`) VALUES
(14, '233', '2022-11-15', 10),
(27, '233', '2024-11-08', 12),
(28, '777888', '2024-11-08', 17);

-- --------------------------------------------------------

--
-- Table structure for table `operacions`
--

CREATE TABLE `operacions` (
  `codOperacion` int NOT NULL,
  `chip` varchar(20) NOT NULL,
  `descripcion` text NOT NULL,
  `data` date NOT NULL,
  `anestesia` tinyint NOT NULL,
  `raios` tinyint NOT NULL,
  `sangue` tinyint NOT NULL,
  `scaner` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `operacions`
--

INSERT INTO `operacions` (`codOperacion`, `chip`, `descripcion`, `data`, `anestesia`, `raios`, `sangue`, `scaner`) VALUES
(4, '3705', 'Ovariohisterectomia', '2020-09-01', 1, 0, 0, 1),
(5, '2233', 'Biopsia diagnostico dermatoloxico', '2018-08-07', 1, 0, 0, 0),
(7, '4577', 'Entropion', '2019-05-03', 0, 0, 0, 0),
(11, '2233', 'Radiografía', '2018-04-21', 0, 0, 1, 0),
(12, '8765', 'Castración', '2021-07-15', 1, 0, 0, 0),
(13, '3456', 'Limpieza dental', '2020-06-23', 1, 0, 0, 0),
(14, '2789', 'Sutura de ferida', '2022-03-18', 0, 0, 0, 0),
(15, '9876', 'Desparasitacion interna', '2021-11-29', 0, 0, 0, 0),
(16, '5612', 'Control de displasia de cadeira', '2023-02-10', 0, 1, 0, 1),
(17, '6421', 'Cirugía de rotura de ligamentos', '2023-05-04', 1, 1, 1, 0),
(18, '7458', 'Extracción de corpo estraño', '2019-12-22', 1, 0, 1, 0),
(19, '9072', 'Vacunación anual', '2021-09-09', 0, 0, 0, 0),
(20, '1314', 'Cirugía de hernia umbilical', '2020-03-15', 1, 0, 0, 0),
(21, '5123', 'Limpieza e curetaje dental', '2022-07-27', 1, 0, 0, 0),
(22, '4510', 'Extracción de tumor benigno', '2020-01-16', 1, 0, 1, 0),
(23, '2345', 'Cirugía de luxación de patela', '2019-11-10', 1, 0, 1, 0),
(24, '6789', 'Reparación de fractura', '2021-06-30', 1, 1, 1, 1),
(25, '8524', 'Endoscopia', '2023-04-21', 1, 0, 0, 1),
(26, '9786', 'Extracción de espiña', '2022-01-08', 0, 0, 0, 0),
(27, '2356', 'Limpieza de oídos', '2020-11-17', 0, 0, 0, 0),
(28, '1245', 'TAC abdominal', '2022-09-14', 0, 0, 0, 1),
(29, '3678', 'Tratamento de otite', '2023-05-19', 0, 0, 0, 0),
(30, '9823', 'Electrocardiograma', '2021-10-04', 0, 0, 1, 0),
(31, '4356', 'Ciruxía de amputación de rabo', '2013-02-14', 1, 0, 1, 0),
(32, '6932', 'Corrección de fractura de mandíbula', '2020-12-10', 1, 1, 1, 0),
(33, '8897', 'Lavado gástrico', '2022-08-05', 1, 0, 0, 0),
(34, '1001', 'Extracción de pólipo', '2021-03-07', 1, 0, 1, 0),
(35, '7412', 'Ciruxía ocular', '2023-01-18', 1, 0, 0, 0),
(36, '9023', 'Desinfección de ferida profunda', '2013-04-23', 0, 0, 0, 0),
(37, '8764', 'Biopsia hepática', '2023-06-12', 1, 0, 1, 1),
(38, '1345', 'Corrección de displasia', '2020-09-28', 1, 1, 1, 0),
(39, '7632', 'Cirugía de torsión gástrica', '2021-12-11', 1, 0, 1, 0),
(40, '8452', 'Tratamento de epilepsia', '2024-11-02', 0, 0, 0, 0),
(41, '8765', 'Extracción de zapatilla robada', '2023-08-15', 0, 0, 0, 0),
(42, '3456', 'Reconstrución de xoguete de goma', '2022-12-11', 0, 0, 0, 0),
(43, '2789', 'Eliminación de pelo entre os dentes', '2021-04-04', 0, 0, 0, 0),
(44, '9876', 'Ciruxía de cola demasiado alegre', '2023-07-20', 1, 0, 0, 0),
(45, '5612', 'Desenredo de correa imposible', '2022-03-13', 0, 0, 0, 0),
(46, '6421', 'Exceso de mimos: Tratamento de abrazo', '2023-02-14', 0, 0, 0, 0),
(47, '7458', 'Extracción de area post-praia', '2022-07-29', 0, 0, 0, 0),
(48, '9072', 'Desinflado de ego tras ganar concurso de beleza', '2023-05-16', 0, 0, 0, 0),
(49, '1314', 'Redución de ladrido por orde veciñal', '2021-06-28', 0, 0, 0, 0),
(50, '5123', 'Desparasitación tras morderse a si mesmo', '2022-10-09', 0, 0, 0, 0),
(51, '4510', 'Diagnóstico: Soños de persecución de gatos', '2023-03-11', 0, 0, 0, 0),
(52, '2345', 'Recuperación de autoestima tras pelexa co aspirador', '2021-02-17', 0, 0, 0, 0),
(53, '6789', 'Extracción de galleta oculta baixo o sofá', '2020-11-05', 0, 0, 0, 0),
(54, '8524', 'Reconstrución de orella despois de sacudirse en exceso', '2022-06-21', 1, 0, 0, 0),
(55, '9786', 'Tratamento de alerxia a humanos aburridos', '2023-09-03', 0, 0, 0, 0),
(56, '2356', 'Eliminación de restos de carne roubada da grella', '2020-05-22', 0, 0, 0, 0),
(57, '1245', 'Estiramento de espiña dorsal tras sesta intensa', '2021-08-29', 0, 0, 0, 0),
(58, '3678', 'Control de ansiedade por falta de galletas', '2023-07-13', 0, 0, 0, 0),
(59, '9823', 'Limpeza de barro tras paseo por charco', '2022-01-27', 0, 0, 0, 0),
(60, '4356', 'Investigación de misteriosa bola de pelo en cama', '2021-12-02', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `propietarios`
--

CREATE TABLE `propietarios` (
  `dni` varchar(15) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `ap1` varchar(50) NOT NULL,
  `ap2` varchar(50) NOT NULL,
  `tlf` varchar(50) NOT NULL,
  `eMail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `propietarios`
--

INSERT INTO `propietarios` (`dni`, `nome`, `ap1`, `ap2`, `tlf`, `eMail`) VALUES
('11111111A', 'Pedra', 'Perez', 'Chousa', '981111111', 'pedro@gmail.com'),
('111144', 'Kevin', 'Outeiro', 'Cons', '986787744', 'kevincinho@hotmail.com'),
('123', 'Martin', 'Cabaleira', 'Davila', '986858585', 'cabaleiroala@live.com'),
('123424', 'Ines', 'Domínguez', 'Varelha', '611444777', 'inesinesines@gmail.com'),
('1243', 'Manolo', 'Fontán', 'Davila', '644888556', 'fonti@gmail.com'),
('19283746Z', 'Fina', 'Risitas', 'De Los Montes', '612987654', 'fina.risitas@yahoo.es'),
('213', 'Ramón', 'Barcaza', 'Bernal', '688777888', 'barc@bernal.com'),
('22222222B', 'Ana', 'Miranda', 'Lodeira', '98222222', 'ana@hotmail.com'),
('24713685V', 'Tina', 'Castaña', 'Pilonga', '666234789', 'tina.pilonga@bosque.com'),
('32769757R', 'Luis', 'Zapata', 'Pousa', '649855788', 'luis@edu.xunta.es'),
('33333333C', 'Maria', 'Pino', 'Lopez', '983333333', 'maria@yahoo.es'),
('38572169C', 'Rita', 'Chirimoya', 'Peluda', '611998887', 'rita.peluda@frutas.com'),
('38912457L', 'Paco', 'Cachopo', 'De Asturias', '688234567', 'paco.cachopo@asturias.com'),
('48576298D', 'Pepe', 'Patillas', 'Locas', '654987123', 'pepe.patillas@barberia.com'),
('53614589E', 'Miguel', 'del Rio', 'Martinez', '644182244', 'mdelrmart@gmail.com'),
('54782134G', 'Antón', 'Cortapel', 'De León', '699123456', 'antoncorte@gmail.com'),
('57289341D', 'Héctor', 'Calcetín', 'Perdido', '654789321', 'hector.calcetin@gmail.com'),
('58294736L', 'Toni', 'Cepillo', 'De Dientes', '622345678', 'toni.cepillo@dental.com'),
('62318947S', 'Marta', 'Cucharilla', 'Del Café', '689123456', 'marta_cucharilla@desayunos.com'),
('62378912M', 'Jacinto', 'Melodías', 'Armónicas', '699876543', 'j.melodias@hotmail.com'),
('63829741R', 'Roi', 'Chupito', 'De Licor', '612345987', 'roi.chupito@taberna.com'),
('63849271N', 'Pepiño', 'Chispa', 'Electrón', '698123765', 'pepiño@chispazos.com'),
('67283918F', 'María', 'Bailonga', 'Del Pino', '612345678', 'maria_bailonga@disco.com'),
('74928153T', 'Pili', 'Chincheta', 'Oxidada', '622789654', 'pili.oxidada@bricolaje.com'),
('75849301H', 'Tucho', 'Furtivo', 'Del Monte', '688912345', 'tucho.furtivo@gmail.com'),
('75983421E', 'Lucía', 'Tarjeta', 'De Crédito', '654321987', 'lucia.tarjeta@bancos.es'),
('76366226Q', 'Maria Jesus', 'Roca', 'Cortes', '674741456', 'mariaj@hotmail.es'),
('76401369S', 'Irene', 'Fraga', 'Hernando', '986858789', 'ifh@fraga.com'),
('76814588T', 'Fernando', 'Simón', 'Soria', '649588741', 'fernando.simon@covidalerta.es'),
('82937465P', 'Sara', 'Salero', 'Pimentón', '689123987', 'sara_salero@especias.com'),
('84937261Y', 'Antonio', 'Bigote', 'Risol', '688789123', 'gustavo.bigote@barber.com'),
('93847162J', 'Arturo', 'Martinez', 'Del Pan', '655123789', 'a.rebanada@panaderia.es'),
('98123456K', 'Lolo', 'Trotamundos', 'De La Sierra', '678912345', 'loloaventuras@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `razas`
--

CREATE TABLE `razas` (
  `codRaza` int NOT NULL,
  `descripcion` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `razas`
--

INSERT INTO `razas` (`codRaza`, `descripcion`) VALUES
(1, 'Pastor aleman'),
(2, 'Schnauzer'),
(3, 'Yorkshire'),
(4, 'Foxterrier'),
(5, 'Cocker'),
(6, 'Labrador Retriever'),
(7, 'Golden Retriever'),
(8, 'Bulldog Francés'),
(9, 'Beagle'),
(10, 'Dálmata'),
(11, 'Shih Tzu'),
(12, 'Poodle'),
(13, 'Boxer'),
(14, 'Rottweiler'),
(15, 'Doberman'),
(16, 'Pomerania'),
(17, 'Bichón Maltés'),
(18, 'Husky Siberiano'),
(19, 'Chihuahua'),
(20, 'Gran Danés');

-- --------------------------------------------------------

--
-- Table structure for table `tcabsfacturas`
--

CREATE TABLE `tcabsfacturas` (
  `id_factura` int NOT NULL,
  `numfactura` varchar(15) NOT NULL,
  `datafactura` date NOT NULL,
  `id_iva` varchar(9) NOT NULL,
  `cliente` varchar(30) NOT NULL,
  `enderezo` varchar(50) NOT NULL,
  `poboacion` varchar(30) NOT NULL,
  `provincia` varchar(30) NOT NULL,
  `cod_postal` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tcabsfacturas`
--

INSERT INTO `tcabsfacturas` (`id_factura`, `numfactura`, `datafactura`, `id_iva`, `cliente`, `enderezo`, `poboacion`, `provincia`, `cod_postal`) VALUES
(1, '1A/2019', '2019-01-09', '11111111A', 'Pedro Perez Chousa', 'Moraña 23', 'Moraña', 'Pontevedra', '36110'),
(2, '2A/2019', '2019-01-10', '22222222B', 'Ana Miranda Lodeiro', 'Portela 27, Baixo', 'Marín', 'Pontevedra', '36145'),
(4, '2A/2019', '2019-02-15', '22222222B', 'Ana Miranda Lodeiro', 'Portela 27, Baixo', 'Marín', 'Pontevedra', '36145'),
(5, '4A/2019', '2019-03-05', '33333333C', 'Maria Pino Lopez', 'Portela 27, Baixo', 'Marín', 'Pontevedra', '36145'),
(11, '6A/2019', '2019-03-09', '11111111A', 'Pedro Perez Chousa', 'Moraña 23', 'Moraña', 'Pontevedra', '36110'),
(12, '7A/2019', '2019-03-11', '11111111A', 'Pedro Perez Chousa', 'Moraña 23', 'Moraña', 'Pontevedra', '36110'),
(13, '8A/2019', '2019-03-12', '33333333C', 'Maria Pino Lopez', 'Portela 27, Baixo', 'Marín', 'Pontevedra', '36145');

-- --------------------------------------------------------

--
-- Table structure for table `tlinsfacturas`
--

CREATE TABLE `tlinsfacturas` (
  `id_linea_factura` int NOT NULL,
  `id_factura` int NOT NULL,
  `cod_produto` varchar(15) NOT NULL,
  `produto` varchar(50) NOT NULL,
  `notas` text NOT NULL,
  `cantidade` double NOT NULL,
  `prezo` double NOT NULL,
  `dto` double NOT NULL,
  `ive` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tlinsfacturas`
--

INSERT INTO `tlinsfacturas` (`id_linea_factura`, `id_factura`, `cod_produto`, `produto`, `notas`, `cantidade`, `prezo`, `dto`, `ive`) VALUES
(1, 1, 'SERVET067', 'Desparasitacion interna', '', 2, 27.42, 0, 21),
(2, 1, 'SERVET001', 'Cirugia', '', 1, 90, 0, 21),
(4, 2, 'SERVET002', 'Consulta', '', 1, 55, 0, 21),
(6, 4, 'MEDANA023', 'Dosis Cydectin', 'VIA ORAL DURANTE 45 DÍAS. 0.4 MG/ KG, MEZCLADO CON SUERO FISIOLOGICO AL 50 %.', 6, 9.67, 0, 10),
(8, 4, 'SERVET044', 'Revision', '', 1, 70, 5, 21),
(9, 4, 'SERVET002', 'Consulta', '', 1, 55, 5, 21),
(10, 5, 'SERVET002', 'Consulta', '', 1, 55, 0, 21),
(20, 11, 'SERVET044', 'Revision', '', 1, 70, 0, 21),
(52, 12, 'SERVET068', 'Desparasitacion externa', '', 8, 19.65, 0, 21),
(54, 13, 'SERVET006', 'Ovariohisterectomia', '', 1, 98.2, 0, 21),
(56, 13, 'SERVET004', 'Vacunacion', '', 1, 37.68, 5, 21),
(57, 13, 'MEDANA211', 'Covinan', '', 1, 7.21, 0, 10);

-- --------------------------------------------------------

--
-- Table structure for table `vacinacions`
--

CREATE TABLE `vacinacions` (
  `codVacinacion` int NOT NULL,
  `chip` varchar(20) NOT NULL,
  `codVacina` int NOT NULL,
  `data` date NOT NULL,
  `observacions` varchar(100) NOT NULL,
  `dose` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vacinacions`
--

INSERT INTO `vacinacions` (`codVacinacion`, `chip`, `codVacina`, `data`, `observacions`, `dose`) VALUES
(1, '2233', 2, '2022-12-12', 'Proba', 1),
(2, '4577', 3, '2023-04-03', '', 1),
(3, '2233', 4, '2023-04-01', '', 3);

-- --------------------------------------------------------

--
-- Table structure for table `vacinas`
--

CREATE TABLE `vacinas` (
  `codVacina` int NOT NULL,
  `nomeVacina` varchar(50) NOT NULL,
  `numTotalDoses` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vacinas`
--

INSERT INTO `vacinas` (`codVacina`, `nomeVacina`, `numTotalDoses`) VALUES
(1, 'Parvo-moquillo', 3),
(2, 'Leptospirosis', 2),
(3, 'Hepatite', 1),
(4, 'Rabia', 1),
(5, 'Tos de Canel', 3),
(6, 'Herpes Canino', 2),
(7, 'Enfermidade de Lyme', 1),
(8, 'Dirofilariose', 2),
(9, 'Parainfluenza', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cans`
--
ALTER TABLE `cans`
  ADD PRIMARY KEY (`chip`),
  ADD KEY `BB` (`codRaza`),
  ADD KEY `CC` (`dniPropietario`);

--
-- Indexes for table `citasperrucaria`
--
ALTER TABLE `citasperrucaria`
  ADD PRIMARY KEY (`codCita`),
  ADD KEY `EE` (`chip`);

--
-- Indexes for table `operacions`
--
ALTER TABLE `operacions`
  ADD PRIMARY KEY (`codOperacion`),
  ADD KEY `FF` (`chip`);

--
-- Indexes for table `propietarios`
--
ALTER TABLE `propietarios`
  ADD PRIMARY KEY (`dni`);

--
-- Indexes for table `razas`
--
ALTER TABLE `razas`
  ADD PRIMARY KEY (`codRaza`);

--
-- Indexes for table `tcabsfacturas`
--
ALTER TABLE `tcabsfacturas`
  ADD UNIQUE KEY `id_factura` (`id_factura`);

--
-- Indexes for table `tlinsfacturas`
--
ALTER TABLE `tlinsfacturas`
  ADD UNIQUE KEY `id_linea_factura` (`id_linea_factura`);

--
-- Indexes for table `vacinacions`
--
ALTER TABLE `vacinacions`
  ADD PRIMARY KEY (`codVacinacion`),
  ADD KEY `AA` (`codVacina`),
  ADD KEY `DD` (`chip`);

--
-- Indexes for table `vacinas`
--
ALTER TABLE `vacinas`
  ADD PRIMARY KEY (`codVacina`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `citasperrucaria`
--
ALTER TABLE `citasperrucaria`
  MODIFY `codCita` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `operacions`
--
ALTER TABLE `operacions`
  MODIFY `codOperacion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `razas`
--
ALTER TABLE `razas`
  MODIFY `codRaza` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `vacinacions`
--
ALTER TABLE `vacinacions`
  MODIFY `codVacinacion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `vacinas`
--
ALTER TABLE `vacinas`
  MODIFY `codVacina` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cans`
--
ALTER TABLE `cans`
  ADD CONSTRAINT `BB` FOREIGN KEY (`codRaza`) REFERENCES `razas` (`codRaza`),
  ADD CONSTRAINT `CC` FOREIGN KEY (`dniPropietario`) REFERENCES `propietarios` (`dni`);

--
-- Constraints for table `citasperrucaria`
--
ALTER TABLE `citasperrucaria`
  ADD CONSTRAINT `EE` FOREIGN KEY (`chip`) REFERENCES `cans` (`chip`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `operacions`
--
ALTER TABLE `operacions`
  ADD CONSTRAINT `FF` FOREIGN KEY (`chip`) REFERENCES `cans` (`chip`);

--
-- Constraints for table `vacinacions`
--
ALTER TABLE `vacinacions`
  ADD CONSTRAINT `AA` FOREIGN KEY (`codVacina`) REFERENCES `vacinas` (`codVacina`),
  ADD CONSTRAINT `DD` FOREIGN KEY (`chip`) REFERENCES `cans` (`chip`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
