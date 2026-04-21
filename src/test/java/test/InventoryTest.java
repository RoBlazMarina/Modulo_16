package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.InventoryPage;
import pages.LoginPage;

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

            InventoryPage.anadirCarrito("Sauce Labs Backpack");
            Thread.sleep(2000);

            assertEquals(1, InventoryPage.getNumeroCarrito(),
                    "El carrito debería mostrar 1 producto");
        }

        @Test
        void anadirDosProductosAlCarrito() throws InterruptedException {

            InventoryPage.anadirCarrito("Sauce Labs Backpack");
            InventoryPage.anadirCarrito("Sauce Labs Bike Light");
            Thread.sleep(2000);

            assertEquals(2, InventoryPage.getNumeroCarrito(),
                    "El carrito debería mostrar 2 productos");
        }

        @Test
        void botonCambiaTrasAnadirProducto() throws InterruptedException {

            InventoryPage.anadirCarrito("Sauce Labs Backpack");
            Thread.sleep(2000);

            assertTrue(InventoryPage.botonQuitarVisible("Sauce Labs Backpack"),
                    "El botón debería cambiar a Remove");
        }
    }

}
