README — Proyecto de Automatización con Selenium, JUnit y Maven

Descripción del proyecto

Este proyecto implementa un conjunto de pruebas automatizadas sobre la web SauceDemo, utilizando:

Java 22

Selenium WebDriver 4

JUnit 5

Maven

Chrome en modo incógnito (porque si no, da problemas con la contraseña)

El objetivo es validar funcionalidades clave del flujo de compra:

Inicio de sesión

Visualización de productos

Añadir productos al carrito

Comportamiento dinámico de botones

El proyecto sigue el patrón Page Object Model (POM) para mantener un código limpio, escalable y fácil de mantener.

📂 Estructura del proyecto

Modulo_16/

│
├── pom.xml

├── src/

│   ├── main/

│   │   └── java/

│   │   |    └── pages/

│   │   |    |    ├── LoginPage.java

│   │   |     |   └── InventoryPage.java


│   |└── test/

│   |    |└── java/

│    |    |   |└── tests/

│     |    |   |   ├── LoginTest.java

│     |    |    |  |── InventoryTest.java


Tecnologías utilizadas

Tecnología	Uso

Java 22	--> Lenguaje principal

Selenium WebDriver -->	Automatización del navegador

JUnit 5	--> Framework de testing

Maven	--> Gestión de dependencias y ejecución

ChromeDriver	--> Control del navegador Chrome


🚀 Cómo ejecutar los tests
Instalar dependencias:

Código
mvn clean install
Ejecutar todos los tests:

Código
mvn test
Ejecutar un test concreto desde VS Code o IntelliJ usando el panel de Testing.

🧪 Pruebas implementadas
🔐 LoginTest
loginCorrecto()  
Verifica que un usuario válido accede correctamente al inventario.
<img width="1346" height="236" alt="image" src="https://github.com/user-attachments/assets/cecac305-fb46-48c9-8eb4-a07138fbe8bb" />

loginIncorrecto()  
Comprueba que aparece el mensaje de error al introducir credenciales inválidas.

<img width="1451" height="268" alt="image" src="https://github.com/user-attachments/assets/767a5c00-9ea5-4747-ab56-669e4883c150" />

)

🛒 InventoryTest
anadirUnProductoAlCarrito()  
Añade un producto y verifica que el contador del carrito muestra “1”.
<img width="1255" height="197" alt="image" src="https://github.com/user-attachments/assets/a06541a2-d01a-4727-9731-7246c7147636" />

anadirDosProductosAlCarrito()  
Añade dos productos distintos y valida que el contador muestra “2”.
<img width="1227" height="248" alt="image" src="https://github.com/user-attachments/assets/7d4f8f2e-869b-44e0-aba3-1e81af6ae9a7" />

botonCambiaTrasAnadirProducto()  
Comprueba que el botón cambia de Add to cart a Remove tras añadir un producto.
<img width="1258" height="206" alt="image" src="https://github.com/user-attachments/assets/9af48adf-4277-4cef-87fe-05cbaa474d92" />


🧩 Detalles técnicos importantes
✔ Uso de modo incógnito
Se utiliza:

java
options.addArguments("--incognito");
Esto garantiza que:

El carrito empieza siempre vacío

No se arrastra estado entre tests

No aparecen popups persistentes de Chrome

 Limpieza del carrito antes de cada test
Para asegurar independencia entre pruebas:

java
inventoryPage.vaciarCarrito();
Este método:

Entra al carrito

Elimina cualquier producto previo

Regresa al inventario

📘 Reflexión personal

Durante el desarrollo de esta práctica he podido experimentar la necesidad de aislar los test para obtener resultados consistentes.
Ha habido numerosos problemas debido al perfil que estaba usando el programa en el navegador, que no solo ocasionaba que una alerta de google interrumpiese todo el proceso,
si no que al guardarse en la cache los datos de la sesión, la página guardaba los datos de test anteriores y por ello provocaba errores en los siguientes y 
al repetirlos, incluso los que en un principio eran optimos daban error. En ese sentido no solo he tenido que revisar el inspector en numerosas ocasiones para dar con el fallo,
si no que al momento de la prueba me he tenido que fijar en lo que ocurría en pantalla ( lo cual ha sido m´´as fácil con el Thread.sleep) para ubicar el problema.
Esta práctica me ha servido para entender que al momento de realizar los testeos hay que tener en cuenta  elementos tales como la memoria de las aplicaciones, ppues si no se realiza adecuadamente el diseño de los test (con before y aftereach), puede irse acumulando y generar errores que en principio no deberían ocurrir.
