-- Script SQL para ProyectoInicial - Reservas de Canchas Deportivas en Colombia
-- Eliminar base de datos si existe y crearla nuevamente
DROP DATABASE IF EXISTS ProyectoInicial;
CREATE DATABASE ProyectoInicial;
USE ProyectoInicial;

-- Crear tabla usuarios
CREATE TABLE usuarios (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cedula VARCHAR(20) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  usuario VARCHAR(50) UNIQUE NOT NULL,
  contraseña VARCHAR(255) NOT NULL COMMENT 'Contraseña encriptada con BCrypt',
  rol VARCHAR(20) NOT NULL COMMENT 'ADMIN, OPERATOR, USER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla canchas (actualizada con capacidad y horarios)
CREATE TABLE canchas (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  deporte VARCHAR(50) NOT NULL,
  ubicacion VARCHAR(150) NOT NULL,
  precio_hora DECIMAL(10,2) NOT NULL,
  capacidad INT NOT NULL DEFAULT 30,
  hora_apertura TIME NOT NULL DEFAULT '05:00:00',
  hora_cierre TIME NOT NULL DEFAULT '22:00:00',
  estado VARCHAR(20) DEFAULT 'ACTIVA' COMMENT 'ACTIVA, INACTIVA',
  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla reservas
CREATE TABLE reservas (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  cancha_id INT NOT NULL,
  fecha DATE NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL,
  estado VARCHAR(20) DEFAULT 'ACTIVA' COMMENT 'ACTIVA, FINALIZADA, CANCELADA',
  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
  FOREIGN KEY (cancha_id) REFERENCES canchas(id) ON DELETE CASCADE
);

-- Insertar administradores (3 administradores)
INSERT INTO usuarios (cedula, nombre, email, usuario, contraseña, rol) VALUES
('1005451321', 'Juan David', 'juan2214111@correo.uis.edu.co', 'admin', '123', 'ADMIN'),
('987654321', 'Ana Operadora', 'ana.operadora@proyectoinicial.com', 'anaoperator', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'OPERATOR'),
('456789123', 'Luis Manager', 'luis.manager@proyectoinicial.com', 'luismanager', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'ADMIN');

-- Insertar clientes (10 clientes)
INSERT INTO usuarios (cedula, nombre, email, usuario, contraseña, rol) VALUES
('1001234567', 'María González', 'maria.gonzalez@email.com', 'mariagonz', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1002345678', 'Juan Pérez', 'juan.perez@email.com', 'juanperez', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1003456789', 'Laura Martínez', 'laura.martinez@email.com', 'lauramart', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1004567890', 'Carlos Rodríguez', 'carlos.rodriguez@email.com', 'carlosrod', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1005678901', 'Ana López', 'ana.lopez@email.com', 'analopez', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1006789012', 'David Hernández', 'david.hernandez@email.com', 'davidher', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1007890123', 'Sofia Díaz', 'sofia.diaz@email.com', 'sofiadiaz', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1008901234', 'Miguel Torres', 'miguel.torres@email.com', 'migueltor', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1009012345', 'Isabella Ramírez', 'isabella.ramirez@email.com', 'isabellar', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER'),
('1010123456', 'Andrés Silva', 'andres.silva@email.com', 'andressil', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'USER');

-- Insertar canchas con capacidades actualizadas (mínimo 30, máximo 50)
INSERT INTO canchas (nombre, deporte, ubicacion, precio_hora, capacidad, hora_apertura, hora_cierre, estado) VALUES
('Cancha Principal Fútbol 5', 'Fútbol', 'Bogotá, Localidad de Chapinero, Cr 15 #45-20', 45000.00, 40, '05:00:00', '22:00:00', 'ACTIVA'),
('Cancha Sintética El Gol', 'Fútbol', 'Medellín, Barrio El Poblado, Cl 10 #40-35', 50000.00, 45, '06:00:00', '23:00:00', 'ACTIVA'),
('Cancha Techada Los Deportistas', 'Fútbol', 'Cali, Barrio Granada, Av 5N #20-15', 40000.00, 35, '05:00:00', '21:00:00', 'ACTIVA'),
('Cancha Básquet Centro', 'Básquetbol', 'Barranquilla, Centro, Cr 44 #35-10', 30000.00, 30, '05:00:00', '22:00:00', 'ACTIVA'),
('Cancha Voleibol Playa', 'Voleibol', 'Cartagena, Bocagrande, Av San Martín #5-60', 35000.00, 30, '06:00:00', '20:00:00', 'ACTIVA'),
('Cancha Múltiple', 'Múltiple', 'Bucaramanga, Cabecera, Cl 34 #25-40', 38000.00, 50, '05:00:00', '22:00:00', 'ACTIVA'),
('Cancha Tenis Club', 'Tenis', 'Pereira, Barrio Cuba, Av Circunvalar #12-30', 55000.00, 30, '07:00:00', '21:00:00', 'ACTIVA'),
('Cancha Fútbol 7 La Victoria', 'Fútbol', 'Bogotá, Localidad de Usaquén, Tv 15 #120-25', 48000.00, 40, '05:00:00', '23:00:00', 'ACTIVA'),
('Cancha Sintética Padel', 'Pádel', 'Medellín, Laureles, Cr 70 #25-15', 42000.00, 30, '08:00:00', '20:00:00', 'INACTIVA'),
('Cancha Rugby Los Andes', 'Rugby', 'Bogotá, Localidad de Suba, Cl 145 #90-10', 47000.00, 50, '05:00:00', '22:00:00', 'ACTIVA');

-- Insertar reservas con fechas actualizadas a octubre 2025
-- Reservas pasadas (antes del 16 de octubre 2025)
INSERT INTO reservas (usuario_id, cancha_id, fecha, hora_inicio, hora_fin, estado) VALUES
(4, 1, '2025-10-10', '14:00:00', '16:00:00', 'FINALIZADA'),  -- Carlos Rodríguez - Cancha Principal (pasada)
(5, 2, '2025-10-12', '16:00:00', '18:00:00', 'FINALIZADA'),  -- Ana López - Cancha Sintética El Gol (pasada)
(6, 3, '2025-10-14', '10:00:00', '12:00:00', 'FINALIZADA'),  -- David Hernández - Cancha Techada (pasada)

-- Reservas para hoy 16 de octubre 2025
(7, 4, '2025-10-16', '09:00:00', '11:00:00', 'ACTIVA'),      -- Sofia Díaz - Cancha Básquet (hoy en la mañana)
(8, 5, '2025-10-16', '14:00:00', '16:00:00', 'ACTIVA'),      -- Miguel Torres - Cancha Voleibol (hoy en la tarde)
(9, 6, '2025-10-16', '18:00:00', '20:00:00', 'ACTIVA'),      -- Isabella Ramírez - Cancha Múltiple (hoy en la noche)

-- Más reservas para mostrar disponibilidad
(10, 1, '2025-10-16', '14:00:00', '16:00:00', 'ACTIVA'),     -- Andrés Silva - Cancha Principal (misma hora que otras)
(4, 1, '2025-10-16', '14:00:00', '16:00:00', 'ACTIVA'),      -- Carlos Rodríguez - Cancha Principal (misma hora)
(5, 1, '2025-10-16', '14:00:00', '16:00:00', 'ACTIVA'),      -- Ana López - Cancha Principal (misma hora)

-- Reservas futuras (después del 16 de octubre 2025)
(10, 7, '2025-10-18', '15:00:00', '17:00:00', 'ACTIVA'),     -- Andrés Silva - Cancha Tenis (futura)
(4, 8, '2025-10-20', '11:00:00', '13:00:00', 'ACTIVA'),      -- Carlos Rodríguez - Cancha Fútbol 7 (futura)
(5, 1, '2025-10-22', '16:00:00', '18:00:00', 'ACTIVA'),      -- Ana López - Cancha Principal (futura)

-- Algunas reservas canceladas
(6, 2, '2025-10-17', '10:00:00', '12:00:00', 'CANCELADA'),   -- David Hernández - Cancha Sintética (cancelada)
(7, 3, '2025-10-19', '14:00:00', '16:00:00', 'CANCELADA');   -- Sofia Díaz - Cancha Techada (cancelada)
