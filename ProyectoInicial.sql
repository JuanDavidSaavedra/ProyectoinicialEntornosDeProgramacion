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
  rol VARCHAR(20) NOT NULL COMMENT 'ADMIN, USER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla canchas
CREATE TABLE canchas (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  deporte VARCHAR(50) NOT NULL,
  ubicacion VARCHAR(150) NOT NULL,
  precio_hora DECIMAL(10,2) NOT NULL,
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
  estado VARCHAR(20) DEFAULT 'ACTIVA' COMMENT 'ACTIVA, CANCELADA',
  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  FOREIGN KEY (cancha_id) REFERENCES canchas(id)
);

-- Insertar administradores (3 administradores)
-- Contraseñas: todas son "password123" encriptadas con BCrypt
INSERT INTO usuarios (cedula, nombre, email, usuario, contraseña, rol) VALUES
('1005451321', 'Juan David', 'juan2214111@correo.uis.edu.co', 'admin', '123', 'ADMIN'),
('987654321', 'Ana Operadora', 'ana.operadora@proyectoinicial.com', 'anaoperator', '$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ012345', 'ADMIN'),
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

-- Insertar canchas (10 canchas en diferentes ciudades de Colombia)
INSERT INTO canchas (nombre, deporte, ubicacion, precio_hora, estado) VALUES
('Cancha Principal Fútbol 5', 'Fútbol', 'Bogotá, Localidad de Chapinero, Cr 15 #45-20', 45000.00, 'ACTIVA'),
('Cancha Sintética El Gol', 'Fútbol', 'Medellín, Barrio El Poblado, Cl 10 #40-35', 50000.00, 'ACTIVA'),
('Cancha Techada Los Deportistas', 'Fútbol', 'Cali, Barrio Granada, Av 5N #20-15', 40000.00, 'ACTIVA'),
('Cancha Básquet Centro', 'Básquetbol', 'Barranquilla, Centro, Cr 44 #35-10', 30000.00, 'ACTIVA'),
('Cancha Voleibol Playa', 'Voleibol', 'Cartagena, Bocagrande, Av San Martín #5-60', 35000.00, 'ACTIVA'),
('Cancha Múltiple', 'Múltiple', 'Bucaramanga, Cabecera, Cl 34 #25-40', 38000.00, 'ACTIVA'),
('Cancha Tenis Club', 'Tenis', 'Pereira, Barrio Cuba, Av Circunvalar #12-30', 55000.00, 'ACTIVA'),
('Cancha Fútbol 7 La Victoria', 'Fútbol', 'Bogotá, Localidad de Usaquén, Tv 15 #120-25', 48000.00, 'ACTIVA'),
('Cancha Sintética Padel', 'Pádel', 'Medellín, Laureles, Cr 70 #25-15', 42000.00, 'INACTIVA'),
('Cancha Rugby Los Andes', 'Rugby', 'Bogotá, Localidad de Suba, Cl 145 #90-10', 47000.00, 'ACTIVA');

-- Insertar reservas (8 reservas interconectadas)
INSERT INTO reservas (usuario_id, cancha_id, fecha, hora_inicio, hora_fin, estado) VALUES
(4, 1, '2024-01-15', '14:00:00', '16:00:00', 'ACTIVA'),  -- Carlos Rodríguez reserva Cancha Principal Fútbol 5
(5, 2, '2024-01-15', '16:00:00', '18:00:00', 'ACTIVA'),  -- Ana López reserva Cancha Sintética El Gol
(6, 3, '2024-01-16', '10:00:00', '12:00:00', 'ACTIVA'),  -- David Hernández reserva Cancha Techada Los Deportistas
(7, 4, '2024-01-16', '12:00:00', '14:00:00', 'CANCELADA'), -- Sofia Díaz reserva Cancha Básquet Centro (cancelada)
(8, 5, '2024-01-17', '15:00:00', '17:00:00', 'ACTIVA'),  -- Miguel Torres reserva Cancha Voleibol Playa
(9, 6, '2024-01-17', '17:00:00', '19:00:00', 'ACTIVA'),  -- Isabella Ramírez reserva Cancha Múltiple
(10, 7, '2024-01-18', '09:00:00', '11:00:00', 'ACTIVA'), -- Andrés Silva reserva Cancha Tenis Club
(4, 8, '2024-01-18', '11:00:00', '13:00:00', 'ACTIVA');  -- Carlos Rodríguez reserva Cancha Fútbol 7 La Victoria

-- Consultas de ejemplo para verificar los datos
SELECT '=== USUARIOS ===' AS '';
SELECT * FROM usuarios;

SELECT '=== CANCHAS ===' AS '';
SELECT * FROM canchas;

SELECT '=== RESERVAS CON DETALLES ===' AS '';
SELECT 
    r.id,
    u.nombre AS usuario,
    c.nombre AS cancha,
    c.deporte,
    r.fecha,
    r.hora_inicio,
    r.hora_fin,
    r.estado,
    c.precio_hora,
    TIMESTAMPDIFF(HOUR, r.hora_inicio, r.hora_fin) * c.precio_hora AS total
FROM reservas r
JOIN usuarios u ON r.usuario_id = u.id
JOIN canchas c ON r.cancha_id = c.id
ORDER BY r.fecha, r.hora_inicio;

-- Consulta específica para ver reservas activas
SELECT '=== RESERVAS ACTIVAS ===' AS '';
SELECT 
    r.id,
    u.nombre AS usuario,
    c.nombre AS cancha,
    r.fecha,
    CONCAT(r.hora_inicio, ' - ', r.hora_fin) AS horario,
    c.ubicacion
FROM reservas r
JOIN usuarios u ON r.usuario_id = u.id
JOIN canchas c ON r.cancha_id = c.id
WHERE r.estado = 'ACTIVA'
ORDER BY r.fecha, r.hora_inicio;