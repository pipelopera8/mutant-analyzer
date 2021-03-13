# Prueba de ingreso Mercadolibre para Felipe Lopera

## Introducción
El ejercicio plantea poder detectar mediante una API REST, si una cadena de ADN entrante le pertenece a un mutante o no y al final del ejercicio tener estadísticas de la cantidad de mutantes que se encontraron durante el proceso.

## Aplicación

- Comprobación de la cadena de ADN:

Ejecutar un ```POST``` al siguiente endpoint:
http://54.83.227.42:30001/mutant
con el siguiente body:
```
{
  "dna":["ATGCAA", "CGGATA", "TAATGT", "AAGGGG", "CCCTTA", "TTACTT"]
}
```
Los códigos de respuestas que se obtienen en este recurso son:

```200 OK``` Si el ADN enviado en la cadena corresponde a un mutante.

```403 FORBIDDEN``` Si el ADN enviado en la cadena corresponde a un humano.

- Estadísticas:

Ejecutar un ```GET``` al siguiente endpoint:

http://54.83.227.42:30001/stats

Responde un body con la siguiente información:
```
{
  "ratio":1,"count_mutant_dna":0,"count_human_dna":4
}
```

## Implementación y desarrollo
La aplicación fue desarrollada en java 1.8, usando Springboot, creando un microservicio en donde se realizó una separación de capas implementando el patrón de diseño mvc.
Se utilizo maven como manejador de dependencias en donde se implementaron las siguientes librerías.

- Connector de H2 (Base de datos en memoria la cual es totalmente compatible con Spring Boot y se adapta a la necesidad del ejercicio
- JaCoCo para el coverage de los test.
- Jpa, el ORM utilizado.

las pruebas unitarias se realizaron con SpringBootTest y para el manejo de logs, Slf4j de lombok.

## Entorno productivo
Se desarrollo una implementación de integración continua, esta implementación corre a través de https://bitbucket.org/ por medio de pipelines en donde por motivo de la prueba solo agregue un paso para que verifique que en los pull request que se hagan a proyecto no se envíen contraseñas quemadas en el código, esto gracias a una herencia de librería de bitbucket.

Se desarrollo una implementación de integración continua, esta implementación corre a través de https://bitbucket.org/ por medio de pipelines, en donde por medio de la secuencia de pasos se genera el depliegue de la siguiente manera:

- Se genera el jar de la aplicación
- Se conecta a aws por medio de crediciales AMI, generadas a través de terraform
- Se inicia sesión en dockerhub 
- Se construye el docker
- Se conecta al servicio de aws de kueberne, previo a esto se construye la infraestructura adecuada en aws con terraform
- despliega la imagen del docker con kubernete

Se adjunta todo el código en la carpeta terraform, y el bitbucket-pipelines.yml los cuales son los encargados de generar tanto la infraestructura como la integración continua, esto funciona de manera automática al hacer un commit en master (me toco publicar el proyecto en bitbucket para hacer las ejecuciones de los pipelines

## Entorno local
Para la ejecución local del proyecto solo se necesita tener java 1.8 y maven instalados, el servicio sube por el puerto 7220


## Coverage
La cobertura de pruebas unitarias supera el 90%, en el proyecto se adjunta el informe generado por la herramienta jacoco en la carpeta doc.

