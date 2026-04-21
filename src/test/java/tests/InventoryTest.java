package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.InventoryPage;
import pages.LoginPage;

class InventoryTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    // Aquí hemos tenido que meter más de lo que se debería porque el navegador
    // estaba dando problemas con pops up sobre contraseña insegura.
    @BeforeEach
    void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/selenium-profile");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
        options.addArguments("--disable-save-password-bubble");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);

        inventoryPage = new InventoryPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        inventoryPage.vaciarCarrito();

    }

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

        assertEquals(1, inventoryPage.getNumeroCarrito());
    }

    @Test
    void anadirDosProductosAlCarrito() throws InterruptedException {

        inventoryPage.anadirCarrito("Sauce Labs Backpack");
        inventoryPage.anadirCarrito("Sauce Labs Bike Light");
        Thread.sleep(2000);

        assertEquals(2, inventoryPage.getNumeroCarrito());
    }

    @Test
    void botonCambiaTrasAnadirProducto() throws InterruptedException {

        inventoryPage.anadirCarrito("Sauce Labs Backpack");
        Thread.sleep(2000);

        assertTrue(inventoryPage.botonQuitarVisible("Sauce Labs Backpack"));
    }
}
