# 🚗 UrbeFlow API - Sistema Inteligente de Peajes Concurrentes

UrbeFlow es el motor central de una solución de peajes urbanos de alta velocidad diseñada para procesar millones de transacciones simultáneas sin degradación de rendimiento. Este microservicio expone una API REST profesional, estructurada bajo una arquitectura desacoplada y preparada para soportar cargas masivas de tráfico concurrente utilizando estructuras de datos atómicas no bloqueantes.

---

## 🏗️ Arquitectura y Decisiones de Diseño

El proyecto está estructurado siguiendo las mejores prácticas de la industria y la arquitectura limpia:

* **Capa de Modelos Polimórficos (`model`):** Se utiliza herencia y estrategias de mapeo de tabla única (`SINGLE_TABLE`) con JPA/Hibernate para gestionar diferentes tipos de vehículos (`Carro`, `Camion`, `Ambulancia`) calculando sus tarifas dinámicamente en tiempo de ejecución.
* **Alta Concurrencia Seguro para Hilos (`service`):** En lugar de utilizar sincronización pesada (`synchronized`) o bloqueos mutuos que ralentizan el sistema, se implementó `java.util.concurrent.atomic.DoubleAdder`. Esto distribuye la carga en celdas de memoria dinámicas, garantizando un recaudo masivo thread-safe a máxima velocidad.
* **Seguridad de Datos (`dto`):** Aislamiento total de las entidades de la base de datos mediante Objetos de Transferencia de Datos (`RegistroVehiculoDTO`) para la recepción de peticiones externas.
* **Resiliencia y Control Global (`exception`):** Incorporación de un interceptor centralizado (`@RestControllerAdvice`) que captura errores en tiempo de ejecución y unifica las respuestas en un formato JSON estándar y seguro, evitando la fuga de trazas internas del servidor.

---

## 🛠️ Stack Tecnológico

* **Java 17 / 21**
* **Spring Boot 3.x** (Spring Web, Spring Data JPA)
* **H2 Database** (Persistencia local en fichero seguro)
* **Maven** (Gestor de dependencias)

---

## 🚀 Instalación y Ejecución

### Prerrequisitos
* Tener instalado JDK 17 o superior.
* Un gestor de dependencias Maven instalado (o utilizar el Maven Wrapper `./mvnw` incluido).

### Pasos para correr el proyecto
1. Clona este repositorio:
   ```bash
   git clone [https://github.com/TU_USUARIO/urbeflow-api.git](https://github.com/TU_USUARIO/urbeflow-api.git)
   cd urbeflow-api
