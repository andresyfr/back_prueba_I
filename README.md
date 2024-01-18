# back_prueba_I
Si tienes cierta sitúa (Ur) y ya le diste 2 oportunidades ni le des mas chance haz el reporte si no sabes dónde escríbeme ;) éxitos! ---> se encarga de revisar los prestamos de libros de una biblioteca y demás ...

## Tencnologías a usar:
- Java version: 1.8
- Gradle version: 6.8.3
- Spring Boot version: 2.2.1.RELEASE

## Las Api's las puedes testear con Swagger en el siguiente recurso si te encuentras en local:
http://localhost:8000/swagger-ui.html

## Otros enlaces utiles para testear api's y bases de datos:
http://localhost:8080/v2/api-docs
http://localhost:8080/h2-console

## Algunos comandos utiles:
cd biblioteca; chmod +x ./gradlew; ./gradlew --info clean bootRun
cd biblioteca; chmod +x ./gradlew; ./gradlew --info clean test
cd biblioteca; chmod +x ./gradlew; ./gradlew --info clean build

## Archivos de solo lectura:
Estos archivos no podrán ser modificados por usted
- _src/test/java/com/andresyfr/biblioteca/PrestamoTests_: en esta clase se encuentran las pruebas automatizadas encargadas de calificar la prueba, usted podrá ir ejecutando estas pruebas para ir verificando el avance y completitud de la solución. Pero no podrá modificar nada de esta clase.

## Problema de negocio
El sistema busca automatizar el comportamiento de un bibliotecario cuando un usuario
desea prestar un libro.

Un préstamo es representado en nuestro negocio por los siguientes atributos

**isbn**: identificador único de un libro (campo alfanumérico de máximo 10 dígitos)  
**identificacionUsuario**: número de la identificación del usuario (campo alfanumérico de maximo 10 digitos)  
**tipoUsuario**: determina la relación que tiene el usuario con la biblioteca, corresponde a un campo que puede tener solo alguno de los siguientes dígitos numérico  
1. usuario afilado
2. usuario empleado de la biblioteca
3. usuario invitado

## Objetivo
Crear una API tipo REST la cual permita llevar a cabo las siguientes funcionalidades
1. El Path debe ser `/prestamo`  y el método HTTP tipo **POST**: permite crear un prestamo con las siguientes validaciones
    1. Un usuario invitado solo puede tener un libro prestado en la biblioteca, si un usuario invitado intenta prestar más de un libro debería retornar un error HTTP 400 con el siguiente json.  
       **Para verificar si un usuario ya tiene un préstamo se debe usar el campo _identificacionUsuario_**
        ```json
            {
             "mensaje" : "El usuario con identificación xxxxxx ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo"
            }
        ```       
       Donde **xxxxxx** corresponde a la identificación del usuario que intenta hacer el prestamo
    2. Al momento de realizar el préstamo se debe hacer el cálculo de la fecha máxima de devolución del libro, con la siguiente reglas de negocio
        1. Si el préstamo lo hace un usuario tipo **afiliado** la fecha de devolución debería ser la fecha actual más 10 días sin contar sábados y domingos
        2. Si el préstamo lo hace un usuario tipo **empleado** la fecha de devolución debería ser la fecha actual más 8 días sin contar sábados y domingos
        3. Si el préstamo lo hace un usuario tipo **invitado** la fecha de devolución debería ser la fecha actual más 7 días sin contar sábados y domingos  
        **Esta fecha deberá ser almacenada en la base de datos junto con toda la información del préstamo**
   3. Si en el campo **tipoUsuario** llega un valor diferente a los permitidos, deberá retornar un un error HTTP 400 con el siguiente JSON
        ```json
            {
              "mensaje" : "Tipo de usuario no permitido en la biblioteca"
            }
        ```
   **Ejemplo de petición y respuesta exitosa**  
   Petición  path: `/prestamo` método: **POST**
   ```json
    {
        "isbn":"DASD154212",
        "identificaciónUsuario":"154515485",
        "tipoUsuario":1
    }
    ```
   **Respuesta exitosa**
    ```json
        {
            "id": 1,
            "fechaMaximaDevolucion" : "15/02/2021"
        }
    ```
2. El path debe ser `/prestamo/{id-prestamo}` y el método HTTP tipo **GET**, donde la variable  {id-prestamo} corresponde al identificador con el cual se almacenó el préstamo en la base de datos, explicado en el primer punto.
   El siguiente es un ejemplo de petición y un ejemplo de cómo debería ser la respuesta en un caso exitoso  
   Petición  path: `/prestamo/1` método: **GET**
   ```json
        {
            "id": 1,	
            "isbn":"DASD154212",
            "identificaciónUsuario":"154515485",
            "tipoUsuario":1,
             "fechaMaximaDevolucion" : "15/02/2021"
        }
    ```
## Restricciones técnicas
1. La base de datos debe ser en memoria, en el archivo application.properties ya se encuentra la configuración la cual está soportada por el motor H2, si necesita modificar estos archivos o algo de la conexión asegúrese de que sea una base de datos en memoria.
    1. Si necesita ejecutar sentencias DDL(crear tablas, modificar tablas...) antes de que la aplicación se ejecute, debe crear un archivo llamado _schema.sql_ en la carpeta _src/main/resources_ y spring automáticamente lo ejecutará

## Conceptos a evaluar
1. **Cumpliento de los requerimientos**: para esto hay 6 pruebas automatizadas en la clase PrestamoTests ubicada en el paquete src/test/java, las cuales son las encargadas de validar que usted cumpla con cada uno de los requerimientos. Estas pruebas se encuentran fallando y su objetivo es hacerlas funcionar correctamente.  
2. **Código limpio**: valoramos que su código sea mantenible y con principios de código limpio.  
3. **Arquitectura**: valoramos que su arquitectura propuesta demuestre una correcta separación de responsabilidades.
   1. Te recomendamos hacer uso del principio de responsabilidad única
   2. Te recomendamos usar un patrón de arquitectura, como arquitectura hexagonal, arquitectura limpia o MVC.
   3. Trata de no poner la lógica de negocio en los controladores, separa tu logica de acuerdo a las restricciones del patrón de arquitectura seleccionado.
4. **Pruebas unitarias y de integración (deseable)**: valoramos si logra construir pruebas unitarias y de integración a su lógica de negocio.
5. **Si el sistema identifica que la prueba no ha sido desarrollada por usted inmediatamente se cancela el proceso de selección**
           
##Final de los tests en local:

./biblioteca/build/reports/tests/test
:test (Thread[Execution worker for ':',5,main]) completed. Took 28.146 secs.
:check (Thread[Execution worker for ':',5,main]) started.

> Task :check
Skipping task ':check' as it has no actions.
:check (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.
:build (Thread[Execution worker for ':',5,main]) started.

> Task :build
Skipping task ':build' as it has no actions.
:build (Thread[Execution worker for ':',5,main]) completed. Took 0.0 secs.

BUILD SUCCESSFUL in 44s
7 actionable tasks: 7 executed

## links de ayuda a nivel visual

1. **Funcionamiento de APIs con swagger**:
al iniciar el proyecto con :

./gradlew --info clean bootRun

podremos testear los controladores y el
funcionamiento del aplicativo en la siguiente ruta:

http://localhost:8000/swagger-ui.html

1. **Funcionamiento de base de datos con H2**:

primero ingresar al siguiente recurso:

http://localhost:8000/h2/

posterior a ello en la sección JDBC URL:
escibrir lo siguiente:
jdbc:h2:mem:./src/main/resources/db/biblioteca

luego presionar en test y debe mostrar un mensaje sucess

para posterior a ello iniciar la conexión y realizar lo necesario

##Algunos ejemplos

respuesta post:
curl -X POST "http://localhost:8000/prestamo" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"fechaMaximaDevolucion\": \"string\",  \"id\": 0,  \"identificacionUsuario\": \"125454\",  \"isbn\": \"1254fcgh\",  \"tipoUsuario\": 1}"

response:
{
  "id": 1,
  "fechaMaximaDevolucion": "15/01/2024"
}

respuesta get:
curl -X GET "http://localhost:8000/prestamo/1" -H  "accept: */*"

response:
{
  "id": 1,
  "isbn": "1254fcgh",
  "identificacionUsuario": "125454",
  "tipoUsuario": 1,
  "fechaMaximaDevolucion": "15/01/2024"
}

///////////////////////////////////////////////////////////////////////////