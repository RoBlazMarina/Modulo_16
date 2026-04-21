package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.InventoryPage;
import pages.LoginPage;

class InventoryTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

@BeforeEach
void setUp() throws InterruptedException {
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
options.addArguments("--incognito");
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
