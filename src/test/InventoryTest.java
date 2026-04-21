

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {

    public class LoginTest {

        private WebDriver driver;
        private LoginPage loginPage;

        // Abre el navegador
        @BeforeEach
        void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            loginPage = new LoginPage(driver);

        }

        // Cierra el navegador
        @AfterEach
        void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Test
    void anadirUnProductoAlCarrito() throws InterruptedException {

        inventoryPage.anadirCarrito("Sauce Labs Backpack");
        Thread.sleep(2000);

        assertEquals(1, inventoryPage.getNumeroCarrito(),
                "El carrito debería mostrar 1 producto");
    }

    @Test
    void anadirDosProductosAlCarrito() throws InterruptedException {

        inventoryPage.anadirCarrito("Sauce Labs Backpack");
        inventoryPage.anadirCarrito("Sauce Labs Bike Light");
        Thread.sleep(2000);

        assertEquals(2, inventoryPage.getNumeroCarrito(),
                "El carrito debería mostrar 2 productos");
    }

    @Test
    void botonCambiaTrasAnadirProducto() throws InterruptedException {

        inventoryPage.anadirCarrito("Sauce Labs Backpack");
        Thread.sleep(2000);

        assertTrue(inventoryPage.botonQuitarVisible("Sauce Labs Backpack"),
                "El botón debería cambiar a Remove");
    }
    }

}
