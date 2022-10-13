-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-10-2022 a las 17:48:25
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cuentas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `ID` int(11) NOT NULL,
  `NUMERO_CUENTA` int(11) NOT NULL,
  `TIPO_CUENTA` varchar(35) NOT NULL,
  `SALDO_INICIAL` decimal(10,0) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`ID`, `NUMERO_CUENTA`, `TIPO_CUENTA`, `SALDO_INICIAL`, `ESTADO`, `ID_CLIENTE`) VALUES
(22, 123456, 'AHORROS', '2500', 1, 15),
(46, 456123, 'AHORROS', '2000', 1, 44),
(50, 225487, 'CORRIENTE', '0', 1, 42),
(51, 495878, 'AHORROS', '0', 1, 15),
(53, 496825, 'AHORROS', '1000', 1, 42),
(55, 585545, 'CORRIENTE', '1635', 1, 44);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(62);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `ID` int(11) NOT NULL,
  `TIPO_MOVIMIENTO` varchar(20) NOT NULL,
  `FECHA_MOVIMIENTO` date NOT NULL,
  `VALOR` double NOT NULL,
  `SALDO_DISPONIBLE` double NOT NULL,
  `ID_CUENTA` int(11) NOT NULL,
  `ES_ULTIMO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`ID`, `TIPO_MOVIMIENTO`, `FECHA_MOVIMIENTO`, `VALOR`, `SALDO_DISPONIBLE`, `ID_CUENTA`, `ES_ULTIMO`) VALUES
(41, 'C', '2022-10-01', 1000, 1000, 22, 0),
(54, 'C', '2022-10-02', 1500, 2500, 22, 1),
(56, 'C', '2022-10-04', 2000, 2000, 55, 0),
(57, 'D', '2022-10-07', 575, 1425, 55, 0),
(58, 'C', '2022-10-10', 600, 2025, 55, 0),
(59, 'C', '2022-10-12', 150, 2175, 55, 0),
(60, 'D', '2022-10-12', 540, 1635, 55, 1),
(61, 'C', '2022-10-13', 2000, 2000, 46, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `ID` int(11) NOT NULL,
  `IDENTIFICACION` varchar(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `GENERO` varchar(20) NOT NULL,
  `EDAD` int(11) NOT NULL,
  `DIRECCION` varchar(80) NOT NULL,
  `TELEFONO` int(11) NOT NULL,
  `CONTRASENA` varchar(20) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `TIPO_PERSONA` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`ID`, `IDENTIFICACION`, `NOMBRE`, `GENERO`, `EDAD`, `DIRECCION`, `TELEFONO`, `CONTRASENA`, `ESTADO`, `TIPO_PERSONA`) VALUES
(15, '1234567890', 'Juan Osorio', 'MASCULINO', 25, '13 junio y Equinoccial ', 98874587, '1245', 1, 'CLIENTE'),
(42, '9876543210', 'Marianela Montalvo', 'FEMENINO', 29, 'Amazonas y NNUU ', 97548965, '5678', 1, 'CLIENTE'),
(44, '5467891235', 'Jose Lema ', 'MASCULINO', 20, 'otavalo sn y principal ref bomberos ', 98254785, '1234', 1, 'CLIENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `account_number` int(11) DEFAULT NULL,
  `account_state` bit(1) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `movementbbalance` double DEFAULT NULL,
  `movement_date` datetime DEFAULT NULL,
  `movement_type` varchar(255) DEFAULT NULL,
  `name_client` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NUMERO_CUENTA_UK` (`NUMERO_CUENTA`),
  ADD KEY `ID_CLIENTE_FK` (`ID_CLIENTE`);

--
-- Indices de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_CUENTA_FK` (`ID_CUENTA`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICACION_UK` (`IDENTIFICACION`),
  ADD UNIQUE KEY `ID_UK` (`ID`) USING BTREE;

--
-- Indices de la tabla `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `ID_CLIENTE_FK` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `persona` (`ID`);

--
-- Filtros para la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD CONSTRAINT `ID_CUENTA_FK` FOREIGN KEY (`ID_CUENTA`) REFERENCES `cuenta` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
