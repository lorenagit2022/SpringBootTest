-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-10-2022 a las 15:04:23
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
(2, 44556677, 'AHORROS', '1000', 1, 2),
(12, 112233, 'CORRIENTE', '1000', 1, 2);

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
(13);

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
  `ID_CUENTA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `ID` int(11) NOT NULL,
  `IDENTIFICACION` int(11) NOT NULL,
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
(2, 1112213324, 'TESTremoteCambio', 'MASCULINO', 25, 'TESTCALLE TEST', 12345678, 'TESTREMOTE', 1, 'CLIENTE'),
(3, 1112223321, 'TESTremoteCambio', 'MASCULINO', 25, 'TESTCALLE TEST', 12345678, 'TESTREMOTE', 1, 'CLIENTE'),
(5, 987654321, 'PRUEBA', 'FEMENINO', 25, 'PRUEBA', 221234567, 'PRUEBA', 1, ''),
(6, 1112213327, 'TESTremoteCamb', 'MASCULINO', 25, 'TESTCALLE TEST', 12345678, 'TESTREMOTE', 1, 'CLIENTE'),
(7, 456789132, 'TEST2', 'FEMENINO', 30, 'TEST2 CALLE ', 221234567, 'TEST2', 1, 'CLIENTE'),
(8, 1524567532, 'TESTremoteOtro', 'MASCULINO', 25, 'TESTCALLE TEST', 12345678, 'TESTREMOTE', 1, 'CLIENTE'),
(9, 1111122222, 'TESTremotess', 'MASCULINO', 25, 'TESTCALLE TEST', 12345678, 'TESTREMOTE', 0, 'CLIENTE'),
(10, 11111111, 'TESTremoteCambio', 'MASCULINO', 25, 'TESTCALLE TEST', 12345678, 'TESTREMOTE', 1, 'CLIENTE');

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
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
