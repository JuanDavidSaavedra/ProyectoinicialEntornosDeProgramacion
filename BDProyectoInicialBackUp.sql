-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: proyectoinicial
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `canchas`
--

DROP TABLE IF EXISTS `canchas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canchas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `deporte` varchar(50) NOT NULL,
  `ubicacion` varchar(150) NOT NULL,
  `precio_hora` double NOT NULL,
  `capacidad` int NOT NULL DEFAULT '10',
  `hora_apertura` time NOT NULL DEFAULT '05:00:00',
  `hora_cierre` time NOT NULL DEFAULT '22:00:00',
  `estado` varchar(20) DEFAULT 'ACTIVA' COMMENT 'ACTIVA, INACTIVA',
  `creado_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canchas`
--

LOCK TABLES `canchas` WRITE;
/*!40000 ALTER TABLE `canchas` DISABLE KEYS */;
INSERT INTO `canchas` VALUES (1,'Cancha Principal Fútbol 5','Fútbol','Bogotá, Localidad de Chapinero, Cr 15 #45-20',45000,10,'05:00:00','22:00:00','ACTIVA','2025-10-17 04:53:31'),(2,'Cancha Sintética El Gol','Fútbol','Medellín, Barrio El Poblado, Cl 10 #40-35',50000,12,'06:00:00','23:00:00','ACTIVA','2025-10-17 04:53:31'),(3,'Cancha Techada Los Deportistas','Fútbol','Cali, Barrio Granada, Av 5N #20-15',40000,8,'05:00:00','21:00:00','ACTIVA','2025-10-17 04:53:31'),(4,'Cancha Básquet Centro','Básquetbol','Barranquilla, Centro, Cr 44 #35-10',30000,15,'05:00:00','22:00:00','ACTIVA','2025-10-17 04:53:31'),(5,'Cancha Voleibol Playa','Voleibol','Cartagena, Bocagrande, Av San Martín #5-60',35000,12,'06:00:00','20:00:00','ACTIVA','2025-10-17 04:53:31'),(6,'Cancha Múltiple','Múltiple','Bucaramanga, Cabecera, Cl 34 #25-40',38000,20,'05:00:00','22:00:00','ACTIVA','2025-10-17 04:53:31'),(7,'Cancha Tenis Club','Tenis','Pereira, Barrio Cuba, Av Circunvalar #12-30',55000,4,'07:00:00','21:00:00','ACTIVA','2025-10-17 04:53:31'),(8,'Cancha Fútbol 7 La Victoria','Fútbol','Bogotá, Localidad de Usaquén, Tv 15 #120-25',48000,14,'05:00:00','23:00:00','ACTIVA','2025-10-17 04:53:31'),(9,'Cancha Sintética Padel','Pádel','Medellín, Laureles, Cr 70 #25-15',42000,4,'08:00:00','20:00:00','INACTIVA','2025-10-17 04:53:31'),(10,'Cancha Rugby Los Andes','Rugby','Bogotá, Localidad de Suba, Cl 145 #90-10',47000,20,'05:00:00','22:00:00','ACTIVA','2025-10-17 04:53:31');
/*!40000 ALTER TABLE `canchas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `cancha_id` int NOT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `estado` varchar(20) DEFAULT 'ACTIVA' COMMENT 'ACTIVA, FINALIZADA, CANCELADA',
  `creado_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `cancha_id` (`cancha_id`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`cancha_id`) REFERENCES `canchas` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,4,1,'2025-10-10','14:00:00','16:00:00','FINALIZADA','2025-10-17 04:53:31'),(2,5,2,'2025-10-12','16:00:00','18:00:00','FINALIZADA','2025-10-17 04:53:31'),(3,6,3,'2025-10-14','10:00:00','12:00:00','FINALIZADA','2025-10-17 04:53:31'),(4,7,4,'2025-10-16','09:00:00','11:00:00','FINALIZADA','2025-10-17 04:53:31'),(5,8,5,'2025-10-16','14:00:00','16:00:00','FINALIZADA','2025-10-17 04:53:31'),(6,9,6,'2025-10-16','18:00:00','20:00:00','FINALIZADA','2025-10-17 04:53:31'),(7,10,1,'2025-10-16','15:00:00','22:00:00','FINALIZADA','2025-10-17 04:53:31'),(8,6,1,'2025-10-20','11:00:00','13:00:00','ACTIVA','2025-10-17 04:53:31'),(9,5,1,'2025-10-22','16:00:00','18:00:00','ACTIVA','2025-10-17 04:53:31'),(10,6,2,'2025-10-17','10:00:00','12:00:00','CANCELADA','2025-10-17 04:53:31'),(11,7,3,'2025-10-19','14:00:00','16:00:00','CANCELADA','2025-10-17 04:53:31');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cedula` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contraseña` varchar(255) NOT NULL COMMENT 'Contraseña encriptada con BCrypt',
  `rol` varchar(20) NOT NULL COMMENT 'ADMIN, OPERATOR, USER',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cedula` (`cedula`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'1005451321','Juan David','juan2214111@correo.uis.edu.co','admin','123','ADMIN','2025-10-17 04:53:31'),(2,'987654321','Ana Operadora','ana.operadora@proyectoinicial.com','anaoperator','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','OPERATOR','2025-10-17 04:53:31'),(3,'456789123','Luis Manager','luis.manager@proyectoinicial.com','luismanager','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','ADMIN','2025-10-17 04:53:31'),(4,'1001234567','María González','maria.gonzalez@email.com','mariagonz','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(5,'1002345678','Juan Pérez','juan.perez@email.com','juanperez','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(6,'1003456789','Laura Martínez','laura.martinez@email.com','lauramart','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(7,'1004567890','Carlos Rodríguez','carlos.rodriguez@email.com','carlosrod','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(8,'1005678901','Ana López','ana.lopez@email.com','analopez','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(9,'1006789012','David Hernández','david.hernandez@email.com','davidher','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(10,'1007890123','Sofia Díaz','sofia.diaz@email.com','sofiadiaz','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(11,'1008901234','Miguel Torres','miguel.torres@email.com','migueltor','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(12,'1009012345','Isabella Ramírez','isabella.ramirez@email.com','isabellar','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31'),(13,'1010123456','Andrés Silva','andres.silva@email.com','andressil','$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345','USER','2025-10-17 04:53:31');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-17  0:02:38