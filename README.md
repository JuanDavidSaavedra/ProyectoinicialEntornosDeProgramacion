# 🏟️ Sistema de Reservas Deportivas - Plataforma Integral

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 📖 Descripción del Proyecto

**Sistema de Reservas Deportivas** es una plataforma web completa diseñada para optimizar la gestión de instalaciones deportivas. Desarrollado como proyecto académico para la asignatura de Entornos de Programación, ofrece una solución integral que conecta administradores y usuarios en un ecosistema deportivo eficiente.


https://github.com/user-attachments/assets/31388d50-1ed3-4819-9732-073b00bfed24


### 🎯 Objetivos Principales
- Digitalizar y automatizar el proceso de reservas deportivas
- Mejorar la experiencia del usuario final
- Optimizar la utilización de instalaciones deportivas
- Proporcionar herramientas avanzadas de gestión para administradores

---

## ✨ Características Destacadas

### 👨‍💻 Para Administradores
- **Gestión Completa de Usuarios**: Control de roles y permisos (ADMIN, OPERATOR, USER)
- **Administración de Canchas**: Configuración de deportes, ubicaciones y tarifas
- **Monitoreo en Tiempo Real**: Visualización instantánea de reservas activas
- **Reportes Avanzados**: Estadísticas detalladas y métricas de uso
- **Configuración Flexible**: Horarios, precios y disponibilidad

### 👥 Para Usuarios
- **Reserva Intuitiva**: Interfaz amigable para reservar canchas disponibles
- **Disponibilidad en Tiempo Real**: Visualización actualizada de horarios
- **Gestión Personal**: Control de reservas activas e historial completo
- **Notificaciones Automáticas**: Recordatorios y confirmaciones
- **Perfil Personalizado**: Preferencias y historial de actividades

---

## 🛠️ Stack Tecnológico

### **Backend**
- **Spring Boot 3.x** - Framework principal de Java
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **Maven** - Gestión de dependencias

### **Frontend**
- **HTML5** - Estructura semántica
- **CSS3** - Estilos y diseño responsive
- **JavaScript** - Interactividad del cliente
- **Bootstrap 5** - Framework CSS
- **Materialize CSS** - Componentes UI

### **Base de Datos**
- **MySQL 8.0** - Sistema gestor de base de datos
- **JPA/Hibernate** - ORM para mapeo objeto-relacional

### **Herramientas de Desarrollo**
- **Git & GitHub** - Control de versiones
- **Azure DevOps** - Gestión ágil (SCRUM)
- **Eclipse/STS** - Entorno de desarrollo

---

## 🗃️ Modelo de Datos

### Diagrama Entidad-Relación
![Diagrama de la Base de Datos](https://github.com/user-attachments/assets/44b89901-667a-409d-9092-09c8432555f4)

### Entidades Principales

#### 👤 **Usuarios**
```sql
- id, nombre, email, contraseña, rol, fecha_registro, estado
- Roles: ADMIN, OPERATOR, USER
````

#### 🏟️ **Canchas**

```sql
- id, nombre, deporte, ubicación, precio_hora, estado, descripción
- Deportes: Fútbol, Tenis, Baloncesto, Vóley, etc.
```

#### 📅 **Reservas**

```sql
- id, usuario_id, cancha_id, fecha, hora_inicio, hora_fin, estado, precio_total
- Estados: ACTIVA, CANCELADA, COMPLETADA
```

---

## 🚀 Instalación y Configuración

### Prerrequisitos

* Java JDK 17 o superior
* MySQL Server 8.0+
* Maven 3.6+
* Git

### Pasos de Instalación

1. **Clonar el Repositorio**

   ```bash
   git clone https://github.com/JuanDavidSaavedra/ProyectoinicialEntornosDeProgramacion.git
   cd ProyectoinicialEntornosDeProgramacion
   ```

2. **Configurar Base de Datos**

   ```sql
   CREATE DATABASE reservas_deportivas;
   CREATE USER 'reservas_user'@'localhost' IDENTIFIED BY 'password';
   GRANT ALL PRIVILEGES ON reservas_deportivas.* TO 'reservas_user'@'localhost';
   ```

3. **Configurar Application Properties**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/reservas_deportivas
   spring.datasource.username=reservas_user
   spring.datasource.password=password
   ```

4. **Ejecutar la Aplicación**

   ```bash
   mvn spring-boot:run
   ```

5. **Acceder al Sistema**

   ```
   http://localhost:8080
   ```

### 🔐 Credenciales de Prueba

* **Administrador**: usuario: `admin` | contraseña: `123`
* **Usuario Normal**: usuario: `mariagonz` | contraseña: `password123`

---

## 📁 Estructura del Proyecto

```
ProyectoinicialEntornosDeProgramacion/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── reservas/
│   │   │           ├── controller/
│   │   │           ├── model/
│   │   │           ├── repository/
│   │   │           ├── service/
│   │   │           └── SecurityConfig.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── img/
│   │       ├── templates/
│   │       └── application.properties
├── sql/
│   └── script.sql
├── pom.xml
└── README.md
```

---

## 🎮 Funcionalidades por Módulo

### 🔐 Módulo de Autenticación

* Registro de nuevos usuarios
* Login seguro con roles
* Recuperación de contraseña
* Gestión de sesiones

### 🏟️ Módulo de Canchas

* Catálogo de canchas disponibles
* Filtros por deporte y ubicación
* Gestión de precios y horarios
* Estados de disponibilidad

### 📅 Módulo de Reservas

* Sistema de reservas en tiempo real
* Calendario interactivo
* Confirmaciones automáticas
* Historial y cancelaciones

### 📊 Módulo de Reportes

* Métricas de uso por cancha
* Estadísticas de reservas
* Reportes financieros
* Análisis de tendencias

---

## 🤝 Metodología de Desarrollo

### 🎯 Enfoque SCRUM

* **Sprints** de 2 semanas
* **Daily Stand-ups** virtuales
* **Review y Retrospectiva** al final de cada sprint
* **Azure Boards** para seguimiento de tareas

### ✅ Criterios de Aceptación

* Código limpio y documentado
* Pruebas unitarias implementadas
* Interfaz responsive y accesible
* Seguridad aplicada en todos los niveles

---

## 🛣️ Roadmap Futuro

### 🚀 Próximas Características

* [ ] App móvil nativa
* [ ] Sistema de pagos en línea
* [ ] Integración con redes sociales
* [ ] Notificaciones push
* [ ] API REST pública
* [ ] Sistema de torneos y ligas

---

## 👥 Equipo de Desarrollo

**Universidad** - Asignatura: Entornos de Programación
**Estudiante**: Juan David Saavedra
**Periodo**: 2024-1

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 📞 Soporte y Contacto

¿Encuentras un error o tienes sugerencias?

* 🐛 **Issues**: [Reportar un problema](https://github.com/JuanDavidSaavedra/ProyectoinicialEntornosDeProgramacion/issues)
* 💬 **Discusiones**: [Foro del proyecto](https://github.com/JuanDavidSaavedra/ProyectoinicialEntornosDeProgramacion/discussions)

---

**⭐ ¿Te gusta este proyecto? ¡Dale una estrella al repositorio!**

---

*Última actualización: Octubre 2025*

```
