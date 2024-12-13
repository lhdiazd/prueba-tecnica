# Proyecto Prueba técnica

A continuación se detallan las instrucciones y requisitos necesarios para compilar y ejecutar la aplicación.

## Tecnologías Usadas
- **Java**: 17
- **Spring Boot**: Framework para la creación de aplicaciones Java basadas en servicios.
- **JPA**: Java Persistence API para la gestión de la persistencia de datos, sin la dependencia de Hibernate.
- **H2**: Base de datos en memoria ligera, ideal para desarrollos rápidos y pruebas.
- **Java Swing**: Biblioteca para la creación de interfaces gráficas en Java.

## Requisitos
Para compilar y ejecutar el proyecto necesitas tener instalada la siguiente tecnología:
- **Java**: Se requiere Java 17 o superior. Puedes verificar tu versión con el comando `java -version`.
- **Maven**: Herramienta de administración de dependencias y construcción para proyectos Java. Verifica tu versión con `mvn -version`.

### Instalación y Ejecución
1. **Clonar el Proyecto**:
   1. Abre una terminal o consola.
   2. Usa el siguiente comando para clonar el proyecto desde GitHub (o cualquier otra plataforma de control de versiones):

   ```bash
   git clone https://github.com/lhdiazd/prueba-tecnica.git

2. **Instalar Dependencias:**:
   1. Navega al directorio del proyecto.
   2. El comando "mvn clean install" compilará y ejecutará los tests unitarios. Es preferible utilizar el siguiente comando:
   ```bash
   mvn clean install -DskipTests

3. **Compilar y Ejecutar la Aplicación:**:
   ```bash
   mvn spring-boot:run
   
